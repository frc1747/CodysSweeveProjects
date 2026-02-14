// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.lang.annotation.Target;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class AutoShoot extends Command {
  /** Creates a new AutoShoot. */
  private Shooter shooter;
  private Turret turret;
  private Pose2d target;

  public AutoShoot(Shooter shooter, Turret turret , Pose2d target) {
    this.shooter = shooter;
    this.turret = turret;
    this.target = target;
    addRequirements(shooter, turret);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double distance = Math.sqrt(Math.pow(target.getX(),2) + Math.pow(target.getY(),2)); // this needs to be intergeted with mult pose
   // System.out.println(distance);
    double [] speedAndAngle = shooter.findSpeedAndAngleFromDistance(distance);
   // shooter.moveHoodToAngle(speedAndAngle[0]);
   // turret.aimAtPose(new Pose2d(), target ); // this needs to be intergeted with mult pose
   // shooter.shoot(speedAndAngle[1]);
    System.out.println("hoodAngle " + speedAndAngle[0] + " : power " + speedAndAngle[1] + " : Turret Angle");
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
