package member;

import java.io.File;						
import java.io.FileInputStream;						
import java.io.ObjectInputStream;						
import java.util.ArrayList;						
import java.util.Scanner;

import mainviewfile.FileSave;						

public class MemberController {						
	public static ArrayList<MemberModel> members = new ArrayList<>();					

	Scanner sc = new Scanner(System.in);					
	MemberModel temp = new MemberModel();					

	public MemberController()  {					

	}					

	public void join() throws Exception  {					
		//printAll();				

		System.out.println("ȸ������ �޴��Դϴ�.");	
		String id = null;	
		if(!members.isEmpty()) {

			int result=1;				

			while(result!=0) {				
				System.out.println("id �ߺ� �˻縦 �մϴ� ");			

				System.out.println("����Ͻ� id �Է�: ");
				id = sc.nextLine();	
				for(MemberModel member :members) {			
					//			for(int i=0;i<members.size();i++) {			
							
					if(member.getId().equals(id)) {		
						//				if(((String)members.get(i).id).equals(id)) {		
						//System.out.println("inner1, result == 1");	
						result = 1;	
						break;	
					}else {		
						//System.out.println("inner2, result == 0");	
						result = 0;	

					}		
				}			
				if(result==1) {			
					//System.out.println("result == 1");		
					System.out.println("�̹� ���� ���̵� �Դϴ�.");		
				}else {			
					//System.out.println("result == 0");		
					System.out.println("��밡���� ���̵��Դϴ�.");		
				}			

			}
		}
		System.out.println("id: ");			
		id = sc.nextLine();			

		System.out.println("password: ");				
		String password = sc.nextLine();				
		System.out.println("�̸�: ");				
		String name = sc.nextLine();				
		System.out.println("��ȭ��ȣ: ");				
		String phoneNumber = sc.nextLine();				
		System.out.println("��������(ī���ȣ): ");				
		String creditCard = sc.nextLine();				
		System.out.println("���Ǹ���(������ ����): ");				
		String myStroe = sc.nextLine();				

		MemberModel member =  new MemberModel(name,phoneNumber,creditCard,id,password,myStroe);				

		members.add(member);				

		FileSave.savetoFile();				
	}					
	public MemberModel login() throws Exception {	
		printAll();

		while(true) {
			System.out.println("--�α��θ޴�--");				
			System.out.println("id�� �Է�: ");				
			String inputid = sc.nextLine();				
			System.out.println("password �Է�: ");				
			String inputpassword = sc.nextLine();				
			int result = 0;	
			if(!members.isEmpty()) {
				for(int i=0;i<members.size();i++) {				
					if(members.get(i).id.equals(inputid)) {			
						if(members.get(i).password.equals(inputpassword)) {		
							temp = members.get(i);	
							result = 0;	
							break ;	
						}else {		
							result = 1;
						}	
					}else {			
						result = 1;	
					}			
				}

				if(result ==0) {				
					System.out.println("�α��� �Ǿ����ϴ�.");	
					break;
				}else {				
					System.out.println("��ġ���� �ʽ��ϴ�.");			
				}	
			}else {
				System.out.println("ȸ������ �Ͻñ� �ٶ��ϴ�");
				join();
			}
		}

		return temp;

	}					

	public void load() throws Exception{					

		File file = new File("memberadd.dat");				
		if(!(file.exists()&&file.isFile())) {				
			return;			
		}				
		try (			// close()�� �ڵ����� ó�����ش�.
				
				FileInputStream in = new FileInputStream(file);			
				ObjectInputStream in2 = new ObjectInputStream(in);)
		{
			
			MemberModel member =null;			
			while(true){			
				
				member = (MemberModel)in2.readObject();		// ������ȭ
				members.add(member);		
			}	

		}catch(Exception e) {			
					
		}


	}					




	public void printAll() {					
		for(MemberModel member : members) {				
			System.out.println(member);			
		}				
	}					


}						
