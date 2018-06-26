package com.skilltrack.skilltracker.services.impl;

import java.util.List;

import com.skilltrack.skilltracker.req.AssociateDetails;
import com.skilltrack.skilltracker.req.Response;
import com.skilltrack.skilltracker.req.SkillRequest;
import com.skilltrack.skilltracker.services.intf.AssociateServicesInt;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.skilltrack.skilltracker.model.Associate;
import com.skilltrack.skilltracker.model.AssociateSkills;
import com.skilltrack.skilltracker.model.Skills;
import com.skilltrack.skilltracker.repo.*;
/*import com.cts.fsd.skilltracker.repository.AssociateRepository;
import com.cts.fsd.skilltracker.repository.entity.AssociateSkillsTable;
import com.cts.fsd.skilltracker.repository.entity.AssociateTable;
import com.cts.fsd.skilltracker.repository.entity.SkillsTable;*/
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AssociateServiceImpl implements AssociateServicesInt {

	@Autowired
	AssociateRepository repository;

	@Override
	@Cacheable("associates")
	public ResponseEntity<List<AssociateDetails>> getAllAssociates() {
		List<AssociateDetails> associateList = new ArrayList<>();
		try {
			List<Associate> associateTableList = repository.findAll();
			if (associateTableList != null && !associateTableList.isEmpty()) {
				for (Associate associateTable : associateTableList) {
					AssociateDetails details = new AssociateDetails();
					details.setAssociateId(associateTable.getAssociate_id());
					details.setEmail(associateTable.getEmail());
					details.setLevel1(associateTable.isLevel1());
					details.setLevel2(associateTable.isLevel2());
					details.setLevel3(associateTable.isLevel3());
					details.setMobile(associateTable.getMobile());
					details.setName(associateTable.getName());
					details.setGender(associateTable.getGender());
					details.setPic(Base64.getEncoder().encodeToString(associateTable.getPic()));
					details.setRemark(associateTable.getRemark());
					details.setStatusBlue(associateTable.isStatus_blue());
					details.setStatusGreen(associateTable.isStatus_green());
					details.setStatusRed(associateTable.isStatus_red());
					details.setStrength(associateTable.getStrength());
					details.setWeakness(associateTable.getWeakness());
					details.setAptitudeLevel(associateTable.getAptitudeLevel());
					details.setCommunicactionLevel(associateTable.getCommunicactionLevel());
					details.setConfidenceLevel(associateTable.getConfidenceLevel());
					details.setLogicLevel(associateTable.getLogicLevel());
					details.setSpokenLevel(associateTable.getSpokenLevel());
					List<SkillRequest> skills = new ArrayList<SkillRequest>();
					for (AssociateSkills associateSkillTable : associateTable.getAssociateSkills()) {
						SkillRequest skiillReq = new SkillRequest();
						skiillReq.setSkill_id(associateSkillTable.getSkills().getSkill_id());
						skiillReq.setSkill_name(associateSkillTable.getSkills().getSkill_name());
						skiillReq.setSkill_level(associateSkillTable.getSkillLevel());
						skills.add(skiillReq);
					}
					details.setSkills(skills);
					associateList.add(details);
				}

				return new ResponseEntity<List<AssociateDetails>>(associateList, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<AssociateDetails>>(associateList, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<List<AssociateDetails>>(associateList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@CacheEvict(cacheNames = "associates", allEntries = true)
	public ResponseEntity<Response> saveAssociate(MultipartFile file, String associateDetailString) {
		Associate associateTable = new Associate();

		try {
			if (!StringUtils.isEmpty(associateDetailString)) {

				AssociateDetails associateDetails = new ObjectMapper().readValue(associateDetailString,
						AssociateDetails.class);

				associateTable.setAssociate_id(associateDetails.getAssociateId());
				associateTable.setEmail(associateDetails.getEmail());
				associateTable.setLevel1(associateDetails.isLevel1());
				associateTable.setLevel2(associateDetails.isLevel2());
				associateTable.setLevel3(associateDetails.isLevel3());
				associateTable.setMobile(associateDetails.getMobile());
				associateTable.setName(associateDetails.getName());
				associateTable.setGender(associateDetails.getGender());
				associateTable.setPic(file.getBytes());
				associateTable.setRemark(associateDetails.getRemark());
				associateTable.setStatus_blue(associateDetails.isStatusBlue());
				associateTable.setStatus_green(associateDetails.isStatusGreen());
				associateTable.setStatus_red(associateDetails.isStatusRed());
				associateTable.setStrength(associateDetails.getStrength());
				associateTable.setWeakness(associateDetails.getWeakness());
				associateTable.setAptitudeLevel(associateDetails.getAptitudeLevel());
				associateTable.setCommunicactionLevel(associateDetails.getCommunicactionLevel());
				associateTable.setConfidenceLevel(associateDetails.getConfidenceLevel());
				associateTable.setLogicLevel(associateDetails.getLogicLevel());
				associateTable.setSpokenLevel(associateDetails.getSpokenLevel());
				Set<AssociateSkills> associateSkillsTableList = new HashSet<AssociateSkills>();
				if (associateDetails.getSkills() != null) {
					for (SkillRequest skill : associateDetails.getSkills()) {
						Skills skillTable = new Skills();
						skillTable.setSkill_id(skill.getSkill_id());
						skillTable.setSkill_name(skill.getSkill_name());
						AssociateSkills associateSkillsTable = new AssociateSkills();
						associateSkillsTable.setAssociates(associateTable);
						associateSkillsTable.setSkills(skillTable);
						associateSkillsTable.setSkillLevel(skill.getSkill_level());
						associateSkillsTableList.add(associateSkillsTable);
					}
				}
				associateTable.setAssociateSkills(associateSkillsTableList);
				repository.save(associateTable);
				return new ResponseEntity<Response>(new Response("Success"), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(new Response("Insufficient data"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("Failed"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Cacheable(value = "associates", key = "#id")
	public ResponseEntity<AssociateDetails> getAssociateById(String id) {
		AssociateDetails details = new AssociateDetails();
		try {
			if (id != null) {
				int idNum = Integer.parseInt(id);
				Associate associateTable = repository.findById(idNum).get();

				details.setAssociateId(associateTable.getAssociate_id());
				details.setEmail(associateTable.getEmail());
				details.setLevel1(associateTable.isLevel1());
				details.setLevel2(associateTable.isLevel2());
				details.setLevel3(associateTable.isLevel3());
				details.setMobile(associateTable.getMobile());
				details.setName(associateTable.getName());
				details.setGender(associateTable.getGender());
				details.setPic(Base64.getEncoder().encodeToString(associateTable.getPic()));
				details.setRemark(associateTable.getRemark());
				details.setStatusBlue(associateTable.isStatus_blue());
				details.setStatusGreen(associateTable.isStatus_green());
				details.setStatusRed(associateTable.isStatus_red());
				details.setStrength(associateTable.getStrength());
				details.setWeakness(associateTable.getWeakness());
				details.setAptitudeLevel(associateTable.getAptitudeLevel());
				details.setCommunicactionLevel(associateTable.getCommunicactionLevel());
				details.setConfidenceLevel(associateTable.getConfidenceLevel());
				details.setLogicLevel(associateTable.getLogicLevel());
				details.setSpokenLevel(associateTable.getSpokenLevel());
				List<SkillRequest> skills = new ArrayList<SkillRequest>();
				for (AssociateSkills associateSkillTable : associateTable.getAssociateSkills()) {
					SkillRequest skill = new SkillRequest();
					skill.setSkill_id(associateSkillTable.getSkills().getSkill_id());
					skill.setSkill_name(associateSkillTable.getSkills().getSkill_name());
					skill.setSkill_level(associateSkillTable.getSkillLevel());
					skills.add(skill);
				}
				details.setSkills(skills);
				return new ResponseEntity<AssociateDetails>(details, HttpStatus.OK);
			} else {
				return new ResponseEntity<AssociateDetails>(details, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<AssociateDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@CacheEvict(cacheNames = "associates", allEntries = true)
	public ResponseEntity<Response> deleteAssociate(AssociateDetails associateDetails) {
		Associate associateTable = new Associate();
		try {
			if (associateDetails != null) {
				associateTable.setAssociate_id(associateDetails.getAssociateId());
				associateTable.setEmail(associateDetails.getEmail());
				associateTable.setLevel1(associateDetails.isLevel1());
				associateTable.setLevel2(associateDetails.isLevel2());
				associateTable.setLevel3(associateDetails.isLevel3());
				associateTable.setMobile(associateDetails.getMobile());
				associateTable.setName(associateDetails.getName());
				associateTable.setGender(associateDetails.getGender());
				associateTable.setPic(associateDetails.getPic().getBytes());
				associateTable.setRemark(associateDetails.getRemark());
				associateTable.setStatus_blue(associateDetails.isStatusBlue());
				associateTable.setStatus_green(associateDetails.isStatusGreen());
				associateTable.setStatus_red(associateDetails.isStatusRed());
				associateTable.setStrength(associateDetails.getStrength());
				associateTable.setWeakness(associateDetails.getWeakness());
				associateTable.setAptitudeLevel(associateDetails.getAptitudeLevel());
				associateTable.setCommunicactionLevel(associateDetails.getCommunicactionLevel());
				associateTable.setConfidenceLevel(associateDetails.getConfidenceLevel());
				associateTable.setLogicLevel(associateDetails.getLogicLevel());
				associateTable.setSpokenLevel(associateDetails.getSpokenLevel());
				Set<AssociateSkills> associateSkillsTableList = new HashSet<AssociateSkills>();
				for (SkillRequest skill : associateDetails.getSkills()) {
					Skills skillTable = new Skills();
					skillTable.setSkill_id(skill.getSkill_id());
					skillTable.setSkill_name(skill.getSkill_name());
					AssociateSkills associateSkillsTable = new AssociateSkills();
					associateSkillsTable.setAssociates(associateTable);
					associateSkillsTable.setSkills(skillTable);
					associateSkillsTable.setSkillLevel(skill.getSkill_level());
					associateSkillsTableList.add(associateSkillsTable);
				}
				associateTable.setAssociateSkills(associateSkillsTableList);
				repository.delete(associateTable);
				return new ResponseEntity<Response>(new Response("Success"), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(new Response("Insufficienrt data"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response("Failed"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
