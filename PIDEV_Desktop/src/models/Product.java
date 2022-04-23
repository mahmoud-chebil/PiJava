package models;

import javafx.collections.ObservableList;

public class Product {

	private int id;
	private String name;
	private String price;
	private String description;
	private int category_id;
        private String categorie;
        private String fileName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public Product(int id, String name, String price, String description, int category_id) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.category_id = category_id;
	}
	public Product(String name, String price, String description, int category_id) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.category_id = category_id;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", category_id=" + category_id + "]";
	}

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Product(String name, String description, String price, int category_id, String categorie) {
        
        this.name = name;
        this.price = price;
        this.description = description;
        this.category_id = category_id;
        this.categorie = categorie;
    }
     public Product(int id,String name, String description, String price, int category_id, String categorie) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category_id = category_id;
        this.categorie = categorie;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Product(int id, String name, String price, String description, int category_id, String categorie, String fileName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category_id = category_id;
        this.categorie = categorie;
        this.fileName = fileName;
    }
	
	 public Product(String name, String description, String price, int category_id, String categorie, String fileName) {
        
        this.name = name;
        this.price = price;
        this.description = description;
        this.category_id = category_id;
        this.categorie = categorie;
        this.fileName = fileName;
    }

    public void setItems(ObservableList OL) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getSelectionModel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
