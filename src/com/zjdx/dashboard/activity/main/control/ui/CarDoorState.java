package com.zjdx.dashboard.activity.main.control.ui;


public class CarDoorState {
	public boolean mFrontLeftOpen = false;
	public boolean mFrontRightOpen = false;
	public boolean mBehindLeftOpen = false;
	public boolean mBehindRightOpen = false;
	
	public CarDoorState(){
	}
	
	public CarDoorState(boolean frontLeftOpen, boolean frontRightOpen,
			boolean behindLeftOpen, boolean behindRightOpen){
		mFrontLeftOpen = frontLeftOpen;
		mFrontRightOpen = frontRightOpen;
		mBehindLeftOpen = behindLeftOpen;
		mBehindRightOpen = behindRightOpen;
	}
}