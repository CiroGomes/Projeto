package br.com.novaroma.rcinfo.apresentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import br.com.novaroma.rcinfo.apresentation.internalframes.CreateClientScreen;
import br.com.novaroma.rcinfo.apresentation.internalframes.CreateEmployeeScreen;
import br.com.novaroma.rcinfo.apresentation.internalframes.CreateProductScreen;
import br.com.novaroma.rcinfo.apresentation.internalframes.InternalFrames;
import br.com.novaroma.rcinfo.apresentation.internalframes.PerformSellScreen;
import br.com.novaroma.rcinfo.apresentation.internalframes.SearchClientScreen;
import br.com.novaroma.rcinfo.apresentation.internalframes.SearchEmployeeScreen;
import br.com.novaroma.rcinfo.apresentation.internalframes.SearchProductScreen;
import br.com.novaroma.rcinfo.apresentation.internalframes.SearchSellScreen;
import br.com.novaroma.rcinfo.entities.Employee;
import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;
import br.com.novaroma.rcinfo.exceptions.NotFoundException;
import br.com.novaroma.rcinfo.facade.Facade;

public class HomeScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	int openFrameCount = 0;
	static final int xOffset = 50, yOffset = 50;

	private String employeeTypeOn;
	private Employee employee;

	private JPanel mainPane;
	final Facade facade;
	private JDesktopPane desktopPane;
	private JLabel lblLogo;

	private JButton sellPurchaseButton;
	private JButton sellSearchButton;
	private JButton clientRegisterButton;
	private JButton productRegisterButton;
	private JButton employeeRegisterButton;
	private JButton clientSearchButton;
	private JButton productSearchButton;
	private JButton employeeSearchButton;
	private JButton logoutButton;
	private JButton closeButton;

	InternalFrames ClientRegister;
	InternalFrames EmployeeRegister;
	InternalFrames ProductSearch;
	InternalFrames ProductRegister;
	InternalFrames SellPurchase;
	InternalFrames SellSearch;
	InternalFrames ClientSearch;
	InternalFrames EmployeeSearch;

	public InternalFrames ScreenInitialize(InternalFrames frame, String type)
			throws EmptyFieldException, InvalidCharactersFieldException, NotFoundException {
		if (frame == null || frame.getInternalFrame().isClosed()) {
			frame = type.equals("realizarV") ? new PerformSellScreen(facade, desktopPane)
					: type.equals("cadastrarC") ? new CreateClientScreen(facade)
							: type.equals("cadastrarP") ? new CreateProductScreen(facade)
									: type.equals("cadastrarF") ? new CreateEmployeeScreen(facade, employeeTypeOn)
											: type.equals("buscarP")
													? new SearchProductScreen(facade, employeeTypeOn, desktopPane)
													: type.equals("buscarC")
															? new SearchClientScreen(facade, desktopPane)
															: type.equals("buscarF")
																	? new SearchEmployeeScreen(facade, employeeTypeOn,
																			desktopPane)
																	: type.equals("buscarV")
																			? new SearchSellScreen(facade, desktopPane)
																			: null;
			desktopPane.add(frame.getInternalFrame());
			try {
				if (frame instanceof PerformSellScreen) {
					((PerformSellScreen) frame).getTaxCouponScreen().setSelected(true);
				}
				frame.getInternalFrame().setSelected(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				frame.getInternalFrame().setSelected(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == sellPurchaseButton) {

				SellPurchase = ScreenInitialize(SellPurchase, "realizarV");

			} else if (e.getSource() == clientRegisterButton) {

				ClientRegister = ScreenInitialize(ClientRegister, "cadastrarC");

			} else if (e.getSource() == productRegisterButton) {

				ProductRegister = ScreenInitialize(ProductRegister, "cadastrarP");

			} else if (e.getSource() == employeeRegisterButton) {

				EmployeeRegister = ScreenInitialize(EmployeeRegister, "cadastrarF");

			} else if (e.getSource() == productSearchButton) {

				ProductSearch = ScreenInitialize(ProductSearch, "buscarP");

			} else if (e.getSource() == logoutButton) {

				super.dispose();
				new LoginScreen(facade);

			} else if (e.getSource() == closeButton) {

				System.exit(JFrame.EXIT_ON_CLOSE);

			} else if (e.getSource() == clientSearchButton) {

				ClientSearch = ScreenInitialize(ClientSearch, "buscarC");

			} else if (e.getSource() == employeeSearchButton) {

				EmployeeSearch = ScreenInitialize(EmployeeSearch, "buscarF");

			} else if (e.getSource() == sellSearchButton) {

				SellSearch = ScreenInitialize(SellSearch, "buscarV");

			}
		} catch (EmptyFieldException | InvalidCharactersFieldException | NotFoundException e1) {
			JOptionPane.showMessageDialog(desktopPane, e1.getMessage());
		}
	}

	public HomeScreen(final Facade facade, String employeeCodeOn) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.employeeTypeOn = employeeCodeOn;
		try {
			this.employee = facade.simpleReadEmployee(employeeCodeOn);
		} catch (EmptyFieldException | InvalidCharactersFieldException | NotFoundException e) {
			JOptionPane.showMessageDialog(desktopPane, e.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
		}
		this.facade = facade;
		String employeeOn = employee.getOffice() == 0 ? "Vendedor "
				: employee.getOffice() == 1 ? "Gerente" : "Administrador";

		super.setTitle("RC INFO - " + employeeOn);
		super.setSize(1024, 710);
		super.setLocationRelativeTo(null);
		super.setIconImage(Toolkit.getDefaultToolkit().getImage("icone.png"));
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setMainPane(new JPanel());
		this.getMainPane().setBackground(new Color(130, 130, 130));
		this.getMainPane().setBorder(new EmptyBorder(5, 5, 5, 5));
		super.setContentPane(this.getMainPane());
		this.getMainPane().setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(209, 0, 809, 682);
		desktopPane.setLayout(null);
		desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		desktopPane.setBackground(null);
		mainPane.add(desktopPane);

		JLabel lblAccountOptions = new JLabel("Opções de Conta:");
		lblAccountOptions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAccountOptions.setBounds(10, 559, 116, 20);
		mainPane.add(lblAccountOptions);

		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(this);
		logoutButton.setToolTipText("Fechar conta atual e voltar à tela de login");
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		logoutButton.setBounds(20, 590, 179, 35);
		logoutButton.setBackground(null);
		mainPane.add(logoutButton);

		closeButton = new JButton("Sair");
		closeButton.setToolTipText("Fechar completamente o sistema");
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		closeButton.setBounds(20, 636, 179, 35);
		closeButton.addActionListener(this);
		closeButton.setBackground(null);
		mainPane.add(closeButton);

		sellPurchaseButton = new JButton("Realizar Venda");
		sellPurchaseButton.setBounds(10, 129, 189, 35);
		mainPane.add(sellPurchaseButton);
		sellPurchaseButton.setToolTipText("Cadastra nova venda");
		sellPurchaseButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sellPurchaseButton.addActionListener(this);
		sellPurchaseButton.setBackground(null);

		sellSearchButton = new JButton("Buscar Venda");
		sellSearchButton.addActionListener(this);
		sellSearchButton.setToolTipText("Busca venda através da data da venda e cpf do cliente");
		sellSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sellSearchButton.setBackground(null);
		sellSearchButton.setBounds(10, 175, 189, 35);
		mainPane.add(sellSearchButton);

		JLabel lblRegister = new JLabel("Cadastrar:");
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegister.setBounds(10, 221, 90, 20);
		mainPane.add(lblRegister);

		clientRegisterButton = new JButton("Cadastrar Cliente");
		clientRegisterButton.setToolTipText("Cadastra novo cliente");
		clientRegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		clientRegisterButton.setBounds(20, 252, 179, 35);
		clientRegisterButton.addActionListener(this);
		clientRegisterButton.setBackground(null);
		mainPane.add(clientRegisterButton);

		productRegisterButton = new JButton("Cadastrar Produto");
		productRegisterButton.setToolTipText("Cadastra novo produto");
		productRegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		productRegisterButton.setBounds(20, 298, 179, 35);
		productRegisterButton.addActionListener(this);
		productRegisterButton.setBackground(null);
		mainPane.add(productRegisterButton);

		JLabel lblSearch = new JLabel("Buscar: ");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearch.setBounds(10, 390, 90, 20);
		mainPane.add(lblSearch);

		clientSearchButton = new JButton("Buscar Cliente");
		clientSearchButton.setBounds(20, 421, 179, 35);
		clientSearchButton.setBackground(null);
		mainPane.add(clientSearchButton);
		clientSearchButton.setToolTipText("Busca cliente através do cpf");
		clientSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		clientSearchButton.addActionListener(this);

		productSearchButton = new JButton("Buscar Produto");
		productSearchButton.setBounds(20, 467, 179, 35);
		productSearchButton.setBackground(null);
		mainPane.add(productSearchButton);
		productSearchButton.setToolTipText("Busca produto através do código");
		productSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("logo.png"));
		lblLogo.setBounds(20, 0, 170, 129);
		mainPane.add(lblLogo);
		productSearchButton.addActionListener(this);

		if (employee.getOffice() > 0) {
			employeeRegisterButton = new JButton("Cadastrar Funcionário");
			employeeRegisterButton.setToolTipText("Cadastra novo funcionário");
			employeeRegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
			employeeRegisterButton.setBounds(20, 344, 179, 35);
			employeeRegisterButton.addActionListener(this);
			employeeRegisterButton.setBackground(null);
			mainPane.add(employeeRegisterButton);

			employeeSearchButton = new JButton("Buscar Funcionário");
			employeeSearchButton.setToolTipText("Busca funcionário através do código");
			employeeSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
			employeeSearchButton.setBounds(20, 513, 179, 35);
			employeeSearchButton.addActionListener(this);
			employeeSearchButton.setBackground(null);
			mainPane.add(employeeSearchButton);
		}

		super.setResizable(false);
		super.setVisible(true);

	}

	public JPanel getMainPane() {
		return mainPane;
	}

	public void setMainPane(JPanel mainPane) {
		this.mainPane = mainPane;
	}
}