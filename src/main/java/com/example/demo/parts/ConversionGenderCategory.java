package com.example.demo.parts;

public class ConversionGenderCategory {

	public static Integer conversionCategory(String category) {
		System.err.println(category);
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
