package br.com.novaroma.rcinfo.apresentation.internalframes;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import br.com.novaroma.rcinfo.entities.Adress;
import br.com.novaroma.rcinfo.entities.Client;
import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidAgeException;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidEmailException;
import br.com.novaroma.rcinfo.exceptions.NotFoundException;
import br.com.novaroma.rcinfo.facade.Facade;

public class SearchClientScreen implements InternalFrames {
	private JInternalFrame internalFrame;
	Client client = null;
	final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private void completeList(final Facade facade, final JDesktopPane desktopPane) {
		JInternalFrame completeList = new JInternalFrame();
		completeList.setClosable(true);
		completeList.setMaximizable(true);
		completeList.setResizable(true);
		completeList.setIconifiable(true);
		ArrayList<Client> listSelectedEmployees = new ArrayList<Client>(facade.getMapClient().values());
		String[] arrayClients = new String[listSelectedEmployees.size()];
		for (int i = 0; i < arrayClients.length; i++) {
			arrayClients[i] = listSelectedEmployees.get(i).getCpf() + ": " + listSelectedEmployees.get(i).getName();
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JList<String[]> list = new JList(arrayClients);
		list.setBounds(485, 116, 116, 105);
		JScrollPane scrollPaneTest = new JScrollPane(list);

		completeList.setBounds(550, 20, 260, 482);
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

	public SearchClientScreen(final Facade facade, final JDesktopPane desktopPane) {
		internalFrame = new JInternalFrame();
		internalFrame.setTitle("Buscar Cliente");
		internalFrame.setBounds(0, 20, 550, 482);
		internalFrame.getContentPane().setLayout(null);

		MaskFormatter dateMask = null;
		MaskFormatter cpfMask = null;
		MaskFormatter phoneNumberMask = null;
		MaskFormatter zipCodeMask = null;
		try {
			dateMask = new MaskFormatter("##/##/####");
			cpfMask = new MaskFormatter("###.###.###-##");
			phoneNumberMask = new MaskFormatter("(##)#####-####");
			zipCodeMask = new MaskFormatter("##.###-###");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cpfMask.setPlaceholderCharacter('_');
		dateMask.setPlaceholderCharacter('_');
		phoneNumberMask.setPlaceholderCharacter('_');
		zipCodeMask.setPlaceholderCharacter('_');

		final JLabel clientSearchLabel = new JLabel("Buscar Cliente");
		clientSearchLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		clientSearchLabel.setBounds(10, 58, 350, 27);
		internalFrame.getContentPane().add(clientSearchLabel);

		JLabel nameLabel = new JLabel("Nome Completo: ");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setBounds(30, 137, 140, 30);
		internalFrame.getContentPane().add(nameLabel);

		final JTextField nameTextField = new JTextField();
		nameTextField.setEditable(false);
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameTextField.setBounds(169, 137, 352, 30);
		internalFrame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);

		JLabel lblBirthday = new JLabel("Data de Nascimento:  ");
		lblBirthday.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBirthday.setBounds(30, 178, 140, 30);
		internalFrame.getContentPane().add(lblBirthday);

		final JFormattedTextField formattedTextFieldBirthday = new JFormattedTextField(dateMask);
		formattedTextFieldBirthday.setEditable(false);
		formattedTextFieldBirthday.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextFieldBirthday.setColumns(10);
		formattedTextFieldBirthday.setBounds(170, 178, 132, 30);
		internalFrame.getContentPane().add(formattedTextFieldBirthday);

		final JDateChooser dateChooserBirthday = new JDateChooser();
		dateChooserBirthday.setBounds(formattedTextFieldBirthday.getBounds());
		internalFrame.getContentPane().add(dateChooserBirthday);
		dateChooserBirthday.setVisible(false);

		JLabel lblCpf = new JLabel("CPF: ");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(30, 96, 43, 30);
		internalFrame.getContentPane().add(lblCpf);

		final JFormattedTextField formattedTextFieldCpf = new JFormattedTextField(cpfMask);
		formattedTextFieldCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextFieldCpf.setBounds(131, 96, 133, 30);
		internalFrame.getContentPane().add(formattedTextFieldCpf);

		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(30, 219, 140, 30);
		internalFrame.getContentPane().add(lblEmail);

		final JTextField textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(169, 219, 352, 30);
		internalFrame.getContentPane().add(textFieldEmail);

		JLabel streetLabel = new JLabel("Rua: ");
		streetLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		streetLabel.setBounds(30, 260, 140, 30);
		internalFrame.getContentPane().add(streetLabel);

		final JTextField streetTextField = new JTextField();
		streetTextField.setEditable(false);
		streetTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		streetTextField.setColumns(10);
		streetTextField.setBounds(169, 260, 352, 30);
		internalFrame.getContentPane().add(streetTextField);

		JLabel lblNumero = new JLabel("N\u00FAmero: ");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumero.setBounds(30, 301, 69, 30);
		internalFrame.getContentPane().add(lblNumero);

		final JTextField numberTextField = new JTextField();
		numberTextField.setEditable(false);
		numberTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numberTextField.setColumns(10);
		numberTextField.setBounds(169, 301, 133, 30);
		internalFrame.getContentPane().add(numberTextField);

		JLabel lblTelefone = new JLabel("Telefone: ");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(312, 178, 66, 30);
		internalFrame.getContentPane().add(lblTelefone);

		final JFormattedTextField formattedTextFieldTelefone = new JFormattedTextField(phoneNumberMask);
		formattedTextFieldTelefone.setEditable(false);
		formattedTextFieldTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextFieldTelefone.setColumns(10);
		formattedTextFieldTelefone.setBounds(388, 179, 133, 30);
		internalFrame.getContentPane().add(formattedTextFieldTelefone);

		JLabel lblCidade = new JLabel("Cidade: ");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCidade.setBounds(30, 342, 69, 30);
		internalFrame.getContentPane().add(lblCidade);

		final JTextField cityTextField = new JTextField();
		cityTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cityTextField.setEditable(false);
		cityTextField.setColumns(10);
		cityTextField.setBounds(169, 342, 133, 30);
		internalFrame.getContentPane().add(cityTextField);

		JLabel neighborhoodLabel = new JLabel("Bairro: ");
		neighborhoodLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		neighborhoodLabel.setBounds(312, 342, 69, 30);
		internalFrame.getContentPane().add(neighborhoodLabel);

		final JTextField neighborhoodTextField = new JTextField();
		neighborhoodTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		neighborhoodTextField.setEditable(false);
		neighborhoodTextField.setColumns(10);
		neighborhoodTextField.setBounds(388, 342, 133, 30);
		internalFrame.getContentPane().add(neighborhoodTextField);

		JLabel zipCodeLabel = new JLabel("CEP:");
		zipCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		zipCodeLabel.setBounds(316, 301, 43, 30);
		internalFrame.getContentPane().add(zipCodeLabel);

		final JFormattedTextField formattedTextFieldCep = new JFormattedTextField(zipCodeMask);
		formattedTextFieldCep.setEditable(false);
		formattedTextFieldCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextFieldCep.setBounds(384, 301, 137, 30);
		internalFrame.getContentPane().add(formattedTextFieldCep);

		internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		internalFrame.setClosable(true);
		internalFrame.setIconifiable(true);

		final JButton searchButton = new JButton("Buscar");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client = facade.simpleReadClient(formattedTextFieldCpf.getText());

					fillFields(nameTextField, formattedTextFieldBirthday, textFieldEmail, streetTextField,
							numberTextField, formattedTextFieldTelefone, formattedTextFieldCep, cityTextField,
							neighborhoodTextField);
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (EmptyFieldException | NotFoundException e1) {
					JOptionPane.showMessageDialog(internalFrame, e1.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchButton.setBounds(274, 96, 120, 30);
		internalFrame.getContentPane().add(searchButton);

		final JButton closeButton = new JButton("Fechar");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				internalFrame.dispose();
			}
		});
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		closeButton.setBounds(401, 412, 120, 30);
		internalFrame.getContentPane().add(closeButton);

		final JButton deleteButton = new JButton("Deletar");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (client != null) {
					try {
						if (facade.delete(facade.simpleReadClient(formattedTextFieldCpf.getText()))) {
							clearFields(nameTextField, formattedTextFieldBirthday, textFieldEmail, streetTextField,
									numberTextField, formattedTextFieldTelefone, formattedTextFieldCep, cityTextField,
									neighborhoodTextField);
							client = null;
							JOptionPane.showMessageDialog(internalFrame, "Cliente deletado com sucesso");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Cliente não pôde ser deletado");
				}
			}
		});
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deleteButton.setBounds(141, 412, 120, 30);
		internalFrame.getContentPane().add(deleteButton);

		final JButton btnChange = new JButton("Alterar");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (client != null) {
					internalFrame.setVisible(false);
					changeScreen(clientSearchLabel, nameTextField, formattedTextFieldBirthday, dateChooserBirthday,
							formattedTextFieldCpf, textFieldEmail, streetTextField, cityTextField,
							neighborhoodTextField, numberTextField, formattedTextFieldTelefone, formattedTextFieldCep,
							searchButton, closeButton, deleteButton, btnChange);

					internalFrame.setVisible(true);

					final JButton btnConfirm = new JButton("Confirmar");
					btnConfirm.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							Client newClient = new Client(nameTextField.getText(), formattedTextFieldCpf.getText(),
									dateChooserBirthday.getCalendar(), formattedTextFieldTelefone.getText(),
									textFieldEmail.getText(),
									new Adress(streetTextField.getText(), cityTextField.getText(),
											neighborhoodTextField.getText(), numberTextField.getText(),
											formattedTextFieldCep.getText()));
							try {
								if (facade.update(newClient)) {
									newClientInformationScreen(clientSearchLabel, nameTextField,
											formattedTextFieldBirthday, textFieldEmail, streetTextField,
											cityTextField, neighborhoodTextField, numberTextField,
											formattedTextFieldTelefone, formattedTextFieldCep, btnConfirm, newClient);
								} else {
									JOptionPane.showMessageDialog(null, "Falha ao atualizar");
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
					btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
					btnConfirm.setBounds(btnChange.getBounds());
					internalFrame.getContentPane().add(btnConfirm);

					final JButton backButton = new JButton("Voltar");
					backButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							searchScreen(clientSearchLabel, nameTextField, formattedTextFieldBirthday,
									dateChooserBirthday, formattedTextFieldCpf, textFieldEmail, streetTextField,
									cityTextField, neighborhoodTextField, numberTextField, formattedTextFieldTelefone,
									formattedTextFieldCep, searchButton, closeButton, deleteButton, btnChange, btnConfirm,
									backButton);

						}

					});
					backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
					backButton.setBounds(closeButton.getBounds());
					internalFrame.getContentPane().add(backButton);

				} else {
					JOptionPane.showMessageDialog(null, "Defina um cliente cadastrado primeiro");
				}
			}
		});
		btnChange.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChange.setBounds(271, 412, 120, 30);
		internalFrame.getContentPane().add(btnChange);

		JButton btnListAll = new JButton("Listar Todos");
		btnListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				completeList(facade, desktopPane);
			}
		});
		btnListAll.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListAll.setBounds(401, 96, 120, 30);
		internalFrame.getContentPane().add(btnListAll);

		internalFrame.setVisible(true);
		try {
			internalFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	private void searchScreen(final JLabel clientSearchLabel, final JTextField nameTextField,
			final JFormattedTextField formattedTextFieldBirthday, final JDateChooser dateChooserBirthday,
			final JFormattedTextField formattedTextFieldCpf, final JTextField textFieldEmail,
			final JTextField publicPlaceTextField, final JTextField cityTextField, final JTextField neighborhoodTextField,
			final JTextField numberTextField, final JFormattedTextField formattedTextFieldTelefone,
			final JFormattedTextField formattedTextFieldCep, final JButton searchButton, final JButton closeButton,
			final JButton deleteButton, final JButton changeButton, final JButton btnConfirm, final JButton backButton) {
			btnConfirm.setVisible(false);
			backButton.setVisible(false);
	
			changeButton.setVisible(true);
			deleteButton.setVisible(true);
			closeButton.setVisible(true);
			searchButton.setVisible(true);
	
			clientSearchLabel.setText("Buscar Cliente");
	
			formattedTextFieldCpf.setEditable(true);
	
			nameTextField.setEditable(false);
	
			formattedTextFieldBirthday.setVisible(true);
			dateChooserBirthday.setVisible(false);
	
			formattedTextFieldTelefone.setEditable(false);
			textFieldEmail.setEditable(false);
			publicPlaceTextField.setEditable(false);
			numberTextField.setEditable(false);
			formattedTextFieldCep.setEditable(false);
			cityTextField.setEditable(false);
			neighborhoodTextField.setEditable(false);

		fillFields(nameTextField, formattedTextFieldBirthday, textFieldEmail, publicPlaceTextField, numberTextField,
				formattedTextFieldTelefone, formattedTextFieldCep, cityTextField, neighborhoodTextField);
	}

	private void fillFields(final JTextField nameTextField, final JFormattedTextField formattedTextFieldBirthday,
			final JTextField textFieldEmail, final JTextField publicPlaceTextField, final JTextField numberTextField,
			final JFormattedTextField formattedTextFieldTelefone, final JFormattedTextField formattedTextFieldCep,
			final JTextField cityTextField, final JTextField neighborhoodTextField) {
			nameTextField.setText(client.getName());
			formattedTextFieldBirthday.setText(dateFormat.format(client.getBirthday().getTime()));
			formattedTextFieldTelefone.setText(client.getPhoneNumber());
			textFieldEmail.setText(client.getEmail());
			publicPlaceTextField.setText(client.getAdress().getPublicPlace());
			numberTextField.setText(client.getAdress().getnumber());
			formattedTextFieldCep.setText(client.getAdress().getCep());
			cityTextField.setText(client.getAdress().getCity());
			neighborhoodTextField.setText(client.getAdress().getNeighborhood());
	}

	private void newClientInformationScreen(final JLabel clientSearchLabel, final JTextField nameTextField,
			final JFormattedTextField formattedTextFieldBirthday, final JTextField textFieldEmail,
			final JTextField publicPlaceTextField, final JTextField cityTextField, final JTextField neighborhoodTextField,
			final JTextField numberTextField, final JFormattedTextField formattedTextFieldTelefone,
			final JFormattedTextField formattedTextFieldCep, final JButton btnConfirmar, Client newCliete) {
			client = newCliete;
			clientSearchLabel.setText("Informações atualizadas do cliente");
			btnConfirmar.setVisible(false);

			nameTextField.setEditable(false);

			formattedTextFieldBirthday.setVisible(true);

			formattedTextFieldTelefone.setEditable(false);
			textFieldEmail.setEditable(false);
			publicPlaceTextField.setEditable(false);
			numberTextField.setEditable(false);
			formattedTextFieldCep.setEditable(false);
			cityTextField.setEditable(false);
			neighborhoodTextField.setEditable(false);

		JOptionPane.showMessageDialog(null, "Atualizado com sucesso");

		fillFields(nameTextField, formattedTextFieldBirthday, textFieldEmail, publicPlaceTextField, numberTextField,
				formattedTextFieldTelefone, formattedTextFieldCep, cityTextField, neighborhoodTextField);
	}

	private void changeScreen(final JLabel clientSearchLabel, final JTextField nameTextField,
			final JFormattedTextField formattedTextFieldBirthday, final JDateChooser dateChooserBirthday,
			final JFormattedTextField formattedTextFieldCpf, final JTextField textFieldEmail,
			final JTextField publicPlaceTextField, final JTextField cityTextField, final JTextField neighborhoodTextField,
			final JTextField numberTextField, final JFormattedTextField formattedTextFieldTelefone,
			final JFormattedTextField formattedTextFieldCep, final JButton searchButton, final JButton closeButton,
			final JButton deleteButton, final JButton changeButton) {
			clientSearchLabel.setText("Alterar Cliente");

		nameTextField.setEditable(true);

		formattedTextFieldBirthday.setVisible(false);
		dateChooserBirthday.setCalendar(client.getBirthday());
		dateChooserBirthday.setVisible(true);

		formattedTextFieldTelefone.setEditable(true);
		textFieldEmail.setEditable(true);
		publicPlaceTextField.setEditable(true);
		numberTextField.setEditable(true);
		formattedTextFieldCep.setEditable(true);
		cityTextField.setEditable(true);
		neighborhoodTextField.setEditable(true);

		formattedTextFieldCpf.setEditable(false);

		changeButton.setVisible(false);
		deleteButton.setVisible(false);
		closeButton.setVisible(false);
		searchButton.setVisible(false);
	}

	private void clearFields(final JTextField nameTextField, final JFormattedTextField formattedTextFieldBirthday,
			final JTextField textFieldEmail, final JTextField publicPlaceTextField, final JTextField numberTextField,
			final JFormattedTextField formattedTextFieldTelefone, final JFormattedTextField formattedTextFieldCep,
			final JTextField cityTextField, final JTextField neighborhoodTextField) {
		nameTextField.setText("");
		formattedTextFieldBirthday.setText("");
		formattedTextFieldTelefone.setText("");
		textFieldEmail.setText("");
		publicPlaceTextField.setText("");
		numberTextField.setText("");
		formattedTextFieldCep.setText("");
		cityTextField.setText("");
		neighborhoodTextField.setText("");
	}
}