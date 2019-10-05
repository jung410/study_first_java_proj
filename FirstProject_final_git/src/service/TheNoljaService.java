package service;

import static service.SequenceControl.gotoMain;
import static service.SequenceControl.isLogin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
//import java.io.BufferedReader;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
//import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.UI;
import vo_Accommodation.Accommodation;
//import vo_Accommodation.Glamping;
//import vo_Accommodation.GuestHouse;
//import vo_Accommodation.Hotel;
//import vo_Accommodation.Pension;
import vo_Reservation.Reservation;
import vo_person.Person;

public class TheNoljaService {
	// ============================================================
	// <Include>
	// ============================================================
	UI ui = new UI();
	Util util = new Util();
	Reservation reserve = new Reservation();
	static List<Person> people = new ArrayList<>();
	public static List<Accommodation> accommodation = new ArrayList<>();
	static List<Reservation> reservations = new ArrayList<>();
	DecimalFormat decFormat = new DecimalFormat("###,###");
	static int loginnum = 0;
	static int select = 0;
	static int log = 0;

	static String memSer = "mem.ser";
	static String resSer = "res.ser";
	static String accSer = "acc.ser";
	String[] tempArr = new String[6];
	Person currentUser = new Person();

	{
		// 숙소 정보 불러오기
		try {
//			BufferedReader bReader = new BufferedReader(new FileReader(new File("Acommodationlist2.csv")));
//			String s = null;
//			while ((s = bReader.readLine()) != null) {
//				tempArr = s.split(",");
//				if (tempArr[0].equals("호텔")) {
//					accommodation.add(new Hotel(accommodation.size() + 1, tempArr[0], tempArr[1].trim(),
//							tempArr[2].trim(), Integer.parseInt(tempArr[3].trim()), Integer.parseInt(tempArr[4].trim()),
//							Integer.parseInt(tempArr[5].trim())));
//				} else if (tempArr[0].equals("펜션")) {
//					accommodation.add(new Pension(accommodation.size() + 1, tempArr[0], tempArr[1].trim(),
//							tempArr[2].trim(), Integer.parseInt(tempArr[3].trim()), Integer.parseInt(tempArr[4].trim()),
//							Integer.parseInt(tempArr[5].trim())));
//				} else if (tempArr[0].equals("게스트하우스")) {
//					accommodation.add(new GuestHouse(accommodation.size() + 1, tempArr[0], tempArr[1].trim(),
//							tempArr[2].trim(), Integer.parseInt(tempArr[3].trim()), Integer.parseInt(tempArr[4].trim()),
//							Integer.parseInt(tempArr[5].trim())));
//				} else if (tempArr[0].equals("글램핑")) {
//					accommodation.add(new Glamping(accommodation.size() + 1, tempArr[0], tempArr[1].trim(),
//							tempArr[2].trim(), Integer.parseInt(tempArr[3].trim()), Integer.parseInt(tempArr[4].trim()),
//							Integer.parseInt(tempArr[5].trim())));
//				}
//			}
//			bReader.close();
//			
//			ObjectOutputStream oosAcc = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(accSer)));
//			oosAcc.writeObject(accommodation);
//			oosAcc.close();
			
			ObjectInputStream oisAcc = new ObjectInputStream(new BufferedInputStream(new FileInputStream(accSer)));
			accommodation = (List<Accommodation>) oisAcc.readObject();
			oisAcc.close();

			ObjectInputStream oisMem = new ObjectInputStream(new BufferedInputStream(new FileInputStream(memSer)));
			people = (List<Person>) oisMem.readObject();
			oisMem.close();

			ObjectInputStream oisRes = new ObjectInputStream(new BufferedInputStream(new FileInputStream(resSer)));
			reservations = (List<Reservation>) oisRes.readObject();
			oisRes.close();

//			people.add(new Person(people.size() + 1, "test", "test", "test", 11, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "wjdalsdud", "123", "정민영", 29, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "skarndtmddus", "123", "남궁승연", 30, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "rladbswo", "123", "김윤재", 28, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "rlawodud", "123", "김재영", 25, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "a", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "b", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "c", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "d", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "e", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "f", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "g", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "h", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "i", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "j", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "k", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "l", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "m", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "n", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "o", "1", "망아지", 10, "010-1234-1234", 1000000));
//			people.add(new Person(people.size() + 1, "p", "1", "망아지", 10, "010-1234-1234", 1000000));
//
//			ObjectOutputStream oosMem = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(memSer)));
//			oosMem.writeObject(people);
//			oosMem.close();
//			
//			ObjectOutputStream oosRes = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(resSer)));
//			oosRes.writeObject(reservations);
//			oosRes.close();
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace(); 
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ============================================================
	// <Field>
	// ============================================================
	Scanner scanner = new Scanner(System.in);

	// ============================================================
	// <Method>
	// function : 5. 로그인 기능구현
	// parameter : void
	// return : void
	// ============================================================
	public void login() {
		while (gotoMain) {
			try {
				ui.MemberLoginUI();
				// for(Person p : people) {
				// System.out.println(p);
				// }
				System.out.print("사용자 ID를 입력하세요 >");
				String inputID = scanner.nextLine();
				if (inputID.equals("9"))
					return;
				// System.out.println(inputID);
				if (!util.checkID(inputID)) {
					System.out.println("<한글입력은 허용하지 않습니다>");
					Thread.sleep(1000);
					continue;
				}
				System.out.print("사용자 PASSWORD를 입력하세요 >");
				String inputPW = scanner.nextLine();
				if (inputPW.equals("9"))
					return;
				// System.out.println(inputPW);

				if (inputID.equals("0") || inputPW.equals("0")) {
					System.exit(0);
				}
				// login 성공시
				boolean check = false;
				for (int i = 0; i < people.size(); i++) {
					if (inputID.equals(people.get(i).getId()) && inputPW.equals(people.get(i).getPassword())) {
						check = true;
						currentUser = people.get(i);
						loginnum = i;
					}
				}
				if (check) {
					ui.MemberLoginfillUI();
					Thread.sleep(1000);
					isLogin = true;
					AfterLogin();
				}else {
					ui.WrongInputUI();
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	// ============================================================
	// <Method>
	// function : 6. 회원가입 기능구현
	// parameter : void
	// return : void
	// ============================================================
	public void regist() {
		while (gotoMain) {
			try {
				ui.MemberRegistUI();
				String memberID;
				String memberPW;
				String memberName;
				int memberYear;
				String memberNo;
				int memberRegMoney;
				boolean hasID;
				boolean nextStep;
				String tmp;
				int input = Integer.parseInt(scanner.nextLine());
				switch (input) {
				case 1:
					while (true) {
						System.out.print("ID를 입력하세요 > ");
						memberID = scanner.nextLine();
						System.out.println();
						hasID = false;
						nextStep = true;
						for (int i = 0; i < people.size(); i++) {
							if (people.get(i).getId().equals(memberID)) {
								System.out.println("<중복된 아이디가 존재합니다>");
								System.out.println("   <다시 입력하여 주세요>");
								hasID = true;
								break;
							} 
						}
						if (hasID) {
							continue;
						} 
						if (nextStep) {
							if (util.checkID(memberID)) {
								System.out.println("<사용 가능한 아이디입니다>");
							} else {
								System.out.println("<영어와 숫자로만 이루어진 아이디를 입력하세요>");
								continue;
							}
						}
						System.out.print("PASSWORD를 입력하세요 > ");
						memberPW = scanner.nextLine();

						System.out.print("이름을 입력하세요 > ");
						memberName = scanner.nextLine();
						if (!util.checkKorean(memberName)) {
							System.out.println("<유효한 한글 이름을 입력해주세요>");
							continue;

						}

						System.out.print("나이를 입력하세요 > ");
//						memberYear = Integer.parseInt(scanner.nextLine());
						tmp = scanner.nextLine();
						if(!util.checkNum(tmp)) {
							System.out.println("<숫자만 입력해주세요>");
							Thread.sleep(1000);
							continue;
						}else {
							memberYear = Integer.parseInt(tmp);
						}
						System.out.print("전화번호를 입력하세요 > ");
						memberNo = scanner.nextLine();
						if (!util.checkNum(memberNo)) {
							System.out.println("<숫자만 입력해주세요>");
							Thread.sleep(1000);
							continue;
						}
						if (!util.checkNumLength(memberNo)) {
							System.out.println("010-XXXX-XXXX와 같이 '-' 을 포함한");
							System.out.println("13자리 핸드폰 번호를 입력해주세요.");
							Thread.sleep(1000);
							continue;
						}
						System.out.print("최초 충전금액을 입력하세요 > ");
//						memberRegMoney = Integer.parseInt(scanner.nextLine());
						tmp = scanner.nextLine();
						if(!util.checkNum(tmp)){
							System.out.println("<정확한 금액을 입력해주세요>");
							Thread.sleep(1000);
							continue;
						} else {
							memberRegMoney = Integer.parseInt(tmp);
						}
						
						people.add(new Person(people.size() + 1, memberID, memberPW, memberName, memberYear, memberNo,
								memberRegMoney));
						saveMember();
						ui.MemberRegistCompleteUI();
						Thread.sleep(1000);
						return;
					}

				case 9:
					gotoMain = false;
					return;
				case 0:
					saveMember();
					saveReservation();
					System.exit(0);
				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	// ============================================================
	// <Method>
	// function : 로그인 후 기능구현
	// parameter : void
	// return : void
	// ============================================================
	public void AfterLogin() {
		while (isLogin) {
			try {
				ui.AfterLogin();
				int input = Integer.parseInt(scanner.nextLine());
				switch (input) {
				case 0:
					saveReservation();
					saveMember();
					System.exit(0);
				case 9:
					gotoMain = false;
					isLogin = false;
					currentUser = null;
					loginnum = 0;
					break;
				case 1:
				case 2:
				case 3:
				case 4:
					gotoMain = true;
					select = input;
					goToLocation();
					break;
				case 5:
					listOneInfo();
					break;
				case 6:
					ModifyMember();
					break;
				case 7:
					withdrawMember();
					break;
				case 8:
					confirmReservation();
					break;
				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	// ============================================================
	// <Method>
	// function : 로그인 후 개인정보 조회
	// parameter : void
	// return : void
	// ============================================================
	public void listOneInfo() {
		while (isLogin) {
			try {
				ui.Title();
				System.out.print(people.get(loginnum));
				for (int i = 0; i < 10; i++) {
					System.out.println();
				}

				ui.TailBasic();
				char input = scanner.nextLine().charAt(0);
				switch (input) {
				case 114:
					return;
				case 109:
					return;
				case 120:
					saveMember();
					saveReservation();
					saveAccommodation();
					System.exit(0);
				default:
					break;
				}
			} catch (Exception e) {

			}
		}
	}

	// ============================================================
	// <Method>
	// function : 로그인 후 개인정보 수정
	// parameter : void
	// return : void
	// ============================================================
	public void ModifyMember() {
		while (isLogin) {
			try {
				ui.MemberModifyUI();
				int input = Integer.parseInt(scanner.nextLine());
				switch (input) {
				case 1:
					System.out.print("수정할 이름을 입력하세요 (현재이름 : " + currentUser.getName() + ") > ");
					Thread.sleep(100);
					String name = scanner.nextLine();
					if (!util.checkKorean(name)) {
						System.out.println("<한글을 입력해주세요>");
						Thread.sleep(1000);
						continue;
					}

					System.out.print("수정할 나이를 입력하세요 (현재나이 : " + currentUser.getAge() + ") > ");
					Thread.sleep(100);
					int age = Integer.parseInt(scanner.nextLine());
					if(!util.checkNum(age)) {
						System.out.println("<정확한 나이를 입력해주세요>");
						Thread.sleep(1000);
						continue;
					}
					System.out.print("수정할 전화번호를 입력하세요 (현재전화번호 : " + currentUser.getPhoneNo() + ") > ");
					Thread.sleep(100);
					String phoneNo = scanner.nextLine();
					if (!util.checkNum(phoneNo)) {
						System.out.println("<숫자만 입력해주세요>");
						Thread.sleep(1000);
						continue;
					}
					if(!util.checkNumLength(phoneNo)) {
						System.out.println("<정확한 핸드폰 번호를 입력해주세요>");
						Thread.sleep(1000);
						continue;
					}
					System.out.print("추가할 금액을 입력하세요 (현재잔액 : " + decFormat.format(currentUser.getAccount()) + ") > ");
					Thread.sleep(100);
					int chargeMoney = Integer.parseInt(scanner.nextLine());
					if(!util.checkNum(chargeMoney)) {
						System.out.println("<정확한 금액을 입력해주세요>");
						Thread.sleep(1000);	
						continue;
					}
					currentUser.setName(name);
					currentUser.setAge(age);
					currentUser.setPhoneNo(phoneNo);
					currentUser.setAccount(currentUser.getAccount() + chargeMoney);
					saveMember();
					ui.MemberModifyCompleteUI();
					Thread.sleep(1000);
					return;
				case 2:
					gotoMain = false;
					return;
				case 0:
					saveMember();
					saveReservation();
					saveAccommodation();
					System.exit(0);
				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	// ============================================================
	// <Method>
	// function : 로그인 후 회원탈퇴
	// parameter : void
	// return : void
	// ============================================================
	public void withdrawMember() {
		List<Integer> num = new ArrayList<>();
		while (isLogin) {
			try {
				ui.WithdrawMemberUI();
				String input = scanner.nextLine();
				if (currentUser.getPassword().equals(input)) {
					for (int i = 0; i < reservations.size(); i++) {
						if (currentUser.getId().equals(reservations.get(i).getPerson().getId())) {
							if (reservations.get(i).getIsSpecBook().equals("O")) {
								for (int j = 0; j < accommodation.size(); j++) {
									if (accommodation.get(j).getName().equals(reservations.get(i).getAccom().getName())) {
										// 방 매출 감소
										accommodation.get(j).setSales(accommodation.get(j).getSales()
												- accommodation.get(j).getPrice()
												- accommodation.get(j).getSpecialPrice());
										// 방 잔여객실 수 증가
										accommodation.get(j).setRemain(accommodation.get(j).getRemain() + 1);
										// 사용자 매출 감소, 잔고 증가
										currentUser.setAccount(currentUser.getAccount() 
												+ reservations.get(i).getAccom().getPrice()
												+ reservations.get(i).getAccom().getSpecialPrice());
										currentUser.setSales(currentUser.getSales()
												- reservations.get(i).getAccom().getPrice()
												- reservations.get(i).getAccom().getSpecialPrice());
									}
								}
								num.add(i);
							} else if (reservations.get(i).getIsSpecBook().equals("X")) {
								for (int j = 0; j < accommodation.size(); j++) {
									if (accommodation.get(j).equals(reservations.get(i).getAccom())) {
										accommodation.get(j).setSales(reservations.get(i).getAccom().getSales()
												- accommodation.get(j).getPrice());
										// 방 잔여객실 수 증가
										accommodation.get(j).setRemain(accommodation.get(j).getRemain() + 1);
										currentUser
												.setAccount(currentUser.getAccount() + accommodation.get(j).getPrice());
										currentUser.setSales(reservations.get(i).getAccom().getSales()
												- reservations.get(i).getAccom().getPrice());
									}
								}
								num.add(i);
							}
						}
					}
					for (int i = (num.size() - 1); i >= 0; i--) {
						reservations.remove(i);
					}
					 people.remove(loginnum);
					 saveMember();
					 saveReservation();
					 saveAccommodation();
					loginnum = 0;
					gotoMain = false;
					isLogin = false;
					return;
				} else if(!currentUser.getPassword().equals(input)){
					System.out.println("<비밀번호가 일치하지 않습니다>");
					Thread.sleep(1000);
					continue;
				}
				switch (input) {
				case "8":
					return;
				case "9":
					return;
				case "0":
					saveMember();
					saveReservation();
					saveAccommodation();
					System.exit(0);
				default:
					ui.WithdrawMemberFailUI();
					Thread.sleep(1500);
					break;
				}
			} catch (Exception e) {
				System.out.println("<잘못 입력하셨습니다>");
			}
		}
	}

	public void goToLocation() {
		// while 반복문을 사용하는데, 조건은 isLogin이 true이고, gotoMain이 true 일 때
		// 해당 반복문을 실행 할것이다.
		while (isLogin && gotoMain) {
			// try - catch 문으로 예외를 잡을거고
			try {
				// ui 실행하고
				ui.AreaSelectMainUI();
				String input = scanner.nextLine();
				switch (input) {
				case "1":
				case "2":
				case "3":
					getAccom(input);
					break;
				case "m":
					gotoMain = false;
					return;
				case "x":
					saveMember();
					saveReservation();
					saveAccommodation();
					System.exit(0);
				default:
					break;
				}
			} catch (Exception e) {
			}
		}
	}

	private void getAccom(String input) {
		String location = " ";
		String group = " ";
		boolean isRecommend = false;

		try {
			switch (input) {
			case "1":
				location = "서울";
				break;
			case "2":
				location = "경기";
				break;
			case "3":
				location = "강원";
				break;
			default:
				break;
			}

			switch (select) {
			case 1:
				group = "호텔";
				break;
			case 2:
				group = "펜션";
				break;
			case 3:
				group = "게스트하우스";
				break;
			case 4:
				group = "글램핑";
				break;
			default:
				break;
			}

			ui.Title();
			int pageCnt = 0;
			int tempCnt = 0;
			ArrayList<Accommodation> tempList = new ArrayList<>();
			
			for (int i = 0;; i++) {
				if (i == accommodation.size())
					break;
				if (accommodation.get(i).getLocation().equals(location)
						&& accommodation.get(i).getGroup().equals(group)) {
					tempCnt++;
					tempList.add(accommodation.get(i).clone());
					tempList.get(tempCnt - 1).setNo(tempCnt);
				}
			}

			for (int i = 0; i < 10; i++) {
				System.out.print(tempList.get(i));
			}

			ui.TailSortData();
			System.out.println("예약 하실려면 0을 입력해주세요.");
			while (isLogin) {
				String tmp = scanner.nextLine();
				switch (tmp) {
				case "a":
					isRecommend = false;
					ui.Title();
					pageCnt = 0;
					for (int i = 0; i < 10; i++) {
						System.out.print(util.getPossibleReservation(accommodation, group, location).get(i));
					}
					tempList = util.getPossibleReservation(accommodation, group, location);
					tempCnt = tempList.size();

					ui.TailSortData();
					System.out.println("예약 하실려면 0을 입력해주세요.");
					break;
				case "b":
					isRecommend = false;
					ui.Title();
					pageCnt = 0;
					for (int i = 0; i < 10; i++) {
						System.out.print(util.sortRemain(accommodation, group, location).get(i));
					}
					tempList = util.sortRemain(accommodation, group, location);
					tempCnt = tempList.size();
					ui.TailSortData();
					System.out.println("예약 하실려면 0을 입력해주세요.");
					break;
				case "c":
					isRecommend = false;
					ui.Title();
					pageCnt = 0;
					for (int i = 0; i < 10; i++) {
						System.out.print(util.sortPrice(accommodation, group, location, "오름차순").get(i));
					}
					tempList = util.sortPrice(accommodation, group, location, "오름차순");
					tempCnt = tempList.size();
					ui.TailSortData();
					System.out.println("예약 하실려면 0을 입력해주세요.");
					break;

				case "d":
					isRecommend = false;
					ui.Title();
					pageCnt = 0;
					for (int i = 0; i < 10; i++) {
						System.out.print(util.sortPrice(accommodation, group, location, "내림차순").get(i));
					}
					tempList = util.sortPrice(accommodation, group, location, "내림차순");
					tempCnt = tempList.size();
					ui.TailSortData();
					System.out.println("예약 하실려면 0을 입력해주세요.");
					break;

				case "<":
					if (!isRecommend) {
						ui.Title();

						pageCnt--;

						if (pageCnt < 0) {
							pageCnt = 0;
						}

						for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
							try {
								System.out.print(tempList.get(i));
							} catch (Exception e) {
								break;
							}
						}
						if (pageCnt == (tempCnt) / 10) {
							for (int j = 0; j < 10 - tempCnt % 10; j++) {
								System.out.println();
								System.out.println();
							}
						}
						ui.TailSortData();
					}
					System.out.println("예약 하실려면 0을 입력해주세요.");
					break;
				case ">":
					if (!isRecommend) {
						ui.Title();

						if ((tempCnt - 1) / 10 > pageCnt) {
							pageCnt++;
							for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
								try {
									System.out.print(tempList.get(i));
								} catch (Exception e) {
									break;
								}
							}
							if (pageCnt == (tempCnt) / 10) {
								for (int i = 0; i < 10 - tempCnt % 10; i++) {
									System.out.println();
									System.out.println();
								}
							}
						} else {
							pageCnt = (tempCnt - 1) / 10 + 1;
							for (int i = 0; i < 9; i++) {
								System.out.println();
							}
							System.out.println("                                  <더이상 표시할 page가 없습니다>");
							for (int i = 0; i < 10; i++) {
								System.out.println();
							}
						}
						ui.TailSortData();
					}
					System.out.println("예약 하실려면 0을 입력해주세요.");
					break;
				case "z":
					isRecommend = true;
					ui.Title();
					ArrayList<Accommodation> tempList2 = new ArrayList<>();

					// recommendReservation에서 Remain 순으로 정렬된 list를 가져와서
					// tempList2에 넣는다.
					tempList2 = util.recommendReservation(tempList);
					int tmpint = 0;
					int[] arr = new int[tempList2.size()];
					for (int i = 0; i < arr.length; i++) {
						arr[i] = tmpint++;
					}

					// 임시로 생성된 배열의 원소들을 list의 index로 사용할 것이며
					// random성을 주기위해 arr의 길이만큼 반복하며 모든 원소를 섞어준다.
					for (int i = 0; i < arr.length; i++) {
						tmpint = 0;
						int tmprdint = (int) (Math.random() * arr.length);
						tmpint = arr[i];
						arr[i] = arr[tmprdint];
						arr[tmprdint] = tmpint;
					}
					for (int i = 0; i < 2; i++) {
						System.out.println();
					}
					// 그 중 5개만 뽑아내어 출력한다.
					for (int i = 0; i < 5; i++) {
						tempList2.get(arr[i]).setNo(i + 1);
						System.out.println(tempList2.get(arr[i]));
					}
					for (int i = 0; i < 3; i++) {
						System.out.println();
					}
					ui.TailSortData();
					System.out.println("예약 하실려면 0을 입력해주세요.");
					break;
				case "0":
					if(reserve.reserve(reservations, accommodation, tempList, currentUser)) {
						saveAccommodation();
					}

					ui.Title();

					for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
						try {
							System.out.print(tempList.get(i));
						} catch (Exception e) {
							break;
						}
					}
					if (pageCnt == (tempCnt) / 10) {
						for (int i = 0; i < 10 - tempCnt % 10; i++) {
							System.out.println();
							System.out.println();
						}
					}

					ui.TailSortData();
					System.out.println("예약 하실려면 0을 입력해주세요.");
					break;
				case "r":
					return;
				case "m":
					gotoMain = false;
					return;
				case "x":
					saveMember();
					saveReservation();
					saveAccommodation();
					System.exit(0);
				default:
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void confirmReservation() {
		try {
			int tmpcnt = 0;
			int tmpcnt2 = -1;
			List<Reservation> res = new ArrayList<>();

			for (int i = 0; i < reservations.size(); i++) {
				if (reservations.get(i).getPerson().getId().equals(currentUser.getId())) {
					res.add(reservations.get(i));
				}
			}
			System.out.println("  [시간]        [번호]             [호텔 이름]             [방 가격]        [추가예약금]");

			int tmp = 20;
			for (int i = 0; i < res.size(); i++) {
				// 예약확인 출력때 "시간 번호 호텔 이름 가격"순으로 이쁘게 출력
				int tmpSpec = res.get(i).getIsSpecBook().equals("O") ? res.get(i).getAccom().getSpecialPrice() : 0;
				System.out.printf(
						"%s %9d             %" + -(tmp - util.getNameLength(res.get(i).getAccom().getName()))
								+ "s       %-10s         %s%n",
						res.get(i).getTime(), (i + 1), res.get(i).getAccom().getName(),
						decFormat.format(res.get(i).getAccom().getPrice()), decFormat.format(tmpSpec));
			}
			if (res.size() != 0) {
				System.out.println("예약을 취소하시려면 1번을 , 메뉴로 돌아가려면 다른키를 입력하세요.");
				String tm = scanner.nextLine();
				if (tm.equals("1")) {
					System.out.println("취소할 숙소를 정해주세요");

					int tm2 = Integer.parseInt(scanner.nextLine());

					for (int i = 0; i < reservations.size(); i++) {
						if (res.get(tm2 - 1).getAccom().getName().equals(reservations.get(i).getAccom().getName())) {
							for(int k = 0; k < tm2-1; k++) {
								if (res.get(k).getAccom().getName().equals(reservations.get(i).getAccom().getName())) {
									tmpcnt++;
								}
							}
							tmpcnt2++;
							if(tmpcnt != tmpcnt2) {
								tmpcnt = 0;
								continue;
							}
							if (reservations.get(i).getIsSpecBook().equals("O")) {
								for (int j = 0; j < accommodation.size(); j++) {
									if (res.get(tm2 - 1).getAccom().getName().equals(accommodation.get(j).getName())) {
										// 잔여 객실수 변경
										accommodation.get(j).setRemain(accommodation.get(j).getRemain() + 1);
										// 매출액 변경
										accommodation.get(j)
												.setSales(accommodation.get(j).getSales()
														- accommodation.get(j).getPrice()
														- reservations.get(i).getAccom().getSpecialPrice());
									}
								}
								// 현재 유저의 잔고 변경
								currentUser
										.setAccount(currentUser.getAccount() + reservations.get(i).getAccom().getPrice()
												+ reservations.get(i).getAccom().getSpecialPrice());
								reservations.remove(i);
								saveAccommodation();
								saveReservation();
								saveMember();
								System.out.println("<<삭제되었습니다>>");
								return;
							} else {
								for (int j = 0; j < accommodation.size(); j++) {
									if (res.get(tm2 - 1).getAccom().getName().equals(accommodation.get(j).getName())) {
										// 잔여 객실수 변경
										accommodation.get(j).setRemain(accommodation.get(j).getRemain() + 1);
										// 매출액 변경
										accommodation.get(j).setSales(
												accommodation.get(j).getSales() - accommodation.get(j).getPrice());
									}
								}
								// 현재 유저의 잔고 변경
								currentUser.setAccount(
										currentUser.getAccount() + reservations.get(i).getAccom().getPrice());

								reservations.remove(i);
								saveAccommodation();
								saveReservation();
								saveMember();
								System.out.println("<<삭제되었습니다>>");
								return;
							}
						}
					}
				} else {
					return;
				}
			} else {
				System.out.println("<예약된 목록이 없습니다>");
				System.out.println(" <메인으로 돌아갑니다>");
			}
			// res.removeAll(reservations);
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	public static void saveMember() {
		try {
			ObjectOutputStream oosMem = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(memSer)));
			oosMem.writeObject(people);
			oosMem.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void saveReservation() {
		try {
			ObjectOutputStream oosMem = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(resSer)));
			oosMem.writeObject(reservations);
			oosMem.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void saveAccommodation() {
		try {
			ObjectOutputStream oosMem = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(accSer)));
			oosMem.writeObject(accommodation);
			oosMem.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}