package DB_Project;


import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		try {
			Login window = new Login();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
