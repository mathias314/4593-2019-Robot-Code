/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

/**
 * This sample program shows how to control a motor using a joystick. In the
 * operator control part of the program, the joystick is read and the value is
 * written to the motor.
 *
 * <p>Joystick analog values range from -1 to 1 and speed controller inputs also
 * range from -1 to 1 making it easy to work together.
 */
public class Robot extends TimedRobot {
  private static final int kMotorPort = 0;
  private static final int kJoystickPort = 0;

  // private CANSparkMax m_motor;
  private CANSparkMax m_FLM;
  private CANSparkMax m_FRM;
  private CANSparkMax m_RRM;
  private CANSparkMax m_RLM;
  private XboxController m_stick1;
  
  private DifferentialDrive m_myRobot;

  @Override
  public void robotInit() {
    // m_motor = new CANSparkMax(3, MotorType.kBrushless);
    final CANSparkMax m_FLM = new CANSparkMax(RobotMap.Drive.FLM, MotorType.kBrushless);
    final CANSparkMax m_FRM = new CANSparkMax(RobotMap.Drive.FRM, MotorType.kBrushless);
    final CANSparkMax m_RLM = new CANSparkMax(RobotMap.Drive.RLM, MotorType.kBrushless);
    final CANSparkMax m_RRM = new CANSparkMax(RobotMap.Drive.RRM, MotorType.kBrushless);
  
    m_FLM = new CANSparkMax(1, MotorType.kBrushless);
    m_RLM = new CANSparkMax(2, MotorType.kBrushless);
    m_FRM = new CANSparkMax(3, MotorType.kBrushless);
    m_RRM = new CANSparkMax(4, MotorType.kBrushless);


    private SpeedControllerGroup m_left = new SpeedControllerGroup(m_FLM, m_RLM);
    private SpeedControllerGroup m_right = new SpeedControllerGroup(m_FRM, m_RRM);
    final DifferentialDrive m_DRIVE = new DifferentialDrive(m_left, m_right);
    public CANEncoder m_encoder_left = new CANEncoder(m_FLM);
    public CANEncoder m_encoder_right = new CANEncoder(m_FRM);

    m_stick1 = new XboxController(kJoystickPort);
  }

  @Override
  public void teleopPeriodic() {
   double one = Math.abs(0.75 * m_stick1.getRawAxis(1)) > 0.15 ? 0.5 * m_stick1.getRawAxis(1) : 0;
   int boost = m_stick1.getBButton() ? 1 : 1;
   // CANEncoder encoder = new CANEncoder(m_motor);
   // System.out.println(encoder.getPosition());
   // m_motor.set(one*(boost)); 
  }
}
