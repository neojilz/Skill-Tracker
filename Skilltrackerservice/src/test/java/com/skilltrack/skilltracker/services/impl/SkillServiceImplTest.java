package com.skilltrack.skilltracker.services.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.skilltrack.skilltracker.model.Skills;
import com.skilltrack.skilltracker.repo.SkillsRepository;
import com.skilltrack.skilltracker.req.SkillRequest;

@RunWith(SpringRunner.class)
public class SkillServiceImplTest {
	
	
	@Mock
	SkillsRepository skillRepository;
	
	@InjectMocks
	SkillServiceImpl skillService;
	
	Skills skills = new Skills ();
	List<Skills> skillList =  new ArrayList<Skills>();
	SkillRequest skillReq = new SkillRequest();
	
	
	
	
	@Before
	public void setUp(){
		skillReq.setSkill_id(1);
		skillReq.setSkill_name("skill_name");
		skills.setSkill_id(1);
		skills.setSkill_name("skill_name");
	}
	
	@Test
	public void testSaveSkills(){
		Mockito.when(skillRepository.save(Mockito.any(Skills.class))).thenReturn(skills);
		String response = skillService.saveSkills(skillReq);
		assertEquals("success", response);
	}
	
	

}
