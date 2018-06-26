package com.skilltrack.skilltracker.services.intf;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skilltrack.skilltracker.model.Skills;
import com.skilltrack.skilltracker.req.Response;
import com.skilltrack.skilltracker.req.SkillRequest;
@Service
public interface SkillsServiceInt {

	ResponseEntity<Response> deleteSkill(SkillRequest skill);

	ResponseEntity<List<SkillRequest>> getAllSkills();

	ResponseEntity<Response> saveSkill(SkillRequest skill);



}
