package frc.robot;

import java.util.List;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;

public class Constants {
    public static final class Turret {
        public static final int MOTOR_PORT = 0;
        public static final int ENCODER_PORT = 0;
    }

    public static final class Vision {
        // Local hostnames of the unique Limelights on the system
        // WARNING: IF YOU CHANGE OUT THE HARDWARE, ENSURE TO PROPERLY
        // SET THE HOSTNAME ON THE LIMELIGHT TO COORESPOND WITH ITS 
        // LOCATION ON THE BOT!!! 
        public static final String LIMELIGHT_FRONT = "limelight-front";
        public static final String LIMELIGHT_REAR = "limelight-rear";
        public static final String LIMELIGHT_TURRET = "limelight-turret";

        // List of the active Limelights on the system to be used for Pose2D estimation
        // Add any Limelights defined above to this list.
        public static final List<String> ACTIVE_POSE_LIMELIGHTS = List.of(LIMELIGHT_FRONT);

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

    }
}