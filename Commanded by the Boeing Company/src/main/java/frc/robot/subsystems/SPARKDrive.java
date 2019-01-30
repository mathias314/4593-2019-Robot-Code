/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
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
  public CANSparkMax m_FLM = new CANSparkMax(RobotMap.Drive.FLM, MotorType.kBrushless);
  public CANSparkMax m_FRM = new CANSparkMax(RobotMap.Drive.FRM, MotorType.kBrushless);
  private CANSparkMax m_RLM = new CANSparkMax(RobotMap.Drive.RLM, MotorType.kBrushless);
  private CANSparkMax m_RRM = new CANSparkMax(RobotMap.Drive.RRM, MotorType.kBrushless);

  public DifferentialDrive m_DRIVE = new DifferentialDrive(m_FLM, m_FRM);
  public CANPIDController m_econtroller_left = new CANPIDController(m_FLM);
  public CANEncoder m_encoder_left = new CANEncoder(m_FLM);
  public CANPIDController m_econtroller_right = new CANPIDController(m_FRM);
  public CANEncoder m_encoder_right = new CANEncoder(m_FRM);

  // TODO INIT MOTORS INVERTED PROPERLY

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void init(){
    m_FLM.setInverted(true);
    m_FRM.setInverted(false);
    m_RLM.follow(m_FLM, false);
    m_RRM.follow(m_FRM, false);

    m_FLM.setRampRate(0.2);
    m_FRM.setRampRate(0.2);

    m_econtroller_left.setOutputRange(-1.0, 1.0);
    m_econtroller_left.setFF(0.4);
    m_econtroller_left.setD(0.1);
    m_econtroller_right.setOutputRange(-1.0, 1.0);
    m_econtroller_right.setFF(0.0);
    m_econtroller_right.setD(0.1);
    m_econtroller_left.setReference(0, ControlType.kVelocity);
    m_econtroller_right.setReference(0, ControlType.kVelocity);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new SPARKDriveTeleop());
  }

}
