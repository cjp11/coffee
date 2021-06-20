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

		System.out.println("회원가입 메뉴입니다.");	
		String id = null;	
		if(!members.isEmpty()) {

			int result=1;				

			while(result!=0) {				
				System.out.println("id 중복 검사를 합니다 ");			

				System.out.println("사용하실 id 입력: ");
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
					System.out.println("이미 사용된 아이디 입니다.");		
				}else {			
					//System.out.println("result == 0");		
					System.out.println("사용가능한 아이디입니다.");		
				}			

			}
		}
		System.out.println("id: ");			
		id = sc.nextLine();			

		System.out.println("password: ");				
		String password = sc.nextLine();				
		System.out.println("이름: ");				
		String name = sc.nextLine();				
		System.out.println("전화번호: ");				
		String phoneNumber = sc.nextLine();				
		System.out.println("결제정보(카드번호): ");				
		String creditCard = sc.nextLine();				
		System.out.println("나의매장(없으면 없음): ");				
		String myStroe = sc.nextLine();				

		MemberModel member =  new MemberModel(name,phoneNumber,creditCard,id,password,myStroe);				

		members.add(member);				

		FileSave.savetoFile();				
	}					
	public MemberModel login() throws Exception {	
		printAll();

		while(true) {
			System.out.println("--로그인메뉴--");				
			System.out.println("id를 입력: ");				
			String inputid = sc.nextLine();				
			System.out.println("password 입력: ");				
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
					System.out.println("로그인 되었습니다.");	
					break;
				}else {				
					System.out.println("일치하지 않습니다.");			
				}	
			}else {
				System.out.println("회원가입 하시기 바랍니다");
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
		try (			// close()를 자동으로 처리해준다.
				
				FileInputStream in = new FileInputStream(file);			
				ObjectInputStream in2 = new ObjectInputStream(in);)
		{
			
			MemberModel member =null;			
			while(true){			
				
				member = (MemberModel)in2.readObject();		// 역직렬화
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
