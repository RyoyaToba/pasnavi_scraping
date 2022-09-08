package com.example.demo.parts;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.example.demo.database.DBManager;
import com.example.demo.entity.University;

public class CreateIdList {

	public static List<Integer> createIdList() throws IOException {

		List<Integer> idList = new ArrayList<>();

		for (Integer pref = 1; pref <= 47; pref++) {

			List<Integer> nowIdList = new ArrayList<>();

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

			elements = document.select("a");

			nowIdList = elements.stream().map(e -> String.valueOf(e.absUrl("href"))).filter(e -> e.contains("campus"))
					.map(e -> Integer.parseInt(e.substring((e.indexOf("univ/") + 5), e.indexOf("univ/") + 9)))
					.collect(Collectors.toList());

			idList = Stream.concat(idList.stream(), nowIdList.stream()).collect(Collectors.toList());

		}
		System.out.println(idList);
		return idList;
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
