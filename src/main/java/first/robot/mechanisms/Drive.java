package first.robot.mechanisms;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;
import org.wpilib.drive.DifferentialDrive;
import org.wpilib.epilogue.Logged;
import org.wpilib.math.linalg.VecBuilder;
import org.wpilib.math.system.DCMotor;
import org.wpilib.simulation.DifferentialDrivetrainSim;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;

import first.robot.constants.DriveConstants;
import first.robot.constants.DriveConstants.SimulationConstants;

import static first.robot.constants.DriveConstants.*;
import static org.wpilib.units.Units.Kilograms;
import static org.wpilib.units.Units.Meters;

import java.util.function.Supplier;
@Logged
public class Drive implements Mechanism {

    private final DifferentialDrive differentialDrive;

    private final SparkMax leftLeader;
    private final SparkMax leftFollower;
    private final SparkMax rightLeader;
    private final SparkMax rightFollower;

    private final DCMotor dcmotor;
    // Create the simulation model of our drivetrain.
    DifferentialDrivetrainSim m_driveSim = new DifferentialDrivetrainSim(
        SimulationConstants.gearing,                    // 7.29:1 gearing reduction.
        SimulationConstants.interia,                     // MOI of 7.5 kg m^2 (from CAD model).
        SimulationConstants.mass.in(Kilograms),                    // The mass of the robot is 60 kg.
        SimulationConstants.wheelRadius.in(Meters),                    // The robot uses 3" radius wheels.
        SimulationConstants.trackWidth.in(Meters),                  // The track width is 0.7112 meters.
        // The standard deviations for measurement noise:
        // x and y:          0.001 m
        // heading:          0.001 rad
        // l and r velocity: 0.1   m/s
        // l and r position: 0.005 m
        null);
        
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
