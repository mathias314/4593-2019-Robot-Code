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

  private CANSparkMax m_motor;
  private XboxController m_stick1;

  @Override
  public void robotInit() {
    m_motor = new CANSparkMax(3, MotorType.kBrushless);
    m_stick1 = new XboxController(kJoystickPort);
  }

  @Override
  public void teleopPeriodic() {
   double one = Math.abs(0.75 * m_stick1.getRawAxis(1)) > 0.15 ? 0.5 * m_stick1.getRawAxis(1) : 0;
   int boost = m_stick1.getBButton() ? 1 : 1;
   CANEncoder encoder = new CANEncoder(m_motor);
   System.out.println(encoder.getPosition());
    m_motor.set(one*(boost)); 
  }
}
