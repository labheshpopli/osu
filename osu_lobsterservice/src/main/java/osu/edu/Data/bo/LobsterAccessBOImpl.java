package osu.edu.Data.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import osu.edu.Data.dao.LobsterTransaction;
import osu.edu.Model.Lobster;
import osu.edu.Util.LobsterConstants;

public class LobsterAccessBOImpl implements LobsterAccessBO {
	
	@Autowired
	protected Lobster lobster;
	
	@Autowired
	protected LobsterTransaction lobsterTransaction;
	
	public List<Lobster> getAllLobsters() {
		List<Lobster> lobsterList;
		try {
			lobsterList = new ArrayList<Lobster>();
			lobsterList = lobsterTransaction.getAll();
		} catch (Exception ex) {
			throw ex;
		}
		return lobsterList;
	}
	
	@Override
	public Lobster getLobsterDetailsFromId(String id) {
		
		List<Lobster> lobsterList = lobsterTransaction.getAll();
		Lobster lobs = lobsterList.stream()
				.filter(lobster -> id.equalsIgnoreCase(lobster.getUniqueId()))
				.findAny()
				.orElse(null);
		return lobs;
	}

	@Override
	public String createLobster(Lobster lobster) {
		String result = "";
		if(getLobsterDetailsFromId(lobster.getUniqueId()) == null)
			result = lobsterTransaction.add(lobster);
		else
			result = LobsterConstants.LOBSTER_ALREADYEXIST;
		return result;
	}

	@Override
	public String updateLobster(Lobster lobster) {
		String result = "";
		if(getLobsterDetailsFromId(lobster.getUniqueId()) != null)
			result = lobsterTransaction.update(lobster);
		else
			result = LobsterConstants.LOBSTER_NOTFOUND;
		return result;
	}

	@Override
	public String deleteLobster(String uniqueId) {
		String result = "";
		if(getLobsterDetailsFromId(uniqueId) != null)
			result = lobsterTransaction.delete(uniqueId);
		else
			result = LobsterConstants.LOBSTER_NOTFOUND;
		return result;
	}
	
}
