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
	
	String associateDetailString = "{\"associateId\":3,\"name\":\"name\",\"email\":\"\",\"mobile\":\"1213546\",\"pic\":\"com.mysql.jdbc.Blob@23f5ff57\",\"statusGreen\":false,\"statusBlue\":true,\"statusRed\":false,\"level1\":true,\"level2\":false,\"level3\":false,\"remark\":\"\",\"strength\":\"\",\"weakness\":\"\",\"skills\":[{\"skill_id\":1,\"skill_name\":\"Skill\",\"skill_level\":1}]}";
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

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/associates/getall")
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

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/associates/getasociate/3")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(associateDetailString, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testSaveAssociate() throws Exception {

		MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some image".getBytes());
		Mockito.when(service.saveAssociate(Mockito.any(MultipartFile.class), Mockito.anyString()))
				.thenReturn(new ResponseEntity<Response>(response, HttpStatus.OK));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.multipart("/associates/save").file(file)
				.param("associateDetails", associateDetailString);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals("{\"message\":\"Success\"}", result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testDeleteAssociate() throws Exception {

		Mockito.when(service.deleteAssociate(Mockito.any(AssociateDetails.class)))
				.thenReturn(new ResponseEntity<Response>(response, HttpStatus.OK));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/associates/delete").content(associateDetailString)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals("{\"message\":\"Success\"}", result.getResponse().getContentAsString(), false);
	}
}