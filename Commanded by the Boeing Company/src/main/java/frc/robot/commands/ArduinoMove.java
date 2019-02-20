package frc.robot.commands;

import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;
import frc.robot.subsystems.*;

/**
 * An example command.  You can replace me with your own command.
 */
public class ArduinoMove extends Command {
  public ArduinoMove() {
    // Use requires() here to declare subsystem dependencies

    // requires(Robot.m_Arduino);
    // requires(Robot.m_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      System.out.println("Switching to data mode...");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    ArduinoPacket packet = Robot.m_Arduino.readPacket();
    try {
      System.out.println(packet.mDirection);
    switch(packet.mDirection){
      case LEFT:
        Robot.m_subsystem.m_FLM.set(0.2);
        Robot.m_subsystem.m_FRM.set(0);
        break;
      case RIGHT:
        Robot.m_subsystem.m_FLM.set(0);
        Robot.m_subsystem.m_FRM.set(0.2);
        break;
      case TURNL:
        Robot.m_subsystem.m_FLM.set(-0.2);
        Robot.m_subsystem.m_FRM.set(0.2);
        break;
      case TURNR:
        Robot.m_subsystem.m_FLM.set(0.2);
        Robot.m_subsystem.m_FRM.set(-0.2);
        break;
      case FORWARDS:
        Robot.m_subsystem.m_FLM.set(0);
        Robot.m_subsystem.m_FRM.set(0);
        new SPARKDriveTeleop().start();
        break;
      default:
        // new SPARKDriveTeleop().start();
    }
  } catch (NullPointerException e) {
      // new SPARKDriveTeleop().start();
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


// if 69, sex;
