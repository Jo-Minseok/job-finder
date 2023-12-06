package DB_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Resume_Apply {

	private JFrame frame;

	public Resume_Apply() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("지원");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
