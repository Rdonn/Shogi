package GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountController implements ActionListener{

	private CreateAccountPanel createAccountPanelReference; 
	public CreateAccountController(CreateAccountPanel createAccountPanel) {
		// TODO Auto-generated constructor stub
		this.createAccountPanelReference = createAccountPanel; 
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(arg0.getActionCommand());
		
	}

}
