package vo_person;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	// ============================================================
	// 			 				 <Field>
	// ============================================================
	private int no;
	private String id;
	private String password;
	private String name;
	private int age;
	private String phoneNo;
	private int account;
	static DecimalFormat decFormat = new DecimalFormat("###,###");
	private int sales;
	
	// ============================================================
	// 						  <Constructor>
	// ============================================================
	public Person() {};
	public Person(Person p) {
		this.id = p.getId();
		this.password = p.getPassword();
		this.name = p.getName();
	}
	
	public Person(int no, String id, String password, String name, int age, String phoneNo, int account) {
		this.no = no;
		this.id = id;
		this.password = password;
		this.name = name;
		this.age = age;
		this.phoneNo = phoneNo;
		this.account = account;
	}

	public Person(int account) {
		super();
		this.account = account;
	}
	
	
	// ============================================================
	// 					     <Getter / Setter>
	// ============================================================
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public int getAccount() {
		return account;
	}
	
	public void setAccount(int account) {
		this.account = account;
	}
	
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	// ============================================================
	// 			  				 <Method>
	// function : 
	// parameter : 
	// return : 
	// ============================================================
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("                                  이름       :   %s%n%n"
				+ "                                  ID         :   %s%n%n"
				+ "                                  PW         :   ******%n%n"
				+ "                                  나이       :   %d%n%n"
				+ "                                  전화번호   :   %s%n%n"
				+ "                                  잔액       :   %s%n%n"
				, getName(), getId(), getAge(), getPhoneNo(), decFormat.format(account));
	}
	
	// ============================================================
	// 			  				 <Method>
	// function : 
	// parameter : 
	// return : 
	// ============================================================
//	public void setInfo(String id, String password, String name) {
//		this.id = id;
//		this.password = password;
//		this.name = name;
//	};








}
