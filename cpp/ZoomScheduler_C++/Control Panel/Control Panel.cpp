#include <iostream>
#include <Windows.h>
#include <thread>
#include <array>
#include "Util.h"

int main() {
	std::cout << "Welcome to the ZoomScheduler control panel! Enter any commands here to control the program. Type '-HELP' to display list of commands.\n\n";

	getCommand();

	return 0;
}