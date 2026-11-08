package first.robot.constants;

import static first.robot.constants.RobotConstants.*;
import static org.wpilib.units.Units.Amps;

import org.wpilib.hardware.bus.CANPort;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;



public class ElevatorConstants {
    public static final int MOTOR_ID = 15;
    public static final CANPort PORT = CANPort.CAN_S1;

    // TODO: Update the gear ratio with the actual value for the elevator mechanism
    public static final double MOTOR_ROTATIONS_PER_METER = 100;

    // Motor configuration for the elevator mechanism
    // Do not edit without understanding what each option does (feel free to read the documentation or ask for help)
    public static final SparkMaxConfig MOTOR_CONFIG = new SparkMaxConfig();

    static {
        MOTOR_CONFIG
            .idleMode(IdleMode.kBrake)
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
