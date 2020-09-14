package osu.edu.Data.dao;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import osu.edu.Model.Lobster;
import osu.edu.Model.LobstersLookupResponse;
import osu.edu.Util.LobsterConstants;

public class LobsterTransactionImpl implements LobsterTransaction{
	
	@Autowired
	protected LobstersLookupResponse lookupResponse;
	
	private File f = new File(LobsterConstants.JSON_FILE_PATH);
	
	private ObjectMapper mapper = null;
	
	public List<Lobster> getLobsters() {
		
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			String responseJson = new String(Files.readAllBytes(Paths.get(f.getAbsolutePath())));
			JsonNode rootNode = mapper.readTree(responseJson);
			ArrayNode result = (ArrayNode) rootNode.get(LobsterConstants.JSON_LOBSTERDATA_TAG);
			lookupResponse = new LobstersLookupResponse(result.size());
			
			result.forEach(jsonNode -> {
				try {
					lookupResponse.setLobsterList(mapper.readValue(jsonNode.toString(), Lobster.class));
				} catch (JsonProcessingException jpEx) {
					jpEx.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lookupResponse.getLobsterList();
	}

	@Override
	public List<Lobster> getAll() {
		
		return getLobsters();
	}

	@Override
	public String update(Lobster lobster) {
		mapper = new ObjectMapper();
		String result = "";
		try {
			String existingJson = new String(Files.readAllBytes(Paths.get(f.getAbsolutePath())));
			JsonNode rootNode = mapper.readTree(existingJson);
			ArrayNode anode = (ArrayNode)rootNode.get(LobsterConstants.JSON_LOBSTERDATA_TAG);
			
			anode.forEach(objNode -> {
				TextNode tNode = (TextNode)objNode.get(LobsterConstants.LOBSTER_ID);
				if(tNode.asInt() == Integer.parseInt(lobster.getUniqueId())) {
					
					((ObjectNode)objNode).remove(LobsterConstants.LOBSTER_NAME);
					((ObjectNode)objNode).put(LobsterConstants.LOBSTER_NAME,  new TextNode(lobster.getName()));
					
					((ObjectNode)objNode).remove(LobsterConstants.LOBSTER_DESCRIPTION);
					((ObjectNode)objNode).put(LobsterConstants.LOBSTER_DESCRIPTION,  new TextNode(lobster.getDescription()));
					
					((ObjectNode)objNode).remove(LobsterConstants.LOBSTER_SPECIES);
					((ObjectNode)objNode).put(LobsterConstants.LOBSTER_SPECIES,  new TextNode(lobster.getSpecies()));
				}
			});
			
			String finalVal = mapper.writeValueAsString(rootNode);
			FileChannel.open(Paths.get(f.getAbsolutePath()), StandardOpenOption.WRITE).truncate(0).close();
			Files.write(Paths.get(f.getAbsolutePath()), finalVal.getBytes(), StandardOpenOption.WRITE);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		result = LobsterConstants.CR_OK;
		return result;
	}

	@Override
	public String add(Lobster lobster) {
		mapper = new ObjectMapper();
		String result = "";
		try {
			ObjectNode lobsterNode = (ObjectNode)mapper.readTree(mapper.writeValueAsString(lobster));

			String existingJson = new String(Files.readAllBytes(Paths.get(f.getAbsolutePath())));
			JsonNode rootNode = mapper.readTree(existingJson);
			ArrayNode anode = (ArrayNode)rootNode.get(LobsterConstants.JSON_LOBSTERDATA_TAG);
			anode.add(lobsterNode);
			String finalVal = mapper.writeValueAsString(rootNode);
			
			FileChannel.open(Paths.get(f.getAbsolutePath()), StandardOpenOption.WRITE).truncate(0).close();
			Files.write(Paths.get(f.getAbsolutePath()), finalVal.getBytes(), StandardOpenOption.WRITE);
			
			result = LobsterConstants.CR_OK;
			
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String delete(String id) {
		mapper = new ObjectMapper();
		String result = "";
		try {
			String existingJson = new String(Files.readAllBytes(Paths.get(f.getAbsolutePath())));
			JsonNode rootNode = mapper.readTree(existingJson);
			ArrayNode anode = (ArrayNode)rootNode.get(LobsterConstants.JSON_LOBSTERDATA_TAG);
			
			for(int i = 0; i < anode.size(); i++) {
				ObjectNode oNode = (ObjectNode)anode.get(i);
				TextNode tNode = (TextNode)oNode.get(LobsterConstants.LOBSTER_ID);
				if(tNode.asInt() == Integer.parseInt(id)) {
					anode.remove(i);
					break;
				}
			}
			String finalVal = mapper.writeValueAsString(rootNode);
			FileChannel.open(Paths.get(f.getAbsolutePath()), StandardOpenOption.WRITE).truncate(0).close();
			Files.write(Paths.get(f.getAbsolutePath()), finalVal.getBytes(), StandardOpenOption.WRITE);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		result = LobsterConstants.CR_OK;
		return result;
	}
}
