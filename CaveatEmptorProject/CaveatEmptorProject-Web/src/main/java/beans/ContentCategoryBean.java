package beans;


import java.util.List;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;
import com.dtos.CategoriesDTO;
import ServiceInterfaces.CategoryService;
import exceptions.CaveatEmptorException;
import utils.FacesMessagesUtil;
import utils.LoggerUtils;

@ManagedBean(name = "contentCategory")
@ViewScoped
public class ContentCategoryBean {

	@ManagedProperty("#{treeBean}")
	private TreeBean categoryTree;

	private CategoriesDTO categoriesDto;

	private boolean disableButtonUpdateOrDelete;
	private boolean readOnly;
	private boolean renderedButtonUpdateOrDelete ;
	private boolean createButtonClicked ;
	private boolean renderedInsert ;
	private boolean disableInsertButton ;

	@SuppressWarnings("unused")
	private boolean deleteOption;


	@EJB
	CategoryService categoryService;

	@PostConstruct
	public void init() {
		disableButtonUpdateOrDelete = true;
		readOnly = true;
		categoriesDto = new CategoriesDTO();
		categoryTree.init();
		disableButtonUpdateOrDelete = true;
	}

	public CategoriesDTO getCategoriesDto() {
		return categoriesDto;
	}

	public void setCategoriesDto(CategoriesDTO categoriesDto) {
		this.categoriesDto = categoriesDto;
	}

	public TreeBean getCategoryTree() {
		return categoryTree;
	}

	public void setCategoryTree(TreeBean categoryTree) {
		this.categoryTree = categoryTree;
	}

	public boolean isDisableButtonUpdateOrDelete() {
		return disableButtonUpdateOrDelete;
	}

	public void setDisableButtonUpdateOrDelete(boolean disableButtonUpdateOrDelete) {
		this.disableButtonUpdateOrDelete = disableButtonUpdateOrDelete;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isRenderedButtonUpdateOrDelete() {
		return renderedButtonUpdateOrDelete;
	}

	public void setRenderedButtonUpdateOrDelete(boolean renderedButtonUpdateOrDelete) {
		this.renderedButtonUpdateOrDelete = renderedButtonUpdateOrDelete;
	}

	public boolean isRenderedInsert() {
		return renderedInsert;
	}

	public void setRenderedInsert(boolean renderedInsert) {
		this.renderedInsert = renderedInsert;
	}

	public boolean isDisableInsertButton() {
		return disableInsertButton;
	}

	public void setDisableInsertButton(boolean disableInsertButton) {
		this.disableInsertButton = disableInsertButton;
	}

	

	public void insert() throws CaveatEmptorException {
		FlagsValues(false, true, false, false, false);
		try {
			Long maxCategoryId = categoryService.getMaxCategoryId();
			if (categoriesDto.getCategoryId() == null) {
				categoriesDto.setParentId(1L);
			} else {
				categoriesDto.setParentId(categoriesDto.getCategoryId());
			}
			categoriesDto.setCategoryId(maxCategoryId + 1);
			String insertMessage = categoryService.insertCategory(categoriesDto);
			if (insertMessage.equals("categoryExist") || insertMessage.equals("error") || insertMessage.equals("nullValues")) {
				resetValues();
				FacesMessagesUtil.message_error("Category already exist or invalid fields !", "");
				FlagsValues(false, true, false, false, false);
			} else {
				FacesMessagesUtil.message_info("Category was inserted!", "");
			}
		} catch (CaveatEmptorException e) {
			LoggerUtils.getLogger().log(Level.INFO, "Exception in insert method from ContentCategoryBean",e.getMessage());
		}
		categoryTree.init();
		createButtonClicked = false;
		expandNode(categoriesDto);
	}

	public void cancelUpdate() {
		FlagsValues(false, true, false, false, false);
		categoryTree.init();
		createButtonClicked = false;
		expandNode(categoriesDto);

	}

	public void cancelInsert() {
		FlagsValues(false, true, false, false, false);
		categoryTree.init();
		createButtonClicked = false;
		expandNode(categoriesDto);
	}

	public void FlagsValues(boolean disableButtonUpdateOrDelete, boolean readOnly, boolean renderedButtonUpdateOrDelete,
			boolean disaleInsertButton, boolean renderedInsert) {
		this.disableButtonUpdateOrDelete = disableButtonUpdateOrDelete;
		this.readOnly = readOnly;
		this.renderedButtonUpdateOrDelete = renderedButtonUpdateOrDelete;
		this.disableInsertButton = disaleInsertButton;
		this.renderedInsert = renderedInsert;
	}

	public void update() {
		FlagsValues(false, true, false, false, false);
		try {
			if (categoryService.updateCategory(categoriesDto).equals("categoryNotExist")) {
				FacesMessagesUtil.message_error("Category doesn't exist.Choose an existing category for update!", "");
			}else{
				FacesMessagesUtil.message_info("Category " + categoriesDto.getNameCategory() + " updated!", "");
			}
		} catch (Exception e) {
			LoggerUtils.getLogger().log(Level.INFO, "Exception in updateCategory method from ContentCategoryBean", e.getMessage());
		}
		categoryTree.init();
		createButtonClicked = false;
		expandNode(categoriesDto);
	}

	public void updateCategory() {
		FlagsValues(true, false, true, true, false);
		expandNode(categoriesDto);
	}

	public void resetValues() {
		categoriesDto.setCategoryId(null);
		categoriesDto.setParentId(null);
		categoriesDto.setNameCategory(null);
		categoriesDto.setDescription(null);
		FlagsValues(true, false, false, true, true);
		createButtonClicked = true;
		categoryTree.init();
		expandNode(categoriesDto);
	}

	public void removeCategory() {
		deleteOption=true;
		
		if (categoriesDto.getNameCategory() == null || categoriesDto.getNameCategory().isEmpty()) {
			FacesMessagesUtil.message_info("Empty fields!", "");
		} else {
			categoriesDto = (CategoriesDTO) categoryTree.getSelectedNode().getData();
			if (categoriesDto != null) {
				try {
					RequestContext context = RequestContext.getCurrentInstance();
					context.execute("PF('myDialogDelete').show();");
				} catch (Exception e) {
					LoggerUtils.getLogger().log(Level.INFO, "Exception in removeCategory method from ContentCategoryBean",e.getMessage());
				}
			} else {
				FacesMessagesUtil.message_info("Invalid fields!", "");
			}
		}
	}

	private void expandNode(CategoriesDTO categoriesDto) {
		List<TreeNode> nodes = categoryTree.getChildrenForATreeNode(categoryTree.getRoot());
		for (TreeNode node : nodes) {
			if (((CategoriesDTO) node.getData()).getNameCategory().equalsIgnoreCase(categoriesDto.getNameCategory())) {
				unselectAllNodes();
				selectNode(node);
				break;
			}
		}
	}

	private void unselectAllNodes() {
		List<TreeNode> nodes = categoryTree.getChildrenForATreeNode(categoryTree.getRoot());
		for (TreeNode node : nodes) {
			if (node.isSelected()) {
				node.setSelected(false);
			}
		}
	}
	


	private void selectNode(TreeNode node) {
		categoryTree.setSelectedNode(node);
		expandParent(node);
		node.setSelected(true);
		node.setExpanded(false);
	}

	private void expandParent(TreeNode child) {
		if (child.getParent() != null) {
			child.getParent().setExpanded(true);
			expandParent(child.getParent());
		}
	}

	public void onNodeSelect(NodeSelectEvent event) {
		disableButtonUpdateOrDelete = false;
		if (createButtonClicked) {
			resetValues();
		}
		categoriesDto = (CategoriesDTO) categoryTree.getSelectedNode().getData();
		expandNode(categoriesDto);
	}
	
	
	public void delete() throws CaveatEmptorException{
	
			if (categoryService.deleteCategory(categoriesDto)) {
				FacesMessagesUtil.message_info("Category " + categoriesDto.getNameCategory() + " deleted!", "");
			} else {
				FacesMessagesUtil.message_info("Category wasn't deleted.Try again!", "");
			}
			categoriesDto = new CategoriesDTO();
			categoryTree.init();
			if(categoryTree.getCategoriesDto().isEmpty()){
				disableButtonUpdateOrDelete=true;
			}
			expandNode(categoriesDto);	
	}
	
}
