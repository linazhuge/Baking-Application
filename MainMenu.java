import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MainMenu extends JPanel implements ActionListener{
	private static JButton [] button = new JButton[4];
	private JPanel mainL, centerL;
	private static JLabel top;
	private Font font;
		
	public MainMenu(){

		mainL = new JPanel(new BorderLayout(0, 50));
		centerL = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		
		mainL.setPreferredSize(new Dimension(800, 650));
		this.add(mainL);
		mainL.add(centerL, BorderLayout.CENTER);
		
		mainL.setVisible(true);
		this.setFocusable(true);
		this.setRequestFocusEnabled(true);
		
		try{
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Caramel Sweets.ttf");
		font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(30f);}
		catch(IOException|FontFormatException e){}
		
		button[0] = new JButton("Add New Recipe");
		button[1] = new JButton("Edit Existing Recipes");
		button[2] = new JButton("Start Baking!");
		button[3] = new JButton("Exit");
		
		top = new JLabel("Main Menu", SwingConstants.CENTER);
		top.setFont(font);
		top.setPreferredSize(new Dimension(800, 120));
		top.setBackground(Baking.PURPLE);
		top.setOpaque(true);
		mainL.add(top, BorderLayout.NORTH);
		
		for(int i=0; i<4; i++){
			button[i].addActionListener(this);
			button[i].setBorder(BorderFactory.createLineBorder(Color.white));
			button[i].setPreferredSize(new Dimension(600, 100));
			//button[i].setFont(new Font("Arial", Font.PLAIN, 24));
			button[i].setFont(Baking.font);
			button[i].setBackground(Color.WHITE);
			centerL.add(button[i], BorderLayout.CENTER);
		}
	}

	public void paint(Graphics g){
		super.paint(g);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == button[0]){
			Baking.a.setEdit(false);
			Baking.cardL.next(Baking.c);
		}
		else if(e.getSource() == button[1]){
			Baking.e.update();
			Baking.cardL.show(Baking.c, "EditRecipe");
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