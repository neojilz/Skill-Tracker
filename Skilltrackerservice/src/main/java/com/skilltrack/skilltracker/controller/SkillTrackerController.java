package com.skilltrack.skilltracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skilltrack.skilltracker.services.intf.*;
import com.skilltrack.skilltracker.model.Skills;
import com.skilltrack.skilltracker.req.*;

@CrossOrigin(origins="*")
@RestController
public class SkillTrackerController {

	@Autowired
	AssociateServicesInt associateServicesInt;

	@Autowired
	SkillsServiceInt skillsServiceInt;

	// Skill Rest calls
	

	@RequestMapping(value="/getallskills",method = RequestMethod.GET)
	public ResponseEntity<List<SkillRequest>> getAllSkills(){
		
		return skillsServiceInt.getAllSkills();
		
	}
	
	@RequestMapping(value="/saveskills",method = RequestMethod.POST)
	public ResponseEntity<Response> saveSkill(@RequestBody SkillRequest skill){
		
		return skillsServiceInt.saveSkill(skill);
		
	}
	
	@RequestMapping(value="/deleteskill",method = RequestMethod.POST)
	public ResponseEntity<Response> deleteSkill(@RequestBody SkillRequest skill){
		
		return skillsServiceInt.deleteSkill(skill);
		
	}
}
