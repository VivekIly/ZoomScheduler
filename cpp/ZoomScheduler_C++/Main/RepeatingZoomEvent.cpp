#include <iostream>
#include <fstream>
#include <shlobj.h>
#include <direct.h>
#include <string>

#include "RepeatingZoomEvent.h"

inline bool fileExists(const std::string& name) {
	struct stat buffer;
	return (stat(name.c_str(), &buffer) == 0);
}

void RepeatingZoomEvent::setAttributes(std::string name, std::string url, std::string time, std::string days) {
	m_name = name;
	m_url = url;
	m_time = time;
	m_days = days;
}

RepeatingZoomEvent::RepeatingZoomEvent() {
	m_name = "";
	m_url = "";
	m_time = "";
	m_days = "";
}

RepeatingZoomEvent::RepeatingZoomEvent(std::string name, std::string url, std::string time, std::string days) {
	setAttributes(name, url, time, days);
}

void RepeatingZoomEvent::openLink() {
	std::string preout{};
	preout = "start /max chrome.exe " + m_url;
	system(preout.c_str());
}

bool RepeatingZoomEvent::serialize() {

	char path[MAX_PATH];
	SHGetFolderPathA(NULL, CSIDL_PROFILE, NULL, 0, path);

	std::string dirPath(path);
	dirPath += "\\ZoomScheduler - DO NOT TAMPER";

	std::cout << "Attempting to create directory . . .\n";
	if (_mkdir(dirPath.c_str()) == 0)
		std::cout << "Successfully created directory.\n";
	else
		std::cout << "Unable to create directory. It may already exist.\n";

	std::cout << "Generating filepath . . .\n";
	std::string filePath{ dirPath + "\\" + m_days + ".rze" };

	std::cout << "Writing object and members . . .\n";
	std::ofstream outFile;
	outFile.open(filePath.c_str(), std::ios::app);
	outFile << "type code 2\n";
	outFile << this->getName() << '\n';
	outFile << this->getURL() << '\n';
	outFile << this->getTime() << '\n';
	outFile << this->getDays() << '\n';

	std::cout << "Repeating Zoom event \'" << m_name << "\' successfully serialized.\n\n";

	outFile.close();

	return fileExists(filePath);
}

RepeatingZoomEvent RepeatingZoomEvent::fetch(std::string filePath) {

	std::cout << "Fetching data . . .\n";
	std::fstream inFile;
	inFile.open(filePath.c_str(), std::ios::in);

	std::string buf;
	std::string temp_name;
	std::string temp_url;
	std::string temp_days;
	std::string temp_time;

	std::getline(inFile, buf);

	if (buf == "type code 2") {
		std::getline(inFile, temp_name);
		std::getline(inFile, temp_url);
		std::getline(inFile, temp_time);
		std::getline(inFile, temp_days);
	}
	else {
		std::cout << "Incompatible type codes.";
	}

	RepeatingZoomEvent temp(temp_name, temp_url, temp_days, temp_time);

	return temp;
}