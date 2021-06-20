package mainviewfile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import basket.BasketController;
import basket.BasketModel;
import data.StoreMenuList;
import member.MemberController;
import member.MemberModel;
import member.Phistory;

// ȭ�鿡 �������� �κ�
public class View {

	public static Scanner sc = new Scanner(System.in);

	MemberController mjoin = new MemberController();
	StoreMenuList smlist = new StoreMenuList();
	MemberModel mem = new MemberModel();

	public void mainView() throws Exception {
		mjoin.load();		// dat ���� �ε��Ͽ� ���
		mjoin.printAll();	// �ε�� dat ���� ���� ���

		while (true) {

			System.out.println("�ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�");
			System.out.println("�ڡڡ�coffee  oder�ڡڡ�");
			System.out.println("�ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�");
			System.out.println("�ڡڡ�1. ȸ������         �ڡڡ�");
			System.out.println("�ڡڡ�2. �α���            �ڡڡ�");
			System.out.println("�ڡڡ�3. ����             �ڡڡ�");
			System.out.println("�ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�");
			System.out.println("�ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�");

			String input = sc.nextLine();
			switch (input) {
			case "1":
				joinMenu();
				break;
			case "2":
				mem = loginMenu(); // ��� ���� ����ϱ� ���� return ����
				subMenu();
				break;
			case "3":
				System.out.println("���α׷��� ����˴ϴ�.");
				return;
			default:
				System.out.println("�߸��Է��ϼ̽��ϴ�.");
				break;
			}
		}

	}

	public void joinMenu() throws Exception {
		mjoin.join();
	}

	public MemberModel loginMenu() throws Exception {
		return mjoin.login();
	}

	public void subMenu() throws Exception {

		int selStore = 0;

		while(true) {
			System.out.println();
			System.out.println("======= 1.���� ����Ʈ  ==========");
			System.out.println("======= 2.���� ���� �̷�   ========");
			System.out.println("======= 3.�� �� �� ��   ===========");
			int selNum = Integer.parseInt(sc.nextLine());

			switch(selNum) {
			case 1: 
				smlist.storeListPrint(); 
				selStore = smlist.storeSelect();
				orderMenu(selStore);
				break;
			case 2:
				myPerchaseHistory();
				break;
			case 3: 
				logout();
				return;
			default: System.out.println("�ش� ��ȣ�� �Է����ּ���");
			}
		}
	}
	
	public void myPerchaseHistory() {
		int sz = mem.plist.size();
		System.out.println(sz);
		for(int i=0; i<mem.plist.size(); i++) {
			System.out.println(i + ". " +mem.plist.get(i).toString());
		}
	}

	// �α� �ƿ�
	public void logout() {
		mem = null;
	}
	// ��ٱ��� ���
	public void insertBasket(BasketController bc1) {
		BasketModel b = new BasketModel();
		smlist.menuListPrint();			// ���� ���� ���
		int selMenu = -1;
		int tempMenu = -1;

		while(true) {
			try {
				if(tempMenu == -1)	{
					System.out.println("�޴� ����>> ");
					tempMenu = sc.nextInt();
					sc.nextLine();	// ���ٿ��� ���� int�� �Է� �޾� ���͸� ������
									// ���ۿ� ����Ű�� ���� �ִµ� �� ����Ű�� ���ۿ��� �����ִ� ������ �Ͽ�
									// ���� �Է¿� ������ ������ �ʰ� ����.
					selMenu = tempMenu;
					
					if((selMenu < 1) || (selMenu > 10)) {
						System.out.println("������ ���� 1~10 ���� �Է����ּ���.");
						tempMenu = -1;	// �ʱ�ȭ.
						continue;
					}
				}
				System.out.println("���� ����>> ");
				int selCnt = sc.nextInt();
				sc.nextLine();

				b = smlist.bc.getObjectIndex(selMenu - 1);
				b.getCount(selCnt); // �������� ����
				bc1.addBasket(b);
				System.out.println("��ٱ��Ͽ� ��ҽ��ϴ�.");
				break;

			}catch(InputMismatchException e) {
				System.out.println("���ڷ� �Է����ּ���");
				sc.nextLine();		// ���� ����ֱ�
			}

		}
	}
	
	
	public void orderMenu(int selStore) throws Exception {


		ArrayList<BasketModel> blist1 = new ArrayList<BasketModel>();
		BasketController bc1 = new BasketController(blist1);
		int selNum;
		int totalPrice = 0;


		while(true) {
			System.out.println();
			System.out.println("=======	1.�ֹ� �߰�  ==========");
			System.out.println("======= 2.�����ϱ�   ==========");
			System.out.println("======= 3.��ٱ��� ��ǰ ���� =====");
			System.out.println("=======	4.���� �޴�  ==========");
			selNum = Integer.parseInt(sc.nextLine());

			switch(selNum) {
			case 1: insertBasket(bc1); break;
			case 2: 
					if(!bc1.bl.isEmpty()) {
						int price = paymentBasket(totalPrice, bc1); 
						//System.out.println(price);
						if(price == -1) break;	// ���� ���
						else {					// ������

							// ���� �̷� ����
							LocalDateTime now = LocalDateTime.now();
							int[] dateInfo = new int[5];
							dateInfo[0] = now.getYear();
							dateInfo[1] = now.getMonthValue();
							dateInfo[2] = now.getDayOfMonth();
							dateInfo[3] = now.getHour();
							dateInfo[4] = now.getMinute();

							addHistory(smlist.SL1[selStore-1],bc1.getObjectIndex(0).getName(), price, dateInfo);
							FileSave.savetoFile();
							/*
							for(int i=0; i<mem.plist.size(); i++) {
								System.out.println(mem.plist.get(i));
							}*/
							// ��ٱ��� ��ü ����, ���� �� �̷� ����������
							deleteBasketAll(bc1);
							return;
						}

					}
					else {
						System.out.println("������ ��ǰ�� �����ϴ�. ��ٱ��ϸ� ä���ּ���");
						break;
					}

			case 3: 
				if(!bc1.bl.isEmpty()) {		// ��ٱ��� ��ǰ ����
					deleteBasket(bc1);
				}
				else {
					System.out.println("������ ��ǰ�� �����ϴ�. ��ٱ��ϰ� ����ֽ��ϴ�.");
				}
				break;
			case 4: return;
			default: System.out.println("�Է°��� '1~4'�� ���ּ���");
			}
		}


	}

	public void basketInfo(BasketController bc1) {
		System.out.println("<< ��ٱ��� ���� >> ");
		for (int i = 0; i < bc1.bl.size(); i++) {
			System.out.println(i +". " + bc1.getObjectIndex(i).printBasketInfo());
		}
	}
	public void deleteBasketAll(BasketController bc1) {
		int cnt = 0;
		int size = bc1.bl.size();
		while(cnt < size) {
			bc1.removeBasket(bc1.getObjectIndex(0));
			cnt++;
		}
	}

	public void deleteBasket(BasketController bc1) {
		BasketModel b = new BasketModel();
		basketInfo(bc1);	// ��ٱ��� ���� �ٽ� �����ֱ�
		String yno = null;
		String selName = null;
		
		System.out.println("��ٱ��� ��ü ��ǰ�� �����Ͻðڽ��ϱ�?(y/n)");
		yno = sc.nextLine();
		if(yno.equals("y")) {
			deleteBasketAll(bc1);
			return;
		}
		else {
			System.out.println("� ��ǰ�� �����Ͻðڽ��ϱ�?");		// ���ڿ� �ٸ��� �Է��ϸ� �ش� ���ᰡ �����ϴٸ� �߰�
			selName = sc.nextLine();
			
			int size = bc1.bl.size();
			int i = 0;
			while(size > i) {
				if(selName.equals(bc1.getObjectIndex(i).getName())) {
					b = bc1.getObjectIndex(i);
					bc1.removeBasket(b);
					break;
				}
				i++;
			}
			basketInfo(bc1);
		}
		
	}

	public int paymentBasket(int totalPrice, BasketController bc1) {
		// ���� ���	
		basketInfo(bc1);	// ��ٱ��� ���� �ٽ� �����ֱ�
		String yno;
		int discountPrice = 0;	// ���� ����
		int useCoupon = 0;		// ���� ��� ����
		int cnt = 0;			// ���� ���� �� ������ ī��Ʈ
		int l = 0;
		boolean flag;	// Ŀ�Ǹ� �Է¿��� ó���� �ʿ�
		
		
		System.out.println("������ ����Ͻðڽ��ϱ�?(y/n)");
		yno = sc.nextLine();
		
		if (yno.equals("y") && mem.stamp >= 10) {
			int couponNum = mem.stamp / 10;
			System.out.println("���� " + couponNum + "�� �ֽ��ϴ�.");
			System.out.println("�� �� ����Ͻðڽ��ϱ�?");
			useCoupon = Integer.parseInt(sc.nextLine());
			cnt = useCoupon;
			
			while(useCoupon > l) {
				flag = false;
				// ���� ������ŭ ���ϴ� ���� ����
				System.out.println("Ŀ�Ǹ�: ");
				String drinkName = sc.nextLine();
				for(int k=0; k<bc1.bl.size(); k++) {	// Ŀ�Ǹ� �Է� ���� �˻�
					if (drinkName.equals(bc1.getObjectIndex(k).getName())) {
						flag = true;					// �Է� ����
						break;
					}
				}
				if(!flag) {		// Ŀ�Ǹ� �Է� ����
					System.out.println("�Է� ����! Ŀ�Ǹ��� �ٽ� �Է����ּ���.");
					continue;						
				}
				System.out.println("�ش� Ŀ�� ���� ��� ����: ");
				int drinkCount = Integer.parseInt(sc.nextLine());
				if(drinkCount > cnt) {
					System.out.println("����Ϸ���(�����ִ�) ���� ���� ("+cnt+"��)���� ���� �Է��Ͽ����ϴ�. Ŀ�Ǹ���� �ٽ� �Է����ּ���.");
					continue;
				}

				for (int j = 0; j < bc1.bl.size(); j++) { // ��ٱ��Ͽ� ���� ��ǰ �� search
					
					if (drinkName.equals(bc1.getObjectIndex(j).getName())) {
						discountPrice += drinkCount * (bc1.getObjectIndex(j).getPrice());
						cnt -= drinkCount*1;	// ����Ϸ��� ���� ������ �°� ������ ����ϴ��� Ȯ���ϱ� ����
						l += drinkCount*1;		// while�� ������ ���� ��ġ
					}
				}

			}
			//mem.stamp = mem.stamp - (useCoupon * 10); // (���� ����*10)�� ��ŭ ������ �� ����
		} else if(yno.equals("yes") && mem.stamp < 10){
			System.out.println("�������� 10�� �̸�! ���� ��� �Ұ�!");
		} 

		// ����
		System.out.println("�����Ͻðڽ��ϱ�?(y/n)");
		yno = sc.nextLine();

		if (yno.equals("y")) {
			mem.stamp = mem.stamp - (useCoupon * 10); // ���� ������ (���� ����*10)�� ��ŭ ������ �� ����
			LocalDateTime nowTime = LocalDateTime.now();					
			int strDateTime = nowTime.getHour();					
			int timediscount = 0;					
			if(strDateTime>=9&&strDateTime<=11) {
				
					timediscount = 500;	
			}
			
			totalPrice = bc1.payment() - discountPrice - timediscount;	
			if(totalPrice <= 0) {
				timediscount = 0;
				totalPrice = 0;
			}
			System.out.println("���� �� �ݾ� >> " + bc1.payment() + "��");					
			System.out.printf("���� �ݾ� >> %d��\n",(discountPrice + timediscount));					
			System.out.println("���� �ݾ� >> " + totalPrice + "��");	
			if(timediscount > 0) {
				System.out.println("!!Ÿ�Ӽ��Ͻð�!!�Դϴ�.500�����ε˴ϴ�.");
			}
			for (int i = 0; i < bc1.bl.size(); i++) {					
				mem.stamp += bc1.getObjectIndex(i).getCount(); // �ֹ� ��ǰ ���� ��ŭ ������ �߰�				
			}					
			mem.stamp = mem.stamp - useCoupon; // ���� ��뿡�� ������ �߰� ���� �Ұ�					
			//System.out.println(mem.stamp);					
		} else {						
			System.out.println("������ ����ϼ̽��ϴ�.");
			totalPrice = -1;
		}						
		return totalPrice;						
	}

	public void addHistory(String selStore,String menuName,int price,int[] dateInfo) {
		
		Phistory phis = new Phistory();
		phis.setStoreName(selStore);
		phis.setMenuName(menuName);		// ��ٱ��Ͽ� ù��°�� ���� Ŀ�Ǹ� ��ǥ�� ����
		phis.setPrice(price);			// ���� �Ѿ�
		phis.setDate(dateInfo);
		mem.plist.add(phis);
		

		
	}
}