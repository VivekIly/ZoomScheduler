//#include <iostream>
//#include <string>
//#include <thread>
//#include <Windows.h>
//#include "Commands.h"
//
//#ifndef UTIL_H
//#define UTIL_H
//
//namespace events {
//	class ZoomEvent {
//	public:
//		std::string name{};
//		std::string url{};
//		std::string date{};
//		std::string time{};
//
//		void openLink() {
//			std::string preout{};
//			preout = "start /max chrome.exe " + url;
//			system(preout.c_str());
//		}
//
//		std::string getName() {
//			return name;
//		}
//
//		std::string getDate() {
//			return date;
//		}
//
//		std::string getTime() {
//			return time;
//		}
//
//		std::string getURL() {
//			return url;
//		}
//
//	};
//
//	class RepeatingZoomEvent {
//	public:
//		std::string name{};
//		std::string url{};
//		std::string time{};
//		int repeatingDays[7];
//
//		void openLink() {
//			std::string preout{};
//			preout = "start /max chrome.exe " + url;
//			system(preout.c_str());
//		}
//
//		std::string getName() {
//			return name;
//		}
//
//		std::string getTime() {
//			return time;
//		}
//
//		std::string getURL() {
//			return url;
//		}
//
//		std::string getDaysInInts() {
//			std::string returnString{ "" };
//
//			for (int i : repeatingDays) {
//				returnString += i;
//			}
//
//			return returnString;
//		}
//
//		template<class Archive>
//		void serialize(Archive& archive) {
//			archive(name, url, time, repeatingDays);
//		}
//	};
//}
//
//namespace util {
//
//	std::string commands[] = { "-help", "-exit", "-restart" };
//
//	char* getCurrentDateTime() {
//		time_t rawtime;
//		struct tm* timeinfo;
//		char buffer[1];
//		time(&rawtime);
//		timeinfo = localtime(&rawtime);
//
//		strftime(buffer, 80, "%Y/%m/%d %H:%M:%S> ", timeinfo);
//
//		return buffer;
//	}
//
//	char* getCurrentTime() {
//		time_t rawtime;
//		struct tm* timeinfo;
//		char buffer[1];
//		time(&rawtime);
//		timeinfo = localtime(&rawtime);
//
//		strftime(buffer, 80, "%H:%M:%S> ", timeinfo);
//
//		return buffer;
//	}
//
//	void startMain() {
//		/*ShellExecute(
//		NULL,
//		L"open",
//		L"C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\Main\\Release\\ZoomScheduler_C++.exe",
//		NULL,
//		NULL,
//		SW_SHOWDEFAULT
//	);*/
//
//		std::cout << "Opening main application . . .\n";
//
//		system("start C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\Main\\Release\\ZoomScheduler_C++.exe");
//
//		std::cout << "Main application has been opened . . .\n\n";
//	}
//
//	void createZoomEvent(std::string name, std::string url, std::string date, std::string time) {
//		events::ZoomEvent temp{ name, url, date, time };
//	}
//
//	void getCommand();
//
//	//--------------------------------------------------------------------- checkSyntax
//	void checkSyntax(std::string command) {
//		bool flag{ false };
//
//		if (command.length() == 0)
//			getCommand();
//
//		for (std::string c : commands) {
//			if (command.compare(c) == 0) {
//				flag = true;
//			}
//		}
//
//		if (flag == false) {
//			std::cout << "\'" << command << "\' is not recognized as a command.\n\n";
//			getCommand();
//		}
//	}
//
//	//--------------------------------------------------------------------- runCommand
//	void runCommand(std::string& command) {
//		if (command == "-help") {
//			help();
//		}
//		else if (command == "-restart") {
//			restart();
//		}
//		getCommand();
//	}
//
//	//--------------------------------------------------------------------- getCommand
//	void getCommand() {
//		std::cout << ">> ";
//		std::string cmd{};
//		std::getline(std::cin, cmd);
//
//		checkSyntax(cmd);
//
//		if (cmd != "-exit")
//			runCommand(cmd);
//
//		std::cout << "Attempting to exit application . . . \n";
//		std::this_thread::sleep_for(std::chrono::milliseconds(2000));
//	}
//
//	//--------------------------------------------------------------------- hideConsole
//	void hideConsole() {
//		::ShowWindow(::GetConsoleWindow(), SW_HIDE);
//	}
//
//	//--------------------------------------------------------------------- showConsole
//	void showConsole() {
//		::ShowWindow(::GetConsoleWindow(), SW_SHOW);
//	}
//
//}
//
//namespace serialize {
//
//}
//
//#endif