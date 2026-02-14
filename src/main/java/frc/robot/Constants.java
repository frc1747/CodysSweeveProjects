package frc.robot;

public class Constants {
    public static final class Turret {
        public static final int MOTOR_PORT = 59;
        public static final int ENCODER_PORT = 0;
        public static final double TURRETRATIO = 11; // the number of teeth on the turret's gear is 110 and the motor has a gear with 10 teeth
        public static final double PID_P = 0;
        public static final double PID_I = 0; // needs tuning
        public static final double PID_D = 0;
        public static final double GO_TO_ANGLE_LOWER_SAFETY = -1;
        public static final double GO_TO_ANGLE_HIGHER_SAFETY = 1;
        public static final double UPPER_LIMIT = 90;
        public static final double LOWER_LIMIT = -90;
    }
    public static final class Shooter {
        public static final int MOTOR_LEFT_PORT = 41;
        public static final int MOTOR_RIGHT_PORT = 42;
        public static final int MOTOR_HOOD_PORT = 40;
        public static final int ENCODER_PORT = 1; // needs to be set
        public static final double ENCODER_OFFSET = .2; // needs to be set o7
        public static final double SURFACE_A = 44.1596; // needs tuning
        public static final double SURFACE_B = -4.3595; // needs tuning
        public static final double SURFACE_C = -0.94726; // needs tuning
        public static final double SURFACE_D = 0.12545; // needs tuning
        public static final double SURFACE_E = 0.36687; // needs tuning
        public static final double SURFACE_F = 0.017314; // needs tuning
        public static final double MAX_AUTOSHOOT_POWER =.85;
        public static final double MAX_HOOD_ANGLE = 43;// degrees
        public static final double MIN_HOOD_ANGLE = 26;// degrees
        public static final double PID_P = 0;
        public static final double PID_I = 0;
        public static final double PID_D = 0;

    }
}
