package com.pkglobal.app.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.pkglobal.app.model.CustomerAddress;

@Tag("unit")
public class ObjectMapperUtilTest {
	@Test
	public void testConvertJavaObjectToJsonWhenValidObjectPassedShouldreturnJson() {
		assertNotNull(ObjectMapperUtil.convertJavaObjectToJson(new CustomerAddress()));
	}
}
