package org.bashemera.openfarm.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "Animals")
public class Animal {

	@Id
	private String id;
	
	@Size(min = 3, max = 50)
	private String name;
	
	@Size(min = 1, max = 1)
	@NotBlank
	private String gender; //TODO change this to a char
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfDeath;
	
	@DBRef
	private List<Animal> parents;
	
	@DBRef
	private List<Animal> children;
	
	private boolean milkable;
	
	@Indexed(unique=true) //Might need to turn this off
	@NotBlank
	private String tagId;
	
	@DBRef
	private AnimalType animalType;
	
	public Animal() {}
	
	public Animal(AnimalType animalType, String gender) {
		this.animalType = animalType;
		this.gender = gender;
	}
	
	public Animal(String name, String gender, Date dateOfBirth, Date dateOfDeath, List<Animal> parents, List<Animal> children,
			boolean milkable, String tagId, AnimalType animalType) {
		super();
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;
		this.parents = parents;
		this.children = children;
		this.milkable = milkable;
		this.tagId = tagId;
		this.animalType = animalType;
	}

	public AnimalType getAnimalType() {
		return animalType;
	}

	public void setAnimalType(AnimalType animalType) {
		this.animalType = animalType;
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

	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", tagId=" + tagId + ", animalType=" + animalType + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public List<Animal> getParents() {
		return parents;
	}

	public void setParents(List<Animal> parents) {
		this.parents = parents;
	}

	public List<Animal> getChildren() {
		return children;
	}

	public void setChildren(List<Animal> children) {
		this.children = children;
	}
	
}
