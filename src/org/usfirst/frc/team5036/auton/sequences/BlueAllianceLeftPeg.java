package org.usfirst.frc.team5036.auton.sequences;

import org.usfirst.frc.team5036.robot.RobotOutput;
import org.usfist.frc.team5036.auton.PIDDriveStraight;
import org.usfist.frc.team5036.auton.PIDDriveStraightTest;
import org.usfist.frc.team5036.auton.PIDRotateTest;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class BlueAllianceLeftPeg {
	public static final int DRIVE_DISTANCE_1 = 5140;
	public static final int ROTATE_ANGLE = 55;
	public static final int DRIVE_DISTANCE_2 = 6351;
	public static void execute(){
		PIDDriveStraightTest.driveStraight(DRIVE_DISTANCE_1,5);
		RobotOutput.drivetrain.stop();
		PIDRotateTest.rotate(ROTATE_ANGLE,1.25);
		PIDDriveStraightTest.driveStraight(DRIVE_DISTANCE_2,0);
		RobotOutput.drivetrain.stop();
	}
}
