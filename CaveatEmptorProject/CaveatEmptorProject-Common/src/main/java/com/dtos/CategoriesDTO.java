package com.dtos;

import java.io.Serializable;

public class CategoriesDTO  implements Serializable  {

	private static final long serialVersionUID = -3515174780659714112L;
	private Long categoryId;
	private String nameCategory;
	private String description;
	private Long parentId;	

	public CategoriesDTO(){}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	



}
