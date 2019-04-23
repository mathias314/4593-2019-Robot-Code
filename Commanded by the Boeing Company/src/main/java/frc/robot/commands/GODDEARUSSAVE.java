/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class GODDEARUSSAVE extends Command {
  public GODDEARUSSAVE() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_ohgod);
    requires(Robot.m_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_subsystem.autoTimer = System.currentTimeMillis();
    Robot.m_ohgod.ClimberBack.set(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    long diff = System.currentTimeMillis() - Robot.m_subsystem.autoTimer;
    if(diff > 500){
      Robot.m_subsystem.m_FLM.set(0.3);
      Robot.m_subsystem.m_FRM.set(0.3);
    }
    if(diff > 2250 && diff < 3000){
      Robot.m_ohgod.ClimberFront.set(true);
      Robot.m_ohgod.ClimberBack.set(false);
    }
    if(diff > 4500){
      Robot.m_ohgod.ClimberFront.set(false);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return System.currentTimeMillis() - Robot.m_subsystem.autoTimer > 5000;
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
