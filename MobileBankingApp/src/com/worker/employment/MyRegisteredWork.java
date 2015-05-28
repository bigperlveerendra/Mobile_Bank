package com.worker.employment;

import java.io.Serializable;

public class MyRegisteredWork implements Serializable {
	int regID,workID;
	public MyRegisteredWork(int a,int b) {
		regID=a;
		workID=b;
	}

}
