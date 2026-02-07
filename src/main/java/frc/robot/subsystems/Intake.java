package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.Constants;

public class Intake {
    private TalonFX motorArm;
    private TalonFX motorWheels;

    public Intake() {
        motorArm = new TalonFX(50);
        motorArm.setNeutralMode(NeutralModeValue.Brake);
        motorWheels = new TalonFX(51);
        motorWheels.setNeutralMode(NeutralModeValue.Brake);
    }
    public void SetIntakePower(double armPower, double wheelsPower) {
        motorArm.set(armPower);
        motorWheels.set(wheelsPower);
    }
}
