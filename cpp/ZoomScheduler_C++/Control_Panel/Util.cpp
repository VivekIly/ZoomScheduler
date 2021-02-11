#include <string>
#include <iostream>
#include <cstdio>
#include <windows.h>
#include <tlhelp32.h>
#include <shlobj.h>
#include <direct.h>

#include "Util.h"

std::string commands[] = { "-help", "-exit zs", "-restart", "-create", "-toggle", "-toggle def" };

char* getCurrentDateTime() {
	time_t rawtime;
	struct tm* timeinfo;
	char buffer[1];
	time(&rawtime);
	timeinfo = localtime(&rawtime);

	strftime(buffer, 80, "%Y/%m/%d %H:%M:%S> ", timeinfo);

	return buffer;
}

char* getCurrentDateTimeName() {
	time_t rawtime;
	struct tm* timeinfo;
	char buffer[1];
	time(&rawtime);
	timeinfo = localtime(&rawtime);

	strftime(buffer, 80, "%Y%m%d%H%M%S", timeinfo);

	return buffer;
}

char* getCurrentTime() {
	time_t rawtime;
	struct tm* timeinfo;
	char buffer[1];
	time(&rawtime);
	timeinfo = localtime(&rawtime);

	strftime(buffer, 80, "%H:%M:%S> ", timeinfo);

	return buffer;
}

void initialize() {
	std::cout << "Initializing . . .\nGenerating directory path . . .\n";

	char path[MAX_PATH];
	SHGetFolderPathA(NULL, CSIDL_PROFILE, NULL, 0, path);

	std::string dirPath(path);
	dirPath += "\\ZoomScheduler - DO NOT TAMPER";

	std::cout << "Attempting to create directory . . .\n";
	if (_mkdir(dirPath.c_str()) == 0)
		std::cout << "Successfully created directory.\n";
	else
		std::cout << "Unable to create directory. It may already exist.\n";

	if (!IsMainRunning()) {

		std::string filePathDefault{ dirPath + "\\eventViewerDefaultVisibility.txt" };
		std::string filePathTemp{ dirPath + "\\eventViewerVisible.txt" };

		std::cout << "Writing settings . . .\n";

		std::string def;

		if (!fileExists(filePathDefault)) {
			std::ofstream outFileDef;
			outFileDef.open(filePathDefault.c_str());
			outFileDef << "true";
			def = "true";
			outFileDef.close();
		}
		else {
			std::fstream inFileDef;
			inFileDef.open(filePathDefault.c_str(), std::ios::in);

			getline(inFileDef, def);
		}

		std::ofstream outFileTemp;
		outFileTemp.open(filePathTemp.c_str());
		outFileTemp << def;
		outFileTemp.close();
	}
}

void startMainZS() {

	std::cout << "Checking if main application is running . . .\n";

	if (!IsMainRunning()) {

		std::cout << "Main application is not running. Attempting to open main application . . .\n";
		std::chrono::steady_clock::time_point begin = std::chrono::steady_clock::now();
		system("start C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\Main\\Release\\ZoomScheduler_C++.exe");
		std::this_thread::sleep_for(std::chrono::milliseconds(1000));
		std::chrono::steady_clock::time_point end = std::chrono::steady_clock::now();

		if (IsMainRunning()) {
			double seconds = std::chrono::duration_cast<std::chrono::microseconds>(end - begin).count() / 1000000.000000;
			std::cout << "Main application opened and process existence verified in " << seconds << " seconds.\n\n";
		}
		else {
			std::cout << "Main application could not be opened.\n\n";
		}
	}
	else {
		std::cout << "Main application is already running.\n\n";
	}
}

void checkSyntax(std::string command) {
	bool flag{ false };

	if (command.length() == 0)
		getCommand(true);

	for (std::string c : commands) {
		if (command.compare(c) == 0) {
			flag = true;
		}
	}

	if (flag == false) {
		std::cout << "\'" << command << "\' is not recognized as a command.\n\n";
		getCommand(true);
	}
}

void runCommand(std::string& command) {
	if (command == "-help") {
		help();
	}
	else if (command == "-restart") {
		restart();
	}
	else if (command == "-create") {
		create();
	}
	else if (command == "-toggle") {
		toggleMainVisibility();
	}
	else if (command == "-toggle def") {
		toggleDefMainVisibility();
	}

	//std::cout << '\r' << std::flush;
}

void getCommand(bool displayInd) {
	if (displayInd)
		std::cout << ">> " << std::flush;
	std::string cmd{};
	std::getline(std::cin, cmd);

	checkSyntax(cmd);

	if (cmd != "-exit zs") {
		runCommand(cmd);
		getCommand(true);
	}


	std::cout << "Attempting to exit application . . . \n";
	killMain();
	std::cout << "Closing control panel in 5 seconds . . .\n";
	std::this_thread::sleep_for(std::chrono::milliseconds(5000));
}

inline bool fileExists(const std::string& name) {
	struct stat buffer;
	return (stat(name.c_str(), &buffer) == 0);
}

bool IsMainRunning() {
	bool exists = false;
	PROCESSENTRY32 entry;
	entry.dwSize = sizeof(PROCESSENTRY32);

	HANDLE snapshot = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, NULL);

	if (Process32First(snapshot, &entry))
		while (Process32Next(snapshot, &entry))
			if (!_wcsicmp(entry.szExeFile, L"ZoomScheduler_C++.exe"))
				exists = true;

	CloseHandle(snapshot);
	return exists;
}

bool killMain() {
	system("cd \"C:\\Windows\\System32\" && taskkill /IM \"ZoomScheduler_C++.exe\" /F");
	return !IsMainRunning;
}

void hideConsole() {
	::ShowWindow(::GetConsoleWindow(), SW_HIDE);
}

bool toggleMainVisOut() {
	char path[MAX_PATH];
	SHGetFolderPathA(NULL, CSIDL_PROFILE, NULL, 0, path);

	std::string dirPath(path);
	dirPath += "\\ZoomScheduler - DO NOT TAMPER";

	std::string filePathTemp{ dirPath + "\\eventViewerVisible.txt" };

	std::string tempVisibility;

	std::ifstream inFileTemp;
	inFileTemp.open(filePathTemp);

	getline(inFileTemp, tempVisibility);

	std::ofstream outFileTemp;
	outFileTemp.open(filePathTemp.c_str());

	std::string newVis = (tempVisibility == "true") ? "false" : "true";

	outFileTemp << newVis;
	outFileTemp.close();

	return (newVis == "true");
}

bool toggleDefMainVisOut() {
	char path[MAX_PATH];
	SHGetFolderPathA(NULL, CSIDL_PROFILE, NULL, 0, path);

	std::string dirPath(path);
	dirPath += "\\ZoomScheduler - DO NOT TAMPER";

	std::string filePathTemp{ dirPath + "\\eventViewerDefaultVisibility.txt" };

	std::string defVisibility;

	std::ifstream inFileDef;
	inFileDef.open(filePathTemp);

	getline(inFileDef, defVisibility);

	std::ofstream outFileDef;
	outFileDef.open(filePathTemp.c_str());

	std::string newVis = (defVisibility == "true") ? "false" : "true";

	outFileDef << newVis;
	outFileDef.close();

	return (newVis == "true");
}

void showConsole() {
	::ShowWindow(::GetConsoleWindow(), SW_SHOW);
}