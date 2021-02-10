#include <iostream>
#include <string>
#include <regex>
#include "Util.h"
#include "ZoomEvent.h"
#include "RepeatingZoomEvent.h"
#include "Commands.h"

void help() {
	std::cout << "List of commands: \n\n\'-restart\':\tRestarts the application. Reopens the Control Panel console and the main application in the background.\n\'-exit\':\tExits the application after safely storing data.\n\n";
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
		restart();
	}
}