package com.ivo.util;

import java.util.Calendar;

/**
 *@author wangjian
 *@time 2017年9月12日 - 下午4:04:21
 *@description:
 */
public class CurrentUtil {
	public static String CurrentTracking(int equipmentGroupID){  
        int year = CurrentYear();  
        int month = CurrentMonth();
        int day = CurrentDay();
        String returnStr = "DAR"+year;
        if(month<10) {
        		returnStr = returnStr+"0"+month;    
        } else {
        		returnStr = returnStr+month;
        	}
        if(day<10){
        	returnStr = returnStr +"0"+ day;
        	}else{
        		returnStr = returnStr + day;
        	}
     
        if(equipmentGroupID<10){
        		returnStr = returnStr +"00"+ equipmentGroupID;
        } else if(equipmentGroupID<100){
        		returnStr = returnStr +"0"+ equipmentGroupID;
        } else {
        		returnStr = returnStr+equipmentGroupID;
        }
        return returnStr;
	}
	
	public static int CurrentYear(){
		Calendar now = Calendar.getInstance();  
        int year = now.get(Calendar.YEAR);  
        return year;
	}
	
	public static int CurrentMonth(){
		Calendar now = Calendar.getInstance();  
        int month = now.get(Calendar.MONTH)+1;
        return month;
	}
	
	public static int CurrentDay(){
		Calendar now = Calendar.getInstance();  
        int day = now.get(Calendar.DAY_OF_MONTH);
        return day;
	}
}
