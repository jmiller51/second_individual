import java.io.*;
import java.util.*;
public class Game 
{
	private static Scanner input = new Scanner(System.in);
	private Scanner sc1;
	private Scanner sc2;
	private Scanner sc3;
	private FileReader fr1;
	private FileReader fr2;
	private FileReader fr3;
	private ArrayList<Room> rooms;
	private ArrayList<Item> items;
	private ArrayList<Puzzle> puzzles;
	
	public Game()
	{
		this.rooms = new ArrayList<Room>();
		this.items = new ArrayList<Item>();
		this.puzzles = new ArrayList<Puzzle>();
		
		//Setting up the file reader + scanner pairs
		
		try
		{
			this.fr1 = new FileReader("Rooms.txt");
			this.sc1 = new Scanner(fr1);
		}
		catch (FileNotFoundException fnf)
		{
			System.out.println("The data file for the rooms could not be located.");
			System.exit(0);
		}
		try
		{
			this.fr2 = new FileReader("Items.txt");
			this.sc2 = new Scanner(fr2);
		}
		catch (FileNotFoundException fnf)
		{
			System.out.println("The data file for the items could not be located.");
			System.exit(0);
		}
		try
		{
			this.fr3 = new FileReader("Puzzles.txt");
			this.sc3 = new Scanner(fr3);
		}
		catch (FileNotFoundException fnf)
		{
			System.out.println("The data file for the puzzles could not be located.");
		}

	}
	
	public void readFile()
	{
		while (sc2.hasNext()) //sc2 reads the input file Items.txt and converts them to Item objects that the program can read.
		{
			String s1 = sc2.nextLine();
			String s2 = sc2.nextLine();
			String s3 = sc2.nextLine();
			String j = sc2.nextLine(); //This line should consist of three dashes. These dashes act as a separator between items.
			Item i = new Item(s1, s2, s3);
			items.add(i);
		}
		sc2.close();
		
		while(sc3.hasNext()) //sc3 reads Puzzles.txt and converts the data into readable Puzzles.
		{
			String s1 = sc3.nextLine();
			String s2 = sc3.nextLine();
			String s3 = sc3.nextLine();
			String s4 = sc3.nextLine();
			String s5 = sc3.nextLine();
			String j = sc3.nextLine(); //Same thing as the items
			Puzzle p = new Puzzle(s1, s2, s3, s4, s5);
			puzzles.add(p);
		}
		sc3.close();
		
		while (sc1.hasNext()) //sc1 reads Rooms.txt and converts them into readable Rooms.
		{
			String room = sc1.nextLine();
			String[] info = room.split(",");
			Room r = new Room(info[0], info[1], info[2], info[3], info[4], info[5]);
			
			for(Item i: items) //assigning items to rooms
			{
				if(i.getStart() == r.getId())
				{
					r.addItem(i);
				}
			}
			
			for(Puzzle p: puzzles) //assigning puzzles to rooms
			{
				if(p.getStart() == r.getId())
				{
					r.setPuzzle(p);
				}
			}
			rooms.add(r);
		}
		sc1.close();
	}
	
	public ArrayList<Room> getRooms()
	{
		return this.rooms;
	}
	
	public static void main(String[] args)
	{
		//Customary startup instructions
		Game game = new Game();
		game.readFile();
		System.out.println("Welcome to the Map Game!");
		
		//Creating fundamental gameplay objects
		ArrayList<Room> rooms = game.getRooms();
		Player player = new Player(rooms.get(0));
		
		//Initial player-room interactions
		player.getCurrent().visit();
		System.out.println(player.getCurrent().toString());
		
		//Setting up functionality for puzzles
		boolean puzzleMode = false;
		Puzzle active = null;
		
		//Here comes the main gameplay loop
		while (true)
		{
			/* Puzzle mode is activated when the player navigates to a room with a puzzle.
			 * The program stays in puzzle mode until the player solves or fails the puzzle.
			 * After puzzle mode ends, the program returns to the normal gameplay loop.
			 */
			if(puzzleMode == true)
			{
				System.out.println("You have activated a puzzle!");
				System.out.println(active.getText());
				int a = active.getAttempts(); //Copies the value because the number of attempts per puzzle needs to stay consistent. a is edited to keep track of the number of attempts the user has used.
				while(a > 0)
				{
					String guess = input.nextLine();
					if(guess.equalsIgnoreCase(active.getAnswer()))
					{
						System.out.println("You solved the puzzle correctly!");
						player.getCurrent().removePuzzle();
						a = 0; //This is to end the puzzle mode loop early.
					}
					else
					{
						a -= 1;
						if(a > 0)
						{
							System.out.println("The answer you have provided is wrong. You still have " + String.valueOf(a) + " attempt(s) remaining. Try again.");
						}
						else
						{
							System.out.println("Puzzle failed.");
						}
					}
				}
				puzzleMode = false;
			}
			
			//For when the player is not in puzzle mode (aka most of the program)
			String resp = input.nextLine();
			
			//Movement
			if(resp.equalsIgnoreCase("N") | resp.equalsIgnoreCase("E") | resp.equalsIgnoreCase("W") | resp.equalsIgnoreCase("S"))
			{
				int dest = 0;
				if(resp.equalsIgnoreCase("N"))
				{
					dest = player.getCurrent().getNorth();
				}
				else if(resp.equalsIgnoreCase("E"))
				{
					dest = player.getCurrent().getEast();
				}
				else if(resp.equalsIgnoreCase("S"))
				{
					dest = player.getCurrent().getSouth();
				}
				else if(resp.equalsIgnoreCase("W"))
				{
					dest = player.getCurrent().getWest();
				}
				if(dest == 0)
				{
					System.out.println("There is no room in that direction. Please try again.");
				}
				else
				{
					for(Room r: rooms)
					{
						if(r.getId() == dest) //find the destination room
						{
							player.setCurrent(r);
							System.out.println(player.getCurrent());
							if(player.getCurrent().visited())
							{
								System.out.println("This room looks familiar.");
							}
							player.getCurrent().visit();
							if(player.getCurrent().hasPuzzle())
							{
								//To activate puzzle mode, enter a room with a puzzle.
								puzzleMode = true;
								active = player.getCurrent().getPuzzle();
							}
						}
					}
				}
			}
			
			//Explore = list all items in the current room
			else if(resp.equalsIgnoreCase("explore"))
			{
				player.getCurrent().explore();
			}
			
			//Inventory = list all items in the player's inventory
			else if(resp.equalsIgnoreCase("inventory"))
			{
				player.printInventory();
			}
			
			//Inspect = list description of given item if said item is in the room.
			else if(resp.toLowerCase().startsWith("inspect") && resp.length() >= 9)
			{
				String name = resp.substring(8); //Given item
				Item look = null;
				if(player.getCurrent().getItems().size() != 0)
				{
					for(Item i: player.getCurrent().getItems())
					{
						if(name.equalsIgnoreCase(i.getName()))
						{
							look = i;
						}
					}
				}
				try
				{
					System.out.println(look.getDescription());
				}
				catch(NullPointerException npe)
				{
					System.out.println("There is no item in the current room with that name.");
				}
			}
			
			//Pickup = add given item to inventory if item is in the room.
			else if(resp.toLowerCase().startsWith("pickup") && resp.length() >= 8)
			{
				String name = resp.substring(7);
				Item add = null;
				if(player.getCurrent().getItems().size() != 0)
				{
					for(Item i: player.getCurrent().getItems())
					{
						if(name.equalsIgnoreCase(i.getName()))
						{
							add = i;
						}
					}
				}
				try
				{
					System.out.println(add.getName() + " has been picked up from the room and successfully added to the player inventory.");
					player.addItem(add);
					player.getCurrent().removeItem(add);
				}
				catch(NullPointerException npe)
				{
					System.out.println("There is no item in the current room with that name.");
				}
			}
			
			//Drop = add item to current room & remove from player's inventory if it's in the player's inventory.
			else if(resp.toLowerCase().startsWith("drop") && resp.length() >= 6)
			{
				String name = resp.substring(5);
				Item rem = null;
				if(player.getInventory().size() != 0)
				{
					try
					{
						for(Item i: player.getInventory())
						{
							if(name.equalsIgnoreCase(i.getName()))
							{
								rem = i;
							}
						}
					}
					catch(NullPointerException npe)
					{
						System.out.println(player.getInventory().get(0));
					}
				}
				try
				{
					System.out.println(rem.getName() + " has been dropped successfully from the player inventory and placed in " + player.getCurrent().getName());
					player.removeItem(rem);
					player.getCurrent().addItem(rem);
				}
				catch(NullPointerException npe)
				{
					System.out.println("There is no item in your inventory with that name.");
				}
			}
			
			//Exit
			else if(resp.equalsIgnoreCase("quit"))
			{
				System.out.println("Thanks for playing!");
				System.exit(0);
			}
			
			//Invalid command registration
			else
			{
				System.out.println("That is not a valid input. Please try again.");
			}
		}
	}
}