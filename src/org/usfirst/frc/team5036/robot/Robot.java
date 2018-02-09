package org.usfirst.frc.team5036.robot;

import org.usfirst.frc.team5036.auton.sequences.BlueAllianceLeftPeg;
import org.usfirst.frc.team5036.auton.sequences.LeftSidePegGearScore;
import org.usfirst.frc.team5036.auton.sequences.MiddlePegGearScore;
import org.usfirst.frc.team5036.auton.sequences.MiddlePegPlusPartTwo;
import org.usfirst.frc.team5036.auton.sequences.RedAllianceRightPeg;
import org.usfirst.frc.team5036.auton.sequences.SidePegChuteSide;
import org.usfirst.frc.team5036.subsystems.Drivetrain;
import org.usfirst.frc.team5036.subsystems.GearMechanism;
import org.usfirst.frc.team5036.teleop.Teleop;
import org.usfirst.frc.team5036.util.DevilAuton;
import org.usfirst.frc.team5036.vision.DevilCamera;
import org.usfist.frc.team5036.auton.PIDDriveStraightTest;
import org.usfist.frc.team5036.auton.PIDRotate;
import org.usfist.frc.team5036.auton.PIDRotateTest;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public DevilCamera camera;
	private static final Integer ONE_GEAR_MIDDLE = 0;
	private static final Integer NOTHING = 1;
	private static final Integer ONE_GEAR_MIDDLE_PLUS_PART_2_RED = 2;
	private static final Integer GEAR_LEFT_PEG_RED_ALLIANCE = 3;
	private static final Integer GEAR_RIGHT_PEG_BLUE_ALLIANCE = 4;
	private static final Integer ONE_GEAR_MIDDLE_PLUS_PART_2_BLUE = 5;
	int autoSelected;
	
	//GLOBAL PID VARIABLES
	public static double driveStraightP;
	SendableChooser<Integer> autonChooser = new SendableChooser<Integer>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotOutput.init();
		autonChooser.addDefault("One Gear, Middle Peg", ONE_GEAR_MIDDLE);
		autonChooser.addObject("Nothing", NOTHING);
		autonChooser.addObject("One Gear Middle With Part 2-red",ONE_GEAR_MIDDLE_PLUS_PART_2_RED);
		autonChooser.addObject("One Gear Middle With Part 2-blue",ONE_GEAR_MIDDLE_PLUS_PART_2_BLUE);
		autonChooser.addObject("One Gear Side Red", GEAR_LEFT_PEG_RED_ALLIANCE);
		autonChooser.addObject("One Gear Side Blue",GEAR_RIGHT_PEG_BLUE_ALLIANCE);
		SmartDashboard.putData("Auto choices",autonChooser);
		camera = DevilCamera.getInstance();
		Sensors.getInstance();
	}
	public void disabledPeriodic(){
		//driveStraightP = Preferences.getInstance().getDouble("Drive Straight-P",0);
	}
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		GearMechanism.getInstance().setServoAngle(0);
		System.out.println("autonstarted");
		autoSelected = autonChooser.getSelected();
		System.out.println("auton selected");
		if(autoSelected==ONE_GEAR_MIDDLE){
			MiddlePegGearScore.execute();
		}
		else if(autoSelected==ONE_GEAR_MIDDLE_PLUS_PART_2_RED){
			MiddlePegPlusPartTwo.execute(0);
		}
		else if(autoSelected==ONE_GEAR_MIDDLE_PLUS_PART_2_BLUE){
			MiddlePegPlusPartTwo.execute(1);
		}
		else if(autoSelected==GEAR_LEFT_PEG_RED_ALLIANCE){
			SidePegChuteSide.execute(SidePegChuteSide.RED);
		}
		else if(autoSelected==GEAR_RIGHT_PEG_BLUE_ALLIANCE){
			SidePegChuteSide.execute(SidePegChuteSide.BLUE);
		}
		/*else if(autoSelected==GEAR_LEFT_PEG_BLUE_ALLIANCE){
			BlueAllianceLeftPeg.execute();
		}
		else if(autoSelected==GEAR_RIGHT_PEG_RED_ALLIANCE){
			RedAllianceRightPeg.execute();
		}*/
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopInit(){
		Teleop.init();
	}
	
	@Override
	public void teleopPeriodic() {
		Teleop.run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

