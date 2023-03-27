public class Item 
{
	private String name;
	private String description;
	private int start;
	
	public Item(String n, String d, String s)
	{
		this.name = n;
		this.description = d;
		this.setStart(s);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String s)
	{
		this.name = s;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setDescription(String d)
	{
		this.description = d;
	}
	
	public void setStart(String s)
	{
		try
		{
			this.start = Math.abs(Integer.parseInt(s));
		}
		catch (NumberFormatException nfe)
		{
			this.start = -1;
		}
	}
	
	public void setStart(int s)
	{
		this.start = s;
	}
	
	public int getStart()
	{
		return this.start;
	}
}
