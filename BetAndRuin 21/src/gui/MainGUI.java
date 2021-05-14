package gui;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import businessLogic.BlFacade;
import businessLogic.BlFacadeImplementation;
import java.awt.Font;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private String Username;
	private JPanel mainPane;
	protected JLabel selectOptionLbl;
	private JButton browseQuestionsBtn;
	private JButton BifunctionalBtn;
	private JPanel localePane;
	private JRadioButton euskaraRbtn;
	private JRadioButton castellanoRbtn;
	private JRadioButton englishRbtn;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private BlFacade businessLogic;
	private JPanel panel;
	private JButton btnRegister;
	private JButton btnLogin;


	public BlFacade getBusinessLogic() {
		return businessLogic;
	}

	public void setBussinessLogic(BlFacade afi) {
		businessLogic = afi;
	}
	public String getUsername() {
		return Username;
	}



	public void setUsername(String username) {
		Username = username;
	}

	public MainGUI(BlFacade bl) {
		super();
		this.setBussinessLogic(bl);
		Username=null;
		setResizable(true);
		System.out.println(Username);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					// if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					System.out.println(
							"Error: " + e1.toString() + " , likely problems " + "with Business Logic or Data Accesse");
				}
				System.exit(1);
			}
		});
		
		this.setBounds(100, 100, 348, 309);

		this.initializeMainPane();
		this.setContentPane(mainPane);
		{
			btnInsertResults = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnInsertResults")); //$NON-NLS-1$ //$NON-NLS-2$
			btnInsertResults.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					InsertResultsGUI InsertWindow = new InsertResultsGUI();
					InsertWindow.setBusinessLogic(businessLogic);
					InsertWindow.setVisible(true);
					InsertWindow.previousFrame(thisFrame);
				}
			});
			btnInsertResults.setBounds(35, 123, 265, 38);
			mainPane.add(btnInsertResults);
		}
		
		btnInsertResults.setVisible(false);

		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		//this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initializeMainPane() {
		mainPane = new JPanel();
		mainPane.setLayout(null);
		
		businessLogic.resetLogins();
		selectOptionLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Welcome"));
		selectOptionLbl.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		selectOptionLbl.setBounds(35, 0, 265, 38);
		selectOptionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mainPane.add(selectOptionLbl);

		initializeBrowseQuestionsBtn();
		mainPane.add(browseQuestionsBtn);
		initializeCancelBetsBtn();
		mainPane.add(BifunctionalBtn);

		initializeLocalePane();
		{
			panel = new JPanel();
			panel.setBounds(0, 172, 332, 38);
			mainPane.add(panel);
			{
				MainGUI thisFrame = this;
				btnRegister = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton.text"));
				btnRegister.setBounds(0, 0, 144, 34);
				btnRegister.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						setVisible(false);
						RegisterGUI RegisterWindow = new RegisterGUI();
						RegisterWindow.setBusinessLogic(businessLogic);
						RegisterWindow.setVisible(true);
						RegisterWindow.previousFrame(thisFrame);
					}
				});
				panel.setLayout(null);
				panel.add(btnRegister);
			}
			{
				MainGUI thisFrame = this;
				btnLogin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton_1.text"));
				btnLogin.setBounds(193, 1, 139, 33);
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						setVisible(false);
						LoginGUI LoginWindow = new LoginGUI();
						LoginWindow.setBusinessLogic(businessLogic);
						LoginWindow.setVisible(true);
						LoginWindow.previousFrame(thisFrame);
					}
				});
				panel.add(btnLogin);
			}
		}
		mainPane.add(localePane);
	}

	private void initializeBrowseQuestionsBtn() {
		
		JFrame thisFrame = this;
		browseQuestionsBtn = new JButton();
		browseQuestionsBtn.setBounds(35, 45, 265, 38);
		browseQuestionsBtn.setText(ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestions"));
		browseQuestionsBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				setVisible(false);
				BrowseQuestionsGUI findQuestionsWindow = new BrowseQuestionsGUI(businessLogic);
				findQuestionsWindow.setUsername(Username);	
				findQuestionsWindow.setVisible(true);
				findQuestionsWindow.previousFrame(thisFrame);
			}
		});
	}
	
	MainGUI thisFrame = this;
	private JButton btnInsertResults;
	private void initializeCancelBetsBtn() {
		BifunctionalBtn = new JButton();
		BifunctionalBtn.setBounds(35, 84, 265, 38);
		BifunctionalBtn.setVisible(false);
		BifunctionalBtn.setText(ResourceBundle.getBundle("Etiquetas").getString("ViewProfile")); 

		
		BifunctionalBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
					setVisible(false);
					if(Username.equals("admin")) {
						CreateQuestionGUI nextGUI = new CreateQuestionGUI(businessLogic, null);
						nextGUI.previousFrame(thisFrame);
						nextGUI.setVisible(true);
						nextGUI.setUsername(Username);
					
					}else {
						ViewProfileGUI nextGUI = new ViewProfileGUI(Username);
						nextGUI.setUsername(Username);
						nextGUI.setBusinessLogic(businessLogic);
						nextGUI.previousFrame(thisFrame);
						nextGUI.setVisible(true);
					}
					
			}
		});
	}

	private void initializeLocalePane() {
		localePane = new JPanel();
		localePane.setBounds(0, 221, 322, 38);

		initializeEuskaraRbtn();
		localePane.setLayout(null);
		localePane.add(euskaraRbtn);

		initializeCastellanoRbtn();
		localePane.add(castellanoRbtn);

		initializeEnglishRbtn();
		localePane.add(englishRbtn);
	}

	private void initializeEuskaraRbtn() {
		euskaraRbtn = new JRadioButton("Euskara");
		euskaraRbtn.setBounds(25, 7, 96, 23);
		euskaraRbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("eus"));
				System.out.println("Locale: " + Locale.getDefault());
				redraw();
			}
		});
		buttonGroup.add(euskaraRbtn);
	}

	private void initializeCastellanoRbtn() {
		castellanoRbtn = new JRadioButton("Castellano");
		castellanoRbtn.setBounds(123, 7, 105, 23);
		castellanoRbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: " + Locale.getDefault());
				redraw();
			}
		});
		buttonGroup.add(castellanoRbtn);
	}

	private void initializeEnglishRbtn() {
		englishRbtn = new JRadioButton("English");
		englishRbtn.setBounds(230, 7, 92, 23);
		englishRbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: " + Locale.getDefault());
				redraw();
			}
		});
		buttonGroup.add(englishRbtn);
	}

	private void redraw() {
		selectOptionLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectUseCase"));
		browseQuestionsBtn.setText(ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestions"));
		BifunctionalBtn.setText(ResourceBundle.getBundle("Etiquetas").getString("ViewProfile"));
		btnInsertResults.setText(ResourceBundle.getBundle("Etiquetas").getString("btnInsertResults"));
		
		if(Username!=(null))
			if(Username.equals("admin"))
				BifunctionalBtn.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestion"));;
				
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	public JButton getBtnRegister() {
		return btnRegister;
	}

	public void setBtnRegister(JButton btnNewButton) {
		this.btnRegister = btnNewButton;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	public JButton getBifunctionalBtn() {
		return BifunctionalBtn;
	}

	public void setBifunctionalBtn(JButton bifunctionalBtn) {
		BifunctionalBtn = bifunctionalBtn;
	}

	public JButton getBtnInsertResults() {
		return btnInsertResults;
	}

	public void setBtnInsertResults(JButton btnInsertResults) {
		this.btnInsertResults = btnInsertResults;
	}
	
	
}