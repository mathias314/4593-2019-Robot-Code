/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ManipulateBall;

/**
 * Add your docs here.
 */
public class BallManipulator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Spark m_bMotor = new Spark(RobotMap.ForkKnife.sparkBALL);

  public DigitalInput m_ballLimitSwitch = new DigitalInput(RobotMap.ForkKnife.ballLimitSwitch);

  public Solenoid m_lit = new Solenoid(RobotMap.ForkKnife.LED_PCM);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManipulateBall());
  }
}
