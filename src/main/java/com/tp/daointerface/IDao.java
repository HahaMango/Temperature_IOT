package com.tp.daointerface;

import java.util.Date;
import java.util.List;

import com.tp.enity.TpRowMapping;

public interface IDao {
	
	public boolean insert(float tp,String local);
	
	public List<TpRowMapping> getTp(Date startData,Date endDate);
}
