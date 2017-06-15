package beans;

import java.util.ArrayList;
import java.util.Collections;
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
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
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
	private List<CategoriesDTO> categoriesDtoList;
	private MenuModel model;
	private DefaultMenuItem itemMenu;
	private boolean disableButtonUpdateOrDelete;
	private boolean readOnly;
	private boolean renderedButtonUpdateOrDelete ;
	private boolean createButtonClicked ;
	private boolean renderedInsert ;
	private boolean disableInsertButton ;
	private boolean showBreadCrumb;
	private boolean showBreadCrumbHiddden;
	private boolean renderedParentText;
	private boolean renderedInsertChild;
	private CategoriesDTO parent;
	private String parentName;
	
	@SuppressWarnings("unused")
	private boolean deleteOption;


	@EJB
	CategoryService categoryService;

	@PostConstruct
	public void init() {
		parent=new CategoriesDTO();
		renderedParentText=false;
		showBreadCrumb=false;
		showBreadCrumbHiddden=true;
		initializeBreadcrumb();
		disableButtonUpdateOrDelete = true;
		readOnly = true;
		categoriesDtoList=new ArrayList<>();
		categoriesDto = new CategoriesDTO();
		categoryTree.init();
	}

	
	public boolean isRenderedInsertChild() {
		return renderedInsertChild;
	}


	public String getParentName() {
		return parentName;
	}


	public boolean isRenderedParentText() {
		return renderedParentText;
	}


	public void setRenderedParentText(boolean renderedParentText) {
		this.renderedParentText = renderedParentText;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	public CategoriesDTO getParent() {
		return parent;
	}


	public void setParent(CategoriesDTO parent) {
		this.parent = parent;
	}


	public void setRenderedInsertChild(boolean renderedInsertChild) {
		this.renderedInsertChild = renderedInsertChild;
	}


	public boolean isShowBreadCrumbHiddden() {
		return showBreadCrumbHiddden;
	}


	public void setShowBreadCrumbHiddden(boolean showBreadCrumbHiddden) {
		this.showBreadCrumbHiddden = showBreadCrumbHiddden;
	}


	public CategoriesDTO getCategoriesDto() {
		return categoriesDto;
	}

	public void setCategoriesDto(CategoriesDTO categoriesDto) {
		this.categoriesDto = categoriesDto;
	}

	public List<CategoriesDTO> getCategoriesDtoList() {
		return categoriesDtoList;
	}

	public boolean isShowBreadCrumb() {
		return showBreadCrumb;
	}

	public void setShowBreadCrumb(boolean showBreadCrumb) {
		this.showBreadCrumb = showBreadCrumb;
	}

	public void setCategoriesDtoList(List<CategoriesDTO> categoriesDtoList) {
		this.categoriesDtoList = categoriesDtoList;
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
	

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public DefaultMenuItem getItemMenu() {
		return itemMenu;
	}

	public void setItemMenu(DefaultMenuItem itemMenu) {
		this.itemMenu = itemMenu;
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
	
	private void initializeBreadcrumb() {
		model = new DefaultMenuModel();
		itemMenu = new DefaultMenuItem("External");
		itemMenu.setUrl("#");
		itemMenu.setIcon("ui-icon-home");
		model.addElement(itemMenu);
	}
	public void addBreadcrumbMenuItem(MenuModel model) {
		if (categoryTree.getSelectedNode() != null) {
			categoriesDtoList = new ArrayList<>();
			getNodeParents(categoryTree.getSelectedNode());
			Collections.reverse(categoriesDtoList);
			for (CategoriesDTO category : categoriesDtoList) {
				addElementToBreadcrumb(category);
			}
			addElementToBreadcrumb((CategoriesDTO)categoryTree.getSelectedNode().getData());
		}
	}

	private void addElementToBreadcrumb(CategoriesDTO category) {

		itemMenu = new DefaultMenuItem(category.getNameCategory());
		itemMenu.setUrl("#");
		itemMenu.setId(category.getNameCategory().toString());
		model.addElement(itemMenu);

	}
	
	public void insertChild() throws CaveatEmptorException {
		FlagsValues(false, true, false, false, false,false);
		parent=new CategoriesDTO();
		try {
			Long maxCategoryId = categoryService.getMaxCategoryId();
			if(IsAnyNodeSelected()==false){
				FlagsValues(true, false, false, false, false ,true);
				FacesMessagesUtil.message_error("Please select a parent category !", "");
				disableButtonUpdateOrDelete=true;
			}else{
				parent=(CategoriesDTO) categoryTree.getSelectedNode().getData();
				FlagsValues(true, false, false, false, false ,true);
				categoriesDto.setParentId(parent.getCategoryId());
				categoriesDto.setCategoryId(maxCategoryId + 1);
					String insertMessage = categoryService.insertCategory(categoriesDto);
					if (insertMessage.equals("categoryExist") || insertMessage.equals("error") || insertMessage.equals("nullValues")) {
						resetValuesChild();
						FacesMessagesUtil.message_error("Category already exist or invalid fields !", "Insert another category");
						FlagsValues(true, false, false, false, false,true);
						disableButtonUpdateOrDelete=true;
						disableInsertButton=true;						
					} else {
						FacesMessagesUtil.message_info("Category was inserted!", "");
						renderedInsertChild=false;
						createButtonClicked=false;
						disableButtonUpdateOrDelete=false;
						categoryTree.init();
						expandNode(categoriesDto);
					}
			}
				
		} catch (CaveatEmptorException e) {
			LoggerUtils.getLogger().log(Level.INFO, "Exception in insert method from ContentCategoryBean",e.getMessage());
		}
	catch (Exception e) {
		FacesMessagesUtil.redirectPage("error.xhtml");
	}
		
	}

	public void insert() throws CaveatEmptorException {
		FlagsValues(false, true, false, false, false,false);
		try {
			Long maxCategoryId = categoryService.getMaxCategoryId();
				categoriesDto.setCategoryId(null);
				categoriesDto.setParentId(1L);
				categoriesDto.setCategoryId(maxCategoryId + 1);			
				String insertMessage = categoryService.insertCategory(categoriesDto);
				if (insertMessage.equals("categoryExist") || insertMessage.equals("error") || insertMessage.equals("nullValues")) {
					resetValues();
					FacesMessagesUtil.message_error("Category already exist or invalid fields !", "");
					FlagsValues(false, true, false, false, false,false);
				} else {
					FacesMessagesUtil.message_info("Category was inserted!", "");
				}
			
		} catch (CaveatEmptorException e) {
			LoggerUtils.getLogger().log(Level.INFO, "Exception in insert method from ContentCategoryBean",e.getMessage());
		}
	catch (Exception e) {
		FacesMessagesUtil.redirectPage("error.xhtml");
	}
		categoryTree.init();
		expandNode(categoriesDto);
	}


	private void getNodeParents(TreeNode selectedNode) {

		CategoriesDTO node = (CategoriesDTO) selectedNode.getData();

		if (node.getParentId() != null) {

			categoriesDtoList.add((CategoriesDTO) selectedNode.getParent().getData());
			getNodeParents(selectedNode.getParent());

		}

	}
	public void cancel() {
		FlagsValues(false, true, false, false, false,false);
		categoryTree.init();
		createButtonClicked=false;
		expandNode(categoriesDto);
	}

	public void FlagsValues(boolean disableButtonUpdateOrDelete, boolean readOnly, boolean renderedButtonUpdateOrDelete,
			boolean disaleInsertButton, boolean renderedInsert,boolean renderedInsertChild) {
		this.disableButtonUpdateOrDelete = disableButtonUpdateOrDelete;
		this.readOnly = readOnly;
		this.renderedButtonUpdateOrDelete = renderedButtonUpdateOrDelete;
		this.disableInsertButton = disaleInsertButton;
		this.renderedInsert = renderedInsert;
		this.renderedInsertChild=renderedInsertChild;
	}

	public void update() throws CaveatEmptorException{
		FlagsValues(false, true, false, false, false, false);
		try {
			 if (categoryService.updateCategory(categoriesDto).equals("categoryNotExist")){
				FacesMessagesUtil.message_error("Category doesn't exist.Choose an existing category for update!", "");
			}else{
				FacesMessagesUtil.message_info("Category " + categoriesDto.getNameCategory() + " updated!", "");
			}
		} catch (CaveatEmptorException e) {
			LoggerUtils.getLogger().log(Level.INFO, "Exception in updateCategory method from ContentCategoryBean", e.getMessage());
		}
		catch (Exception e) {
			FacesMessagesUtil.redirectPage("error.xhtml");
		}
		
		categoryTree.init();
		expandNode(categoriesDto);
	}

	public void updateCategory() {
		FlagsValues(true, false, true, true, false,false);
		expandNode(categoriesDto);
	}

	
public void resetValuesChild() {
		FlagsValues(true, false, false, true, false, true);
		createButtonClicked = true;
		categoryTree.init();
		renderedParentText=false;
		expandNode(categoriesDto);
}

	public void resetValues() {
		
		categoriesDto.setCategoryId(null);
		categoriesDto.setParentId(null);
		categoriesDto.setNameCategory(null);
		categoriesDto.setDescription(null);
		FlagsValues(true, false, false, true, true, false);
		categoryTree.init();
		expandNode(categoriesDto);
		disabledNodesSelected();
	}

	public void removeCategory() throws CaveatEmptorException{
		deleteOption=true;
		try{
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
		}catch (Exception e) {
			FacesMessagesUtil.redirectPage("error.xhtml");
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
	private boolean IsAnyNodeSelected() {
		List<TreeNode> nodes = categoryTree.getChildrenForATreeNode(categoryTree.getRoot());
		boolean selected=false;
		for (TreeNode node : nodes) {
			if( node.isSelected()){
				selected =true;
			}
		}
		return selected;
	}
	
	
	private void disabledNodesSelected() {
		List<TreeNode> nodes = categoryTree.getChildrenForATreeNode(categoryTree.getRoot());
		for (TreeNode node : nodes) {
				node.setSelectable(false);
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
		parent=(CategoriesDTO) categoryTree.getSelectedNode().getData();
		showBreadCrumb=true;
		showBreadCrumbHiddden=false;
		initializeBreadcrumb();
		addBreadcrumbMenuItem(model);	
		if (createButtonClicked) {
			disableButtonUpdateOrDelete = true;
			renderedParentText=true;
			parentName=parent.getNameCategory();
		}else{
			disableButtonUpdateOrDelete = false;
			categoriesDto = (CategoriesDTO) categoryTree.getSelectedNode().getData();
			parent=(CategoriesDTO) categoryTree.getSelectedNode().getData();
		}
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
