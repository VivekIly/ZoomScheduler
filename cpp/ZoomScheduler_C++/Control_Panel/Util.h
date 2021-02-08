#include <iostream>
#include <string>
#include <Windows.h>
#include <thread>
#include <array>
#include <fstream>
#include "Commands.h"

std::string commands[] = { "-help", "-exit", "-restart", "-create" };

char* getCurrentDateTime() {
	time_t rawtime;
	struct tm* timeinfo;
	char buffer[1];
	time(&rawtime);
	timeinfo = localtime(&rawtime);

	strftime(buffer, 80, "%Y/%m/%d %H:%M:%S> ", timeinfo);

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

void startMain() {
	/*ShellExecute(
	NULL,
	L"open",
	L"C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\Main\\Release\\ZoomScheduler_C++.exe",
	NULL,
	NULL,
	SW_SHOWDEFAULT
);*/

	std::cout << "Opening main application . . .\n";

	std::chrono::steady_clock::time_point begin = std::chrono::steady_clock::now();
	system("start C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\Main\\Release\\ZoomScheduler_C++.exe");
	std::chrono::steady_clock::time_point end = std::chrono::steady_clock::now();

	double seconds = std::chrono::duration_cast<std::chrono::microseconds>(end - begin).count() / 1000000.000000;
	std::cout << "Main application opened in " << seconds << " seconds.\n\n";
}

void getCommand(bool displayInd);

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

	//std::cout << '\r' << std::flush;
}

void getCommand(bool displayInd) {
	if (displayInd)
		std::cout << ">> " << std::flush;
	std::string cmd{};
	std::getline(std::cin, cmd);

	checkSyntax(cmd);

	if (cmd != "-exit") {
		runCommand(cmd);
		getCommand(false);
	}


	std::cout << "Attempting to exit application . . . \n";
	std::this_thread::sleep_for(std::chrono::milliseconds(2000));
}

void hideConsole() {
	::ShowWindow(::GetConsoleWindow(), SW_HIDE);
}

void showConsole() {
	::ShowWindow(::GetConsoleWindow(), SW_SHOW);
}