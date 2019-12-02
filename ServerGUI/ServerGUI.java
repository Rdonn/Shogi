package ServerGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Communication.ServerCommunication.GameServer;

public class ServerGUI extends JFrame{
	private JLabel status; 
	private String[] labels = {"Port #", "Timeout"};
	private JTextField[] textFields = new JTextField[labels.length];
	private JTextArea log;
	private JButton listen, 
					close, 
					stop, 
					quit; 
	private GameServer gameServer; 
	
	
	public ServerGUI(String title) {
		
		this.gameServer = new GameServer(); 
		this.gameServer.setServerGUI(this);
		
		int dimX = 500, dimY = 700; 
		setSize(new Dimension(dimX, dimY));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(title);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		
		//building input params panel
		JPanel oneLinersContainer = new JPanel(); 
		oneLinersContainer.add(this.oneLinersBuilder()); 
		this.add(oneLinersContainer); 
		
		//building the server log panel
		JPanel serverLogContainer = new JPanel(); 
		serverLogContainer.add(this.serverLogBuilder()); 
		this.add(serverLogContainer); 
		
		//building the button panel
		JPanel buttonPanelContainer = new JPanel(); 
		buttonPanelContainer.add(this.buttonBuilder()); 
		this.add(buttonPanelContainer); 
		
		this.pack();
		this.setVisible(true);
		
	}

	//build the server log panel
	public JPanel serverLogBuilder() {
		JPanel serverLogPanel = new JPanel(); 
		serverLogPanel.setLayout(new BoxLayout(serverLogPanel, BoxLayout.PAGE_AXIS));
		
		JPanel serverLogLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		serverLogLabelPanel.add(new JLabel("Server Log Below")); 
		serverLogPanel.add(serverLogLabelPanel); 
		
		JPanel logTextAreaPanel = new JPanel(new FlowLayout()); 
		this.log = new JTextArea(10, 60);
		this.log.setBorder(BorderFactory.createLineBorder(Color.black));
		JScrollPane logScrollPane = new JScrollPane(this.log); 
		logTextAreaPanel.add(logScrollPane); 
		serverLogPanel.add(logTextAreaPanel); 
		
		return serverLogPanel; 
		
	}
	
	//builds the button panel
		public JPanel buttonBuilder() {
			JPanel buttonPanel = new JPanel(new GridLayout(1, 0, 3, 3)); 
			
			//each button will be given a reference to the GUI
			
			//create listen button
			this.listen = new JButton("Listen"); 
			this.listen.addActionListener(new ListenButtonHandler(this));
			//create close button
			this.close = new JButton("Close");
			this.close.addActionListener(new CloseHandler(this));
			//create stop button
			this.stop = new JButton("Stop"); 
			this.stop.addActionListener(new StopButtonHandler(this));
			//create quit button
			this.quit = new JButton("Quit"); 
			this.quit.addActionListener(new QuitHandler(this));
			
			buttonPanel.add(this.listen); 
			buttonPanel.add(this.close); 
			buttonPanel.add(this.stop); 
			buttonPanel.add(this.quit); 
			
			return buttonPanel; 
		}
	
	//build the text fields panel
	public JPanel oneLinersBuilder() {
		JPanel oneLinersPanel = new JPanel(new GridLayout(0, 2)); 
		
		JPanel plainStatusTextPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		plainStatusTextPanel.add(new JLabel("Status: ")); 
		oneLinersPanel.add(plainStatusTextPanel); 
		
		JPanel statusTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		this.status = new JLabel("Not Connected"); 
		this.status.setForeground(Color.red);
		statusTextPanel.add(this.status); 
		oneLinersPanel.add(this.status); 
		
		JPanel portNumberPlainPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		portNumberPlainPanel.add(new JLabel(this.labels[0])); 
		oneLinersPanel.add(portNumberPlainPanel); 
		
		JPanel portNumberTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		this.textFields[0] = new JTextField("", 13); 
		this.textFields[0].setText("8300");
		portNumberTextFieldPanel.add(this.textFields[0]); 
		oneLinersPanel.add(portNumberTextFieldPanel); 
		
		JPanel timeoutPlainLabelPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
		timeoutPlainLabelPanel.add(new JLabel(this.labels[1])); 
		oneLinersPanel.add(timeoutPlainLabelPanel); 
		
		JPanel timeoutTextAreaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.textFields[1] = new JTextField("", 5); 
		this.textFields[1].setText("500");
		timeoutTextAreaPanel.add(this.textFields[1]); 
		oneLinersPanel.add(timeoutTextAreaPanel); 
		
		return oneLinersPanel; 
		
	}
	
	/***
	 * Implementing the handlers starting here
	 * **/
	
	//handles the listen button
		public class  ListenButtonHandler implements ActionListener {
			private ServerGUI serverGUI; 
			public ListenButtonHandler(ServerGUI serverGUI) {
				this.serverGUI = serverGUI; 
			}
			private boolean checkIfDataReady(String port, String timeout) {
				return !(port.length() == 0 || timeout.length() == 0); 
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				//checking to see if the fields are instantiated
				if (checkIfDataReady(this.serverGUI.getTextFields()[0].getText(), 
									this.serverGUI.getTextFields()[1].getText())) {
					try {
						this.serverGUI.getGameServer()
						.setPort(Integer.parseInt(this.serverGUI.getTextFields()[0].getText()));
						this.serverGUI.getGameServer()
						.setTimeout(Integer.parseInt(this.serverGUI.getTextFields()[1].getText()));
						this.serverGUI.getGameServer().listen();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						this.serverGUI.getLog().append("Error starting server\n");
						this.serverGUI.getLog().append(e1.getMessage() + "\n");
					}
				}
				else {
					this.serverGUI.getLog().append("Port Number/Timeout not entered before pressing listen\n");
				}
				
			}
			
		}
		

		//handles the closing operation
		public class CloseHandler implements ActionListener{
			private ServerGUI serverGUI; 
			public CloseHandler(ServerGUI serverGUI) {
				// TODO Auto-generated constructor stub
				this.serverGUI = serverGUI; 
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				if (this.serverGUI.getGameServer().isListening()) {
					try {
						this.serverGUI.getGameServer().close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						this.serverGUI.getLog().append(e1.getMessage());
					}
				}
				else if(!this.serverGUI.getGameServer().isListening()) {
					this.serverGUI.getLog().append("Server not currently started\n");
				}
				
			}
			
		}
		
		//handles the stop button action
		public class StopButtonHandler implements ActionListener{
			private ServerGUI serverGUI;  
			public StopButtonHandler(ServerGUI serverGUI) {
				// TODO Auto-generated constructor stub
				this.serverGUI = serverGUI; 
				
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				if (this.serverGUI.getGameServer().isListening()) {
					this.serverGUI.getGameServer().stopListening();
				}
				else if(!this.serverGUI.getGameServer().isListening()) {
					this.serverGUI.getLog().append("Server not currently started\n");
				}
			}
			
		}
	

		//handles the quit button
		public class QuitHandler implements ActionListener{
			private ServerGUI serverGUI; 
			
			public QuitHandler(ServerGUI serverGUI) {
				this.serverGUI = serverGUI; 
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (this.serverGUI.getGameServer().isListening()) {
					this.serverGUI.getGameServer().stopListening();
				}
				this.serverGUI.dispose();
			}
			
		}
	public JLabel getStatus() {
		return status;
	}
	public JTextField[] getTextFields() {
		return textFields;
	}
	public GameServer getGameServer() {
		return gameServer;
	}
	public JTextArea getLog() {
		return log;
	}
	public static void main(String[] args) {
		try {
			new ServerGUI("Shogi Server");
		} catch (Exception e) {
		}
	}
	
}