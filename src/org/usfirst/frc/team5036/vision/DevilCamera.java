package org.usfirst.frc.team5036.vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class DevilCamera {
	private static DevilCamera instance;
	UsbCamera camera;
	public static DevilCamera getInstance(){
		if(instance==null){
			instance = new DevilCamera();
		}
		return instance;
	}
	public DevilCamera(){
		camera = CameraServer.getInstance().startAutomaticCapture();
	}
}
