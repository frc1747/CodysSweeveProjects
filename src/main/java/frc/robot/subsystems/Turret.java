// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXSConfiguration;
import com.ctre.phoenix6.configs.TalonFXSConfigurator;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFXS;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorArrangementValue;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
  private TalonFXS motor;
  private DutyCycleEncoder encoder;

  // optimization for not creating new control object 50/sec
  private DutyCycleOut dutyCycle = new DutyCycleOut(0);

  private final PIDController pid = new PIDController(Constants.Turret.PID_D, Constants.Turret.PID_D, Constants.Turret.PID_D);


  public Turret() {
    motor = new TalonFXS(Constants.Turret.MOTOR_PORT);
    TalonFXSConfiguration config = new TalonFXSConfiguration();
    
    config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

    config.Voltage
      .withPeakForwardVoltage(12)
      .withPeakReverseVoltage(-12);
    
    config.Commutation.MotorArrangement = MotorArrangementValue.Minion_JST;
    motor.getConfigurator().apply(config);
    
    encoder = new DutyCycleEncoder(Constants.Turret.ENCODER_PORT);
    encoder.setInverted(false);  // if needed

    pid.enableContinuousInput(0.0, 360.0);
    pid.setTolerance(1.0);
  }


  public void basicSpin(double power) {
    dutyCycle.Output = power;
    motor.setControl(dutyCycle);
  }


  public double getTurretAngle() {
    return encoder.get() * 360 * 11;
  }

  // aim at a pose2d
  public double aimAtPose(Pose2d botPose, Pose2d TargetPose){
    double opp = botPose.getY() - TargetPose.getY(); 
    double adj = botPose.getX() - TargetPose.getX(); 
    // get the angle with basic trig
    double diffAngle = Math.toDegrees(Math.atan2(opp,adj));
    goToAngle(diffAngle);
    // moving to the angle  
    return diffAngle;
    //returning the angle to see the error.
  }


  public void goToAngle(double targetAngle) {
    double currentAngle = getTurretAngle();
    double output = pid.calculate(currentAngle, targetAngle);

    // Safety
    output = MathUtil.clamp(output, Constants.Turret.GO_TO_ANGLE_LOWER_SAFETY, Constants.Turret.GO_TO_ANGLE_HIGHER_SAFETY);

    dutyCycle.Output = output;
    motor.setControl(dutyCycle);
  }


  @Override
  public void periodic() {
    SmartDashboard.putBoolean("encoder connected?", encoder.isConnected());
    if (encoder.isConnected()) {
      SmartDashboard.putNumber("encoder value", encoder.get());
      SmartDashboard.putNumber("encoder angle", getTurretAngle());
    }
  }
}
