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
  /** Creates a new Shooter. */
  // idk what to caller the motors of the shooter
  // shooting dir is froward.
  private TalonFXS motorLeft;
  private TalonFXS motorRight;
  private TalonFXS motorHood;
  private DutyCycleOut dutyCycle = new DutyCycleOut(0);
  
  public Shooter() {

    TalonFXS motorLeft = new TalonFXS(Constants.Shooter.MOTOR_LEFT_PORT);
    TalonFXS motorRight = new TalonFXS(Constants.Shooter.MOTOR_RIGHT_PORT);
    TalonFXS motorHood = new TalonFXS(Constants.Shooter.MOTOR_HOOD_PORT);

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

  public double shoot(double power){
    dutyCycle.Output = power;
    motorLeft.setControl(dutyCycle);
    motorRight.setControl(-dutyCycle);
    return power;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
