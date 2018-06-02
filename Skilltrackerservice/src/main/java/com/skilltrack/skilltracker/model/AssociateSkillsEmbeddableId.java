package com.skilltrack.skilltracker.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Embeddable
public class AssociateSkillsEmbeddableId implements Serializable {
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private  Associate associate;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Skills skills;

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}

	public Skills getSkills() {
		return skills;
	}

	public void setSkills(Skills skills) {
		this.skills = skills;
	}

	
	
	
}
