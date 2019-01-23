//
// begin license header
//
// This file is part of Pixy CMUcam5 or "Pixy" for short
//
// All Pixy source code is provided under the terms of the
// GNU General Public License v2 (http://www.gnu.org/licenses/gpl-2.0.html).
// Those wishing to use Pixy source code, software and/or
// technologies under different licensing terms should contact us at
// cmucam@cs.cmu.edu. Such licensing terms are available for
// all portions of the Pixy codebase presented here.
//
// end license header
//
// This sketch is a good place to start if you're just getting started with 
// Pixy and Arduino.  This program simply prints the detected object blocks 
// (including color codes) through the serial console.  It uses the Arduino's 
// ICSP port.  For more information go here:
//
// http://cmucam.org/projects/cmucam5/wiki/Hooking_up_Pixy_to_a_Microcontroller_(like_an_Arduino)
//
// It prints the detected blocks once per second because printing all of the 
// blocks for all 50 frames per second would overwhelm the Arduino's serial port.
//
   
#include <SPI.h>  
#include <Pixy.h>

// This is the main Pixy object 
Pixy pixy;

void setup()
{
  Serial.begin(9600);
  Serial.print("Starting...\n");

  pixy.init();
}

double theta(int x, int y)
{
  return atan(((double) y)/x - (70-y)*0-.1) * 180 / 3.14159;
}

double heuristic(int x, int y, double theta)
{
  return theta/90*((double) 2/320)*x*100 + (90.0-theta)/90*((double) 2/200)*y*100;
}

void loop()
{ 
  static int i = 0;
  int j;
  uint16_t blocks;
  char buf[32]; 
  
  // grab blocks!
  blocks = pixy.getBlocks();
  
  // If there are detect blocks, print them!
  if (blocks)
  {
    i++;
    
    // do this (print) every 50 frames because printing every
    // frame would bog down the Arduino
    if (i%50==0)
    {
      for (j=0; j<1; j++)
      {
        double th = theta(pixy.blocks[j].width, pixy.blocks[j].height);
        double h = heuristic(pixy.blocks[j].x, pixy.blocks[j].y, th);
        if(h > 120){
          Serial.print(1); //move left forwards
        } else if (h < 110){
          Serial.print(2); //move right forwards
        } else {
          if(th > 45){
            Serial.print(3); // right forward, left back
          } else if (th < 35){
            Serial.print(4); // left forward, right back;
          } else {
            Serial.print(5); // forwards
          }
        }
      }
    }
  }  
}
