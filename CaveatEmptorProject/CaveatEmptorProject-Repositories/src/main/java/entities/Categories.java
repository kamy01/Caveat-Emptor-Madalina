package entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import RepositoryConstants.*;

@Entity
@NamedQueries({ @NamedQuery(name = QueryConstants.GET_CATEGORIES, query = QueryConstants.GET_CATEGORIES_QUERY)
})
public class Categories implements Serializable{
	
	private static final long serialVersionUID = 8408902672059587243L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;	
	@Column
	private Long parentId;	
	@Column
	private String nameCategory;	
	@Column
	private String description;
	
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

}
