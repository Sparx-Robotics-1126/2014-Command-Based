package org.usfirst.frc.team1126.robot.utilities;

import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class PotentiometerData {

	private Potentiometer pot;
	private double inchesPerVolt;
	private double zeroPointVolts;
	
    public PotentiometerData(Potentiometer pot, double inchesPerVolt){
        this.pot = pot;
        this.inchesPerVolt = inchesPerVolt;
        this.reset();
    }
	
    public void reset(){
        zeroPointVolts = pot.get();
    }
    
    public double getInches(){
        return ((pot.get() - zeroPointVolts) * inchesPerVolt);
    }
    
}
