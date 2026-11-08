// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import org.wpilib.command3.Scheduler;
import org.wpilib.command3.button.CommandGamepad;
import org.wpilib.framework.OpModeRobot;

import first.robot.mechanisms.*;

import static first.robot.constants.RobotConstants.*;
/**
 * Steps to create the testBot:
 * 
 * Create the commands for the mechanisms
 * Create the gamepad bindings for the mechanisms to test
 * Add telemetry / logging
 * Connect to a dashboard
 * Add simulations
 */
public class Robot extends OpModeRobot {

  public final CommandGamepad gamepad = new CommandGamepad(GAMEPAD_PORT);

  // Mechanisms
  public final Arm      m_arm      = new Arm();
  public final Elevator m_elevator = new Elevator();
  public final Flywheel m_flywheel = new Flywheel();


  public Robot() {}

  @Override
  public void robotPeriodic() {
    Scheduler.getDefault().run();
  }
}
