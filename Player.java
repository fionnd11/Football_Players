import java.io.Serializable;

// class for football player
public class Player implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private String playerId;
    private String firstName;
    private String surname;
    private String dob;
    private String club;
   
    public Player(String pid)
    {
        this.playerId = pid;
    }

    // constructor with 5 fields
    public Player(String pid, String fname, String sname, String dob, String club)
    {
        this(pid);
        this.firstName = fname;
        this.surname = sname; 
        this.dob = dob;
        this.club = club;    
    }

    // getter method for player ID
	public String getPlayerId() 
	{
		return playerId;
	}

    // setter method for player ID
	public void setPlayerId(String playerId) 
	{
		this.playerId = playerId;
	}

    // getter method for player First Name
	public String getFirstName() 
	{
		return firstName;
	}

    // setter method for player First Name
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

    // getter method for player Last Name
	public String getSurname() 
	{
		return surname;
	}

    // setter method for player Last Name
	public void setSurname(String surname) 
	{
		this.surname = surname;
	}
	
    // getter method for DoB
	public String getDob() 
	{
		return dob;
	}
	
	// Display player details in the message box whenever required
	public String toString()
	{
        return new String(this.playerId + "-" + this.firstName + "-" + this.surname + "-" + this.dob  + "-" + this.club);   
    }    
}