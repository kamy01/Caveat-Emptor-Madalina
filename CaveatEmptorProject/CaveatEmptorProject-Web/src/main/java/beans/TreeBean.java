package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import com.dtos.CategoriesDTO;
import ServiceInterfaces.CategoryService;
import exceptions.CaveatEmptorException;
import utils.LoggerUtils;


@ManagedBean(name="treeBean")
@SessionScoped
public class TreeBean implements Serializable{

	private static final long serialVersionUID = 4823707193499977530L;
	private TreeNode root;
	private TreeNode selectedNode;
	private List<CategoriesDTO> categoriesDto;
	private CategoriesDTO categoryDto;
	

	@EJB
	CategoryService categoryService;
	
	
	@PostConstruct
    public void init() {
		try {
		CategoriesDTO rootCategory=new CategoriesDTO();
		rootCategory=categoryService.getRootCategory();
		categoriesDto=new ArrayList<>();
		
		root=new DefaultTreeNode(rootCategory,null);
		categoriesDto = categoryService.getChildrenForParent(rootCategory);
		createTree(categoriesDto, root);
	
		} catch (CaveatEmptorException e) {
			LoggerUtils.getLogger().log( Level.INFO, "Exception in init method from TreeBean" ,e.getMessage());	
		}
    }
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
	public List<CategoriesDTO> getCategoriesDto() {
		return categoriesDto;
	}

	public void setCategoriesDto(List<CategoriesDTO> categoriesDto) {
		this.categoriesDto = categoriesDto;
	}


	public CategoriesDTO getCategoryDto() {
		return categoryDto;
	}
	public void setCategoryDto(CategoriesDTO categoryDto) {
		this.categoryDto = categoryDto;
	}
	private void createTree(List<CategoriesDTO> categoriesDto, TreeNode parent) {

		for (CategoriesDTO categoryDTO : categoriesDto) {
			TreeNode node = new DefaultTreeNode(categoryDTO, parent);
			List<CategoriesDTO> children = new ArrayList<CategoriesDTO>();
			try {
				children = categoryService.getChildrenForParent(categoryDTO);
			} catch (CaveatEmptorException e) {
				LoggerUtils.getLogger().log( Level.INFO, "Exception in constructTree method from TreeBean" ,e.getMessage());	
			}
			if (!children.isEmpty())
				createTree(children, node);
		}
	}
	
	public void getNodeParents(TreeNode selectedNode) {

		CategoriesDTO selectedNodeData = (CategoriesDTO) selectedNode.getData();
		if (selectedNodeData.getParentId() != null) {
			getNodeParents(selectedNode.getParent());
		}

	}
	public List<TreeNode> getChildrenForATreeNode(TreeNode parent) {

		List<TreeNode> childrenNodes = new ArrayList<>();
		List<TreeNode> nodes = new ArrayList<>();
		if(parent.getChildCount()>0){
			childrenNodes=parent.getChildren();
			nodes.addAll(childrenNodes);
			for (TreeNode categoryNode : childrenNodes) {
				if(categoryNode.getChildCount()>0){
					nodes.addAll(getChildrenForATreeNode(categoryNode));
				}
			}
		}
		return nodes;
	}


	public List<CategoriesDTO> getChildrenForACategory(TreeNode parent) {
		List<CategoriesDTO> categoriesDto=new ArrayList<>();
		List<TreeNode> children=getChildrenForATreeNode(parent);
		
		for (TreeNode child : children) {
			categoriesDto.add((CategoriesDTO) child.getData());
		}
		return categoriesDto;
	}
	
public List<CategoriesDTO> getCategories() {
	List<CategoriesDTO> categoriesDto = new ArrayList<CategoriesDTO>();
	List<TreeNode> children = new ArrayList<>();
		children.addAll(getChildrenForATreeNode(root));	
		for (TreeNode child : children) {
			categoriesDto.add((CategoriesDTO) child.getData());
		}
		return categoriesDto;
	}


	public void onNodeSelect(NodeSelectEvent event) {
		CategoriesDTO categoriesDto = new CategoriesDTO();
		try {
			categoriesDto = (CategoriesDTO) event.getTreeNode().getData();
			if (event != null && event.getTreeNode() != null) {
				event.getTreeNode().setSelected(true);
			}
		} catch (Exception e) {
			LoggerUtils.getLogger().log( Level.INFO, "Exception in onNodeSelect method from TreeBean for selectedNode: "+ categoriesDto ,e.getMessage());	
		}
	}
	public void onNodeUnselect(NodeUnselectEvent event) {
		if (event != null && event.getTreeNode() != null) {
			event.getTreeNode().setSelected(false);
		}
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
