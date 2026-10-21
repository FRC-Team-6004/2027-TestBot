package first.robot.OpModes;

import org.wpilib.opmode.Teleop;

import first.robot.Robot;
import first.robot.mechanisms.Elevator;

@Teleop
public class ElevatorTeleOp {
    private final Elevator m_elevator = new Elevator();

    public ElevatorTeleOp(Robot robot) {
        // Add other gamepad bindings for controlling the elevator mechanism
        // Example: Dpad Up to move the elevator up, trigger to move to the position based on the trigger value
        robot.gamepad.faceRight().onTrue(m_elevator.stop());
    }
}
