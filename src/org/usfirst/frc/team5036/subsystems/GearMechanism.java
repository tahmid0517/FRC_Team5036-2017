package org.usfirst.frc.team5036.subsystems;

import org.usfirst.frc.team5036.robot.RobotMap;
import org.usfirst.frc.team5036.util.FlashingLights;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearMechanism {
	private static GearMechanism instance;
	Solenoid gearLight1,gearLight2;
	public CANTalon photonCannon;
	public DigitalInput btn1,btn2;
	public Servo flap1,flap2;
	public static GearMechanism getInstance(){
		if(instance==null){
			instance = new GearMechanism();
		}
		return instance;
	}
	public GearMechanism(){
		gearLight1 = new Solenoid(RobotMap.GEAR_SIGNAL_1);
		gearLight2 = new Solenoid(RobotMap.GEAR_SIGNAL_2);
		photonCannon = new CANTalon(RobotMap.PHOTON_CANNON);
		setPhotonCannon(true);
		btn1 = new DigitalInput(RobotMap.GEAR_MECHANISM_BUTTON_1);
		btn2 = new DigitalInput(RobotMap.GEAR_MECHANISM_BUTTON_2);
		flap1 = new Servo(RobotMap.FLAP_SERVO_1);
		flap2 = new Servo(RobotMap.FLAP_SERVO_2);
	}
	public void turnLightOn(){
		gearLight1.set(true);
		gearLight2.set(true);
	}
	public void turnLightOff(){
		gearLight1.set(false);
		gearLight2.set(false);
	}
	public boolean hasGear(){
		return !btn1.get() || !btn2.get();
	}
	public void setPhotonCannon(boolean status){
		if(status){
			photonCannon.set(1);
		}
		else{
			photonCannon.set(0);
		}
	}
	public void setServoAngle(double value){
		double angle = value;
		flap1.set(1-angle);
		flap2.set(angle);
	}
	public void setFlapsDisabled(){
		flap1.setDisabled();
		flap2.setDisabled();
	}
	public void setAbsoluteServoAngle(double angle){
		flap1.set(1-angle);
		flap2.set(angle);
	}
	public void setAngleInDegrees(double angle){
		flap2.set(angle);
	}
}
