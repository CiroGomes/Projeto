package br.com.novaroma.rcinfo.apresentation.internalframes;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.novaroma.rcinfo.entities.Product;
import br.com.novaroma.rcinfo.facade.Facade;
import br.com.novaroma.rcinfo.utils.Utils;

public class CreateProductScreen implements InternalFrames {
	JInternalFrame internalFrame;

	public JInternalFrame getInternalFrame() {
		return internalFrame;
	}

	public void setInternalFrame(JInternalFrame internalFrame) {
		this.internalFrame = internalFrame;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CreateProductScreen(final Facade facade) {
		internalFrame = new JInternalFrame();
		internalFrame.setBounds(0, 60, 550, 482);
		internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		internalFrame.getContentPane().setLayout(null);

		JLabel productRegisterLabel = new JLabel("Cadastrar Produto");
		productRegisterLabel.setBounds(10, 110, 189, 30);
		productRegisterLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		internalFrame.getContentPane().add(productRegisterLabel);

		final JComboBox typeComboBox = new JComboBox();
		typeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		typeComboBox.setModel(new DefaultComboBoxModel(new String[] { "Tipo do produto", "Placa-Mãe",
				"Processador", "Memória", "Gabinete", "HD", "Placa De Vídeo", "Fonte", "Placa De Rede",
				"Cooler", "SSD", "Drive De DVD", "Placa de Som", "Placa De Captura", "Headset", "Teclado", "Mouse",
				"Monitor", "Adaptadores", "HD Externo", "Pen-Drive" }));
		typeComboBox.setBounds(383, 164, 136, 30);
		internalFrame.getContentPane().add(typeComboBox);

		JLabel priceLabel = new JLabel("Preço :");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		priceLabel.setBounds(10, 245, 67, 30);
		internalFrame.getContentPane().add(priceLabel);

		final JTextField priceTextField = new JTextField();
		priceTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		priceTextField.setBounds(74, 246, 108, 30);
		internalFrame.getContentPane().add(priceTextField);
		priceTextField.setColumns(10);

		JLabel amountLabel = new JLabel("Quantidade: ");
		amountLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		amountLabel.setBounds(10, 205, 94, 30);
		internalFrame.getContentPane().add(amountLabel);

		final JTextField amountTextField = new JTextField();
		amountTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		amountTextField.setColumns(10);
		amountTextField.setBounds(102, 206, 80, 30);
		internalFrame.getContentPane().add(amountTextField);

		JLabel modelLabel = new JLabel("Modelo: ");
		modelLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		modelLabel.setBounds(220, 245, 60, 30);
		internalFrame.getContentPane().add(modelLabel);

		final JTextField modelTextField = new JTextField();
		modelTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		modelTextField.setColumns(10);
		modelTextField.setBounds(302, 245, 217, 30);
		internalFrame.getContentPane().add(modelTextField);

		JLabel codeLabel = new JLabel("Código: ");
		codeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		codeLabel.setBounds(10, 164, 60, 30);
		internalFrame.getContentPane().add(codeLabel);

		final JTextField codeTextField = new JTextField();
		codeTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		codeTextField.setEditable(false);
		codeTextField.setColumns(10);
		codeTextField.setBounds(74, 164, 108, 30);
		internalFrame.getContentPane().add(codeTextField);

		JButton codeGenerateButton = new JButton("Gerar Código");
		codeGenerateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				codeTextField.setText(Utils.generateCode(facade.getMapProduct()) + "");
			}
		});
		codeGenerateButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		codeGenerateButton.setBounds(192, 164, 117, 30);
		internalFrame.getContentPane().add(codeGenerateButton);

		JLabel brandLabel = new JLabel("Fabricante: ");
		brandLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		brandLabel.setBounds(220, 205, 80, 30);
		internalFrame.getContentPane().add(brandLabel);

		final JTextField manufacturerTextField = new JTextField();
		manufacturerTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		manufacturerTextField.setColumns(10);
		manufacturerTextField.setBounds(302, 205, 217, 30);
		internalFrame.getContentPane().add(manufacturerTextField);

		JButton registerButton = new JButton("Cadastrar");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (facade.create(new Product(((String) typeComboBox.getSelectedItem()), codeTextField.getText(),
							modelTextField.getText(), manufacturerTextField.getText(), priceTextField.getText(),
							amountTextField.getText()))) {
						clearFields(typeComboBox, priceTextField, amountTextField, modelTextField, codeTextField,
								manufacturerTextField);
						JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível cadastrar este produto");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(internalFrame, e1.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
				}
			}

			private void clearFields(final JComboBox typeComboBox, final JTextField priceTextField,
					final JTextField amountTextField, final JTextField modelTextField,
					final JTextField codeTextField, final JTextField manufacturerTextField) {
				typeComboBox.setSelectedIndex(0);
				codeTextField.setText("");
				modelTextField.setText("");
				manufacturerTextField.setText("");
				priceTextField.setText("");
				amountTextField.setText("");
			}
		});
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registerButton.setBounds(275, 286, 117, 30);
		internalFrame.getContentPane().add(registerButton);

		JButton closeButton = new JButton("Fechar");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFrame.dispose();
			}
		});
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		closeButton.setBounds(402, 286, 117, 30);

		internalFrame.getContentPane().add(closeButton);

		JLabel typeLabel = new JLabel("Tipo: ");
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		typeLabel.setBounds(319, 164, 60, 30);
		internalFrame.getContentPane().add(typeLabel);

		internalFrame.setTitle("Cadastro Produto");
		internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		internalFrame.setClosable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setVisible(true);
	}
}