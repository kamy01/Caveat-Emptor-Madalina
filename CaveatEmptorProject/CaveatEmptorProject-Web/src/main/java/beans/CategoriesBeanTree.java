package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import com.dtos.CategoriesDTO;
import ServiceInterfaces.CategoryService;
import exceptions.CaveatEmptorException;
import utils.Constants;


@ManagedBean(name="categoriesBeanTree")
@ViewScoped
public class CategoriesBeanTree implements Serializable{

	private static final long serialVersionUID = 4823707193499977530L;
	private TreeNode root;
	private TreeNode selectedNode;

	@EJB
	CategoryService categoryService;
	
	private String nameCategory;
	private String description;
	private List<CategoriesDTO> categoriesDto;

	
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
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

	public List<CategoriesDTO> getCategoriesDto() {
		return categoriesDto;
	}

	public void setCategoriesDto(List<CategoriesDTO> categoriesDto) {
		this.categoriesDto = categoriesDto;
	}

	@PostConstruct
    public void init() {
		try {
			root = new DefaultTreeNode(new CategoriesDTO(), null);
			categoriesDto=categoryService.getCategories();
			createRootNodes(root, null);
			
		} catch (CaveatEmptorException e) {
			Constants.getLogger().log( Level.INFO, "Exception in init method from CategoriesBeanTree" ,e.getMessage());	
		}
    }
	
	private void createRootNodes(TreeNode root, Long parentId) {

		ArrayList<CategoriesDTO> children = (ArrayList<CategoriesDTO>)getChildrenForAParrent(parentId);

		for (CategoriesDTO child : children) {
			TreeNode thChild = new DefaultTreeNode(child, root);
			thChild.setParent(root);
			createRootNodes(thChild, child.getCategoryId());

		}

	}
	
	private List<CategoriesDTO> getChildrenForAParrent(Long parentId) {

		ArrayList<CategoriesDTO> children = new ArrayList<>();

		for (CategoriesDTO category : categoriesDto) {

			if (category.getParentId() == parentId) {

				children.add(category);
			}
		}

		return (List<CategoriesDTO>)children;
	}

	public void onNodeSelect(NodeSelectEvent event) {
		event.getTreeNode().getData();
		this.nameCategory = ((CategoriesDTO)event.getTreeNode().getData()).getNameCategory();
		this.description = ((CategoriesDTO)event.getTreeNode().getData()).getDescription();
	}
	
	public void onNodeUnSelect(NodeExpandEvent event) {
		event.getTreeNode().getData();
		this.nameCategory = ((CategoriesDTO)event.getTreeNode().getData()).getNameCategory();
		this.description = ((CategoriesDTO)event.getTreeNode().getData()).getDescription();
	}
	
	public void onNodeCollapse(NodeCollapseEvent event) {
		if (event != null && event.getTreeNode() != null) {
			event.getTreeNode().setExpanded(false);
		}
	}
	public void onNodeExpand(NodeExpandEvent event) {
	    System.out.println("onNodeExpand() " + event + " event " + event.getTreeNode().isExpanded());
	    event.getTreeNode().setExpanded(true);
	}

    
    
}
