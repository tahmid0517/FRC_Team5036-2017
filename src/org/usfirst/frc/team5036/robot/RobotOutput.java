package org.usfirst.frc.team5036.robot;

import org.usfirst.frc.team5036.subsystems.Climber;
import org.usfirst.frc.team5036.subsystems.Drivetrain;
import org.usfirst.frc.team5036.subsystems.GearMechanism;

public class RobotOutput {
	public static Drivetrain drivetrain;
	public static GearMechanism gearMechanism;
	public static Climber climber;
	public static void init()
	{
		drivetrain = Drivetrain.getInstance();
		gearMechanism = GearMechanism.getInstance();
		climber = Climber.getInstance();
	}
}
