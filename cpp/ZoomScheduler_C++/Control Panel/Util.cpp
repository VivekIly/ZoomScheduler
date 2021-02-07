#include <iostream>
#include <string>
#include <thread>
#include <Windows.h>
#include "Util.h"

std::string commands[] = { "-HELP", "-EXIT", };

//--------------------------------------------------------------------- checkSyntax
void checkSyntax(std::string command) {
	bool flag{ false };
	for (std::string c : commands) {
		if (command.compare(c) == 0) {
			flag = true;
		}
	}

	if (flag == false) {
		std::cout << command << " cannot be identified as a command.\n\n";
		getCommand();
	}
}

//--------------------------------------------------------------------- runCommand
void runCommand(std::string command) {

}

//--------------------------------------------------------------------- getCommand
void getCommand() {
	std::cout << ">> ";
	std::string cmd{};
	std::cin >> cmd;

	checkSyntax(cmd);
	runCommand(cmd);

	if (cmd.compare("-EXIT") != 0) {
		getCommand();
	}

	std::cout << "Exiting application... ";
	std::this_thread::sleep_for(std::chrono::milliseconds(5000));
}

//--------------------------------------------------------------------- hideConsole
void hideConsole() {
	::ShowWindow(::GetConsoleWindow(), SW_HIDE);
}

//--------------------------------------------------------------------- showConsole
void showConsole() {
	::ShowWindow(::GetConsoleWindow(), SW_SHOW);
}