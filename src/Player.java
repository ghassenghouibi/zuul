package src;

import java.util.*;

public class Player {
	
	private String 						name;
	private int 						bagWeight;
	private Room   						location;
	private HashMap<String,Item>		bag;
	
	public Player(String name,int weight,Room actualRoom){
		this.name=name;
		this.bagWeight=weight;
		this.location=actualRoom;
		bag=new HashMap<String,Item>();
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setWeight(int weight) {
		this.bagWeight=weight;
	}
	
	public void setLocation(Room actualRoom) {
		this.location=actualRoom;
	}
	
	public String getName() {
		return this.name;
	}

	public int getWeight() {
		return this.bagWeight;
	}
	
	public Room getLocation() {
		return this.location;
	}
	
	public void addItemToBag(String name,Item item){
		System.out.println(item.getWeight()+" "+getWeight()+" "+(getWeight()-item.getWeight()));
		if(getWeight()-item.getWeight()>0){
			setWeight(getWeight()-item.getWeight());
			if(item.getName()=="cookie")
				setWeight(getWeight()*2);
			bag.put(name,item);
		}else {
			System.out.println("You can't It's too heavy");
		}
	}

	public void removeItemToBag(String name){
		setWeight(getWeight()+bag.get(name).getWeight());
		bag.remove(name);
	}
	
	public String showMyBag() {
	
		return ""+getItemsDescription();
	}
	
	public Item checkItemInTheBag(String name) {
    	if(bag.get(name)==null) {
    		return null;
    	}
    	return bag.get(name);
    }
	
	public StringBuilder getItemsDescription() {
    	StringBuilder returnString=new StringBuilder("Your Bag contain :");
		if(bag.isEmpty()) 
			return returnString.append("Your Bag is empty");
		
		for(Item x : bag.values()) 
			returnString.append(" "+x.getName());
		
		return returnString;
    }
	
}