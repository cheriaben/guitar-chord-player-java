import java.awt.*;
import javax.swing.*;

public class About extends JFrame
{
	public About()
	{
		setTitle("About");
		setSize(250,350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		String message1 = "    Copyright © 2013 Guitar Chord Player               ";
		String message2 = "                        JAVA PROJECT                    ";
		String message6 = "              Universitas Tarumanagara		   ";
		String message3 = "       Lecturer: Janson Hendry Li, S.Kom";
		String message4 = "                                    By:";
		String message5 = "           Cheria Benedicta (535120012)";
		String message8 = "       Sound from: www.sampleswap.org";
		String message9 = " ";
		String message10 = "Shortcuts:";
		String message11 = "Press alt+P to play the chord";
		String message12 = "Press alt+R to reset";
		String message13 = "Press alt+A to open About section";
		String message14 = "Thank you for using Guitar Chord Player!";
		
		
		JTextArea txt_help1 = new JTextArea(message1);
		JTextArea txt_help2 = new JTextArea(message2);
		JTextArea txt_help6 = new JTextArea(message6);
		JTextArea txt_help3 = new JTextArea(message3);
		JTextArea txt_help4 = new JTextArea(message4);
		JTextArea txt_help5 = new JTextArea(message5);
		JTextArea txt_help8 = new JTextArea(message8);
		JTextArea txt_help9 = new JTextArea(message9);
		JTextArea txt_help10 = new JTextArea(message10);
		JTextArea txt_help11 = new JTextArea(message11);
		JTextArea txt_help12 = new JTextArea(message12);
		JTextArea txt_help13 = new JTextArea(message13);
		JTextArea txt_help14 = new JTextArea(message14);
		
		
		txt_help1.setEditable(false);
		txt_help2.setEditable(false);
		txt_help6.setEditable(false);
		txt_help3.setEditable(false);
		txt_help4.setEditable(false);
		txt_help5.setEditable(false);
		txt_help8.setEditable(false);
		txt_help9.setEditable(false);
		txt_help10.setEditable(false);
		txt_help11.setEditable(false);
		txt_help12.setEditable(false);
		txt_help13.setEditable(false);
		txt_help14.setEditable(false);
		
		
		JPanel panel = new JPanel(new GridLayout(14,1));
		panel.add(txt_help1);
		panel.add(txt_help2);
		panel.add(txt_help6);
		panel.add(txt_help3);
		panel.add(txt_help4);
		panel.add(txt_help5);
		panel.add(txt_help8);
		panel.add(txt_help9);
		panel.add(txt_help10);
		panel.add(txt_help11);
		panel.add(txt_help12);
		panel.add(txt_help13);
		panel.add(txt_help14);
		
		add(panel);
		setVisible(true);
	}
}