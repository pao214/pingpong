import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class SinglePlayer extends GameActivity {
	
	public SinglePlayer() {
		super();
		setLayout(null);
		

		JLabel txtCpuPlayers = new JLabel("CPU Players");
		
		JLabel txtLevel = new JLabel("LEVEL");
		
		// set layout parameters
		txtCpuPlayers.setBounds(5, 63, 116, 22);
		add(txtCpuPlayers);
		
		JRadioButton one = new JRadioButton("1");
		one.setSelected(true);
		one.setBounds(129, 62, 52, 25);
		one.setMnemonic(KeyEvent.VK_B);
	    
	    
		JRadioButton two = new JRadioButton("3");
		two.setBounds(185, 62, 46, 25);
		two.setMnemonic(KeyEvent.VK_C);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(one);
	    group.add(two);
	    
		//
		one.addItemListener(new ItemListener(){
             @Override
			public void itemStateChanged(ItemEvent e) {
				Constants.numPlayers=1;
			}
		});
		
		//
		two.addItemListener(new ItemListener(){
              @Override
			public void itemStateChanged(ItemEvent e) {
            	  Constants.numPlayers=3;
			}
		});
		
		add(one);
		add(two);

		///set layout parameters
		
		
		//
		
	    
	    //
	    
	    
		
	    //
	    txtLevel.setText("LEVEL");
	    txtLevel.setBounds(5, 141, 116, 22);
	    add(txtLevel);
	    
	    //
	    JRadioButton easy = new JRadioButton("Easy");
	    easy.setSelected(true);
	    easy.setBounds(129, 140, 64, 25);
	    easy.setMnemonic(KeyEvent.VK_B);
	    add(easy);
	    
	    //
	    JRadioButton medium = new JRadioButton("Medium");
	    medium.setBounds(197, 140, 73, 25);
	    medium.setMnemonic(KeyEvent.VK_C);
	    add(medium);
	    
	    //
	    JRadioButton hard = new JRadioButton("Hard");
	    hard.setBounds(281, 140, 64, 25);
	    hard.setMnemonic(KeyEvent.VK_D);
	    add(hard);
	    
	    //
	    ButtonGroup level = new ButtonGroup();
	    level.add(easy);
	    level.add(medium);
		level.add(hard);
		
		
		easy.addItemListener(new ItemListener(){
            @Override
			public void itemStateChanged(ItemEvent e) {
				Constants.level = Constants.easy;
			}
			
		});
		
		medium.addItemListener(new ItemListener(){
            @Override
			public void itemStateChanged(ItemEvent e) {
            	Constants.level = Constants.medium;
			}
			
		});
		
		hard.addItemListener(new ItemListener(){
            @Override
			public void itemStateChanged(ItemEvent e) {
            	Constants.level = Constants.hard;
			}
			
		});
		
		JButton start = new JButton("START");
		start.setBounds(129, 204, 97, 25);
		add(start);
		
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				startActivity(new Intent(PlayBoard.class));
			}
		});
		
		JButton back = new JButton("BACK");
		back.setBounds(303, 204, 97, 25);
		add(back);
		
		back.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent arg0) {
				super.mousePressed(arg0);
				startActivity(new Intent(Menu.class));
			}
		});
	}
}
