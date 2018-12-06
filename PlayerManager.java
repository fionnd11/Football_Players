import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager implements Serializable 
{
	private static final long serialVersionUID = 1L;
	// Create an ArrayList to hold the player objects
	List<Player> players;
	// Constructor - instantiate players ArrayList
	
	public PlayerManager() 
	{
		// Creating an empty ArrayList
		players = new ArrayList<Player>();
	}

	public boolean add(Player p) 
	{
		try 
		{
			return players.add(p);
		} 
		catch (Exception error) 
		{
			error.printStackTrace();
			return false;
		}
	}

	public boolean delete(String pid) 
	{
		// Search for the Player by Jersey Number i.e. ID
		Player player = getPlayerByID(pid);
		// If a Player was found then delete the player
		if (player != null) 
		{
			return players.remove(player);
		} else 
		{
			// If no player was found Return false
			return false;
		}
	}

	public Player getPlayerByID(String pid) 
	{
		// Loop over arrayList for Player type elements in the players ArrayList do
		for (Player player : players) 
		{
			// No need to check for null as ArrayList is dynamic and fills holes
			if (player.getPlayerId().equals(pid)) 
			{
				return player;
			}
		}
		// Return null if pid was not found
		return null;
	}

	// Find a list of player by first name
	public List<Player> getPlayersByFirstName(String fname) 
	{
		// Create a new ArrayList to Hold Players with same names
		List<Player> sameNames = new ArrayList<Player>();
		// Loop over arrayList for Player type elements in the players ArrayList do
		for (Player player : players) 
		{
			// If I find a player with the given first name then add to list
			if (!(player.getFirstName() == null) && player.getFirstName().equalsIgnoreCase(fname)) 
			{
				sameNames.add(player);
			}
		}
		// Check if list has any players
		if (sameNames.size() > 0) 
		{
			// If players were found then return the list
			return sameNames;
		}
		// If no players were found with that first name then return null
		return null;
	}

	public int findTotalPlayers() 
	{
		// returns the current number of Players in the ArrayList
		return players.size();
	}
	
    public PlayerManager loadDB(String dbPath)
    {
    	PlayerManager pm = null;
    	try 
    	{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(dbPath));
			pm = (PlayerManager) in.readObject();
    		in.close();
    	} 
    	catch (Exception e) 
    	{
    		System.out.print("[Error] Cannont load DB. Cause: ");
    		e.printStackTrace();
    	}
		return pm;
    }    
}