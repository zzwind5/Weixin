package cn.wltc.junit;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/config/spring/**/*-beans.xml")
public class SimpleTest extends AbstractJUnit4SpringContextTests {

	@BeforeClass
	public static void init() {
	   PropertyConfigurator.configure("src/test/resources/log4j.properties");
	}
	
	@Test
	public void myTest(){
		assertTrue(true);
	}
}
