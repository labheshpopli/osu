package osu.edu.Data.bo;

import java.util.List;

import osu.edu.Model.Lobster;

public interface LobsterAccessBO {
	
	public List<Lobster> getAllLobsters();
	
	public Lobster getLobsterDetailsFromId(String id);
	
	public String createLobster(Lobster lobster);
	
	public String updateLobster(Lobster lobster);
	
	public String deleteLobster(String uniqueId);
	
}
