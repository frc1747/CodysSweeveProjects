// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.estimator.PoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.LimeLight;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class AprilLock2 extends Command {
  /** Creates a new FaceObject. */
  LimeLight limelight;
  CommandSwerveDrivetrain drivetrain;
  private DoubleSupplier translationSup;
  private DoubleSupplier strafeSup;
  private PIDController pid;

  // TODO: fix starting pose of robot
  public AprilLock2(LimeLight limeLight, CommandSwerveDrivetrain drivetrain, DoubleSupplier translationSup, DoubleSupplier strafeSup) {
    this.limelight = limeLight;
    this.drivetrain = drivetrain;
    this.translationSup = translationSup;
    this.strafeSup = strafeSup;
    this.pid = new PIDController(0.9, 0.02, 0.05); // TODO: tune pid
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // TODO: reformat to make more readable
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      // apply deadzone
      // translation value is forward/backward on left joystick
      double translationVal = MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.ControllerConstants.STICK_DEADBAND);
      // strafe value is left/right on left joystick
      double strafeVal = MathUtil.applyDeadband(strafeSup.getAsDouble(), Constants.ControllerConstants.STICK_DEADBAND);
        
      // pose of apriltag on field, rotation represents angle of its normal vector
      Pose2d apriltagPose = new Pose2d(new Translation2d(0.0, 0.0), new Rotation2d(0.0));
      Pose2d robotPose = drivetrain.getState().Pose; 
      
      // difference between robot and april tag poses
      Translation2d diff = robotPose.getTranslation().minus(apriltagPose.getTranslation());
      // angle between diff and from vector(1, 0, 0)
      double diffAngle = Math.atan2(diff.getY(), diff.getX());
      // strafe angle is angle of a vector perpendicular to diff
      double strafeAngle = diffAngle + Math.PI / 2;
      // unit vector for direction of strafe at this moment
      Translation2d strafeDir = new Translation2d(Math.cos(strafeAngle), Math.sin(strafeAngle));
      // unit vector for direction towards target at this moment
      Translation2d translationDir = new Translation2d(-Math.cos(diffAngle), -Math.sin(diffAngle));
        
      // yaw offset between april tag normal vector and robot vector pointing directly out from camera
      // need to fix offset
      // I beleive there is a logic error here
      double yawOffset = ((diffAngle - 180) - robotPose.getRotation().getDegrees()) % 360;
      System.out.println(yawOffset);

      // pid controlling rotation compensation
      double pidOutput = pid.calculate(yawOffset);
      double clampPid = pidOutput > 1.0 ? 1.0 : pidOutput;

      // TODO: check max speed math
      // strafe component of x component of final field oriented translation
      double strafeX = strafeDir.getX() * strafeVal * Constants.DrivetrainConstants.MAX_SPEED * 0.5;
      // strafe component of y component of final field oriented translation
      double strafeY = strafeDir.getY() * strafeVal * Constants.DrivetrainConstants.MAX_SPEED * 0.5;
      // forward/backward component of x component of final field oriented translation
      double translationX = translationDir.getX() * translationVal * Constants.DrivetrainConstants.MAX_SPEED * 0.5;
      // forward/backward component of y component of final field oriented translation
      double translationY = translationDir.getY() * translationVal * Constants.DrivetrainConstants.MAX_SPEED * 0.5;
      // rotation compensation power, currently unused
      double rotation = 0.1 * yawOffset / 360.0 * Constants.DrivetrainConstants.maxAngularVelocity;
      
      // make drivetrain drive
      SwerveRequest request = new SwerveRequest.FieldCentric()
          .withVelocityX(strafeX + translationX)
          .withVelocityY(strafeY + translationY)
          .withRotationalRate(0.0); // use rotation once calculation i  s proper
      drivetrain.setControl(request);
  } 

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // stop drivetrain when command ends
    // this.drivetrain.simpleDrive(new Translation2d(0, 0), 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
