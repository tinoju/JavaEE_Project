package com.example.SpringBootSecurityJDBC.model;

public class Category {	//The Category class represents for a record in the  CATEGORY table of database.
	
	private Long menuId;
    private String menuName;
    private String menuDescription;
    private String menuLink;
    private String roleName;

    public Category() {
		// TODO Auto-generated constructor stub
	}
    
    public Category(Long menuId, String menuName, String menuDescription, String menuLink, String roleName) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuDescription = menuDescription;
		this.menuLink = menuLink;
		this.roleName = roleName;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	public String getMenuLink() {
		return menuLink;
	}

	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Category [menuId=" + menuId + ", menuName=" + menuName + ", menuDescription=" + menuDescription
				+ ", menuLink=" + menuLink + ", roleName=" + roleName + "]";
	}

    
	
}
