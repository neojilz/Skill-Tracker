package com.skilltrack.skilltracker.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
/* @Table(name = "associate_skills") */

@AssociationOverrides({ @AssociationOverride(name = "asEid.associate", joinColumns = @JoinColumn(name = "associate_id")),
		@AssociationOverride(name = "asEid.skills", joinColumns = @JoinColumn(name = "skill_id")) })
public class AssociateSkills {

	private int skill_value;

	@EmbeddedId
	private AssociateSkillsEmbeddableId asEid = new AssociateSkillsEmbeddableId();
	private int skillLevel;

	/*
	 * @Column private int associate_id;
	 * 
	 * @Column private int skill_id;
	 * 
	 * @Column
	 */

	/*
	 * @Id
	 * 
	 * @Column private int row_num;
	 */

	// this way, an additional field required for AssociateSkills
	/*
	 * @ManyToOne(fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name="associate_id",insertable=false,updatable=false) private
	 * Associate associate;
	 * 
	 * @ManyToOne(fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name="skills_id") private Skills skills;
	 */

	public int getSkill_value() {
		return skill_value;
	}

	public void setSkill_value(int skill_value) {
		this.skill_value = skill_value;
	}
	
	//
	public int getAssociateId() {
		return asEid.getAssociate().getAssociate_id();
	}
	
	public int getSkillsId() {
		return asEid.getSkills().getSkill_id();
	}
	
	public void setAssociateId(int associate_id) {
		this.asEid.getAssociate().setAssociate_id(associate_id);
	}
	
	public void setSkillId(int skill_id) {
		this.asEid.getSkills().setSkill_id(skill_id);;
	}

	/**
	 * @return the asEid
	 */
	public AssociateSkillsEmbeddableId getAsEid() {
		return asEid;
	}

	/**
	 * @param asEid the asEid to set
	 */
	public void setAsEid(AssociateSkillsEmbeddableId asEid) {
		this.asEid = asEid;
	}
	
	
	
	public Skills getSkills() {
		return getAsEid().getSkills();
	}

	public void setSkills(Skills skills) {
		getAsEid().setSkills(skills);
	}

	
	public Associate getAssociates() {
		return getAsEid().getAssociate();
	}

	public void setAssociates(Associate associates) {
		getAsEid().setAssociate(associates);
	}
	/**
	 * @return the skillLevel
	 */
	public int getSkillLevel() {
		return skillLevel;
	}

	/**
	 * @param skillLevel the skillLevel to set
	 */
	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}

	
	
}
