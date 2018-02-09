package org.usfirst.frc.team5036.auton.sequences;

import org.usfirst.frc.team5036.robot.RobotOutput;
import org.usfist.frc.team5036.auton.PIDDriveStraight;
import org.usfist.frc.team5036.auton.PIDDriveStraightTest;
import org.usfist.frc.team5036.auton.PIDRotateTest;

public class RedAllianceRightPeg {
	public static final int DRIVE_DISTANCE_1 = 5140;
	public static final int ROTATE_ANGLE = -55;
	public static final int DRIVE_DISTANCE_2 = 6351;
	public static void execute(){
		PIDDriveStraightTest.driveStraight(DRIVE_DISTANCE_1,5);
		RobotOutput.drivetrain.stop();
		PIDRotateTest.rotate(ROTATE_ANGLE,1.25);
		PIDDriveStraightTest.driveStraight(DRIVE_DISTANCE_2,5);
		RobotOutput.drivetrain.stop();
	}
}
