package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.TreeNode;

import com.dtos.CategoriesDTO;

@ManagedBean(name="contentCategory")
@ViewScoped
public class ContentCategoryBean {
	
	private String nameCategory;
	private String description;
	private TreeNode selectedNode;
	
	private String option;
	@PostConstruct
    public void init() {
		option=new String("Categories");
		
	}
	
	
	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
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
	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}


	
	public void onNodeUnSelect(NodeExpandEvent event) {
		event.getTreeNode().getData();
		this.nameCategory = ((CategoriesDTO)event.getTreeNode().getData()).getNameCategory();
		this.description = ((CategoriesDTO)event.getTreeNode().getData()).getDescription();
	}
	
}
