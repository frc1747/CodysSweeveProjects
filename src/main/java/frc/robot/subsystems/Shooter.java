// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Dictionary;

import com.ctre.phoenix6.configs.TalonFXSConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFXS;
import com.ctre.phoenix6.signals.MotorArrangementValue;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Shooter extends SubsystemBase {
  // shooting dir is froward.
  private TalonFXS motorLeft;
  private TalonFXS motorRight;
  private TalonFXS motorHood;
  private DutyCycleOut dutyCycleShooter = new DutyCycleOut(0);
  private DutyCycleOut dutyCycleHood = new DutyCycleOut(0);
  private DutyCycleEncoder encoder;
  private Dictionary<Double[], Double> SetShootDistancePoints;
  private double[][] listOfSetPoints;

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

  public double autoAimSurfaceGetZ(double x, double y){
    return Constants.Shooter.SURFACE_A + Constants.Shooter.SURFACE_B*x + Constants.Shooter.SURFACE_C*y + Constants.Shooter.SURFACE_D*Math.pow(x,2) + Constants.Shooter.SURFACE_E*Math.pow(y,2) + Constants.Shooter.SURFACE_F*x*y;
  }

  public double getSpeedNeededFromAngle(double y, double z ){
    double C = Constants.Shooter.SURFACE_A + Constants.Shooter.SURFACE_C*y + Constants.Shooter.SURFACE_E*Math.pow(y,2) +- z;
    double B =  Constants.Shooter.SURFACE_B + Constants.Shooter.SURFACE_F;
    double A = Constants.Shooter.SURFACE_D;
    double aws = (- B + Math.sqrt( Math.pow(B, 2) - 4*A*C))/2*A; // we need to see if it's postive or negative
    if (aws > 0) return aws;
    return (- B - Math.sqrt( Math.pow(B, 2) - 4*A*C))/2*A;
    // slove with the good old quady for
  }

  public double getAngleNeededFromSpeed(double x, double z ){
    double C = Constants.Shooter.SURFACE_A + Constants.Shooter.SURFACE_B*x + Constants.Shooter.SURFACE_D*Math.pow(x,2) +- z;
    double B =  Constants.Shooter.SURFACE_C + Constants.Shooter.SURFACE_F;
    double A = Constants.Shooter.SURFACE_E;
    double aws = (- B + Math.sqrt( Math.pow(B, 2) - 4*A*C))/2*A; // we need to see if it's postive or negative
    if (aws > 0) return aws;
    return (- B - Math.sqrt( Math.pow(B, 2) - 4*A*C))/2*A;
    // slove with the good old quady for
  }

  public double[] findSpeedAndAngleFromDistance(double Distance){
    double currentAngle = getHoodAngle();
    double wantedPower = getSpeedNeededFromAngle(currentAngle,Distance);

    if (wantedPower <= Constants.Shooter.MAX_AUTOSHOOT_POWER) {
     double[] angleAndSpeed = {currentAngle, wantedPower};
      return angleAndSpeed;
  }
  double[] array = {0,0};
  return array;

  }



  
  


  @Override
  public void periodic() {
  }
}
