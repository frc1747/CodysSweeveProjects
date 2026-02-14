// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Turret;

public class TurretAimToPose extends Command {
  private Turret turret;
  private double angle;
  private Pose2d targetPose;
  private Pose2d botPose;

  public TurretAimToPose(Turret turret, Pose2d botPose, Pose2d targetPose) {
    this.turret = turret;
    this.targetPose = targetPose;
    this.botPose = botPose;
    addRequirements(turret);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    System.out.println(turret.aimAtPose(this.botPose,this.targetPose));
  }

  @Override
  public void end(boolean interrupted) {
    turret.basicSpin(0.0);
  }

  @Override
  public boolean isFinished() {
    return  0 == turret.aimAtPose(this.botPose,this.targetPose);
  }
}
