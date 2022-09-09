package com.example.demo.parts;

public class ConversionUniversityCategory {

	public static Integer conversionCategory(String category) {

		switch (category) {
			case "国立":
				return 1;
			case "公立":
				return 2;
			case "私立":
				return 3;
			default:
				return 0;
		}
	}
}
