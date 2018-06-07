package com.tp.enity;

public class TpRowMapping {

	// id列
	private int id;

	// 时间列
	private String date;

	// 温度列
	private float temperature;

	// 地点列
	private String local;
	
	public TpRowMapping() {
		// TODO Auto-generated constructor stub
	}

	public TpRowMapping(int id, String date, float temp, String local) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.date = date;
		this.temperature = temp;
		this.local = local;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
}
