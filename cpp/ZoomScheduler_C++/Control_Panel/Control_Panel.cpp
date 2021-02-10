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

	//Print welcome message.
	std::cout << "Welcome to the ZoomScheduler control panel! Enter any commands here to control the program. Type '-help' to display list of commands. Use the '-exit' command to close the application rather than manually pressing the red X in order to ensure all data has been saved properly.\n\n";

	initialize();

	startMain();

	//Starts loop of command inputs. 
	getCommand(true);

	return 0;
}