package com.skilltrack.skilltracker.services.intf;

import org.springframework.stereotype.Service;

import com.skilltrack.skilltracker.model.Skills;
import com.skilltrack.skilltracker.req.SkillRequest;
@Service
public interface SkillsServiceInt {

	String saveSkills(SkillRequest req);

	Iterable<Skills> getAllSkills();

	String addSkillForAssociate(int associateId,SkillRequest req);

}
