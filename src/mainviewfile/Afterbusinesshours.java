package mainviewfile;

import java.time.LocalDateTime;					

				

public class Afterbusinesshours {					

	void AbHour() {				
		LocalDateTime nowTime = LocalDateTime.now();				
		int strDateTime = nowTime.getHour();				
		if(strDateTime<9||strDateTime>21) {				
			System.out.println("*****영업시간이 아닙니다.****");			
			System.out.println("***** 영    업   시    간    ****");			
			System.out.println("*****09:00 ~ 21:00****");			
			System.out.println("메뉴 1:에스프레소              1500");			
			System.out.println("메뉴 2:아메리카노              1500");				
			System.out.println("메뉴 3:카푸치노                 2500");				
			System.out.println("메뉴 4:카페라떼                 2500");					
			System.out.println("메뉴 5:바닐라라떼              2800");					
			System.out.println("메뉴 6:헤이즐넛라떼           2800");					
			System.out.println("메뉴 7:카라멜마끼아또        3000");					
			System.out.println("메뉴 8:카페모카                 3000");					
			System.out.println("메뉴 9:카라멜모카              3300");				
			System.out.println("메뉴 10:화이트초콜렛모카   3500");				
			System.out.println("***** 영    업   시    간    ****");				
			System.out.println("*****09:00 ~ 21:00****");			
			System.out.println("*****영업시간이 아닙니다.****");				
			System.exit(0);				

		}				
	}					
}					

