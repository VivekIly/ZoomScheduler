#include <iostream>
#include <fstream>
#include <shlobj.h>
#include <direct.h>
#include <string>

#include "Util.h"
#include "ZoomEvent.h"

void ZoomEvent::setAttributes(std::string name, std::string url, std::string date, std::string time) {
	m_name = name;
	m_url = url;
	m_date = date;
	m_time = time;
}

ZoomEvent::ZoomEvent() {
	m_name = "";
	m_url = "";
	m_date = "";
	m_time = "";
}

ZoomEvent::ZoomEvent(std::string name, std::string url, std::string date, std::string time) {
	setAttributes(name, url, date, time);
}

void ZoomEvent::openLink() {
	std::string preout{};
	preout = "start /max chrome.exe " + m_url;
	system(preout.c_str());
}

bool ZoomEvent::serialize() {

	char path[MAX_PATH];
	SHGetFolderPathA(NULL, CSIDL_PROFILE, NULL, 0, path);

	std::string dirPath(path);
	dirPath += "\\ZoomScheduler - DO NOT TAMPER";

	std::cout << "Generating filepath . . .\n";
	std::string filePath{ dirPath + "\\" + m_name + getCurrentTime() + ".ze" };

	std::cout << "Writing object and members . . .\n";
	std::ofstream outFile;
	outFile.open(filePath.c_str(), std::ios::app);
	outFile << "type code 1\n";
	outFile << this->getName() << '\n';
	outFile << this->getURL() << '\n';
	outFile << this->getDate() << '\n';
	outFile << this->getTime() << '\n';

	std::cout << "Zoom event \'" << m_name << "\' successfully serialized.\n\n";

	outFile.close();

	return fileExists(filePath);
}

ZoomEvent ZoomEvent::fetch(std::string filePath) {

	std::cout << "Fetching data . . .\n";
	std::fstream inFile;
	inFile.open(filePath.c_str(), std::ios::in);

	std::string buf;
	std::string temp_name;
	std::string temp_url;
	std::string temp_date;
	std::string temp_time;

	std::getline(inFile, buf);

	if (buf == "type code 1") {
		std::getline(inFile, temp_name);
		std::getline(inFile, temp_url);
		std::getline(inFile, temp_date);
		std::getline(inFile, temp_time);
	}
	else {
		std::cout << "Incompatible type codes.";
	}

	ZoomEvent temp(temp_name, temp_url, temp_date, temp_time);

	return temp;
}