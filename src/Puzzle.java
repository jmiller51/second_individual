public class Puzzle 
{
	private String name;
	private String text;
	private String answer;
	private int attempts;
	private int start;
	
	public Puzzle(String name, String text, String answer, String attempts, String start)
	{
		this.name = name;
		this.text = text;
		this.answer = answer;
		this.setAttempts(attempts);
		this.setStart(start);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getText()
	{
		return this.text;
	}
	
	public String getAnswer()
	{
		return this.answer;
	}
	
	public int getAttempts()
	{
		return this.attempts;
	}
	
	public int getStart()
	{
		return this.start;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public void setAnswer(String answer)
	{
		this.answer = answer;
	}
	
	public void setAttempts(String a)
	{
		try
		{
			this.attempts = Math.abs(Integer.parseInt(a));
		}
		catch (NumberFormatException nfe)
		{
			this.attempts = 0;
		}
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
}