package first.robot.OpModes;

import org.wpilib.opmode.Teleop;

import first.robot.Robot;
import first.robot.mechanisms.Arm;

@Teleop
public class ArmTeleOp {
    private final Arm m_arm = new Arm();

    public ArmTeleOp(Robot robot) {
        // Add other gamepad bindings for controlling the arm mechanism
        // Example: Dpad Up to move the arm up, trigger to move to the position based on the trigger value
        robot.gamepad.faceRight().onTrue(m_arm.stop());
    }
}
