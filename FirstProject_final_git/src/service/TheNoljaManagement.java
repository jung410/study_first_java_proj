package service;

import static service.SequenceControl.gotoMain;
import static service.TheNoljaService.people;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import app.UI;
import vo_Accommodation.Accommodation;

public class TheNoljaManagement {
	// ============================================================
	// 			 				 <Field>
	// ============================================================
	Scanner scanner = new Scanner(System.in);
	UI ui = new UI();
	Util util = new Util();
	DecimalFormat formatter = new DecimalFormat("###,###");
	// ============================================================
	// 						  <Constructor>
	// ============================================================

	// ============================================================
	// 					     <Getter / Setter>
	// ============================================================

	// ============================================================
	// 			  				 <Method>
	// function : Management 로그인 화면
	// parameter : 
	// return : 
	// ============================================================
	public void theNoljaManagementLogin() throws InterruptedException {
		ui.ManagementLoginUI();
		System.out.print("관리자 ID를 입력하세요 > ");
		String inputID = scanner.next();
		System.out.print("관리자 PASSWORD를 입력하세요 > ");
		String inputPW = scanner.next();
		if(inputID.equals("admin") && inputPW.equals("admin")) {
			ui.ManagementPassfillUI();
			Thread.sleep(1000);
			theNoljaManagement();
		}else if(inputID.equals("0") || inputPW.equals("0")) {
			return;
		}
		else {
			ui.WrongInputUI();
			Thread.sleep(1500);
			theNoljaManagementLogin();
		}
	}
	
	// ============================================================
	// 			  				 <Method>
	// function : 관리자 메인화면
	// parameter : 
	// return : 
	// ============================================================
	public void theNoljaManagement() {
		while(gotoMain) {
			try {
				ui.ManagementUI();
				int input = Integer.parseInt(scanner.next());
				switch (input) {
				case 1:
					MemberManage();
					break;
				case 2:
					ReservationManage();
					break;
				case 3:
					TotalSales();
					break;
				case 4:
					CompanySales();
					break;
				case 5:
					CustomSales();
					break;
//				case 6:
//					DaysSales();
//					break;
				case 9:
					return;
				case 0:
					TheNoljaService.saveMember();
					TheNoljaService.saveReservation();
					System.exit(0);
				default:
					break;
				}	
			} catch (Exception e) {
			}
		}
	}
	
	// ============================================================
	// 			  				 <Method>
	// function : 총 매출 선택화면
	// parameter : 
	// return : 
	// ============================================================
	public void TotalSales() {
		while(gotoMain) {
			try {
				ui.ManagementTotalSalesUI();
				int input = Integer.parseInt(scanner.next());
				switch (input) {
				case 1:
					AreaSalesTotal();
					break;
				case 2:
					CategorySalesTotal();
					break;
				case 8:
					return;
				case 9:
					gotoMain = false;
					break;
				case 0:
					TheNoljaService.saveMember();
					TheNoljaService.saveReservation();
					System.exit(0);
				default:
					break;
				}
			} catch (Exception e) {
				
			}
		}
	}
	
	// ============================================================
	// 			  				 <Method>
	// function : 총 매출 선택화면 -> 지역별 선택화면
	// parameter : 
	// return : 
	// ============================================================
	public void AreaSalesTotal() {
		int sum1 = 0;
		int sum2 = 0;
		int sum3 = 0;
		
		ui.TitleManage();
		System.out.println("                                       지역별 총 매출 현황");
		System.out.println();
		System.out.println();
		System.out.println();
		for(int i = 0; i < TheNoljaService.accommodation.size(); i++) {
			if(TheNoljaService.accommodation.get(i).getLocation().equals("서울")) {
				sum1 += TheNoljaService.accommodation.get(i).getSales();
			}
			else if(TheNoljaService.accommodation.get(i).getLocation().equals("경기")) {
				sum2 += TheNoljaService.accommodation.get(i).getSales();
			}
			else if(TheNoljaService.accommodation.get(i).getLocation().equals("강원")) {
				sum3 += TheNoljaService.accommodation.get(i).getSales();
			}
		}
		
		System.out.println("                                     < 서울 지역의 매출 총합 > ");
		System.out.println();
		System.out.printf("                                              %8s%n" ,  formatter.format(sum1));
		System.out.println();
		System.out.println();
		System.out.println("                                     < 경기 지역의 매출 총합 > ");
		System.out.println();
		System.out.printf("                                              %8s%n" ,formatter.format(sum2));
		System.out.println();
		System.out.println();
		System.out.println("                                     < 강원 지역의 매출 총합 > ");
		System.out.println();
		System.out.printf("                                              %8s%n" ,formatter.format(sum3));
		
		ui.TailBasic();
		
		while(gotoMain) {
			try {
				String input = scanner.next();
				switch (input) {
				case "r":
					return;
				case "m":
					gotoMain = false;
					return;
				case "x":
					TheNoljaService.saveMember();
					TheNoljaService.saveReservation();
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
	// 			  				 <Method>
	// function : 총 매출 선택화면 -> 업체별 선택화면
	// parameter : 
	// return : 
	// ============================================================
	public void CompanySalesTotal() {
		int temp = 20;
		ui.TitleManage();
		System.out.println("                                       업체별 총 매출 현황");
		System.out.println();
		System.out.println("                          <번호>          <업체이름>              <매출액>");
		System.out.println();
		for(int i = 0; i < 10; i++) {
			System.out.printf("                          %3d         %"+ -(temp - util.getNameLength((TheNoljaService.accommodation.get(i).getName())))+
					"s    %8s%n%n",TheNoljaService.accommodation.get(i).getNo(),
					TheNoljaService.accommodation.get(i).getName(), formatter.format((int)(TheNoljaService.accommodation.get(i).getSales())));
		}
		
		ui.TailSortSales();
		
		int pageCnt = 0;
		int tempCnt = 0;
		tempCnt = TheNoljaService.accommodation.size();
		

		while(gotoMain) {
			try {
				String input = scanner.next();
				switch (input) {
				case "a":
					ui.Title();
					System.out.println("등록번호 오름차순 출력");
					ui.TailSortData();
					break;
				case "b":
					ui.Title();
					System.out.println("등록번호 내림차순 출력");
					ui.TailSortData();
					break;
				case "c":
					ui.Title();
					System.out.println("매출 오름차순 출력");
					ui.TailSortData();
					break;
				case "d":
					ui.Title();
					System.out.println("매출 내림차순 출력");
					ui.TailSortData();
					break;
				case "<":
					ui.Title();

					pageCnt--;

					if (pageCnt < 0) {
						pageCnt = 0;
					}
					System.out.println("                                       업체별 총 매출 현황");
					System.out.println();
					System.out.println("                          <번호>          <업체이름>              <매출액>");
					System.out.println();
					for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
						try {
							System.out.printf("                           %3d         %"+ -(temp - util.getNameLength((TheNoljaService.accommodation.get(i).getName())))+
									"s    %8s%n%n",TheNoljaService.accommodation.get(i).getNo(),
									TheNoljaService.accommodation.get(i).getName(), formatter.format((int)(TheNoljaService.accommodation.get(i).getSales())));
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
					break;
				case ">":
					ui.Title();
					if ((tempCnt - 1) / 10 > pageCnt) {
						pageCnt++;
						System.out.println("                                       업체별 총 매출 현황");
						System.out.println();
						System.out.println("                          <번호>          <업체이름>              <매출액>");
						System.out.println();
						for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
							try {
								System.out.printf("                           %3d         %"+ -(temp - util.getNameLength((TheNoljaService.accommodation.get(i).getName())))+
										"s    %8s%n%n",TheNoljaService.accommodation.get(i).getNo(),
										TheNoljaService.accommodation.get(i).getName(), formatter.format((int)(TheNoljaService.accommodation.get(i).getSales())));
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

					break;
				case "r":
					return;
				case "m":
					gotoMain = false;
					return;
				case "x":
					TheNoljaService.saveMember();
					TheNoljaService.saveReservation();
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
	// 			  				 <Method>
	// function : 총 매출 선택화면 -> 업종별 선택화면
	// parameter : 
	// return : 
	// ============================================================
	public void CategorySalesTotal() {
		int sum1 = 0;
		int sum2 = 0;
		int sum3 = 0;
		int sum4 = 0;
		
		ui.TitleManage();
		System.out.println("                                        업종별 총 매출 현황");
		System.out.println();
		System.out.println();
		System.out.println();
		for(int i = 0; i < TheNoljaService.accommodation.size(); i++) {
			if(TheNoljaService.accommodation.get(i).getGroup().equals("호텔")) {
				sum1 += TheNoljaService.accommodation.get(i).getSales();
			}
			else if(TheNoljaService.accommodation.get(i).getGroup().equals("게스트하우스")) {
				sum2 += TheNoljaService.accommodation.get(i).getSales();
			}
			else if(TheNoljaService.accommodation.get(i).getGroup().equals("글램핑")) {
				sum3 += TheNoljaService.accommodation.get(i).getSales();
			}
			else if(TheNoljaService.accommodation.get(i).getGroup().equals("펜션")) {
				sum4 += TheNoljaService.accommodation.get(i).getSales();
			}
		}
		
		System.out.println("                                       < 호텔의 매출 총합 > ");
		System.out.println();
		System.out.printf("                                              %8s%n" , formatter.format(sum1));
		System.out.println();
		System.out.println("                                    < 게스트하우스의 매출 총합 > ");
		System.out.println();
		System.out.printf("                                              %8s%n" , formatter.format(sum2));
		System.out.println();
		System.out.println("                                       < 글램핑의 매출 총합 > ");
		System.out.println();
		System.out.printf("                                              %8s%n" , formatter.format(sum3));
		System.out.println();
		System.out.println("                                       < 펜션의 매출 총합 > ");
		System.out.println();
		System.out.printf("                                              %8s%n" , formatter.format(sum4));
		
		ui.TailBasic();
		
		while(gotoMain) {
			try {
				String input = scanner.next();
				switch (input) {
				case "r":
					return;
				case "m":
					gotoMain = false;
					return;
				case "x":
					TheNoljaService.saveMember();
					TheNoljaService.saveReservation();
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
	// 			  				 <Method>
	// function : 업체별 매출 현황
	// parameter : 
	// return : 
	// ============================================================
	public void CompanySales() {
		int temp = 20;
		ui.TitleManage();
		System.out.println("                                       업체별 총 매출 현황");
		System.out.println();
		System.out.println("                          <번호>          <업체이름>              <매출액>");
		System.out.println();
		for(int i = 0; i < 10; i++) {
			System.out.printf("                          %3d         %"+ -(temp - util.getNameLength((TheNoljaService.accommodation.get(i).getName())))+
					"s    %8s%n%n",TheNoljaService.accommodation.get(i).getNo(),
					TheNoljaService.accommodation.get(i).getName(), formatter.format((int)(TheNoljaService.accommodation.get(i).getSales())));
		}
		
		ui.TailSortSales();
		
		int pageCnt = 0;
		int tempCnt = 0;
		tempCnt = TheNoljaService.accommodation.size();
		ArrayList<Accommodation> tempList = new ArrayList<>();
		for(int i = 0; i < tempCnt; i++) {
			tempList.add(TheNoljaService.accommodation.get(i).clone());
		}
		while(gotoMain) {
			try {
				String input = scanner.next();
				switch (input) {
				case "a":
					ui.TitleManage();
					
					System.out.println("                                       업체별 총 매출 현황");
					System.out.println();
					System.out.println("                          <번호>          <업체이름>              <매출액>");
					System.out.println();
					tempList = util.sortNumber(TheNoljaService.accommodation, "번호오름차순");
					tempCnt = tempList.size();
					for(int i = 0; i < 10; i++) {
						System.out.printf("                          %3d         %"+ -(temp - util.getNameLength((tempList.get(i).getName())))+
								"s    %8s%n%n",tempList.get(i).getNo(),
								tempList.get(i).getName(), formatter.format((int)(tempList.get(i).getSales())));
					}
					
					ui.TailSortSales();
					break;
				case "b":
					ui.TitleManage();
					
					System.out.println("                                       업체별 총 매출 현황");
					System.out.println();
					System.out.println("                          <번호>          <업체이름>              <매출액>");
					System.out.println();
					tempList = util.sortNumber(TheNoljaService.accommodation, "번호내림차순");
					tempCnt = tempList.size();
					for(int i = 0; i < 10; i++) {
						System.out.printf("                          %3d         %"+ -(temp - util.getNameLength((tempList.get(i).getName())))+
								"s    %8s%n%n",tempList.get(i).getNo(),
								tempList.get(i).getName(), formatter.format((int)(tempList.get(i).getSales())));
					}
					
					ui.TailSortSales();
					break;
				case "c":
					ui.TitleManage();
					pageCnt = 0;
					
					System.out.println("                                       업체별 총 매출 현황");
					System.out.println();
					System.out.println("                          <번호>          <업체이름>              <매출액>");
					System.out.println();
					tempList = util.sortSales(TheNoljaService.accommodation, "오름차순");
					tempCnt = tempList.size();
					for(int i = 0; i < 10; i++) {
						System.out.printf("                          %3d         %"+ -(temp - util.getNameLength((tempList.get(i).getName())))+
								"s    %8s%n%n",tempList.get(i).getNo(),
								tempList.get(i).getName(), formatter.format((int)(tempList.get(i).getSales())));
					}
					
					ui.TailSortSales();
					break;
				case "d":
					ui.TitleManage();
					pageCnt = 0;
					System.out.println("                                       업체별 총 매출 현황");
					System.out.println();
					System.out.println("                          <번호>          <업체이름>              <매출액>");
					System.out.println();
					tempList = util.sortSales(TheNoljaService.accommodation, "내림차순");
					tempCnt = tempList.size();
					for(int i = 0; i < 10; i++) {
						System.out.printf("                          %3d         %"+ -(temp - util.getNameLength((tempList.get(i).getName())))+
								"s    %8s%n%n",tempList.get(i).getNo(),
								tempList.get(i).getName(), formatter.format((int)(tempList.get(i).getSales())));
					}
					ui.TailSortSales();
					break;
				case "<":
					ui.TitleManage();

					pageCnt--;

					if (pageCnt < 0) {
						pageCnt = 0;
					}
					System.out.println("                                       업체별 총 매출 현황");
					System.out.println();
					System.out.println("                          <번호>          <업체이름>              <매출액>");
					System.out.println();
					for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
						try {
							System.out.printf("                           %3d         %"+ -(temp - util.getNameLength((tempList.get(i).getName())))+
									"s    %8s%n%n",tempList.get(i).getNo(),
									tempList.get(i).getName(), formatter.format((int)(tempList.get(i).getSales())));
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
					ui.TailSortSales();
					break;
				case ">":
					ui.TitleManage();
					if ((tempCnt - 1) / 10 > pageCnt) {
						pageCnt++;
						System.out.println("                                       업체별 총 매출 현황");
						System.out.println();
						System.out.println("                          <번호>          <업체이름>              <매출액>");
						System.out.println();
						for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
							try {
								System.out.printf("                           %3d         %"+ -(temp - util.getNameLength((tempList.get(i).getName())))+
										"s    %8s%n%n",tempList.get(i).getNo(),
										tempList.get(i).getName(), formatter.format((int)(tempList.get(i).getSales())));
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
					ui.TailSortSales();
					break;
				case "r":
					return;
				case "m":
					gotoMain = false;
					return;
				case "x":
					TheNoljaService.saveMember();
					TheNoljaService.saveReservation();
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
	// 			  				 <Method>
	// function : 고객별 매출현황
	// parameter : 
	// return : 
	// ============================================================
	public void CustomSales() {
		int pageCnt = 0;
		int tempCnt = TheNoljaService.people.size();
		int tmp = 10;
		
		ui.TitleManage();
		System.out.println("                                       고객별 매출 현황");
		System.out.println();
		System.out.println();
		System.out.println("                           번호      ID            이름          매출    ");
		System.out.println("                         ----------------------------------------------  ");
		System.out.println();
		for(int i = 0; i < 10;i++) {
			System.out.printf("                          %3d      %-15s %"
					+ -(tmp - util.getNameLength((TheNoljaService.people.get(i).getName()))) +"s %s%n",
					TheNoljaService.people.get(i).getNo(),TheNoljaService.people.get(i).getId(),
					TheNoljaService.people.get(i).getName(),formatter.format(TheNoljaService.people.get(i).getSales()));
			System.out.println();
		}
		ui.TailBasicPage();
		while(gotoMain) {
			try {
				String input = scanner.next();
				switch (input) {
				case "<":
					ui.TitleManage();
					pageCnt--;

					if (pageCnt < 0) {
						pageCnt = 0;
					}
					System.out.println("                           번호      ID            이름          매출    ");
					System.out.println("                         ----------------------------------------------  ");
					System.out.println();
					for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
						try {
							System.out.printf("                          %3d      %-15s %"
									+ -(tmp - util.getNameLength((TheNoljaService.people.get(i).getName()))) +"s %s%n",
									TheNoljaService.people.get(i).getNo(),TheNoljaService.people.get(i).getId(),
									TheNoljaService.people.get(i).getName(),formatter.format(TheNoljaService.people.get(i).getSales()));
							System.out.println();
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
					ui.TailBasicPage();
					break;
				case ">":
					ui.TitleManage();
					if ((tempCnt - 1) / 10 > pageCnt) {
						pageCnt++;
						System.out.println("                           번호      ID            이름          매출    ");
						System.out.println("                         ----------------------------------------------  ");
						System.out.println();
						for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
							try {
								System.out.printf("                          %3d      %-15s %"
										+ -(tmp - util.getNameLength((TheNoljaService.people.get(i).getName()))) +"s %s%n",
										TheNoljaService.people.get(i).getNo(),TheNoljaService.people.get(i).getId(),
										TheNoljaService.people.get(i).getName(),formatter.format(TheNoljaService.people.get(i).getSales()));
								System.out.println();
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
						for (int i = 0; i < 12; i++) {
							System.out.println();
						}
						System.out.println("                                  <더이상 표시할 page가 없습니다>");
						for (int i = 0; i < 11; i++) {
							System.out.println();
						}
					}
					ui.TailBasicPage();
					break;
				case "r":
					return;
				case "m":
					gotoMain = false;
					return;
				case "x":
					TheNoljaService.saveMember();
					TheNoljaService.saveReservation();
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
	// 			  				 <Method>
	// function : 날짜별 매출현황 - 미정
	// parameter : 
	// return : 
	// ============================================================
//	public void DaysSales() {
//		ui.TitleManage();
//		System.out.println("날짜별 매출 현황 목록");
//		ui.TailSortSales();
//		while(gotoMain) {
//			try {
//				String input = scanner.next();
//				switch (input) {
//				case "a":
//					ui.Title();
//					System.out.println("등록번호 오름차순 출력");
//					ui.TailSortData();
//					break;
//				case "b":
//					ui.Title();
//					System.out.println("등록번호 내림차순 출력");
//					ui.TailSortData();
//					break;
//				case "c":
//					ui.Title();
//					System.out.println("매출 오름차순 출력");
//					ui.TailSortData();
//					break;
//				case "d":
//					ui.Title();
//					System.out.println("매출 내림차순 출력");
//					ui.TailSortData();
//					break;
//				case "<":
//					ui.Title();
//					System.out.println("이전 페이지");
//					ui.TailSortData();
//					break;
//				case ">":
//					ui.Title();
//					System.out.println("다음 페이지");
//					ui.TailSortData();
//					break;
//				case "r":
//					return;
//				case "m":
//					gotoMain = false;
//					return;
//				case "x":
//					TheNoljaService.saveMember();
//					TheNoljaService.saveReservation();
//					System.exit(0);
//				default:
//					break;
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//	}
	
	// ============================================================
	// 			  				 <Method>
	// function : 고객관리 기능
	// parameter : 
	// return : 
	// ============================================================
	public void MemberManage() {
		int pageCnt = 0;
		int tempCnt = 0;
		tempCnt = TheNoljaService.people.size();
//		TheNoljaService service = new TheNoljaService();
		int tmp = 10;
		ui.TitleManage();
		System.out.println("                                       고객 정보 관리");
		System.out.println();
		System.out.println();
		System.out.println("             번호      ID           PW       이름     나이     전화번호       잔액");
		System.out.println("             ------------------------------------------------------------------------");
		System.out.println();
		for(int i = 0; i < 10;i++) {
			System.out.printf("            %3d   %-15s ******   %"+ -(tmp - util.getNameLength(TheNoljaService.people.get(i).getName())) +"s %3d   %13s %10s%n",i + 1,
					TheNoljaService.people.get(i).getId()
					,TheNoljaService.people.get(i).getName()
					,TheNoljaService.people.get(i).getAge(),TheNoljaService.people.get(i).getPhoneNo()
					,formatter.format(TheNoljaService.people.get(i).getAccount()));
			System.out.println();
		}
//		System.out.println("Member 목록");
		ui.TailBasicPage();
		System.out.println("수정하시려면 0번을 입력하세요.");
		while(gotoMain) {
			try {
				String input = scanner.next();
				switch (input) {
				case "<":
					ui.TitleManage();
					pageCnt--;

					if (pageCnt < 0) {
						pageCnt = 0;
					}
					System.out.println("             번호      ID           PW       이름     나이     전화번호       잔액");
					System.out.println("             ------------------------------------------------------------------------");
					System.out.println();
					for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
						try {
							System.out.printf("            %3d   %-15s ******   %"+ -(tmp - util.getNameLength(TheNoljaService.people.get(i).getName())) +"s %3d   %13s %10s%n",i + 1,
									TheNoljaService.people.get(i).getId()
									,TheNoljaService.people.get(i).getName()
									,TheNoljaService.people.get(i).getAge(),TheNoljaService.people.get(i).getPhoneNo()
									,formatter.format(TheNoljaService.people.get(i).getAccount()));
							System.out.println();
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
					
					
					ui.TailBasicPage();
					System.out.println("수정하시려면 0번을 입력하세요.");
					break;
				case ">":
					ui.TitleManage();
					if ((tempCnt - 1) / 10 > pageCnt) {
						pageCnt++;
						System.out.println("             번호      ID           PW       이름     나이     전화번호       잔액");
						System.out.println("             ------------------------------------------------------------------------");
						System.out.println();
						for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
							try {
								System.out.printf("            %3d   %-15s ******   %"+ -(tmp - util.getNameLength(TheNoljaService.people.get(i).getName())) +"s %3d   %13s %10s%n",i + 1,
										TheNoljaService.people.get(i).getId()
										,TheNoljaService.people.get(i).getName()
										,TheNoljaService.people.get(i).getAge(),TheNoljaService.people.get(i).getPhoneNo()
										,formatter.format(TheNoljaService.people.get(i).getAccount()));
								System.out.println();
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
						for (int i = 0; i < 11; i++) {
							System.out.println();
						}
						System.out.println("                                  <더이상 표시할 page가 없습니다>");
						for (int i = 0; i < 12; i++) {
							System.out.println();
						}
					}
					ui.TailBasicPage();
					System.out.println("수정하시려면 0번을 입력하세요.");
					break;
				case "0":
					System.out.println("수정할 고객번호를 입력하세요.");
					int num = Integer.parseInt(scanner.next());
					ModifyManager(num);
					
					ui.TitleManage();
					
					System.out.println("             번호      ID           PW       이름     나이     전화번호       잔액");
					System.out.println("             ------------------------------------------------------------------------");
					System.out.println();
					for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
						try {
							System.out.printf("            %3d   %-15s ******   %"+ -(tmp - util.getNameLength(TheNoljaService.people.get(i).getName())) +"s %3d   %13s %10s%n",i + 1,
									TheNoljaService.people.get(i).getId()
									,TheNoljaService.people.get(i).getName()
									,TheNoljaService.people.get(i).getAge(),TheNoljaService.people.get(i).getPhoneNo()
									,formatter.format(TheNoljaService.people.get(i).getAccount()));
							System.out.println();
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
					ui.TailBasicPage();
					System.out.println("수정하시려면 0번을 입력하세요.");
					break;
				case "r":
					return;
				case "m":
					gotoMain = false;
					return;
				case "x":
					TheNoljaService.saveMember();
					TheNoljaService.saveReservation();
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
	// 			  				 <Method>
	// function : 예약 현황 기능
	// parameter : 
	// return : 
	// ============================================================
	public void ReservationManage() {
		int pageCnt = 0;
		int tempCnt = TheNoljaService.reservations.size();
		int tempCnt2 = 0;
		int tmp = 20;
		ui.TitleManage();
		System.out.println("          번호     시간       Id               숙박업체 이름              금액      추가예약" );
		System.out.println("          ----------------------------------------------------------------------------------");
		System.out.println();
		for(int i = 0; i < TheNoljaService.reservations.size();i++) {
			tempCnt2 = i;
			if(i == 10) break;
			int tmpSpec = TheNoljaService.reservations.get(i).getIsSpecBook().equals("O")?TheNoljaService.reservations.get(i).getAccom().getSpecialPrice():0;
			System.out.printf("          %3d    %s   %-8s      %" + -(tmp - util.getNameLength(TheNoljaService.reservations.get(i).getAccom().getName())) +
					"s        %-10s    %s%n", i + 1, TheNoljaService.reservations.get(i).getTime(), 
					TheNoljaService.reservations.get(i).getPerson().getId(), TheNoljaService.reservations.get(i).getAccom().getName(),
					formatter.format(TheNoljaService.reservations.get(i).getAccom().getPrice()) , formatter.format(tmpSpec));
			System.out.println();
		}
		for(int j = 0; j < 9 - tempCnt2 % 10; j++) {
			System.out.println();
			System.out.println();
		}
		ui.TailBasicPage();
		while(gotoMain) {
			try {
				String input = scanner.next();
				switch (input) {
				case "<":
					ui.Title();
					ui.TitleManage();
					pageCnt--;

					if (pageCnt < 0) {
						pageCnt = 0;
					}
					System.out.println("          번호     시간       Id               숙박업체 이름              금액      추가예약" );
					System.out.println("          ----------------------------------------------------------------------------------");
					System.out.println();
					for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
						try {
							int tmpSpec = TheNoljaService.reservations.get(i).getIsSpecBook().equals("O")?TheNoljaService.reservations.get(i).getAccom().getSpecialPrice():0;
							System.out.printf("          %3d    %s   %-8s      %" + -(tmp - util.getNameLength(TheNoljaService.reservations.get(i).getAccom().getName())) +
									"s        %-10s    %s%n", i + 1, TheNoljaService.reservations.get(i).getTime(), 
									TheNoljaService.reservations.get(i).getPerson().getId(), TheNoljaService.reservations.get(i).getAccom().getName(),
									formatter.format(TheNoljaService.reservations.get(i).getAccom().getPrice()) , formatter.format(tmpSpec));
							System.out.println();
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
					
					ui.TailBasicPage();
					break;
				case ">":
					ui.TitleManage();
					if ((tempCnt - 1) / 10 > pageCnt) {
						pageCnt++;
						System.out.println("          번호     시간       Id               숙박업체 이름              금액      추가예약" );
						System.out.println("          ----------------------------------------------------------------------------------");
						System.out.println();
						for (int i = pageCnt * 10; i <= pageCnt * 10 + 9; i++) {
							try {
								int tmpSpec = TheNoljaService.reservations.get(i).getIsSpecBook().equals("O")?TheNoljaService.reservations.get(i).getAccom().getSpecialPrice():0;
								System.out.printf("          %3d    %s   %-8s      %" + -(tmp - util.getNameLength(TheNoljaService.reservations.get(i).getAccom().getName())) +
										"s        %-10s    %s%n", i + 1, TheNoljaService.reservations.get(i).getTime(), 
										TheNoljaService.reservations.get(i).getPerson().getId(), TheNoljaService.reservations.get(i).getAccom().getName(),
										formatter.format(TheNoljaService.reservations.get(i).getAccom().getPrice()) , formatter.format(tmpSpec));
								System.out.println();
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
						for (int i = 0; i < 11; i++) {
							System.out.println();
						}
						System.out.println("                                  <더이상 표시할 page가 없습니다>");
						for (int i = 0; i < 12; i++) {
							System.out.println();
						}
					}
					ui.TailBasicPage();
					break;
				case "r":
					return;
				case "m":
					gotoMain = false;
					return;
				case "x":
					TheNoljaService.saveMember();
					TheNoljaService.saveReservation();
					System.exit(0);
				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public void ModifyManager(int num) {
		while (true) {
			try {
				String name;
				int age;
				String phoneNo;
				int account;
				
				ui.MemberModifyUI();
				int input = Integer.parseInt(scanner.nextLine());
				switch (input) {
				case 1:
					System.out.print("수정할 이름을 입력하세요 (현재이름 : " + people.get(num-1).getName() + ") > ");
					Thread.sleep(100);
					name = scanner.nextLine();
					if (!util.checkKorean(name)) {
						System.out.println("<유효한 한글 이름을 입력해주세요>");
						Thread.sleep(1000);
						continue;
					}
					
					System.out.print("수정할 나이를 입력하세요 (현재나이 : " + people.get(num-1).getAge() + ") > ");
					Thread.sleep(100);
					String tmpage;
					tmpage = scanner.nextLine();
					if(!util.checkNum(tmpage)) {
						System.out.println("<숫자만 입력해주세요>");
						Thread.sleep(1000);
						continue;
					}else {
						age = Integer.parseInt(tmpage);
					}
					
					System.out.print("수정할 전화번호를 입력하세요 (현재전화번호 : " + people.get(num-1).getPhoneNo() + ") > ");
					Thread.sleep(100);
					phoneNo = scanner.nextLine();
					if (!util.checkNum(phoneNo)) {
						System.out.println("<숫자만 입력해주세요>");
						Thread.sleep(1000);
						continue;
					}
					if(!util.checkNumLength(phoneNo)) {
						System.out.println("010-XXXX-XXXX와 같이 '-' 을 포함한");
						System.out.println("13자리 핸드폰 번호를 입력해주세요.");
						Thread.sleep(1000);
						continue;
					}
					
					System.out.print("추가할 금액을 입력하세요 (현재잔액 : " + formatter.format(people.get(num-1).getAccount()) + ") > ");
					Thread.sleep(100);
					String tmpAccount;
					tmpAccount = scanner.nextLine();
					if(!util.checkNum(tmpAccount)){
						System.out.println("<정확한 금액을 입력해주세요>");
						Thread.sleep(1000);
						continue;
					} else {
						account = Integer.parseInt(tmpAccount);
					}
					
					people.get(num-1).setName(name);
					people.get(num-1).setAge(age);
					people.get(num-1).setPhoneNo(phoneNo);
					people.get(num-1).setAccount(people.get(num-1).getAccount() + account);
					
					TheNoljaService.saveMember();
					TheNoljaService.saveReservation();
					
					ui.MemberModifyCompleteUI();
					Thread.sleep(1000);
					return;
				case 2:
					gotoMain = false;
					return;
				case 0:
					TheNoljaService.saveMember();
					TheNoljaService.saveReservation();
					TheNoljaService.saveAccommodation();
					System.exit(0);
				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
