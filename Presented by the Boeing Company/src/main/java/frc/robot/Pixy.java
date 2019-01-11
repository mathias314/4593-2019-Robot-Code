package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
//Warning: if the pixy is plugged in through mini usb, this code WILL NOT WORK b/c the pixy is smart and detects where it should send data
public class Pixy {
SerialPort pixy;
Port port = Port.kMXP;
PixyPacket[] packets;
PixyException pExc;
String print;

public Pixy() {
pixy = new SerialPort(19200, port);
pixy.setReadBufferSize(14);
packets = new PixyPacket[7];
pExc = new PixyException(print);
}
//This method parses raw data from the pixy into readable integers
public int cvt(byte upper, byte lower) {
return (((int)upper & 0xff) << 8) | ((int)lower & 0xff);
}

public void pixyReset(){
pixy.reset();
}

//This method gathers data, then parses that data, and assigns the ints to global variables
public PixyPacket readPacket(int Signature) throws PixyException {
int Checksum;
int Sig;
byte[] rawData = new byte[32];
try{
rawData = pixy.read(32);
} catch (RuntimeException e){
}
if(rawData.length < 32){
System.out.println("byte array length is broken");
return null;
}
int n = 0;
for (int i = 0; i <= 16; i++) {
int syncWord = cvt(rawData[n], rawData[n + 1]); //Parse first 2 bytes
n += 2;
if (syncWord == 0xaa55) { //Check is first 2 bytes equal a "sync word", which indicates the start of a packet of valid data
syncWord = cvt(rawData[n], rawData[n + 1]); //Parse the next 2 bytes
n += 2;
if (syncWord != 0xaa55){ //Shifts everything in the case that one syncword is sent
i -= 2;
}
//This next block parses the rest of the data
Checksum = cvt(rawData[n], rawData[n+1]);
Sig = cvt(rawData[n+2], rawData[n+3]);
n += 4;
if(Sig <= 0 || Sig > packets.length){
break;
}
packets[Sig - 1] = new PixyPacket();
packets[Sig - 1].X = cvt(rawData[n], rawData[n+1]);
packets[Sig - 1].Y = cvt(rawData[n+2], rawData[n+3]);
packets[Sig - 1].Width = cvt(rawData[n+4], rawData[n+5]);
packets[Sig - 1].Height = cvt(rawData[n+6], rawData[n+7]);
//Checks whether the data is valid using the checksum *This if block should never be entered*
if (Checksum != Sig + packets[Sig - 1].X + packets[Sig - 1].Y + packets[Sig - 1].Width + packets[Sig - 1].Height) {
packets[Sig - 1] = null;
throw pExc;
}
break;
}
}
//Assigns our packet to a temp packet, then deletes data so that we dont return old data
PixyPacket pkt = packets[Signature - 1];
packets[Signature - 1] = null;
return pkt;
}
} 