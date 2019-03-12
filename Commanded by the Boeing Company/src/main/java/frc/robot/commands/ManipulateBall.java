/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Relay.Value;
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

    // System.out.println(Robot.m_ballMan.m_ballLimitSwitch.get()); 
    

    Boolean limitPressed = Robot.m_ballMan.m_ballLimitSwitch.get();
    // Boolean relay = Robot.m_ballMan.m_relay.get();

    if (Robot.m_oi.auxController.getYButton()){
      Robot.m_ballMan.m_bSpark.set(1);
    } else if (limitPressed == false) {
      Robot.m_ballMan.m_bTalon.set(0);
      Robot.m_ballMan.m_bSpark.set(0);
      Robot.m_ballMan.lastStop = System.currentTimeMillis();
      
      if(Robot.m_ballMan.relay_blinks < 8 && System.currentTimeMillis() - Robot.m_ballMan.lastBlink > 500) {
        Robot.m_ballMan.relay_state = !Robot.m_ballMan.relay_state;
        Robot.m_ballMan.m_relay.set(Robot.m_ballMan.relay_state);
        Robot.m_ballMan.relay_blinks++;
        Robot.m_ballMan.lastBlink = System.currentTimeMillis();
      } else if(Robot.m_ballMan.relay_blinks >= 8) {
        Robot.m_ballMan.m_relay.set(false);
        Robot.m_ballMan.relay_state = false;
      }
    } else if (Robot.m_oi.auxController.getXButton()  && System.currentTimeMillis() - Robot.m_ballMan.lastStop > 1000) {
      Robot.m_ballMan.m_bTalon.set(-1);
      Robot.m_ballMan.m_bSpark.set(.6);
        // Robot.m_ballMan.m_lit.set(true);
      }
      else {
        Robot.m_ballMan.m_bTalon.set(0);
        Robot.m_ballMan.m_bSpark.set(0);
        Robot.m_ballMan.m_relay.set(true);
        Robot.m_ballMan.relay_blinks = 0;
        Robot.m_ballMan.relay_state = (true);
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
