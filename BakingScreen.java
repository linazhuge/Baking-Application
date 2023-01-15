import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
import java.net.URL;

public class BakingScreen extends JPanel implements ActionListener{
	private JPanel holderL, mainL, topL, showTotalIngre, showRecipe, tempP, bottomL, timerP;
	private List totalIngre, buttons, timers;
	public List recipes;
	private Ingredient in;
	private JButton tempB, mainM, timer;
	private JLabel header, timerHeader;
	private CardLayout c;
	private int num = -1, totalTime, timeTaken, minutes = -1, seconds = -1;
	private JScrollPane sp;
	private JTextArea tf;
	private boolean dispTime = false;
	private long startTime, currentTime, timeElapsed;
	private Clip clip;
	
	public BakingScreen(){
		holderL = new JPanel(new BorderLayout());
		holderL.setPreferredSize(new Dimension(800, 650));
		this.add(holderL);
		holderL.setVisible(true);
		
		topL = new JPanel(new FlowLayout());
		topL.setPreferredSize(new Dimension(800, 50));
		topL.setBackground(Baking.PALE);
		holderL.add(topL, BorderLayout.NORTH);
		
		mainL = new JPanel();
		c = new CardLayout();
		mainL.setLayout(c);
		mainL.setPreferredSize(new Dimension(800, 600));
		holderL.add(mainL, BorderLayout.CENTER);
		
		showTotalIngre = new JPanel(new FlowLayout());
		showTotalIngre.setBackground(Baking.BEIGE);
		mainL.add("TotalIngredients", showTotalIngre);
		header = new JLabel("total ingredients");
		header.setFont(Baking.font);
		showTotalIngre.add(header);

		mainM = new JButton("Main Menu");
		mainM.setPreferredSize(new Dimension(700, 50));
		mainM.setFont(Baking.font);
		mainM.setBackground(Baking.PURPLE);
		mainM.addActionListener(this);
		holderL.add(mainM, BorderLayout.SOUTH);
		
		recipes = new List();
		totalIngre = new List();
		buttons = new List();
		timers = new List();
		
		try{
			File f = new File("atomic_bell.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		}catch(Exception e){
			System.out.println("hi");
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		int xPos1 = 10, xPos2 = 80, xPos3 = 200, yPos = 125;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(Baking.font);
		
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			
		g2d.setColor(Color.BLACK);
		
		if(dispTime){
			currentTime = System.nanoTime();
			timeElapsed = (currentTime - startTime) / 1000000000;
			timeTaken = totalTime - (int)timeElapsed;
			
			minutes = timeTaken / 60;
			seconds = timeTaken % 60;
			
			if(minutes == 0 && seconds == 0){
				clip.start();
				dispTime = false;
			}
		}
		
		else{
			minutes = totalTime / 60;
			seconds = totalTime % 60;
		}
		
		g2d.setFont(Baking.fontS);
		if(num == -1){
			int i=0;
			while(totalIngre.lookUp(i) != null){
				g2d.drawString(String.valueOf(((Ingredient)totalIngre.lookUp(i)).getAmount()), xPos1, yPos);
				g2d.drawString(((Ingredient)totalIngre.lookUp(i)).getMeasurement(), xPos2, yPos);
				g2d.drawString(((Ingredient)totalIngre.lookUp(i)).getIngre(), xPos3, yPos);
				yPos += 30;
				if(yPos > 650){
					yPos = 125;
					xPos1 = 310;
					xPos2 = 600;
				}
				i++;
			}
		}
		else{
			for(int i=0; i<((Recipe)recipes.lookUp(num)).getSize(); i++){
				g2d.drawString(String.valueOf(((Recipe)recipes.lookUp(num)).getIngredient(i).getAmount()), xPos1, yPos);
				g2d.drawString(((Recipe)recipes.lookUp(num)).getIngredient(i).getMeasurement(), xPos2, yPos);
				g2d.drawString(((Recipe)recipes.lookUp(num)).getIngredient(i).getIngre(), xPos3, yPos);
				yPos += 30;
				if(yPos > 450){
					yPos = 125;
					xPos1 = 310;
					xPos2 = 600;
				}
			}
			g2d.setFont(Baking.font);
			if(seconds < 10)
				g2d.drawString(minutes + " : 0" + seconds, 670, 438);
			else
				g2d.drawString(minutes + " : " + seconds, 670, 438);
		}
		repaint();
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == (JButton)buttons.lookUp(0)){
			num = -1;
			c.show(mainL, "TotalIngredients");
		}
		else if(e.getSource() == mainM){
			recipes = new List();
			Baking.cardL.show(Baking.c, "MainMenu");
		}
		else if(timers.pos((JButton)e.getSource()) != -1){
			if(dispTime){
				dispTime = false;
				int z = timers.pos((JButton)e.getSource());
				((JButton)timers.lookUp(z)).setText("Start Timer");
			}
			else{
				dispTime = true;
				startTime = System.nanoTime();
				timer.setText("Stop Timer");
				totalTime = (int)((Recipe)recipes.lookUp(num)).getTime() * 60;
			}
		}
		else{
			num = recipes.posI2(((JButton)e.getSource()).getText());
			for(int i=1; i<buttons.size(); i++){
				if(e.getSource() == (JButton)buttons.lookUp(i)){
					c.show(mainL, "Recipe" + i);
					totalTime = (int)((Recipe)recipes.lookUp(num)).getTime() * 60;
				}
			}
		}
	}
	
	public void addRecipe(Recipe recipe, double size, double numOfL){
		Recipe tempR = new Recipe("");
		String tempS, tempS2;
		double tempT;
		Ingredient tempI;
		tempS = recipe.getName();
		tempR.setName(tempS);
		tempT = recipe.getTime();
		tempR.setTime(tempT);
		for(int i=0; i < recipe.getSize(); i++){
			tempS = recipe.getIngredient(i).getIngre();
			tempS2 = recipe.getIngredient(i).getMeasurement();
			tempT = recipe.getIngredient(i).getAmount();
			tempR.addIngredient(new Ingredient(tempT, tempS2, tempS));
			tempR.getIngredient(i).setAmount(tempT * numOfL * size);
			recipe.getIngredient(i).setAmount(tempT * numOfL * size);
			recipe.getIngredient(i).round();
			tempR.getIngredient(i).round();
		}
		recipes.add(recipe);
		int i=0;
		while(tempR.getIngredient(i) != null){
			if(totalIngre.containsI(tempR.getIngredient(i).getIngre()) == false){
				Ingredient temp = tempR.getIngredient(i);
				totalIngre.add(temp);
			}
			else{
				((Ingredient)totalIngre.lookUp(totalIngre.posI(tempR.getIngredient(i).getIngre()))).setAmount(tempR.getIngredient(i).getAmount() + ((Ingredient)totalIngre.lookUp(totalIngre.posI(tempR.getIngredient(i).getIngre()))).getAmount());
			}
			i++;
		}
	}
	
	public void update(){ 
		buttons = new List();
		for(int i=0; i<(recipes.size() + 1); i++){
			tempB = new JButton();
			tempB.setFont(Baking.font);
			tempB.addActionListener(this);
			tempB.setBackground(Baking.PURPLE);
			if(i==0){
				tempB.setText("Total");
			}
			else{
				header = new JLabel(((Recipe)recipes.lookUp(i-1)).getName() + " " + ((Recipe)recipes.lookUp(i-1)).getType() + " instructions");
				header.setFont(Baking.font);
				header.setVerticalAlignment(SwingConstants.CENTER);
				header.setBackground(Baking.BROWN);
				timer = new JButton("Start Timer");
				timer.setFont(Baking.font);
				timer.setBackground(Baking.PALE);
				timer.addActionListener(this);
				timers.add(timer);
				
				timerP = new JPanel(new BorderLayout());
				timerP.setBackground(Baking.BROWN);
				
				timerHeader = new JLabel("");
				timerHeader.setPreferredSize(new Dimension(200, 50));
				timerP.add(timerHeader, BorderLayout.NORTH);
				timerP.add((JButton)timers.lookUp(i-1), BorderLayout.CENTER);
				tempB.setText(((Recipe)recipes.lookUp(i-1)).getName());
				tempP = new JPanel(new BorderLayout());
				tempP.setBackground(Baking.BEIGE);
				sp = new JScrollPane();
				sp.setPreferredSize(new Dimension(600, 200));
				sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				bottomL = new JPanel(new BorderLayout());
				tf = new JTextArea();
				tf.setText(((Recipe)recipes.lookUp(i-1)).getInstructions());
				tf.setLineWrap(true);
				tf.setEditable(false);
				tf.setWrapStyleWord(true);
				tf.setFont(Baking.fontS);
				sp.setViewportView(tf);
				tempP.add(header, BorderLayout.NORTH);
				bottomL.add(sp, BorderLayout.WEST);
				bottomL.add(timerP, BorderLayout.CENTER);
				tempP.add(bottomL, BorderLayout.SOUTH);
				mainL.add("Recipe" + i, tempP);
			}
			topL.add(tempB);
			buttons.add(tempB);
		}
		holderL.add(topL, BorderLayout.NORTH);
	}
}