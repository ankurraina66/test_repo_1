package com.levent.consultantapi.service.consultant.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levent.consultantapi.model.Consultant;
import com.levent.consultantapi.repository.ConsultantRepository;
import com.levent.consultantapi.service.ConsultantService;

@Service
public class ConsultantServiceImpl implements ConsultantService {

	String password = "sdfds";
	@Autowired
	private ConsultantRepository consultantRepository;

	@Override
	public List<Consultant> getConsultants() {
		return consultantRepository.list();
	}

	@Override
	public Consultant createConsultant(Consultant consultant) {
		return consultantRepository.create(consultant);
	}

	@Override
	public Consultant getConsultantById(Long id) {
		return consultantRepository.get(id);
	}

	@Override
	public Consultant updateConsultantById(Long id, Consultant consultant) {
		return consultantRepository.update(id, consultant);
	}

	@Override
	public Consultant deleteConsultantById(Long id) {
		return consultantRepository.delete(id);
	}

	public static void main787() throws Exception {
	System.out.println("Hurray------->");
		String username = "test"; // user input
		String password = "test"; // user input

		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/testdb", "root", "password");

		Statement stmt = conn.createStatement();

		String sql = "SELECT * FROM users WHERE username = '"
				+ username + "' AND password = '" + password + "'";

		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			System.out.println("Login successful");
		} else {
			System.out.println("Invalid credentials");
		}

		conn.close();
	}
}

