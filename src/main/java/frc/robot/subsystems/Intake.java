package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXSConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.TalonFXS;
import com.ctre.phoenix6.signals.MotorArrangementValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private TalonFXS motorArm;
    private TalonFXS motorWheels;
    private DutyCycleOut dutyCycle = new DutyCycleOut(0);

    public Intake() {
        this.motorArm = new TalonFXS(Constants.Intake.MOTOR_ARM_PORT);
        this.motorWheels = new TalonFXS(Constants.Intake.MOTOR_WHEELS_PORT);
        
        TalonFXSConfiguration config = new TalonFXSConfiguration();
        
        config.Voltage
            .withPeakForwardVoltage(12);

        config.Commutation.MotorArrangement = MotorArrangementValue.Minion_JST;
        motorArm.getConfigurator().apply(config);
        motorWheels.getConfigurator().apply(config);

    }
    public void intakespin(double power) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'intakespin'");
    }
    
    public void intakeout(double power) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'intakeout'");
        }

    public void SetIntakePower(double armPower, double wheelsPower) {
        this.motorArm.set(armPower);
        this.motorWheels.set(wheelsPower);
    }
}
