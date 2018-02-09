package org.usfirst.frc.team5036.subsystems;

import org.usfirst.frc.team5036.robot.RobotMap;

import com.ctre.CANTalon;

public class Climber {
	private static Climber instance;
	public static Climber getInstance(){
		if(instance==null){
			instance = new Climber();
		}
		return instance;
	}
	CANTalon climber1,climber2;
	public Climber(){
		climber1 = new CANTalon(RobotMap.CLIMBER_2);
		climber2 = new CANTalon(RobotMap.CLIMBER_1);
	}
	public void climb(double value){
		climber1.set(-value);
		climber2.set(value);
	}
}
