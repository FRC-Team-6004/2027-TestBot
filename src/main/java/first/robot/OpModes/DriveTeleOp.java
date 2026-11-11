package first.robot.OpModes;

import org.wpilib.opmode.OpMode;
import org.wpilib.opmode.Teleop;

import first.robot.Robot;

@Teleop
public class DriveTeleOp implements OpMode {
    
    public DriveTeleOp(Robot robot) {

        // Tank drive with a given left and right rates
        robot.gamepad.faceDown().onTrue(
            robot.m_drive.tankDrive(
                () -> -robot.gamepad.getLeftY(), 
                () -> -robot.gamepad.getRightY()
            )
        );
        
        // Arcade drive with a given forward and turn rate
        robot.gamepad.faceRight().onTrue(
            robot.m_drive.arcadeDrive(
                () -> -robot.gamepad.getLeftY(),
                () -> -robot.gamepad.getLeftX()
            )
        );

        // Curvature drive with a given forward and turn rate, as well as a button for turning in-place.
        
        robot.gamepad.faceLeft().onTrue(
            robot.m_drive.curvatureDrive(
                () -> -robot.gamepad.getLeftY(),
                () -> -robot.gamepad.getLeftX(),
                () -> robot.gamepad.getRightTrigger() > 0.5
            )
        );
    }
}
