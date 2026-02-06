// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Kicker extends SubsystemBase {
  /** Creates a new Kicker. */

  private TalonFX frontShooter;
  private TalonFX backShooter;
  
  public Kicker() {
    frontShooter = new TalonFX(4); // IDs are NOT set in stone
    frontShooter.setNeutralMode(NeutralModeValue.Brake);
    backShooter = new TalonFX(5); // this one too
    backShooter.setNeutralMode(NeutralModeValue.Brake);
  }

  public void SetKickerPower(double frontPower, double backPower) {
    frontShooter.set(frontPower);
    backShooter.set(backPower);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
