package com.startcaft.json.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Classz implements Serializable {

	private List<Student> students = new ArrayList<Student>();

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
