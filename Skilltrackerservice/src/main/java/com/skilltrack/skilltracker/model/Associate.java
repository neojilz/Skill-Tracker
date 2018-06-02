package com.skilltrack.skilltracker.model;

import java.sql.Blob;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="associate_table")
public class Associate {

	@Id
	@Column
	@GeneratedValue
	private int associate_id;
	@Column
	private String name; 
	@Column
	private String email;
	@Column
	private String mobile;
	@Column
	private Blob pic;
	@Column
	private boolean status_green;
	@Column
	private boolean status_blue;
	@Column
	private boolean status_red;
	@Column
	private boolean level1;
	@Column
	private boolean level2;
	@Column
	private boolean level3;
	@Column
	private String remark;
	@Column
	private String strength;
	@Column
	private String weakness;

	
	/*@OneToMany(cascade =CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="associate",orphanRemoval=true)
	private Set<AssociateSkills> associateskills = new HashSet<AssociateSkills>();*/
	
	/*@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="associate_skills",joinColumns={@JoinColumn(name="associate_id",nullable=false,updatable=false) },
	inverseJoinColumns= {@JoinColumn(name="skill_id",nullable=false,updatable=false)})
	private List<Skills> skills;
	*/
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="asEid.associate",orphanRemoval=true)
	private Set<AssociateSkills> associateSkills = new HashSet<AssociateSkills>();
	
	
	public int getAssociate_id() {
		return associate_id;
	}
	public void setAssociate_id(int associate_id) {
		this.associate_id = associate_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Blob getPic() {
		return pic;
	}
	public void setPic(Blob pic) {
		this.pic = pic;
	}
	public boolean isStatus_green() {
		return status_green;
	}
	public void setStatus_green(boolean status_green) {
		this.status_green = status_green;
	}
	public boolean isStatus_blue() {
		return status_blue;
	}
	public void setStatus_blue(boolean status_blue) {
		this.status_blue = status_blue;
	}
	public boolean isStatus_red() {
		return status_red;
	}
	public void setStatus_red(boolean status_red) {
		this.status_red = status_red;
	}
	public boolean isLevel1() {
		return level1;
	}
	public void setLevel1(boolean level1) {
		this.level1 = level1;
	}
	public boolean isLevel2() {
		return level2;
	}
	public void setLevel2(boolean level2) {
		this.level2 = level2;
	}
	public boolean isLevel3() {
		return level3;
	}
	public void setLevel3(boolean level3) {
		this.level3 = level3;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getWeakness() {
		return weakness;
	}
	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}
	
	public Set<AssociateSkills> getAssociateSkills() {
		return associateSkills;
	}
	public void setAssociateSkills(Set<AssociateSkills> associateSkills) {
		this.associateSkills = associateSkills;
	}
	

	
}
