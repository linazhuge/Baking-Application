import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class AddRecipe extends JPanel implements ActionListener, KeyListener{
	private JPanel mainL, basicInfoL, top, instructionsL, bottom, addIngredientsL, topAI;
	public InputIngredients ingreInfo;
	private JScrollPane ingredientsL, instructionsSP;
	private static JLabel header, nameL, timeL, sizeL, ingredientH, instructionsH;
	private JTextField nameI, timeI, sizeI;
	private JTextArea instructionsI;
	private JButton returnB, exit;
	private static JLabel amount, name, measurement;
	private boolean edit;
	private Recipe recipe;
	private double num;
	private String type;
	
	public AddRecipe(String type, boolean load){
		edit = false;
		this.type = type;
		
		mainL = new JPanel(new BorderLayout(0,0));
		this.add(mainL);
		mainL.setPreferredSize(new Dimension(800, 650));
		mainL.setVisible(true);
		
		top = new JPanel(new BorderLayout());
		top.setPreferredSize(new Dimension(800, 200));
		header = new JLabel("Add New Recipe", SwingConstants.CENTER);
		header.setPreferredSize(new Dimension(800, 40));
		header.setOpaque(true);
		header.setFont(Baking.font);
		header.setBackground(Baking.PURPLE);
		
		top.add(header, BorderLayout.NORTH);

		basicInfoL = new JPanel(new GridBagLayout());
		basicInfoL.setPreferredSize(new Dimension(800, 200));
		mainL.add(top, BorderLayout.NORTH);

		nameL = new JLabel("Name:");
		timeL = new JLabel("Time:");
		sizeL = new JLabel();
		nameL.setFont(Baking.font);
		timeL.setFont(Baking.font);
		sizeL.setFont(Baking.font);
		
		nameI = new JTextField();
		timeI = new JTextField();
		sizeI = new JTextField();
		nameI.setFont(Baking.font);
		timeI.setFont(Baking.font);
		sizeI.setFont(Baking.font);
		timeI.addKeyListener(this);
		sizeI.addKeyListener(this);
		
		if(type.equals("cake")){	
			sizeL.setText("Pan Size:");
		}
		else if(type.equals("cupcake")){
			sizeL.setText("Number:");
		}
		else if(type.equals("frosting")){
			sizeL.setText("Cups:");
		}
		else{
			sizeL.setText("Number:");
		}
		
		nameI.setPreferredSize(new Dimension(300, 40));
		timeI.setPreferredSize(new Dimension(300, 40));
		sizeI.setPreferredSize(new Dimension(300, 40));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		basicInfoL.add(nameL, gbc);
		gbc.gridx = 1;
		basicInfoL.add(nameI, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		basicInfoL.add(timeL, gbc);
		gbc.gridx = 1;
		basicInfoL.add(timeI, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		basicInfoL.add(sizeL, gbc);
		gbc.gridx = 1;
		basicInfoL.add(sizeI, gbc);
		top.add(basicInfoL, BorderLayout.CENTER);
		
		addIngredientsL = new JPanel(new BorderLayout(0,0));
		addIngredientsL.setPreferredSize(new Dimension(450, 400));
		
		topAI = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		topAI.setPreferredSize(new Dimension(450, 30));
		addIngredientsL.add(topAI, BorderLayout.NORTH);
		
		amount = new JLabel("  Amount");
		amount.setFont(Baking.fontS);
		amount.setPreferredSize(new Dimension(85, 30));
		measurement = new JLabel("  Measurement");
		measurement.setFont(Baking.fontS);
		measurement.setPreferredSize(new Dimension(115, 30));
		name = new JLabel("  Ingredient");
		name.setFont(Baking.fontS);
		name.setPreferredSize(new Dimension(250, 30));
		
		topAI.add(amount);
		topAI.add(measurement);
		topAI.add(name);
		
		ingredientsL = new JScrollPane();
		ingredientsL.setPreferredSize(new Dimension(450, 310));
		ingredientsL.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ingredientsL.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		addIngredientsL.add(ingredientsL, BorderLayout.CENTER);
		mainL.add(addIngredientsL, BorderLayout.WEST);
		
		ingreInfo = new InputIngredients();
		ingreInfo.setVisible(true);
		
		ingredientsL.setViewportView(ingreInfo);
		ingredientsL.getViewport().setMaximumSize(new Dimension(450, Integer.MAX_VALUE));
		
		instructionsSP = new JScrollPane();
		instructionsSP.setPreferredSize(new Dimension(350, 310));
		instructionsSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		instructionsSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		instructionsSP.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		instructionsSP.setBorder(BorderFactory.createEmptyBorder());
		
		instructionsL = new JPanel(new BorderLayout());
		instructionsH = new JLabel("Instructions");
		instructionsH.setFont(Baking.fontS);
		instructionsH.setPreferredSize(new Dimension(350, 30));
		instructionsL.add(instructionsH, BorderLayout.NORTH);
		
		instructionsI = new JTextArea();
		instructionsI.setFont(Baking.font);
		instructionsI.setMinimumSize(new Dimension(350, 300));
		instructionsI.setLineWrap(true);
		instructionsI.setWrapStyleWord(true);
		instructionsI.setText("");
		instructionsL.add(instructionsSP, BorderLayout.CENTER);
		instructionsSP.setViewportView(instructionsI);
		mainL.add(instructionsL, BorderLayout.EAST);
		
		returnB = new JButton("Save & Return");
		returnB.setFont(Baking.font);
		returnB.addActionListener(this);
		returnB.setBackground(Baking.PURPLE);
		returnB.setPreferredSize(new Dimension(300, 50));
		
		exit = new JButton("Cancel");
		exit.setFont(Baking.font);
		exit.addActionListener(this);
		exit.setBackground(Baking.PURPLE);
		exit.setPreferredSize(new Dimension(300, 50));
		
		bottom = new JPanel(new FlowLayout());
		bottom.add(returnB);
		bottom.add(exit);
		mainL.add(bottom, BorderLayout.SOUTH);
		
		if(load == false){
			for(int i=0; i<10; i++){
				ingreInfo.addIngredients("", "", "");
			}
		}
		else{
			setEdit(true);
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
	}
	
	
	public void setEdit(boolean edit){
		this.edit = edit;
		if(edit==true){
			header.setText("Edit Recipe");
			exit.setText("Cancel");
		}
		else{
			header.setText("Add Recipe");
			exit.setText("Cancel");
		}
	}
	
	public String getName(){
		return nameI.getText();
	}
	
	public void setNum(double num){
		this.num = num;
	}
	
	public void setName(String name){
		nameI.setText(name);
	}
	
	public void setTime(String time){
		timeI.setText(time);
	}
	
	public void setSize(String size){
		sizeI.setText(size);
	}
	
	public void setInstructions(String instr){
		instructionsI.setText(instr);
	}
	
	public void actionPerformed(ActionEvent e){
		String n, in;
		double t;
		int i=0;
		if(e.getSource() == returnB){
			try{
				Recipe recipe;
				n = nameI.getText();
				t = Double.parseDouble(timeI.getText());
				in = instructionsI.getText();
				if(type.equals("cake")){
					recipe = new Cake(n, t, in);
				}
				else if(type.equals("cupcake")){
					recipe = new Cupcake(n, t, in);
				}
				else if(type.equals("frosting")){
					recipe = new Frosting(n, t, in);
				}
				else{
					recipe = new Other(n, t, in);
				}
				while(!ingreInfo.getAmount(i).equals("")){
					double amount = Double.parseDouble(ingreInfo.getAmount(i));
					recipe.addIngredient(new Ingredient(amount, ingreInfo.getMeasurement(i), ingreInfo.getIngredient(i)));
					i++;
				}
				recipe.setRSize(Double.parseDouble(sizeI.getText()));
				if(edit == false){
					Baking.recipes.addR(recipe);
				}	
				else{
					Baking.recipes.replace(num, recipe);
				}
				this.setEdit(true);
				Baking.cardL.show(Baking.c, "MainMenu");
			}catch(NumberFormatException ne){
				UIManager.put("OptionPane.messageFont", Baking.font);
				UIManager.put("OptionPane.buttonFont", Baking.font);
				JOptionPane.showMessageDialog(this, "You are missing or have entered invalid information! Please complete all fields.");
			}
		}
		else if(e.getSource() == exit && edit == false){
			Baking.m.remove();
		}
		else{
			Baking.cardL.show(Baking.c, "EditRecipe");
		}
	}
	
	public void keyTyped(KeyEvent e){
		char c = e.getKeyChar();
		if(!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || c == '.' || c == '>')){
			e.consume();
		}
	}
	
	public void keyPressed(KeyEvent e){
	}
	
	public void keyReleased(KeyEvent e){	
	}
}