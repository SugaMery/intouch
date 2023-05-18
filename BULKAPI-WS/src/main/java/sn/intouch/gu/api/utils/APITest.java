package sn.intouch.gu.api.utils;

import java.util.HashMap;

public class APITest {
	public static void main(String[] args) {
		Integer nombre = new Integer(200);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("objet", nombre);
		if (map.get("objet").equals(new Integer(200))){
			System.out.println("Egal");
		}else{
			System.out.println("Different");
		}
	}
}
