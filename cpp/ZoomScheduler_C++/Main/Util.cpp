#include <Windows.h>
#include <chrono>
#include <fstream>
#include <filesystem>
#include <shlobj.h>
#include <direct.h>
#include <iostream>
#include <string>
#include <vector>

#include "Util.h"

//--------------------------------------------------------------------- getCurrentTime
char* getCurrentTime() {
	time_t rawtime;
	struct tm* timeinfo;
	char buffer[1];
	time(&rawtime);
	timeinfo = localtime(&rawtime);

	strftime(buffer, 80, "%Y/%m/%d %H:%M:%S> ", timeinfo);

	return buffer;
}

std::string getCurrentTimeComp() {
	time_t rawtime;
	struct tm* timeinfo;
	char buffer[1];
	time(&rawtime);
	timeinfo = localtime(&rawtime);

	strftime(buffer, 80, "%H:%M", timeinfo);

	std::string time{ buffer };

	return time;
}

std::string getCurrentDateComp() {
	time_t rawtime;
	struct tm* timeinfo;
	char buffer[1];
	time(&rawtime);
	timeinfo = localtime(&rawtime);

	strftime(buffer, 80, "%Y/%m/%d", timeinfo);

	std::string date{ buffer };

	return date;
}

//--------------------------------------------------------------------- hideConsole
void hideConsole() {
	::ShowWindow(::GetConsoleWindow(), SW_HIDE);
}

//--------------------------------------------------------------------- showConsole
void showConsole() {
	::ShowWindow(::GetConsoleWindow(), SW_SHOW);
}

std::vector<std::string> getFiles() {
    char path[MAX_PATH];
    SHGetFolderPathA(NULL, CSIDL_PROFILE, NULL, 0, path);

    std::string dirPath(path);
    dirPath += "\\ZoomScheduler - DO NOT TAMPER";

	std::vector<std::string> files;

	for (const auto& file : std::filesystem::directory_iterator(dirPath)) {
		std::filesystem::path filePath = file.path();
		files.push_back(filePath.string());
	}

	return files;
}

bool isVisible() {

	char path[MAX_PATH];
	SHGetFolderPathA(NULL, CSIDL_PROFILE, NULL, 0, path);

	std::string dirPath(path);
	dirPath += "\\ZoomScheduler - DO NOT TAMPER";

	std::string filePathTemp{ dirPath + "\\eventViewerVisible.txt" };

	std::fstream inFileDef;
	inFileDef.open(filePathTemp.c_str(), std::ios::in);

	std::string vis;
	getline(inFileDef, vis);

	return vis == "true";
}

inline bool endsIn(std::string const& value, std::string const& ending) {
	if (ending.size() > value.size()) return false;
	return std::equal(ending.rbegin(), ending.rend(), value.rbegin());
}

inline bool fileExists(const std::string& name) {
	struct stat buffer;
	return (stat(name.c_str(), &buffer) == 0);
}