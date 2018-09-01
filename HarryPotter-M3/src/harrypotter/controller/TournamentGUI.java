package harrypotter.controller;

import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.exceptions.OutOfRangeException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.SecondTask;
import harrypotter.model.tournament.TaskListener;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.TreasureCell;
import harrypotter.model.world.WallCell;
import harrypotter.view.Task1View;
import harrypotter.view.TournamentView;

public class TournamentGUI implements TaskListener,ActionListener{
	private Tournament tournament;
	private TournamentView tournamentView;
	public Task1View task1view;
	public ArrayList<String> x = new ArrayList<String>();
	private ImageIcon tempo;
	private Wizard temp = new RavenclawWizard("");
	private boolean firstTask = false; 
	private boolean secondTask = false; 
	private ArrayList<Point> fire = new ArrayList();
	private boolean fireflag = false;
	public TournamentGUI() throws IOException {
		tournamentView = new TournamentView();
		tournament = new Tournament();
		task1view = new Task1View();
		task1view.setVisible(true);
		tournamentView.button.addActionListener(this);
		tournamentView.button2.addActionListener(this);
		tournamentView.button3.addActionListener(this);
		tournamentView.button1.addActionListener(this);
		tournamentView.button4.addActionListener(this);
		tournamentView.button5.addActionListener(this);	
		tournamentView.Player1.addActionListener(this);
		tournamentView.Player2.addActionListener(this);
		tournamentView.Player3.addActionListener(this);
		tournamentView.Player4.addActionListener(this);
		tournamentView.Player5.addActionListener(this);
		tournamentView.Player6.addActionListener(this);
        task1view.movebackward.addActionListener(this);
        task1view.moveforward.addActionListener(this);
        task1view.moveleft.addActionListener(this);
        task1view.moveright.addActionListener(this);
        task1view.spell_1.addActionListener(this);
        task1view.spell_2.addActionListener(this);
        task1view.spell_3.addActionListener(this);
        task1view.damgeloc.addActionListener(this);
        task1view.reloc.addActionListener(this);
        task1view.choosespell.addActionListener(this);
        task1view.slythdirbut.addActionListener(this);
        task1view.trait.addActionListener(this);
        task1view.inv.addActionListener(this);
        task1view.passturn.addActionListener(this);
        task1view.potionbut.addActionListener(this);
        tournamentView.button7.addActionListener(this);
		Spell[] x = (tournament.getSpells()).toArray(new Spell[(tournament.getSpells()).size()]);

		for(int i = 0;i < x.length;i++) {
			if(x[i] instanceof HealingSpell)
			tournamentView.Spells.addItem(x[i].getName());
			if(x[i] instanceof RelocatingSpell)
			tournamentView.Spells2.addItem(x[i].getName());
			if(x[i] instanceof DamagingSpell)
			tournamentView.Spells3.addItem(x[i].getName());
		}
		
		tournamentView.Spells.setSize(tournamentView.Spells.getPreferredSize());
		tournamentView.Spells2.setSize(tournamentView.Spells.getPreferredSize());
		tournamentView.Spells3.setSize(tournamentView.Spells.getPreferredSize()); 
		tournamentView.setVisible(true);
	}

	@Override
	public void onFinishingFirstTask(ArrayList<Champion> winners) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinishingSecondTask(ArrayList<Champion> winners) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinishingThirdTask(Champion winner) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String [] args) throws IOException {
		new TournamentGUI();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == tournamentView.button7) {
			String spell1 = (String) tournamentView.Spells.getSelectedItem();
			String spell2 = (String) tournamentView.Spells2.getSelectedItem();
			String spell3 = (String) tournamentView.Spells3.getSelectedItem();
			String spell_1 = "";
			String spell_2 = "";
			String spell_3 = "";
			for(int i = 0;i < tournament.getSpells().size();i ++) {
				if(tournament.getSpells().get(i).getName().equals(spell1)) 
					spell_1 += 	"Spell Name: "+tournament.getSpells().get(i).getName()+"\n"+"Spell's Healing Amount: "+((HealingSpell) tournament.getSpells().get(i)).getHealingAmount()+"\n"+"Potion's Cost: "+tournament.getSpells().get(i).getCost()+"\n"+"Potion's CoolDown: "+tournament.getSpells().get(i).getDefaultCooldown()+"\n"+"\n";
				if(tournament.getSpells().get(i).getName().equals(spell2))
					spell_2 += 	"Spell Name: "+tournament.getSpells().get(i).getName()+"\n"+"Spell's Range Amount: "+((RelocatingSpell) tournament.getSpells().get(i)).getRange()+"\n"+"Potion's Cost: "+tournament.getSpells().get(i).getCost()+"\n"+"Potion's CoolDown: "+tournament.getSpells().get(i).getDefaultCooldown()+"\n"+"\n";
				if(tournament.getSpells().get(i).getName().equals(spell3)) 
					spell_3 += 	"Spell Name: "+tournament.getSpells().get(i).getName()+"\n"+"Spell's Damaging Amount: "+((DamagingSpell) tournament.getSpells().get(i)).getDamageAmount()+"\n"+"Potion's Cost: "+tournament.getSpells().get(i).getCost()+"\n"+"Potion's CoolDown: "+tournament.getSpells().get(i).getDefaultCooldown()+"\n"+"\n";
			}
			JOptionPane.showMessageDialog(null, spell_1+spell_2+spell_3);
		}
		if(e.getSource() == tournamentView.button3) {
			try {
				glow(tournamentView.button1,new ImageIcon("gryffindor_badge.png"));
				glow(tournamentView.button2,new ImageIcon("hufflepuff_badge.png"));
				glow(tournamentView.button3,new ImageIcon("Ravenclaw_glow.png"));
				glow(tournamentView.button,new ImageIcon("Slytherin_Badge.png"));
				tournamentView.picLabel.setIcon(new ImageIcon(ImageIO.read(new File("Ravenclaw.png"))));
				tournamentView.txt.setForeground(new Color(160,163,250));
				tournamentView.Spells3.setBackground(new Color(8,15,25));
				tournamentView.Spells2.setBackground(new Color(8,15,25));
				tournamentView.Spells.setBackground(new Color(8,15,25));
				tournamentView.Spells.setForeground(new Color(160,163,250));
				tournamentView.Spells2.setForeground(new Color(160,163,250));
				tournamentView.Spells3.setForeground(new Color(160,163,250));
				tournamentView.button4.setForeground(new Color(160,163,250));
				tournamentView.button7.setForeground(new Color(160,163,250));
				tournamentView.setVisible(true);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
			temp = new RavenclawWizard("");
			 
		}
		if(e.getSource() == tournamentView.button2) {
			try {
				glow(tournamentView.button1,new ImageIcon("gryffindor_badge.png"));
				glow(tournamentView.button2,new ImageIcon("hufflepuff_glow.png"));
				glow(tournamentView.button3,new ImageIcon("Ravenclaw_badge.png"));
				glow(tournamentView.button,new ImageIcon("Slytherin_Badge.png"));
				tournamentView.picLabel.setIcon(new ImageIcon(ImageIO.read(new File("hufflepuff.png"))));
				tournamentView.txt.setForeground(new Color(202,156,0));
				tournamentView.Spells2.setBackground(new Color(16,16,16));
				tournamentView.Spells3.setBackground(new Color(16,16,16));
				tournamentView.Spells.setBackground(new Color(16,16,16));
				tournamentView.Spells.setForeground(new Color(202,156,0));
				tournamentView.Spells2.setForeground(new Color(202,156,0));
				tournamentView.Spells3.setForeground(new Color(202,156,0));
				tournamentView.button4.setForeground(new Color(202,156,0));
				tournamentView.button7.setForeground(new Color(202,156,0));
				tournamentView.setVisible(true);
				 
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
			temp = new HufflepuffWizard("");
			  
		}
		if(e.getSource() == tournamentView.button) {
			try {
				glow(tournamentView.button1,new ImageIcon("gryffindor_badge.png"));
				glow(tournamentView.button2,new ImageIcon("hufflepuff_badge.png"));
				glow(tournamentView.button3,new ImageIcon("Ravenclaw_badge.png"));
				glow(tournamentView.button,new ImageIcon("Slytherin_glow.png"));
				tournamentView.picLabel.setIcon(new ImageIcon(ImageIO.read(new File("Slytherin.png"))));
				tournamentView.Spells3.setBackground(new Color(1,15,5));
				tournamentView.Spells.setBackground(new Color(1,15,5));
				tournamentView.Spells2.setBackground(new Color(1,15,5));
				tournamentView.txt.setForeground(new Color(145,186,20));
				tournamentView.Spells.setForeground(new Color(145,186,20));
				tournamentView.Spells2.setForeground(new Color(145,186,20));
				tournamentView.Spells3.setForeground(new Color(145,186,20));
				tournamentView.button4.setForeground(new Color(145,186,20));
				tournamentView.button7.setForeground(new Color(145,186,20));
				tournamentView.setVisible(true);
				 
				  
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
             temp = new SlytherinWizard("");
			  
		}
		if(e.getSource() == tournamentView.button1) { 
			try {
				glow(tournamentView.button1,new ImageIcon("gryffindor_glow.png"));
				glow(tournamentView.button2,new ImageIcon("hufflepuff_Badge.png"));
				glow(tournamentView.button3,new ImageIcon("Ravenclaw_badge.png"));
				glow(tournamentView.button,new ImageIcon("Slytherin_Badge.png"));
				tournamentView.picLabel.setIcon(new ImageIcon(ImageIO.read(new File("gryffindor.png"))));
				tournamentView.Spells3.setBackground(new Color(63,4,4));
				tournamentView.Spells.setBackground(new Color(63,4,4));
				tournamentView.Spells2.setBackground(new Color(63,4,4));
				tournamentView.txt.setForeground(new Color(174,174,174));
				tournamentView.Spells.setForeground(new Color(174,174,174));
				tournamentView.Spells2.setForeground(new Color(174,174,174));
				tournamentView.Spells3.setForeground(new Color(174,174,174));
				tournamentView.button4.setForeground(new Color(174,174,174));
				tournamentView.button7.setForeground(new Color(174,174,174));
				tournamentView.setVisible(true);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
		temp = new GryffindorWizard("");
		  
	   }
	 		
		if(e.getSource() == tournamentView.button5) {
			tournamentView.setContentPane(tournamentView.picLabel);
			tournamentView.validate();
		}
		if(e.getSource() == tournamentView.button4) {
			if(tournamentView.button4.getText().equals("Begin Tournament")){
				try {
					tournament.beginTournament();
					fire = tournament.getFirstTask().getMarkedCells();
					tournamentView.setVisible(false);
					updatemap1(tournament);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				tournamentView.frame.setVisible(true);
			
			
			}
			
		}
		if(e.getSource() == tournamentView.Player1) {
			tempo = new ImageIcon("Wizard.gif");
			tournamentView.Player1.setEnabled(false);
			tournamentView.frame.setVisible(false);
			
		}
		if(e.getSource() == tournamentView.Player2) {
			tempo = new ImageIcon("Amazon_Sprite.gif");
			tournamentView.Player2.setEnabled(false);
			tournamentView.frame.setVisible(false);
			
		}
		if(e.getSource() == tournamentView.Player3) {
			tempo = new ImageIcon("Dwarf_Sprite.gif");
			tournamentView.Player3.setEnabled(false);
			tournamentView.frame.setVisible(false);
		}
		if(e.getSource() == tournamentView.Player4) {
			tempo = new ImageIcon("Elf_Sprite.gif");
			tournamentView.Player4.setEnabled(false);
			tournamentView.frame.setVisible(false);
		}
		if(e.getSource() == tournamentView.Player5) {
			tempo = new ImageIcon("Fighter_Sprite.gif");
			tournamentView.Player5.setEnabled(false);
			tournamentView.frame.setVisible(false);
		}
		if(e.getSource() == tournamentView.Player6) {
			tempo = new ImageIcon("Sorceress_Sprite.gif");
			tournamentView.Player6.setEnabled(false);
			tournamentView.frame.setVisible(false);
		}
		if(e.getSource() == tournamentView.Player1 || (e.getSource() == tournamentView.Player1) || 	(e.getSource() == tournamentView.Player2) ||(e.getSource() == tournamentView.Player3) ||(e.getSource() == tournamentView.Player4) ||(e.getSource() == tournamentView.Player5) ||(e.getSource() == tournamentView.Player6)) {
			temp.setName(tournamentView.txt.getText());
			temp.setImageicon(tempo);
			tournamentView.txt.setText("");
			String spell1 = (String) tournamentView.Spells.getSelectedItem();
			String spell2 = (String) tournamentView.Spells2.getSelectedItem();
			String spell3 = (String) tournamentView.Spells3.getSelectedItem();
			for(int i = 0;i < tournament.getSpells().size();i ++) {
				if(tournament.getSpells().get(i).getName().equals(spell1)) 
					temp.getSpells().add(new HealingSpell(tournament.getSpells().get(i).getName(),tournament.getSpells().get(i).getCost(),tournament.getSpells().get(i).getDefaultCooldown(),((HealingSpell) tournament.getSpells().get(i)).getHealingAmount()));		
				if(tournament.getSpells().get(i).getName().equals(spell2))
					temp.getSpells().add(new RelocatingSpell(tournament.getSpells().get(i).getName(),tournament.getSpells().get(i).getCost(),tournament.getSpells().get(i).getDefaultCooldown(),((RelocatingSpell) tournament.getSpells().get(i)).getRange()));
				if(tournament.getSpells().get(i).getName().equals(spell3)) 
					temp.getSpells().add(new DamagingSpell(tournament.getSpells().get(i).getName(),tournament.getSpells().get(i).getCost(),tournament.getSpells().get(i).getDefaultCooldown(),((DamagingSpell) tournament.getSpells().get(i)).getDamageAmount()));
			}
			tournament.addChampion(((Champion) temp));
			if(((Wizard)temp) instanceof HufflepuffWizard)
				temp = new HufflepuffWizard("");
			else
				if(((Wizard)temp) instanceof RavenclawWizard)
					temp = new RavenclawWizard("");
				else
					if(((Wizard)temp) instanceof GryffindorWizard)
						temp = new GryffindorWizard("");
					else
						if(((Wizard)temp) instanceof SlytherinWizard)
							temp = new SlytherinWizard("");
			tournamentView.button4.setText("Create Champion "+(tournament.getChampions().size()+1));
			if(tournamentView.button4.getText().equals("Create Champion 5")) {
				tournamentView.button4.setText("Begin Tournament");
				tournamentView.txt.setVisible(false);
				}
		
			tournamentView.validate();
			tournamentView.setVisible(true);
			
		}
		if(e.getSource() == task1view.movebackward) {
			boolean flag = true;
			if(secondTask==true){
				try {
					fire = tournament.getFirstTask().getMarkedCells();
					tournament.getThirdTask().moveBackward();
					updatemap3(tournament);
					
				} catch (OutOfBordersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
					
				} catch (InvalidTargetCellException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			
			}
			else
			if(firstTask==true){
				try {
					fire = tournament.getFirstTask().getMarkedCells();
					tournament.getSecondTask().moveBackward();
					updatemap2(tournament);
					
				} catch (OutOfBordersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (InvalidTargetCellException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else{
			try {
				fire = tournament.getFirstTask().getMarkedCells();
				tournament.getFirstTask().moveBackward();
				updatemap1(tournament);
				
			} catch (OutOfBordersException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (InvalidTargetCellException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
			 }
		}
		if(e.getSource() == task1view.moveright) {
			if(secondTask==true){
				try {
					fire = tournament.getFirstTask().getMarkedCells();
					tournament.getThirdTask().moveRight();
					updatemap3(tournament);
					
				} catch (OutOfBordersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (InvalidTargetCellException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			
				
			}
			else if(firstTask==true){
				try {
					fire = tournament.getFirstTask().getMarkedCells();
					tournament.getSecondTask().moveRight();
					updatemap2(tournament);
					
				} catch (OutOfBordersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (InvalidTargetCellException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else{
			try {
				fire = tournament.getFirstTask().getMarkedCells();
				tournament.getFirstTask().moveRight();
				updatemap1(tournament);
				
			} catch (OutOfBordersException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (InvalidTargetCellException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
			 }
		}
		if(e.getSource() == task1view.moveforward) {
			if(secondTask==true){
				try {
					fire = tournament.getFirstTask().getMarkedCells();
					tournament.getThirdTask().moveForward();
					updatemap3(tournament);
					
				} catch (OutOfBordersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (InvalidTargetCellException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else
		if(firstTask==true){
	try {
		fire = tournament.getFirstTask().getMarkedCells();
				tournament.getSecondTask().moveForward();
				
			
			} catch (OutOfBordersException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (InvalidTargetCellException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
			try {
				updatemap2(tournament);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}	
		}
		else {
	try {
		fire = tournament.getFirstTask().getMarkedCells();
				tournament.getFirstTask().moveForward();
				
			
			} catch (OutOfBordersException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (InvalidTargetCellException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
			try {
				updatemap1(tournament);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}	
		} }
		if(e.getSource() == task1view.moveleft) {
			if(secondTask==true){
				try {
					fire = tournament.getFirstTask().getMarkedCells();
					tournament.getThirdTask().moveLeft();
					updatemap3(tournament);
					
				} catch (OutOfBordersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (InvalidTargetCellException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else
			if(firstTask==true){
				try {
					fire = tournament.getFirstTask().getMarkedCells();
					tournament.getSecondTask().moveLeft();
					updatemap2(tournament);
					
				} catch (OutOfBordersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (InvalidTargetCellException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else{
			try {
				fire = tournament.getFirstTask().getMarkedCells();
				tournament.getFirstTask().moveLeft();
				updatemap1(tournament);
				
			} catch (OutOfBordersException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (InvalidTargetCellException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
			 }
		}
		if(e.getSource() ==  task1view.choosespell) {
			task1view.frame.setVisible(true);
		}
		Spell y = null;
		if(e.getSource() == task1view.spell_1 ) {
			
			if(secondTask==true){
				task1view.frame.setVisible(false);
				y =  ((Wizard)tournament.getThirdTask().getCurrentChamp()).getSpells().get(1);
				String x = task1view.spell_1.getIcon().toString(); 
				if(x.equals("relocatingspell.png")) {
					task1view.relocatingspell.setVisible(true);
				}
			    if(x.equals("healingspell.png")) {
				   try {
					tournament.getThirdTask().castHealingSpell((HealingSpell) y);
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughIPException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				   try {
					updatemap3(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
			    
			    if(x.equals("Damaging spell.png")) {
			    	task1view.damagingspell.setVisible(true);
			    }
			}
			else if(firstTask==true){
				task1view.frame.setVisible(false);
				y =  ((Wizard)tournament.getSecondTask().getCurrentChamp()).getSpells().get(1);
				String x = task1view.spell_1.getIcon().toString(); 
				if(x.equals("relocatingspell.png")) {
					task1view.relocatingspell.setVisible(true);
				}
			    if(x.equals("healingspell.png")) {
				   try {
					tournament.getSecondTask().castHealingSpell((HealingSpell) y);
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughIPException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				   try {
					updatemap2(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
			    
			    if(x.equals("Damaging spell.png")) {
			    	task1view.damagingspell.setVisible(true);
			    }
			}
			else{
			task1view.frame.setVisible(false);
			y =  ((Wizard)tournament.getFirstTask().getCurrentChamp()).getSpells().get(1);
			String x = task1view.spell_1.getIcon().toString(); 
			if(x.equals("relocatingspell.png")) {
				task1view.relocatingspell.setVisible(true);
			}
		    if(x.equals("healingspell.png")) {
			   try {
				   
				tournament.getFirstTask().castHealingSpell((HealingSpell) y);
				
			} catch (InCooldownException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (NotEnoughIPException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
			   try {
				updatemap1(tournament);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
		}
		    
		    if(x.equals("Damaging spell.png")) {
		    	task1view.damagingspell.setVisible(true);
		    }
		    }
		} 
		if(e.getSource() == task1view.spell_2) {
			task1view.frame.setVisible(false);
			if(secondTask==true){
				task1view.frame.setVisible(false);
				y =  ((Wizard)tournament.getThirdTask().getCurrentChamp()).getSpells().get(1);
				String x = task1view.spell_2.getIcon().toString(); 
				if(x.equals("relocatingspell.png")) {
					task1view.relocatingspell.setVisible(true);
					
				}
			    if(x.equals("healingspell.png")) {
				   try {
					tournament.getThirdTask().castHealingSpell((HealingSpell) y);
					
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughIPException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				   try {
					updatemap3(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
			    
			    if(x.equals("Damaging spell.png")) {
			    	task1view.damagingspell.setVisible(true);
			    	
			    }
			}
			else if(firstTask==true){
				task1view.frame.setVisible(false);
				y =  ((Wizard)tournament.getSecondTask().getCurrentChamp()).getSpells().get(1);
				String x = task1view.spell_2.getIcon().toString(); 
				  
				if(x.equals("relocatingspell.png")) {
					task1view.relocatingspell.setVisible(true);
					
				}
			    if(x.equals("healingspell.png")) {
				   try {
					  
					tournament.getSecondTask().castHealingSpell((HealingSpell) y);
				
					
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughIPException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				   try {
					updatemap2(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
			    
			    if(x.equals("Damaging spell.png")) {
			    	task1view.damagingspell.setVisible(true);
			    	
			    }
			}
			else{
			y =  ((Wizard)tournament.getFirstTask().getCurrentChamp()).getSpells().get(1);
			String x = task1view.spell_2.getIcon().toString(); 
			if(x.equals("relocatingspell.png")) {
				task1view.relocatingspell.setVisible(true);
				
			}
		    if(x.equals("healingspell.png")) {
			   try {
				tournament.getFirstTask().castHealingSpell((HealingSpell) y);
				

			} catch (InCooldownException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (NotEnoughIPException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
			   try {
				updatemap1(tournament);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
		}
		    
		    if(x.equals("Damaging spell.png")) {
		    	task1view.damagingspell.setVisible(true);
		    	
		    } }
		}
		if(e.getSource() == task1view.spell_3) {
			
			task1view.frame.setVisible(false);
			if(secondTask==true){
				task1view.frame.setVisible(false);
				y =  ((Wizard)tournament.getThirdTask().getCurrentChamp()).getSpells().get(1);
				String x = task1view.spell_3.getIcon().toString(); 
				if(x.equals("relocatingspell.png")) {
					task1view.relocatingspell.setVisible(true);
					
				}
			    if(x.equals("healingspell.png")) {
				   try {
					tournament.getThirdTask().castHealingSpell((HealingSpell) y);
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughIPException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}	
				   try {
					updatemap3(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
			    if(x.equals("Damaging spell.png")) {
			    	task1view.damagingspell.setVisible(true);
			    }
			}
			if(firstTask==true){
				task1view.frame.setVisible(false);
				y =  ((Wizard)tournament.getSecondTask().getCurrentChamp()).getSpells().get(1);
				String x = task1view.spell_3.getIcon().toString(); 
				if(x.equals("relocatingspell.png")) {
					task1view.relocatingspell.setVisible(true);
					
				}
			    if(x.equals("healingspell.png")) {
				   try {
					tournament.getSecondTask().castHealingSpell((HealingSpell) y);
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughIPException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}	
				   try {
					updatemap2(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
			    if(x.equals("Damaging spell.png")) {
			    	task1view.damagingspell.setVisible(true);
			    }
			}
			else{
			y =  ((Wizard)tournament.getFirstTask().getCurrentChamp()).getSpells().get(1);
			String x = task1view.spell_3.getIcon().toString(); 
			if(x.equals("relocatingspell.png")) {
				task1view.relocatingspell.setVisible(true);
				
			}
		    if(x.equals("healingspell.png")) {
			   try {
				tournament.getFirstTask().castHealingSpell((HealingSpell) y);
			} catch (InCooldownException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (NotEnoughIPException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}	
			   try {
				updatemap1(tournament);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
		}
		    if(x.equals("Damaging spell.png")) {
		    	task1view.damagingspell.setVisible(true);
		    } }
		} 
		if(e.getSource() == task1view.damgeloc) {
			if(secondTask==true){
				String direction = (String) task1view.direction1.getSelectedItem();
				task1view.damagingspell.setVisible(false);
				y =  ((Wizard)tournament.getThirdTask().getCurrentChamp()).getSpells().get(0);
				try {
					tournament.getThirdTask().castDamagingSpell((DamagingSpell) y, Direction.valueOf(direction));
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughIPException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (OutOfBordersException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (InvalidTargetCellException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				try {
					updatemap3(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(firstTask==true){
				String direction = (String) task1view.direction1.getSelectedItem();
				task1view.damagingspell.setVisible(false);
				y =  ((Wizard)tournament.getSecondTask().getCurrentChamp()).getSpells().get(0);
				try {
					tournament.getSecondTask().castDamagingSpell((DamagingSpell) y, Direction.valueOf(direction));
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughIPException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (OutOfBordersException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (InvalidTargetCellException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				try {
					updatemap2(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
			String direction = (String) task1view.direction1.getSelectedItem();
			task1view.damagingspell.setVisible(false);
			y =  ((Wizard)tournament.getFirstTask().getCurrentChamp()).getSpells().get(0);
			try {
				tournament.getFirstTask().castDamagingSpell((DamagingSpell) y, Direction.valueOf(direction));
			} catch (InCooldownException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (NotEnoughIPException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (OutOfBordersException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (InvalidTargetCellException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
			try {
				updatemap1(tournament);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} }
		}
		if(e.getSource() == task1view.reloc) {
			if(secondTask==true){
				String direction =(String) task1view.direction.getSelectedItem();
				String range = task1view.range.getText();
				String target =(String) task1view.Target.getSelectedItem();
				task1view.relocatingspell.setVisible(false);
				y =  ((Wizard)tournament.getThirdTask().getCurrentChamp()).getSpells().get(2);
				try {
					tournament.getThirdTask().castRelocatingSpell((RelocatingSpell) y, Direction.valueOf(direction), Direction.valueOf(target), Integer.parseInt(range));
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughIPException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (OutOfBordersException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (InvalidTargetCellException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (OutOfRangeException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				try {
					updatemap3(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(firstTask==true){
				String direction =(String) task1view.direction.getSelectedItem();
				String range = task1view.range.getText();
				String target =(String) task1view.Target.getSelectedItem();
				task1view.relocatingspell.setVisible(false);
				y =  ((Wizard)tournament.getSecondTask().getCurrentChamp()).getSpells().get(2);
				try {
					tournament.getSecondTask().castRelocatingSpell((RelocatingSpell) y, Direction.valueOf(direction), Direction.valueOf(target), Integer.parseInt(range));
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughIPException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (OutOfBordersException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (InvalidTargetCellException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (OutOfRangeException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				try {
					updatemap2(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
			String direction =(String) task1view.direction.getSelectedItem();
			String range = task1view.range.getText();
			String target =(String) task1view.Target.getSelectedItem();
			task1view.relocatingspell.setVisible(false);
			
			y =  ((Wizard)tournament.getFirstTask().getCurrentChamp()).getSpells().get(2);
			try {
				tournament.getFirstTask().castRelocatingSpell((RelocatingSpell) y, Direction.valueOf(direction), Direction.valueOf(target), Integer.parseInt(range));
			} catch (InCooldownException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (NotEnoughIPException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (OutOfBordersException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (InvalidTargetCellException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (OutOfRangeException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
			try {
				updatemap1(tournament);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} }
			
		}
		if(e.getSource() == task1view.trait) {
			if(secondTask==true){
				if(((Wizard)(tournament.getThirdTask().getCurrentChamp())) instanceof SlytherinWizard)
					task1view.slythtrait.setVisible(true);
				else
					if(((Wizard)(tournament.getThirdTask().getCurrentChamp()))instanceof RavenclawWizard)
						try {
							((RavenclawWizard)((Wizard)(tournament.getThirdTask().getCurrentChamp()))).useTrait();
							
						} catch (InCooldownException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
						}
						else
							if(((Wizard)(tournament.getThirdTask().getCurrentChamp()))instanceof HufflepuffWizard)
								try {
									((HufflepuffWizard)((Wizard)(tournament.getThirdTask().getCurrentChamp()))).useTrait();
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
								}
			
							else
								if(((Wizard)(tournament.getThirdTask().getCurrentChamp()))instanceof GryffindorWizard)
									try {
										((GryffindorWizard)((Wizard)(tournament.getThirdTask().getCurrentChamp()))).useTrait();
										
										task1view.setVisible(true);
									} catch (InCooldownException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
									}
				task1view.trait.setEnabled(false);
			}
			else if(firstTask==true){
				if(((Wizard)(tournament.getSecondTask().getCurrentChamp())) instanceof SlytherinWizard)
					task1view.slythtrait.setVisible(true);
				else
					if(((Wizard)(tournament.getSecondTask().getCurrentChamp()))instanceof RavenclawWizard)
						try {
							((RavenclawWizard)((Wizard)(tournament.getSecondTask().getCurrentChamp()))).useTrait();
						} catch (InCooldownException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
						}
						else
							if(((Wizard)(tournament.getSecondTask().getCurrentChamp()))instanceof HufflepuffWizard)
								try {
									((HufflepuffWizard)((Wizard)(tournament.getSecondTask().getCurrentChamp()))).useTrait();
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
								}
							else
								if(((Wizard)(tournament.getSecondTask().getCurrentChamp()))instanceof GryffindorWizard)
									try {
										
										((GryffindorWizard)((Wizard)(tournament.getSecondTask().getCurrentChamp()))).useTrait();
										
										task1view.setVisible(true);
									} catch (InCooldownException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
									}
				task1view.trait.setEnabled(false);
			}
			else{
			if(((Wizard)(tournament.getFirstTask().getCurrentChamp())) instanceof SlytherinWizard)
				task1view.slythtrait.setVisible(true);
			else
				if(((Wizard)(tournament.getFirstTask().getCurrentChamp()))instanceof RavenclawWizard)
					try {
						((RavenclawWizard)((Wizard)(tournament.getFirstTask().getCurrentChamp()))).useTrait();
						ArrayList<Point> firea = tournament.getFirstTask().getMarkedCells(); 
						task1view.buttons[firea.get(0).x][firea.get(0).y].add(new JLabel(new ImageIcon("unnamed.gif")));
						task1view.buttons[firea.get(1).x][firea.get(1).y].add(new JLabel(new ImageIcon("unnamed.gif")));
						task1view.revalidate();
					} catch (InCooldownException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
					}
					else
						if(((Wizard)(tournament.getFirstTask().getCurrentChamp()))instanceof HufflepuffWizard)
							try {
								
								((HufflepuffWizard)((Wizard)(tournament.getFirstTask().getCurrentChamp()))).useTrait();
								
							} catch (InCooldownException e1) {
								
								JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
							}
						else
							if(((Wizard)(tournament.getFirstTask().getCurrentChamp()))instanceof GryffindorWizard)
								try {
									((GryffindorWizard)((Wizard)(tournament.getFirstTask().getCurrentChamp()))).useTrait();
									
									task1view.setVisible(true);
									
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
								}
			task1view.trait.setEnabled(false);
			}
		}
		if(e.getSource() == task1view.slythdirbut) {
			if(secondTask==true){
				String x =  task1view.slythdir.getText();
				((SlytherinWizard)((Wizard)(tournament.getThirdTask().getCurrentChamp()))).setTraitDirection(Direction.valueOf(x));
				try {
					((SlytherinWizard)((Wizard)(tournament.getThirdTask().getCurrentChamp()))).useTrait();
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (OutOfBordersException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (InvalidTargetCellException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				task1view.slythtrait.setVisible(false);
				 try {
					updatemap3(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} 
			}
			else if(firstTask==true){
				String x =  task1view.slythdir.getText();
				((SlytherinWizard)((Wizard)(tournament.getSecondTask().getCurrentChamp()))).setTraitDirection(Direction.valueOf(x));
				try {
					((SlytherinWizard)((Wizard)(tournament.getSecondTask().getCurrentChamp()))).useTrait();
				} catch (InCooldownException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (OutOfBordersException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (InvalidTargetCellException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				task1view.slythtrait.setVisible(false);
				 try {
					updatemap2(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				} 
			}
			else{
			String x =  task1view.slythdir.getText();
			((SlytherinWizard)((Wizard)(tournament.getFirstTask().getCurrentChamp()))).setTraitDirection(Direction.valueOf(x));
			try {
				((SlytherinWizard)((Wizard)(tournament.getFirstTask().getCurrentChamp()))).useTrait();
			} catch (InCooldownException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (OutOfBordersException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (InvalidTargetCellException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
			task1view.slythtrait.setVisible(false);
			 try {
				updatemap1(tournament);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} 
		}
		}
		if(e.getSource() == task1view.inv) 
			task1view.potions.setVisible(true);
		if(e.getSource() == task1view.potionbut) {
			if(secondTask==true){
				for(int i = 0;i<((Wizard)(tournament.getThirdTask().getCurrentChamp())).getInventory().size();i ++) {
					if(((Wizard)(tournament.getThirdTask().getCurrentChamp())).getInventory().get(i).getName().equals(task1view.potion.getSelectedItem().toString()))
						tournament.getThirdTask().usePotion((Potion) ((Wizard)(tournament.getThirdTask().getCurrentChamp())).getInventory().get(i));
				}
				UpdatePotions(((Wizard)(tournament.getThirdTask().getCurrentChamp())));
				task1view.potions.setVisible(false);
			}
			else if(firstTask==true){
				for(int i = 0;i<((Wizard)(tournament.getSecondTask().getCurrentChamp())).getInventory().size();i ++) {
					if(((Wizard)(tournament.getSecondTask().getCurrentChamp())).getInventory().get(i).getName().equals(task1view.potion.getSelectedItem().toString()))
						tournament.getSecondTask().usePotion((Potion) ((Wizard)(tournament.getSecondTask().getCurrentChamp())).getInventory().get(i));
				}
				UpdatePotions(((Wizard)(tournament.getSecondTask().getCurrentChamp())));
				task1view.potions.setVisible(false);
			} else{
			for(int i = 0;i<((Wizard)(tournament.getFirstTask().getCurrentChamp())).getInventory().size();i ++) {
				if(((Wizard)(tournament.getFirstTask().getCurrentChamp())).getInventory().get(i).getName().equals(task1view.potion.getSelectedItem().toString()))
					tournament.getFirstTask().usePotion((Potion) ((Wizard)(tournament.getFirstTask().getCurrentChamp())).getInventory().get(i));
			}
			UpdatePotions(((Wizard)(tournament.getFirstTask().getCurrentChamp())));
			task1view.potions.setVisible(false); }
		}
		if(e.getSource() == task1view.passturn) {
			if(secondTask==true){
				try {
					tournament.getThirdTask().finalizeAction();
				} catch (IOException e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				try {
					updatemap3(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(firstTask==true){
				try {
					tournament.getSecondTask().finalizeAction();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
				try {
					updatemap2(tournament);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			} else{
			try {
				tournament.getFirstTask().finalizeAction();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
			try {
				updatemap1(tournament);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} }
		} }
	
	public void glow(JButton a,ImageIcon x) {
		a.setIcon(x);
		
	}
	public void updatemap1(Tournament a) throws IOException {
		
		if(firstTask == false) {
			if((Wizard)(tournament.getFirstTask().getCurrentChamp()) instanceof HufflepuffWizard)
				task1view.pic.setIcon(new ImageIcon(new ImageIcon("hufflepuff_badge.png").getImage().getScaledInstance(65, 75, java.awt.Image.SCALE_DEFAULT)));
			else
				if((Wizard)(tournament.getFirstTask().getCurrentChamp()) instanceof SlytherinWizard)
					task1view.pic.setIcon(new ImageIcon(new ImageIcon("Slytherin_Badge.png").getImage().getScaledInstance(65, 75, java.awt.Image.SCALE_DEFAULT)));
				else
					if((Wizard)(tournament.getFirstTask().getCurrentChamp()) instanceof GryffindorWizard)
						task1view.pic.setIcon(new ImageIcon(new ImageIcon("gryffindor_Badge.png").getImage().getScaledInstance(65, 75, java.awt.Image.SCALE_DEFAULT)));
					else
						if((Wizard)(tournament.getFirstTask().getCurrentChamp()) instanceof RavenclawWizard)
							task1view.pic.setIcon(new ImageIcon(new ImageIcon("Ravenclaw_badge.png").getImage().getScaledInstance(65, 75, java.awt.Image.SCALE_DEFAULT)));
		task1view.spell_2.setToolTipText("<html>"+"Name : "+((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(1).getName()+"<br>"+
				"Cost : "+((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(1).getCost()+"<br>"+
				"Healing Amount :"+((HealingSpell) ((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(1)).getHealingAmount()+"<br>"+
				"Cooldown : "+((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(1).getCoolDown()+"<html>");
		
		task1view.spell_1.setToolTipText("<html>"+"Name : "+((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(0).getName()+"<br>"+
				"Cost : "+((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(0).getCost()+"<br>"+"Spell Damage : "
				+((DamagingSpell) ((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(0)).getDamageAmount()+"<br>"+
				"Cooldown : "+((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(0).getCoolDown()+"<html>");
		
		
		task1view.spell_3.setToolTipText("<html>"+"Name : "+((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(2).getName()+"<br>"+
				"Cost : "+((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(2).getCost()+"<br>"+
				"Range Amount :"+((RelocatingSpell) ((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(2)).getRange()+"<br>"+
				"Cooldown : "+((Wizard)(tournament.getFirstTask().getCurrentChamp())).getSpells().get(2).getCoolDown()+"<html>");
		task1view.fighter.setToolTipText("Allowed Moves : "+tournament.getFirstTask().getAllowedMoves());
		task1view.trait.setToolTipText("Cooldown : "+((Wizard)(tournament.getFirstTask().getCurrentChamp())).getTraitCooldown());
		UpdateHPAndMP(((Wizard)(tournament.getFirstTask().getCurrentChamp())));
		UpdatePotions(((Wizard)(tournament.getFirstTask().getCurrentChamp())));
		task1view.txt.setText(((Wizard)(tournament.getFirstTask().getCurrentChamp())).getName());
		task1view.panel.removeAll();
		task1view.fighter.setIcon(((Wizard)tournament.getFirstTask().getCurrentChamp()).getImageicon());
		for (int i = 0;i < task1view.buttons.length;i++) {
			for(int j = 0;j < 10; j++) {
				if(i==4&&j==4){
					 if(((i==fire.get(0).x&&j==fire.get(0).y)||(i==fire.get(1).x&&j==fire.get(1).y))&&(fireflag)){
						task1view.buttons[i][j] = new JButton(new ImageIcon("collect.png"));
						task1view.buttons[i][j].add(new JLabel(new ImageIcon("unnamed.gif")));
						 
						task1view.panel.add(task1view.buttons[i][j]);}
					else{
					
					task1view.buttons[i][j] = new JButton(new ImageIcon("collect.png"));
					task1view.panel.add(task1view.buttons[i][j]);}
				}
				
				else if(tournament.getFirstTask().getMap()[i][j] instanceof ObstacleCell){
					 if(((i==fire.get(0).x&&j==fire.get(0).y)||(i==fire.get(1).x&&j==fire.get(1).y))&&(fireflag)){
							task1view.buttons[i][j] = new JButton(new ImageIcon("obss.png"));
							task1view.buttons[i][j].add(new JLabel(new ImageIcon("unnamed.gif")));
							 
							task1view.panel.add(task1view.buttons[i][j]);
							}
					 else{
						 
				task1view.buttons[i][j] = new JButton(new ImageIcon("obss.png"));
				task1view.panel.add(task1view.buttons[i][j]);}
				
				}
			else
			if(tournament.getFirstTask().getMap()[i][j] instanceof ChampionCell){
				Wizard champ = (Wizard) ((ChampionCell)(tournament.getFirstTask().getMap()[i][j])).getChamp();
				 if(((i==fire.get(0).x&&j==fire.get(0).y)||(i==fire.get(1).x&&j==fire.get(1).y))&&(fireflag)){
					task1view.buttons[i][j] = new JButton(new ImageIcon("normal.png"));		
					task1view.buttons[i][j].add(new JLabel(new ImageIcon("unnamed.gif")));
					task1view.buttons[i][j].add(new JLabel(new ImageIcon("smaller_"+champ.getImageicon().getDescription())));
					task1view.panel.add(task1view.buttons[i][j]);
					} else{
						
				task1view.buttons[i][j] = new JButton(new ImageIcon("normal.png"));
				task1view.buttons[i][j].add(new JLabel(new ImageIcon("smaller_"+champ.getImageicon().getDescription())));
				task1view.panel.add(task1view.buttons[i][j]);}
				
			}
			else
				if(tournament.getFirstTask().getMap()[i][j] instanceof CupCell){
					task1view.buttons[i][j] = new JButton(new ImageIcon("normal.png"));
					task1view.panel.add(task1view.buttons[i][j]);
				}
				else
					if(tournament.getFirstTask().getMap()[i][j] instanceof CollectibleCell){
						 if(((i==fire.get(0).x&&j==fire.get(0).y)||(i==fire.get(1).x&&j==fire.get(1).y))&&(fireflag)){
							task1view.buttons[i][j] = new JButton(new ImageIcon("normal.png"));
							task1view.buttons[i][j].add(new JLabel(new ImageIcon("unnamed.gif")));
							task1view.panel.add(task1view.buttons[i][j]);
							 
							} else{
								
						task1view.buttons[i][j] = new JButton(new ImageIcon("normal.png"));
						task1view.panel.add(task1view.buttons[i][j]);}
					}
					else
						if(tournament.getFirstTask().getMap()[i][j] instanceof EmptyCell){
							 if(((i==fire.get(0).x&&j==fire.get(0).y)||(i==fire.get(1).x&&j==fire.get(1).y))&&(fireflag)){
								task1view.buttons[i][j] = new JButton(new ImageIcon("normal.png"));
								task1view.buttons[i][j].add(new JLabel(new ImageIcon("unnamed.gif")));
								task1view.panel.add(task1view.buttons[i][j]);
								 
								} else{
									
							task1view.buttons[i][j] = new JButton(new ImageIcon("normal.png"));
							task1view.panel.add(task1view.buttons[i][j]); }
						}
						else
							if(tournament.getFirstTask().getMap()[i][j] instanceof WallCell){
								task1view.buttons[i][j] = new JButton(new ImageIcon("try.png"));
								//task1view.buttons[i][j].add(new JLabel(new ImageIcon("wall.png")));
								task1view.panel.add(task1view.buttons[i][j]);
							}
							else
							{
								task1view.buttons[i][j] = new JButton(new ImageIcon("normal.png"));
								task1view.panel.add(task1view.buttons[i][j]);
							}

		
			task1view.buttons[i][j].setContentAreaFilled(false);
			task1view.buttons[i][j].setBorderPainted(false);
			
			}
			
			}
		
		UpdateSpells(((Wizard)(tournament.getFirstTask().getCurrentChamp())));
		task1view.add(task1view.panel);
		
		task1view.panel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		task1view.setVisible(true);
		fireflag = true;
		if(((Wizard)(tournament.getFirstTask().getCurrentChamp())).getTraitCooldown() == 0)
			task1view.trait.setEnabled(true);
			else {
				task1view.trait.setEnabled(false);
			}
		if(tournament.getFirstTask().getChampions().size() == 0) {
		      tournament.onFinishingFirstTask(tournament.getFirstTask().getWinners());
		      if(tournament.getFirstTask().getWinners().size()== 0)
		    	  task1view.setVisible(false);
		      firstTask = true;
		     
		      
		}
		
		}
		else {
			updatemap2(tournament);
		}
	}
	public void updatemap2(Tournament a)throws IOException {
		firstTask=true;
		if(secondTask==false){
			task1view.picLabel.setIcon(new ImageIcon(new ImageIcon("task2back.jpg").getImage().getScaledInstance(1366, 768, java.awt.Image.SCALE_DEFAULT)));	
			if((Wizard)(tournament.getSecondTask().getCurrentChamp()) instanceof HufflepuffWizard)
				task1view.pic.setIcon(new ImageIcon(new ImageIcon("hufflepuff_badge.png").getImage().getScaledInstance(65, 75, java.awt.Image.SCALE_DEFAULT)));
			else
				if((Wizard)(tournament.getSecondTask().getCurrentChamp()) instanceof SlytherinWizard)
					task1view.pic.setIcon(new ImageIcon(new ImageIcon("Slytherin_Badge.png").getImage().getScaledInstance(65, 75, java.awt.Image.SCALE_DEFAULT)));
				else
					if((Wizard)(tournament.getSecondTask().getCurrentChamp()) instanceof GryffindorWizard)
						task1view.pic.setIcon(new ImageIcon(new ImageIcon("gryffindor_Badge.png").getImage().getScaledInstance(65, 75, java.awt.Image.SCALE_DEFAULT)));
					else
						if((Wizard)(tournament.getSecondTask().getCurrentChamp()) instanceof RavenclawWizard)
							task1view.pic.setIcon(new ImageIcon(new ImageIcon("Ravenclaw_badge.png").getImage().getScaledInstance(65, 75, java.awt.Image.SCALE_DEFAULT)));
		
			task1view.spell_2.setToolTipText("<html>"+"Name : "+((Wizard)(tournament.getSecondTask().getCurrentChamp())).getSpells().get(1).getName()+"<br>"+
					"Cost : "+((Wizard)(tournament.getSecondTask().getCurrentChamp())).getSpells().get(1).getCost()+"<br>"+
					"Healing Amount :"+((HealingSpell) ((Wizard)(tournament.getSecondTask().getCurrentChamp())).getSpells().get(1)).getHealingAmount()+"<br>"+
					"Cooldown : "+((Wizard)(tournament.getSecondTask().getCurrentChamp())).getSpells().get(1).getCoolDown()+"<html>");

			task1view.spell_1.setToolTipText("<html>"+"Name : "+((Wizard)(tournament.getSecondTask().getCurrentChamp())).getSpells().get(0).getName()+"<br>"+
					"Cost : "+((Wizard)(tournament.getSecondTask().getCurrentChamp())).getSpells().get(0).getCost()+"<br>"+"Spell Damage : "
					+((DamagingSpell) ((Wizard)(tournament.getSecondTask().getCurrentChamp())).getSpells().get(0)).getDamageAmount()+"<br>"+
					"Cooldown : "+((Wizard)(tournament.getSecondTask().getCurrentChamp())).getSpells().get(0).getCoolDown()+"<html>");
			

			task1view.spell_3.setToolTipText("<html>"+"Name : "+((Wizard)(tournament.getSecondTask().getCurrentChamp())).getSpells().get(2).getName()+"<br>"+
					"Cost : "+((Wizard)(tournament.getSecondTask().getCurrentChamp())).getSpells().get(2).getCost()+"<br>"+
					"Range Amount :"+((RelocatingSpell) ((Wizard)(tournament.getSecondTask().getCurrentChamp())).getSpells().get(2)).getRange()+"<br>"+
					"Cooldown : "+((Wizard)(tournament.getSecondTask().getCurrentChamp())).getSpells().get(2).getCoolDown()+"<html>");
			task1view.fighter.setToolTipText("Allowed Moves : "+tournament.getSecondTask().getAllowedMoves());
			task1view.trait.setToolTipText("Cooldown : "+((Wizard)(tournament.getSecondTask().getCurrentChamp())).getTraitCooldown());
		UpdateHPAndMP(((Wizard)(tournament.getSecondTask().getCurrentChamp())));
		UpdatePotions(((Wizard)(tournament.getSecondTask().getCurrentChamp())));
		task1view.txt.setText(((Wizard)(tournament.getSecondTask().getCurrentChamp())).getName());
		task1view.panel.removeAll();
		task1view.panel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		task1view.fighter.setIcon(((Wizard)tournament.getSecondTask().getCurrentChamp()).getImageicon());
		for (int i = 0;i < task1view.buttons.length;i++) {
			for(int j = 0;j < 10; j++) {
			if(tournament.getSecondTask().getMap()[i][j] instanceof ObstacleCell){
				task1view.buttons[i][j] = new JButton(new ImageIcon("ice.jpg"));
				task1view.buttons[i][j].add(new JLabel(new ImageIcon("ew3a.png")));
				task1view.panel.add(task1view.buttons[i][j]);
				}
			else
			if(tournament.getSecondTask().getMap()[i][j] instanceof ChampionCell){
				Wizard champ = (Wizard) ((ChampionCell)(tournament.getSecondTask().getMap()[i][j])).getChamp();
				task1view.buttons[i][j] = new JButton(new ImageIcon("ice.jpg"));
				task1view.buttons[i][j].add(new JLabel(new ImageIcon("smaller_"+champ.getImageicon().getDescription())));
				task1view.panel.add(task1view.buttons[i][j]);
				
			}
			else
				if(tournament.getSecondTask().getMap()[i][j] instanceof CupCell){
					task1view.buttons[i][j] = new JButton(new ImageIcon("ice.jpg"));
					task1view.panel.add(task1view.buttons[i][j]);
				}
				else
					if(tournament.getSecondTask().getMap()[i][j] instanceof CollectibleCell){
						task1view.buttons[i][j] = new JButton(new ImageIcon("ice.jpg"));
						task1view.panel.add(task1view.buttons[i][j]);
					}
					else
						if(tournament.getSecondTask().getMap()[i][j] instanceof EmptyCell){
							task1view.buttons[i][j] = new JButton(new ImageIcon("ice.jpg"));
							task1view.panel.add(task1view.buttons[i][j]);
						}
						else
							if(tournament.getSecondTask().getMap()[i][j] instanceof WallCell){
								task1view.buttons[i][j] = new JButton(new ImageIcon("try.png"));
								//task1view.buttons[i][j].add(new JLabel(new ImageIcon("wall.png")));
								task1view.panel.add(task1view.buttons[i][j]);
							}
							else if (tournament.getSecondTask().getMap()[i][j] instanceof TreasureCell){
								task1view.buttons[i][j] = new JButton(new ImageIcon("ice.jpg"));
								task1view.buttons[i][j].add(new JLabel(new ImageIcon("mabrokk.png")));
								task1view.panel.add(task1view.buttons[i][j]);
							}
							else
							{
								task1view.buttons[i][j] = new JButton(new ImageIcon("ice.jpg"));
								task1view.panel.add(task1view.buttons[i][j]);
							}
			if(tournament.getSecondTask().getChampions().size() == 0) {
			      if(tournament.getSecondTask().getWinners().size()== 0)
			    	  task1view.setVisible(false);
			      secondTask =true;
			      firstTask = true;
			     
			} 
			
			task1view.buttons[i][j].setContentAreaFilled(false);
			task1view.buttons[i][j].setBorderPainted(false);
			} 
			}
		if(((Wizard)(tournament.getSecondTask().getCurrentChamp())).getTraitCooldown() == 0)
			task1view.trait.setEnabled(true);
		else {
			task1view.trait.setEnabled(false);
		}
		UpdateSpells(((Wizard)(tournament.getSecondTask().getCurrentChamp())));
		task1view.add(task1view.panel);

		task1view.setVisible(true); }
		else {
		
			task1view.picLabel.setIcon(new ImageIcon(new ImageIcon("task3back.jpg").getImage().getScaledInstance(1366, 768, java.awt.Image.SCALE_DEFAULT)));	
			task1view.setContentPane(task1view.picLabel);
			updatemap3(tournament);
		}
		
	}
	public void updatemap3(Tournament a)throws IOException {
		task1view.picLabel.setIcon(new ImageIcon(new ImageIcon("task3back.jpg").getImage().getScaledInstance(1366, 768, java.awt.Image.SCALE_DEFAULT)));	
		firstTask=true;
		secondTask=true;
		if((Wizard)(tournament.getThirdTask().getCurrentChamp()) instanceof HufflepuffWizard)
			task1view.pic.setIcon(new ImageIcon(new ImageIcon("hufflepuff_badge.png").getImage().getScaledInstance(65, 75, java.awt.Image.SCALE_DEFAULT)));
		else
			if((Wizard)(tournament.getThirdTask().getCurrentChamp()) instanceof SlytherinWizard)
				task1view.pic.setIcon(new ImageIcon(new ImageIcon("Slytherin_Badge.png").getImage().getScaledInstance(65, 75, java.awt.Image.SCALE_DEFAULT)));
			else
				if((Wizard)(tournament.getThirdTask().getCurrentChamp()) instanceof GryffindorWizard)
					task1view.pic.setIcon(new ImageIcon(new ImageIcon("gryffindor_Badge.png").getImage().getScaledInstance(65, 75, java.awt.Image.SCALE_DEFAULT)));
				else
					if((Wizard)(tournament.getThirdTask().getCurrentChamp()) instanceof RavenclawWizard)
						task1view.pic.setIcon(new ImageIcon(new ImageIcon("Ravenclaw_badge.png").getImage().getScaledInstance(65, 75, java.awt.Image.SCALE_DEFAULT)));
		
		task1view.spell_2.setToolTipText("<html>"+"Name : "+((Wizard)(tournament.getThirdTask().getCurrentChamp())).getSpells().get(1).getName()+"<br>"+
				"Cost : "+((Wizard)(tournament.getThirdTask().getCurrentChamp())).getSpells().get(1).getCost()+"<br>"+
				"Healing Amount :"+((HealingSpell) ((Wizard)(tournament.getThirdTask().getCurrentChamp())).getSpells().get(1)).getHealingAmount()+"<br>"+
				"Cooldown : "+((Wizard)(tournament.getThirdTask().getCurrentChamp())).getSpells().get(1).getCoolDown()+"<html>");
		task1view.spell_1.setToolTipText("<html>"+"Name : "+((Wizard)(tournament.getThirdTask().getCurrentChamp())).getSpells().get(0).getName()+"<br>"+
				"Cost : "+((Wizard)(tournament.getThirdTask().getCurrentChamp())).getSpells().get(0).getCost()+"<br>"+"Spell Damage : "
				+((DamagingSpell) ((Wizard)(tournament.getThirdTask().getCurrentChamp())).getSpells().get(0)).getDamageAmount()+"<br>"+
				"Cooldown : "+((Wizard)(tournament.getThirdTask().getCurrentChamp())).getSpells().get(0).getCoolDown()+"<html>");
		

		task1view.spell_3.setToolTipText("<html>"+"Name : "+((Wizard)(tournament.getThirdTask().getCurrentChamp())).getSpells().get(2).getName()+"<br>"+
				"Cost : "+((Wizard)(tournament.getThirdTask().getCurrentChamp())).getSpells().get(2).getCost()+"<br>"+
				"Range Amount :"+((RelocatingSpell) ((Wizard)(tournament.getThirdTask().getCurrentChamp())).getSpells().get(2)).getRange()+"<br>"+
				"Cooldown : "+((Wizard)(tournament.getThirdTask().getCurrentChamp())).getSpells().get(2).getCoolDown()+"<html>");
		task1view.fighter.setToolTipText("Allowed Moves : "+tournament.getThirdTask().getAllowedMoves());
		task1view.trait.setToolTipText("Cooldown : "+((Wizard)(tournament.getThirdTask().getCurrentChamp())).getTraitCooldown());
		UpdateHPAndMP(((Wizard)(tournament.getThirdTask().getCurrentChamp())));
		UpdatePotions(((Wizard)(tournament.getThirdTask().getCurrentChamp())));
		task1view.txt.setText(((Wizard)(tournament.getThirdTask().getCurrentChamp())).getName());
		task1view.panel.removeAll();
		task1view.fighter.setIcon(((Wizard)tournament.getThirdTask().getCurrentChamp()).getImageicon());
		for (int i = 0;i < task1view.buttons.length;i++) {
			for(int j = 0;j < 10; j++) {
			if(tournament.getThirdTask().getMap()[i][j] instanceof ObstacleCell){
				task1view.buttons[i][j] = new JButton(new ImageIcon("tryy.png"));
				task1view.buttons[i][j].add(new JLabel(new ImageIcon("obss_2.gif")));
				//task1view.buttons[i][j].add(new JLabel(new ImageIcon("ew3a.png")));
				task1view.panel.add(task1view.buttons[i][j]);
				}
			else
			if(tournament.getThirdTask().getMap()[i][j] instanceof ChampionCell){
				Wizard champ = (Wizard) ((ChampionCell)(tournament.getThirdTask().getMap()[i][j])).getChamp();
				task1view.buttons[i][j] = new JButton(new ImageIcon("tryy.png"));
				task1view.buttons[i][j].add(new JLabel(new ImageIcon("smaller_"+champ.getImageicon().getDescription())));
				task1view.panel.add(task1view.buttons[i][j]);
				
			}
			else
				if(tournament.getThirdTask().getMap()[i][j] instanceof CupCell){
					task1view.buttons[i][j] = new JButton(new ImageIcon("tryy.png"));
					task1view.panel.add(task1view.buttons[i][j]);
				}
				else
					if(tournament.getThirdTask().getMap()[i][j] instanceof CollectibleCell){
						task1view.buttons[i][j] = new JButton(new ImageIcon("tryy.png"));
						task1view.panel.add(task1view.buttons[i][j]);
					}
					else
						if(tournament.getThirdTask().getMap()[i][j] instanceof EmptyCell){
							task1view.buttons[i][j] = new JButton(new ImageIcon("tryy.png"));
							task1view.panel.add(task1view.buttons[i][j]);
						}
						else
							if(tournament.getThirdTask().getMap()[i][j] instanceof WallCell){
								task1view.buttons[i][j] = new JButton(new ImageIcon("walls.png"));
								//task1view.buttons[i][j].add(new JLabel(new ImageIcon("wall.png")));
								task1view.panel.add(task1view.buttons[i][j]);
							}
							else if (tournament.getThirdTask().getMap()[i][j] instanceof TreasureCell){
								task1view.buttons[i][j] = new JButton(new ImageIcon("ice.jpg"));
								task1view.buttons[i][j].add(new JLabel(new ImageIcon("mabrok.png")));
								task1view.panel.add(task1view.buttons[i][j]);
							}
							else
							{
								task1view.buttons[i][j] = new JButton(new ImageIcon("tryy.png"));
								task1view.panel.add(task1view.buttons[i][j]);
							}
			
			task1view.buttons[i][j].setContentAreaFilled(false);
			task1view.buttons[i][j].setBorderPainted(false);
			}
			}
		if(((Wizard)(tournament.getThirdTask().getCurrentChamp())).getTraitCooldown() == 0 && !(tournament.getThirdTask().isTraitActivated()))
			task1view.trait.setEnabled(true);
			else {
				task1view.trait.setEnabled(false);
			}
		UpdateSpells(((Wizard)(tournament.getThirdTask().getCurrentChamp())));
		task1view.panel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		task1view.add(task1view.panel);
		
		task1view.setVisible(true);
		if(tournament.getThirdTask().getChampions().size() == 0) {
		      if(tournament.getSecondTask().getWinners().size()== 0)
		    	  task1view.setVisible(false);
		      secondTask =true;
		      firstTask = true;
		    
		} 
		
	}
	private void UpdatePotions(Wizard wizard) {
		if( wizard.getInventory().size() == 0)
			task1view.inv.setEnabled(false);
		else		
			task1view.inv.setEnabled(true);
		task1view.MP.setText(wizard.getIp()+"/"+wizard.getDefaultIp());
		double temp1 = (((double)wizard.getIp())/wizard.getDefaultIp())*100;
		if (temp1 == 100)
			task1view.mp.setIcon(new ImageIcon("MP bars/mp_100.png")); 
		if (temp1 <100 && temp1 >=90)
			task1view.mp.setIcon(new ImageIcon("MP bars/mp_90.png"));
		if (temp1 <90 && temp1 >=70)
			task1view.mp.setIcon(new ImageIcon("MP bars/mp_70.png"));
		if (temp1 <70 && temp1 >=50)
			task1view.mp.setIcon(new ImageIcon("MP bars/mp_50.png"));
		if (temp1 <50 && temp1 >=40)
			task1view.mp.setIcon(new ImageIcon("MP bars/mp_40.png"));
		if (temp1 <40 && temp1 >=20)
			task1view.mp.setIcon(new ImageIcon("MP bars/mp_20.png"));
		if (temp1 <20)
			task1view.mp.setIcon(new ImageIcon("MP bars/mp_0.png"));
		task1view.potion.removeAllItems();
		for(int i = 0 ;i < wizard.getInventory().size();i++) {
			task1view.potion.addItem(wizard.getInventory().get(i).getName());
			
		}
		
		
	}

	public void UpdateHPAndMP(Wizard x) {
		
		task1view.hp.setText(x.getHp()+"/"+x.getDefaultHp());
		double temp = (((double)x.getHp())/x.getDefaultHp())*100;
		task1view.hp_bar.setText("YOUR HEALTH IS "+temp);
		if (temp == 100)
			task1view.mp.setIcon(new ImageIcon("HP bars/health_100.png")); 
		else
		if (temp <100 && temp >=90)
			task1view.hp_bar.setIcon(new ImageIcon("HP bars/health_90.png"));
		else
		if (temp <90 && temp >=70)
			task1view.hp_bar.setIcon(new ImageIcon("HP bars/health_70.png"));
		else
		if (temp <70 && temp >=50)
			task1view.hp_bar.setIcon(new ImageIcon("HP bars/health_50.png"));
		else
		if (temp <50 && temp >=40)
			task1view.hp_bar.setIcon(new ImageIcon("HP bars/health_40.png"));
		else
		if (temp <40 && temp >=20)
			task1view.hp_bar.setIcon(new ImageIcon("HP bars/health_20.png"));
		else
		if (temp <20)
			task1view.hp_bar.setIcon(new ImageIcon("HP bars/health_0.png"));
		
		
	
		
	}
	public void UpdateSpells(Wizard x) {
		Spell spell1 = x.getSpells().get(0);
		Spell spell2 = x.getSpells().get(1);
		Spell spell3 = x.getSpells().get(2);
		if(spell1 .getCoolDown() != 0)
			task1view.spell_1.setEnabled(false);
		else
			task1view.spell_1.setEnabled(true);
		
		if(spell2 .getCoolDown() != 0)
			task1view.spell_2.setEnabled(false);
		else
			task1view.spell_2.setEnabled(true);
		
		if(spell3 .getCoolDown() != 0)
			task1view.spell_3.setEnabled(false);
		else
			task1view.spell_3.setEnabled(true);
	
	}
	
	
	
}
