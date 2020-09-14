package osu.edu.Model;

import static osu.edu.Util.LobsterConstants.LOBSTER_DESCRIPTION;
import static osu.edu.Util.LobsterConstants.LOBSTER_ID;
import static osu.edu.Util.LobsterConstants.LOBSTER_NAME;
import static osu.edu.Util.LobsterConstants.LOBSTER_SPECIES;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lobster {
	
	public Lobster() {

	}

	public Lobster(String uniqueId, String name, String description, String species) {
		this.uniqueId = uniqueId;
		this.name = name;
		this.description = description;
		this.species = species;
	}

	@JsonProperty(LOBSTER_ID)
	private String uniqueId;
	
	@JsonProperty(LOBSTER_NAME)
	private String name;
	
	@JsonProperty(LOBSTER_DESCRIPTION)
	private String description;
	
	@JsonProperty(LOBSTER_SPECIES)
	private String species;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}
}
