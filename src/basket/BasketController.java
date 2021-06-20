package basket;

import java.util.ArrayList;



public class BasketController {
	public ArrayList<BasketModel> bl; /* = new ArrayList<BasketModel>();*/	// 장바구니 목록
	
	public BasketController(ArrayList<BasketModel> list) {
		this.bl = list;
	}
	
	public void addBasket(BasketModel b) {			//장바구니에 추가
		bl.add(b);
	}
	public void removeBasket(BasketModel b) {		//장바구니에서 삭제
		bl.remove(b);								//ArrayList에서 인스턴스 삭제시,index 재배치 됨.(당겨짐)
													//따라서, for문 으로 해당 index에 접근해 삭제하면 안됨.
		
	}
	
	public int payment() {
		int sum = 0;
		for(int i=0; i<bl.size(); i++) {
			sum += bl.get(i).getSum();
		}
		return sum;
	}
	
	public BasketModel getObjectIndex(int num)  { //객체index 찾기
		return bl.get(num);
	}
}
