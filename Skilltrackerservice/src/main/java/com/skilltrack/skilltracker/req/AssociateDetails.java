package com.skilltrack.skilltracker.req;


import java.util.List;

import com.skilltrack.skilltracker.model.Skills;

public class AssociateDetails {

	private int associateId;

	private String name;

	private String email;

	private String mobile;
	
	private String gender;

	private String pic;

	private boolean statusGreen;

	private boolean statusBlue;

	private boolean statusRed;

	private boolean level1;

	private boolean level2;

	private boolean level3;
  
	private String remark;

	private int spokenLevel;
	
	private int communicactionLevel;
	
	private int logicLevel;
	
	private int aptitudeLevel;
	
	private int confidenceLevel;
	
	private String strength;

	private String weakness;
	
	private List<SkillRequest> skills;
	
	
	


	public int getAssociateId() {
		return associateId;
	}

	public void setAssociateId(int associateId) {
		this.associateId = associateId;
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

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public boolean isStatusGreen() {
		return statusGreen;
	}

	public void setStatusGreen(boolean statusGreen) {
		this.statusGreen = statusGreen;
	}

	public boolean isStatusBlue() {
		return statusBlue;
	}

	public void setStatusBlue(boolean statusBlue) {
		this.statusBlue = statusBlue;
	}

	public boolean isStatusRed() {
		return statusRed;
	}

	public void setStatusRed(boolean statusRed) {
		this.statusRed = statusRed;
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

	public List<SkillRequest> getSkills() {
		return skills;
	}

	
	public void setSkills(List<SkillRequest> skills) {
		this.skills = skills;
	}

	public int getSpokenLevel() {
		return spokenLevel;
	}

	public void setSpokenLevel(int spokenLevel) {
		this.spokenLevel = spokenLevel;
	}

	public int getCommunicactionLevel() {
		return communicactionLevel;
	}

	public void setCommunicactionLevel(int communicactionLevel) {
		this.communicactionLevel = communicactionLevel;
	}

	public int getLogicLevel() {
		return logicLevel;
	}

	public void setLogicLevel(int logicLevel) {
		this.logicLevel = logicLevel;
	}

	public int getAptitudeLevel() {
		return aptitudeLevel;
	}

	public void setAptitudeLevel(int aptitudeLevel) {
		this.aptitudeLevel = aptitudeLevel;
	}

	public int getConfidenceLevel() {
		return confidenceLevel;
	}

	public void setConfidenceLevel(int confidenceLevel) {
		this.confidenceLevel = confidenceLevel;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
