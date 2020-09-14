package osu.edu.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import osu.edu.Data.bo.LobsterAccessBO;
import osu.edu.Model.CommonResponse;
import osu.edu.Model.Lobster;
import osu.edu.Util.LobsterConstants;

public class LobsterServiceImpl implements LobsterService {
	
	@Autowired
	protected Lobster lobsterDetails;
	
	@Autowired
	protected LobsterAccessBO lobsterAccessBO;
	
	protected CommonResponse commonResp;
	
	@Override
	public CommonResponse getAllLobsters() {
		commonResp = new CommonResponse();
		List<Lobster> lobsterList = new ArrayList<Lobster>();
		try {
			lobsterList = lobsterAccessBO.getAllLobsters();
		} catch (Exception ex) {
			prepareCommonResponse(LobsterConstants.CR_EXCEPTION, LobsterConstants.CR_EXCEPTION, ex.getMessage(), null);
			return commonResp;
		}
		prepareCommonResponse(LobsterConstants.CR_OK, LobsterConstants.CR_OK, null, lobsterList);
		return commonResp;
	}

	
	@Override
	public CommonResponse getLobsterDetailsFromId(String id) {
		commonResp = new CommonResponse();
		try {
			lobsterDetails = lobsterAccessBO.getLobsterDetailsFromId(id);
			if(lobsterDetails != null)
				prepareCommonResponse(LobsterConstants.CR_OK, LobsterConstants.CR_OK, null, lobsterDetails);
			else
				prepareCommonResponse(LobsterConstants.CR_OK, LobsterConstants.CR_OK, null, LobsterConstants.LOBSTER_NOTFOUND);
		} catch (Exception ex) {
			prepareCommonResponse(LobsterConstants.CR_EXCEPTION, LobsterConstants.CR_EXCEPTION, ex.getMessage(), null);
			return commonResp;
		}
		return commonResp;
	}

	@Override
	public CommonResponse createLobster(Lobster lobster) {
		commonResp = new CommonResponse();
		try {
			String response = lobsterAccessBO.createLobster(lobster);
			if(LobsterConstants.LOBSTER_ALREADYEXIST.equalsIgnoreCase(response))
				prepareCommonResponse(LobsterConstants.CR_FAILURE, LobsterConstants.CR_FAILURE, null, LobsterConstants.LOBSTER_ALREADYEXIST);
			else
				prepareCommonResponse(LobsterConstants.CR_OK, LobsterConstants.LOBSTER_SUCCESSFULLY_ADDED, null, LobsterConstants.LOBSTER_SUCCESSFULLY_ADDED);
		} catch (Exception ex) {
			prepareCommonResponse(LobsterConstants.CR_EXCEPTION, LobsterConstants.CR_EXCEPTION, ex.getMessage(), null);
			return commonResp;
		}
		return commonResp;
	}

	@Override
	public CommonResponse updateLobster(Lobster lobster) {
		commonResp = new CommonResponse();
		try {
			String response = lobsterAccessBO.updateLobster(lobster);
			if(!LobsterConstants.LOBSTER_ALREADYEXIST.equalsIgnoreCase(response))
				prepareCommonResponse(LobsterConstants.CR_FAILURE, LobsterConstants.CR_FAILURE, null, LobsterConstants.LOBSTER_ALREADYEXIST);
			else
				prepareCommonResponse(LobsterConstants.CR_OK, LobsterConstants.LOBSTER_SUCCESSFULLY_ADDED, null, LobsterConstants.LOBSTER_SUCCESSFULLY_UPDATED);
		} catch (Exception ex) {
			prepareCommonResponse(LobsterConstants.CR_EXCEPTION, LobsterConstants.CR_EXCEPTION, ex.getMessage(), null);
			return commonResp;
		}
		return commonResp;
	}

	@Override
	public CommonResponse deleteLobster(String uniqueId) {
		commonResp = new CommonResponse();
		try {
			String response = lobsterAccessBO.deleteLobster(uniqueId);
			if(LobsterConstants.LOBSTER_NOTFOUND.equalsIgnoreCase(response))
				prepareCommonResponse(LobsterConstants.CR_FAILURE, LobsterConstants.CR_FAILURE, null, LobsterConstants.LOBSTER_NOTFOUND);
			else
				prepareCommonResponse(LobsterConstants.CR_OK, LobsterConstants.LOBSTER_SUCCESSFULLY_ADDED, null, LobsterConstants.LOBSTER_SUCCESSFULLY_DELETED);
		} catch (Exception ex) {
			prepareCommonResponse(LobsterConstants.CR_EXCEPTION, LobsterConstants.CR_EXCEPTION, ex.getMessage(), null);
			return commonResp;
		}
		return commonResp;
	}
	
	public void prepareCommonResponse(String status, String message, String errors, Object data) {
		
		commonResp.setstatus(status);
		commonResp.setmessage(message);
		commonResp.setErrors(errors);
		commonResp.setData(data);
	}
}
