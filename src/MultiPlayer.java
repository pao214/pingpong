import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class MultiPlayer extends GameActivity {

	/**
	 * Create the panel.
	 */
	public MultiPlayer() {
		setLayout(null);
			
		JButton btnHost = new JButton("Host");
		btnHost.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				startActivity(new Intent(Host.class));
			}
		});
		btnHost.setBounds(58, 130, 89, 23);
		add(btnHost);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		btnConnect.setBounds(298, 130, 89, 23);
		add(btnConnect);
		
	}
}
