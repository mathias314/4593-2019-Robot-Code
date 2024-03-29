/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;
import java.lang.Math.*;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private NetworkTableEntry distanceEntry;


  // these talon bois are havin a rough go at it
  // take note that talons 3 and 4 are currently inverted to their actual positions on the bot
  // troubleshooting with talons
  private final TalonSRX m_FLM = new TalonSRX(3);
  private final TalonSRX m_FRM = new TalonSRX(7);
  private final TalonSRX m_RLM = new TalonSRX(2);
  private final TalonSRX m_RRM = new TalonSRX(4);
  private final XboxController m_stick1 = new XboxController(0);

  private final Arduino m_Arduino = new Arduino();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("cameratable");
    distanceEntry = table.getEntry("distance");
    m_FRM.setInverted(true);
    m_RRM.setInverted(true);
    m_FLM.setInverted(false);
    m_RLM.setInverted(false);

    m_FRM.config_kF(0, 0.6);
    m_RRM.config_kF(0, 0.6);
    m_FLM.config_kF(0, 0.6);
    m_RLM.config_kF(0, 0.6);

    m_FRM.config_kP(0, 0.12);
    m_RRM.config_kP(0, 0.12);
    m_FLM.config_kP(0, 0.12);
    m_RLM.config_kP(0, 0.12);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // double distance = distanceEntry.getDouble(0.0);
    //SmartDashboard.putNumber("distance", distance);

    double five = Math.abs(0.75 * m_stick1.getRawAxis(1)) > 0.15 ? 0.75 * m_stick1.getRawAxis(1) : 0; // left forwards allegedly
    double one = Math.abs(0.75 * m_stick1.getRawAxis(5)) > 0.15 ? 0.75 * m_stick1.getRawAxis(5) : 0; // right forwards
  
    double zero = 0.0;
    m_FLM.set(ControlMode.Current, one + zero);
    m_RLM.set(ControlMode.Current, one - zero);

    m_FRM.set(ControlMode.Current, five + zero);
    m_RRM.set(ControlMode.Current, five - zero);

    if(m_stick1.getAButton()){
      moveyBoy();
    }
  }

  public void moveyBoy(){
    
    // arduino code makes the robot quit as well for some unknown reason

    ArduinoPacket packet = m_Arduino.readPacket();
    System.out.println(packet.mDirection);

    /**
    switch(direction){
      case LEFT:
        m_FLM.set(ControlMode.Current, 0.4);
        m_RLM.set(ControlMode.Current, 0.4);
        break;
      case RIGHT:
        m_FRM.set(ControlMode.Current, 0.4);
        m_RRM.set(ControlMode.Current, 0.4);
        break;
      case TURNL:
        m_FLM.set(ControlMode.Current, -0.4);
        m_RLM.set(ControlMode.Current, -0.4);
        m_FRM.set(ControlMode.Current, 0.4);
        m_RRM.set(ControlMode.Current, 0.4);
        break;
      case TURNR:
        m_FLM.set(ControlMode.Current, 0.4);
        m_RLM.set(ControlMode.Current, 0.4);
        m_FRM.set(ControlMode.Current, -0.4);
        m_RRM.set(ControlMode.Current, -0.4);
        break;
      case FORWARDS:
        m_FLM.set(ControlMode.Current, 0.4);
        m_RLM.set(ControlMode.Current, 0.4);
        m_FRM.set(ControlMode.Current, 0.4);
        m_RRM.set(ControlMode.Current, 0.4);
        break;
    }
    */
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
