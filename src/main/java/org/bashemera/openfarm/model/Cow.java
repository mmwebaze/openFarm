package org.bashemera.openfarm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cows")
public class Cow extends Animal {
	
	private boolean milkable;
	private String tagId;
	protected List<Cow> parents;
	protected List<Cow> children;
	
	public Cow(String name, String gender, Date dateOfBirth, Date dateOfDeath, List<Cow> parents, List<Cow> children, String tagId) {
		super(name, gender, dateOfBirth, dateOfDeath);
		this.tagId = tagId;
		this.parents = parents;
		this.children = children;
		
		if (gender.equalsIgnoreCase("F")) {
			this.milkable = true;
		}
	}
	
	public boolean isMilkable() {
		return milkable;
	}
	public void setMilkable(boolean milkable) {
		this.milkable = milkable;
	}
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public List<Cow> getParents() {
		return parents;
	}

	public void setParents(List<Cow> parents) {
		this.parents = parents;
	}

	public List<Cow> getChildren() {
		return children;
	}

	public void setChildren(List<Cow> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Cow [tagId=" + tagId + ", id=" + id + ", name=" + name + ", gender=" + gender + "]";
	}
}
