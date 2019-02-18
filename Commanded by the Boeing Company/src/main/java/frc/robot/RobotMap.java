/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  public static class Drive {
    public static final int FLM = 1; 
    public static final int FRM = 4;
    public static final int RLM = 2;
    public static final int RRM = 3; 
  }

  public static class ForkKnife {
    public static final int sparkOne = 1;
    public static final int sparkTwo = 2;
    public static final int sparkBALL = 0;
    public static final int solenoidSideA = 0;
    public static final int solenoidSideB = 1;
    public static final int LED_PCM = 5;
    public static final int ballLimitSwitch = 0;
    public static final int SERVO = 4; 
 
    public static final int gripperSolenoidSideA = 6;
    public static final int gripperSolenoidSideB = 7;

    // figure out where these bad bois are actually plugged into
    // public static final int deployerSolenoidSideA = 2;
    // public static final int deployerSolenoidSid  eB = 3;
  }

  public static class Controls{
    public static final int ControllerOne = 0;
    public static final int auxController = 1;
    public static final int StartButton = 8;
    public static final int BackButton = 7;
    public static final int AButton = 0;
    public static final int BButton = 1;
  }
}
