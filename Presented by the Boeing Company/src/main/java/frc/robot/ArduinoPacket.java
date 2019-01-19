package frc.robot;

enum DIRECTION {
    LEFT, RIGHT, FORWARDS, NULL;
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
            mDirection = DIRECTION.FORWARDS;
            break;
        default:
            mDirection = DIRECTION.NULL;
    }
}
}