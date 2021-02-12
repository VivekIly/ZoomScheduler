#include <Windows.h>
#include <iostream>

void killCP() {
    system("cd \"C:\\Windows\\System32\" && taskkill /IM \"ControlPanel.exe\" /F");
}

void killMain() {
    system("cd \"C:\\Windows\\System32\" && taskkill /IM \"ZoomScheduler_C++.exe\" /F");
}

void startCP() {
    system("start C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\Main\\Release\\ControlPanel.exe");
}

void startMain() {
    system("start C:\\Users\\Vivek\\OneDrive\\Documents\\GitHub\\ZoomScheduler\\cpp\\ZoomScheduler_C++\\Main\\Release\\ZoomScheduler_C++.exe");
}