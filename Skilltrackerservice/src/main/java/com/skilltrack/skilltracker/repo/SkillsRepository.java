package com.skilltrack.skilltracker.repo;

import org.springframework.data.repository.CrudRepository;
import com.skilltrack.skilltracker.model.Skills;


public interface SkillsRepository extends  CrudRepository<Skills,Long>{

}
