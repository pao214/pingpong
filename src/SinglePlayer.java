import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class SinglePlayer extends GameActivity {
	
	int no_Player = 1;
	int no_ball = 1;
	int LEVEL = Constants.easy;
	
	public SinglePlayer() {
		super();
		setLayout(null);
		

		JLabel txtCpuPlayers = new JLabel("CPU Players");
		JLabel txtNoofBalls = new JLabel("No. of Balls");
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
				no_Player=1;
			}
			
		});
		
		//
		two.addItemListener(new ItemListener(){
              @Override
			public void itemStateChanged(ItemEvent e) {
				no_Player=2;
			}
			
		});
		
		add(one);
		add(two);

		///set layout parameters
		txtNoofBalls.setBounds(5, 123, 116, 22);
		add(txtNoofBalls);
		
		//
		JRadioButton ball1 = new JRadioButton("1");
		ball1.setSelected(true);
		ball1.setBounds(129, 122, 52, 25);
		ball1.setMnemonic(KeyEvent.VK_B);
		add(ball1);
		
		//
		JRadioButton ball2 = new JRadioButton("2");
		ball2.setBounds(185, 122, 46, 25);
		ball2.setMnemonic(KeyEvent.VK_C);
		add(ball2);
		
		//
		ButtonGroup ball = new ButtonGroup();
	    ball.add(ball1);
	    ball.add(ball2);
	    
	    //
	    ball1.addItemListener(new ItemListener(){
            @Override
			public void itemStateChanged(ItemEvent e) {
				no_ball=1;
			}
			
		});
	    
	    //
	    ball2.addItemListener(new ItemListener(){
            @Override
			public void itemStateChanged(ItemEvent e) {
				no_ball=2;
			}
			
		});
	    
		
	    //
	    txtLevel.setText("LEVEL");
	    txtLevel.setBounds(5, 185, 116, 22);
	    add(txtLevel);
	    
	    //
	    JRadioButton easy = new JRadioButton("Easy");
	    easy.setSelected(true);
	    easy.setBounds(129, 184, 64, 25);
	    easy.setMnemonic(KeyEvent.VK_B);
	    add(easy);
	    
	    //
	    JRadioButton medium = new JRadioButton("Medium");
	    medium.setBounds(197, 184, 73, 25);
	    medium.setMnemonic(KeyEvent.VK_C);
	    add(medium);
	    
	    //
	    JRadioButton hard = new JRadioButton("Hard");
	    hard.setBounds(281, 184, 64, 25);
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
				LEVEL=Constants.easy;
			}
			
		});
		
		medium.addItemListener(new ItemListener(){
            @Override
			public void itemStateChanged(ItemEvent e) {
				LEVEL=Constants.medium;
			}
			
		});
		
		hard.addItemListener(new ItemListener(){
            @Override
			public void itemStateChanged(ItemEvent e) {
				LEVEL =Constants.hard;
			}
			
		});
		
		JButton start = new JButton("START");
		start.setBounds(129, 248, 97, 25);
		add(start);
		
		start.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				startActivity(new Intent(Menu.class));
			}
		});
		
		JButton back = new JButton("BACK");
		back.setBounds(303, 248, 97, 25);
		add(back);
		
		back.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				startActivity(new Intent(Menu.class));
			}
		});
	}
}
