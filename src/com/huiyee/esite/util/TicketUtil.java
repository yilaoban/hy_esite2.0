package com.huiyee.esite.util;

import org.apache.commons.codec.net.BCodec;
import org.jasypt.util.text.BasicTextEncryptor;

public class TicketUtil {

	private static String password = "2Bornt2B!";
	private static BasicTextEncryptor textEncryptor ;
	private static BCodec bCodec = new BCodec();
	private static final int eb_allowed = 4;
	private static final int ey_allowed= 2;
	private static final int es_allowed = 1;
	
	static{
		textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(password);
	};

	public static String encode(String text) {
		// º”√‹
		return textEncryptor.encrypt(text.trim());
	}

	public static String decode(String ciphertext) {
		// Ω‚√‹
		return textEncryptor.decrypt(ciphertext);

	}
	
	public static void main(String[] args) {
		System.out.println(getLicence("Y", "N", "Y"));
	}
	
	public static int getLicence(String eb,String ey,String es){
		int licence = 0;
		if ("Y".equalsIgnoreCase(eb)) {
			licence += eb_allowed;
		}
		if ("Y".equalsIgnoreCase(ey)) {
			licence += ey_allowed;
		}
		if ("Y".equalsIgnoreCase(es)) {
			licence += es_allowed;
		}
		return licence;
	}
	
	public static int ebLicence(int eblicence){
		if((eblicence & eb_allowed) == eb_allowed){
			return 1;
		}
		return -1;
	}
	
	public static int eyLicence(int eylicence){
		if((eylicence & ey_allowed) == ey_allowed){
			return 1;
		}
		return -1;
	}
	
	public static int esLicence(int eslicence){
		if((eslicence & es_allowed) == es_allowed){
			return 1;
		}
		return -1;
	}
	
	public static String encodeString(String s) throws Exception {
		return bCodec.encode(s);
	}

	public static String decodeString(String s) throws Exception {
		try {
			return bCodec.decode(s.trim());
		} catch (Exception x) {
			x.printStackTrace();
		}
		return null;
	}

}
