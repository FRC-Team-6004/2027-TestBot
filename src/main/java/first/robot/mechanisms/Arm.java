package first.robot.mechanisms;

import org.wpilib.command3.Mechanism;
import org.wpilib.epilogue.Logged;
import org.wpilib.units.measure.Angle;
import org.wpilib.command3.Command;

import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import static first.robot.constants.ArmConstants.*; 
@Logged
public class Arm implements Mechanism {
    private TalonFX m_motor;

    private final NeutralOut m_neutralRequest = new NeutralOut();
    private final PositionVoltage m_positionRequest = new PositionVoltage(0);
    
    // TODO: Add requests for controlling the arm mechanism (VelocityVoltage, PositionVoltage)

    public Arm() {
        m_motor = new TalonFX(MOTOR_ID, BUS);
        m_motor.getConfigurator().apply(MOTOR_CONFIG);
        setDefaultCommand(stop());
    }
    
    // TODO: Add methods for controlling the arm mechanism (e.g., moveToPosition, armUp, armDown)

    public Command armUp() {
        return setAngle(UP_ANGLE);
    }

    public Command armDown() {
        return setAngle(DOWN_ANGLE);
    }

    public Command setAngle(Angle angle) {
        return run(coroutine -> {
            m_motor.setControl(m_positionRequest.withPosition(angle));
            coroutine.park();
        }).named("set arm angle");
    }
    // This command will run until interrupted
    public Command stop() {
        return run(coroutine -> {
            m_motor.setControl(m_neutralRequest);
            coroutine.park();
        }).named("stop arm");
    }
}
