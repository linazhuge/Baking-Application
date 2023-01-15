import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Baking extends JFrame{
	static CardLayout cardL;
	static Container c;
	static FourOptions m, t1, t2;
	static AddRecipe a;
	static EditRecipe e;
	static ChooseRecipe cr;
	static BakingScreen bs;
	static Font font, fontS;
	static Color PURPLE = new Color(166, 141, 173);
	static Color BROWN = new Color(199, 177, 152);
	static Color BEIGE = new Color(223, 211, 195);
	static Color PALE = new Color(240, 236, 227);
	static List recipes, recipePanels;
	static String order = "";
	static FileReader file;
	static BufferedReader buffer;
	
	public Baking() throws Exception{
		InputStream stream;
		try{
		stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Louis George Cafe Bold.ttf");
		font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(28f);}
		catch(IOException|FontFormatException e){}
		
		try{
		stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Louis George Cafe Bold.ttf");
		fontS = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(16f);}
		catch(IOException|FontFormatException e){}
		
		c = getContentPane();
		cardL = new CardLayout();
		c.setLayout(cardL);
		
		recipePanels = new List();
		recipes = new List();
		
		m = new FourOptions("Main Menu");
		m.setButtons("Add Recipe", "Edit Recipe", "Start Baking", "Exit");
		m.setType(0);
		e = new EditRecipe();
		t1 = new FourOptions("Choose a Recipe Type");
		t1.setButtons("Cake", "Cupcake", "Frosting", "Other");
		t1.setType(1);
		t2 = new FourOptions("Choose a Recipe Type");
		t2.setButtons("Cake", "Cupcake", "Frosting", "Other");
		t2.setType(2);
		cr = new ChooseRecipe();
		bs = new BakingScreen();
		
		c.add("MainMenu", m);
		c.add("EditRecipe", e);
		c.add("ChooseType1", t1);
		c.add("ChooseType2", t2);
		c.add("ChooseRecipe", cr);
		c.add("BakingScreen", bs);
		
		String line, tempMeas;
		Recipe tempRec = new Recipe();
		JPanel tempP = new JPanel();
		int num1, num2, typeNum;
		InputStream inputStream = this.getClass().getResourceAsStream("/recipes.txt");
		//InputStream inputStream = getClass().getResourceAsStream("/resources/recipes.txt");
		//InputStream inputStream = new FileInputStream("recipes.txt");
		//System.out.println(inputStream == null);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		//file = new FileReader("recipes.txt");
		//buffer = new BufferedReader(file);
		buffer = new BufferedReader(inputStreamReader);
		while((line = buffer.readLine()) != null &&  !line.equals("")){
			if(line.contains("cupcake: ")){
				tempRec = new Cupcake();
				tempRec.setName(line.substring(9));
				tempP = new AddRecipe("cupcake", true);
				((AddRecipe)tempP).setName(line.substring(9));
				typeNum = 1;
			}
			else if(line.contains("cake: ")){
				tempRec = new Cake();
				tempRec.setName(line.substring(6));
				tempP = new AddRecipe("cake", true);
				((AddRecipe)tempP).setName(line.substring(6));
				typeNum = 0;
			}
			else if(line.contains("frosting: ")){
				tempRec = new Frosting();
				tempRec.setName(line.substring(10));
				tempP = new AddRecipe("frosting", true);
				((AddRecipe)tempP).setName(line.substring(10));
				typeNum = 2;
			}
			else{
				tempRec = new Other();
				tempRec.setName(line.substring(7));
				tempP = new AddRecipe("other", true);
				((AddRecipe)tempP).setName(line.substring(7));
				typeNum = 3;
			}
			
			line = buffer.readLine();
			tempRec.setTime(Double.parseDouble(line));
			((AddRecipe)tempP).setTime(line);
			line = buffer.readLine();
			tempRec.setRSize(Double.parseDouble(line));

			((AddRecipe)tempP).setSize(line);
			while((line = buffer.readLine()) != null && !line.equals("***")){
				num1 = line.indexOf(" ");
				num2 = line.indexOf(" ", num1 + 1);
				tempMeas = line.substring(num1 + 1, num2);
				if(tempMeas.equals("^"))
					tempMeas = "";
				((AddRecipe)tempP).ingreInfo.addIngredients((line.substring(0, num1)), tempMeas, line.substring(num2 + 1));
				tempRec.addIngredient(new Ingredient(Double.parseDouble(line.substring(0, num1)), tempMeas, line.substring(num2 + 1)));
			}
			line = buffer.readLine();
			((AddRecipe)tempP).setInstructions(line);
			tempRec.setInstructions(line);
			recipePanels.add(tempP);
			c.add(("Recipe" + recipePanels.size()), tempP);
			recipes.addR(tempRec);
		}

	}
	
	public static void main(String[] args) throws Exception{
		Baking frame = new Baking();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
	}

}