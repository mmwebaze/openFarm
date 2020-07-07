package org.bashemera.openfarm.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Animal_types")
public class AnimalType {
	
	@Id
	private String id;
	
	@Size(min = 3, max = 50)
	@NotBlank
	private String name;

	public AnimalType(String name) {
		super();
		this.name = name;
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

	@Override
	public String toString() {
		return "AnimalType [id=" + id + ", name=" + name + "]";
	}
}
