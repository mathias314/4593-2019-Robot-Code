/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PWMTalonSRX;
// import edu.wpi.first.wpilibj.Relay;
// import edu.wpi.first.wpilibj.Servo;
// import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
// import edu.wpi.first.wpilibj.Talon;
// import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ManipulateBall;

/**
 * Add your docs here.
 */
public class BallManipulator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Spark m_bSpark = new Spark(RobotMap.ForkKnife.sparkBALL);
  private PWMTalonSRX m_bTalonLeft = new PWMTalonSRX(RobotMap.ForkKnife.talonBallLeft);
  private PWMTalonSRX m_bTalonRight = new PWMTalonSRX(RobotMap.ForkKnife.talonBallRight);
  public SpeedControllerGroup m_bTalon = new SpeedControllerGroup(m_bTalonLeft, m_bTalonRight);

  public DigitalInput m_ballLimitSwitch = new DigitalInput(RobotMap.ForkKnife.ballLimitSwitch);
  // public DigitalOutput m_relay = new DigitalOutput(RobotMap.ForkKnife.relay);

  // public int relay_blinks;
  // public boolean relay_state;
  
  public double lastStop = System.currentTimeMillis();
  // public double lastBlink = System.currentTimeMillis();


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManipulateBall());
  }
}
