package br.com.novaroma.rcinfo.apresentation.internalframes;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import br.com.novaroma.rcinfo.entities.Employee;
import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.ExistingEntityException;
import br.com.novaroma.rcinfo.exceptions.InvalidAgeException;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;
import br.com.novaroma.rcinfo.exceptions.NotFoundException;
import br.com.novaroma.rcinfo.facade.Facade;
import br.com.novaroma.rcinfo.utils.Utils;

public class CreateEmployeeScreen implements InternalFrames {
	JInternalFrame internalFrame;

	public JInternalFrame getInternalFrame() {
		return internalFrame;
	}

	public void setInternalFrame(JInternalFrame internalFrame) {
		this.internalFrame = internalFrame;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CreateEmployeeScreen(final Facade facade, String employeeCodeOn) throws EmptyFieldException, InvalidCharactersFieldException, NotFoundException {
		internalFrame = new JInternalFrame();
		internalFrame.setBounds(0, 100, 550, 482);
		internalFrame.getContentPane().setLayout(null);

		final JTextField nameTextField = new JTextField();
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameTextField.setBounds(172, 114, 352, 30);
		nameTextField.setColumns(10);
		internalFrame.getContentPane().add(nameTextField);

		JLabel birthdayLabel = new JLabel("Data de Nascimento:  ");
		birthdayLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		birthdayLabel.setBounds(33, 155, 140, 30);
		internalFrame.getContentPane().add(birthdayLabel);

		final JComboBox employeeComboBox = new JComboBox();
		employeeComboBox.setBackground(null);
		employeeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		employeeComboBox.setModel(new DefaultComboBoxModel(facade.simpleReadEmployee(employeeCodeOn).getOffice() == 2
				? new String[] { "Vendedor", "Gerente", "Administrador" }
				: facade.simpleReadEmployee(employeeCodeOn).getOffice() > 1 ? new String[] { "Vendedor", "Gerente" }
				: new String[] { "Vendedor" }));
		employeeComboBox.setBounds(172, 237, 114, 30);
		internalFrame.getContentPane().add(employeeComboBox);

		JLabel cpfLabel = new JLabel("CPF: ");
		cpfLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cpfLabel.setBounds(316, 155, 43, 30);
		internalFrame.getContentPane().add(cpfLabel);

		MaskFormatter cpfMask = null;
		try {
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cpfMask.setPlaceholderCharacter('_');

		final JFormattedTextField cpfTextFieldFormatted = new JFormattedTextField(cpfMask);
		cpfTextFieldFormatted.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cpfTextFieldFormatted.setSize(137, 30);
		cpfTextFieldFormatted.setLocation(387, 155);
		internalFrame.getContentPane().add(cpfTextFieldFormatted);

		JLabel employeeRegisterLabel = new JLabel("Cadastro de Funcionário");
		employeeRegisterLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		employeeRegisterLabel.setBounds(10, 76, 259, 27);
		internalFrame.getContentPane().add(employeeRegisterLabel);

		final JTextField codeTextField = new JTextField();
		codeTextField.setEditable(false);
		codeTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codeTextField.setColumns(10);
		codeTextField.setBounds(173, 196, 113, 30);

		internalFrame.getContentPane().add(codeTextField);

		JLabel nameLabel = new JLabel("Nome Completo: ");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setBounds(33, 114, 140, 30);
		internalFrame.getContentPane().add(nameLabel);

		JLabel codeLabel = new JLabel("Código: ");
		codeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codeLabel.setBounds(33, 196, 140, 30);
		internalFrame.getContentPane().add(codeLabel);

		JLabel officeLabel = new JLabel("Cargo: ");
		officeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		officeLabel.setBounds(32, 237, 46, 30);
		internalFrame.setTitle("Cadastro Funcionário");
		internalFrame.getContentPane().add(officeLabel);

		JButton codeGenerateButton = new JButton("Gerar Código");
		codeGenerateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				codeTextField.setText(Utils.generateCode(facade.getMapEmployee()) + "");
			}
		});
		codeGenerateButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codeGenerateButton.setBounds(296, 196, 113, 30);
		internalFrame.getContentPane().add(codeGenerateButton);

		final JPasswordField passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(386, 237, 138, 30);
		internalFrame.getContentPane().add(passwordField);

		JLabel passwordLabel = new JLabel("Senha: ");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordLabel.setBounds(315, 237, 62, 30);

		final JDateChooser dateChooserBirthday = new JDateChooser();
		dateChooserBirthday.setBounds(172, 155, 114, 30);
		internalFrame.getContentPane().add(dateChooserBirthday);

		JButton registerButton = new JButton("Cadastrar");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		registerButton.setBounds(322, 278, 96, 30);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (facade.getEmployeeController().createEmployee(new Employee(employeeComboBox.getSelectedIndex(),
							codeTextField.getText(), nameTextField.getText(), cpfTextFieldFormatted.getText(),
							dateChooserBirthday.getCalendar()), String.valueOf(passwordField.getPassword()))) {
						JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso");

					} else {
						JOptionPane.showMessageDialog(null, "Falha ao cadastrar funcionário");
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (NumberFormatException | ExistingEntityException | EmptyFieldException | InvalidCharactersFieldException | InvalidAgeException e) {
					JOptionPane.showMessageDialog(internalFrame, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		internalFrame.getContentPane().add(registerButton);
		internalFrame.getContentPane().add(passwordLabel);

		JButton closeButton = new JButton("Fechar");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				internalFrame.dispose();
			}
		});
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		closeButton.setBounds(428, 278, 96, 30);
		internalFrame.getContentPane().add(closeButton);
		internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		internalFrame.setClosable(true);
		internalFrame.setIconifiable(true);

		internalFrame.addComponentListener(new ComponentAdapter() {
			public void componentMoved(ComponentEvent event) {
				internalFrame.setLocation(100, 100);
			}
		});
		internalFrame.setVisible(true);
	}
}