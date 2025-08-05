package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;


public class Robot extends TimedRobot {
  private final Timer timer = new Timer();
  private final Field2d field = new Field2d(); // Field2d is used to see the robot's position on the field in the Simulation GUI. 
  // Variables for Field2d: 
  private double simulateX = 0;
  private double simulateY = 0;
  private double simulateRotation = 0;


  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  public Robot() {
    SmartDashboard.putData("Field", field);
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    timer.reset();
    timer.start();
    // Robot's starting position: 
    simulateX = 1.2;
    simulateY = 2.1;
    field.setRobotPose(new Pose2d(simulateX, simulateY, new Rotation2d(0)));

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
    double t = timer.get();

    double[] durations1 = {0.5, 0.2, 1.0, 0.3, 0.3};
    double[] xChanges = {0.06, 0.0, 0.0, 0.0, 0.06};
    double[] yChanges = {0.0, 0.0, 0.06, 0.0, 0.06};
    double[] rotationChanges = {0.0, Math.PI / 20, 0.0, -Math.PI / 60, 0.0};
    
    // The robot will move in straight lines and turn. 
    double n = 0.0;
    for (int i = 0; i < durations1.length; i++) {
        if (t > n && t < n + durations1[i]) {
            simulateX += xChanges[i];
            simulateY += yChanges[i];
            simulateRotation += rotationChanges[i];
            field.setRobotPose(new Pose2d(simulateX, simulateY, new Rotation2d(simulateRotation)));
            break;
        }
        n += durations1[i];
    }

    double[] durations2 = {0.45, 0.65, 0.6, 0.5, 0.5, 0.5, 0.3, 0.3};
    double[] rotationSpeeds = {-Math.PI / 100, -Math.PI / 80, -Math.PI / 160, -Math.PI / 130, -Math.PI / 120, -Math.PI / 180, 0.0, Math.PI / 80};
    // The robot will rotate and move in a curve. 
    n = 2.3;
    for (int i = 0; i < durations2.length; i++) {
      if (t > n && t < n + durations2[i]) {
        simulateRotation += rotationSpeeds[i];
        // Using sin and cos make the robot move in the direction they are facing, creating an arc or circle in they are rotating by the same degree every tick. 
        simulateX += 0.06 * Math.cos(simulateRotation); 
        simulateY += 0.06 * Math.sin(simulateRotation);
        field.setRobotPose(new Pose2d(simulateX, simulateY, new Rotation2d(simulateRotation)));
        break; 
      }
      n += durations2[i];
    }
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
