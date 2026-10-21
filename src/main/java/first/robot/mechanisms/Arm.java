package first.robot.mechanisms;

import org.wpilib.command3.Mechanism;
import org.wpilib.command3.Command;

import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.hardware.TalonFX;

import static first.robot.constants.ArmConstants.*; 

public class Arm implements Mechanism {
    private TalonFX m_motor;

    private final NeutralOut m_neutralRequest = new NeutralOut();
    
    // TODO: Add requests for controlling the arm mechanism (VelocityVoltage, PositionVoltage)

    public Arm() {
        m_motor = new TalonFX(MOTOR_ID, BUS);
        m_motor.getConfigurator().apply(MOTOR_CONFIG);
        setDefaultCommand(stop());
    }
    
    // TODO: Add methods for controlling the arm mechanism (e.g., moveToPosition, armUp, armDown)


    // This command will run until interrupted
    public Command stop() {
        return run(coroutine -> {
            m_motor.setControl(m_neutralRequest);
            coroutine.park();
        }).named("stop arm");
    }
}
