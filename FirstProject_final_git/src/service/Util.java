package service;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vo_Accommodation.Accommodation;

public class Util implements Serializable{
	private static final long serialVersionUID = 0;
	static DecimalFormat decFormat = new DecimalFormat("###,###");
	
	public String toDecFormat(int input) {
		return decFormat.format(input);
	}
	
	
	public int getNameLength(String name) {
		int tempLength = 0;
		
		for (int i = 0; i < name.length(); i++) {
			// 한글일 때
			if ((int) name.charAt(i) >= 44032 && (int) name.charAt(i) <= 55203) {
				tempLength++;
			}
		}
		return tempLength;
	}

	
	public boolean checkKorean(String name) {
		try {
			char[] ch = name.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				if (ch[i] >= '가' && ch[i] <= '힝') {
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
	
	public boolean checkID(String id) throws RuntimeException{
		try {
			char[] ch = id.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				if((ch[i] >= '0' && ch[i] <= '9') || 
						(ch[i] >= 'a' && ch[i] <= 'z') || 
						(ch[i] >= 'A' && ch[i] <= 'Z')) {
					return true;
				}
			}
			return false;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean checkNum(String num) {
		try {
			char[] ch = num.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				if(ch[i] >= '0' && ch[i] <= '9') {
					return true;
				}
			}
			return false;
			
		} catch (Exception e) {
			return false;
		}
	}
	public boolean checkNum(int num) {
		try {
			char[] ch = Integer.toString(num).toCharArray();
			for (int i = 0; i < ch.length; i++) {
				if(ch[i] >= '0' && ch[i] <= '9') {
					return true;
				}
			}
			return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean checkNumLength(String phoneNum) {
		try {
			char[] ch = phoneNum.toCharArray();
			if(ch.length == 13) {
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean checkMoney(String money) {
		try {
			int i = Integer.parseInt(money);
			if(( i % 10 ) != 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public ArrayList<Accommodation> sortPrice(List<Accommodation> accommodation2, String group, String location, String direct){
		ArrayList<Accommodation> tempList = new ArrayList<>();
		for(int i = 0; i < accommodation2.size(); i++) {
			if(accommodation2.get(i).getGroup().equals(group) && accommodation2.get(i).getLocation().equals(location)) {
				tempList.add(accommodation2.get(i));
			}
		}
		
		if(direct.equals("오름차순")) {
			Collections.sort(tempList, new Comparator<Accommodation>() {
				@Override
				public int compare(Accommodation o1, Accommodation o2) {
					// TODO Auto-generated method stub
					return (int) (o2.getPrice() - o1.getPrice());
				}
			});
		}else if(direct.equals("내림차순")) {
			Collections.sort(tempList, new Comparator<Accommodation>() {
				@Override
				public int compare(Accommodation o1, Accommodation o2) {
					// TODO Auto-generated method stub
					return (int) (o1.getPrice() - o2.getPrice());
				}
			});
		}
		
		for(int i = 0; i < tempList.size(); i++) {
			tempList.get(i).setNo(i+1);
		}
		
		
		return tempList;
	}
	
	public ArrayList<Accommodation> sortNumber(List<Accommodation> accommodation2, String direct){
		ArrayList<Accommodation> tempList = new ArrayList<>();
		for(int i = 0; i < accommodation2.size(); i++) {
			tempList.add(accommodation2.get(i));
		}
		
		if(direct.equals("번호오름차순")) {
			Collections.sort(tempList, new Comparator<Accommodation>() {
				@Override
				public int compare(Accommodation o1, Accommodation o2) {
					// TODO Auto-generated method stub
					return (int) (o1.getNo() - o2.getNo());
				}
			});
		}else if(direct.equals("번호내림차순")) {
			Collections.sort(tempList, new Comparator<Accommodation>() {
				@Override
				public int compare(Accommodation o1, Accommodation o2) {
					// TODO Auto-generated method stub
					return (int) (o2.getNo() - o1.getNo());
				}
			});
		}
		
		return tempList;
	}
	
	public ArrayList<Accommodation> sortSales(List<Accommodation> accommodation2, String direct){
		ArrayList<Accommodation> tempList = new ArrayList<>();
		for(int i = 0; i < accommodation2.size(); i++) {
			tempList.add(accommodation2.get(i));
		}
		
		if(direct.equals("오름차순")) {
			Collections.sort(tempList, new Comparator<Accommodation>() {
				@Override
				public int compare(Accommodation o1, Accommodation o2) {
					// TODO Auto-generated method stub
					return (int) (o1.getSales() - o2.getSales());
				}
			});
		}else if(direct.equals("내림차순")) {
			Collections.sort(tempList, new Comparator<Accommodation>() {
				@Override
				public int compare(Accommodation o1, Accommodation o2) {
					// TODO Auto-generated method stub
					return (int) (o2.getSales() - o1.getSales());
				}
			});
		}
		return tempList;
	}
	
	public ArrayList<Accommodation> sortRemain(List<Accommodation> accommodation2, String group, String location){
		ArrayList<Accommodation> tempList = new ArrayList<>();
		for(int i = 0; i < accommodation2.size(); i++) {
			if(accommodation2.get(i).getGroup().equals(group) && accommodation2.get(i).getLocation().equals(location)) {
				tempList.add(accommodation2.get(i));
			}
		}
		
		Collections.sort(tempList, new Comparator<Accommodation>() {
			@Override
			public int compare(Accommodation o1, Accommodation o2) {
				// TODO Auto-generated method stub
				return (int) (o2.getRemain() - o1.getRemain());
			}
		});
		
		for(int i = 0; i < tempList.size(); i++) {
			tempList.get(i).setNo(i+1);
		}
		
		
		return tempList;
	}

	public ArrayList<Accommodation> getPossibleReservation(List<Accommodation> accommodation2, String group, String location){
		ArrayList<Accommodation> tempList = new ArrayList<>();
		for(int i = 0; i < accommodation2.size(); i++) {
			if(accommodation2.get(i).getGroup().equals(group) && accommodation2.get(i).getLocation().equals(location)
					&& accommodation2.get(i).getRemain() != 0) {
				tempList.add(accommodation2.get(i));
			}
		}
		
		for(int i = 0; i < tempList.size(); i++) {
			tempList.get(i).setNo(i+1);
		}
		
		return tempList;
	}
	
	public ArrayList<Accommodation> recommendReservation(List<Accommodation> accommodation2){
		ArrayList<Accommodation> tempList = new ArrayList<>();
		for(int i = 0; i < accommodation2.size(); i++) {
			tempList.add(accommodation2.get(i));
		}
		
		tempList.sort(new Comparator<Accommodation>() {
			@Override
			public int compare(Accommodation o1, Accommodation o2) {
				// TODO Auto-generated method stub
				return o1.getSales() - o2.getSales();
			}
		});
		
		for(int i = accommodation2.size() - 1; i > (int)((double)accommodation2.size()/100*30); i--) {
			tempList.remove(i);
		}
		
		return tempList;
	}
}
