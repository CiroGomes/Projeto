package br.com.novaroma.rcinfo.apresentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidLoginException;
import br.com.novaroma.rcinfo.facade.Facade;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;

public class LoginScreen extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JPanel mainPane;
	private JLabel lblLogo;
	private JPanel LoginBackground;
	private JLabel lblCodeLogin;
	private JTextField codeTextField;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	@SuppressWarnings("rawtypes")
	private JComboBox selectOffice;
	private JButton buttonLogin;
	private JButton buttonClose;

	private Map<Integer, String[]> loginMap;
	private Facade facade;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.getLoginButton()) {
			this.LoginButton();
		} else if (e.getSource() == this.getCodeTextField()) {
			this.LoginButton();
		} else if (e.getSource() == this.getPasswordField()) {
			this.LoginButton();
		} else if (e.getSource() == this.getCloseButton()) {
			this.closeButton();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LoginScreen(Facade facade) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		this.setFacade(facade);
		this.setLoginMap(this.getFacade().getArrayListLogin());

		super.setTitle("RC INFO\r\n");
		super.setIconImage(Toolkit.getDefaultToolkit().getImage("icone.png"));
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(380, 320);
		super.setLocationRelativeTo(null);

		this.setMainPane(new JPanel());
		this.getMainPane().setBackground(new Color(130, 130, 130));
		this.getMainPane().setBorder(new EmptyBorder(5, 5, 5, 5));
		super.setContentPane(this.getMainPane());
		this.getMainPane().setLayout(null);

		super.setResizable(false);

		this.setLblLogo(new JLabel(""));
		this.getLblLogo().setIcon(new ImageIcon("logo.png"));
		this.getLblLogo().setBounds(111, 11, 163, 112);
		this.getMainPane().add(this.getLblLogo());

		this.setLoginBackground(new JPanel());
		this.getLoginBackground().setBackground(new Color(156, 156, 156));
		this.getLoginBackground().setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.getLoginBackground().setBounds(15, 140, 342, 137);
		this.getLoginBackground().setLayout(null);
		this.getMainPane().add(this.getLoginBackground());

		this.setCloseButton(new JButton("Sair"));
		this.getCloseButton().setBounds(260, 93, 70, 30);
		this.getCloseButton().setBackground(null);
		this.getLoginBackground().add(this.getCloseButton());
		this.getCloseButton().addActionListener(this);

		this.setLoginButton(new JButton("Entrar"));
		this.getLoginButton().setBounds(159, 93, 91, 30);
		this.getLoginButton().setBackground(null);
		this.getLoginBackground().add(this.getLoginButton());
		this.getLoginButton().addActionListener(this);

		this.setSelectOffice(new JComboBox());
		this.getSelectOffice().setModel(new DefaultComboBoxModel(new String[] { "Vendedor", "Gerente", "Administrador" }));
		this.getSelectOffice().setSelectedIndex(2);

		this.setCodeLoginLabel(new JLabel("Código:"));
		this.getCodeLoginLabel().setForeground(new Color(0, 0, 0));
		this.getCodeLoginLabel().setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.setCodeTextField(new JTextField());
		this.getCodeTextField().addActionListener(this);

		this.setLblPassword(new JLabel("Senha:"));
		this.getLblPassword().setForeground(new Color(0, 0, 0));
		this.getLblPassword().setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.setPasswordField(new JPasswordField());
		this.getPasswordField().addActionListener(this);

		super.setVisible(true);

	}

	public void LoginButton() {

		try {
			if (this.getFacade().autenticarLogin(this.getSelectOffice().getSelectedIndex(),
					this.getCodeTextField().getText(), String.valueOf(this.getPasswordField().getPassword()))) {

				super.dispose();

				try {
					new HomeScreen(this.getFacade(), this.getCodeTextField().getText());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (EmptyFieldException | InvalidCharactersFieldException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
		} catch (InvalidLoginException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void closeButton() {
		System.exit(JFrame.EXIT_ON_CLOSE);
	}

	public Map<Integer, String[]> getMapaLogin() {
		return loginMap;
	}

	public void setLoginMap(Map<Integer, String[]> mapaLogin) {
		this.loginMap = mapaLogin;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getSelectOffice() {
		return selectOffice;
	}

	@SuppressWarnings("rawtypes")
	public void setSelectOffice(JComboBox selectOffice) {
		this.selectOffice = selectOffice;
		selectOffice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.getSelectOffice().setBounds(10, 93, 139, 30);
		this.getLoginBackground().add(this.getSelectOffice());
	}

	public JPanel getMainPane() {
		return mainPane;
	}

	public void setMainPane(JPanel mainPane) {
		this.mainPane = mainPane;
	}

	public JTextField getCodeTextField() {
		return codeTextField;
	}

	public void setCodeTextField(JTextField userTextField) {
		this.codeTextField = userTextField;
		codeTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.codeTextField.setBounds(75, 11, 255, 30);
		this.getLoginBackground().add(this.codeTextField);
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		this.passwordField.setBounds(75, 52, 255, 30);
		this.getLoginBackground().add(this.passwordField);
	}

	public JButton getLoginButton() {
		return buttonLogin;
	}

	public void setLoginButton(JButton loginButton) {
		this.buttonLogin = loginButton;
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
	}

	public JButton getCloseButton() {
		return buttonClose;
	}

	public void setCloseButton(JButton closeButton) {
		this.buttonClose = closeButton;
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
	}

	public JLabel getCodeLoginLabel() {
		return lblCodeLogin;
	}

	public void setCodeLoginLabel(JLabel lblLoginCodigo) {
		this.lblCodeLogin = lblLoginCodigo;
		this.lblCodeLogin.setBounds(10, 11, 70, 30);
		this.getLoginBackground().add(this.lblCodeLogin);
	}

	public JLabel getLblLogo() {
		return lblLogo;
	}

	public void setLblLogo(JLabel lblLogo) {
		this.lblLogo = lblLogo;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}

	public void setLblPassword(JLabel lblPassword) {
		this.lblPassword = lblPassword;
		this.lblPassword.setBounds(10, 52, 75, 30);
		this.getLoginBackground().add(this.lblPassword);
	}

	public JPanel getLoginBackground() {
		return LoginBackground;
	}

	public void setLoginBackground(JPanel loginBackground) {
		this.LoginBackground = loginBackground;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Facade getFacade() {
		return facade;
	}

	public void setFacade(Facade facade) {
		this.facade = facade;
	}
}