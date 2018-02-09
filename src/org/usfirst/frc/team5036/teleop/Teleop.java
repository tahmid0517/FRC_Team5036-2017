package org.usfirst.frc.team5036.teleop;

import org.usfirst.frc.team5036.robot.RobotOutput;
import org.usfirst.frc.team5036.robot.Sensors;
import org.usfist.frc.team5036.auton.PIDDriveStraightTest;
import org.usfist.frc.team5036.auton.PIDRotateTest;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop extends RobotOutput {
	public static OperatorInterface oi;
	public static void init(){
		oi = OperatorInterface.getInstance();
		Sensors.getInstance().resetGyro();
		Sensors.getInstance().resetEncoders();
	}
	public static void run(){
		drivetrain();
		gearMechanism();
		Sensors.getInstance().updateSmartDashboard();
		climber();
		/*if(oi.driverController.getRawButton(1)){
			PIDDriveStraightTest.driveStraight(6620,-Sensors.getInstance().getGyro()*PIDRotateTest.kP);
		}
		//left 1
		if(oi.operatorController.getRawButton(1)){
			Drivetrain.getInstance().left1.set(0.4);
		}
		else {
			Drivetrain.getInstance().left1.set(0);
		}
		//left 2
		if(oi.operatorController.getRawButton(4)){
			Drivetrain.getInstance().left2.set(0.4);
		}
		else{
			Drivetrain.getInstance().left2.set(0);
		}
		//left 3
		if(oi.operatorController.getRawButton(3)){
			Drivetrain.getInstance().left3.set(0.4);
		}
		else{
			Drivetrain.getInstance().left3.set(0);
		}
		//right 1
		if(oi.operatorController.getRawButton(2)){
			Drivetrain.getInstance().right1.set(0.4);
		}
		else{
			Drivetrain.getInstance().right1.set(0);
		}
		//right 2
		if(oi.driverController.getRawButton(4)){
			Drivetrain.getInstance().right2.set(0.4);
		}
		else{
			Drivetrain.getInstance().right2.set(0);
		}
		//right 3
		if(oi.driverController.getRawButton(2)){
			Drivetrain.getInstance().right3.set(0.4);
		}
		else{
			Drivetrain.getInstance().right3.set(0);
		}*/
		SmartDashboard.putNumber("Left Encoder: ",Sensors.getInstance().getLeftEncoder());
		SmartDashboard.putNumber("Right Encoder:",Sensors.getInstance().getRightEncoder());
		SmartDashboard.putNumber("Encoder Average",Sensors.getInstance().getLeftEncoder() + Sensors.getInstance().getRightEncoder());
		//SmartDashboard.putNumber("PID P:",PIDDriveStraightTest.getPIDTuner());
	}
	public static void drivetrain(){
		double forward = oi.getDriveForward();
		double rotate = oi.getDriveRotate();
		drivetrain.arcadeDrive(forward,rotate);
	}
	public static void gearMechanism(){
		if(gearMechanism.hasGear()){
			gearMechanism.turnLightOn();
			SmartDashboard.putString("GEAR STATUS:","HAS GEAR");
		}
		else{
			gearMechanism.turnLightOff();
			SmartDashboard.putString("GEAR STATUS:","EMPTY");
		}
		if(oi.getFlashButon()){
			gearMechanism.setPhotonCannon(false);
		}
		else{
			gearMechanism.setPhotonCannon(true);
		}
		if(oi.getAutoFlapControlButton()){
			gearMechanism.setServoAngle(0);
		}
		else if(oi.getFlapsPullIn() || gearMechanism.hasGear()){
			gearMechanism.setServoAngle(1);
		}
		else{
			gearMechanism.setFlapsDisabled();
		}
	}
	public static void climber(){
		if(oi.getClimbFullPower() || oi.driverController.getRawButton(5)){
			climber.climb(-1);
		}
		else{
			climber.climb(oi.getClimbAxis());
		}
	}
}
