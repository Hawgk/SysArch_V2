# System Architecture Project
This project is part of the System Architecture lecture. The assigned group is Vehicle Group 2.
## Project description
Design a vehicle control unit which sends and receives data from network.
## Current status
- Initial design finished
    - Basic software architecture established
    - High priority task for data acquisition
    - Jitter calculation
    - Buffer for incoming data implemented
    - Overflows registered
    - All outputs currently on console
- Run script for easy installation and start up
    - Checks if correct Java version is installed
## Installation
- Start up Raspberry Pi
- Copy "Program_files" folder to Raspberry Pi
    - Use the SD Card, PuTTY or VNC Viewer
- Start terminal from "Program_files" folder and type in "sudo bash run.sh"

### PuTTY
- Connect Raspberry Pi to your network
- Find out Raspberry Pi's IP address
- Start PuTTY
- Host Name: type in Raspberry Pi's IP
- Port: 22
- Make sure SSH is selected as connection type
- Press open
- Login with Raspberry Pi's system account
- Open cmd
- Type: "pscp C:\Path\To\Program_files\program.jar pi@raspberrypi:/Path/To/Save/Folder"
- Confirm password
- Type: "pscp C:\Path\To\Program_files\run.sh pi@raspberrypi:/Path/To/Save/Folder"
- Confirm password
- Note: "pi@raspberrypi" has to be changed to whatever is shown in the terminal of PuTTY, can vary

### VNC Viewer
- Install VNC Viewer on Windows
- Connect Raspberry Pi to your network
- Find out Raspberry Pi's IP address
- Start VNC Viewer
- Connect to Raspberry Pi's IP address
- In the connection window hover near the top window bar. A toolbar should appear.
- Choose transfer files
- Click on "Send files..."
- Select the Program_files folder
- Click on "Use Entire Folder"
- Files will be saved to Desktop on the Raspberry Pi

## Start-up
- Start terminal from "Program_files" folder and type in "sudo bash run.sh"
- Alternatively you can run "java -jar program.jar" (JDK 11 must be installed)

## Tasks
### Amando
- Threads
    - ReadingThread
    - LoggingThread
    - DisplayThread
- main
- MotorController
- CollisionController
- CommunicationController
### Alex
- Sensor
    - Compass
    - LIDAR
    - RFID
    - Speed
- ManagementController
- Measurement
- VehicleUI