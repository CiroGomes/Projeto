package br.com.novaroma.rcinfo.apresentation.internalframes;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import br.com.novaroma.rcinfo.entities.Adress;
import br.com.novaroma.rcinfo.entities.Client;
import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.ExistingEntityException;
import br.com.novaroma.rcinfo.exceptions.InvalidAgeException;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidEmailException;
import br.com.novaroma.rcinfo.facade.Facade;

public class CreateClientScreen implements InternalFrames {
	JInternalFrame internalFrame;
	JDesktopPane desktopPane;

	public JInternalFrame getInternalFrame() {
		return internalFrame;
	}

	public void setInternalFrame(JInternalFrame internalFrame) {
		this.internalFrame = internalFrame;
	}

	public CreateClientScreen(final Facade facade) {
		internalFrame = new JInternalFrame();
		internalFrame.setTitle("Cadastro Cliente");
		internalFrame.setBounds(0, 40, 550, 482);
		internalFrame.getContentPane().setLayout(null);

		MaskFormatter cpfMask = null;
		MaskFormatter phoneNumberMask = null;
		MaskFormatter zipCodeMask = null;
		try {
			cpfMask = new MaskFormatter("###.###.###-##");
			phoneNumberMask = new MaskFormatter("(##)#####-####");
			zipCodeMask = new MaskFormatter("##.###-###");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cpfMask.setPlaceholderCharacter('_');
		phoneNumberMask.setPlaceholderCharacter('_');
		zipCodeMask.setPlaceholderCharacter('_');

		JLabel clientRegisterLabel = new JLabel("Cadastro de Cliente");
		clientRegisterLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		clientRegisterLabel.setBounds(10, 76, 211, 27);
		internalFrame.getContentPane().add(clientRegisterLabel);

		JLabel nameLabel = new JLabel("Nome Completo: ");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setBounds(33, 114, 140, 30);
		internalFrame.getContentPane().add(nameLabel);

		final JTextField nameTextField = new JTextField();
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameTextField.setBounds(172, 114, 352, 30);
		internalFrame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);

		JLabel birthdayLabel = new JLabel("Data de Nascimento:  ");
		birthdayLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		birthdayLabel.setBounds(33, 155, 140, 30);
		internalFrame.getContentPane().add(birthdayLabel);

		JLabel cpfLabel = new JLabel("CPF: ");
		cpfLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cpfLabel.setBounds(319, 155, 43, 30);
		internalFrame.getContentPane().add(cpfLabel);

		final JFormattedTextField cpfTextFieldFormatted = new JFormattedTextField(cpfMask);
		cpfTextFieldFormatted.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cpfTextFieldFormatted.setSize(148, 30);
		cpfTextFieldFormatted.setLocation(376, 155);
		internalFrame.getContentPane().add(cpfTextFieldFormatted);

		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailLabel.setBounds(33, 237, 140, 30);
		internalFrame.getContentPane().add(emailLabel);

		final JTextField emailTextField = new JTextField();
		emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailTextField.setColumns(10);
		emailTextField.setBounds(172, 237, 352, 30);
		internalFrame.getContentPane().add(emailTextField);

		JLabel streetLabel = new JLabel("Rua:");
		streetLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		streetLabel.setBounds(33, 278, 140, 30);
		internalFrame.getContentPane().add(streetLabel);

		final JTextField streetTextField = new JTextField();
		streetTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		streetTextField.setColumns(10);
		streetTextField.setBounds(172, 278, 352, 30);
		internalFrame.getContentPane().add(streetTextField);

		JLabel numberLabel = new JLabel("N\u00FAmero: ");
		numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numberLabel.setBounds(33, 358, 69, 30);
		internalFrame.getContentPane().add(numberLabel);

		final JTextField numberTextField = new JTextField();
		numberTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numberTextField.setColumns(10);
		numberTextField.setBounds(172, 358, 133, 30);
		internalFrame.getContentPane().add(numberTextField);

		JLabel phoneNumberLabel = new JLabel("Telefone: ");
		phoneNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phoneNumberLabel.setBounds(33, 196, 140, 30);
		internalFrame.getContentPane().add(phoneNumberLabel);

		final JFormattedTextField phoneNumberTextFieldFormatted = new JFormattedTextField(phoneNumberMask);
		phoneNumberTextFieldFormatted.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phoneNumberTextFieldFormatted.setColumns(10);
		phoneNumberTextFieldFormatted.setBounds(172, 196, 133, 30);
		internalFrame.getContentPane().add(phoneNumberTextFieldFormatted);

		JLabel zipCodeLabel = new JLabel("CEP:");
		zipCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		zipCodeLabel.setBounds(319, 358, 43, 30);
		internalFrame.getContentPane().add(zipCodeLabel);

		final JFormattedTextField cepTextFieldFormatted = new JFormattedTextField(zipCodeMask);
		cepTextFieldFormatted.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cepTextFieldFormatted.setBounds(387, 359, 137, 30);
		internalFrame.getContentPane().add(cepTextFieldFormatted);

		final JDateChooser dateChooserBirthday = new JDateChooser();
		dateChooserBirthday.setBounds(172, 155, 133, 30);
		internalFrame.getContentPane().add(dateChooserBirthday);

		JLabel cityLabel = new JLabel("Cidade:");
		cityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cityLabel.setBounds(33, 319, 69, 30);
		internalFrame.getContentPane().add(cityLabel);

		final JTextField cityTextField = new JTextField();
		cityTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cityTextField.setColumns(10);
		cityTextField.setBounds(172, 319, 133, 30);
		internalFrame.getContentPane().add(cityTextField);

		JLabel neighborhoodLabel = new JLabel("Bairro: ");
		neighborhoodLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		neighborhoodLabel.setBounds(319, 319, 69, 30);
		internalFrame.getContentPane().add(neighborhoodLabel);

		final JTextField neighborhoodTextField = new JTextField();
		neighborhoodTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		neighborhoodTextField.setColumns(10);
		neighborhoodTextField.setBounds(391, 319, 133, 30);
		internalFrame.getContentPane().add(neighborhoodTextField);

		JButton registerButton = new JButton("Cadastrar");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		registerButton.setBounds(322, 399, 96, 30);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (facade.create(new Client(nameTextField.getText(), cpfTextFieldFormatted.getText(),
							dateChooserBirthday.getCalendar(), phoneNumberTextFieldFormatted.getText(),
							emailTextField.getText(),
							new Adress(streetTextField.getText(), cityTextField.getText(),
									neighborhoodTextField.getText(), numberTextField.getText(),
									cepTextFieldFormatted.getText())))) {
						JOptionPane.showMessageDialog(internalFrame, "Cliente criado com sucesso");
						internalFrame.dispose();

					} else {
						JOptionPane.showMessageDialog(internalFrame, "Cliente não cadastrado");
					}
				} catch (ExistingEntityException | EmptyFieldException | InvalidCharactersFieldException	| InvalidAgeException | InvalidEmailException e) {
					JOptionPane.showMessageDialog(internalFrame, e.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		internalFrame.getContentPane().add(registerButton);

		JButton closeButton = new JButton("Fechar");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				internalFrame.dispose();
			}
		});
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		closeButton.setBounds(428, 399, 96, 30);
		internalFrame.getContentPane().add(closeButton);

		internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		internalFrame.setClosable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setVisible(true);
		try {
			internalFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
}
