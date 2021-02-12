#include <iostream>
#include <string>
#include <regex>
#include "Util.h"
#include "ZoomEvent.h"
#include "RepeatingZoomEvent.h"
#include "Commands.h"

void help() {
	std::cout << "\nList of commands: \n\n";
	std::cout << "\'-restart\':\tRestarts the application to refresh data.\n";
	std::cout << "\'-create\':\tOpens the create event wizard which guides the user through creating a new event.\n";
	std::cout << "\'-toggle\':\tToggles the temporary visibility of the event viewer window.\n";
	std::cout << "\'-toggle def\':\tToggles the default visibility of the event viewer window.\n";
	std::cout << "\'-check\':\tChecks if the main application is running.\n";
	std::cout << "\'-show\':\tShows all the scheduled events.\n";
	std::cout << "\'-exit\':\tExits the application after safely storing data without keeping the contorl panel open.\n";
	std::cout << "\'-kill main\':\tCloses the main application while keeping the control panel open.\n";
	std::cout << '\n';
}

void restart() {
	system("start C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\Main\\Release\\Restart.exe");
}

void create() {
	std::cout << "Would you like to create a one-time event or a repeating event? (1 for one-time, 2 for repeating): ";

	int eventType{};
	std::cin >> eventType;

	if (eventType == 1) {
		std::string buffer{};
		std::string name{};
		std::string url{};
		std::string date{};
		std::string time{};

		std::getline(std::cin, buffer);

		std::cout << "Name: ";
		std::getline(std::cin, name);

		while (name == "") {
			std::cout << "Invalid name. Please try again.\n";
			std::cout << "Name: ";
			std::getline(std::cin, name);
		}

		std::cout << "URL: ";
		std::getline(std::cin, url);

		std::cout << "Date ( yyyy/MM/dd ): ";
		std::getline(std::cin, date);

		std::cout << "Time ( 24-hour HH:MM(:SS) ): ";
		std::getline(std::cin, time);

		ZoomEvent temp(name, url, date, time);
		temp.serialize();
	}
	else if (eventType == 2) {
		std::string buffer{};
		std::string name{};
		std::string url{};
		std::string time{};
		std::string tempDays{};
		std::string days{};

		std::getline(std::cin, buffer);

		std::cout << "Name: ";
		std::getline(std::cin, name);

		while (name == "") {
			std::cout << "Invalid name. Please try again.\n";
			std::cout << "Name: ";
			std::getline(std::cin, name);
		}

		std::cout << "URL: ";
		std::getline(std::cin, url);

		std::cout << "Time ( 24-hour HH:MM(:SS) ): ";
		std::getline(std::cin, time);

		std::cout << "Enter the number(s) corresponding to the day(s) the event is to be repeated:\n";
		std::cout << "(1) Monday\t(2) Tuesday\t(3) Wednesday\t(4) Thursday\t(5) Friday\t(6) Saturday\t(7) Sunday\n";
		std::cout << "Repeat on: ";
		std::getline(std::cin, tempDays);

		days = "0000000";

		for (int i = 0; i < tempDays.length(); i++) {
			if (tempDays.at(i) == '1') {
				days[0] = '1';
			}
			else if (tempDays.at(i) == '2') {
				days[1] = '1';
			}
			else if (tempDays.at(i) == '3') {
				days[2] = '1';
			}
			else if (tempDays.at(i) == '4') {
				days[3] = '1';
			}
			else if (tempDays.at(i) == '5') {
				days[4] = '1';
			}
			else if (tempDays.at(i) == '6') {
				days[5] = '1';
			}
			else if (tempDays.at(i) == '7') {
				days[6] = '1';
			}
		}

		RepeatingZoomEvent temp(name, url, time, days);
		temp.serialize();
	}
	else {
		std::cout << "Invalid parameter. Please try again.\n";
		create();
	}
}

void toggleMainVisibility() {
	if (toggleMainVisOut())
		std::cout << "Event viewer window temporary visibility set to true.\n\n";
	else
		std::cout << "Event viewer window temporary visibility set to false.\n\n";
}

void toggleDefMainVisibility() {
	if (toggleDefMainVisOut())
		std::cout << "Event viewer window default visibility set to true.\n";
	else
		std::cout << "Event viewer window default visibility set to false.\n";

	if (toggleMainVisOut())
		std::cout << "Event viewer window temporary visibility set to true.\n\n";
	else
		std::cout << "Event viewer window temporary visibility set to false.\n\n";
}

void check() {
	if (IsMainRunning()) {
		std::cout << "Main application is running.\n\n";
	}
	else {
		std::cout << "Main application is not running.\n\n";
	}
}

inline bool endsIn(std::string const& value, std::string const& ending) {
	if (ending.size() > value.size()) return false;
	return std::equal(ending.rbegin(), ending.rend(), value.rbegin());
}

void show() {
	std::vector<std::string> files = getFiles();

	ZoomEvent fetchZE;
	RepeatingZoomEvent fetchRZE;

	std::vector<std::string> ZEfiles{};
	std::vector<std::string> RZEfiles{};

	for (std::string s : files) {
		if (endsIn(s, ".ze")) {
			ZEfiles.push_back(s);
		}
		else if (endsIn(s, ".rze")) {
			RZEfiles.push_back(s);
		}
	}

	if (ZEfiles.size() == 0 && RZEfiles.size() == 0) {
		std::cout << "There are no scheduled events.\n";
	}
	else {
		int numOfFiles = ZEfiles.size() + RZEfiles.size();

		std::cout << "\nFound a total of " << numOfFiles << " scheduled events (" << ZEfiles.size() << " one-time and " << RZEfiles.size() << " repeating).\n";

		for (std::string s : ZEfiles) {
			ZoomEvent temp = fetchZE.fetch(s);
			std::cout << '\n' << temp.getName() << std::endl;
			std::cout << temp.getDate() << '\t';
			std::cout << temp.getTime() << std::endl;
			std::cout << temp.getURL() << "\n";
		}
		for (std::string s : RZEfiles) {
			RepeatingZoomEvent temp = fetchRZE.fetch(s);
			std::cout << '\n' << temp.getName() << std::endl;

			std::string daysOut{ "" };
			std::string tempDays = temp.getDays();

			if (tempDays[0] == '1') {
				daysOut += "Mon ";
			}
			if (tempDays[1] == '1') {
				daysOut += "Tues ";
			}
			if (tempDays[2] == '1') {
				daysOut += "Wed ";
			}
			if (tempDays[3] == '1') {
				daysOut += "Thurs ";
			}
			if (tempDays[4] == '1') {
				daysOut += "Fri ";
			}
			if (tempDays[5] == '1') {
				daysOut += "Sat ";
			}
			if (tempDays[6] == '1') {
				daysOut += "Sun ";
			}

			std::cout << daysOut;
			std::cout << '\t' << temp.getTime() << std::endl;
			std::cout << temp.getURL() << "\n";
		}
	}

	std::cout << '\n';
}