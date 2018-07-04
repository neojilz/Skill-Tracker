package com.skilltrack.skilltracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import com.skilltrack.*;
import com.skilltrack.skilltracker.model.Associate;
import com.skilltrack.skilltracker.req.AssociateDetails;
import com.skilltrack.skilltracker.req.Response;
import com.skilltrack.skilltracker.services.impl.AssociateServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AssociateController.class, secure = false)
public class AssociateControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AssociateServiceImpl service;
	String associateDetailString = "{\"associateId\":3,\"name\":\"Jijil K I\",\"email\":\"autobindoptout@yahoo.com\",\"mobile\":\"7894561236\",\"gender\":null,\"pic\":\"/9j/4AAQSkZJ\",\"statusGreen\":false,\"statusBlue\":false,\"statusRed\":false,\"level1\":false,\"level2\":false,\"level3\":true,\"remark\":\"Hello\",\"spokenLevel\":1,\"communicationLevel\":2,\"logicLevel\":2,\"aptitudeLevel\":13,\"confidenceLevel\":5,\"strength\":\"123\",\"weakness\":\"2121\",\"skills\":[{\"skill_id\":1,\"skill_name\":\"AA\",\"skill_level\":10}]}";
	//String associateDetailString= "{\"associateId\":0,\"name\":null,\"email\":null,\"mobile\":null,\"gender\":null,\"pic\":null,\"statusGreen\":false,\"statusBlue\":false,\"statusRed\":false,\"level1\":false,\"level2\":false,\"level3\":false,\"remark\":null,\"spokenLevel\":0,\"communicationLevel\":0,\"logicLevel\":0,\"aptitudeLevel\":0,\"confidenceLevel\":0,\"strength\":null,\"weakness\":null,\"skills\":null}";
	//String associateDetailString =  "{\"associateId\":3,\"name\":\"name\",\"email\":\"\",\"mobile\":\"1213546\",\"pic\":\"com.mysql.jdbc.Blob@23f5ff57\",\"statusGreen\":false,\"statusBlue\":true,\"statusRed\":false,\"level1\":true,\"level2\":false,\"level3\":false,\"remark\":\"\",\"strength\":\"\",\"weakness\":\"\",\"skills\":[{\"skill_id\":1,\"skill_name\":\"Skill\",\"skill_level\":1}]}";
	//String associateDetailString = "{\"associateId\":3,\"name\":\"name\",\"email\":\"\",\"mobile\":\"1213546\",\"pic\":\"com.mysql.jdbc.Blob@23f5ff57\",\"statusGreen\":false,\"statusBlue\":true,\"statusRed\":false,\"level1\":true,\"level2\":false,\"level3\":false,\"remark\":\"\",\"strength\":\"\",\"weakness\":\"\",\"skills\":[{\"skill_id\":1,\"skill_name\":\"Skill\",\"skill_level\":1}]}";
	//String associateDetailString = "{\"associateId\":3,\"name\":\"name\",\"email\":\"\",\"mobile\":\"1213546\",\"pic\":\"com.mysql.jdbc.Blob@23f5ff57\",\"statusGreen\":false,\"statusBlue\":true,\"statusRed\":false,\"level1\":true,\"level2\":false,\"level3\":false,\"remark\":\"\",\"strength\":\"\",\"weakness\":\"\",\"skills\":[{\"skill_id\":1,\"skill_name\":\"Skill\",\"skill_level\":1}]}";
	//String associateDetailString = "{\"associateId\":2,\"name\":\"Heyya\",\"email\":\"afasd@gmailc.comqa\",\"mobile\":\"132231\",\"gender\":\"F\",\"pic\":\"9VG4u7i5cG4nkkP\",\"statusGreen\":false,\"statusBlue\":false,\"statusRed\":true,\"level1\":false,\"level2\":false,\"level3\":true,\"remark\":\"123\",\"spokenLevel\":5,\"communicationLevel\":5,\"logicLevel\":0,\"aptitudeLevel\":6,\"confidenceLevel\":3,\"strength\":\"111\",\"weakness\":\"212\",\"skills\":[{\"skill_id\":1,\"skill_name\":\"Hello\",\"skill_level\":11}]}";
	List<AssociateDetails> associateDetailsList = new ArrayList<AssociateDetails>();
	Response response = new Response("Success");

	@Test
	public void testGetAllAssociates() throws Exception {
		AssociateDetails associateDetails = new ObjectMapper().readValue(associateDetailString, AssociateDetails.class);
		associateDetailsList.add(associateDetails);

		Mockito.when(service.getAllAssociates())
				.thenReturn(new ResponseEntity<List<AssociateDetails>>(associateDetailsList, HttpStatus.OK));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getall")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals("[" + associateDetailString + "]", result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetAssociateById() throws Exception {
		AssociateDetails associateDetails = new ObjectMapper().readValue(associateDetailString, AssociateDetails.class);
		associateDetailsList.add(associateDetails);

		Mockito.when(service.getAssociateById("3"))
				.thenReturn(new ResponseEntity<AssociateDetails>(associateDetails, HttpStatus.OK));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getassociate/3")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(associateDetailString, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testSaveAssociate() throws Exception {

		MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some image".getBytes());
		Mockito.when(service.saveAssociate(Mockito.any(MultipartFile.class), Mockito.anyString()))
				.thenReturn(new ResponseEntity<Response>(response, HttpStatus.OK));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.multipart("/save").file(file)
				.param("associateDetails", associateDetailString);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals("{\"message\":\"Success\"}", result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testDeleteAssociate() throws Exception {

		Mockito.when(service.deleteAssociate(Mockito.any(AssociateDetails.class)))
				.thenReturn(new ResponseEntity<Response>(response, HttpStatus.OK));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/delete").content(associateDetailString)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals("{\"message\":\"Success\"}", result.getResponse().getContentAsString(), false);
	}
}