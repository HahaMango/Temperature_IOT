package com.tp.contorller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tp.daointerface.IDao;
import com.tp.enity.Tp2JSON;
import com.tp.enity.TpRowMapping;

@Controller
public class Main_TpContorller {
	
	@Autowired
	private IDao databaseAccess;

	// json转换类
	@Autowired
	private ObjectMapper objectmapper;

	@ResponseBody
	@RequestMapping(value = "/test")
	public String test() {
		return "hello world";
	}

	@ResponseBody
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public int Send(@RequestParam(value = "tp") String tp, @RequestParam(value = "local") String local) {
		float tp_float = 0;

		try {
			tp_float = Float.parseFloat(tp);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Send parse float error");
		}

		boolean result = databaseAccess.insert(tp_float, local);
		if (result)
			return 1;
		return 0;
	}

	@RequestMapping("/Page")
	public String tpPage() {
		return "main_tp";
	}

	//json输出
	@ResponseBody
	@RequestMapping("/jsoninterface")
	public String getJSON(@RequestParam(value = "start") Date start, @RequestParam(value = "daynumber") int daynumber) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		calendar.add(calendar.DAY_OF_MONTH, daynumber);
		Date end = calendar.getTime();
		
		List<TpRowMapping> tempTplist = databaseAccess.getTp(start, end);
		Tp2JSON tp2json = new Tp2JSON(daynumber, tempTplist);
		String json = "";
		try {
			json = objectmapper.writeValueAsString(tp2json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
