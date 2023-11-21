package DB_Project;

public class Main {

	public static void main(String[] args) {
		try {
			Login window = new Login();
			window.frame.setVisible(true);
			Register window2 = new Register();
			window2.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
