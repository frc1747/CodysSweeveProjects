// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Turret;

public class TurretMove extends Command {
  private Turret turret;
  private double speed;

  public TurretMove(Turret turret,double speed) {
    this.turret = turret;
    this.speed = speed;
    addRequirements(turret);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    turret.basicSpin(speed);
  }

  @Override
  public void end(boolean interrupted) {
    turret.basicSpin(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
