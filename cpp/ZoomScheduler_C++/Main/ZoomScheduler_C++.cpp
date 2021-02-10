#include <iostream>
#include <thread>
#include <stdio.h>
#include "Util.h"
#include "ZoomEvent.h"
#include "RepeatingZoomEvent.h"

int main() {

	std::cout << "Program logs window. View any changes to the program and events that take place here.\n\n";
	std::cout.flush();

	ZoomEvent fetchZE;
	RepeatingZoomEvent fetchRZE;

	std::vector<std::string> files = getFiles();

	for (int i = 0; i < 1; i--) {
		if (!isVisible())
			//hideConsole();

	    std::cout << '\r' << getCurrentTime();
	    std::cout.flush();

		files = getFiles();

		for (std::string s : files) {
			if (endsIn(s, ".ze")) {
				ZoomEvent temp = fetchZE.fetch(s);
				if (getCurrentDateComp() == temp.getDate() && getCurrentTimeComp() == temp.getTime()) {
					temp.openLink();
					remove(s.c_str());
				}
			}
		}

	    std::this_thread::sleep_for(std::chrono::milliseconds(1000));
	}

	return 0;
}

// TODO: Open in default browser