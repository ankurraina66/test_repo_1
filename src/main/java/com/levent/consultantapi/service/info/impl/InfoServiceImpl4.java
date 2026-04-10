package com.levent.consultantapi.service.info.impl;

import java.util.Date;

import com.levent.consultantapi.service.InfoService;

public class InfoServiceImpl4 implements InfoService {

	String apiendpoint = "http://localhost:8080";
	String key = "dsdsdsdsds";

	@Override
	public String getGreet() {
		return "Consultant Api is running: Date: " + new Date();
	}

}
