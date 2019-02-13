/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class SPARKDriveTeleop extends Command {
  public SPARKDriveTeleop() {
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
    double rawFive = Robot.m_oi.xboxController.getRawAxis(5);
    double rawOne = Robot.m_oi.xboxController.getRawAxis(1);
    double one = Math.abs(1 * rawOne) > 0.25 ? -3000 * Math.pow(rawOne, 1) : 0; // left forwards allegedly
    double five = Math.abs(1 * rawFive) > 0.25 ? -3000 * Math.pow(rawFive, 1) : 0; // right forwards
    // SmartDashboard.putNumber("left", Robot.m_subsystem.m_encoder_left.getVelocity());
    // SmartDashboard.putNumber("right", Robot.m_subsystem.m_encoder_right.getVelocity());
    if(one != 0){
      Robot.m_subsystem.m_econtroller_left.setReference(one,ControlType.kVelocity);
    } else {
      Robot.m_subsystem.m_FLM.set(0);
    }
    if(five != 0){
      Robot.m_subsystem.m_econtroller_right.setReference(five, ControlType.kVelocity);
    } else {
      Robot.m_subsystem.m_FRM.set(0);
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
