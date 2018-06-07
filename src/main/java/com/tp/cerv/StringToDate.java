package com.tp.cerv;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class StringToDate implements Formatter<Date>{

	DateFormat dateFormat = null;
	
	public StringToDate() {
		// TODO Auto-generated constructor stub
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
	}
	
	@Override
	public String print(Date arg0, Locale arg1) {
		// TODO Auto-generated method stub
		if(arg0==null)
			return "";
		return dateFormat.format(arg0);
	}

	@Override
	public Date parse(String arg0, Locale arg1) throws ParseException {
		// TODO Auto-generated method stub
		if(arg0.length()==0)
			return null;
		return dateFormat.parse(arg0);
	}
	
}
