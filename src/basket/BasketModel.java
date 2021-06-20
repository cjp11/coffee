package basket;

public class BasketModel {
	private String name;	// �ֹ��� �����
	private int count;		// �ֹ��� ���� ����
	private int price;		// �ܴ� ����
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
	public int getPrice() {		// ���� ����ϴ� �κп� �ʿ�
		return price;
	}
	
	public int getSum() {		
		return sum = count*price;
	}
	public String toString() {
		return name + " " + price;
	}
	public String printBasketInfo() {
		return name + " ����:" + count + " �Ѱ���:" + getSum();
	}
}
