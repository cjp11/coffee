package mainviewfile;

import java.time.LocalDateTime;					

				

public class Afterbusinesshours {					

	void AbHour() {				
		LocalDateTime nowTime = LocalDateTime.now();				
		int strDateTime = nowTime.getHour();				
		if(strDateTime<9||strDateTime>21) {				
			System.out.println("*****�����ð��� �ƴմϴ�.****");			
			System.out.println("***** ��    ��   ��    ��    ****");			
			System.out.println("*****09:00 ~ 21:00****");			
			System.out.println("�޴� 1:����������              1500");			
			System.out.println("�޴� 2:�Ƹ޸�ī��              1500");				
			System.out.println("�޴� 3:īǪġ��                 2500");				
			System.out.println("�޴� 4:ī���                 2500");					
			System.out.println("�޴� 5:�ٴҶ��              2800");					
			System.out.println("�޴� 6:������Ӷ�           2800");					
			System.out.println("�޴� 7:ī��Ḷ���ƶ�        3000");					
			System.out.println("�޴� 8:ī���ī                 3000");					
			System.out.println("�޴� 9:ī����ī              3300");				
			System.out.println("�޴� 10:ȭ��Ʈ���ݷ���ī   3500");				
			System.out.println("***** ��    ��   ��    ��    ****");				
			System.out.println("*****09:00 ~ 21:00****");			
			System.out.println("*****�����ð��� �ƴմϴ�.****");				
			System.exit(0);				

		}				
	}					
}					

