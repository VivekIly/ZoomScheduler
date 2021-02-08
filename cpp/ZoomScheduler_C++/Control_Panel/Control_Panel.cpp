#include <iostream>
#include <string>
#include <Windows.h>
#include <thread>
#include <array>

void help();
void restart();
void createEvent();

namespace events {
	class ZoomEvent {
	public:
		std::string name{};
		std::string url{};
		std::string date{};
		std::string time{};

		void openLink() {
			std::string preout{};
			preout = "start /max chrome.exe " + url;
			system(preout.c_str());
		}

		std::string getName() {
			return name;
		}

		std::string getDate() {
			return date;
		}

		std::string getTime() {
			return time;
		}

		std::string getURL() {
			return url;
		}

	};

	class RepeatingZoomEvent {
	public:
		std::string name{};
		std::string url{};
		std::string time{};
		int repeatingDays[7];

		void openLink() {
			std::string preout{};
			preout = "start /max chrome.exe " + url;
			system(preout.c_str());
		}

		std::string getName() {
			return name;
		}

		std::string getTime() {
			return time;
		}

		std::string getURL() {
			return url;
		}

		std::string getDaysInInts() {
			std::string returnString{ "" };

			for (int i : repeatingDays) {
				returnString += i;
			}

			return returnString;
		}

		template<class Archive>
		void serialize(Archive& archive) {
			archive(name, url, time, repeatingDays);
		}
	};
}

namespace util {

	std::string commands[] = { "-help", "-exit", "-restart" };

	char* getCurrentDateTime() {
		time_t rawtime;
		struct tm* timeinfo;
		char buffer[1];
		time(&rawtime);
		timeinfo = localtime(&rawtime);

		strftime(buffer, 80, "%Y/%m/%d %H:%M:%S> ", timeinfo);

		return buffer;
	}

	char* getCurrentTime() {
		time_t rawtime;
		struct tm* timeinfo;
		char buffer[1];
		time(&rawtime);
		timeinfo = localtime(&rawtime);

		strftime(buffer, 80, "%H:%M:%S> ", timeinfo);

		return buffer;
	}

	void startMain() {
		/*ShellExecute(
		NULL,
		L"open",
		L"C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\Main\\Release\\ZoomScheduler_C++.exe",
		NULL,
		NULL,
		SW_SHOWDEFAULT
	);*/

		std::cout << "Opening main application . . .\n";

		std::chrono::steady_clock::time_point begin = std::chrono::steady_clock::now();
		system("start C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\Main\\Release\\ZoomScheduler_C++.exe");
		std::chrono::steady_clock::time_point end = std::chrono::steady_clock::now();

		double seconds = std::chrono::duration_cast<std::chrono::microseconds>(end - begin).count() / 1000000.000000;
		std::cout << "Main application opened in " << seconds << " seconds.\n\n";
	}

	void createZoomEvent(std::string name, std::string url, std::string date, std::string time) {
		events::ZoomEvent temp{ name, url, date, time };
	}

	void getCommand();

	//--------------------------------------------------------------------- checkSyntax
	void checkSyntax(std::string command) {
		bool flag{ false };

		if (command.length() == 0)
			getCommand();

		for (std::string c : commands) {
			if (command.compare(c) == 0) {
				flag = true;
			}
		}

		if (flag == false) {
			std::cout << "\'" << command << "\' is not recognized as a command.\n\n";
			getCommand();
		}
	}

	//--------------------------------------------------------------------- runCommand
	void runCommand(std::string& command) {
		if (command == "-help") {
			help();
		}
		else if (command == "-restart") {
			restart();
		}
		getCommand();
	}

	//--------------------------------------------------------------------- getCommand
	void getCommand() {
		std::cout << ">> ";
		std::string cmd{};
		std::getline(std::cin, cmd);

		checkSyntax(cmd);

		if (cmd != "-exit")
			runCommand(cmd);

		std::cout << "Attempting to exit application . . . \n";
		std::this_thread::sleep_for(std::chrono::milliseconds(2000));
	}

	//--------------------------------------------------------------------- hideConsole
	void hideConsole() {
		::ShowWindow(::GetConsoleWindow(), SW_HIDE);
	}

	//--------------------------------------------------------------------- showConsole
	void showConsole() {
		::ShowWindow(::GetConsoleWindow(), SW_SHOW);
	}

}

namespace serialize {

}

void help() {
	std::cout << "List of commands: \n\n\'-restart\':\tRestarts the application. Reopens the Control Panel console and the main application in the background.\n\'-exit\':\tExits the application after safely storing data.\n\n";
}

void restart() {
	system("start C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\RestartZScpp\\Release\\RestartZScpp.exe");
}

void createEvent() {
	std::cout << "Would you like to create a one-time event or a repeating event? (1 for one-time, 2 for repeating): ";

	int eventType{};
	std::cin >> eventType;

	if (eventType == 1) {
		std::string name{};
		std::string url{};
		std::string date{};
		std::string time{};

		std::cout << "Name: ";
		std::cin >> name;

		std::cout << "URL: ";
		std::cin >> url;

		std::cout << "Date: ";
		std::cin >> date;

		std::cout << "Time: ";
		std::cin >> time;

		util::createZoomEvent(name, url, date, time);
	}
}

int main() {

	//Print welcome message.
	{
		std::cout << "Welcome to the ZoomScheduler control panel! Enter any commands here to control the program. Type '-help' to display list of commands. Use the '-exit' command to close the application rather than manually pressing the red X in order to ensure all data has been saved properly.\n\n";
	}

	util::startMain();

	//system("C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\Main\\Release\\ZoomScheduler_C++.exe");

	//Starts loop of command inputs. 
	util::getCommand();

	return 0;
}