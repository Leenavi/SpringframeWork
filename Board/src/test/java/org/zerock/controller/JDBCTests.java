package org.zerock.controller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnecton() {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hr","hr"
					);
			log.info("------------testConnecton----------------------");
			log.info(conn);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
