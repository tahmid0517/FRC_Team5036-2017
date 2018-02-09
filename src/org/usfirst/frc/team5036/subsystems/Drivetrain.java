package org.usfirst.frc.team5036.subsystems;


import org.usfirst.frc.team5036.robot.RobotMap;
import org.usfirst.frc.team5036.robot.Sensors;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain {
	public static final double MAX_ANGLE = 90;
	public Victor left1,left2,left3,right1,right2,right3;
	public static Drivetrain instance;
	public Drivetrain(){
		left1 = new Victor(RobotMap.DRIVETRAIN_LEFT_CIM_1);
		left2 = new Victor(RobotMap.DRIVETRAIN_LEFT_CIM_2);
		left3 = new Victor(RobotMap.DRIVETRAIN_LEFT_CIM_3);
		right1 = new Victor(RobotMap.DRIVETRAIN_RIGHT_CIM_1);
		right2 = new Victor(RobotMap.DRIVETRAIN_RIGHT_CIM_2);
		right3 = new Victor(RobotMap.DRIVETRAIN_RIGHT_CIM_3);
	}
	public static Drivetrain getInstance(){
		if(instance==null){
			instance = new Drivetrain();
		}		return instance;
	}
	public void driveLeftSide(double power){
		power = capValue(power);
		left1.set(power);
		left2.set(power);
		left3.set(power);
	}
	public void driveRightSide(double power){
		power = capValue(power);
		right1.set(-power);
		right2.set(-power);
		right3.set(-power);
	}
	public double capValue(double val)
	{
		val = Math.max(val,-1);
		val = Math.min(val,1);
		return val;
	}
	public void arcadeDrive(double forward,double rotation){
		double scale = Math.max(1,Math.abs(forward)+Math.abs(rotation));
		double leftSidePower = (forward+rotation)/scale;
		double rightSidePower = (forward-rotation)/scale;
		driveLeftSide(leftSidePower);
		driveRightSide(rightSidePower); 
	}
	public void adjustedArcadeDrive(double forward,double rotation){
		double scale = Math.max(1,Math.abs(forward)+Math.abs(rotation));
		double motorScale = 12/Sensors.getInstance().getVoltage();
		double leftSidePower = (forward+rotation)*motorScale/scale;
		double rightSidePower = (forward-rotation)*motorScale/scale;
		driveLeftSide(leftSidePower);
		driveRightSide(rightSidePower);
	}
	public void stop(){
		arcadeDrive(0,0);
	}
	public void brake(){
		driveLeftSide(-left1.get());
		driveRightSide(-right1.get());
		Timer.delay(0.01);
		stop();
	}
	public void rotate(double power){
		arcadeDrive(0,power);
	}
}
