#include <iostream>
#include <string>
#include <thread>

std::string commands[] = { "-HELP", "-EXIT", };

void removeSpaces(std::string str) {
    // To keep track of non-space character count 
    int count = 0;

    // Traverse the given string. If current character 
    // is not space, then place it at index 'count++' 
    for (int i = 0; str[i]; i++)
        if (str[i] != ' ')
            str[count++] = str[i]; // here count is 
    // incremented
    str[count] = '\0';
}

void checkSyntax(std::string command) {
    //for ()
}

void getCommand() {
    std::cout << ">> ";
    std::string cmd{};
    std::cin >> cmd;

    removeSpaces(cmd);
    std::cout << cmd;

    checkSyntax(cmd);

    if (cmd.compare("-EXIT") != 0) {
        getCommand();
    }

    std::cout << "Exiting application... ";
    std::this_thread::sleep_for(std::chrono::milliseconds(5000));
}