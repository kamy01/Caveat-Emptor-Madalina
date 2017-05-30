package beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;

import com.dtos.CategoriesDTO;

import ServiceInterfaces.CategoryService;
import ServiceInterfaces.UserService;
import exceptions.CaveatEmptorException;

@ManagedBean(name="contentCategory")
@ViewScoped
public class ContentCategoryBean {
	
	private CategoriesBeanTree categoryTree;
	
	@EJB
	CategoryService categoryService;
	

	public CategoriesBeanTree getCategoryTree() {
		return categoryTree;
	}

	public void setCategoryTree(CategoriesBeanTree categoryTree) {
		this.categoryTree = categoryTree;
	}

	public void onNodeSelect(NodeSelectEvent event) {
		event.getTreeNode().getData();
		
		this.categoryTree.setNameCategory(((CategoriesDTO)event.getTreeNode().getData()).getNameCategory());
		this.categoryTree.setDescription(((CategoriesDTO)event.getTreeNode().getData()).getDescription());
		////categoryTree.getNameCategory() = ((CategoriesDTO)event.getTreeNode().getData()).getNameCategory();
		//this.description = ((CategoriesDTO)event.getTreeNode().getData()).getDescription();
	
	}
	
	public void editOrCreateCategory(){
		
	}
	
	public void remove() throws CaveatEmptorException{
		categoryService.removeCategory((CategoriesDTO)categoryTree.getSelectedNode());
	}
	
}
