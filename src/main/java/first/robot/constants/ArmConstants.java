package first.robot.constants;

import static first.robot.constants.RobotConstants.*;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class ArmConstants {
    // TODO: Update the Motor ID with the actual value (Should stay on MAIN_BUS)
    public static final int MOTOR_ID = 1;
    public static final CANBus BUS = MAIN_BUS;  

    // TODO: Update the gear ratio with the actual value for the arm mechanism
    public static final double GEAR_RATIO = 100; 

    // Motor configuration for the arm mechanism
    // Do not edit without understanding what each option does (feel free to read the documentation or ask for help)
    public static final TalonFXConfiguration MOTOR_CONFIG = new TalonFXConfiguration()
            .withSlot0(new Slot0Configs()
                .withKS(0.1)
                .withKV(0.1)
                .withKA(0)
                .withKG(0)  // Update gravity constant for arm mechanism
                .withKP(1)
                .withKI(0)
                .withKD(0)
                .withGravityType(GravityTypeValue.Arm_Cosine))
            .withCurrentLimits(new CurrentLimitsConfigs()
                .withSupplyCurrentLimit(MAX_TALONFX_SUPPLY_CURRENT)
                .withSupplyCurrentLimitEnable(true)
                .withStatorCurrentLimit(MAX_TALONFX_STATOR_CURRENT)
                .withStatorCurrentLimitEnable(true))
            .withMotorOutput(new MotorOutputConfigs()
                .withNeutralMode(NeutralModeValue.Brake)
                .withInverted(InvertedValue.CounterClockwise_Positive))
            .withFeedback(new FeedbackConfigs()
                .withSensorToMechanismRatio(GEAR_RATIO));
}
