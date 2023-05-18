package sn.intouch.gu.api.ejb.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import sn.intouch.gu.api.ejb.entities.PDA;


public class Utils {
	private static final String ACTIF = null;

	public static String getEncodedPassword(String key) throws NoSuchAlgorithmException {
		byte[] uniqueKey = key.getBytes();
		byte[] hash = null;
		hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		StringBuffer hashString = new StringBuffer();
		for (int i = 0; i < hash.length; ++i) {
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			} else {
				hashString.append(hex.substring(hex.length() - 2));
			}
		}
		return hashString.toString();
	}

	public static void formatLabel(Double double1) {
		// TODO Auto-generated method stub
		
	}
	
	public static PDA getFirstActivatePda(List<PDA> pdas) {
		PDA pda = null;
		String statut ="ACTIF";
		for (PDA p : pdas) {
			if (statut.equalsIgnoreCase(p.getEtat())) {
				return p;
			}
		}
		return pda;
	}
	
	
	public static Date addDays(Date date, int days, TimeZone tz) {
		  Calendar cal = new GregorianCalendar(tz);
		  cal.setTime(date);
		  cal.add(Calendar.DATE, days);
		  return cal.getTime();
	}
}
