package org.usfirst.frc.team5036.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Sensors {
	public static Sensors instance;
	PowerDistributionPanel pdp;
	Encoder leftEncoder;
	Encoder rightEncoder;
	ADXRS450_Gyro gyro;
	public Sensors(){
		pdp = new PowerDistributionPanel();
		leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_INPUT,RobotMap.LEFT_ENCODER_OUTPUT,false,Encoder.EncodingType.k4X);
		rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_INPUT,RobotMap.RIGHT_ENCODER_OUTPUT,false,Encoder.EncodingType.k4X);
		gyro = new ADXRS450_Gyro();
	}
	public static Sensors getInstance(){
		if(instance==null){
			instance = new Sensors();
		}
		return instance;
	}
	public double getVoltage(){
		//return pdp.getVoltage();
		return 0;
	}
	public void resetGyro(){
		gyro.reset();
	}
	public void resetEncoders(){
		leftEncoder.reset();
		rightEncoder.reset();
	}
	public double getGyro(){
		return gyro.getAngle();
	}
	public double getLeftEncoder(){
		return leftEncoder.getRaw();
	}
	public double getRightEncoder(){
		return rightEncoder.getRaw();
	}
	public double getEncoderAverage(){
		return getLeftEncoder();
	}
	public void updateSmartDashboard(){
		SmartDashboard.putNumber("Battery Voltage:",getVoltage());
		SmartDashboard.putNumber("Left Encoder:",getLeftEncoder());
		SmartDashboard.putNumber("RightEncoder:",getRightEncoder());
		SmartDashboard.putNumber("Gyro:",getGyro());
	}
}
