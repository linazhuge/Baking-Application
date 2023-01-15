import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class EditRecipe extends JPanel implements ActionListener, KeyListener{
	public JPanel mainL, recipeL;
	public JTextField userChoice;
	public JScrollPane sp;
	public List recipeB;
	public Recipe tempRecipe;
	private String activeB = "";
	
	public EditRecipe(){
		mainL = new JPanel(new BorderLayout(10,10));
		mainL.setPreferredSize(new Dimension(800, 650));
		this.add(mainL);
		mainL.setVisible(true);
		
		userChoice = new JTextField();
		userChoice.setPreferredSize(new Dimension(800, 50));
		userChoice.setFont(Baking.font);
		userChoice.setText("");
		userChoice.addKeyListener(this);
		mainL.add(userChoice, BorderLayout.NORTH);
		
		sp = new JScrollPane();
		sp.setPreferredSize(new Dimension(800, 600));
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
	}
	
	public void addButtons(){
		recipeL = new JPanel(new FlowLayout());
		recipeL.setPreferredSize(new Dimension(800,600));
		recipeB = new List();
		sp.setViewportView(recipeL);
		mainL.add(sp, BorderLayout.CENTER);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(Baking.font);
		
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			
		g2d.setColor(Color.LIGHT_GRAY);
		
		if(userChoice.getText().equals("")){
			g2d.drawString("Enter Recipe Name", 10, 40);
		}
		
		repaint();
	}
	
	public void actionPerformed(ActionEvent e){
		int p = recipeB.pos((JButton)e.getSource()) + 1;
		int ones = 0;
		int zeroes = 0;
		for(int i = 0; i<activeB.length(); i++){
			if(ones == p){
				break;
			}
			if(activeB.charAt(i) == '1'){
				ones++;
			}
			else{
				zeroes++;
			}
		}
		int j = (int)Baking.order.charAt(ones + zeroes - 1);
		j = j-49;
		((AddRecipe)Baking.recipePanels.lookUp(j)).setNum(ones + zeroes - 1);
		userChoice.setText("");
		Baking.cardL.show(Baking.c, ("Recipe" + (j+1)));
	}
	
	public void keyTyped(KeyEvent e){
		
	}
	
	public void keyPressed(KeyEvent e){
	}
	
	public void keyReleased(KeyEvent e){
		update();		
	}
	
	public void update(){
		addButtons();
		activeB = "";
		if(Baking.recipes != null){
			for(int i=0; i<Baking.recipes.size(); i++){
				if(((Recipe)Baking.recipes.lookUp(i)).getName().contains(userChoice.getText())){
					tempRecipe = (Recipe)Baking.recipes.lookUp(i);
					if(!recipeB.containsB(tempRecipe.getName())){
						JButton b = new JButton(tempRecipe.getName());
						b.setHorizontalAlignment(SwingConstants.LEFT);
						b.setContentAreaFilled(false);
						b.setPreferredSize(new Dimension(800, 40));
						b.setFont(Baking.font);
						b.addActionListener(this);
						recipeB.add(b);
						recipeL.add(b);
					}
					activeB += 1;
				}
				else
					activeB += 0;
			}
		}
	}
}