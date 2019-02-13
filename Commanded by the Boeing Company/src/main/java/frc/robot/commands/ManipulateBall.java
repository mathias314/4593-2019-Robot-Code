/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManipulateBall extends Command {
  public ManipulateBall() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_ballMan);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    System.out.println(Robot.m_ballMan.m_ballLimitSwitch.get()); 
    

    Boolean limitPressed = Robot.m_ballMan.m_ballLimitSwitch.get();
    if (Robot.m_oi.auxController.getXButton()){
      Robot.m_ballMan.m_bMotor.set(-.6);
    } else if (limitPressed == false) {
      Robot.m_ballMan.m_bMotor.set(0);
      try {
        Thread.sleep(300); 
      } catch (Exception e) {
        //TODO: handle exception
      }
    } else if (Robot.m_oi.auxController.getYButton()) {
        Robot.m_ballMan.m_bMotor.set(-.6);
      }
      else {
        Robot.m_ballMan.m_bMotor.set(0);
      }
    
    }
  
  
    

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.m_ballMan.m_ballLimitSwitch.get();
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
