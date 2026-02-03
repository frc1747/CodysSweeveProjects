// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Kicker extends SubsystemBase {
  /** Creates a new Kicker. */

  private TalonFX shooter;
  
  public Kicker() {
    shooter = new TalonFX(56); // PLEASE CHANGE THIS TO THE ACTUAL ID. THIS IS ARBITRARY. PLEASE!!! I BEG YOU!!! PLEASE :(!!!
    shooter.setNeutralMode(NeutralModeValue.Brake);
  }

  public void SetKickerPower(double power) {
    shooter.set(power);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
