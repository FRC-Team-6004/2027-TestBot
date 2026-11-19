package first.robot.constants;

import static first.robot.constants.RobotConstants.*;
import static org.wpilib.units.Units.*;

import org.wpilib.hardware.bus.CANPort;
import org.wpilib.math.linalg.VecBuilder;
import org.wpilib.math.system.DCMotor;
import org.wpilib.simulation.DifferentialDrivetrainSim;
import org.wpilib.units.measure.Distance;
import org.wpilib.units.measure.Mass;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class DriveConstants {
    // All drive on the same CANPort
    public static final CANPort PORT = CANPort.CAN_S1;

    public static final int LEFT_LEADER_PORT = 1;
    public static final int LEFT_FOLLOWER_PORT = 2;
    public static final int RIGHT_LEADER_PORT = 3;
    public static final int RIGHT_FOLLOWER_PORT = 4;

    public static final SparkMaxConfig MOTOR_CONFIG = new SparkMaxConfig();
    public static final double GEAR_RATIO = 10.71; // Update with actual gear ratio for the drive mechanism
    
    // TODO: Update the values with real measurements
    public class SimulationConstants {
        public static double gearing = 7.29; // Update with actual gear ratio for the drive mechanism
        public static double interia = 7.0;
        public static Mass mass = Kilograms.of(60.0); // Update with actual mass of the robot
        public static Distance wheelRadius = Inches.of(10); // Update with actual wheel radius
        public static Distance trackWidth = Inches.of(20); // Update with actual track width
    }

    static {
        MOTOR_CONFIG
            .idleMode(IdleMode.kCoast)
            .inverted(false)
            .smartCurrentLimit((int)MAX_MOTOR_SUPPLY_CURRENT.in(Amps))
            .secondaryCurrentLimit((int)MAX_MOTOR_STATOR_CURRENT.in(Amps));

        /* Broken in the current alpha? 
        MOTOR_CONFIG.encoder
            .positionConversionFactor(1.0 / GEAR_RATIO)
            .velocityConversionFactor(1.0 / GEAR_RATIO / 60.0);
        */

        MOTOR_CONFIG.closedLoop
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
            .pid(1.0, 0.0, 0.0, ClosedLoopSlot.kSlot0);
    }
}
