# ZoomScheduler
ZoomScheduler is a lightweight program that automatically open your Zoom meetings when the time comes.

## Set up:
### Windows:
1. Download the .exe file from the latest version release (currently, the latest is v1.0.0).
    1. Downloading the .jar file also works, but the .exe file is better suited for Windows.
1. If using the .exe file, open the file by double-clicking.
    1. If you have not installed Java on your system yet, the program will direct you to a safe, official website to download the latest version of Java.
        1. Click [here](http://java.com/download) for the same link
        1. Download and run the file to install Java.
        1. After Java has been installed, open the ZoomScheduler.exe file. If an error message occurs, follow [this link](https://javatutorial.net/set-java-home-windows-10) to troubleshoot further.
1. If Java installation was successfull, you should now see a screen with the heading "Zoom Scheduler" once you double-click the .exe file. Now, the program is ready to use. Enjoy!

#### Recommended for Windows users: Start ZoomScheduler on startup
You may find it useful to set the ZoomScheduler.exe file to run automatically on startup. This will prevent you from forgetting to open the application when you start up your computer. 

To set the ZoomScheduler.exe file to run on startup: 
1. Press the Windows logo key on your keyboard or on the left side of the toolbar (if it is at the bottom of the screen).
1. Search for 'ZoomScheduler.' 
1. Right-click on the app and select 'Open file location.'
1. With the file location open, press the **Windows logo key  + R**, type **shell:startup**, then press **Enter** or select **OK**. This opens the startup folder with shortcuts to all the applications that are set to open on startup. 
1. Copy the shortcut to the ZoomScheduler.exe file from the file location and paste it into the startup folder.  
1. If configured properly, you should receive a notification a few minutes later saying, "ZoomScheduler.exe is now configured to run when you log in." or another similar message.

Follow [this link](https://support.microsoft.com/en-us/windows/add-an-app-to-run-automatically-at-startup-in-windows-10-150da165-dcd9-7230-517b-cf3c295d89dd) for further troubleshooting.

### macOS
1. Download the .jar file from the latest version release (currently, the latest is v1.0.0).
1. Open the file by double-clicking.
    1. If the computer pushes a message claiming that the program was created by an unknown developer, please do not panic. I do not have the skills to program malware. Follow [this link](https://support.apple.com/guide/mac-help/open-a-mac-app-from-an-unidentified-developer-mh40616/mac) to troubleshoot.
    1. If you have not installed Java on your system yet, follow [this link](https://java.com/en/download/apple.jsp) to download Java for Mac.
1. Once you have ensured that Java is installed on your Mac, run the .jar file. You should now see a screen with the heading "Zoom Scheduler." Now, the program is ready to use. Enjoy!
    
##Q&A
### What are the basic features of this program?
#### 1. Moving the window
On the main window, you will see a heading named "Zoom Scheduler." This heading is bounded from below by a blue line that is 3 pixels thick. You can click and drag from anywhere in the header to move the window. This applies to all windows and popups in the program.  

#### 2. Date and time
Under the header, you will see the date and the time as the program sees it. Please keep in mind that the program experiences a delay of one second in reading the time from the computer. This delay does not increase and will not affect the functionality of the program. I will work on trying to reduce or get rid of this delay in a future version release.

#### 3. Main program controls
Under the time section, you will see the control panel for the program with three buttons named **Add repeating event**, **Add one-time event**, and **Show list of events**. Select **Add repeating event** to add a recurring meeting. This is most applicable for meetings that run for classes or clubs that meet regularly. Select **Add one-time event** to add a meeting that will only be run once. This is most applicable for meetings that are for temporary events or meetings that recur infrequently. Select **Show list of events** to display a window with a list of the events that you have scheduled. As of v1.0.0, this window can only display up to 54 events, but the program itself can store up to 99 events. This will be improved in future releases. 

#### 4. Color theme
Under the control panel, you will find two buttons to change the color theme, named **Temporarily switch to Light/Dark theme** and **Switch defailt theme to Light/Dark**. The button named **Temporarily switch to Light/Dark theme** will switch the color of the windows to the opposite color theme for the current session. It will revert back to the default color theme when the program is rerun. The button named **Switch default theme to Light/Dark** will switch the default color theme to the opposite of what it is currently set to. The default color theme is the theme that will persist throughout multiple sessions. 

#### 5. Hide/Exit Application
**UNDERSTANDING THE FUNCTIONALITY OF THIS BUTTON IS CRUCIAL TO THE SUCCESSFUL PERFORMANCE OF THE PROGRAM.** 

Under the color theme panel, you will see two buttons named **Hide Window** and  **Exit Application**. The **Hide Window** button will simply hide the window, while allowing the program to run in the background, so that the Zoom meeting will open even if the window is not visible. Select this option if you need to keep the application running in the background. The **Exit Application** button will close both the window and the program. The program will **not** run in the background and any scheduled meetings will **not** be opened. Select this option if you anticipate that you will be away from your computer for an extended period of time. 

### What does the red warning at the bottom of the main window mean?
At the bottom of the main window, there is a red warning message that reads, "PLEASE DO NOT TAMPER WITH THE 'ZoomScheduler' FOLDER!!" This warning serves as a reminder to not tamper with the program folder. This folder contains all the data for the program to run. Deleting its contents will cause you to lose your data irreversibly. Removing the folder in its entirety is analogous to wiping the program's data and will factory reset the program to its default settings as it was when you first installed it. I am keeping the location of this folder a secret in order to ensure the proper behaviour of the program. Try to find it if you want!

### Is there a way to export and import settings?
Great question! As of v1.0.0, there is no option to export and import settings. I will, however, making this available in a future version in case it becomes useful. 

### What do I do if I find a bug?
PLEASE, PLEASE, PLEASE write an issue on the Github page. You can navigate to the issues section by clicking the tab named **Issues** at the top of the ZoomScheduler Github page. For here, you can click the green **New issue** button to submit any comments, suggestions, and bugs you encounter. I will try my best to review these issues on a regular basis and take them into consideration when adding modifications to the program. 

Please do not treat the issues section as an open forum to discuss personal, political, religious, or other irrelevant topics. You have other social media accounts for that. 

### What are your future plans in terms of the program?
To find a mroe complete list of my todo list for the program, scroll to the bottom of the Main.java file in the src folder. If you have any other suggestions, please let me know by opening a Github issue. 

Here is a simplified list of my goals for this program:
1. Detect if the user is already in a meeting. If so, the program should not delete the event and rather, it should push a message to the screen saying that the event could not be opened, because another zoom meeting was open. 
1. Detect if another window of the same type (event viewer, event adder, etc.) is open. If so, move focus to that window when the button is click, rather than opening another window of that type. 
1. Fit program to all screen sizes and resolutions. 
1. Add an option to export and import settings. 
1. Play a sound and push a message that says that the meeting was opened when the time for a meeting comes. 
1. Add the ability to join a meeting with the meeting ID and password, rather than just the link. 
1. Be able to display and store more events. 
1. Add keyboard shortcuts. 