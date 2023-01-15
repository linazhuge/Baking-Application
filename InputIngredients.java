import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class InputIngredients extends JPanel implements KeyListener, ActionListener{
	public JPanel ingredientsInfo, mainL, top;
	private List amounts, ingredients, measurements;
	private static JButton button;
	
	public InputIngredients(){
		mainL = new JPanel(new BorderLayout());
		mainL.setMaximumSize(new Dimension(450, Integer.MAX_VALUE));
		mainL.setMinimumSize(new Dimension(450, 360));
		
		this.add(mainL);
		this.setVisible(true);
		this.setFocusable(true);
		
		ingredientsInfo = new JPanel(new GridBagLayout());
		ingredientsInfo.setMaximumSize(new Dimension(450, Integer.MAX_VALUE));
		ingredientsInfo.setMinimumSize(new Dimension(450, 360));
		
		amounts = new List();
		ingredients = new List();
		measurements = new List();
		
		JTextField temp;
		
		mainL.add(ingredientsInfo, BorderLayout.NORTH);
		
		button = new JButton("+ add ingredient");
		button.setPreferredSize(new Dimension(450, 50));
		button.setFont(Baking.font);
		button.setBackground(Color.WHITE);
		button.addActionListener(this);
		
		mainL.add(button, BorderLayout.SOUTH);
	}
	
	public void paint(Graphics g){
		super.paint(g);
	}
	
	public void addIngredients(String am, String ty, String in){
		JTextField temp;
		temp = new JTextField();
		temp.setPreferredSize(new Dimension(85, 50));
		temp.setFont(Baking.font);
		temp.addKeyListener(this);
		temp.setText(am);
		amounts.add(temp);
		temp = new JTextField();
		temp.setPreferredSize(new Dimension(115, 50));
		temp.setFont(Baking.font);
		temp.setText("");
		temp.setText(ty);
		measurements.add(temp);
		temp = new JTextField();
		temp.setPreferredSize(new Dimension(250, 50));
		temp.setFont(Baking.font);
		temp.setText(in);
		ingredients.add(temp);
	
		
		int num = amounts.size()-1;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.gridx = 0;
		gbc.gridy = num;
		ingredientsInfo.add(((JTextField)amounts.lookUp(num)), gbc);
		gbc.gridx = 1;
		ingredientsInfo.add(((JTextField)measurements.lookUp(num)), gbc);
		gbc.gridx = 2;
		ingredientsInfo.add(((JTextField)ingredients.lookUp(num)), gbc);
	}
	
	
	public void actionPerformed(ActionEvent e){
		addIngredients("", "", "");
		revalidate();
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
	
	public String getAmount(int i){
		Object o = amounts.lookUp(i);
		if(o == null)
			return "";
		return ((JTextField)amounts.lookUp(i)).getText();
	
	}
	
	public String getIngredient(int i){
		return ((JTextField)ingredients.lookUp(i)).getText();
	}
	
	public String getMeasurement(int i){
		return ((JTextField)measurements.lookUp(i)).getText();
	}
	
	public void setTextField(int recipeNum, int num){
		Recipe temp = (Recipe)Baking.recipes.lookUp(recipeNum);
		((JTextField)amounts.lookUp(num)).setText(String.valueOf(temp.getIngredient(num).getAmount()));
		((JTextField)measurements.lookUp(num)).setText(temp.getIngredient(num).getMeasurement());
		((JTextField)ingredients.lookUp(num)).setText(temp.getIngredient(num).getIngre());
	}
}