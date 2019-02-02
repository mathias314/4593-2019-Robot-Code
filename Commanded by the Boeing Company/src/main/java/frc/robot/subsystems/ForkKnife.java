/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.FkCommand;

/**
 * Add your docs here.
 */
public class ForkKnife extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark sparkOne = new Spark(RobotMap.ForkKnife.sparkOne);
  private Spark sparkTwo = new Spark(RobotMap.ForkKnife.sparkTwo);

  public SpeedControllerGroup fk = new SpeedControllerGroup(sparkOne, sparkTwo);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new FkCommand());
  }
}
