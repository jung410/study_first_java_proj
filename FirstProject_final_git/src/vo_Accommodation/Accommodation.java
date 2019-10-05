package vo_Accommodation;

import java.io.Serializable;

abstract public class Accommodation implements Serializable, Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2;
	// ============================================================
	// 			 				 <Field>
	// ============================================================
	int no;
	String group;
	String name;
	String location;
	int remain;
	int price;
	int sales;
	int specialPrice;
	public Accommodation() {}
	
	public Accommodation(int no, String group, String name, String location, int remain, int price, int sales) {
		this.no = no;
		this.group = group;
		this.name = name;
		this.location = location;
		this.remain = remain;
		this.price = price;
		this.sales = sales;
		
	}
	

	public int getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(int specialPrice) {
		this.specialPrice = specialPrice;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return String.format("%10s %7s %5s %8d %15d" , group, name, remain, price, sales);
	}
	
	
	public abstract int specialBook();
	
	@Override
	public Accommodation clone() {
		// TODO Auto-generated method stub
		Object object = null;
		try {
			object = super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO: handle exception
		}
		return (Accommodation)object;
	}
	
//	추상클래스
//	시설분류 : 필드 String group
//	상호명 : String name
//	위치 : String location
//	예약가능여부 : boolean reservation availability
//	잔여 객실 : int remain
//	가격 : double price
//	매출 현황 : double sales

}
