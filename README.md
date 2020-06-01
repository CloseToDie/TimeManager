# TimeManager
![Icon](https://imgur.com/hvvP8Hc.png)

This program is build to track time for customers.

## Installation Guide

### Downloading the project
- Download the github repo to your local machine.
- Unzip the zip file, and place in your desired project folder.
- Start Netbeans, and click the "Open Project".
- Now choose the project from where you placed it from before.

### Required libraries
- MSSQL Driver
- JFoenix 9.0.8

### Setting up the database
In your desired database software, please create a new database for the project.

There sould be multiple sql files in the "db" folder in the project folder structure, you can import these files into your database. You can also open the files, and copy the raw sql into your database query field. 

### Connecting to the database
- Create a file in the root of the project named "DBSettings.txt"
- Open the DBSettings.txt file, and input the following data.
  - server=(Your database IP)
  - user=(Your db username here)
  - password=(Your db username here)
  - database=TimeManager (Or your chosen database name)
- Please note that there should not be any parentheses in the final DBSettings.txt file.
  
### Running the program
After having gone through alle the steps, and installed the project, you should be good to go. Now go to Netbeans, and click the Clean and Build button, and then click the green Run Project button next to it. The project should start, and you should be able to use it.

