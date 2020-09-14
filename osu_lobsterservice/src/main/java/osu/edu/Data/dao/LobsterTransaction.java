package osu.edu.Data.dao;

import java.util.List;

import osu.edu.Model.Lobster;

public interface LobsterTransaction {
	 
	public List<Lobster> getAll();
	
	public String add(Lobster lobster);
	
	public String update(Lobster lobster);
	
	public String delete(String id);
}
