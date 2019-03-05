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

  public CANPIDController m_econtroller_left = new CANPIDController(m_FLM);
  public CANEncoder m_encoder_left = new CANEncoder(m_FLM);
  public CANPIDController m_econtroller_right = new CANPIDController(m_FRM);
  public CANEncoder m_encoder_right = new CANEncoder(m_FRM);

  // TODO INIT MOTORS INVERTED PROPERLY

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void init(){
    m_FLM.setInverted(false);
    m_FRM.setInverted(true);
    m_FLM.setClosedLoopRampRate(0.7); // adjusting ramp rate
    m_RLM.follow(m_FLM, false);
    m_RRM.follow(m_FRM, false);
    m_RLM.setClosedLoopRampRate(0.8);

    m_econtroller_left.setOutputRange(-1.0, 1.0);
    m_econtroller_left.setFF(0.00015);
    m_econtroller_left.setP(0.00035);
    m_econtroller_left.setI(0);
    m_econtroller_left.setD(0);
    m_econtroller_left.setIZone(0);

    m_econtroller_right.setOutputRange(-1.0, 1.0);
    m_econtroller_right.setFF(0.00015);
    m_econtroller_right.setP(0.00035);
    m_econtroller_right.setI(0);
    m_econtroller_right.setD(0.00008);
    m_econtroller_right.setIZone(0);
 }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new SPARKDriveTeleop());
  }

}