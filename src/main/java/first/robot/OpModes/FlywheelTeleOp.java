package first.robot.OpModes;

import org.wpilib.opmode.OpMode;
import org.wpilib.opmode.Teleop;

import first.robot.Robot;

@Teleop
public class FlywheelTeleOp implements OpMode{

    public FlywheelTeleOp(Robot robot) {
        // Add other gamepad bindings for controlling the flywheel mechanism
        // Example: Dpad Up to spin the flywheel forward, trigger to set velocity based on the trigger value
        
        // right face - fast forward
        // bottom face - slow forward
        // top face - slow backward
        // left face - fast backward 

        robot.gamepad.start().onTrue(robot.m_flywheel.stop());
        robot.gamepad.faceDown().onTrue(robot.m_flywheel.runSlow());
        robot.gamepad.faceRight().onTrue(robot.m_flywheel.runFast());
        robot.gamepad.faceUp().onTrue(robot.m_flywheel.runSlowBackward());
        robot.gamepad.faceLeft().onTrue(robot.m_flywheel.runFastBackward());


    }
}
