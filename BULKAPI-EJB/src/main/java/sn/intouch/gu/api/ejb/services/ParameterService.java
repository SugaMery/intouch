package sn.intouch.gu.api.ejb.services;

import sn.intouch.gu.api.ejb.entities.Parametre;

public interface ParameterService {

	public int nextValue(String code);

	public int getValue(String code);

	public Parametre getParameterByCode(String code);

	public int setValue(String code, String value);

}
