/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.SPARKDriveTeleop;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class SPARKDrive extends Subsystem {
  private CANSparkMax m_FLM = new CANSparkMax(RobotMap.Drive.FLM, MotorType.kBrushless);
  private CANSparkMax m_FRM = new CANSparkMax(RobotMap.Drive.FRM, MotorType.kBrushless);
  private CANSparkMax m_RLM = new CANSparkMax(RobotMap.Drive.RLM, MotorType.kBrushless);
  private CANSparkMax m_RRM = new CANSparkMax(RobotMap.Drive.RRM, MotorType.kBrushless);

  private SpeedControllerGroup m_left = new SpeedControllerGroup(m_FLM, m_RLM);
  private SpeedControllerGroup m_right = new SpeedControllerGroup(m_FRM, m_RRM);
  public DifferentialDrive m_DRIVE = new DifferentialDrive(m_left, m_right);
  public CANEncoder m_encoder_left = new CANEncoder(m_FLM);
  public CANEncoder m_encoder_right = new CANEncoder(m_FRM);

  // TODO INIT MOTORS INVERTED PROPERLY

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void init(){
    m_FLM.setInverted(true);
    m_RLM.setInverted(true);
    m_FRM.setInverted(true);
    m_RRM.setInverted(true);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new SPARKDriveTeleop());
  }

}
