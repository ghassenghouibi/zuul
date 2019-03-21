package src;

public class Item {
	
	private String description;
	private int weight;
	private int price;
	
	public Item(String description, int weight,int price) {
		this.description=description;
		this.weight=weight;
		this.price=price;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return this.price;
	}
	
	public void setWeight(int price) {
		this.price = price;
	}
	public int getWeight() {
		return this.price;
	}
	

}
