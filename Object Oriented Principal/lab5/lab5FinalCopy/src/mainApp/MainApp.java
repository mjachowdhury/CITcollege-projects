package mainApp;

import view.UserView;
/*
 * This is the main App where application will start to run.
 * @author Mohammed
 * @version 1.0
 * @since 2017/11/25
 */
public class MainApp {
	public static void main(String[] args) {
		UserView userView = new UserView();
		try {
			userView.userMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
