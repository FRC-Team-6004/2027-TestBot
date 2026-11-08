package first.robot.OpModes;

import org.wpilib.opmode.OpMode;
import org.wpilib.opmode.Teleop;

import first.robot.Robot;

@Teleop
public class ElevatorTeleOp implements OpMode {

    public ElevatorTeleOp(Robot robot) {
        // Add other gamepad bindings for controlling the elevator mechanism
        // Example: Dpad Up to move the elevator up, trigger to move to the position based on the trigger value
        robot.gamepad.faceRight().onTrue(robot.m_elevator.stop());
    }
}
