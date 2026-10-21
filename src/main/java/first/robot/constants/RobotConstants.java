package first.robot.constants;

import static org.wpilib.units.Units.Amps;

import org.wpilib.hardware.bus.CANPort;
import org.wpilib.units.measure.Current;

import com.ctre.phoenix6.CANBus;

public class RobotConstants {
    public static final int GAMEPAD_PORT = 0;
    
    public static final CANBus MAIN_BUS = new CANBus(CANPort.CAN_S0);
    public static final CANBus SECONDARY_BUS = new CANBus(CANPort.CAN_S1);

    public static final Current MAX_TALONFX_SUPPLY_CURRENT = Amps.of(40);
    public static final Current MAX_TALONFX_STATOR_CURRENT = Amps.of(60);

}
