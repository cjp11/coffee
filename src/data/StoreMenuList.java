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
                                              
         SL1[0] = "�ų�����";                           
         SL1[1] = "������";                           
         SL1[2] = "��������";                           
         SL1[3] = "�Ｚ�߾���";                           
         SL1[4] = "��������";                           
         SL1[5] = "���տ����";                           
         SL1[6] = "������ ";                           
         SL1[7] = "���̰����";                           
         SL1[8] = "������";                           
         SL1[9] = "���ĳ�����";      
                                       
         //    ������/      ��ġ/         ��ð�/        ��ȭ��ȣ/                              
         SL2[0] = "������:�ų�����\n��ġ:�ų����� 6�� �ⱸ\n��ð�:09:00 ~ 21:00\n��ȭ��ȣ:02-0001-0001";                              
         SL2[1] = "������:������\n��ġ:���ֿ� 2�� �ⱸ\n��ð�:09:00 ~ 21:00\n��ȭ��ȣ:02-0001-0002";                              
         SL2[2] = "������:��������\n��ġ:�������� 1���ⱸ\n��ð�:09:00 ~ 21:00\n��ȭ��ȣ:02-0001-0003";                              
         SL2[3] = "������:�Ｚ�߾���\n��ġ:�Ｚ�߾ӿ� 4�� �ⱸ\n�����ð�:09:00 ~ 21:00\n��ȭ��ȣ:02-0001-0004";                              
         SL2[4] = "������:��������\n��ġ:�����翪 2�� �ⱸ\n�����ð�:09:00 ~ 21:00\n��ȭ��ȣ:02-0001-0005";                              
         SL2[5] = "������:���տ����\n��ġ:���տ�� ����\n�����ð�:09:00 ~ 21:00\n��ȭ��ȣ:02-0001-0006";                              
         SL2[6] = "������:������ \n��ġ:������ 3�� �ⱸ\n�����ð�:09:00 ~ 21:00\n��ȭ��ȣ:02-0001-0007";                              
         SL2[7] = "������:���̰����\n��ġ:���̰�п� 2�� �ⱸ\n�����ð�:09:00 ~ 21:00\n��ȭ��ȣ:02-0001-0008";                              
         SL2[8] = "������:������\n��ġ:���̿� 2���ⱸ\n�����ð�:09:00 ~ 21:00\n��ȭ��ȣ:02-0001-0009";                              
         SL2[9] = "������:���ĳ�����\n��ġ:���ĳ��翪 ���系\n�����ð�:09:00 ~ 21:00\n��ȭ��ȣ:02-0001-0010";
         
        
         
         BasketModel bsk1 = new BasketModel("����������",1500);					
         bc.addBasket(bsk1);					
         BasketModel bsk2 = new BasketModel("�Ƹ޸�ī��",1500);					
         bc.addBasket(bsk2);					
         BasketModel bsk3 = new BasketModel("īǪġ��",2500);					
         bc.addBasket(bsk3);					
         BasketModel bsk4 = new BasketModel("ī���",2500);					
         bc.addBasket(bsk4);					
         BasketModel bsk5 = new BasketModel("�ٴҶ��",2800);					
         bc.addBasket(bsk5);					
         BasketModel bsk6 = new BasketModel("������Ӷ�",2800);					
         bc.addBasket(bsk6);					
         BasketModel bsk7 = new BasketModel("ī��Ḷ���ƶ�",3000);					
         bc.addBasket(bsk7);					
         BasketModel bsk8 = new BasketModel("ī���ī",3000);					
         bc.addBasket(bsk8);					
         BasketModel bsk9 = new BasketModel("ī����ī",3300);					
         bc.addBasket(bsk9);					
         BasketModel bsk10 = new BasketModel("ȭ��Ʈ���ݷ���ī",3500);					
         bc.addBasket(bsk10);
        
         
      }
      
      public void storeListPrint() {  			// ������ ���                         
         for(int i = 0; i < SL1.length; i++) {                           
            System.out.println((i+1) + " " + SL1[i]);                        
         }   
      }
      
      public void menuListPrint() {				// �޴� ����Ʈ ���
    	  for(int i=0; i<bsklist.size(); i++) {
    		  System.out.println((i+1) +". " + bc.getObjectIndex(i).toString());
    	  }
      }
      public int storeSelect() {				// ���� ����
			System.out.println("���� ���� >> ");
			int selStore = Integer.parseInt(sc.nextLine());
			System.out.println(SL2[selStore - 1]);	// ���� ���� ���
			return selStore;
      }
      
      
}      