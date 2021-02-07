#ifdef _WIN32
#include <Windows.h>
#endif
#include <iostream>
#include <cstdlib>
#include <chrono>
#include <thread>

int main()
{
    std::cout << "Welcome to the ZoomScheduler Event Viewer window! Here, you can view any events that occur, changes that you make, and zoom meetings that you add. Keep in mind, typed input into this window will not be processed. This window must stay open for the program to run.\n\n";


    for (int i = 0; i < 1; i--) {
        time_t rawtime;
        struct tm* timeinfo;
        char buffer[1];
        time(&rawtime);
        timeinfo = localtime(&rawtime);

        strftime(buffer, 80, "%Y/%m/%d %H:%M:%S> ", timeinfo);

        std::cout << '\r' << buffer;
        std::cout.flush();

        std::this_thread::sleep_for(std::chrono::milliseconds(1000));
    }

    system("start /max chrome.exe https://naperville203.zoom.us/j/96493406080");

    return 0;
}

// TODO: Open in default browser