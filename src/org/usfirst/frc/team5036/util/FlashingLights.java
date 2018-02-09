package org.usfirst.frc.team5036.util;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FlashingLights {
	public static final int SOLID = 0;
	public static final int FLASHING = 1;
	Timer timer;
	int status;
	Solenoid light;
	public FlashingLights(int port){
		timer = new Timer();
		light = new Solenoid(port);
		turnLightOn();
		status = SOLID;
		timer.schedule(new FlashTask(),0L,500);
	}
	private class FlashTask extends TimerTask {
		@Override
		public void run() {
			if(status==SOLID){
				flashLight();
			}
		}
	}
	public void setStatus(int status){
		SmartDashboard.putNumber("Light Status:",status);
		this.status = status;
		if(status==SOLID){
			turnLightOn();
		}
	}
	public void turnLightOn(){
		light.set(true);
	}
	public void flashLight(){
		if(light.get()){
			light.set(false);
		}
		else{
			light.set(true);
		}
	}
}
