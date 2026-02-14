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
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.LimeLight;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class AprilLock3 extends Command {
  /** Creates a new FaceObject. */
  private final LimeLight limelight;
  private final Turret turret;
  private final PIDController pid;

  // TODO: fix starting pose of robot
  public AprilLock3(LimeLight limeLight, Turret turret) {
    this.limelight = limeLight;
    this.turret = turret;
    this.pid = new PIDController(Constants.Vision.APRIL_LOCK_P, Constants.Vision.APRIL_LOCK_I, Constants.Vision.APRIL_LOCK_D);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // TODO: reformat to make more readable
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      // pose of target on field, rotation represents angle of its normal vector
      Pose2d targetPose = new Pose2d(new Translation2d(Constants.Vision.FIELD_CENTER_X, Constants.Vision.FIELD_CENTER_Y), new Rotation2d(0.0));
      Pose2d turretPose = turret.getAbsTurretPose();
      
      // difference between robot and april tag poses
      Translation2d diff = turretPose.getTranslation().minus(targetPose.getTranslation());
        
      // yaw offset between target and robot vector pointing directly out from robot-front
      double phi = Math.atan2(diff.getY(), diff.getX());
      double yawOffset = phi - turretPose.getRotation().getRadians() - Math.PI;
      double wrappedYaw = Math.atan2(Math.sin(yawOffset), Math.cos(yawOffset));
      // System.out.println("robotPose: " + robotPose);
      // System.out.println("wrappedyaw: " + wrappedYaw);

      // pid controlling rotation compensation
      double pidOutput = -1 * pid.calculate(wrappedYaw); // not sure why it needs to be multiplied by -1
      double clampPid = pidOutput > Constants.Vision.APRIL_LOCK_PID_CLAMP ? Constants.Vision.APRIL_LOCK_PID_CLAMP : pidOutput;
      clampPid = clampPid < -Constants.Vision.APRIL_LOCK_PID_CLAMP ? -Constants.Vision.APRIL_LOCK_PID_CLAMP : clampPid;
      // System.out.println("pidOutput: " + pidOutput);
      // System.out.println("clampPid: " + clampPid);

      double power = -yawOffset / 2 / Math.PI * 0.2;
      if (power > 1.0) {
        power = 1.0;
      } else if (power < -1.0) {
        power = -1.0;
      }
      turret.basicSpin(power);
      System.out.println(power);
  } 

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret.basicSpin(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
