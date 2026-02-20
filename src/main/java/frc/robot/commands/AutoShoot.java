// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.lang.annotation.Target;
import java.lang.constant.Constable;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class AutoShoot extends Command {
  /** Creates a new AutoShoot. */
  private Shooter shooter;
  private Double turretAngle;
  private Pose2d target;
  private Pose2d turretPose;
  private Turret turret;
  private PIDController pid;

  public AutoShoot(Shooter shooter, Turret turret, Pose2d turretPose, Pose2d target) {
    this.shooter = shooter;
    this.turretAngle = turretAngle;
    this.target = target;
    this.turretPose = turretPose;
    this.turret = turret;
    this.pid = new PIDController(Constants.Vision.APRIL_LOCK_P, Constants.Vision.APRIL_LOCK_I, Constants.Vision.APRIL_LOCK_D);
    addRequirements(shooter, turret);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double yawOffset = turret.getYawOffset(new Translation2d(Constants.Vision.FIELD_CENTER_X, Constants.Vision.FIELD_CENTER_Y));

    // pid controlling rotation compensation
    double pidOutput = pid.calculate(yawOffset); 
    double clampPid = pidOutput > Constants.Vision.APRIL_LOCK_PID_CLAMP ? Constants.Vision.APRIL_LOCK_PID_CLAMP : pidOutput;
    clampPid = clampPid < -Constants.Vision.APRIL_LOCK_PID_CLAMP ? -Constants.Vision.APRIL_LOCK_PID_CLAMP : clampPid;

    double power = pidOutput;
    turret.basicSpin(power);

    if (yawOffset <= Constants.Turret.AUTO_AIM_TOLERANCE ){
      double distance = Math.sqrt(Math.pow(target.getX()+ turretPose.getX(),2) + Math.pow(target.getY() + turretPose.getY(), 2));
      double [] speedAndAngle = shooter.findSpeedAndAngleFromDistance(distance);
      shooter.moveHoodToAngle(speedAndAngle[0]);
      shooter.shoot(speedAndAngle[1]);
    //System.out.println("hoodAngle " + speedAndAngle[0] + " : power " + speedAndAngle[1] + " : Turret Angle");
  }
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
