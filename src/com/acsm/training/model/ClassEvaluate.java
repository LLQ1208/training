package com.acsm.training.model;

// Generated 2018-2-28 19:20:23 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ClassEvaluate generated by hbm2java
 */
@Entity
@Table(name = "class_evaluate")
public class ClassEvaluate implements java.io.Serializable {

	private int id;
	private int courseScheduleId;
	private String studentName;
	private String studentPhone;
	private Integer areaId;
	private Integer satisfaction;
	private Integer accommodations;
	private Integer considerate;
	private Integer rationality;
	private Integer serviceAttitude;
	private Integer gain;

	public ClassEvaluate() {
	}

	public ClassEvaluate(int id, int courseScheduleId) {
		this.id = id;
		this.courseScheduleId = courseScheduleId;
	}

	public ClassEvaluate(int id, int courseScheduleId, String studentName,
			String studentPhone, Integer areaId, Integer satisfaction,
			Integer accommodations, Integer considerate, Integer rationality,
			Integer serviceAttitude, Integer gain) {
		this.id = id;
		this.courseScheduleId = courseScheduleId;
		this.studentName = studentName;
		this.studentPhone = studentPhone;
		this.areaId = areaId;
		this.satisfaction = satisfaction;
		this.accommodations = accommodations;
		this.considerate = considerate;
		this.rationality = rationality;
		this.serviceAttitude = serviceAttitude;
		this.gain = gain;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "course_schedule_id", nullable = false)
	public int getCourseScheduleId() {
		return this.courseScheduleId;
	}

	public void setCourseScheduleId(int courseScheduleId) {
		this.courseScheduleId = courseScheduleId;
	}

	@Column(name = "student_name")
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Column(name = "student_phone")
	public String getStudentPhone() {
		return this.studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	@Column(name = "area_id")
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "satisfaction")
	public Integer getSatisfaction() {
		return this.satisfaction;
	}

	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}

	@Column(name = "accommodations")
	public Integer getAccommodations() {
		return this.accommodations;
	}

	public void setAccommodations(Integer accommodations) {
		this.accommodations = accommodations;
	}

	@Column(name = "considerate")
	public Integer getConsiderate() {
		return this.considerate;
	}

	public void setConsiderate(Integer considerate) {
		this.considerate = considerate;
	}

	@Column(name = "rationality")
	public Integer getRationality() {
		return this.rationality;
	}

	public void setRationality(Integer rationality) {
		this.rationality = rationality;
	}

	@Column(name = "service_attitude")
	public Integer getServiceAttitude() {
		return this.serviceAttitude;
	}

	public void setServiceAttitude(Integer serviceAttitude) {
		this.serviceAttitude = serviceAttitude;
	}

	@Column(name = "gain")
	public Integer getGain() {
		return this.gain;
	}

	public void setGain(Integer gain) {
		this.gain = gain;
	}

}
