package first.robot.mechanisms;

import org.wpilib.command3.Mechanism;
import org.wpilib.units.measure.Distance;

import static org.wpilib.units.Units.Meters;

import org.wpilib.command3.Command;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkLowLevel.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import static first.robot.constants.ElevatorConstants.*; 

public class Elevator implements Mechanism {
    private SparkMax m_motor;

    public Elevator() {
        m_motor = new SparkMax(PORT, MOTOR_ID, MotorType.kBrushless);
        m_motor.configure(null, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        setDefaultCommand(stop());
    }

    // Extend the distance and holds the position
    public Command Extend(Distance distance) {
        return run(coroutine -> {
            double targetRotations = m_motor.getEncoder().getPosition().get()
                + distance.in(Meters) * MOTOR_ROTATIONS_PER_METER;

            m_motor.getClosedLoopController().setSetpoint(
                targetRotations,
                ControlType.kPosition,
                ClosedLoopSlot.kSlot0);


            coroutine.park();
        }).named("extend elevator");
    }
    
    // This command will run until interrupted
    public Command stop() {
        return run(coroutine -> {
            m_motor.stopMotor();
            coroutine.park();
        }).named("stop elevator");
    }
}
