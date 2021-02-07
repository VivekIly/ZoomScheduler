#include <iostream>

void getCommand() {
	std::cout << ">> ";
	std::string cmd{};
	std::cin >> cmd;

	if (cmd.compare("exit") != 0) {
		getCommand;
	}
}

int main() {
	std::cout << "Welcome to the ZoomScheduler control panel! Enter any commands here to control the program. Type '-HELP' to display list of commands.\n\n";

	getCommand();

	return 0;
}