package basket;

import java.util.ArrayList;



public class BasketController {
	public ArrayList<BasketModel> bl; /* = new ArrayList<BasketModel>();*/	// ��ٱ��� ���
	
	public BasketController(ArrayList<BasketModel> list) {
		this.bl = list;
	}
	
	public void addBasket(BasketModel b) {			//��ٱ��Ͽ� �߰�
		bl.add(b);
	}
	public void removeBasket(BasketModel b) {		//��ٱ��Ͽ��� ����
		bl.remove(b);								//ArrayList���� �ν��Ͻ� ������,index ���ġ ��.(�����)
													//����, for�� ���� �ش� index�� ������ �����ϸ� �ȵ�.
		
	}
	
	public int payment() {
		int sum = 0;
		for(int i=0; i<bl.size(); i++) {
			sum += bl.get(i).getSum();
		}
		return sum;
	}
	
	public BasketModel getObjectIndex(int num)  { //��üindex ã��
		return bl.get(num);
	}
}
