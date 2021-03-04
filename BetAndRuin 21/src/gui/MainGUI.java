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
import businessLogic.UserManager;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

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
	private UserManager um = new UserManager();

	public BlFacade getBusinessLogic() {
		return businessLogic;
	}

	public void setBussinessLogic(BlFacade afi) {
		businessLogic = afi;
	}

	public MainGUI() {
		super();
		setResizable(false);

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

		this.setBounds(100, 100, 373, 241);

		this.initializeMainPane();
		this.setContentPane(mainPane);
		
		um.resetLogins();

		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initializeMainPane() {
		mainPane = new JPanel();
		mainPane.setLayout(new GridLayout(5, 1, 0, 0));

		selectOptionLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Welcome"));
		selectOptionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mainPane.add(selectOptionLbl);

		initializeBrowseQuestionsBtn();
		mainPane.add(browseQuestionsBtn);
		initializeCancelBetsBtn();
		mainPane.add(BifunctionalBtn);

		initializeLocalePane();
		{
			panel = new JPanel();
			mainPane.add(panel);
			{
				MainGUI thisFrame = this;
				btnRegister = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
				btnRegister.setBounds(0, 0, 122, 34);
				btnRegister.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						setVisible(false);
						RegisterGUI RegisterWindow = new RegisterGUI();
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
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						setVisible(false);
						LoginGUI LoginWindow = new LoginGUI();
						LoginWindow.setVisible(true);
						LoginWindow.previousFrame(thisFrame);
					}
				});
				btnLogin.setBounds(164, 1, 115, 33);
				panel.add(btnLogin);
			}
		}
		mainPane.add(localePane);
	}

	private void initializeBrowseQuestionsBtn() {
		
		JFrame thisFrame = this;
		browseQuestionsBtn = new JButton();
		browseQuestionsBtn.setText(ResourceBundle.getBundle("Etiquetas").getString("BrowseQuestions"));
		browseQuestionsBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				setVisible(false);
				BrowseQuestionsGUI findQuestionsWindow = new BrowseQuestionsGUI(businessLogic);
				findQuestionsWindow.setVisible(true);
				findQuestionsWindow.previousFrame(thisFrame);
			}
		});
	}

	private void initializeCancelBetsBtn() {
		BifunctionalBtn = new JButton();
		BifunctionalBtn.setVisible(false);
		BifunctionalBtn.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI_User.BifunctionalBtn.text")); //$NON-NLS-1$ //$NON-NLS-2$
		BifunctionalBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

			}
		});
	}

	private void initializeLocalePane() {
		localePane = new JPanel();

		initializeEuskaraRbtn();
		localePane.add(euskaraRbtn);

		initializeCastellanoRbtn();
		localePane.add(castellanoRbtn);

		initializeEnglishRbtn();
		localePane.add(englishRbtn);
	}

	private void initializeEuskaraRbtn() {
		euskaraRbtn = new JRadioButton("Euskara");
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
		BifunctionalBtn.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestion"));
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
}