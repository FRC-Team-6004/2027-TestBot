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
        robot.gamepad.faceRight().onTrue(m_flywheel.stop());
    }
}
