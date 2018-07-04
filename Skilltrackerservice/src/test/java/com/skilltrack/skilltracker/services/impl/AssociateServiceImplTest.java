package com.skilltrack.skilltracker.services.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.skilltrack.*;
import com.skilltrack.skilltracker.model.Associate;
import com.skilltrack.skilltracker.model.AssociateSkills;
import com.skilltrack.skilltracker.model.AssociateSkillsEmbeddableId;
import com.skilltrack.skilltracker.model.Skills;
import com.skilltrack.skilltracker.repo.AssociateRepository;
import com.skilltrack.skilltracker.req.AssociateDetails;
import com.skilltrack.skilltracker.req.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class AssociateServiceImplTest {

	@Mock
	AssociateRepository repository;

	@InjectMocks
	private AssociateServiceImpl service;
	
	
	Associate table = new Associate();
	List<Associate> associateLists = new ArrayList<Associate>();
	
	String associateDetailString = "{\"associateId\":3,\"name\":\"name\",\"email\":\"\",\"mobile\":\"1213546\",\"pic\":\"com.mysql.jdbc.Blob@23f5ff57\",\"statusGreen\":false,\"statusBlue\":true,\"statusRed\":false,\"level1\":true,\"level2\":false,\"level3\":false,\"remark\":\"\",\"strength\":\"\",\"weakness\":\"\",\"skills\":[{\"skill_id\":1,\"skill_name\":\"Skill\",\"skill_level\":1}]}";
	
	@Before
	public void setup() {
		table.setAssociate_id(1);
		table.setEmail("test@test.com");
		table.setLevel1(true);
		table.setLevel2(false);
		table.setLevel3(false);
		table.setMobile("12124584");
		table.setName("Name");
		table.setPic("PIC".getBytes());
		table.setRemark("Remark");
		table.setStatus_blue(false);
		table.setStatus_green(true);
		table.setStatus_red(false);
		table.setStrength("strength");
		table.setWeakness("weakness");
		AssociateSkills askillTable = new AssociateSkills();
		Skills skillTable = new Skills();
		skillTable.setSkill_id(1);
		skillTable.setSkill_name("skillName");
		AssociateSkillsEmbeddableId pk = new AssociateSkillsEmbeddableId();
		pk.setAssociate(table);
		pk.setSkills(skillTable);
		askillTable.setAsEid(pk);
		Set<AssociateSkills> skillSet = new HashSet<AssociateSkills>();
		skillSet.add(askillTable);
		table.setAssociateSkills(skillSet);
		associateLists.add(table);
	}

	@Test
	public void testGetAllAssociates() {

		Mockito.when(repository.findAll()).thenReturn(associateLists);
		ResponseEntity<List<AssociateDetails>> getResponse=service.getAllAssociates();
		assertEquals(1, getResponse.getBody().size());
	}
	@Test
	public void testGetAllAssociatesBadInputScenario() {

		Mockito.when(repository.findAll()).thenReturn(null);
		ResponseEntity<List<AssociateDetails>> getResponse=service.getAllAssociates();
		assertEquals(HttpStatus.NO_CONTENT, getResponse.getStatusCode());
	}
	@Test
	public void testGetAllAssociatesExceptionScenario() {

		Mockito.when(repository.findAll()).thenThrow(NullPointerException.class);
		ResponseEntity<List<AssociateDetails>> getResponse=service.getAllAssociates();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, getResponse.getStatusCode());
	}
	
	@Test
	public void testSaveAssociate() {
		Mockito.when(repository.save(Mockito.any(Associate.class))).thenReturn(table);
		MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some image".getBytes());
		ResponseEntity<Response> response=service.saveAssociate(file, associateDetailString);
		assertEquals("Success",response.getBody().getMessage());
	}
	@Test
	public void testSaveAssociateBadRequest() {
		Mockito.when(repository.save(Mockito.any(Associate.class))).thenReturn(table);
		MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some image".getBytes());
		ResponseEntity<Response> response=service.saveAssociate(file, "");
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	@Test
	public void testSaveAssociateExceptiont() {
		Mockito.when(repository.save(Mockito.any(Associate.class))).thenThrow(NullPointerException.class);
		MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some image".getBytes());
		ResponseEntity<Response> response=service.saveAssociate(file, associateDetailString);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testGetAssociateById() {
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(table));
		ResponseEntity<AssociateDetails> getResponse=service.getAssociateById("1");
		assertEquals(1, getResponse.getBody().getAssociateId());
	}
	
	@Test
	public void testGetAssociateByIdException() {
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(null);
		ResponseEntity<AssociateDetails> getResponse=service.getAssociateById("1");
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, getResponse.getStatusCode());
	}
	@Test
	public void testDeleteAssociate() throws Exception {
		AssociateDetails associateDetails = new ObjectMapper().readValue(associateDetailString, AssociateDetails.class);
		Mockito.doNothing().when(repository).delete(Mockito.any(Associate.class));
		ResponseEntity<Response> getResponse=service.deleteAssociate(associateDetails);
		assertEquals(HttpStatus.OK, getResponse.getStatusCode());
	}
	@Test
	public void testDeleteAssociateBadRequest() {
		Mockito.doNothing().when(repository).delete(Mockito.any(Associate.class));
		ResponseEntity<Response> response=service.deleteAssociate(null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	@Test
	public void testDeleteAssociateExceptiont()  throws Exception {
		AssociateDetails associateDetails = new ObjectMapper().readValue(associateDetailString, AssociateDetails.class);
		associateDetails.setPic(null);
		ResponseEntity<Response> response=service.deleteAssociate(associateDetails);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

}