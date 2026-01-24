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

    public static class DrivetrainConstants {
        // TODO: Tune these later
        public static final double MAX_SPEED = 4.1;  // Max speed in m/s
        public static final double MAX_ACCEL = 4.1;  // Max acceleration in m/s
        public static final double maxAngularVelocity = 10.0;  // Rad/s
    }
    
    public static class VisionConstants {
        public static final double FOV_HORIZONTAL = 62.5;
    }
}
