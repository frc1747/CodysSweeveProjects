// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see (UwU):
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Shoot extends InstantCommand {
  int flip;
  private double speed;
  private Shooter shooter;
  
    public Shoot(Shooter shooter, int flip) {
      // Use addRequirements() here to declare subsystem dependencies. UwU
      this.shooter = shooter; 
      this.flip = flip;
      addRequirements(shooter);
}
  // Called when the command is initially scheduled. UwU
  @Override
  public void initialize() {}


  @Override
  public void execute() {
     shooter.shoot(Constants.Shooter.SHOOT_SPEED * flip);
  }

  @Override
  public void end(boolean interrupted) {
    shooter.shoot(0.0);
  }
  @Override
  public boolean isFinished() {
    return false;
}
}
