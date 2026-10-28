package first.robot.OpModes;

import org.wpilib.opmode.Teleop;

import first.robot.Robot;
import first.robot.mechanisms.Flywheel;

@Teleop
public class FlywheelTeleOp {
    private final Flywheel m_flywheel = new Flywheel();

    public FlywheelTeleOp(Robot robot) {
        // Add other gamepad bindings for controlling the flywheel mechanism
        // Example: Dpad Up to spin the flywheel forward, trigger to set velocity based on the trigger value
        
        // right face - fast forward
        // bottom face - slow forward
        // top face - slow backward
        // left face - fast backward 

        robot.gamepad.start().onTrue(m_flywheel.stop());
        robot.gamepad.faceDown().onTrue(m_flywheel.runSlow());
        robot.gamepad.faceRight().onTrue(m_flywheel.runFast());
        robot.gamepad.faceUp().onTrue(m_flywheel.runSlowBackward());
        robot.gamepad.faceLeft().onTrue(m_flywheel.runFastBackward());


    }
}
