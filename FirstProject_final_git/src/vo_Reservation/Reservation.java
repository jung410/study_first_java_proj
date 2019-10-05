package vo_Reservation;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import service.TheNoljaService;
import service.Util;
import vo_Accommodation.Accommodation;
import vo_person.Person;

public class Reservation implements Serializable{
	
	private static final long serialVersionUID = 3;
	private Accommodation accom;
	private Person person;
	private Date date = new Date();
	private String isSpecBook;
	Util util = new Util();
	SimpleDateFormat format = new SimpleDateFormat("[HH:mm:ss]");

	{
		isSpecBook = "X";
	}
	
	public String getIsSpecBook() {
		return isSpecBook;
	}

	public void setIsSpecBook(String isSpecBook) {
		this.isSpecBook = isSpecBook;
	}

	public Reservation() {
	}

	public Reservation(Accommodation accom, Person person, String isSpecBook) {
		this.accom = accom;
		this.person = person;
		this.isSpecBook = isSpecBook;
	}

	public Accommodation getAccom() {
		return accom;
	}

	public void setAccom(Accommodation accom) {
		this.accom = accom;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getTime() {
		return format.format(date);
	}

	public void setTime(Date date) {
		this.date = date;
	}
	
//	public void writeObject(ObjectOutputStream out, List<Reservation> reservations) throws IOException {
//		out.writeObject(reservations.get(0).getAccom());
//		out.writeObject(reservations.get(0).getPerson());
//		out.defaultWriteObject();
//	}
//
//	public List<Reservation> readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
//		this.accom = (Accommodation)in.readObject();
//		this.person = (Person)in.readObject();
//		in.defaultReadObject();
//		return new ArrayList<>();
//	}

	public boolean reserve(List<Reservation> reservations, List<Accommodation> accommodations,
			List<Accommodation> tempList, Person currentUser) {

		try {
			Scanner scanner = new Scanner(System.in);

			
//			if (checkReservation(reservations, currentUser)) {	//방 하나만 예약하기
//				System.out.println("이미 예약된 방이 있습니다.");
//				Thread.sleep(1000);
//				scanner.close();
//				return false;
//			}
			System.out.println("예약 하실 방의 번호를 입력하세요.");
			int tmpnum = Integer.parseInt(scanner.nextLine());
			if (((long) tmpnum > tempList.size())) {
				System.out.println("올바른 방번호를 입력해주세요.");
				Thread.sleep(1500);
				return false;
			}
			
			System.out.println(tempList.get(tmpnum - 1));
			if (tempList.get(tmpnum - 1).getRemain() == 0) {
				System.out.println("남은 방이 없습니다.");
				System.out.println("다른 방을 선택해주세요");
				Thread.sleep(1500);
				return false;
			}
			System.out.println("이 방을 예약 하시겟습니까??");
			System.out.println("1. YES		2. NO");
			int tmpnum2 = Integer.parseInt(scanner.nextLine());
			if(tmpnum2 == 1) {
				if(currentUser.getAccount() < tempList.get(tmpnum - 1).getPrice()) {
					System.out.println("충전 금액이 부족합니다. 다른 선택을 해주세요.");
					Thread.sleep(1000);
					return false;
				}
				
				int tempSpecial = tempList.get(tmpnum - 1).specialBook();
				if(tempSpecial != 0) {
					isSpecBook = "O";
				} else {
					isSpecBook = "X";
				}
				
				for (int i = 0; i < accommodations.size(); i++) {
					if(accommodations.get(i).getName().equals(tempList.get(tmpnum - 1).getName())) {
						reservations.add(new Reservation(tempList.get(tmpnum - 1), currentUser, isSpecBook));
						
						TheNoljaService.accommodation.get(i).setRemain(TheNoljaService.accommodation.get(i).getRemain() - 1);
						// 매출액 변경
						TheNoljaService.accommodation.get(i).setSales(TheNoljaService.accommodation.get(i).getSales()+TheNoljaService.accommodation.get(i).getPrice()+tempSpecial);
						currentUser.setAccount(currentUser.getAccount() - accommodations.get(i).getPrice() - tempSpecial); //잔고 감소
						currentUser.setSales(currentUser.getSales() + accommodations.get(i).getPrice() + tempSpecial);
						Date nowDate = new Date();
						reservations.get(reservations.size()-1).setTime(nowDate);
						
						TheNoljaService.saveReservation();
						TheNoljaService.saveMember();
						System.out.println(accommodations.get(i));
						Thread.sleep(3000);
						return true;
					}
				}
			} else {
				System.out.println("메뉴로 돌아갑니다.");
				Thread.sleep(1000);
				return false;
			}
			
//			
//			System.out.println("이 방을 예약 하시겟습니까??");
//			System.out.println("1. YES		2. NO");
//			int tmpnum2 = Integer.parseInt(scanner.nextLine());
//			if (tmpnum2 == 1) {
//				reservations.add(new Reservation(tempList.get(tmpnum - 1), currentUser));
//				for (int j = 0; j < accommodations.size(); j++) {
//					if (accommodations.get(j).getName().equals(tempList.get(tmpnum - 1).getName())) {
//						if (currentUser.getAccount() < reservations.get(tmpnum - 1).getAccom().getPrice()) {
//							reservations.remove(reservations.size() - 1);
//							System.out.println("충전된 금액이 모자랍니다. 다른 선택을 해주세요.");
//							Thread.sleep(1000);
//							return false;
//						} else {
//							accommodations.get(j).setRemain(accommodations.get(j).getRemain() - 1); // 숙소 남은 방 하나 줄이기
//							reservations.get(j).setTime(time); // 시간 저장
//							time = format.format(date);
//							reservations.get(j).getPerson().setAccount(reservations.get(j).getPerson().getAccount()
//									- reservations.get(j).getAccom().getPrice()); // 사용자 지갑 강탈
//							reservations.get(j).getAccom().setSales(reservations.get(j).getAccom().getSales()
//									+ reservations.get(j).getAccom().getPrice()); // 매출 증가
//							System.out.println(accommodations.get(j));
//							System.out.println("예약 되었습니다.");
//							Thread.sleep(1000);
//							return true;
//						}
//
//					}
//				}
//			} else {
//				System.out.println("메뉴로 돌아갑니다.");
//				Thread.sleep(1000);
//				return false;
//			}
			// gotoMain = false;
			// }
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean cancelReservation(Person currentUser, List<Reservation> reservations) {
		try {
			Scanner scanner = new Scanner(System.in);
			for (int i = 0; i < reservations.size(); i++) {
				if (reservations.get(i).getPerson().getId().equals(currentUser.getId()))
					System.out.println(reservations.get(i));
			}

			System.out.println("예약을 취소하시겟습니까?");
			System.out.println("1. YES		2. NO");
			int input = Integer.parseInt(scanner.nextLine());
			if (input == 1) {
				for (int i = 0; i < reservations.size(); i++) {
					if(isSpecBook.equals("O")) {
						if (reservations.get(i).getPerson().getId().equals(currentUser.getId())) {
							reservations.get(i).getAccom().setRemain(reservations.get(i).getAccom().getRemain() + 1);
							reservations.get(i).getPerson().setAccount( reservations.get(i).getPerson().getAccount()
									+ reservations.get(i).getAccom().getPrice() + reservations.get(i).getAccom().getSpecialPrice() ); // 사용자 지갑 돌려주기
							reservations.get(i).getAccom().setSales( reservations.get(i).getAccom().getSales()
									- reservations.get(i).getAccom().getPrice() - reservations.get(i).getAccom().getSpecialPrice() ); // 매출 감소

							reservations.remove(i);
							TheNoljaService.saveReservation();
							System.out.println("예약이 취소되었습니다.");
							return true;
						}
					} else {
						if (reservations.get(i).getPerson().getId().equals(currentUser.getId())) {
							reservations.get(i).getAccom().setRemain(reservations.get(i).getAccom().getRemain() + 1);
							reservations.get(i).getPerson().setAccount(reservations.get(i).getPerson().getAccount()
									+ (reservations.get(i).getAccom().getPrice())); // 사용자 지갑 돌려주기
							reservations.get(i).getAccom().setSales((reservations.get(i).getAccom().getSales()
									- reservations.get(i).getAccom().getPrice())); // 매출 감소

							reservations.remove(i);
							TheNoljaService.saveReservation();
							System.out.println("예약이 취소되었습니다.");
							return true;
						}
					}

				}
			} else {
				return false;
			}

		} catch (Exception e) {
		}
		return false;
	}

	public boolean checkReservation(List<Reservation> reservations, Person currentUser) { // 이미 예약 되있다면 예약이 안됨
		try {
			for (int i = 0; i < reservations.size(); i++) {
				if (reservations.get(i).getPerson().getId().equals(currentUser.getId())) {
					return true;
				}
			}
			return false;

		} catch (Exception e) {
			return false;
		}
	}

//	@Override
//	public String toString() {
////		return getTime() + accom;
//		return accom;
//	}

}
