package org.usfirst.frc.team5036.auton.sequences;

import org.usfirst.frc.team5036.robot.Sensors;
import org.usfirst.frc.team5036.subsystems.Drivetrain;
import org.usfist.frc.team5036.auton.PIDDriveStraight;
import org.usfist.frc.team5036.auton.PIDDriveStraightTest;
import org.usfist.frc.team5036.auton.PIDRotateTest;

import edu.wpi.first.wpilibj.DriverStation;

public class MiddlePegGearScore {
	public static void execute(){
		PIDDriveStraightTest.driveStraight(7270,4);
		Drivetrain.getInstance().stop();
	}
}
