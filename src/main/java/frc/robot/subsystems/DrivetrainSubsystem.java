package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
  // This is a basic Drivetrain Subsystem that uses differential drive and arcade drive. 
  private final PWMSparkMax leftMotor = new PWMSparkMax(0);
  private final PWMSparkMax rightMotor = new PWMSparkMax(1);
  private final DifferentialDrive drive = new DifferentialDrive(leftMotor::set, rightMotor::set);

  public DrivetrainSubsystem() {
    rightMotor.setInverted(true);
  }

  public void arcadeDrive(double forward, double rotation) {
    drive.arcadeDrive(forward, rotation);
  }

  public void stop() {
    drive.stopMotor();
  }
}
