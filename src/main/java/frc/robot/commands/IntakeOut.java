package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeOut extends Command {
    private Intake intake;
    private double power;

    public IntakeOut(Intake intake, double power) {
        this.intake = intake;
        this.power = power;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.intakeout(power);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void end(boolean interrupted) {
        intake.intakeout(0.0);
    } 

}
