This project includes a basic Drivetrain and simulated robot movement. 

Drivetrain: The Drivetrain Subsystem initializes two PWM Spark Max motors and a DifferentialDrive. It inverts the right motor and has a basic arcadeDrive() method and a stop() method. 

Simulation: In the Robot.java file, you will find the code for the robot simulation. It uses SmartDashboard and Field2d to track and update the simulated robot’s location on the field. First, the robot moves in a straight line and turns. Then, it uses trigonometry to move in the direction it’s facing as it turns, resulting in curves. 
    
How to Simulate: To simulate the robot for yourself, follow the instructions in this video: https://drive.google.com/file/d/1w9pmUIB4bsZaNh3z8dmUjfJ7mN5LtGsK/view?usp=sharing

In the future, I plan to expand the drivetrain to integrate PathPlanner. There is a Pathplanner folder in src/main/deploy/pathplanner. It has paths and an auto, but can’t be used until the drivetrain is more developed. 

All of the other files are default files for the Command-Based Robot Template in WPILib. 
