package com.example.demo.parts;

public class ConversionUniversityCategory {

	public static Integer conversionCategory(String category) {

		switch (category) {
			case "共学":
				return 1;
			case "女子":
				return 2;
			default:
				return 0;
		}
	}
}
