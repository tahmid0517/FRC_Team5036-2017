package org.usfist.frc.team5036.auton;
import org.usfirst.frc.team5036.robot.Sensors;
import org.usfirst.frc.team5036.subsystems.Drivetrain;
import org.usfirst.frc.team5036.subsystems.GearMechanism;
import org.usfirst.frc.team5036.teleop.OperatorInterface;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDDriveStraightTest {
	public static final double kP = 0.005859375;
	public static final double kI = 0.00001015625;
	public static final double kD = 0.3072916666666667;
	public static double currentError,lastError,totalError,angleCurrentError,angleLastError;
	public static void driveStraight(double length,double tolerance){
		Sensors.getInstance().resetEncoders();
		SmartDashboard.putString("PID:","ON");
		double error = length - Sensors.getInstance().getEncoderAverage();
		double angleError = -Sensors.getInstance().getGyro();
		Sensors.getInstance().resetGyro();
		currentError = error;
		lastError = error;
		totalError = 0;
		angleCurrentError = angleError;
		angleLastError = angleError;
		int correct = 0;
		while(DriverStation.getInstance().isAutonomous() && correct<5){
		//while(DriverStation.getInstance().isAutonomous() && Math.abs(error)>0){
			double output = getP(error) - getD()+getI();
			angleError = -Sensors.getInstance().getGyro();
			Drivetrain.getInstance().arcadeDrive(-(output),getAnglePID(angleError));
			error = length - Sensors.getInstance().getEncoderAverage();
			if(error<500){
				totalError+=error;
			}
			lastError = currentError;
			currentError = error;
			angleError = -Sensors.getInstance().getGyro();
			angleLastError = angleCurrentError;
			angleCurrentError = angleError;
			if(Math.abs(error)<tolerance){
				correct++;
			}
			Timer.delay(0.02);
		}
		Drivetrain.getInstance().arcadeDrive(0,0);
	}
	public static void driveStraightTimeCutLoop(double length){
		Sensors.getInstance().resetEncoders();
		SmartDashboard.putString("PID:","ON");
		double error = length - Sensors.getInstance().getEncoderAverage();
		double angleError = -Sensors.getInstance().getGyro();
		Sensors.getInstance().resetGyro();
		currentError = error;
		lastError = error;
		totalError = 0;
		angleCurrentError = angleError;
		angleLastError = angleError;
		int count = 0;
		while(DriverStation.getInstance().isAutonomous() && count<200){
			double output = getP(error) - getD()+getI();
			angleError = -Sensors.getInstance().getGyro();
			Drivetrain.getInstance().arcadeDrive(-(output),getAnglePID(angleError));
			error = length - Sensors.getInstance().getEncoderAverage();
			if(error<500){
				totalError+=error;
			}
			lastError = currentError;
			currentError = error;
			angleError = -Sensors.getInstance().getGyro();
			angleLastError = angleCurrentError;
			angleCurrentError = angleError;
			Timer.delay(0.02);
			if(!GearMechanism.getInstance().hasGear()){
				count++;
			}
			
		}
		Drivetrain.getInstance().arcadeDrive(0,0);
	}
	public static double getP(double error){
		return error * getPConstant();
	}
	public static double getD(){
		System.out.println("D:s"+(lastError - currentError));
		return getDConstant() * (lastError - currentError);
	}
	public static double getI(){
		return totalError * getIConstant();
	}
	public static double getPConstant(){
		return 0.0006;
	}
	public static double getDConstant(){
		return 0.0025;
	}
	public static double getIConstant(){
		return kI;
	}
	public static double getAnglePID(double error){
		return getAngleP(error) - getAngleD();
	}
	public static double getAngleP(double error){
		return PIDRotateTest.kP * error;
	}
	public static double getAngleD(){
		return PIDRotateTest.kD * (angleLastError - angleCurrentError);
	}
}
