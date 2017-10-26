package com.ivo.model.equipment;
/**
 *@author wangjian
 *@time 2017年9月7日 - 下午1:59:54
 *@description:每天点检的设备太多,CheckForm明细会有很多
 *	用该类来代替CheckFormItem保存设备每天数据
 *	数据按月保存，减少数据量
 */
public class CheckDataDetail {
	private long id;
	private int year;
	private int month;
	private int equipmentGroup_fk;
	private int equipmentID_fk;
	private String equipmentName;
	private String day1;
	private String day2;
	private String day3;
	private String day4;
	private String day5;
	private String day6;
	private String day7;
	private String day8;
	private String day9;
	private String day10;
	private String day11;
	private String day12;
	private String day13;
	private String day14;
	private String day15;
	private String day16;
	private String day17;
	private String day18;
	private String day19;
	private String day20;
	private String day21;
	private String day22;
	private String day23;
	private String day24;
	private String day25;
	private String day26;
	private String day27;
	private String day28;
	private String day29;
	private String day30;
	private String day31;
	
	public String getCurrentDay(int day){
		switch(day){
			case 1 : return this.day1;
			case 2 : return this.day2;
			case 3 : return this.day3;
			case 4 : return this.day4;
			case 5 : return this.day5;
			case 6 : return this.day6;
			case 7 : return this.day7;
			case 8 : return this.day8;
			case 9 : return this.day9;
			case 10 : return this.day10;
			case 11 : return this.day11;
			case 12 : return this.day12;
			case 13 : return this.day13;
			case 14 : return this.day14;
			case 15 : return this.day15;
			case 16 : return this.day16;
			case 17 : return this.day17;
			case 18 : return this.day18;
			case 19 : return this.day19;
			case 20 : return this.day20;
			case 21 : return this.day21;
			case 22 : return this.day22;
			case 23 : return this.day23;
			case 24 : return this.day24;
			case 25 : return this.day25;
			case 26 : return this.day26;
			case 27 : return this.day27;
			case 28 : return this.day28;
			case 29 : return this.day29;
			case 30 : return this.day30;
			case 31 : return this.day31;
			default : return null;
		}
	}
	
	public void SetCurrentDay(int day, String check){
		switch(day){
			case 1 : this.day1=check; break;
			case 2 : this.day2=check; break;
			case 3 : this.day3=check; break;
			case 4 : this.day4=check; break;
			case 5 : this.day5=check; break;
			case 6 : this.day6=check; break;
			case 7 : this.day7=check; break;
			case 8 : this.day8=check; break;
			case 9 : this.day9=check; break;
			case 10 : this.day10=check; break;
			case 11 : this.day11=check; break;
			case 12 : this.day12=check; break;
			case 13 : this.day13=check; break;
			case 14 : this.day14=check; break;
			case 15 : this.day15=check; break;
			case 16 : this.day16=check; break;
			case 17 : this.day17=check; break;
			case 18 : this.day18=check; break;
			case 19 : this.day19=check; break;
			case 20 : this.day20=check; break;
			case 21 : this.day21=check; break;
			case 22 : this.day22=check; break;
			case 23 : this.day23=check; break;
			case 24 : this.day24=check; break;
			case 25 : this.day25=check; break;
			case 26 : this.day26=check; break;
			case 27 : this.day27=check; break;
			case 28 : this.day28=check; break;
			case 29 : this.day29=check; break;
			case 30 : this.day30=check; break;
			case 31 : this.day31=check; break;
			default : break;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getEquipmentGroup_fk() {
		return equipmentGroup_fk;
	}

	public void setEquipmentGroup_fk(int equipmentGroup_fk) {
		this.equipmentGroup_fk = equipmentGroup_fk;
	}

	public int getEquipmentID_fk() {
		return equipmentID_fk;
	}

	public void setEquipmentID_fk(int equipmentID_fk) {
		this.equipmentID_fk = equipmentID_fk;
	}

	public String getDay1() {
		return day1;
	}

	public void setDay1(String day1) {
		this.day1 = day1;
	}

	public String getDay2() {
		return day2;
	}

	public void setDay2(String day2) {
		this.day2 = day2;
	}

	public String getDay3() {
		return day3;
	}

	public void setDay3(String day3) {
		this.day3 = day3;
	}

	public String getDay4() {
		return day4;
	}

	public void setDay4(String day4) {
		this.day4 = day4;
	}

	public String getDay5() {
		return day5;
	}

	public void setDay5(String day5) {
		this.day5 = day5;
	}

	public String getDay6() {
		return day6;
	}

	public void setDay6(String day6) {
		this.day6 = day6;
	}

	public String getDay7() {
		return day7;
	}

	public void setDay7(String day7) {
		this.day7 = day7;
	}

	public String getDay8() {
		return day8;
	}

	public void setDay8(String day8) {
		this.day8 = day8;
	}

	public String getDay9() {
		return day9;
	}

	public void setDay9(String day9) {
		this.day9 = day9;
	}

	public String getDay10() {
		return day10;
	}

	public void setDay10(String day10) {
		this.day10 = day10;
	}

	public String getDay11() {
		return day11;
	}

	public void setDay11(String day11) {
		this.day11 = day11;
	}

	public String getDay12() {
		return day12;
	}

	public void setDay12(String day12) {
		this.day12 = day12;
	}

	public String getDay13() {
		return day13;
	}

	public void setDay13(String day13) {
		this.day13 = day13;
	}

	public String getDay14() {
		return day14;
	}

	public void setDay14(String day14) {
		this.day14 = day14;
	}

	public String getDay15() {
		return day15;
	}

	public void setDay15(String day15) {
		this.day15 = day15;
	}

	public String getDay16() {
		return day16;
	}

	public void setDay16(String day16) {
		this.day16 = day16;
	}

	public String getDay17() {
		return day17;
	}

	public void setDay17(String day17) {
		this.day17 = day17;
	}

	public String getDay18() {
		return day18;
	}

	public void setDay18(String day18) {
		this.day18 = day18;
	}

	public String getDay19() {
		return day19;
	}

	public void setDay19(String day19) {
		this.day19 = day19;
	}

	public String getDay20() {
		return day20;
	}

	public void setDay20(String day20) {
		this.day20 = day20;
	}

	public String getDay21() {
		return day21;
	}

	public void setDay21(String day21) {
		this.day21 = day21;
	}

	public String getDay22() {
		return day22;
	}

	public void setDay22(String day22) {
		this.day22 = day22;
	}

	public String getDay23() {
		return day23;
	}

	public void setDay23(String day23) {
		this.day23 = day23;
	}

	public String getDay24() {
		return day24;
	}

	public void setDay24(String day24) {
		this.day24 = day24;
	}

	public String getDay25() {
		return day25;
	}

	public void setDay25(String day25) {
		this.day25 = day25;
	}

	public String getDay26() {
		return day26;
	}

	public void setDay26(String day26) {
		this.day26 = day26;
	}

	public String getDay27() {
		return day27;
	}

	public void setDay27(String day27) {
		this.day27 = day27;
	}

	public String getDay28() {
		return day28;
	}

	public void setDay28(String day28) {
		this.day28 = day28;
	}

	public String getDay29() {
		return day29;
	}

	public void setDay29(String day29) {
		this.day29 = day29;
	}

	public String getDay30() {
		return day30;
	}

	public void setDay30(String day30) {
		this.day30 = day30;
	}

	public String getDay31() {
		return day31;
	}

	public void setDay31(String day31) {
		this.day31 = day31;
	}
	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
}
