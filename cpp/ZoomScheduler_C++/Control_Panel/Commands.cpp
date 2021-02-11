#include <iostream>
#include <string>
#include <regex>
#include "Util.h"
#include "ZoomEvent.h"
#include "RepeatingZoomEvent.h"
#include "Commands.h"

void help() {
	std::cout << "List of commands: \n\n";
	std::cout << "\'-restart\':\tRestarts the application to refresh data.\n";
	std::cout << "\'-exit\':\tExits the application after safely storing data.\n";
	std::cout << "\'-create\':\tOpens the create event wizard which guides the user through creating a new event.\n";
	std::cout << "\'-toggle\':\tToggles the temporary visibility of the event viewer window.\n";
	std::cout << "\'-toggle def\':\tToggles the default visibility of the event viewer window.\n\n";
}

void restart() {
	system("start C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\RestartZScpp\\Release\\RestartZScpp.exe");
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

		ZoomEvent temp (name, url, date, time);
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

		RepeatingZoomEvent temp (name, url, time, days);
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