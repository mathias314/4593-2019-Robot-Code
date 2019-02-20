/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.ForkKnife;
import frc.robot.subsystems.Gripper;

public class MakeGrip extends Command {
  public MakeGrip() {
    requires(Robot.m_gripper);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_gripper.GripperSolenoid.set(DoubleSolenoid.Value.kOff);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    // make control scheme however it may be
    // controls as of now are on the driver controller
    // this is subject to change, and most likely should be changed eventually
    // we need to figure out complete control mapping at some point, so we don't have to deal with crappy control schemes like this... :(

    
    if (Robot.m_oi.xboxController.getAButtonPressed()) {
      Robot.m_gripper.GripperSolenoid.set(DoubleSolenoid.Value.kForward);
    } else if (Robot.m_oi.xboxController.getBButtonPressed()) {
      Robot.m_gripper.GripperSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
  
    // write up the controls for the hatch panel manipulator deploy, however and wherever we may wish to do so...
    if (Robot.m_oi.auxController.getBumper(Hand.kRight)) {
      Robot.m_gripper.DeployerSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    else if (Robot.m_oi.auxController.getBumper(Hand.kLeft)) {
      Robot.m_gripper.DeployerSolenoid.set(DoubleSolenoid.Value.kReverse);
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
