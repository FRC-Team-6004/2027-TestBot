package first.robot.constants;

import static first.robot.constants.RobotConstants.*;
import static org.wpilib.units.Units.Amps;

import org.wpilib.hardware.bus.CANPort;

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
