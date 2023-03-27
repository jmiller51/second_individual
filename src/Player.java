import java.util.ArrayList;

public class Player 
{
	private Room current;
	private ArrayList<Item> inventory;
	
	public Player(Room r)
	{
		this.current = r;
		this.inventory = new ArrayList<Item>();
	}
	
	public void setCurrent(Room r)
	{
		this.current = r;
	}
	
	public Room getCurrent()
	{
		return this.current;
	}
	
	public ArrayList<Item> getInventory()
	{
		return this.inventory;
	}
	
	public void printInventory()
	{
		if(this.inventory.isEmpty() || this.inventory.size() == 0)
		{
			System.out.println("There are no items in your inventory.");
		}
		else
		{
			String s = "The following items are in your inventory: ";
			for(Item i: this.inventory)
			{
				s = s + i.getName();
				if(!(i.getName().equals(inventory.get(inventory.size()-1).getName()))) //If this is the last item in the inventory
				{
					s = s + ", ";
				}
			}
			System.out.println(s);
		}
	}
	
	public void addItem(Item i)
	{
		this.inventory.add(i);
	}
	
	public void removeItem(Item i)
	{
		this.inventory.remove(i);
	}
}