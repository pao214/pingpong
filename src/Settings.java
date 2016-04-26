import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Settings extends GameActivity {

	/**
	 * Create the panel.
	 */
	public int Theme=1;
	public int Position=0;
	public int Volume=0;
	public int MulCompPls=3;
	
	public Settings() {
		setLayout(null);
		
		JComboBox<String> gameTheme = new JComboBox<String>();
		gameTheme.setModel(
				new DefaultComboBoxModel<String>(
						new String[] {"Theme1", "Theme2", "Theme3"}));
		gameTheme.setBounds(99, 72, 128, 22);
		add(gameTheme);
		
		JComboBox<String> gameSide = new JComboBox<String>();
		gameSide.setModel(
				new DefaultComboBoxModel<String>(new String[] {"BOTTOM", "LEFT", "UP", "RIGHT"}));
		gameSide.setBounds(99, 107, 128, 22);
		add(gameSide);
		
//		JLabel lblDisplay = DefaultComponentFactory.getInstance().createTitle("DISPLAY");
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
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(
				new DefaultComboBoxModel<String>(new String[] {"ON", "OFF"}));
		comboBox_2.setBounds(99, 197, 128, 22);
		add(comboBox_2);
				
		JButton back = new JButton("BACK");
		back.setBounds(188, 284, 97, 25);
		add(back);
		
		back.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				startActivity(new Intent(Menu.class));
			}
		});
	}
}
