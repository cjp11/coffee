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

// 화면에 보여지는 부분
public class View {

	public static Scanner sc = new Scanner(System.in);

	MemberController mjoin = new MemberController();
	StoreMenuList smlist = new StoreMenuList();
	MemberModel mem = new MemberModel();

	public void mainView() throws Exception {
		mjoin.load();		// dat 파일 로드하여 사용
		mjoin.printAll();	// 로드된 dat 파일 정보 출력

		while (true) {

			System.out.println("★★★★★★★★★★★★★★★★★");
			System.out.println("★★★coffee  oder★★★");
			System.out.println("★★★★★★★★★★★★★★★★★");
			System.out.println("★★★1. 회원가입         ★★★");
			System.out.println("★★★2. 로그인            ★★★");
			System.out.println("★★★3. 종료             ★★★");
			System.out.println("★★★★★★★★★★★★★★★★★");
			System.out.println("★★★★★★★★★★★★★★★★★");

			String input = sc.nextLine();
			switch (input) {
			case "1":
				joinMenu();
				break;
			case "2":
				mem = loginMenu(); // 멤버 정보 사용하기 위해 return 설정
				subMenu();
				break;
			case "3":
				System.out.println("프로그램이 종료됩니다.");
				return;
			default:
				System.out.println("잘못입력하셨습니다.");
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
			System.out.println("======= 1.지점 리스트  ==========");
			System.out.println("======= 2.나의 구매 이력   ========");
			System.out.println("======= 3.로 그 아 웃   ===========");
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
			default: System.out.println("해당 번호로 입력해주세요");
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

	// 로그 아웃
	public void logout() {
		mem = null;
	}
	// 장바구니 담기
	public void insertBasket(BasketController bc1) {
		BasketModel b = new BasketModel();
		smlist.menuListPrint();			// 음료 정보 출력
		int selMenu = -1;
		int tempMenu = -1;

		while(true) {
			try {
				if(tempMenu == -1)	{
					System.out.println("메뉴 선택>> ");
					tempMenu = sc.nextInt();
					sc.nextLine();	// 윗줄에서 값을 int로 입력 받아 엔터를 누르면
									// 버퍼에 엔터키가 남아 있는데 이 엔터키를 버퍼에서 없애주는 역할을 하여
									// 다음 입력에 문제가 생기지 않게 만듦.
					selMenu = tempMenu;
					
					if((selMenu < 1) || (selMenu > 10)) {
						System.out.println("지점을 숫자 1~10 으로 입력해주세요.");
						tempMenu = -1;	// 초기화.
						continue;
					}
				}
				System.out.println("수량 선택>> ");
				int selCnt = sc.nextInt();
				sc.nextLine();

				b = smlist.bc.getObjectIndex(selMenu - 1);
				b.getCount(selCnt); // 수량정보 저장
				bc1.addBasket(b);
				System.out.println("장바구니에 담았습니다.");
				break;

			}catch(InputMismatchException e) {
				System.out.println("숫자로 입력해주세요");
				sc.nextLine();		// 버퍼 비워주기
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
			System.out.println("=======	1.주문 추가  ==========");
			System.out.println("======= 2.결제하기   ==========");
			System.out.println("======= 3.장바구니 상품 삭제 =====");
			System.out.println("=======	4.이전 메뉴  ==========");
			selNum = Integer.parseInt(sc.nextLine());

			switch(selNum) {
			case 1: insertBasket(bc1); break;
			case 2: 
					if(!bc1.bl.isEmpty()) {
						int price = paymentBasket(totalPrice, bc1); 
						//System.out.println(price);
						if(price == -1) break;	// 결제 취소
						else {					// 결제시

							// 구매 이력 저장
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
							// 장바구니 전체 비우기, 구매 후 이력 저장했으니
							deleteBasketAll(bc1);
							return;
						}

					}
					else {
						System.out.println("결제할 상품이 없습니다. 장바구니를 채워주세요");
						break;
					}

			case 3: 
				if(!bc1.bl.isEmpty()) {		// 장바구니 상품 존재
					deleteBasket(bc1);
				}
				else {
					System.out.println("삭제할 상품이 없습니다. 장바구니가 비어있습니다.");
				}
				break;
			case 4: return;
			default: System.out.println("입력값을 '1~4'로 해주세요");
			}
		}


	}

	public void basketInfo(BasketController bc1) {
		System.out.println("<< 장바구니 정보 >> ");
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
		basketInfo(bc1);	// 장바구니 정보 다시 보여주기
		String yno = null;
		String selName = null;
		
		System.out.println("장바구니 전체 상품을 삭제하시겠습니까?(y/n)");
		yno = sc.nextLine();
		if(yno.equals("y")) {
			deleteBasketAll(bc1);
			return;
		}
		else {
			System.out.println("어떤 상품을 삭제하시겠습니까?");		// 문자열 다르게 입력하면 해당 음료가 없습니다를 추가
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
		// 쿠폰 사용	
		basketInfo(bc1);	// 장바구니 정보 다시 보여주기
		String yno;
		int discountPrice = 0;	// 할인 가격
		int useCoupon = 0;		// 쿠폰 사용 개수
		int cnt = 0;			// 쿠폰 적용 될 때마다 카운트
		int l = 0;
		boolean flag;	// 커피명 입력오류 처리시 필요
		
		
		System.out.println("쿠폰을 사용하시겠습니까?(y/n)");
		yno = sc.nextLine();
		
		if (yno.equals("y") && mem.stamp >= 10) {
			int couponNum = mem.stamp / 10;
			System.out.println("현재 " + couponNum + "장 있습니다.");
			System.out.println("몇 장 사용하시겠습니까?");
			useCoupon = Integer.parseInt(sc.nextLine());
			cnt = useCoupon;
			
			while(useCoupon > l) {
				flag = false;
				// 쿠폰 개수만큼 원하는 음료 선택
				System.out.println("커피명: ");
				String drinkName = sc.nextLine();
				for(int k=0; k<bc1.bl.size(); k++) {	// 커피명 입력 오류 검사
					if (drinkName.equals(bc1.getObjectIndex(k).getName())) {
						flag = true;					// 입력 성공
						break;
					}
				}
				if(!flag) {		// 커피명 입력 오류
					System.out.println("입력 오류! 커피명을 다시 입력해주세요.");
					continue;						
				}
				System.out.println("해당 커피 쿠폰 사용 개수: ");
				int drinkCount = Integer.parseInt(sc.nextLine());
				if(drinkCount > cnt) {
					System.out.println("사용하려는(남아있는) 쿠폰 개수 ("+cnt+"개)보다 많게 입력하였습니다. 커피명부터 다시 입력해주세요.");
					continue;
				}

				for (int j = 0; j < bc1.bl.size(); j++) { // 장바구니에 담은 상품 중 search
					
					if (drinkName.equals(bc1.getObjectIndex(j).getName())) {
						discountPrice += drinkCount * (bc1.getObjectIndex(j).getPrice());
						cnt -= drinkCount*1;	// 사용하려는 쿠폰 개수에 맞게 쿠폰을 사용하는지 확인하기 위함
						l += drinkCount*1;		// while문 나오기 위한 장치
					}
				}

			}
			//mem.stamp = mem.stamp - (useCoupon * 10); // (쿠폰 개수*10)개 만큼 스탬프 수 차감
		} else if(yno.equals("yes") && mem.stamp < 10){
			System.out.println("스탬프가 10개 미만! 쿠폰 사용 불가!");
		} 

		// 결제
		System.out.println("결제하시겠습니까?(y/n)");
		yno = sc.nextLine();

		if (yno.equals("y")) {
			mem.stamp = mem.stamp - (useCoupon * 10); // 구매 결정시 (쿠폰 개수*10)개 만큼 스탬프 수 차감
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
			System.out.println("할인 전 금액 >> " + bc1.payment() + "원");					
			System.out.printf("할인 금액 >> %d원\n",(discountPrice + timediscount));					
			System.out.println("결제 금액 >> " + totalPrice + "원");	
			if(timediscount > 0) {
				System.out.println("!!타임세일시간!!입니다.500원할인됩니다.");
			}
			for (int i = 0; i < bc1.bl.size(); i++) {					
				mem.stamp += bc1.getObjectIndex(i).getCount(); // 주문 상품 수량 만큼 스탬프 추가				
			}					
			mem.stamp = mem.stamp - useCoupon; // 쿠폰 사용에는 스탬프 추가 적용 불가					
			//System.out.println(mem.stamp);					
		} else {						
			System.out.println("결제를 취소하셨습니다.");
			totalPrice = -1;
		}						
		return totalPrice;						
	}

	public void addHistory(String selStore,String menuName,int price,int[] dateInfo) {
		
		Phistory phis = new Phistory();
		phis.setStoreName(selStore);
		phis.setMenuName(menuName);		// 장바구니에 첫번째로 담은 커피명만 대표로 저장
		phis.setPrice(price);			// 결제 총액
		phis.setDate(dateInfo);
		mem.plist.add(phis);
		

		
	}
}