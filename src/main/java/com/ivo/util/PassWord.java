package com.ivo.util;

import java.io.UnsupportedEncodingException;

/**
 *@author wangjian
 *@time 2017年8月30日 - 下午4:40:50
 *@description:
 */
public class PassWord {
	
	
	public String testRun(String password)   
	{
		
		String pid = PassWord.getFromUTF8HEX(password);
		
		return pid;

	}
 
	static private String enKey = "0123456789ABCDEF";
	static private int[] deKey = {
	    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	     0,  1,  2,  3,  4,  5,  6,  7,  8,  9, -1, -1, -1, -1, -1, -1,
	    -1, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
		};	

 
	static public String getFromUTF8HEX(String str)
	{
		if (str == null) str = "";
		String strResponse = ""; 
		byte[] pBuffer = new byte[ str.length()/2 ];
		int byte_s = 0; 
		int uw_index, lw_index; 
		char uw_char, lw_char; 
		int j = 0; 
		
		for( int i=0; i < str.length(); ) {
			uw_char = str.charAt( i++ ); 
			lw_char = str.charAt( i++ ); 
			uw_index = deKey[ uw_char ]; 
			lw_index = deKey[ lw_char ]; 
			byte_s = uw_index*16 + lw_index; 
			if( byte_s > 127 ) byte_s = -1*(byte_s-127); 
			
			pBuffer[j++] = (byte)byte_s; 
		} // for i
		
		try {
			strResponse = new String( pBuffer, "UTF-8" ); 
		} catch( UnsupportedEncodingException unen_ex) {
			return ""; 
		} // try {
	
		return strResponse;  
	}
 
 
	static public String getUTF8HEX(String str)
	{
		byte[] pBuffer = null; 
		
		try {
			pBuffer = str.getBytes("UTF-8");
		} catch( UnsupportedEncodingException unen_ex) {
			return ""; 
		} // try {
		
		return getUTF8HEX( pBuffer ); 
	}
	
	
	static public String getUTF8HEX(byte[] pBuffer)
	{
		StringBuffer objResponse = new StringBuffer(); 
		
		int byte_s = 0; 
		int uw_index, lw_index; 
		char uw_char, lw_char; 
		
		for( int i=0; i < pBuffer.length; i++ ) {
			byte_s = pBuffer[i];
			if( byte_s < 0 ) byte_s = -1*byte_s + 127; 
			uw_index = byte_s/16; 
			lw_index = byte_s%16;
			uw_char = enKey.charAt( uw_index ); 
			lw_char = enKey.charAt( lw_index ); 
			
			objResponse.append( uw_char ); 
			objResponse.append( lw_char ); 
		} // for i
		return objResponse.toString(); 
	}
	public static void main(String[] args) {
		PassWord pw = new PassWord();
		System.out.println(pw.testRun("4B696D39383235"));
	}
}
