package br.com.novaroma.rcinfo.apresentation.internalframes;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import br.com.novaroma.rcinfo.entities.Employee;
import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidAgeException;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidEmailException;
import br.com.novaroma.rcinfo.exceptions.NotFoundException;
import br.com.novaroma.rcinfo.facade.Facade;

public class SearchEmployeeScreen implements InternalFrames {

	JInternalFrame internalFrame;
	Employee funcionario = null;
	Employee employeeOn;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private void completeList(final Facade facade, final JDesktopPane desktopPane, final Employee employeeOn) {
		JInternalFrame completeList = new JInternalFrame();
		completeList.setClosable(true);
		completeList.setMaximizable(true);
		completeList.setResizable(true);
		completeList.setIconifiable(true);
		ArrayList<Employee> listAllEmployees = new ArrayList<>(facade.getMapEmployee().values());
		ArrayList<Employee> listSelectedEmployees = new ArrayList<Employee>();
		if (employeeOn.getOffice() != 2) {
			for (Employee employee : listAllEmployees) {
				if (employee.getOffice() < employeeOn.getOffice()) {
					listSelectedEmployees.add(employee);
				}
			}
		} else {
			listSelectedEmployees = listAllEmployees;
		}
		String[] arrayEmployee = new String[listSelectedEmployees.size()];
		for (int i = 0; i < arrayEmployee.length; i++) {
			arrayEmployee[i] = listSelectedEmployees.get(i).getCode() + " - "
					+ (listSelectedEmployees.get(i).getOffice() == 0 ? "Vendedor"
							: listSelectedEmployees.get(i).getOffice() == 1 ? "Gerente"
									: listSelectedEmployees.get(i).getOffice() == 2 ? "Administrador" : "")+ ": " + listSelectedEmployees.get(i).getName();
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JList<String[]> list = new JList(arrayEmployee);
		list.setBounds(485, 116, 116, 105);
		JScrollPane scrollPaneTest = new JScrollPane(list);

		completeList.setBounds(550, 80, 260, 482);
		scrollPaneTest.setBounds(completeList.getBounds());
		scrollPaneTest.setVisible(true);
		completeList.getContentPane().add(scrollPaneTest);
		completeList.setVisible(true);
		desktopPane.add(completeList);
	}

	public JInternalFrame getInternalFrame() {
		return internalFrame;
	}

	public void setInternalFrame(JInternalFrame internalFrame) {
		this.internalFrame = internalFrame;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SearchEmployeeScreen(final Facade facade, final String employeeCodeOn, final JDesktopPane desktopPane)
			throws EmptyFieldException, InvalidCharactersFieldException, NotFoundException {
		employeeOn = facade.simpleReadEmployee(employeeCodeOn);

		internalFrame = new JInternalFrame();
		internalFrame.setBounds(0, 80, 550, 482);
		internalFrame.setTitle("Buscar Funcionário");
		internalFrame.getContentPane().setLayout(null);

		final JLabel employeeSearchLabel = new JLabel("Buscar Funcion\u00E1rio");
		employeeSearchLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		employeeSearchLabel.setBounds(10, 48, 419, 27);
		internalFrame.getContentPane().add(employeeSearchLabel);

		JLabel nameLabel = new JLabel("Nome Completo: ");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setBounds(10, 127, 140, 30);
		internalFrame.getContentPane().add(nameLabel);

		final JTextField nameTextField = new JTextField();
		nameTextField.setEditable(false);
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameTextField.setBounds(149, 127, 375, 30);
		nameTextField.setColumns(10);
		internalFrame.getContentPane().add(nameTextField);

		JLabel cpfLabel = new JLabel("CPF: ");
		cpfLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cpfLabel.setBounds(283, 168, 43, 30);
		internalFrame.getContentPane().add(cpfLabel);

		MaskFormatter dateMask = null;
		MaskFormatter cpfMask = null;
		try {
			dateMask = new MaskFormatter("##/##/####");
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cpfMask.setPlaceholderCharacter('_');
		dateMask.setPlaceholderCharacter('_');

		final JFormattedTextField formattedTextFieldCpf = new JFormattedTextField(cpfMask);
		formattedTextFieldCpf.setEditable(false);
		formattedTextFieldCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextFieldCpf.setBounds(336, 168, 188, 30);
		internalFrame.getContentPane().add(formattedTextFieldCpf);

		JLabel birthdayLabel = new JLabel("Data de Nascimento:  ");
		birthdayLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		birthdayLabel.setBounds(10, 168, 140, 30);
		internalFrame.getContentPane().add(birthdayLabel);

		final JFormattedTextField formattedTextFieldBirthday = new JFormattedTextField(dateMask);
		formattedTextFieldBirthday.setEditable(false);
		formattedTextFieldBirthday.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextFieldBirthday.setColumns(10);
		formattedTextFieldBirthday.setBounds(149, 168, 120, 30);
		internalFrame.getContentPane().add(formattedTextFieldBirthday);

		final JDateChooser dateChooserBirthday = new JDateChooser();
		dateChooserBirthday.setBounds(new Rectangle(149, 168, 120, 30));
		internalFrame.getContentPane().add(dateChooserBirthday);
		dateChooserBirthday.setVisible(false);

		JLabel codeLabel = new JLabel("C\u00F3digo: ");
		codeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codeLabel.setBounds(10, 86, 69, 30);
		internalFrame.getContentPane().add(codeLabel);

		final JTextField codeTextField = new JTextField();
		codeTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codeTextField.setColumns(10);
		codeTextField.setBounds(149, 86, 120, 30);

		internalFrame.getContentPane().add(codeTextField);

		JLabel officeLabel = new JLabel("Cargo: ");
		officeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		officeLabel.setBounds(10, 210, 46, 30);
		internalFrame.setTitle("Cadastro Funcionário");
		internalFrame.getContentPane().add(officeLabel);

		internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		internalFrame.setClosable(true);
		internalFrame.setIconifiable(true);

		final JComboBox comboBoxEmployee = new JComboBox();
		comboBoxEmployee.setEnabled(false);
		comboBoxEmployee.setBackground(null);
		comboBoxEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxEmployee.setModel(new DefaultComboBoxModel(new String[] { "Vendedor", "Gerente", "Administrador" }));
		comboBoxEmployee.setBounds(150, 209, 120, 30);
		internalFrame.getContentPane().add(comboBoxEmployee);

		final JButton deleteButton = new JButton("Deletar");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (funcionario != null && funcionario.getOffice() < employeeOn.getOffice()) {
					try {
						if (facade.delete(facade.simpleReadEmployee(codeTextField.getText()))) {
							nameTextField.setText("");
							formattedTextFieldBirthday.setText("");
							formattedTextFieldCpf.setText("");
							funcionario = null;
							JOptionPane.showMessageDialog(null, "Funcionario deletado com sucesso");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Funcionario não pôde ser deletado");
				}
			}
		});
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deleteButton.setBounds(167, 292, 115, 30);
		deleteButton.setVisible(false);
		internalFrame.getContentPane().add(deleteButton);
		final JButton searchButton = new JButton("Buscar");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					funcionario = facade.simpleReadEmployee(codeTextField.getText());
				} catch (EmptyFieldException | InvalidCharactersFieldException | NotFoundException e1) {
					JOptionPane.showMessageDialog(internalFrame, e1.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
				}
				if (funcionario != null && funcionario.getOffice() >= employeeOn.getOffice()
						&& employeeOn.getOffice() != 2) {
					JOptionPane.showMessageDialog(internalFrame, "Acesso negado");
				} else if (funcionario != null && funcionario.getOffice() < employeeOn.getOffice() || funcionario != null && employeeOn.getOffice() == 2) {
					nameTextField.setText(funcionario.getName());
					formattedTextFieldBirthday.setText(dateFormat.format(funcionario.getBirthday().getTime()));
					formattedTextFieldCpf.setText(funcionario.getCpf());
					comboBoxEmployee.setSelectedIndex(funcionario.getOffice());
					if(!(employeeOn == funcionario)){
						deleteButton.setVisible(true);
					} else {
						deleteButton.setVisible(false);
					}
					
				}
			}
		});
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchButton.setBounds(287, 86, 115, 30);
		internalFrame.getContentPane().add(searchButton);

		final JButton closeButton = new JButton("Fechar");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				internalFrame.dispose();
			}
		});
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		closeButton.setBounds(409, 292, 115, 30);
		internalFrame.getContentPane().add(closeButton);

		final JButton changeButton = new JButton("Alterar");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (funcionario != null && funcionario.getOffice() < employeeOn.getOffice() || funcionario != null && employeeOn.getOffice() == 2) {
					internalFrame.setVisible(false);

					employeeSearchLabel.setText("Alterar Funcionario");

					codeTextField.setEditable(false);

					formattedTextFieldBirthday.setVisible(false);
					dateChooserBirthday.setVisible(true);
					dateChooserBirthday.setCalendar(funcionario.getBirthday());

					formattedTextFieldCpf.setEditable(true);
					nameTextField.setEditable(true);
					comboBoxEmployee.setEnabled(true);

					final JLabel passwordLabel = new JLabel("Senha: ");
					passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
					passwordLabel.setBounds(10, 251, 140, 30);
					internalFrame.getContentPane().add(passwordLabel);

					final JPasswordField passwordField = new JPasswordField();
					passwordField.setColumns(10);
					passwordField.setBounds(149, 251, 120, 30);
					internalFrame.getContentPane().add(passwordField);

					final JLabel lblOpcionalPassword = new JLabel("(Deixar vazio não alterará a senha)");
					lblOpcionalPassword.setEnabled(false);
					lblOpcionalPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
					lblOpcionalPassword.setBounds(283, 251, 218, 30);
					internalFrame.getContentPane().add(lblOpcionalPassword);

					changeButton.setVisible(false);
					deleteButton.setVisible(false);
					closeButton.setVisible(false);
					searchButton.setVisible(false);

					internalFrame.setVisible(true);

					final JButton confirmButton = new JButton("Confirmar");
					confirmButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							Employee newEmployee = new Employee(comboBoxEmployee.getSelectedIndex(),
									codeTextField.getText(), nameTextField.getText(), formattedTextFieldCpf.getText(),
									dateChooserBirthday.getCalendar());

							try {
								if (String.valueOf(passwordField.getPassword()).equals("")
										? facade.update(newEmployee)
										: facade.updateEmployee(newEmployee,
												String.valueOf(passwordField.getPassword()))) {
									funcionario = newEmployee;
									employeeSearchLabel.setText("Informações atualizadas do funcionario");
									confirmButton.setVisible(false);

									nameTextField.setEditable(false);

									formattedTextFieldBirthday.setVisible(true);
									dateChooserBirthday.setVisible(false);

									formattedTextFieldCpf.setEditable(false);
									comboBoxEmployee.setEnabled(false);

									if (String.valueOf(passwordField.getPassword()).equals("")) {
										passwordLabel.setVisible(false);
										passwordField.setVisible(false);
										lblOpcionalPassword.setVisible(false);
									} else {
										passwordField.setEditable(false);
									}

									nameTextField.setText(funcionario.getName());
									formattedTextFieldBirthday.setText(dateFormat.format(funcionario.getBirthday().getTime()));
									comboBoxEmployee.setSelectedIndex(funcionario.getOffice());
									formattedTextFieldCpf.setText(funcionario.getCpf());
									JOptionPane.showMessageDialog(internalFrame, "Atualizado com sucesso");
								} else {
									JOptionPane.showMessageDialog(internalFrame, "Falha ao atualizar");
								}
							} catch (HeadlessException e1) {
								e1.printStackTrace();
							} catch (InvalidCharactersFieldException | EmptyFieldException | InvalidAgeException | InvalidEmailException e1) {
								JOptionPane.showMessageDialog(internalFrame, e1.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
					confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
					confirmButton.setBounds(changeButton.getBounds());
					internalFrame.getContentPane().add(confirmButton);

					final JButton backButton = new JButton("Voltar");
					backButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							confirmButton.setVisible(false);
							backButton.setVisible(false);
							changeButton.setVisible(true);
							deleteButton.setVisible(true);
							closeButton.setVisible(true);
							searchButton.setVisible(true);

							employeeSearchLabel.setText("Buscar Funcionario");

							codeTextField.setEditable(true);
							nameTextField.setEditable(false);

							formattedTextFieldBirthday.setVisible(true);
							dateChooserBirthday.setVisible(false);

							formattedTextFieldCpf.setEditable(false);
							comboBoxEmployee.setEnabled(false);
							passwordLabel.setVisible(false);
							passwordField.setVisible(false);
							lblOpcionalPassword.setVisible(false);

							nameTextField.setText(funcionario.getName());
							formattedTextFieldBirthday.setText(dateFormat.format(funcionario.getBirthday().getTime()));
							comboBoxEmployee.setSelectedIndex(funcionario.getOffice());
							formattedTextFieldCpf.setText(funcionario.getCpf());

						}
					});
					backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
					backButton.setBounds(closeButton.getBounds());
					internalFrame.getContentPane().add(backButton);

				} else {
					JOptionPane.showMessageDialog(null, "Defina um funcionario cadastrado primeiro");
				}
			}
		});
		changeButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		changeButton.setBounds(287, 292, 115, 30);
		internalFrame.getContentPane().add(changeButton);

		JButton listAllButton = new JButton("Listar Todos");
		listAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				completeList(facade, desktopPane, employeeOn);
			}
		});
		listAllButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		listAllButton.setBounds(409, 86, 115, 30);
		internalFrame.getContentPane().add(listAllButton);
		internalFrame.setVisible(true);
	}
}