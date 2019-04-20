package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class DoubleSingle extends Solenoid{

    DoubleSolenoid ds;

    DoubleSingle(int a, int b){
        super(4);
        this.ds = new DoubleSolenoid(a, b);
    }

    @Override
    public void set(boolean on) {
        if(on){
        ds.set(Value.kForward);
        } else {
        ds.set(Value.kReverse);
        }
    }
}