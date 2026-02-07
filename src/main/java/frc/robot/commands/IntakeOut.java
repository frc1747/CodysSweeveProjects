package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Intake;

public class IntakeOut extends Command {
    private Intake intake;
    public IntakeOut(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
            }
            public IntakeOut(double d) {
        //TODO Auto-generated constructor stub
    }
            private void addRequirements(Intake intake2) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'addRequirements'");
            }
    @Override
    public void initialize() {
        this.intake.SetIntakePower(2);
    }
    @Override
    public void execute() {}
    @Override
    public void end(boolean interrupted){
        this.intake.SetIntakePower(0);
    }
    @Override
    public boolean isFinished(){
        return false;
    }
}
