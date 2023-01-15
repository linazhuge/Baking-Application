import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class FourOptions extends JPanel implements ActionListener{
	private JButton [] button = new JButton[4];
	private JPanel mainL, centerL;
	private static JLabel top;
	private Font font;
	private int type;
	private String typeR;
		
	public FourOptions(String s){
		mainL = new JPanel(new BorderLayout(0, 50));
		centerL = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		
		mainL.setPreferredSize(new Dimension(800, 650));
		this.add(mainL);
		mainL.add(centerL, BorderLayout.CENTER);
		
		mainL.setVisible(true);
		this.setFocusable(true);
		this.setRequestFocusEnabled(true);

		
		button[0] = new JButton("Add New Recipe");
		button[0].setBackground(Baking.BROWN);
		button[1] = new JButton("Edit Existing Recipes");
		button[1].setBackground(Baking.BEIGE);
		button[2] = new JButton("Start Baking!");
		button[2].setBackground(Baking.PALE);
		button[3] = new JButton("Exit");
		button[3].setBackground(Color.WHITE);
		
		top = new JLabel(s, SwingConstants.CENTER);
		top.setFont(Baking.font);
		top.setPreferredSize(new Dimension(800, 120));
		top.setBackground(Baking.PURPLE);
		top.setOpaque(true);
		mainL.add(top, BorderLayout.NORTH);
		
		for(int i=0; i<4; i++){
			button[i].addActionListener(this);
			button[i].setBorder(BorderFactory.createLineBorder(Color.white));
			button[i].setPreferredSize(new Dimension(600, 100));
			button[i].setFont(Baking.font);
			centerL.add(button[i], BorderLayout.CENTER);
		}
	}

	public void paint(Graphics g){
		super.paint(g);
	}
	
	public void actionPerformed(ActionEvent e){
		if(type == 0){
			if(e.getSource() == button[0]){
				Baking.cardL.show(Baking.c, "ChooseType1");
			}
			else if(e.getSource() == button[1]){
				if(Baking.recipes.size() != 0){
					Baking.e.update();
					Baking.cardL.show(Baking.c, "EditRecipe");
				}
				else{
					UIManager.put("OptionPane.messageFont", Baking.font);
					UIManager.put("OptionPane.buttonFont", Baking.font);
					JOptionPane.showMessageDialog(this, "There are no recipes entered!");
				}
			}
			else if(e.getSource() == button[2]){
				//Baking.cardL.remove(Baking.bs);
				Baking.bs = new BakingScreen();
				Baking.c.add("BakingScreen", Baking.bs);
				if(Baking.recipes.size() != 0){
					Baking.cardL.show(Baking.c, "ChooseType2");
				}
				else{
					UIManager.put("OptionPane.messageFont", Baking.font);
					UIManager.put("OptionPane.buttonFont", Baking.font);
					JOptionPane.showMessageDialog(this, "There are no recipes entered!");
				}
			}
			else{
				FileWriter file;
				BufferedWriter buffer;
				Recipe temp;
				String meas;
				try{
					file = new FileWriter("recipes.txt");
					buffer = new BufferedWriter(file);
					for(int i=0; i<Baking.recipes.size(); i++){
						temp = (Recipe)Baking.recipes.lookUp(i);
						buffer.write(temp.getType() + ": " + temp.getName());
						buffer.newLine();
						buffer.write(String.valueOf(temp.getTime()));
						buffer.newLine();
						buffer.write(String.valueOf(temp.getRSize()));
						buffer.newLine();
						for(int j=0; j<temp.getSize(); j++){
							meas = temp.getIngredient(j).getMeasurement();
							if(meas.equals(""))
								meas = "^";
							buffer.write(String.valueOf(temp.getIngredient(j).getAmount() + " " + meas + " " + temp.getIngredient(j).getIngre()));
							buffer.newLine();
						}
						buffer.write("***");
						buffer.newLine();
						buffer.write(temp.getInstructions());
						buffer.newLine();
					}
					buffer.close();
				}
				catch(IOException er){
				}
				System.exit(0);
			}
		}
		else if(type == 1){
			if(e.getSource() == button[0]){
				typeR = "cake";
			}
			else if(e.getSource() == button[1]){
				typeR = "cupcake";
			}
			else if(e.getSource() == button[2]){
				typeR = "frosting";
			}
			else{
				typeR = "other";
			}
			Baking.recipePanels.add(new AddRecipe(typeR, false));
			Baking.c.add(("Recipe" + Baking.recipePanels.size()), (JPanel)Baking.recipePanels.lookUp(Baking.recipePanels.size()-1));
			Baking.cardL.show(Baking.c, "Recipe" + Baking.recipePanels.size());
		}
		else{
			if(e.getSource() == button[0]){
				Baking.cr.setType("cake");
			}
			else if(e.getSource() == button[1]){
				Baking.cr.setType("cupcake");
			}
			else if(e.getSource() == button[2]){
				Baking.cr.setType("frosting");
			}
			else{
				Baking.cr.setType("other");
			}
			Baking.cr.update();
			Baking.cardL.show(Baking.c, "ChooseRecipe");
		}
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public void setButtons(String a, String b, String c, String d){
		button[0].setText(a);
		button[1].setText(b);
		button[2].setText(c);
		button[3].setText(d);
	}
	
	public static ImageIcon resize(String name, int width, int height){
		Image scaleImage;
		ImageIcon pic;
		pic = new ImageIcon(name);
		scaleImage = pic.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		pic = new ImageIcon(scaleImage);
		return pic;
	}
	
	public void remove(){
		Baking.recipePanels.delete(Baking.recipePanels.size()-1);
		Baking.c.revalidate();
		Baking.c.repaint();
		Baking.cardL.show(Baking.c, "MainMenu");
	}

}