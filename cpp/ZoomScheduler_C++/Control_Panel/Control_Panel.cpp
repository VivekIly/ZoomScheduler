#include <iostream>
#include <string>
#include <Windows.h>
#include <thread>
#include <array>
#include <fstream>
#include <shlobj.h>
#include "Util.h"
#include "RepeatingZoomEvent.h"
#include "ZoomEvent.h"

int main() {

	initialize();

	startMainZS();

	//Print welcome message.
	std::cout << "Welcome to the ZoomScheduler control panel! Enter any commands here to control the program. \nType '-help' to display list of commands.\n\n";

	//Starts loop of command inputs. 
	getCommand(true);

	return 0;
}