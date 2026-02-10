// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ClimberMove extends Command {
  /** Creates a new ElevatorMove. */

  private Climber climber;
  private double power;

  public ClimberMove(Climber climber, double power) {
    this.climber = climber;
    this.power = power;
    addRequirements(climber);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.climber.SetClimberPower(this.power);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.climber.SetClimberPower(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.climber.GetSwitchPresed();
  }
}
