package frc.robot;

import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.ctre.phoenix6.swerve.SwerveModuleConstants;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
    
    
public class Constants {
    public static class ControllerConstants {
        public static final int DRIVER_CONTROLLER_PORT = 0;
        public static final int OPERATOR_CONTROLLER_PORT = 1;
        public static final double STICK_DEADBAND = 0.05; 
    }

    public static final class DrivetrainConstants {
        // TODO: Tune these later
        public static final double MAX_SPEED = 4.1;  // Max speed in m/s
        public static final double MAX_ACCEL = 4.1;  // Max acceleration in m/s
        public static final double maxAngularVelocity = 10.0;  // Rad/s
    }

    public static final class Turret {
        public static final int MOTOR_PORT = 59;
        public static final int ENCODER_PORT = 0;
        public static final double TURRETRATIO = 11; // the number of teeth on the turret's gear is 110 and the motor has a gear with 10 teeth
        public static final double PID_P = 0;
        public static final double PID_I = 0; // needs tuning
        public static final double PID_D = 0;
        public static final double GO_TO_ANGLE_LOWER_SAFETY = -1;
        public static final double GO_TO_ANGLE_HIGHER_SAFETY = 1;
    }

    public static final class Shooter {
        public static final int MOTOR_LEFT_PORT = 41;
        public static final int MOTOR_RIGHT_PORT = 42;
        public static final int MOTOR_HOOD_PORT = 40;
    }

    public static final class VisionConstants {
        public static final double FOV_HORIZONTAL = 62.5;
    }
}
