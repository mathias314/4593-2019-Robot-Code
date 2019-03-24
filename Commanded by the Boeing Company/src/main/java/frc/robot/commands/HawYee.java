/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HawYee extends Command {
  public HawYee() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_ohgod);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.auxController.getStartButton() && System.currentTimeMillis() - Robot.m_ohgod.lastChange > 500 && DriverStation.getInstance().getMatchTime() < 30){
      Robot.m_ohgod.ClimberFront.set(!Robot.m_ohgod.cf_state);
      Robot.m_ohgod.cf_state = !Robot.m_ohgod.cf_state;
      Robot.m_ohgod.lastChange = System.currentTimeMillis();
    } else if (Robot.m_oi.auxController.getBackButton() && System.currentTimeMillis() - Robot.m_ohgod.lastChange > 500 && DriverStation.getInstance().getMatchTime() < 30){
      Robot.m_ohgod.ClimberBack.set(!Robot.m_ohgod.cb_state);
      Robot.m_ohgod.cb_state = !Robot.m_ohgod.cb_state;
      Robot.m_ohgod.lastChange = System.currentTimeMillis();
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
