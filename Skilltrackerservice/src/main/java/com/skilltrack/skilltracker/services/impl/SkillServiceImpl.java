package com.skilltrack.skilltracker.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilltrack.skilltracker.model.Associate;
import com.skilltrack.skilltracker.model.AssociateSkills;
import com.skilltrack.skilltracker.model.Skills;
import com.skilltrack.skilltracker.repo.AssociateRepository;
import com.skilltrack.skilltracker.repo.SkillsRepository;
import com.skilltrack.skilltracker.req.SkillRequest;
import com.skilltrack.skilltracker.services.intf.SkillsServiceInt;

import java.util.List;

import org.apache.commons.lang3.StringUtils;


@Service
public class SkillServiceImpl implements SkillsServiceInt {

	@Autowired
SkillsRepository skillRepository;
	
	@Autowired
AssociateRepository associateRepository;
	
	@Override
	public String saveSkills(SkillRequest req) {
		Skills skills = new Skills();
		skills.setSkill_name(req.getSkill_name());
		skills.setSkill_id(req.getSkill_id());
		skillRepository.save(skills);
		return "success";
	}
	
	@Override
	public Iterable<Skills> getAllSkills() {
			Iterable<Skills>skillsList = skillRepository.findAll();
		return skillsList;
	}

	@Override
	public String addSkillForAssociate(int associateId,SkillRequest req) {
		Skills skills = new Skills();
		skills.setSkill_name(req.getSkill_name());
		skills.setSkill_id(req.getSkill_id());
		skillRepository.save(skills);
		Associate associate=associateRepository.findById(associateId).get();
		//Yet to code and test.
		/*AssociateSkills associateSkills = new AssociateSkills();
		associateSkills.setAssociateId(associate_id);*/
		return "success";
	}

}
