package com.skilltrack.skilltracker.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilltrack.skilltracker.model.Associate;
import com.skilltrack.skilltracker.model.AssociateSkills;
import com.skilltrack.skilltracker.model.Skills;
import com.skilltrack.skilltracker.repo.AssociateRepository;
import com.skilltrack.skilltracker.repo.SkillsRepository;
import com.skilltrack.skilltracker.req.Response;
import com.skilltrack.skilltracker.req.SkillRequest;
import com.skilltrack.skilltracker.services.intf.SkillsServiceInt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.commons.lang3.StringUtils;


@Service
public class SkillServiceImpl implements SkillsServiceInt {

	@Autowired
SkillsRepository skillRepository;
	
	@Autowired
AssociateRepository associateRepository;
	
	
	
	@Override
	@Cacheable("skills")
	public ResponseEntity<List<SkillRequest>> getAllSkills() {
		List<SkillRequest> skillList = new ArrayList<SkillRequest>();
		try {
			List<Skills> skillTableList = skillRepository.findAll();
			if (skillTableList != null && !skillTableList.isEmpty()) {
				for (Skills skillsTable : skillTableList) {
					SkillRequest skill = new SkillRequest();
					skill.setSkill_id(skillsTable.getSkill_id());
					skill.setSkill_name(skillsTable.getSkill_name());
					skillList.add(skill);
				}
				return new ResponseEntity<List<SkillRequest>>(skillList, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<SkillRequest>>(skillList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<List<SkillRequest>>(skillList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@CacheEvict(cacheNames= {"skills","associates"},allEntries = true)
	public ResponseEntity<Response> saveSkill(SkillRequest skill) {
		Skills skillTable = new Skills();
		try {
			if (skill != null) {
				skillTable.setSkill_id(skill.getSkill_id());
				skillTable.setSkill_name(skill.getSkill_name());
				skillRepository.save(skillTable);
				return new ResponseEntity<Response>(new Response("Success"), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(new Response("Insufficienrt data"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("Failed"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@CacheEvict(cacheNames= {"skills","associates"},allEntries = true)
	public ResponseEntity<Response> deleteSkill(SkillRequest skill) {
		Skills skillTable = new Skills();
		try {
			if (skill != null) {
				skillTable.setSkill_id(skill.getSkill_id());
				skillTable.setSkill_name(skill.getSkill_name());
				skillRepository.delete(skillTable);
				return new ResponseEntity<Response>(new Response("Success"), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(new Response("Insufficienrt data"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("Failed"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

		

	
	

	
}
