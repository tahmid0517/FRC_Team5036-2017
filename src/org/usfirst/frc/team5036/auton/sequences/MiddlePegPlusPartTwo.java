package org.usfirst.frc.team5036.auton.sequences;
import org.usfirst.frc.team5036.subsystems.Drivetrain;
import org.usfirst.frc.team5036.subsystems.GearMechanism;
import org.usfist.frc.team5036.auton.PIDDriveStraightTest;
import org.usfist.frc.team5036.auton.PIDRotateTest;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class MiddlePegPlusPartTwo {
	static final double RED = 0;
	static final double BLUE = 1;
	static final double DRIVE_FORWARD = 7270;
	static final double DRIVE_BACK = -3500;
	static final double ROTATE = 90;
	static final double DRIVE_OUT = 8500;
	static final double DRIVE_UP = 31000;
	public static void execute(double colour){
		System.out.println("driving forward");
		PIDDriveStraightTest.driveStraightTimeCutLoop(DRIVE_FORWARD);
		System.out.println("waiting for gear to get taken out");
		while(GearMechanism.getInstance().hasGear() && DriverStation.getInstance().isAutonomous()){
			Drivetrain.getInstance().stop();
		}
		for(int i = 0;i<16 && DriverStation.getInstance().isAutonomous();i++){
			Timer.delay(0.125);
		}
		System.out.println("driving back");
		PIDDriveStraightTest.driveStraight(DRIVE_BACK,500);
		Drivetrain.getInstance().stop();
		System.out.println("first rotation");
		if(colour==RED){
			PIDRotateTest.rotate(-ROTATE,3);
		}
		else{
			PIDRotateTest.rotate(ROTATE,3);
		}
		Drivetrain.getInstance().stop();
		System.out.println("drive out");
		PIDDriveStraightTest.driveStraight(DRIVE_OUT,500);
		Drivetrain.getInstance().stop();
		System.out.println("rotate");
		if(colour==RED){
			PIDRotateTest.rotate(ROTATE,2);
			System.out.println("red");
		}
		else{
			PIDRotateTest.rotate(-ROTATE,2);
		}
		Drivetrain.getInstance().stop();
		System.out.println("drive up");
		PIDDriveStraightTest.driveStraight(DRIVE_UP,10);
		Drivetrain.getInstance().stop();
	}
}
