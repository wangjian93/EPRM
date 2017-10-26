package com.ivo.model.equipment;
/**
 *@author wangjian
 *@time 2017年9月21日 - 下午1:22:29
 *@description:
 */
public class CheckFormMonth {
	private long id;
	private int equipmentGroupID;
	private String equipmentGroupName;
	private int depOfclassID;
	private int year;
	private int month;
	private float day1;
	private float day2;
	private float day3;
	private float day4;
	private float day5;
	private float day6;
	private float day7;
	private float day8;
	private float day9;
	private float day10;
	private float day11;
	private float day12;
	private float day13;
	private float day14;
	private float day15;
	private float day16;
	private float day17;
	private float day18;
	private float day19;
	private float day20;
	private float day21;
	private float day22;
	private float day23;
	private float day24;
	private float day25;
	private float day26;
	private float day27;
	private float day28;
	private float day29;
	private float day30;
	private float day31;
	private float spec;
	public float getCurrentDay(int day){
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
			default : return 0;
		}
	}
	
	public void SetCurrentDay(int day, float check){
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
	public int getEquipmentGroupID() {
		return equipmentGroupID;
	}
	public void setEquipmentGroupID(int equipmentGroupID) {
		this.equipmentGroupID = equipmentGroupID;
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
	public float getDay1() {
		return day1;
	}
	public void setDay1(float day1) {
		this.day1 = day1;
	}
	public float getDay2() {
		return day2;
	}
	public void setDay2(float day2) {
		this.day2 = day2;
	}
	public float getDay3() {
		return day3;
	}
	public void setDay3(float day3) {
		this.day3 = day3;
	}
	public float getDay4() {
		return day4;
	}
	public void setDay4(float day4) {
		this.day4 = day4;
	}
	public float getDay5() {
		return day5;
	}
	public void setDay5(float day5) {
		this.day5 = day5;
	}
	public float getDay6() {
		return day6;
	}
	public void setDay6(float day6) {
		this.day6 = day6;
	}
	public float getDay7() {
		return day7;
	}
	public void setDay7(float day7) {
		this.day7 = day7;
	}
	public float getDay8() {
		return day8;
	}
	public void setDay8(float day8) {
		this.day8 = day8;
	}
	public float getDay9() {
		return day9;
	}
	public void setDay9(float day9) {
		this.day9 = day9;
	}
	public float getDay10() {
		return day10;
	}
	public void setDay10(float day10) {
		this.day10 = day10;
	}
	public float getDay11() {
		return day11;
	}
	public void setDay11(float day11) {
		this.day11 = day11;
	}
	public float getDay12() {
		return day12;
	}
	public void setDay12(float day12) {
		this.day12 = day12;
	}
	public float getDay13() {
		return day13;
	}
	public void setDay13(float day13) {
		this.day13 = day13;
	}
	public float getDay14() {
		return day14;
	}
	public void setDay14(float day14) {
		this.day14 = day14;
	}
	public float getDay15() {
		return day15;
	}
	public void setDay15(float day15) {
		this.day15 = day15;
	}
	public float getDay16() {
		return day16;
	}
	public void setDay16(float day16) {
		this.day16 = day16;
	}
	public float getDay17() {
		return day17;
	}
	public void setDay17(float day17) {
		this.day17 = day17;
	}
	public float getDay18() {
		return day18;
	}
	public void setDay18(float day18) {
		this.day18 = day18;
	}
	public float getDay19() {
		return day19;
	}
	public void setDay19(float day19) {
		this.day19 = day19;
	}
	public float getDay20() {
		return day20;
	}
	public void setDay20(float day20) {
		this.day20 = day20;
	}
	public float getDay21() {
		return day21;
	}
	public void setDay21(float day21) {
		this.day21 = day21;
	}
	public float getDay22() {
		return day22;
	}
	public void setDay22(float day22) {
		this.day22 = day22;
	}
	public float getDay23() {
		return day23;
	}
	public void setDay23(float day23) {
		this.day23 = day23;
	}
	public float getDay24() {
		return day24;
	}
	public void setDay24(float day24) {
		this.day24 = day24;
	}
	public float getDay25() {
		return day25;
	}
	public void setDay25(float day25) {
		this.day25 = day25;
	}
	public float getDay26() {
		return day26;
	}
	public void setDay26(float day26) {
		this.day26 = day26;
	}
	public float getDay27() {
		return day27;
	}
	public void setDay27(float day27) {
		this.day27 = day27;
	}
	public float getDay28() {
		return day28;
	}
	public void setDay28(float day28) {
		this.day28 = day28;
	}
	public float getDay29() {
		return day29;
	}
	public void setDay29(float day29) {
		this.day29 = day29;
	}
	public float getDay30() {
		return day30;
	}
	public void setDay30(float day30) {
		this.day30 = day30;
	}
	public float getDay31() {
		return day31;
	}
	public void setDay31(float day31) {
		this.day31 = day31;
	}

	public String getEquipmentGroupName() {
		return equipmentGroupName;
	}

	public void setEquipmentGroupName(String equipmentGroupName) {
		this.equipmentGroupName = equipmentGroupName;
	}

	public int getDepOfclassID() {
		return depOfclassID;
	}

	public void setDepOfclassID(int depOfclassID) {
		this.depOfclassID = depOfclassID;
	}

	public float getSpec() {
		return spec;
	}

	public void setSpec(float spec) {
		this.spec = spec;
	}
	
}
