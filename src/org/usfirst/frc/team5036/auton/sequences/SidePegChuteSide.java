package org.usfirst.frc.team5036.auton.sequences;

import org.usfirst.frc.team5036.subsystems.Drivetrain;
import org.usfist.frc.team5036.auton.PIDDriveStraightTest;
import org.usfist.frc.team5036.auton.PIDRotateTest;

public class SidePegChuteSide {
	public static final int RED = 0;
	public static final int BLUE = 1;
	public static final int FIRST_DRIVE_DISTANCE = 6520;
	public static final int ROTATE_ANGLE= 60;
	public static final int SECOND_DRIVE_DISTANCE = 5820;
	public static void execute(int alliance){
		if(alliance==RED){
			PIDDriveStraightTest.driveStraight(FIRST_DRIVE_DISTANCE,10);
			Drivetrain.getInstance().stop();
			PIDRotateTest.rotate(ROTATE_ANGLE,2);
			Drivetrain.getInstance().stop();
			PIDDriveStraightTest.driveStraight(SECOND_DRIVE_DISTANCE,0);
			Drivetrain.getInstance().stop();
		}
		else if(alliance==BLUE){
			PIDDriveStraightTest.driveStraight(FIRST_DRIVE_DISTANCE,10);
			Drivetrain.getInstance().stop();
			PIDRotateTest.rotate(-ROTATE_ANGLE,2);
			Drivetrain.getInstance().stop();
			PIDDriveStraightTest.driveStraight(SECOND_DRIVE_DISTANCE,0);
			Drivetrain.getInstance().stop();
		}
	}
}
