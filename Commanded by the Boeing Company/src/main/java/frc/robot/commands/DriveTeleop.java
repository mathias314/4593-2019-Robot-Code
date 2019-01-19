/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveTeleop extends Command {
  public DriveTeleop() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Switching to driver control...");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double rawFive = Robot.m_oi.xboxController.getRawAxis(1);
    double rawOne = Robot.m_oi.xboxController.getRawAxis(5);
    double five = Math.abs(0.75 * rawOne) > 0.15 ? 0.75 * rawOne : 0; // left forwards allegedly
    double one = Math.abs(0.75 * rawFive) > 0.15 ? 0.75 * rawFive : 0; // right forwards
    Robot.m_subsystem.m_FLM.set(ControlMode.Current, one);
    Robot.m_subsystem.m_RLM.set(ControlMode.Current, one);
    Robot.m_subsystem.m_FRM.set(ControlMode.Current, five);
    Robot.m_subsystem.m_RRM.set(ControlMode.Current, five);

    if(Robot.m_oi.xboxController.getStartButton()){
      Scheduler.getInstance().add(new ArduinoMove());
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
