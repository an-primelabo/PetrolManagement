package ah.petrolmanagement.entity;

public class CategoryEntity extends CommonEntity {
	private static final long serialVersionUID = 9204641492959550620L;

	private int id;
	private String categoryName;

	public Integer getId() {
		return id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "CategoryEntity [id=" + id + ", categoryName=" + categoryName + "]";
	}
}