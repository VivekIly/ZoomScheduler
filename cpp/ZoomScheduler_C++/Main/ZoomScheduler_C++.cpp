#include <iostream>
#include <thread>
#include <stdio.h>
#include "Util.h"
#include "ZoomEvent.h"
#include "RepeatingZoomEvent.h"

inline bool endsIn(std::string const& value, std::string const& ending) {
	if (ending.size() > value.size()) return false;
	return std::equal(ending.rbegin(), ending.rend(), value.rbegin());
}

int main() {

	std::cout << "Program logs window. View any changes to the program and events that take place here.\n\n";
	std::cout.flush();

	ZoomEvent fetchZE;
	RepeatingZoomEvent fetchRZE;

	std::vector<std::string> files = getFiles();

	int today{getToday()};
	int daysIndex{ today - 1 };

	/*std::cout << "Today: " << today << std::endl;
	std::cout << "Day Index: " << daysIndex << std::endl;*/

	for (int i = 0; i < 1; i--) {
		if (!isVisible())
			hideConsole();
		else
			showConsole();

	    std::cout << '\r' << getCurrentTime();
	    std::cout.flush();

		files = getFiles();
		getToday();

		for (std::string s : files) {
			if (endsIn(s, ".ze")) {
				ZoomEvent temp = fetchZE.fetch(s);
				if (getCurrentDateComp() == temp.getDate() && getCurrentTimeComp() == temp.getTime()) {
					temp.openLink();
					std::cout << "Zoom event \'" << temp.getName() << "\' opened.\n";
					remove(s.c_str());
				}
			}
			else if (endsIn(s, ".rze")) {
				RepeatingZoomEvent temp = fetchRZE.fetch(s);
				if (temp.getDays().at(daysIndex) == '1' && getCurrentTimeComp() == temp.getTime()) {
					temp.openLink();
					std::cout << "Repeating zoom event \'" << temp.getName() << "\' opened.\n";
					std::this_thread::sleep_for(std::chrono::milliseconds(61000));
				}
			}
		}

	    std::this_thread::sleep_for(std::chrono::milliseconds(1000));
	}

	return 0;
}

// TODO: Open in default browser
// TODO: Command to remove events
// TODO: Exit create event wizard