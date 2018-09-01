package harrypotter.view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.Border;

import harrypotter.model.character.Wizard;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.WallCell;

public class TournamentView extends JFrame{
	Task1View task1view;
	public JTextArea txt;
	public JTextArea txt2;
	public JTextArea txt3;
	public JTextArea txt4;
	public JComboBox Spells;
	public JComboBox Spells2;
	public JComboBox Spells3;
	
	public JButton button;
	public JButton button1;
	public JButton button2;
	public JButton button3;
	public JButton button5;
	public JButton button4;
	public JLabel picLabel;
	public JLabel picLabel2;
	public JLabel picLabel3;
	public JFrame frame;
	public JButton Player1;
	public JButton Player2;
	public JButton Player3;
	public JButton Player4;
	public JButton Player5;
	public JButton Player6;
	public JLabel Player_choose;
	public JButton button7;
	public TournamentView() throws IOException {
		
		setTitle("HarryPotter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setBounds(50, 50, 1366, 768);
		setLayout(null);
		Image img = ImageIO.read(new File("Ravenclaw.png"));
		Image icon = ImageIO.read(new File("icon.png"));
		setIconImage(icon);
		picLabel = new JLabel(new ImageIcon(img));
		
		setContentPane(picLabel);
		
		
		ImageIcon Slytherin_Badge = new ImageIcon("Slytherin_Badge.png");
		button=new JButton("save", Slytherin_Badge);
		button.setBounds(100, 290, 170, 191);
		button.setOpaque(true);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	
		add(button);
		ImageIcon gryffindor_badge = new ImageIcon("gryffindor_Badge.png");
		button1=new JButton("save", gryffindor_badge);
		button1.setBounds(310, 290, 170, 192);
		button1.setOpaque(false);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		
		
	
		add(button1);
		ImageIcon hufflepuff_badge = new ImageIcon("hufflepuff_Badge.png");
		 button2=new JButton("save", hufflepuff_badge);
		button2.setBounds(520, 290, 175, 200);
		button2.setOpaque(true);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		
		add(button2);
		ImageIcon Ravenclaw_badge = new ImageIcon("Ravenclaw_glow.png");
		 button3=new JButton("save",  Ravenclaw_badge);
		button3.setBounds(730, 290, 170, 195);
		button3.setOpaque(false);
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		
		add(button3);
		
		
		 button4=new JButton("Create Champion 1");
		button4.setSize(40, 40);
		button4.setFont(new Font("Harry P", Font.BOLD, 40));
		button4.setForeground(new Color(160,163,250));
		button4.setBounds(600, 70+30, button4.getPreferredSize().width, button4.getPreferredSize().height);
		button4.setOpaque(true);
		button4.setBorderPainted(true);
		button4.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		
		add(button4);
		
	
		
		Spells = new JComboBox();
		Spells.setBounds(125, 644+30, Spells.getPreferredSize().width, Spells.getPreferredSize().height);
		Spells.setOpaque(false);
		Spells.setBackground(new Color(8,15,25));
		Spells.setForeground(new Color(160,163,250));
		Spells.setFont(new Font("Harry P", Font.PLAIN, 34));
		Spells2 = new JComboBox();
		Spells2.setBackground(new Color(8,15,25));
		Spells2.setForeground(new Color(160,163,250));
		Spells2.setBounds(488, 646+30, 555, 555);
		Spells2.setOpaque(false);
		Spells2.setFont(new Font("Harry P", Font.PLAIN, 36));
		Spells3 = new JComboBox();
		Spells3.setBackground(new Color(8,15,25));
		Spells3.setForeground(new Color(160,163,250));
		Spells3.setBounds(846, 646+30, Spells2.getPreferredSize().width, Spells2.getPreferredSize().height);
		Spells3.setOpaque(false);
		Spells3.setFont(new Font("Harry P", Font.PLAIN, 36));
		add(Spells);
		add(Spells2);
		add(Spells3);
		button7=new JButton("Show Spells Details");
		button7.setSize(40, 402);
		button7.setFont(new Font("Harry P", Font.BOLD, 30));
		button7.setForeground(new Color(21,72,119));
		button7.setBounds(260, 593, button7.getPreferredSize().width, button7.getPreferredSize().height);
		button7.setOpaque(true);
		button7.setBorderPainted(true);
		button7.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		
		add(button7);
		
		
		txt = new JTextArea();
		Color customColor = new Color(187,196,253);
		txt.setFont(new Font("Harry P", Font.PLAIN, 38));
		txt.setForeground(customColor);
		txt.setOpaque(false);
		txt.setBounds(294,75+30, 250, 38);
		txt.setEditable(true);
		
		add(txt);
		
		
		txt2 = new JTextArea();
		txt2.setFont(new Font("Harry P", Font.ITALIC, 30));
		txt2.setForeground(customColor);
		txt2.setOpaque(false);
		txt2.setEditable(true);
		txt2.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));

		txt2.setText("Or yet in wise old Ravenclaw,\n If you've a ready mind,\n Where those of wit and learning,Will always find their kind.");
		txt2.setBounds(40,450, txt2.getPreferredSize().width, txt2.getPreferredSize().height);
		
		 
	        Icon icon2 = new ImageIcon("ww.gif");
	        JLabel label = new JLabel(icon2);
	        label.setIcon(new ImageIcon(new ImageIcon("ww.gif").getImage().getScaledInstance(1800, 1100, java.awt.Image.SCALE_DEFAULT)));
		ImageIcon img2 = new ImageIcon("Harry_Potter.png");
		JLabel photo = new JLabel(img2);
		photo.setBounds(90, 100, photo.getPreferredSize().width, photo.getPreferredSize().height);
		picLabel2 = new JLabel(icon2);
		
		setContentPane(picLabel2);
		setBackground(new Color(21,72,119));
		
		 button5=new JButton("Start");
			button5.setSize(40, 402);
			button5.setFont(new Font("Harry P", Font.BOLD, 60));
			button5.setForeground(new Color(21,72,119));
			button5.setBounds(235, 490, 200, button5.getPreferredSize().height);
			button5.setOpaque(true);
			button5.setBorderPainted(true);
			button5.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
			add(photo);
			add(button5);
			
			
			
			

			frame = new JFrame();
			frame.setTitle("HarryPotter");
			frame.setBounds(45, 170, 1300, 350);
			
			frame.setForeground(new Color(21,72,119));
			frame.setBackground(new Color(21,72,119));
			JLabel ab = new JLabel();
			ab.setIcon(new ImageIcon(new ImageIcon("task2back.jpg").getImage().getScaledInstance(1366, 768, java.awt.Image.SCALE_DEFAULT)));	

			frame.setContentPane(new JLabel(new ImageIcon("dark_1.jpg")));
			frame.setLayout(new GridLayout(1,6));
			Player1 = new JButton("",new ImageIcon("wizard.gif"));
			frame.add(Player1);
		
			Player1.setOpaque(false);
			Player1.setBorderPainted(false);
			Player1.setContentAreaFilled(false);
			Player2 = new JButton("",new ImageIcon("Amazon_Sprite.gif"));
			frame.add(Player2);
			frame.setUndecorated(true);
			Player2.setOpaque(true);
			Player2.setBorderPainted(false);
			frame.add(Player1);
			Player3 = new JButton("",new ImageIcon("Dwarf_Sprite.gif"));
			frame.add(Player3);
			Player3.setOpaque(true);
			Player3.setBorderPainted(false);
			Player4 = new JButton("",new ImageIcon("Elf_Sprite.gif"));
			frame.add(Player4);
			Player4.setOpaque(true);
			Player4.setBorderPainted(false);
			Player5 = new JButton("",new ImageIcon("Fighter_Sprite.gif"));
			frame.add(Player5);
			Player5.setOpaque(true);
			Player5.setBorderPainted(false);
			Player6 = new JButton("",new ImageIcon("Sorceress_Sprite.gif"));
			frame.add(Player6);
			Player6.setOpaque(true);
			Player6.setBorderPainted(false);
			Player2.setContentAreaFilled(false);
			Player3.setContentAreaFilled(false);
			Player4.setContentAreaFilled(false);
			Player5.setContentAreaFilled(false);
			Player6.setContentAreaFilled(false);
			
			setVisible(false);
			
			
			

		        
	}
	public static void main(String[] args) throws IOException {
		
		new TournamentView();
		
	}
	
}
