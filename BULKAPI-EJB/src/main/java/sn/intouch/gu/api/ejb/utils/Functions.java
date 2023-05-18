package sn.intouch.gu.api.ejb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
public class Functions {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public static  HashMap<String,Object>  verifyFunctionArg(FunctionArg arg){
		HashMap<String,Object> res = null;
		if(arg != null){
			res = new HashMap<String, Object>();
			res.put("CODE", 200);res.put("MESSAGE", "OK");
			if(arg.getName() != null && arg.getValue() != null && arg.getExpectedValueCondition() != null && arg.getExpectedType() != null){
				try{
					String expectedType = arg.getExpectedType();
					if(expectedType.equals("String")){
						String value = arg.getValue();
						String expectedCond = arg.getExpectedValueCondition();
						String expectedValue = (String)arg.getExpectedValue();
						boolean eq = value.equals(expectedValue);
						if((eq && expectedCond.equals("=")) || (!eq && expectedCond.equals("!="))){	
							res.put("OBJECT", value);
						}else{
							res.put("MESSAGE", arg.getName()+": VALEUR PAS "+expectedCond+" A "+expectedValue);
							res.put("CODE", 400);
						}
					}
					
					else if(expectedType.equals("Date")){
						String value = arg.getValue();
						Date dateValue = sdf.parse(value);
						String expectedCond = arg.getExpectedValueCondition();
						Date expectedValue = (Date)arg.getExpectedValue();
						boolean comp = dateValue.before(expectedValue);
						if((comp == true &&  expectedCond.equals("<=")) || (comp == true &&  expectedCond.equals("<"))){
							res.put("OBJECT", dateValue);
						}else{
							comp = dateValue.after(expectedValue);
							if((comp == true &&  expectedCond.equals(">=")) || (comp == true &&  expectedCond.equals(">"))){
								res.put("OBJECT", dateValue);
							}else{
								res.put("MESSAGE", arg.getName()+": VALEUR PAS "+expectedCond+" A "+expectedValue);
								res.put("CODE", 400);
							}
							
						}
					}
					else if(expectedType.equals("Integer")){
						Integer value = Integer.parseInt(arg.getValue());
						String expectedCond = arg.getExpectedValueCondition();
						Integer expectedValue = (Integer)arg.getExpectedValue();
						int comp = value.compareTo(expectedValue);
						if((comp == 0 && (expectedCond.equals("=") || expectedCond.equals("<=") || expectedCond.equals(">="))) 
								|| (comp < 0 && (expectedCond.equals("<") || expectedCond.equals("<="))) || 
								(comp > 0 && (expectedCond.equals(">") || expectedCond.equals(">=")))){
							res.put("OBJECT", value);
						}else{
							res.put("MESSAGE", arg.getName()+": VALEUR PAS "+expectedCond+" A "+expectedValue);
							res.put("CODE", 400);
						}
					}
					else if(expectedType.equals("Long")){
						Long value = Long.parseLong(arg.getValue());
						String expectedCond = arg.getExpectedValueCondition();
						Long expectedValue = (Long)arg.getExpectedValue();
						int comp = value.compareTo(expectedValue);
						if((comp == 0 && (expectedCond.equals("=") || expectedCond.equals("<=") || expectedCond.equals(">="))) 
								|| (comp < 0 && (expectedCond.equals("<") || expectedCond.equals("<="))) || 
								(comp > 0 && (expectedCond.equals(">") || expectedCond.equals(">=")))){
							res.put("OBJECT", value);
						}else{
							res.put("MESSAGE", arg.getName()+": VALEUR PAS "+expectedCond+" A "+expectedValue);
							res.put("CODE", 400);
						}
					}
					else if(expectedType.equals("Double")){
						Double value = Double.parseDouble(arg.getValue());
						String expectedCond = arg.getExpectedValueCondition();
						Double expectedValue = (Double)arg.getExpectedValue();
						int comp = value.compareTo(expectedValue);
						if((comp == 0 && (expectedCond.equals("=") || expectedCond.equals("<=") || expectedCond.equals(">="))) 
								|| (comp < 0 && (expectedCond.equals("<") || expectedCond.equals("<="))) || 
								(comp > 0 && (expectedCond.equals(">") || expectedCond.equals(">=")))){
							res.put("OBJECT", value);
						}else{
							res.put("MESSAGE", arg.getName()+": VALEUR PAS "+expectedCond+" A "+expectedValue);
							res.put("CODE", 400);
						}
					}
				}catch(ClassCastException cce){
					res.put("MESSAGE", arg.getName()+": MAUVAIS FORMAT, "+arg.getExpectedType()+" ATTENDU");
					res.put("CODE", 400);
				} catch (ParseException e) {
					res.put("MESSAGE", arg.getName()+": MAUVAIS FORMAT, "+arg.getExpectedType()+" ATTENDU");
					res.put("CODE", 400);
				}
			}else{
				if(arg.getValue() == null){
					res.put("MESSAGE", arg.getName()+": VALEUR OBLIGATOIRE");
					res.put("CODE", 400);
				}
			}
		}
		return res;
	}
	

	/**
	 * Verifie si un service est connu
	 * @param service
	 * @return 
	 */
	
	
	/**
	 * Return la liste des services touchPay
	 * @return 
	 */
	
	

	/**
	 * Verifie si un statut de payment est connu
	 * @param status
	 * @return 
	 */
	
	
	/**
	 * Return la liste des statuts de payment
	 * @return 
	 */
	
	
}
