// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Turret;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class TurretGoToAngle extends Command {
  private Turret turret;
  private double angle;
  public TurretGoToAngle(Turret turret, double angle) {
    this.turret = turret;
    this.angle = angle;
    addRequirements(turret);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    turret.goToAngle(angle);
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
