package com.skilltrack.skilltracker.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="skills_table")
public class Skills {

@Id
@Column
@GeneratedValue
private int skill_id;

@Column
private String skill_name;

//this way, an additional field required for AssociateSkills
/*@OneToMany(cascade =CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="skills",orphanRemoval=true)
private Set<AssociateSkills> associateskills = new HashSet<AssociateSkills>();
*/

//this way, you cant have skillslevel column as there is no entity.
/*@ManyToMany(fetch=FetchType.LAZY,mappedBy="skills")
private List<Associate> associate;*/


@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="asEid.skills",orphanRemoval=true)
private Set<AssociateSkills> associateSkills = new HashSet<AssociateSkills>(0);




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
public Set<AssociateSkills> getAssociateSkills() {
	return associateSkills;
}
public void setAssociateSkills(Set<AssociateSkills> associateSkills) {
	this.associateSkills = associateSkills;
}



	
}
