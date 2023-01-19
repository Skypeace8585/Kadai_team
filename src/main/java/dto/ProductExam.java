package dto;

public class ProductExam {
	private int id;
	private String productName;
	private String productDate;
	private int productId;
	private String note;
	private String createdAt;
	public ProductExam(int id, String productName, String productDate, int productId, String note,
			String createdAt) {
		super();
		this.id = id;
		this.productName = productName;
		this.productDate = productDate;
		this.productId = productId;
		this.note = note;
		this.createdAt = createdAt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}