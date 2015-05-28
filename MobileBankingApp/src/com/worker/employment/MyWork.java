package com.worker.employment;

import java.io.Serializable;

public class MyWork implements Serializable{
	
	
	
	public int id;
	public String description;
	public int days;
	public java.sql.Date startDate;
	public int money;
	public MyWork(int id,String description,int days,java.sql.Date startDate,int money) {
		this.id=id;
		this.description=description;
		this.days=days;
		this.startDate=startDate;
		this.money=money;
	}
	
	

}
