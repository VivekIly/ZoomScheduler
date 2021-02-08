#include <Windows.h>
#include <iostream>
#include <cstdlib>
#include <chrono>
#include <thread>
#include "Util.h"
#include <string>
#include <shlobj.h>

int main() {

	/*char path[MAX_PATH];
	if (SHGetFolderPathA(NULL, CSIDL_PROFILE, NULL, 0, path) != S_OK)
	{
		cout << "I could not retrieve the user's home directory!\n";
	}
	else
	{
		cout << "Home directory = \"" << path << "\"\n";
	}*/

	std::cout << "Program logs window. View any changes to the program and events that take place here.\n\n";
	std::cout.flush();

	//hideConsole();

	for (int i = 0; i < 1; i--) {
	    std::cout << '\r' << getCurrentTime();
	    std::cout.flush();
	    std::this_thread::sleep_for(std::chrono::milliseconds(1000));
	}

	

	return 0;
}

// TODO: Open in default browser