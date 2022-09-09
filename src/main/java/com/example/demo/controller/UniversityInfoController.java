package com.example.demo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.demo.database.DBManager;
import com.example.demo.entity.University;
import com.example.demo.parts.ConversionGenderCategory;
import com.example.demo.parts.ConversionPrefecture;
import com.example.demo.parts.ConversionUniversityCategory;
import com.example.demo.parts.CreateIdList;

public class UniversityInfoController {

	public static void main(String[] args) throws IOException {

		Integer id = 0;
		String idString = "";
		List<Integer> idList = List.of(60, 40, 50, 70, 20, 10, 30, 1010, 1538, 1015, 1020, 1018, 1012, 1510, 1525, 1530, 1535, 1520, 1536, 1539, 1540, 1549, 1548, 1550, 1570, 1625, 1580, 1545, 1590, 1600, 1560, 1610, 1620, 1621, 1635, 1650, 1655, 6000, 6010, 6015, 6020, 6025, 6030, 6040, 6110, 6070, 6065, 6105, 6085, 6120, 80, 1021, 1022, 1660, 1665, 1670, 1680, 1690, 1695, 1700, 6135, 6140, 6150, 6145, 6154, 90, 1023, 1710, 1711, 1720, 1730, 5709, 5710, 6160, 6175, 100, 110, 1024, 1740, 1745, 1755, 1750, 1800, 1760, 1770, 1780, 1790, 1795, 1810, 6185, 6187, 6186, 6195, 6205, 120, 1026, 1032, 1025, 1815, 1818, 1820, 6210, 6215, 6217, 6220, 130, 1027, 1033, 1825, 1827, 1828, 5725, 6225, 6235, 140, 1028, 1030, 1830, 1840, 1850, 1855, 1857, 5730, 6240, 6245, 6250, 6255, 150, 165, 160, 1035, 9226, 1860, 1867, 1865, 1870, 1875, 1880, 6265, 6275, 6285, 180, 1890, 1925, 1895, 1900, 1910, 1920, 1930, 1935, 6295, 6300, 6310, 6315, 6320, 190, 1038, 1040, 1050, 1055, 1938, 1940, 1945, 1946, 1947, 1949, 1950, 1952, 1953, 6335, 6350, 6352, 6365, 6368, 6370, 6375, 200, 5310, 1057, 1962, 1965, 1970, 1975, 1980, 1985, 1990, 1995, 2000, 2010, 2020, 2025, 2030, 2035, 2040, 2050, 2060, 1991, 2062, 2063, 2065, 2070, 2077, 2079, 2086, 6380, 6400, 6410, 6420, 6425, 6430, 6432, 6440, 6460, 6465, 6470, 210, 5320, 1059, 2088, 2089, 2090, 2235, 2095, 2100, 2110, 2120, 2130, 2135, 2230, 2140, 2145, 2150, 2155, 2185, 2160, 2170, 2180, 2190, 2210, 2220, 5250, 2080, 2238, 2240, 2250, 6478, 6495, 6500, 6505, 6515, 6520, 6525, 6532, 6537, 310, 320, 230, 240, 245, 250, 270, 280, 220, 260, 330, 5400, 5330, 1065, 2260, 2270, 1960, 2300, 2290, 2305, 2315, 2310, 2330, 2350, 2360, 2370, 2380, 2390, 2400, 2410, 9202, 2420, 2425, 2431, 2430, 3280, 2440, 2450, 2460, 2470, 9216, 2490, 2480, 2500, 2515, 2520, 2530, 2540, 2550, 2560, 2570, 2580, 2590, 2600, 2610, 2620, 2630, 2640, 2660, 2650, 2670, 2680, 2690, 3410, 2700, 2200, 2704, 2710, 2715, 2720, 2723, 2725, 2730, 2750, 2740, 2760, 2770, 3340, 9215, 2780, 2790, 2795, 2810, 2820, 2800, 2830, 9205, 2835, 2222, 2840, 2845, 2850, 3080, 2860, 1955, 2865, 2868, 2870, 2880, 2900, 2890, 2225, 2910, 2920, 2950, 3010, 2940, 2960, 2970, 2990, 2980, 3000, 2930, 3020, 3035, 3040, 2075, 3050, 3060, 3070, 3090, 3100, 3110, 3130, 3120, 3140, 3150, 2085, 3155, 3160, 3170, 3030, 3180, 3190, 6550, 6563, 6390, 6575, 6595, 6615, 6630, 6640, 6645, 6655, 6660, 6665, 6685, 6695, 6730, 6725, 6732, 6735, 6750, 6770, 6771, 6790, 6795, 6815, 6820, 6825, 6855, 6810, 6720, 6910, 9551, 6913, 340, 5340, 1078, 1079, 1080, 3200, 3220, 3230, 3210, 3240, 3250, 2320, 3270, 3282, 3283, 3284, 3285, 3290, 2510, 1537, 3300, 3310, 3320, 3325, 3330, 3350, 3355, 3360, 3365, 3370, 3371, 3372, 3373, 6925, 6930, 6970, 6940, 6955, 6965, 6975, 6980, 6990, 7000, 7030, 370, 360, 350, 1081, 3383, 1083, 1082, 9217, 3375, 3382, 3381, 3384, 3385, 3386, 3387, 3390, 3392, 3395, 3400, 3402, 7045, 7050, 7055, 7060, 7062, 380, 1085, 3450, 3460, 7070, 7072, 400, 1092, 1091, 1090, 1093, 3470, 3500, 3490, 9206, 3480, 3505, 3512, 3510, 7075, 7090, 7080, 7100, 410, 1094, 1095, 3515, 3516, 3520, 7105, 430, 1100, 1102, 3412, 3415, 3417, 3420, 5800, 7115, 7130, 450, 3425, 3430, 1105, 1107, 3423, 3427, 3433, 3438, 3440, 3437, 7135, 7140, 7145, 7150, 7155, 7170, 7175, 7180, 460, 1115, 1110, 3530, 3535, 3540, 3560, 3550, 3563, 3565, 3567, 3570, 5815, 7185, 7200, 7195, 7205, 7210, 7215, 7220, 7225, 7227, 470, 480, 1120, 9101, 1125, 3572, 3573, 3576, 3575, 3577, 3580, 3592, 5820, 9501, 7230, 7255, 7263, 7265, 500, 520, 510, 490, 1140, 1130, 1150, 3610, 3620, 3630, 3635, 3640, 3645, 3650, 3600, 3717, 3653, 3655, 3656, 3657, 3658, 3660, 3700, 3668, 3670, 3675, 3680, 3690, 3710, 3715, 3720, 3730, 3735, 3740, 3750, 3760, 3763, 3770, 3780, 9224, 3785, 3790, 3800, 3810, 3815, 3818, 3820, 3825, 3830, 3835, 3840, 3850, 7268, 7270, 7275, 7280, 7295, 7310, 7440, 7315, 7300, 7370, 7385, 7390, 7375, 7395, 7410, 7450, 530, 1152, 3860, 3865, 3867, 3881, 3880, 5850, 7460, 7465, 7468, 550, 540, 1155, 3885, 3884, 3886, 3889, 3888, 9218, 7475, 7485, 7480, 570, 580, 560, 1160, 1180, 1170, 3947, 3890, 3895, 3900, 3910, 3912, 3913, 3945, 3970, 3920, 3930, 3940, 3950, 4010, 3953, 3955, 3960, 3915, 3980, 4000, 3990, 4020, 4030, 3887, 4040, 4050, 4060, 7495, 7505, 7520, 7523, 7545, 7560, 7540, 7550, 7575, 610, 590, 5370, 1205, 4063, 4065, 4068, 4070, 4220, 4080, 4090, 4093, 4205, 4100, 4110, 4120, 4130, 4140, 9225, 4150, 4160, 4170, 4180, 4152, 4183, 4185, 4187, 4190, 4200, 4155, 4202, 4203, 4212, 4250, 4253, 4260, 4240, 4255, 4270, 4276, 4278, 4280, 4290, 4295, 4300, 4252, 4530, 4320, 4325, 4330, 4335, 4340, 4343, 4345, 4350, 4355, 4357, 7580, 7595, 7600, 7605, 7750, 7612, 7735, 7625, 7630, 7650, 7655, 7640, 7675, 7680, 7695, 7705, 7710, 7715, 7745, 7770, 620, 640, 9102, 1220, 1225, 1235, 4360, 4380, 4508, 4382, 4385, 4390, 4400, 4420, 4410, 4395, 4430, 4440, 4450, 4570, 4490, 4460, 4470, 4500, 4475, 4480, 4520, 4532, 4397, 4540, 4550, 4545, 4560, 4580, 7795, 7815, 7855, 7835, 7850, 7865, 7870, 7875, 7880, 7805, 7885, 7895, 7900, 7905, 650, 660, 1250, 1255, 4585, 4590, 4601, 4600, 4620, 4610, 7925, 7930, 7937, 670, 1260, 4630, 4628, 9219, 7945, 680, 4635, 4632, 7950, 690, 1263, 5892, 710, 1265, 1266, 9211, 4638, 4640, 4650, 4660, 4665, 4667, 4670, 4675, 4680, 4685, 4690, 4695, 4700, 4710, 5905, 7955, 7960, 7970, 7975, 7980, 7990, 7995, 720, 5350, 1268, 1267, 1271, 1275, 1287, 4720, 4724, 4725, 4730, 4740, 4745, 4750, 4760, 4775, 4723, 4780, 4790, 4795, 4800, 8010, 8025, 8050, 8060, 730, 5360, 1290, 1300, 4835, 4805, 4833, 4810, 4820, 4830, 4834, 8065, 8070, 8075, 8105, 8100, 740, 750, 4840, 4850, 8110, 8115, 8125, 760, 1304, 4860, 4865, 8130, 8150, 780, 1307, 4870, 4885, 4880, 8155, 8165, 8170, 8175, 790, 1310, 1309, 4887, 9212, 8180, 840, 820, 810, 1320, 1330, 1335, 1340, 4889, 4890, 4900, 4910, 4915, 4920, 4940, 4930, 4945, 1450, 4947, 4950, 4955, 4957, 4970, 4980, 5000, 5010, 4960, 5015, 5021, 5030, 5032, 5040, 5051, 5050, 5020, 5056, 8190, 8195, 8200, 8205, 8220, 8230, 8240, 8245, 8250, 8275, 8280, 8285, 8287, 8290, 8260, 8295, 8300, 850, 5060, 8305, 8315, 8310, 870, 1350, 5070, 5073, 5071, 5072, 5075, 5080, 8350, 8335, 880, 1360, 5115, 5095, 5110, 5113, 5120, 5100, 5125, 8375, 8380, 890, 1362, 5130, 5140, 5142, 5950, 8385, 8390, 8400, 8395, 910, 1365, 1367, 5145, 5150, 5155, 5160, 8410, 8415, 930, 940, 5170, 5175, 5180, 5190, 5955, 8425, 8430, 8435, 950, 1380, 1370, 1390, 5205, 5210, 5200, 8440, 8450);
		//List<Integer> idList = CreateIdList.createIdList();
		String sql = "";
		PreparedStatement pstmt = null;
		Integer idNumber = idList.indexOf(7080);
		
		for (int i = idNumber; i < idList.size(); i++) {
			
			id =  idList.get(i);
			
			try {
				Thread.sleep(1 * 2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			
			if (id < 10) {
				idString = "0" + 0 + 0 + id;
			} else if (id < 100) {
				idString = "0" + 0 + id;
			} else if (id < 1000) {
				idString = "0" + id;
			} else {
				idString = String.valueOf(id);
			}
			

			String url = "https://passnavi.evidus.com/search_univ/" + idString + "/campus.html";

			Document document = Jsoup.connect(url).get();

			University university = new University();

			university.setId(Integer.parseInt(idString));

			Elements elements = document.select("h2.ttl-university");
			List<String> nameList = elements.stream().map(e -> String.valueOf(e.text()).replace(" ", "")).distinct()
					.collect(Collectors.toList());

			university.setName(nameList.get(0));

			elements = document.select(".area");
			List<String> prefectureList = elements.stream().map(e -> e.text()).collect(Collectors.toList());

			university.setPrefecturId(ConversionPrefecture.conversionPref((prefectureList.get(0))));

			elements = document.select(".cat");
			for (Element element : elements) {
				if (element.text().contains("|")) {
					university.setUniversityCategoryId(
							ConversionUniversityCategory.conversionCategory(createUniversityCategory(elements)));
					university.setGenderCategoryId(
							ConversionGenderCategory.conversionCategory(createFemailCategory(elements)));
				} else {
					university.setGenderCategoryId(ConversionGenderCategory.conversionCategory("共学"));
					university.setUniversityCategoryId(ConversionUniversityCategory.conversionCategory(element.text()));
				}
			}
			
			Connection con = DBManager.createConnection();
			
			try {
				sql = "INSERT INTO university (id, name, university_category_id, prefecture_id, gender_category_id) "
					+ "VALUES (" + university.getId() + ","  
								+ "'" + university.getName() + "'" + ","
					            + university.getUniversityCategoryId() + ","
								+ university.getPrefecturId() + ","
								+ university.getGenderCategoryId() + ")";
						
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.closeConnection(con);
			}
			System.out.println(sql);
		}
	}

	private static String createUniversityCategory(Elements elements) {
		List<String> genderCategoryList = elements.stream().map(e -> e.text().substring(0, e.text().indexOf("|")))
				.collect(Collectors.toList());
		return genderCategoryList.get(0);
	}

	private static String createFemailCategory(Elements elements) {
		List<String> universityCategoryList = elements.stream().map(e -> e.text().substring(e.text().indexOf("|") + 1))
				.collect(Collectors.toList());
		return universityCategoryList.get(0);
	}

}
