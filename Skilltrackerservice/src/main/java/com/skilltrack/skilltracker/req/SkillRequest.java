package com.skilltrack.skilltracker.req;

import javax.persistence.Column;

public class SkillRequest {
	
	private int skill_id;
	private String skill_name;
	private int skill_level;
	
	public int getSkill_id() {
		return skill_id;
	}
	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}
	public String getSkill_name() {
		return skill_name;
	}
	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}
	/**
	 * @return the skill_level
	 */
	public int getSkill_level() {
		return skill_level;
	}
	/**
	 * @param i the skill_level to set
	 */
	public void setSkill_level(int i) {
		this.skill_level = i;
	}

	
	
}
