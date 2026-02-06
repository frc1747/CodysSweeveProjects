// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix6.hardware.TalonFXS;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXSConfiguration;
import com.ctre.phoenix6.configs.TalonFXSConfigurator;
import com.ctre.phoenix6.signals.MotorArrangementValue;

public class Shooter extends SubsystemBase {
  // shooting dir is froward.
  private TalonFXS motorLeft;
  private TalonFXS motorRight;
  private TalonFXS motorHood;
  private DutyCycleOut dutyCycleShooter = new DutyCycleOut(0);
  private DutyCycleOut dutyCycleHood = new DutyCycleOut(0);
  private DutyCycleEncoder encoder;

  public Shooter() {

    this.motorLeft = new TalonFXS(Constants.Shooter.MOTOR_LEFT_PORT);
    this.motorRight = new TalonFXS(Constants.Shooter.MOTOR_RIGHT_PORT);
    this.motorHood = new TalonFXS(Constants.Shooter.MOTOR_HOOD_PORT);

    this.encoder = new DutyCycleEncoder(Constants.Shooter.ENCODER_PORT);
    // this is use to set the control to follow master motor
    motorLeft.setControl(new Follower(Constants.Shooter.MOTOR_RIGHT_PORT, true));

    // config for the shooter motors
    TalonFXSConfiguration configShooter = new TalonFXSConfiguration();

    configShooter.Voltage
      .withPeakForwardVoltage(12)
      .withPeakReverseVoltage(-12);
    
    configShooter.Commutation.MotorArrangement = MotorArrangementValue.Minion_JST;

    // the config for all the motors we should do differnt ones
    TalonFXSConfiguration configHood = new TalonFXSConfiguration();

    configHood.Voltage
      .withPeakForwardVoltage(12)
      .withPeakReverseVoltage(-12);
    
    configHood.Commutation.MotorArrangement = MotorArrangementValue.Minion_JST;
    motorHood.getConfigurator().apply(configHood);
    motorLeft.getConfigurator().apply(configShooter);
    motorRight.getConfigurator().apply(configShooter);
  }

  public void shoot(double power){
    dutyCycleShooter.Output = power;
    motorRight.setControl(dutyCycleShooter);
  }
  
  public void moveHood(double power){
    dutyCycleHood.Output = power;
    motorRight.setControl(dutyCycleHood);
  }

  public double getHood(){
    return (encoder.get()  + Constants.Shooter.ENCODER_OFFSET );
  }

  public double getHoodAngle(){
    return (encoder.get()  + Constants.Shooter.ENCODER_OFFSET ) * 360;
  }

  

  @Override
  public void periodic() {
  }
}
