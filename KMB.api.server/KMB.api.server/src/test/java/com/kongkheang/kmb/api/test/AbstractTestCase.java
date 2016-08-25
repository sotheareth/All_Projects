package com.kongkheang.kmb.api.test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kongkheang.kmb.api.app.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, MockConfiguration.class})
public class AbstractTestCase extends Assert {
	@Autowired
	protected ObjectMapper objectMapper;
	
	public void prettyPrint(Object obj) {
		try {
			System.out.println(objectMapper.writeValueAsString(obj));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
