package frc.robot;

import java.util.List;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;

public class Constants {
    public static final class Vision {
        // Local hostnames of the unique Limelights on the system
        // WARNING: IF YOU CHANGE OUT THE HARDWARE, ENSURE TO PROPERLY
        // SET THE HOSTNAME ON THE LIMELIGHT TO COORESPOND WITH ITS 
        // LOCATION ON THE BOT!!! 
        public static final String LIMELIGHT_REAR_LEFT = "limelight-rear-left";
        public static final String LIMELIGHT_REAR_RIGHT = "limelight-rear-right";
        public static final String LIMELIGHT_TURRET = "limelight-turret";

        // List of the active Limelights on the system to be used for Pose2D estimation
        // Add any Limelights defined above to this list.
        public static final List<String> ACTIVE_POSE_LIMELIGHTS = List.of(
            LIMELIGHT_REAR_LEFT,
            LIMELIGHT_REAR_RIGHT
        );

        // VISION_STDDEVS allows us to control how much we trust the values coming from the Limelight(s).
        // The higher the value (distance standard deviations), the less we trust it.
        // 
        // n1: X Position Standard Deviations in meters
        //     How wrong do we think vision could be about where we am on the field in X?
        // n2: Y Position Standard Deviations in meters
        //     How wrong do we think vision could be about where we are on the field in Y?
        // n3: Rotation (theta) Standard Deviations in RADIANS
        //     How wrong is vision about our heading?
        //
        // 0.7, 0.7, and 9999999 tells the code that we are somewhat trusting distant april tags
        // and basically completely trusting the Pigeon for Yaw.
        public static final Matrix<N3, N1> VISION_STDDEVS = VecBuilder.fill(0.7, 0.7, 9999999);

        // Limelight horizontal Field of view in degrees
        public static final double FOV_HORIZONTAL = 62.5;

        // AprilLock2 rotation compensation pid values
        public static final double APRIL_LOCK_P = 1.4;
        public static final double APRIL_LOCK_I = 1.0;
        public static final double APRIL_LOCK_D = 0.05;
        // maximum magnitude of PID output
        public static final double APRIL_LOCK_PID_CLAMP = 0.5;

        // targetting locations (maybe should be Pose2d objects, but those are mutable so maybe not?)
        public static final double FIELD_CENTER_X = 8.7741252;
        public static final double FIELD_CENTER_Y = 4.0259508;
    }

    public static class Controller {
        public static final int DRIVER_CONTROLLER_PORT = 0;
        public static final int OPERATOR_CONTROLLER_PORT = 1;
        public static final double STICK_DEADBAND = 0.05; 
    }

    public static final class Drivetrain {
        // TODO: Tune these later                  // v temporary slowing 
        public static final double MAX_SPEED = 4.1 * 0.5;  // Max speed in m/s
        public static final double MAX_ACCEL = 4.1;  // Max acceleration in m/s
        public static final double maxAngularVelocity = 10.0;  // Rad/s
    }

    public static final class Turret {
        public static final int MOTOR_PORT = 59;
        public static final int ENCODER_PORT_A = 6;
        public static final int ENCODER_PORT_B = 5;
        // gear ratio of 396 to 1 here probably but needs to be tested
        public static final double TURRETRATIO = 11; // the number of teeth on the turret's gear is 110 and the motor has a gear with 10 teeth
        public static final int encoderLimit = 5771 / 2; // temporary encoder value limit
        public static final double PID_P = 0;
        public static final double PID_I = 0; // needs tuning
        public static final double PID_D = 0;
        public static final double GO_TO_ANGLE_LOWER_SAFETY = -1;
        public static final double GO_TO_ANGLE_HIGHER_SAFETY = 1;
        public static final double DIST_TO_BOT_CENTER = 0.1529842; // meters
    }

    public static final class Shooter {
        public static final int MOTOR_LEFT_PORT = 41;
        public static final int MOTOR_RIGHT_PORT = 42;
        public static final int MOTOR_HOOD_PORT = 40;
    }
}