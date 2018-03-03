package com.dac.testcases;

import java.io.IOException;
import java.util.Set;

import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.ITestRunnerFactory;

import resources.base;

public class listeners implements ITestListener{
	
base b= new base();
public void onFinish(ITestContext arg0) {
	// TODO Auto-generated method stub
	
}


public void onStart(ITestContext arg0) {
	// TODO Auto-generated method stub
	
}

public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	// TODO Auto-generated method stub
	
}

public void onTestFailure(ITestResult result) {
	// TODO Auto-generated method stub
	try {
		System.out.println("reached listener");
		b.getScreenshot(result.getName());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public void onTestSkipped(ITestResult arg0) {
	// TODO Auto-generated method stub
	
}

public void onTestStart(ITestResult arg0) {
	// TODO Auto-generated method stub
	
}

public void onTestSuccess(ITestResult arg0) {
	// TODO Auto-generated method stub
	
}
}
