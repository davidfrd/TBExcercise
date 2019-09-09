package com.davidredondo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BillingControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	private static final String REQUEST_JSON_1 = "{\n" + 
			"   \"shifts\":[\n" + 
			"      {\n" + 
			"         \"id\":1,\n" + 
			"         \"start\":\"2019-04-28 20:30:00\",\n" + 
			"         \"end\":\"2019-04-29 00:30:00\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"         \"id\":2,\n" + 
			"         \"start\":\"2019-04-29 22:10:00\",\n" + 
			"         \"end\":\"2019-04-30 02:15:00\"\n" + 
			"      }\n" + 
			"   ],\n" + 
			"   \"rules\":[\n" + 
			"      {\n" + 
			"         \"id\":1,\n" + 
			"         \"type\":\"FIXED\",\n" + 
			"         \"start\":\"21:00\",\n" + 
			"         \"end\":\"00:00\",\n" + 
			"         \"payRate\":10.50\n" + 
			"      },\n" + 
			"      {\n" + 
			"         \"id\":2,\n" + 
			"         \"type\":\"DURATION\",\n" + 
			"         \"start\":3600,\n" + 
			"         \"end\":7200,\n" + 
			"         \"payRate\": 20.23\n" + 
			"      },\n" + 
			"      {\n" + 
			"         \"id\":3,\n" + 
			"         \"type\":\"DURATION\",\n" + 
			"         \"start\":7200,\n" + 
			"         \"end\":10801,\n" + 
			"         \"payRate\": 30.50\n" + 
			"      }\n" + 
			"   ]\n" + 
			"}";
	
	
	private static final String EXPECTER_JSON_1 = "{\n" + 
			"    \"pay\": 152.23,\n" + 
			"    \"billedShifts\": [\n" + 
			"        {\n" + 
			"            \"id\": 1,\n" + 
			"            \"start\": \"2019-04-28 20:30:00\",\n" + 
			"            \"end\": \"2019-04-29 00:30:00\",\n" + 
			"            \"session\": 14400,\n" + 
			"            \"pay\": 82.24,\n" + 
			"            \"portions\": [\n" + 
			"                {\n" + 
			"                    \"id\": 1,\n" + 
			"                    \"start\": \"2019-04-28 21:00:00\",\n" + 
			"                    \"end\": \"2019-04-29 00:00:00\",\n" + 
			"                    \"session\": 10800,\n" + 
			"                    \"pay\": 31.50\n" + 
			"                },\n" + 
			"                {\n" + 
			"                    \"id\": 2,\n" + 
			"                    \"start\": \"2019-04-28 21:30:00\",\n" + 
			"                    \"end\": \"2019-04-28 22:30:00\",\n" + 
			"                    \"session\": 3600,\n" + 
			"                    \"pay\": 20.23\n" + 
			"                },\n" + 
			"                {\n" + 
			"                    \"id\": 3,\n" + 
			"                    \"start\": \"2019-04-28 22:30:00\",\n" + 
			"                    \"end\": \"2019-04-28 23:30:01\",\n" + 
			"                    \"session\": 3601,\n" + 
			"                    \"pay\": 30.51\n" + 
			"                }\n" + 
			"            ]\n" + 
			"        },\n" + 
			"        {\n" + 
			"            \"id\": 2,\n" + 
			"            \"start\": \"2019-04-29 22:10:00\",\n" + 
			"            \"end\": \"2019-04-30 02:15:00\",\n" + 
			"            \"session\": 14700,\n" + 
			"            \"pay\": 69.99,\n" + 
			"            \"portions\": [\n" + 
			"                {\n" + 
			"                    \"id\": 1,\n" + 
			"                    \"start\": \"2019-04-29 22:10:00\",\n" + 
			"                    \"end\": \"2019-04-30 00:00:00\",\n" + 
			"                    \"session\": 6600,\n" + 
			"                    \"pay\": 19.25\n" + 
			"                },\n" + 
			"                {\n" + 
			"                    \"id\": 2,\n" + 
			"                    \"start\": \"2019-04-29 23:10:00\",\n" + 
			"                    \"end\": \"2019-04-30 00:10:00\",\n" + 
			"                    \"session\": 3600,\n" + 
			"                    \"pay\": 20.23\n" + 
			"                },\n" + 
			"                {\n" + 
			"                    \"id\": 3,\n" + 
			"                    \"start\": \"2019-04-30 00:10:00\",\n" + 
			"                    \"end\": \"2019-04-30 01:10:01\",\n" + 
			"                    \"session\": 3601,\n" + 
			"                    \"pay\": 30.51\n" + 
			"                }\n" + 
			"            ]\n" + 
			"        }\n" + 
			"    ]\n" + 
			"}"; 
	
	@Test
	public void testOkRequestBody() throws Exception{
		this.mockMvc.perform(get("/billing").contentType(MediaType.APPLICATION_JSON).content(REQUEST_JSON_1)).andExpect(status().isOk()).andExpect(content().json(EXPECTER_JSON_1));
	}
	
}
