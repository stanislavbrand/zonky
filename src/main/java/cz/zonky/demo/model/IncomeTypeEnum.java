package cz.zonky.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum IncomeTypeEnum {
	EMPLOYMENT, SELF_EMPLOYMENT, PENSION, OTHERS_MAIN, MATERNITY_LEAVE, UNKNOWN;
	
    @JsonCreator
    public static IncomeTypeEnum forValue(String value) {
    	try {
            return IncomeTypeEnum.valueOf(value);
		} catch (IllegalArgumentException e) {
			return IncomeTypeEnum.UNKNOWN;
		}

    }
}
