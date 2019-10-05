package app;
import static service.SequenceControl.gotoMain;

import java.util.Scanner;

import service.TheNoljaManagement;
import service.TheNoljaService;



public class TheNoljaApp {
	public static void main(String[] args) {
		// ======================================================================
		// 포함관계의 객체 생성
		// ======================================================================
		UI ui = new UI();
		TheNoljaService theNoljaService = new TheNoljaService();
		TheNoljaManagement salesManagement = new TheNoljaManagement();

		// ======================================================================
		// 시작화면 구현 : Loading -> Main UI
		// ======================================================================
		ui.UIInitialize();
		ui.LoadingUI();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ui.MainUI();

		// ======================================================================
		// Main UI의 목록 선택
		// : while 무한반복문을 통해 계속된 입력을 받음.
		// ======================================================================
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			try {
				int key = Integer.parseInt(scanner.next());
				
				switch (key) {
				case 1:
					ui.RequestLogin();
					Thread.sleep(1000);
					ui.MainUI();
					break;
				case 2:
					ui.RequestLogin();
					Thread.sleep(1000);
					ui.MainUI();
					break;
				case 3:
					ui.RequestLogin();
					Thread.sleep(1000);
					ui.MainUI();
					break;
				case 4:
					ui.RequestLogin();
					Thread.sleep(1000);
					ui.MainUI();
					break;
				case 5:
					ui.UIInitialize();
					theNoljaService.login();
					ui.MainUI();
					gotoMain = true;
					break;
				case 6:
					ui.UIInitialize();
					theNoljaService.regist();
					ui.MainUI();
					gotoMain = true;
					break;
				case 9999:
					ui.UIInitialize();
					salesManagement.theNoljaManagementLogin();
					ui.MainUI();
					gotoMain = true;
					break;
				case 0:
					TheNoljaService.saveMember();
					TheNoljaService.saveReservation();
					TheNoljaService.saveAccommodation();
					return;
				default:
					break;
				}		
			} catch (NumberFormatException e) {
			
			} catch (Exception e) {
				
			}
		}
	}
}

