/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;
import frc.robot.commands.MakeGrip;


/**
 * Add your docs here.
 */
public class Gripper extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DoubleSolenoid GripperSolenoid = new DoubleSolenoid(RobotMap.ForkKnife.gripperSolenoidSideA, RobotMap.ForkKnife.gripperSolenoidSideB);

  // public DoubleSolenoid DeploySolenoid = new DoubleSolenoid(RobotMap.ForkKnife.deployerSolenoidSideA, RobotMap.ForkKnife.deployerSolenoidSideB);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

    setDefaultCommand(new MakeGrip());

  }
}
