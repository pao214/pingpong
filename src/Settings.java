import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Settings extends GameActivity {

	/**
	 * Create the panel.
	 */
	
	public Settings() {
		setLayout(null);
		
		final JComboBox<String> gameTheme = new JComboBox<String>();
		gameTheme.setModel(
				new DefaultComboBoxModel<String>(
						new String[] {"Theme1", "Theme2", "Theme3"}));
		gameTheme.setBounds(99, 72, 128, 22);
		gameTheme.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				
				Constants.index = gameTheme.getSelectedIndex();
				switch(Constants.index){
					case Constants.theme1:
						Constants.theme = Constants.t1;
						break;
					case Constants.theme2:
						Constants.theme = Constants.t2;
						break;
					case Constants.theme3:
						Constants.theme = Constants.t3;
						break;
				}
			}
		});
		gameTheme.setSelectedIndex(Constants.index);
		add(gameTheme);
		
		final JComboBox<String> gameSide = new JComboBox<String>();
		gameSide.setModel(
				new DefaultComboBoxModel<String>(new String[] {"BOTTOM", "RIGHT", "UP", "LEFT"}));
		gameSide.setBounds(99, 107, 128, 22);
		gameSide.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				Constants.position = gameSide.getSelectedIndex();
			}
		});
		gameSide.setSelectedIndex(Constants.position);
		add(gameSide);
		
		JLabel lblDisplay = new JLabel("DISPLAY");
		lblDisplay.setFont(new Font("Stencil", Font.PLAIN, 13));
		lblDisplay.setBounds(12, 30, 104, 29);
		add(lblDisplay);
		
		JLabel lblNewLabel = new JLabel("Theme");
		lblNewLabel.setLabelFor(gameTheme);
		lblNewLabel.setBounds(22, 75, 56, 16);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Position");
		lblNewLabel_1.setLabelFor(gameSide);
		lblNewLabel_1.setBounds(22, 110, 56, 16);
		add(lblNewLabel_1);
		
//		JLabel lblSound = DefaultComponentFactory.getInstance().createTitle("SOUND");
		JLabel lblSound = new JLabel("SOUND");
		lblSound.setFont(new Font("Stencil", Font.PLAIN, 13));
		lblSound.setBounds(12, 166, 104, 29);
		add(lblSound);
		
		JLabel lblVolume = new JLabel("Volume");
		lblVolume.setBounds(22, 200, 56, 16);
		add(lblVolume);
		
		JLabel lblPlaylist = new JLabel("Playlist");
		lblPlaylist.setBounds(22, 229, 56, 16);
		add(lblPlaylist);
		
		final JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(
				new DefaultComboBoxModel<String>(new String[] {"ON", "OFF"}));
		comboBox_2.setBounds(99, 197, 128, 22);
		comboBox_2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				Constants.sound = comboBox_2.getSelectedIndex();
			}
		});
		comboBox_2.setSelectedIndex(Constants.sound);
		add(comboBox_2);
				
		JButton back = new JButton("BACK");
		back.setBounds(188, 284, 97, 25);
		add(back);
		
		back.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					FileWriter writer = new FileWriter(new File("config.txt"));
					writer.write(Constants.index + "\n"
							+ Constants.position + "\n"
							+ Constants.sound + "\n");
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				startActivity(new Intent(Menu.class));
			}
		});
	}
}
