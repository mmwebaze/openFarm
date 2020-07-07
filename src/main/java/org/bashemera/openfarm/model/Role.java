package org.bashemera.openfarm.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "roles")
public class Role {

    @Id
    private String id;
    
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    @Size(min = 4, max = 50)
    private String name;
    
    @Size(min = 3, max = 50)
	@NotBlank
    private String code;
    
    public Role() {
    	super();
    }
    
    public Role(@Size(min = 4, max = 50) String name, @Size(min = 3, max = 50) @NotBlank String code) {
		super();
		this.name = name;
		this.code = code;
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
        this.name = name.toUpperCase();
    }

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", code=" + code + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
