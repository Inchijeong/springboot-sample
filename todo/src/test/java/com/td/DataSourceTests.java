package com.td;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class DataSourceTests {

	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testConnection() {
		
		try{
			Connection con = dataSource.getConnection();
			log.info("Meta Data: " + con.getMetaData());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
