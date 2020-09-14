package osu.edu.Service;

import osu.edu.Model.CommonResponse;
import osu.edu.Model.Lobster;

public interface LobsterService {
	 
	public CommonResponse getAllLobsters();
	
	public CommonResponse getLobsterDetailsFromId(String id);
	
	public CommonResponse createLobster(Lobster lobster);
	
	public CommonResponse updateLobster(Lobster lobster);
	
	public CommonResponse deleteLobster(String uniqueId);
}
