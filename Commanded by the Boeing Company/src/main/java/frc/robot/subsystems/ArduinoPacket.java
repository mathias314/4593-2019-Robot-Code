package frc.robot.subsystems;

enum DIRECTION {
    LEFT, RIGHT, FORWARDS, TURNL, TURNR, NULL;
}

public class ArduinoPacket {
public DIRECTION mDirection;

public ArduinoPacket(int choice){
    switch(choice){
        case 49:
            mDirection = DIRECTION.LEFT;
            break;
        case 50:
            mDirection = DIRECTION.RIGHT;
            break;
        case 51:
            mDirection = DIRECTION.TURNL;
            break;
        case 52: 
            mDirection = DIRECTION.TURNR;
            break;
        case 53:
            mDirection = DIRECTION.FORWARDS;
            break;
        default:
            mDirection = DIRECTION.NULL;
    }
}
}