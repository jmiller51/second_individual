import java.util.ArrayList;

public class Room 
{
	private int id;
	private String name;
	private int north;
	private int east;
	private int south;
	private int west;
	private ArrayList<Item> items;
	private Puzzle puzzle;
	private boolean visited;
	
	public Room(String id, String name, String n, String e, String s, String w)
	{
		this.setId(id);
		this.name = name;
		this.setNorth(n);
		this.setEast(e);
		this.setSouth(s);
		this.setWest(w);
		this.items = new ArrayList<Item>();
		this.puzzle = null;
		this.visited = false;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getNorth()
	{
		return this.north;
	}
	
	public int getSouth()
	{
		return this.south;
	}
	
	public int getEast()
	{
		return this.east;
	}
	
	public int getWest()
	{
		return this.west;
	}
	
	public Puzzle getPuzzle()
	{
		return this.puzzle;
	}
	
	public ArrayList<Item> getItems()
	{
		return this.items;
	}
	
	public void setId(String id)
	{
		try
		{
			this.id = Math.abs(Integer.parseInt(id));
		}
		catch (NumberFormatException nfe)
		{
			this.id = -1;
		}
	}
	
	public void setNorth(String n)
	{
		try
		{
			this.north = Math.abs(Integer.parseInt(n));
		}
		catch (NumberFormatException nfe)
		{
			this.north = 0;
		}
	}
	
	public void setSouth(String s)
	{
		try
		{
			this.south = Math.abs(Integer.parseInt(s));
		}
		catch (NumberFormatException nfe)
		{
			this.south = 0;
		}
	}
	
	public void setEast(String e)
	{
		try
		{
			this.east = Math.abs(Integer.parseInt(e));
		}
		catch (NumberFormatException nfe)
		{
			this.east = 0;
		}
	}
	
	public void setWest(String w)
	{
		try
		{
			this.west = Math.abs(Integer.parseInt(w));
		}
		catch (NumberFormatException nfe)
		{
			this.west = 0;
		}
	}
	
	public void setPuzzle(Puzzle p)
	{
		this.puzzle = p;
	}
	
	public boolean hasPuzzle()
	{
		try
		{
			this.puzzle.getName();
			return true;
		}
		catch(NullPointerException npe)
		{
			return false;
		}
	}
	
	public void removePuzzle()
	{
		this.puzzle = null;
	}
	
	public void addItem(Item i)
	{
		this.items.add(i);
	}
	
	public void removeItem(Item i)
	{
		this.items.remove(i);
	}
	
	public void explore()
	{
		if(this.items.isEmpty() || this.items.size() == 0)
		{
			System.out.println("There are no items in this room.");
		}
		else
		{
			String s = "The following items are in the room: ";
			for(Item i: this.items)
			{
				s = s + i.getName();
				if(!(i.getName().equals(items.get(items.size()-1).getName()))) //If this is the last item in room's arraylist of items
				{
					s = s + ", ";
				}
			}
			System.out.println(s);
		}
	}
	
	public void visit()
	{
		this.visited = true;
	}
	
	public boolean visited()
	{
		return this.visited;
	}
	
	
	public String toString()
	{
		return "You are now in the " + this.name + ".";
	}
}