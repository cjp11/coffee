package member;

import java.io.Serializable;
import java.util.ArrayList;
// MemberModel은 직렬화 가능
public class MemberModel implements Serializable{
   public String name;
   public String phoneNumber;
   public String creditCard;		// 신용카드 비번, 신용카드에 얼마 들어있는지(체크카드 처럼) 금액을 변수로 설정했다면 결제시 오류나게끔 추가해도 될듯
   public String id;
   public String password;
   public String myStore;
   public int stamp;
   public ArrayList<Phistory> plist = new ArrayList<Phistory>();
   
   public  MemberModel() {
      
   }
   public MemberModel(String name,String phoneNumber,String creditCard,String id,
         String password,String myStore) {
      
      this.name = name;
      this.phoneNumber= phoneNumber;
      this.creditCard = creditCard;
      this.id = id;
      this.password = password;
      this.myStore = myStore;
      
      
   }
   public MemberModel(String id, String password) {
      this.id = id;
      this.password = password;
   
   }
   public String getId() {		
	  return id;	
   }		
   
   public Phistory getObjectIndex(int num) {	// 관리자 접근시 사용
	   return plist.get(num);
   }
   @Override
   public String toString() {
      return "MemberModel[id="+id+" , password="+password+" ,name="+name+" ,phoneNumber="+
               phoneNumber+" , creditCard="+creditCard+", myStore="+myStore+"]";
   }
}