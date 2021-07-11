package com.demo.jpa.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Marks {

	private Integer english;
	private Integer maths;
	private Integer science;

	protected Marks() {
	}

	public Marks(Integer english, Integer maths, Integer science) {
		super();
		this.english = english;
		this.maths = maths;
		this.science = science;
	}

	public Integer getEnglish() {
		return english;
	}

	public void setEnglish(Integer english) {
		this.english = english;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	public Integer getScience() {
		return science;
	}

	public void setScience(Integer science) {
		this.science = science;
	}

}
