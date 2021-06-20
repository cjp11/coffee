package basket;

public class BasketModel {
	private String name;	// 주문할 음료명
	private int count;		// 주문할 음료 수량
	private int price;		// 잔당 가격
	private int sum;
	
	public BasketModel() {
		
	}
	
	public BasketModel(String name, int price) {
		this.name = name;
		this.price = price;
		//sum = getSum();
	}
	
	public int getCount() {	// Overroading
		return count;
	}
	
	public int getCount(int count) {	
		return this.count = count;
	}
	
	public String getName() {
		return name;
	}
	public int getPrice() {		// 쿠폰 사용하는 부분에 필요
		return price;
	}
	
	public int getSum() {		
		return sum = count*price;
	}
	public String toString() {
		return name + " " + price;
	}
	public String printBasketInfo() {
		return name + " 수량:" + count + " 총가격:" + getSum();
	}
}
