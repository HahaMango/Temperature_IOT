package com.tp.enity;

import java.util.List;

public class Tp2JSON {
	
	private int dayNumber;
	
	private List<TpRowMapping> jsonList;
	
	public Tp2JSON(int daynumber,List<TpRowMapping> jsonlist) {
		// TODO Auto-generated constructor stub
		this.dayNumber=daynumber;
		this.jsonList=jsonlist;
	}
	
	public void setDayNumber(int daynumber) {
		this.dayNumber = daynumber;
	}
	
	public int getDayNumber() {
		return dayNumber;
	}
	
	public void setJsonList(List<TpRowMapping> jsonList) {
		this.jsonList = jsonList;
	}
	
	public List<TpRowMapping> getJsonList() {
		return jsonList;
	}
}
