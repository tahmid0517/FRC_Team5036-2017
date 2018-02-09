package org.usfirst.frc.team5036.auton.sequences;

import org.usfirst.frc.team5036.robot.RobotOutput;
import org.usfist.frc.team5036.auton.PIDDriveStraight;
import org.usfist.frc.team5036.auton.PIDRotate;
import org.usfist.frc.team5036.auton.PIDRotateTest;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class LeftSidePegGearScore {
	public static void execute(){
		//PIDDriveStraight.driveStraight(5662,1);
		//RobotOutput.drivetrain.stop();
		//for(int i = 0;i<12 && DriverStation.getInstance().isAutonomous();i++){
			//Timer.delay(0.25);
		//}
		PIDRotateTest.rotate(60,0);
	}
}
