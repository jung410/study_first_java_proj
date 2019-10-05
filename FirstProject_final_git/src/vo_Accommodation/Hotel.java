package vo_Accommodation;

import java.util.Scanner;

import service.Util;

public class Hotel extends Accommodation{
	// ============================================================
	// 			 				 <Field>
	// ============================================================
//	private static final long serialVersionUID = 6;
	
	Util util = new Util();
	public Hotel() {}

	public Hotel(int no, String group, String name, String location, int remain, int price, int sales) {
		super(no, group, name, location, remain, price, sales);
		super.specialPrice = 35000;
	}

	public int price() {
		return price;
	}
	
	@Override
	public String toString() {
		int a = 20;
		return String.format("             -------------------------------------------------------------------%n" 
				+ "              %3d       %" + -(a-util.getNameLength(name)) + "s       %4s      %2d      %8s%n"
				, no, name, location, remain, util.toDecFormat(price));
	}
	
	@Override
	public int specialBook() {
		while(true) {
			int tmp;
			System.out.println("조식을 예약하시겠습니까?(가격은 " + util.toDecFormat(specialPrice) + "원 입니다)> 1.예약한다 2.안한다");
			Scanner scanner = new Scanner(System.in);
			tmp = Integer.parseInt(scanner.nextLine());
			if(tmp == 1) {
				System.out.println("<<총 가격은" + util.toDecFormat((price + specialPrice)) + "원 입니다>>");
				return specialPrice;
			}
			
			else if(tmp == 2){
				System.out.println("<<총 가격은" + util.toDecFormat(price) + "원 입니다>>");
				return 0;
			}
			
			else {
				System.out.println("<다시 입력하세요>");
			}
		}
		
	}
	
	// ============================================================
	// 						  <Constructor>
	// ============================================================

	// ============================================================
	// 					     <Getter / Setter>
	// ============================================================

	// ============================================================
	// 			  				 <Method>
	// function : 
	// parameter : 
	// return : 
	// ============================================================
}

// 조식신청()
