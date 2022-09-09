package com.example.demo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private final static String DB_URL = "DBURL";
	private final static String USER_NAME = "USERNAME"
	private final static String PASSWORD = "PASSWORD";

	/* DBに接続するメソッド */
	public static Connection createConnection() {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DBの接続に失敗しました");
		}
	}

	/* DBから切断するメソッド */
	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("DBの切断に失敗しました");
		}
	}
}
