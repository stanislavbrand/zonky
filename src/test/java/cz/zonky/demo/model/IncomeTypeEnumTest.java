package cz.zonky.demo.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IncomeTypeEnumTest {

	@Test
	public void forValueTest() {		
		assertEquals(IncomeTypeEnum.EMPLOYMENT, IncomeTypeEnum.forValue("EMPLOYMENT"));
		assertEquals(IncomeTypeEnum.SELF_EMPLOYMENT, IncomeTypeEnum.forValue("SELF_EMPLOYMENT"));
		assertEquals(IncomeTypeEnum.PENSION, IncomeTypeEnum.forValue("PENSION"));
		assertEquals(IncomeTypeEnum.OTHERS_MAIN, IncomeTypeEnum.forValue("OTHERS_MAIN"));
		assertEquals(IncomeTypeEnum.MATERNITY_LEAVE, IncomeTypeEnum.forValue("MATERNITY_LEAVE"));

		assertEquals(IncomeTypeEnum.UNKNOWN, IncomeTypeEnum.forValue("test"));
	}

}
