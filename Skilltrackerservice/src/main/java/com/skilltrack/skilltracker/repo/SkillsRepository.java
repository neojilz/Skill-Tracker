package com.skilltrack.skilltracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.skilltrack.skilltracker.model.Skills;


public interface SkillsRepository extends  JpaRepository<Skills,Long>{

}
