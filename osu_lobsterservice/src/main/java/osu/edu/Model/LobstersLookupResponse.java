package osu.edu.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Component
@JsonInclude(Include.NON_NULL)
public class LobstersLookupResponse {

	private List<Lobster> lobsterList;

	public LobstersLookupResponse() {
		
	}
	
	public LobstersLookupResponse(int size) {
		lobsterList = new ArrayList<Lobster>(size);
	}

	public List<Lobster> getLobsterList() {
		return lobsterList;
	}

	public void setLobsterList(Lobster lobster) {
		this.lobsterList.add(lobster);
	}
}
