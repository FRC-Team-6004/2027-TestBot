package first.robot.mechanisms;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;
import org.wpilib.drive.DifferentialDrive;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import static first.robot.constants.DriveConstants.*;

import java.util.function.Supplier;

public class Drive implements Mechanism {

    private final DifferentialDrive differentialDrive;

    private final SparkMax leftLeader;
    private final SparkMax leftFollower;
    private final SparkMax rightLeader;
    private final SparkMax rightFollower;

    public Drive() {
        leftLeader = new SparkMax(PORT, LEFT_LEADER_PORT, SparkMax.MotorType.kBrushless);
        leftFollower = new SparkMax(PORT, LEFT_FOLLOWER_PORT, SparkMax.MotorType.kBrushless);
        rightLeader = new SparkMax(PORT, RIGHT_LEADER_PORT, SparkMax.MotorType.kBrushless);
        rightFollower = new SparkMax(PORT, RIGHT_FOLLOWER_PORT, SparkMax.MotorType.kBrushless);
        differentialDrive = new DifferentialDrive(leftLeader::setThrottle, rightLeader::setThrottle);

        leftLeader.configure(MOTOR_CONFIG, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightLeader.configure(MOTOR_CONFIG, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // Add follower code

        leftFollower.configure(MOTOR_CONFIG, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightFollower.configure(MOTOR_CONFIG, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        setDefaultCommand(stop());
    }

    // Will tankDrive until something stops it. 
    public Command tankDrive(Supplier<Double> left, Supplier<Double> right) {
        System.out.println("Starting Tank Drive");
        return run(coroutine -> {
            while (true) {
                differentialDrive.tankDrive(left.get(), right.get());
                coroutine.yield();
            }
        }).named("Tank Drive");
    }

    public Command arcadeDrive(Supplier<Double> forward, Supplier<Double> turn) {
        System.out.println("Starting Arcade Drive");
        return run(coroutine -> {
            while (true) {
                differentialDrive.arcadeDrive(forward.get(), turn.get());
                coroutine.yield();
            }
        }).named("Arcade Drive");
    }

    public Command curvatureDrive(Supplier<Double> forward, Supplier<Double> turn, Supplier<Boolean> turnInPlace) {
        System.out.println("Starting Curvature Drive");
        return run(coroutine -> {
            while (true) {
                differentialDrive.curvatureDrive(forward.get(), turn.get(), turnInPlace.get());
                coroutine.yield();
            }
        }).named("Curvature Drive");
    }

    public Command stop() {
        return run(coroutine -> {
            leftLeader.stopMotor();
            rightLeader.stopMotor();
        }).named("Stop Drive");
    }
    
}
