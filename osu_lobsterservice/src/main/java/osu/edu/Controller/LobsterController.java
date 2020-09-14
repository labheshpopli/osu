package osu.edu.Controller;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import osu.edu.Config.ProtectedResource;
import osu.edu.Model.CommonResponse;
import osu.edu.Model.Lobster;
import osu.edu.Service.LobsterService;
import osu.edu.Util.LobsterConstants;


@RestController
@RequestMapping("/lobster")
public class LobsterController {
	
	@Autowired
	private LobsterService lobsterService;
	
	@GetMapping("/list")
	@ProtectedResource
    public Response getAllLobsters() {
		
		CommonResponse resp;
		resp = lobsterService.getAllLobsters();

		if (resp.getstatus().equals(LobsterConstants.CR_OK)) {
			return Response.ok().entity(resp).build();
		} else {
			return Response.status(400).entity(resp).build();
		}
	
	}
	
	@GetMapping("/lookup/{uniqueId}")
	@ProtectedResource
    public Response getLobsterDetailsFromId(@PathVariable("uniqueId") String uniqueId) {
		
		CommonResponse resp;
		resp = lobsterService.getLobsterDetailsFromId(uniqueId);
		
		if (resp.getstatus().equals(LobsterConstants.CR_OK)) {
			return Response.ok().entity(resp).build();
		} else {
			return Response.status(400).entity(resp).build();
		}
	}
	
	@PostMapping("/add")
	@ProtectedResource
    public Response createLobster(@Valid @RequestBody Lobster lobsterCreateRequest) {
		
		CommonResponse resp;
		resp = lobsterService.createLobster(lobsterCreateRequest);

		if (resp.getstatus().equals(LobsterConstants.CR_OK)) {
			return Response.ok().entity(resp).build();
		} else {
			return Response.status(400).entity(resp).build();
		}
	}
	
	@PutMapping("/update")
	@ProtectedResource
    public Response updateLobster(@Valid @RequestBody Lobster lobsterUpdateRequest) {
		
		CommonResponse resp;
		resp = lobsterService.updateLobster(lobsterUpdateRequest);
		
		if (resp.getstatus().equals(LobsterConstants.CR_OK)) {
			return Response.ok().entity(resp).build();
		} else {
			return Response.status(400).entity(resp).header("Cache-Control", "no-cache").build();
		}
	}
	
	@DeleteMapping("/remove/{uniqueId}")
	@ProtectedResource
    public Response deleteLobster(@PathVariable("uniqueId") String uniqueId) {
		
		CommonResponse resp;
		resp = lobsterService.deleteLobster(uniqueId);
		
		if (resp.getstatus().equals(LobsterConstants.CR_OK)) {
			return Response.ok().entity(resp).build();
		} else {
			return Response.status(400).entity(resp).build();
		}
	}
}