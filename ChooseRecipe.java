import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ChooseRecipe extends JPanel implements ActionListener{
	private JPanel mainL, recipesL, sizing, returnL;
	private JScrollPane sp;
	private JLabel sizeL, multiplyL;
	private JTextField sizeI, multiplyI;
	private List recipeO, recipeB;
	private String type = "";
	private Recipe tempRecipe;
	private double time = 0;
	private JButton b1, b2;
	
	public ChooseRecipe(){
		mainL = new JPanel(new BorderLayout());
		mainL.setPreferredSize(new Dimension(800, 650));
		this.add(mainL);
		mainL.setVisible(true);
		this.setFocusable(true);
		
		sp = new JScrollPane();
		sp.setPreferredSize(new Dimension(800, 500));
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		
		recipesL = new JPanel(new FlowLayout(FlowLayout.LEFT));
		recipesL.setMaximumSize(new Dimension(800, Integer.MAX_VALUE));
		recipesL.setMinimumSize(new Dimension(800, 0));
		
		recipeB = new List();
		recipeO = new List();
		
		sizing = new JPanel(new GridLayout(2,2));
		sizing.setPreferredSize(new Dimension(800,100));
		sizing.setBackground(Baking.PURPLE);
		sizeL = new JLabel();
		sizeL.setPreferredSize(new Dimension(400, 50));
		sizeL.setFont(Baking.font);
		multiplyL = new JLabel("How Many?");
		multiplyL.setPreferredSize(new Dimension(400, 50));
		multiplyL.setFont(Baking.font);
		sizeI = new JTextField();
		sizeI.setPreferredSize(new Dimension(400, 50));
		sizeI.setFont(Baking.font);
		sizeI.setText("");
		multiplyI = new JTextField();
		multiplyI.setPreferredSize(new Dimension(400, 50));
		multiplyI.setFont(Baking.font);
		multiplyI.setText("");
		sizing.add(sizeL);
		sizing.add(multiplyL);
		sizing.add(sizeI);
		sizing.add(multiplyI);
		
		mainL.add(sizing, BorderLayout.NORTH);
		
		returnL = new JPanel(new BorderLayout());
		returnL.setPreferredSize(new Dimension(800, 50));
		b1 = new JButton("Main Menu");
		b1.setFont(Baking.font);
		b1.setBackground(Baking.PURPLE);
		b1.addActionListener(this);
		b1.setPreferredSize(new Dimension(400, 50));
		b2 = new JButton("Recipe Type");
		b2.setFont(Baking.font);
		b2.setBackground(Baking.PURPLE);
		b2.addActionListener(this);
		b2.setPreferredSize(new Dimension(400, 50));
		returnL.add(b1, BorderLayout.WEST);
		returnL.add(b2, BorderLayout.EAST);
		mainL.add(returnL, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == b1){
			Baking.cardL.show(Baking.c, "MainMenu");
		}
		else if(e.getSource() == b2){
			Baking.cardL.show(Baking.c, "ChooseType2");
		}
		else{
			//boolean run = true;
			int i = recipeB.pos((JButton)e.getSource());
			double num1, num2;
			if(sizeI.getText().equals("")){
				num1 = ((Recipe)recipeO.lookUp(i)).getRSize();
				num1 = ((Recipe)recipeO.lookUp(i)).compare(num1);
			}	
			else{
				num1 = Double.parseDouble(sizeI.getText());
				num1 = ((Recipe)recipeO.lookUp(i)).compare(num1);
			}
			if(multiplyI.getText().equals(""))
				num2 = 1;
			else
				num2 = Double.parseDouble(multiplyI.getText());
			
			UIManager.put("OptionPane.messageFont", Baking.font);
			UIManager.put("OptionPane.buttonFont", Baking.font);
			int result = JOptionPane.showConfirmDialog(null, "Selected! Do you want to add another recipe?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			switch(result){
				case JOptionPane.YES_OPTION:
				Baking.cardL.show(Baking.c, "ChooseType2");
				Baking.bs.addRecipe((Recipe)recipeO.lookUp(i), num1, num2);
				multiplyI.setText("");
				sizeI.setText("");
				break;
				case JOptionPane.NO_OPTION:
				Baking.bs.addRecipe((Recipe)recipeO.lookUp(i), num1, num2);
				Baking.bs.update();
				Baking.cardL.show(Baking.c, "BakingScreen");
				multiplyI.setText("");
				sizeI.setText("");
				break;
				case JOptionPane.CANCEL_OPTION:
				//nothing happens
				break;
			}
		}
	}
	
	public void setType(String type){
		this.type = type;
		if(type.equals("cake")){
			sizeL.setText("Cake Pan Size:");
			multiplyL.setText("Number of Layers:");
		}
		else if(type.equals("cupcake"))
			sizeL.setText("Number of Cupcakes Per Tray:");
		else if(type.equals("frosting"))
			sizeL.setText("Cake Pan Size Used:");
		else
			sizeL.setText("Size:");
	}
	
	public void addButtons(){
		recipesL = new JPanel(new GridBagLayout());
		recipesL.setMaximumSize(new Dimension(800, Integer.MAX_VALUE));
		recipesL.setMinimumSize(new Dimension(800, 0));
		recipesL.setBackground(Baking.PALE);
		sp.setViewportView(recipesL);
		sp.getViewport().setMaximumSize(new Dimension(800, Integer.MAX_VALUE));
		mainL.add(sp, BorderLayout.CENTER);
	}
	
	public void update(){
		addButtons();
		recipeB = new List();
		recipeO = new List();
		if(Baking.recipes != null){
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.HORIZONTAL;
			for(int i=0; i<Baking.recipes.size(); i++){
				tempRecipe = (Recipe)Baking.recipes.lookUp(i);
				if(tempRecipe.getType().equals(type)){
					ImageIcon image;
					JLabel imageHold;
					
					JPanel temp = new JPanel(new BorderLayout());
					temp.setBorder(BorderFactory.createLineBorder(Color.WHITE));
					JButton name = new JButton(tempRecipe.getName());
					name.setPreferredSize(new Dimension(185, 50));
					name.addActionListener(this);
					name.setBackground(Baking.BROWN);
					JTextArea time = new JTextArea();
					time.setText(("Time: " + String.valueOf(tempRecipe.getTime()) + " minutes"));
					time.setLineWrap(true);
					time.setEditable(false);
					time.setWrapStyleWord(true);
					name.setFont(Baking.font);
					time.setFont(Baking.font);
					time.setBackground(Baking.BEIGE);
					temp.add(name, BorderLayout.NORTH);
					temp.add(time, BorderLayout.CENTER);
					try{
						image = resize(tempRecipe.getName() + ".jpg", 160, 160);
						imageHold = new JLabel(image);
					}catch(Exception e){
						imageHold = new JLabel();
					}
					temp.add(imageHold, BorderLayout.SOUTH);
					temp.setPreferredSize(new Dimension(190, 300));
					gbc.gridx = i % 4;
					gbc.gridy = i / 4;
					recipeO.add(tempRecipe);
					recipeB.add(name);
					recipesL.add(temp, gbc);
				}
			}
		}
	}
	
	public static ImageIcon resize(String name, int width, int height){
		Image scaleImage;
		ImageIcon pic;
		pic = new ImageIcon(name);
		scaleImage = pic.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		pic = new ImageIcon(scaleImage);
		return pic;
	}
}