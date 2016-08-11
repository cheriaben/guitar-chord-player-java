import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;


public class GuitarChordPlayer extends JFrame implements ActionListener{
	
	public ImageIcon playIcon = new ImageIcon("play.png");
	public ImageIcon aboutIcon = new ImageIcon("about.png");
	LineBorder lineBorder = new LineBorder(Color.BLACK, 1);
	String filewav;
	String chordtype = "Major";
	AudioFormat audioFormat;
  	AudioInputStream audioInputStream;
  	SourceDataLine sourceDataLine;

	String[] chrdType = {"Major", "Minor", "7"}; 
	JLabel lbl_chord = new JLabel("Chord: ");
	JTextField txt_chord = new JTextField();
	
	JButton btn_play = new JButton();
	JButton btn_clr = new JButton("Reset");
	JButton btn_abt = new JButton();
	JComboBox chordList = new JComboBox(chrdType);
	
	public GuitarChordPlayer(){
		
		setTitle("Guitar Chord Player");
		setSize(300,110);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		btn_play.addActionListener(this);
		btn_play.setIcon(playIcon);
		btn_play.setMnemonic(KeyEvent.VK_P);
		btn_play.setToolTipText("Play the chord");
		btn_play.setPreferredSize(new Dimension(25,25));
		
		btn_clr.addActionListener(this);
		btn_clr.setToolTipText("Clear the field");
		btn_clr.setMnemonic(KeyEvent.VK_R);
		btn_clr.setFont(new Font("Dialog",Font.BOLD, 10));
		btn_clr.setPreferredSize(new Dimension(65,25));
		
		btn_abt.addActionListener(this);
		btn_abt.setIcon(aboutIcon);
		btn_abt.setMnemonic(KeyEvent.VK_A);
		btn_abt.setToolTipText("About this application");
		btn_abt.setPreferredSize(new Dimension(16,16));
		
		chordList.setFont(new Font("Dialog",Font.PLAIN, 12));
		chordList.setPreferredSize(new Dimension(65,25));
		chordList.addItemListener( new ItemListener() {
    	
    	public void itemStateChanged (ItemEvent e) {
    			if (e.getStateChange() == ItemEvent.SELECTED){
    				chordtype = (String) chordList.getSelectedItem();
    				}
    			}
    		} 
        	);
        	
		txt_chord.setBorder(lineBorder);
		txt_chord.setFont(new Font("Dialog",Font.BOLD, 12));
		txt_chord.setForeground(Color.RED);
		txt_chord.setPreferredSize(new Dimension(30,25));
        		
		JPanel pnl_input = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
		pnl_input.add(lbl_chord);
		pnl_input.add(txt_chord);
		pnl_input.add(chordList);
		pnl_input.add(btn_abt);
		
		JPanel pnl_button = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
		pnl_button.add(btn_play);
		pnl_button.add(btn_clr);
		
		
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.add(pnl_input);
		panel.add(pnl_button);

		add(panel);
		
		setVisible(true);
	}

	public void playAudio(){
    	try{
      	File soundFile = new File(filewav);
      	audioInputStream = AudioSystem.getAudioInputStream(soundFile);
      	audioFormat = audioInputStream.getFormat();
      	System.out.println(audioFormat);

      	DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

      	sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
      	
      	new PlayThread().start();
      	
    	}
    	catch (Exception e){
      e.printStackTrace();
     JOptionPane.showMessageDialog(this,"There's no such chord!","Error",JOptionPane.ERROR_MESSAGE);
    }//end catch
  }//end playAudio


//Inner class to play back the data from the
// audio file.
class PlayThread extends Thread{
  byte tempBuffer[] = new byte[10000];

  public void run(){
    try{
      sourceDataLine.open(audioFormat);
      sourceDataLine.start();

      int cnt;
      //Keep looping until the input read method
      // returns -1 for empty stream
      while((cnt = audioInputStream.read(
           tempBuffer,0,tempBuffer.length))!=-1){
        if(cnt > 0){
        btn_play.setEnabled(false);
          //Write data to the internal buffer of
          // the data line where it will be
          // delivered to the speaker.
          sourceDataLine.write(tempBuffer, 0, cnt);
        }//end if
      }//end while
      //Block and wait for internal buffer of the
      // data line to empty.
      sourceDataLine.drain();
      sourceDataLine.close();
      btn_play.setEnabled(true);

      //Prepare to playback another file
          }catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }//end catch
  }//end run
}//end inner class PlayThread	
	
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		String chord = txt_chord.getText();
		filewav = chord+chordtype+".wav";
		if(obj == btn_play){
			playAudio();
		}
		else if(obj == btn_clr){
			txt_chord.setText("");
			chordList.setSelectedItem("Major");
		}
		else if(obj == btn_abt)
		{
			About abt = new About();
		}
	}
	
	public static void main(String[] args){
		GuitarChordPlayer gchplyr = new GuitarChordPlayer();
	}
}
