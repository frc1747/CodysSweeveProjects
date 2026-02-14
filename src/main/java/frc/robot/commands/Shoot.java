// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class Shoot extends Command {

  private Shooter shooter;
  private double power;

  public Shoot(Shooter shooter, double power) {
    this.shooter = shooter;
    this.power = power;
    addRequirements(shooter);
  }

  @Override
  public void execute() {
    shooter.shoot(power);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void end(boolean interrupted) {
    shooter.shoot(0.0);
  }
}
