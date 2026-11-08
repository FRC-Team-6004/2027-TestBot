package first.robot.OpModes;

import org.wpilib.opmode.Teleop;
import org.wpilib.opmode.OpMode;

import first.robot.Robot;

@Teleop
public class ArmTeleOp implements OpMode{

    public ArmTeleOp(Robot robot) {
        // Add other gamepad bindings for controlling the arm mechanism
        // Example: Dpad Up to move the arm up, trigger to move to the position based on the trigger value
        robot.gamepad.faceRight().onTrue(robot.m_arm.stop());
    }
}
