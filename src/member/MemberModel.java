package member;

import java.io.Serializable;
import java.util.ArrayList;
// MemberModel�� ����ȭ ����
public class MemberModel implements Serializable{
   public String name;
   public String phoneNumber;
   public String creditCard;		// �ſ�ī�� ���, �ſ�ī�忡 �� ����ִ���(üũī�� ó��) �ݾ��� ������ �����ߴٸ� ������ �������Բ� �߰��ص� �ɵ�
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
   
   public Phistory getObjectIndex(int num) {	// ������ ���ٽ� ���
	   return plist.get(num);
   }
   @Override
   public String toString() {
      return "MemberModel[id="+id+" , password="+password+" ,name="+name+" ,phoneNumber="+
               phoneNumber+" , creditCard="+creditCard+", myStore="+myStore+"]";
   }
}