package com.example.demo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.example.demo.database.DBManager;
import com.example.demo.entity.University;

public class NameScrapingController {

	public static void main(String[] args) throws IOException {

		// PreparedStatement pstmt = null;

		for (Integer pref = 1; pref <= 47; pref++) {

			String prefNumberString = String.valueOf(pref);

			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (pref < 10) {
				prefNumberString = 0 + prefNumberString;
			}

			String url = "https://passnavi.evidus.com/search_univ/list/pre_" + prefNumberString + "/";

			Document document = Jsoup.connect(url).get();

			Elements elements = document.select(".sitemap-list > li");
			List<String> nameList = elements.stream().map(e -> String.valueOf(e.text())).filter(e -> e.contains("大学"))
					.collect(Collectors.toList());

			elements = document.select("a");

			List<Integer> idList = elements.stream().map(e -> String.valueOf(e.absUrl("href")))
					.filter(e -> e.contains("campus"))
					.map(e -> Integer.parseInt(e.substring((e.indexOf("univ/") + 5), e.indexOf("univ/") + 9)))
					.collect(Collectors.toList());

			Connection con = DBManager.createConnection();
			String sql = "";

			for (int i = 0; i < nameList.size(); i++) {
				University university = new University();
				university.setId(idList.get(i));
				university.setName(nameList.get(i));
			}
		}
	}
}

//				sql = "INSERT INTO name (id, name) VALUES (" + university.getId() + ","  + "'" + university.getName() + "'" + ")";
//				System.out.println(sql);
//				try {
//					pstmt = con.prepareStatement(sql);
//					pstmt.executeUpdate();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			DBManager.closeConnection(con);
