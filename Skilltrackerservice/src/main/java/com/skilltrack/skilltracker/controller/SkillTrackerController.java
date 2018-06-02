package com.skilltrack.skilltracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(path = "/saveskills", method = RequestMethod.POST)
	public String addSkills(@RequestBody SkillRequest req) {

		return skillsServiceInt.saveSkills(req);

	}

	@RequestMapping(path = "/getallskills", method = RequestMethod.GET)
	public Iterable<Skills> getAllSkills() {
		return skillsServiceInt.getAllSkills();
	}
	
	@RequestMapping(path = "/addSkillForAssociate", method = RequestMethod.POST)
	public String addSkillForAssociate(@RequestParam int associateId,@RequestBody SkillRequest req) {
		return skillsServiceInt.addSkillForAssociate(associateId,req);
	}

}
