package harrypotter.view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.sound.sampled.spi.AudioFileReader;


import harrypotter.model.world.ObstacleCell;

public class Task1View extends JFrame{
	public JPanel panel = new JPanel();
	public JButton[][] buttons ;
	public JTextArea txt;
	public JButton moveforward ;
	public JButton movebackward ;
	public JButton moveleft ;
	public JButton moveright;
	public JLabel fighter;
	public JLabel hp_bar;
	public JButton spell_3;
	public JButton spell_2;
	public JButton spell_1;
	public JFrame frame;
	public JButton reloc;
	public JTextArea range;
	public JComboBox Target;
	public JComboBox direction;
	public JFrame relocatingspell;
	public JFrame damagingspell;
	public JButton damgeloc;
	public JButton choosespell ;
	public JComboBox direction1;
	public JFrame potions ;
	public JComboBox potion;
	public JButton trait;
	public JFrame slythtrait;
	public JTextArea slythdir;
	public JButton slythdirbut;
	public JButton potionbut;
	public JButton inv;
	public JButton passturn;
	public JTextArea hp;
	public JTextArea MP;
	public JTextArea allowed;
	public JLabel mp;
	public JLabel pic;
	public JLabel picLabel;
	public JLabel task3;
	public JLabel task2;
	public JLabel task1;
	public Task1View() throws IOException {
		setTitle("HarryPotter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setBounds(0, 0, 1366, 768);
		 hp_bar = new JLabel(new ImageIcon("HP bars/health_100.png"));
		hp_bar.setBounds(5, 60, hp_bar.getPreferredSize().width, hp_bar.getPreferredSize().height);
		inv = new JButton(new ImageIcon("INV.png"));
		inv.setBounds(20, 455, 110,110);
		inv.setContentAreaFilled(false);
		inv.setOpaque(false);
		inv.setBorderPainted(false);
		inv.setToolTipText("Inventory");
		
		mp = new JLabel(new ImageIcon("MP.png"));
		mp.setBounds(60, 75, 300, 94);
		
		trait = new JButton(new ImageIcon("usetrait.png"));
		trait.setBounds(270, 455, 100,100);
		trait.setContentAreaFilled(false);
		trait.setOpaque(false);
		trait.setBorderPainted(false);
		
		JLabel control = new JLabel(new ImageIcon("controll.png"));
		control.setBounds(260, 210, 192, 192);
		
		fighter = new JLabel(new ImageIcon("Fighter_Sprite.gif"));
		fighter.setBounds(50, 160, 194, 276);
		
		choosespell = new JButton(new ImageIcon("Choose spell.png"));
		choosespell.setBounds(130, 440, 127,127);
		choosespell.setContentAreaFilled(false);
		choosespell.setOpaque(false);
		choosespell.setBorderPainted(false);
		choosespell.setToolTipText("Choose Spell");
		
		Image img = ImageIO.read(new File("task1back.jpg"));
		Image icon = ImageIO.read(new File("icon.png"));
		setIconImage(icon);
		picLabel = new JLabel(new ImageIcon(img));
		buttons = new JButton[10][10];
		setContentPane(picLabel);
		panel.setLayout(new GridLayout(10, 10));
		panel.setBounds(465, 0, 900, 770);
		panel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		panel.setOpaque(false);
		for(int i = 0;i< 10;i ++)
			for(int j = 0; j< 10;j++) {
					buttons[i][j] = new JButton();
					buttons[i][j].setOpaque(false);
					buttons[i][j].setContentAreaFilled(false);
					buttons[i][j].setBorderPainted(true);
					
	}
		
	
		
		
		//add(panel);
		txt = new JTextArea();
		Color customColor = new Color(173,8,0);
		txt.setFont(new Font("Ace Records", Font.PLAIN, 38));
		txt.setText("asass");
		txt.setForeground(customColor);
		txt.setOpaque(false);
		txt.setBounds(105,60, 250, 38);
		txt.setEditable(false);
		
		moveforward = new JButton();
		moveforward.setBounds(335, 245, 40,40);
		moveforward.setContentAreaFilled(false);
		moveforward.setOpaque(false);
		moveforward.setBorderPainted(false);
		
		 movebackward = new JButton();
		movebackward.setBounds(335, 325, 40,40);
		movebackward.setContentAreaFilled(false);
		movebackward.setOpaque(false);
		movebackward.setBorderPainted(false);
		
		 moveleft = new JButton();
		moveleft.setBounds(300, 285, 40,40);
		moveleft.setContentAreaFilled(false);
		moveleft.setOpaque(false);
		moveleft.setBorderPainted(false);
		
		 moveright = new JButton();
		moveright.setBounds(375, 285, 40,40);
		moveright.setContentAreaFilled(false);
		moveright.setOpaque(false);
		moveright.setBorderPainted(false);
		
		frame = new JFrame();
		frame.setContentPane(new JLabel(new ImageIcon("spells_back.png")));
		frame.setTitle("HarryPotter");
		frame.setBounds(500, 270, frame.getPreferredSize().width, 200);
		frame.setForeground(new Color(21,72,119));
		frame.setBackground(new Color(21,72,119));
		frame.setLayout(new GridLayout(1,3));
		
		spell_2 = new JButton(new ImageIcon("healingspell.png"));
		spell_2.setBounds(270, 455, 100,100);
		spell_2.setContentAreaFilled(false);
		spell_2.setOpaque(false);
		spell_2.setBorderPainted(false);
		frame.add(spell_2);
		
		
		spell_3 = new JButton(new ImageIcon("relocatingspell.png"));
		spell_3.setBounds(270, 455, 100,100);
		spell_3.setContentAreaFilled(false);
		spell_3.setOpaque(false);
		spell_3.setBorderPainted(false);
		
		frame.add(spell_3);
		spell_1 = new JButton(new ImageIcon("Damaging spell.png"));
		spell_1.setBounds(270, 455, 100,100);
		spell_1.setContentAreaFilled(false);
		spell_1.setOpaque(false);
		spell_1.setBorderPainted(false);
		frame.add(spell_1);
		frame.setUndecorated(true);
		
		
		
		
		
		
		
		relocatingspell = new JFrame();
		relocatingspell.setContentPane(new JLabel(new ImageIcon("spells_back.png")));
		relocatingspell.setLayout(null);
		relocatingspell.setBounds(600, 200, 350,240);
		relocatingspell.setUndecorated(true);
		
		JTextArea txt_dir = new JTextArea();
		txt_dir.setText("Choose Direction : ");
		txt_dir.setFont(new Font("Harry P", Font.PLAIN, 30));
		txt_dir.setOpaque(false);
		txt_dir.setBounds(15,10, 160, 38);
		txt_dir.setEditable(false);
		
		
		
		JTextArea txt_tar = new JTextArea();
		txt_tar.setText("Choose Target Cell :");
		txt_tar.setFont(new Font("Harry P", Font.PLAIN, 30));
		txt_tar.setOpaque(false);
		txt_tar.setBounds(15,60, 190, 38);
		txt_tar.setEditable(false);
	

		
		
		JTextArea txt_ran = new JTextArea();
		txt_ran.setText("Enter Range :");
		txt_ran.setFont(new Font("Harry P", Font.PLAIN,30));
		txt_ran.setOpaque(false);
		txt_ran.setBounds(15,110, 130, 38);
		txt_ran.setEditable(false);
		
		
		direction = new JComboBox();
		direction.setForeground(new Color(234,179,119));
		direction.setFont(new Font("Harry P", Font.PLAIN, 20));
		
		String a1="FORWARD";
		 String b1="BACKWARD";
		 String c1="RIGHT";
		 String d1="LEFT";
		
		 direction.addItem(a1);
		 direction.addItem(b1);
		 direction.addItem(c1);
		 direction.addItem(d1);
		 direction.setBounds(190,10,direction.getPreferredSize().width,direction.getPreferredSize().height);
		
		
		
		Target = new JComboBox();
		
		 
		 Target.addItem(a1);
		 Target.addItem(b1);
		 Target.addItem(c1);
		 Target.addItem(d1);
		 Target.setForeground(new Color(234,179,119));
		 Target.setFont(new Font("Harry P", Font.PLAIN, 20));
		 Target.setBounds(210,60,Target.getPreferredSize().width,Target.getPreferredSize().height);

		
		range = new JTextArea();
		range.setForeground(new Color(234,179,119));
		range.setFont(new Font("Harry P", Font.PLAIN,20));
		range.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		range.setBounds(150,110,50,Target.getPreferredSize().height);
		range.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		
		reloc = new JButton("OK");
		reloc.setForeground(new Color(234,179,119));
		reloc.setFont(new Font("Harry P", Font.PLAIN, 30));
		reloc.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		reloc.setBounds(120,180,110,50);
		
		 relocatingspell.add(txt_dir);
		 relocatingspell.add(direction);
		 relocatingspell.add(txt_tar);
		 relocatingspell.add(relocatingspell.add(Target));
		 relocatingspell.add(txt_ran);
		 relocatingspell.add(range);
		 relocatingspell.add(reloc);
		 
		 JTextArea txt_dam = new JTextArea();
		 txt_dam.setText("Enter Direction :");
		 txt_dam.setFont(new Font("Harry P", Font.PLAIN,30));
		 txt_dam.setOpaque(false);
		 txt_dam.setBounds(15,15, 150, 38);
		 txt_dam.setEditable(false);
		 
		damagingspell = new JFrame();
		damagingspell.setContentPane(new JLabel(new ImageIcon("spells_back.png")));
		damagingspell.setLayout(null);
		damagingspell.setUndecorated(true);
		 direction1 = new JComboBox();
		 String a="FORWARD";
		 String b="BACKWARD";
		 String c="RIGHT";
		 String d="LEFT";
		 
		 direction1.addItem(a);
		 direction1.addItem(b);
		 direction1.addItem(c);
		 direction1.addItem(d);
		 direction1.setForeground(new Color(234,179,119));
		direction1.setFont(new Font("Harry P", Font.PLAIN, 20));
		 direction1.setBounds(165,15,direction1.getPreferredSize().width,direction1.getPreferredSize().height);
		damagingspell.add(direction1);
		damgeloc = new JButton("OK");
		damgeloc.setForeground(new Color(234,179,119));
		damgeloc.setFont(new Font("Harry P", Font.PLAIN, 30));
		damgeloc.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		damgeloc.setBounds(90,90,110,50);
		damagingspell.add(damgeloc);
		damagingspell.add(txt_dam);
		damagingspell.setBounds(600, 325, 300,170);
		
		 JTextArea txt_pot = new JTextArea();
		 txt_pot.setText("Enter Direction :");
		 txt_pot.setFont(new Font("Harry P", Font.PLAIN,30));
		 txt_pot.setOpaque(false);
		 txt_pot.setBounds(15,15, 150, 38);
		 txt_pot.setEditable(false);
		 
		potions = new JFrame();
		potions.setContentPane(new JLabel(new ImageIcon("spells_back.png")));
		potions.setBounds(600, 325, 330,160);
		potions.setLayout(null);
		potions.setUndecorated(true);
		potion = new JComboBox();
		potion.setBounds(165,15,direction1.getPreferredSize().width+30,direction1.getPreferredSize().height);
		potion.setForeground(new Color(234,179,119));
		potion.setFont(new Font("Harry P", Font.PLAIN, 25));
		potionbut = new JButton("OK");
		potionbut.setForeground(new Color(234,179,119));
		potionbut.setFont(new Font("Harry P", Font.PLAIN, 30));
		potionbut.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		potionbut.setBounds(110,90,110,50);
		potions.add(potion);
		potions.add(txt_pot);
		potions.add(potionbut);
		
		
		
		slythtrait = new JFrame();
		slythtrait.setBounds(500, 270, 228, 170);
		slythtrait.setLayout(new GridLayout(1,5));
		slythdir = new JTextArea("Direction");
		slythdir.setOpaque(false);
		slythtrait.add(slythdir);
		slythdirbut = new JButton("OK");
		slythdirbut.setBorderPainted(false);
		slythtrait.add(slythdirbut);
		
		passturn = new JButton("Pass");
		passturn.setFont(new Font("Harry P", Font.BOLD, 40));
		passturn.setForeground(new Color(160,163,250));
		passturn.setBounds(310, 390, passturn.getPreferredSize().width, passturn.getPreferredSize().height);
		passturn.setOpaque(true);
		passturn.setBorderPainted(true);		
		
		
		hp = new JTextArea("HP:");
		hp.setFont(new Font("Harry P", Font.PLAIN, 15));
		hp.setForeground(new Color(170,163,250));
		hp.setBounds(200, 97, passturn.getPreferredSize().width, passturn.getPreferredSize().height);
		hp.setOpaque(false);
		hp.setEditable(false);
		
		MP = new JTextArea("MP:");
		MP.setFont(new Font("Harry P", Font.PLAIN, 15));
		MP.setForeground(new Color(0,0,0));
		MP.setBounds(130, 118, passturn.getPreferredSize().width, passturn.getPreferredSize().height);
		MP.setOpaque(false);
		MP.setEditable(false);
		
		allowed = new JTextArea("");
		allowed.setFont(new Font("Harry P", Font.PLAIN, 35));
		allowed.setForeground(new Color(170,163,250));
		allowed.setBackground(new Color(0,0,0));
		allowed.setBounds(45, 575, allowed.getPreferredSize().width+15, allowed.getPreferredSize().height+15);
		allowed.setOpaque(true);
		allowed.setEditable(false);
		allowed.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		
		pic = new JLabel(new ImageIcon("Picture1.png"));
		pic.setIcon(new ImageIcon(new ImageIcon("Picture1.png").getImage().getScaledInstance(65, 75, java.awt.Image.SCALE_DEFAULT)));
		pic.setBounds(20, 62, pic.getPreferredSize().width, pic.getPreferredSize().height);
		
		task3 = new JLabel();
		task3.setIcon(new ImageIcon(new ImageIcon("task3back.jpg").getImage().getScaledInstance(1366, 768, java.awt.Image.SCALE_DEFAULT)));
		task3.setBounds(0,0,task3.getPreferredSize().width,task3.getPreferredSize().height);
		
		task2 = new JLabel();
		task2.setIcon(new ImageIcon(new ImageIcon("task2back.jpg").getImage().getScaledInstance(1366, 768, java.awt.Image.SCALE_DEFAULT)));
		task2.setBounds(0,0,task3.getPreferredSize().width,task3.getPreferredSize().height);
		
		
		task1 = new JLabel();
		task1.setIcon(new ImageIcon(new ImageIcon("task1back.jpg").getImage().getScaledInstance(1366, 768, java.awt.Image.SCALE_DEFAULT)));
		task1.setBounds(0,0,task3.getPreferredSize().width,task3.getPreferredSize().height);
		add(pic);
		add(hp);
		add(MP);
		add(passturn);
        add(hp_bar);
        add(inv);
        add(txt);
        add(mp);
        add(fighter);
        add(choosespell);
        add(control);
        add(trait);
        add(moveright);
        add(moveleft);
        add(moveforward);
        add(movebackward);
		setVisible(false);
		    
	    
	      
	}
	public static void main(String [] args) throws IOException {
		new Task1View();
	}

}
