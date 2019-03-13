/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class FkCommand extends Command {
  public FkCommand() {
    requires(Robot.m_fk);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.m_fk.sparkTwo.setInverted(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // System.out.println(Robot.m_fk.m_raiseTo.get());
    double rawRight = Robot.m_oi.auxController.getTriggerAxis(Hand.kRight);
    double rawLeft = Robot.m_oi.auxController.getTriggerAxis(Hand.kLeft);

    SmartDashboard.putBoolean("Forklift Limit", !Robot.m_fk.m_raiseTo.get());

    // System.out.println("The forklift is tweaking");

    // double voltage = Robot.m_fk.fk.get();
    // System.out.println(voltage);
    // Getting voltage from a pwm motor controller group? 

    // System.out.println(Robot.m_fk.m_raiseTo.get());

    if (rawLeft > 0.15){
      Robot.m_fk.fk.set(-rawLeft); // possibly .75 on the actual comp bot, due to different gearings
      Robot.m_fk.lastStop = System.currentTimeMillis();
    } else if (rawRight > 0.15 && Robot.m_fk.m_raiseTo.get()){
      Robot.m_fk.fk.set(rawRight); // possibly .75 on the actual comp bot, due to different gearings
      Robot.m_fk.lastStop = System.currentTimeMillis();
    } else if (System.currentTimeMillis() - Robot.m_fk.lastStop < 7521) {
      Robot.m_fk.fk.set(.15);
    }else {
      Robot.m_fk.fk.set(0);
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
