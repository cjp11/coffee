package data;


import java.util.ArrayList;
import java.util.Scanner;

import basket.BasketController;
import basket.BasketModel;

public class StoreMenuList { 
	  Scanner sc = new Scanner(System.in);
      public String[] SL1 = new String[10];
      public String[] SL2 = new String[10];   
      ArrayList<BasketModel> bsklist = new ArrayList<BasketModel>();
      public BasketController bc = new BasketController(bsklist); 
      public StoreMenuList() {                              
                                              
         SL1[0] = "신논현점";                           
         SL1[1] = "언주점";                           
         SL1[2] = "선정릉점";                           
         SL1[3] = "삼성중앙점";                           
         SL1[4] = "봉은사점";                           
         SL1[5] = "종합운동장점";                           
         SL1[6] = "삼전점 ";                           
         SL1[7] = "석촌고분점";                           
         SL1[8] = "석촌점";                           
         SL1[9] = "송파나루점";      
                                       
         //    지점명/      위치/         운영시간/        전화번호/                              
         SL2[0] = "지점명:신논현점\n위치:신논현역 6번 출구\n운영시간:09:00 ~ 21:00\n전화번호:02-0001-0001";                              
         SL2[1] = "지점명:언주점\n위치:언주역 2번 출구\n운영시간:09:00 ~ 21:00\n전화번호:02-0001-0002";                              
         SL2[2] = "지점명:선정릉점\n위치:선정릉역 1번출구\n운영시간:09:00 ~ 21:00\n전화번호:02-0001-0003";                              
         SL2[3] = "지점명:삼성중앙점\n위치:삼성중앙역 4번 출구\n영업시간:09:00 ~ 21:00\n전화번호:02-0001-0004";                              
         SL2[4] = "지점명:봉은사점\n위치:봉은사역 2번 출구\n영업시간:09:00 ~ 21:00\n전화번호:02-0001-0005";                              
         SL2[5] = "지점명:종합운동장점\n위치:종합운동장 매점\n영업시간:09:00 ~ 21:00\n전화번호:02-0001-0006";                              
         SL2[6] = "지점명:삼전점 \n위치:삼전역 3번 출구\n영업시간:09:00 ~ 21:00\n전화번호:02-0001-0007";                              
         SL2[7] = "지점명:석촌고분점\n위치:석촌고분역 2번 출구\n영업시간:09:00 ~ 21:00\n전화번호:02-0001-0008";                              
         SL2[8] = "지점명:석촌점\n위치:석촌역 2번출구\n영업시간:09:00 ~ 21:00\n전화번호:02-0001-0009";                              
         SL2[9] = "지점명:송파나루점\n위치:송파나루역 역사내\n영업시간:09:00 ~ 21:00\n전화번호:02-0001-0010";
         
        
         
         BasketModel bsk1 = new BasketModel("에스프레소",1500);					
         bc.addBasket(bsk1);					
         BasketModel bsk2 = new BasketModel("아메리카노",1500);					
         bc.addBasket(bsk2);					
         BasketModel bsk3 = new BasketModel("카푸치노",2500);					
         bc.addBasket(bsk3);					
         BasketModel bsk4 = new BasketModel("카페라떼",2500);					
         bc.addBasket(bsk4);					
         BasketModel bsk5 = new BasketModel("바닐라라떼",2800);					
         bc.addBasket(bsk5);					
         BasketModel bsk6 = new BasketModel("헤이즐넛라떼",2800);					
         bc.addBasket(bsk6);					
         BasketModel bsk7 = new BasketModel("카라멜마끼아또",3000);					
         bc.addBasket(bsk7);					
         BasketModel bsk8 = new BasketModel("카페모카",3000);					
         bc.addBasket(bsk8);					
         BasketModel bsk9 = new BasketModel("카라멜모카",3300);					
         bc.addBasket(bsk9);					
         BasketModel bsk10 = new BasketModel("화이트초콜렛모카",3500);					
         bc.addBasket(bsk10);
        
         
      }
      
      public void storeListPrint() {  			// 지점명 출력                         
         for(int i = 0; i < SL1.length; i++) {                           
            System.out.println((i+1) + " " + SL1[i]);                        
         }   
      }
      
      public void menuListPrint() {				// 메뉴 리스트 출력
    	  for(int i=0; i<bsklist.size(); i++) {
    		  System.out.println((i+1) +". " + bc.getObjectIndex(i).toString());
    	  }
      }
      public int storeSelect() {				// 지점 선택
			System.out.println("지점 선택 >> ");
			int selStore = Integer.parseInt(sc.nextLine());
			System.out.println(SL2[selStore - 1]);	// 지점 정보 출력
			return selStore;
      }
      
      
}      