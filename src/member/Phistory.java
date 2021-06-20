package member;

import java.io.Serializable;

public class Phistory implements Serializable{
	private String selStore;
	private String menuName;
	private int price;		// ±¸¸Å ÃÑ¾×
	private int[] dateInfo = new int[5];
	
	public Phistory() {
		
	}
	public void setStoreName(String selStore) {
		this.selStore = selStore;
	}
	
	
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public void setDate(int[] dateInfo) {
		this.dateInfo[0] = dateInfo[0]; 
		this.dateInfo[1] = dateInfo[1]; 
		this.dateInfo[2] = dateInfo[2]; 
		this.dateInfo[3] = dateInfo[3]; 
		this.dateInfo[4] = dateInfo[4]; 
		
	}
	public String toString() {
		return "Phistory [" + "selStore="
				+ selStore + " menuName="+
				menuName + " price=" + price +
				" dateInfo={"+dateInfo[0]+","
				+dateInfo[1]+","+ dateInfo[2]+","
				+dateInfo[3]+","+ dateInfo[4]+"}";
	}
}