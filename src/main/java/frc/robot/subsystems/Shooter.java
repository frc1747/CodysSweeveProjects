// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix6.hardware.TalonFXS;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXSConfiguration;
import com.ctre.phoenix6.configs.TalonFXSConfigurator;
import com.ctre.phoenix6.signals.MotorArrangementValue;

public class Shooter extends SubsystemBase {
  // shooting dir is froward.
  private TalonFXS motorLeft;
  private TalonFXS motorRight;
  private TalonFXS motorHood;
  private DutyCycleOut dutyCycle = new DutyCycleOut(0);
  
  public Shooter() {

    this.motorLeft = new TalonFXS(Constants.Shooter.MOTOR_LEFT_PORT);
    this.motorRight = new TalonFXS(Constants.Shooter.MOTOR_RIGHT_PORT);
    this.motorHood = new TalonFXS(Constants.Shooter.MOTOR_HOOD_PORT);

    // the config for all the motors we should do differnt ones
    TalonFXSConfiguration config = new TalonFXSConfiguration();

    config.Voltage
      .withPeakForwardVoltage(12)
      .withPeakReverseVoltage(-12);
    
    config.Commutation.MotorArrangement = MotorArrangementValue.Minion_JST;
    motorHood.getConfigurator().apply(config);
    motorLeft.getConfigurator().apply(config);
    motorRight.getConfigurator().apply(config);
  }

  public void shoot(double power){
    this.motorLeft.set(power);
    this.motorRight.set(-power);
  }

  @Override
  public void periodic() {
  }
}
