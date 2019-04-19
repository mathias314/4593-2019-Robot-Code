
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*
here is a motivational story about a llama who is a professional golfer.
hans was a sad llama as a child, and he always tended to dream big. One day, his dreams became reality.
it was a beautiful summer day, the sun was high in the sky, and there were no clouds.
he walked out of his abode that morning, and understood what had to be done.
he reentered his home, picked up his driver, downed a bottle of jack, and made his way triumphantly out of his home.
it was a short walk to Small Pines putt-putt course, and only took him about 15 minutes to gallop to.
he enjoyed the exercise. After all, he didn't often get out of the house.
as he stepped through the front door to purchase his ticket, he was given an odd stare by the man at the counter.
"you cant use a driver on a putt putt course, you hecking idiot," he said.
Hans stared the man down with his steely llama gaze and retorted: "..."
Hans didn't say anything because he's a heccing llama, and llama's can't talk.
*/


package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.SAVEUSDEARGOD;
// import frc.robot.commands.MakeGrip;
import frc.robot.commands.SPARKDriveTeleop;
// import frc.robot.subsystems.Arduino;
import frc.robot.subsystems.BallManipulator;
import frc.robot.subsystems.DearGodPlsWork;
import frc.robot.subsystems.ForkKnife;
import frc.robot.subsystems.Gripper;
import frc.robot.subsystems.Nomatics;
import frc.robot.subsystems.SPARKDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static SPARKDrive m_subsystem = new SPARKDrive();
  public static ForkKnife m_fk = new ForkKnife();
  public static Nomatics m_nomat = new Nomatics();
  public static BallManipulator m_ballMan = new BallManipulator();
  public static DearGodPlsWork m_ohgod = new DearGodPlsWork();
  // public static Arduino m_Arduino = new Arduino();
  public static Gripper m_gripper = new Gripper();
  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("Teleop", new SPARKDriveTeleop());
    m_chooser.addOption("Flavor Explosion 1", new SAVEUSDEARGOD());
    SmartDashboard.putData("Auto mode", m_chooser);
    CameraServer.getInstance().startAutomaticCapture();
    m_subsystem.init();
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
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    Robot.m_gripper.GripperSolenoid.set(Value.kReverse);

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
