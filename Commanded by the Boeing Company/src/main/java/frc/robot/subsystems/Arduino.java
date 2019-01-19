package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

//Warning: if the pixy is plugged in through mini usb, this code WILL NOT WORK b/c the pixy is smart and detects where it should send data
public class Arduino extends Subsystem{
SerialPort pixy;
Port port = Port.kUSB;
String print;

//This method parses raw data from the pixy into readable integers
public int cvt(byte upper, byte lower) {
return (((int)upper & 0xff) << 8) | ((int)lower & 0xff);
}

public void pixyReset(){
pixy.reset();
}

//This method gathers data, then parses that data, and assigns the ints to global variables
public ArduinoPacket readPacket(){
byte[] rawData = new byte[1];
try{
rawData = pixy.read(1);
} catch (RuntimeException e){
}
if(rawData.length < 1){
System.out.println("byte array length is broken");
return null;
}
System.out.println(rawData[0]);
return new ArduinoPacket(rawData[0]);
}

@Override
protected void initDefaultCommand() {
    pixy = new SerialPort(19200, port);
    pixy.setReadBufferSize(1);
}
} 