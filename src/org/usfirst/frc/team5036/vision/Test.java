package org.usfirst.frc.team5036.vision;

import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

public class Test {
	public static void main(String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		VideoCapture camera = new VideoCapture();
		camera.open(0);
		if(camera.isOpened()){
			System.out.println("it worked!");
		}
	}
}
