package com.example.demo.entity;

public class University {

	private Integer id;
	private String name;
	private Integer universityCategoryId;
	private Integer prefecturId;
	private Integer genderCateggoryId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUniversityCategoryId() {
		return universityCategoryId;
	}

	public void setUniversityCategoryId(Integer universityCategoryId) {
		this.universityCategoryId = universityCategoryId;
	}

	public Integer getPrefecturId() {
		return prefecturId;
	}

	public void setPrefecturId(Integer prefecturId) {
		this.prefecturId = prefecturId;
	}

	public Integer getGenderCateggoryId() {
		return genderCateggoryId;
	}

	public void setGenderCateggoryId(Integer genderCateggoryId) {
		this.genderCateggoryId = genderCateggoryId;
	}

	@Override
	public String toString() {
		return "University [id=" + id + ", name=" + name + ", universityCategoryId=" + universityCategoryId
				+ ", prefecturId=" + prefecturId + ", genderCateggoryId=" + genderCateggoryId + "]";
	}

}
