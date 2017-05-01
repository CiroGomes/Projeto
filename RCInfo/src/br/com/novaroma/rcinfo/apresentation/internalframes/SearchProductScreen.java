package br.com.novaroma.rcinfo.apresentation.internalframes;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import br.com.novaroma.rcinfo.entities.Product;
import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidAgeException;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidEmailException;
import br.com.novaroma.rcinfo.exceptions.NotFoundException;
import br.com.novaroma.rcinfo.facade.Facade;

public class SearchProductScreen implements InternalFrames {
	private JInternalFrame internalFrame;
	Product product = null;

	public JInternalFrame getInternalFrame() {
		return internalFrame;
	}

	public void setInternalFrame(JInternalFrame internalFrame) {
		this.internalFrame = internalFrame;
	}

	private void completeList(final Facade fachada, final JDesktopPane desktopPane) {
		JInternalFrame completeList = new JInternalFrame();
		completeList.setClosable(true);
		completeList.setMaximizable(true);
		completeList.setResizable(true);
		completeList.setIconifiable(true);
		ArrayList<Product> listProducts = new ArrayList<>(fachada.getMapProduct().values());
		String[] arrayProducts = new String[listProducts.size()];
		for (int i = 0; i < arrayProducts.length; i++) {
			arrayProducts[i] = listProducts.get(i).getCode() + ": " + listProducts.get(i).getType() + " "
					+ listProducts.get(i).getModel() + " - " + listProducts.get(i).getManufacturer();
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JList<String[]> list = new JList(arrayProducts);
		list.setBounds(485, 116, 116, 105);
		JScrollPane scrollPaneTest = new JScrollPane(list);

		completeList.setBounds(550, 0, 260, 482);
		scrollPaneTest.setBounds(completeList.getBounds());
		scrollPaneTest.setVisible(true);
		completeList.getContentPane().add(scrollPaneTest);
		completeList.setVisible(true);
		desktopPane.add(completeList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SearchProductScreen(final Facade facade, String employeeCodeOn, final JDesktopPane desktopPane)
			throws EmptyFieldException, InvalidCharactersFieldException, NotFoundException {
		try {
			facade.readSerializedFile();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		internalFrame = new JInternalFrame();
		internalFrame.setTitle("Buscar Produto");
		internalFrame.setClosable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setBounds(0, 0, 550, 482);
		internalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		internalFrame.getContentPane().setLayout(null);

		final JLabel productSearchLabel = new JLabel("Buscar Produto");
		productSearchLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		productSearchLabel.setBounds(31, 79, 400, 39);
		internalFrame.getContentPane().add(productSearchLabel);

		JLabel codeLabel = new JLabel("Código:");
		codeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		codeLabel.setBounds(41, 129, 77, 30);
		internalFrame.getContentPane().add(codeLabel);

		final JTextField codeTextField = new JTextField();
		codeTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		codeTextField.setBounds(119, 129, 145, 30);
		codeTextField.setColumns(10);
		internalFrame.getContentPane().add(codeTextField);

		JLabel nameLabel = new JLabel("Nome: ");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameLabel.setBounds(41, 170, 77, 30);
		internalFrame.getContentPane().add(nameLabel);

		final JTextField nameTextField = new JTextField();
		nameTextField.setEditable(false);
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameTextField.setColumns(10);
		nameTextField.setBounds(119, 170, 405, 30);
		internalFrame.getContentPane().add(nameTextField);

		JLabel priceLabel = new JLabel("Preço: ");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		priceLabel.setBounds(41, 252, 77, 30);
		internalFrame.getContentPane().add(priceLabel);

		final JTextField priceTextField = new JTextField();
		priceTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		priceTextField.setEditable(false);
		priceTextField.setColumns(10);
		priceTextField.setBounds(119, 252, 145, 30);
		internalFrame.getContentPane().add(priceTextField);

		JLabel amountLabel = new JLabel("Quantidade: ");
		amountLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		amountLabel.setBounds(274, 211, 93, 30);
		internalFrame.getContentPane().add(amountLabel);

		final JTextField amountTextField = new JTextField();
		amountTextField.setEditable(false);
		amountTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		amountTextField.setColumns(10);
		amountTextField.setBounds(367, 211, 157, 30);
		internalFrame.getContentPane().add(amountTextField);

		JLabel lblFabricante = new JLabel("Fabricante: ");
		lblFabricante.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFabricante.setBounds(41, 293, 77, 30);
		internalFrame.getContentPane().add(lblFabricante);

		final JTextField manufacturerTextField = new JTextField();
		manufacturerTextField.setEditable(false);
		manufacturerTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		manufacturerTextField.setColumns(10);
		manufacturerTextField.setBounds(119, 293, 145, 30);
		internalFrame.getContentPane().add(manufacturerTextField);

		JLabel modelLabel = new JLabel("Modelo: ");
		modelLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		modelLabel.setBounds(274, 252, 63, 30);
		internalFrame.getContentPane().add(modelLabel);

		final JTextField modelTextField = new JTextField();
		modelTextField.setEditable(false);
		modelTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		modelTextField.setColumns(10);
		modelTextField.setBounds(338, 252, 186, 30);
		internalFrame.getContentPane().add(modelTextField);

		JLabel typeLabel = new JLabel("Tipo: ");
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		typeLabel.setBounds(41, 211, 77, 30);
		internalFrame.getContentPane().add(typeLabel);

		final JComboBox typeComboBox = new JComboBox();
		typeComboBox.setEnabled(false);
		typeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		typeComboBox.setModel(new DefaultComboBoxModel(new String[] { "Tipo do Produto", "Placa-Mãe",
				"Processador", "Memória", "Gabinete", "HD", "Placa De Vídeo", "Fonte", "Placa De Rede",
				"Cooler", "SSD", "Drive De DVD", "Placa de Som", "Placa De Captura", "Headset", "Teclado", "Mouse",
				"Monitor", "Adaptadores", "HD Externo", "Pen-Drive" }));
		typeComboBox.setBounds(119, 211, 145, 30);
		internalFrame.getContentPane().add(typeComboBox);

		final JButton searchButton = new JButton("Buscar");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (facade.simpleReadProduct(codeTextField.getText()) != null) {
						product = facade.simpleReadProduct(codeTextField.getText());
						nameTextField.setText(product.getType() + " " + product.getModel() + " - " + product.getManufacturer());
						priceTextField.setText(product.getPrice());
						manufacturerTextField.setText(product.getManufacturer());
						modelTextField.setText(product.getModel());
						typeComboBox.setSelectedItem(product.getType());
						amountTextField.setText(String.valueOf(product.getAmount()));
					} else {
						JOptionPane.showMessageDialog(internalFrame, "Produto não encontrado");
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (EmptyFieldException | InvalidCharactersFieldException e1) {
					JOptionPane.showMessageDialog(internalFrame, e1.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchButton.setBounds(274, 129, 120, 30);
		internalFrame.getContentPane().add(searchButton);

		final JButton closeButton = new JButton("Fechar");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				internalFrame.dispose();
			}
		});
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		closeButton.setBounds(404, 334, 120, 30);
		internalFrame.getContentPane().add(closeButton);

		final JButton deleteButton = new JButton("Deletar");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (product != null) {
					try {
						if (facade.delete(facade.simpleReadProduct(codeTextField.getText()))) {
							nameTextField.setText("");
							priceTextField.setText("");
							amountTextField.setText("");
							codeTextField.setText("");
							manufacturerTextField.setText("");
							modelTextField.setText("");
							typeComboBox.setSelectedIndex(0);
							product = null;
							JOptionPane.showMessageDialog(null, "Produto deletado com sucesso");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Produto não pôde ser deletado");
				}
			}
		});
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deleteButton.setBounds(160, 334, 104, 30);
		if (facade.simpleReadEmployee(employeeCodeOn).getOffice() > 0) {
			internalFrame.getContentPane().add(deleteButton);
		}

		final JButton changeButton = new JButton("Alterar");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (product != null) {
					internalFrame.setVisible(false);
					productSearchLabel.setText("Alterar Produto");

					priceTextField.setEditable(true);
					manufacturerTextField.setEditable(true);
					modelTextField.setEditable(true);
					modelTextField.setEditable(true);
					amountTextField.setEditable(true);
					typeComboBox.setEnabled(true);

					codeTextField.setEditable(false);

					changeButton.setVisible(false);
					deleteButton.setVisible(false);
					closeButton.setVisible(false);
					searchButton.setVisible(false);

					internalFrame.setVisible(true);

					final JButton confirmButton = new JButton("Confirmar");
					confirmButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Product newProduct = new Product((String) typeComboBox.getSelectedItem(),
									codeTextField.getText(), modelTextField.getText(), manufacturerTextField.getText(),
									priceTextField.getText(), amountTextField.getText());
							try {
								if (facade.update(newProduct)) {
									product = newProduct;
									productSearchLabel.setText("Informações atualizadas do produto");
									confirmButton.setVisible(false);

									priceTextField.setEditable(false);
									manufacturerTextField.setEditable(false);
									modelTextField.setEditable(false);
									modelTextField.setEditable(false);
									amountTextField.setEditable(false);
									typeComboBox.setEnabled(false);

									JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
									nameTextField.setText(product.getType() + " " + product.getModel() + " - " + product.getManufacturer());
									priceTextField.setText(product.getPrice());
									manufacturerTextField.setText(product.getManufacturer());
									modelTextField.setText(product.getModel());
									typeComboBox.setSelectedItem(product.getType());
									amountTextField.setText(String.valueOf(product.getAmount()));
								} else {
									JOptionPane.showMessageDialog(null, "Falha ao atualizar");
								}
							} catch (HeadlessException e1) {
								e1.printStackTrace();
							} catch (InvalidCharactersFieldException | EmptyFieldException | InvalidAgeException
									| InvalidEmailException e1) {
								JOptionPane.showMessageDialog(internalFrame, e1.getMessage(), "Alerta",	JOptionPane.WARNING_MESSAGE);
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

							productSearchLabel.setText("Buscar Produto");

							codeTextField.setEditable(true);
							priceTextField.setEditable(false);
							manufacturerTextField.setEditable(false);
							modelTextField.setEditable(false);
							modelTextField.setEditable(false);
							amountTextField.setEditable(false);
							typeComboBox.setEnabled(false);

							nameTextField.setText(product.getType() + " " + product.getModel() + " - " + product.getManufacturer());
							priceTextField.setText(product.getPrice());
							manufacturerTextField.setText(product.getManufacturer());
							modelTextField.setText(product.getModel());
							typeComboBox.setSelectedItem(product.getType());
							amountTextField.setText(String.valueOf(product.getAmount()));

						}
					});
					backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
					backButton.setBounds(closeButton.getBounds());
					internalFrame.getContentPane().add(backButton);

				} else {
					JOptionPane.showMessageDialog(null, "Defina um produto cadastrado primeiro");
				}
			}
		});
		changeButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		changeButton.setBounds(274, 334, 120, 30);
		internalFrame.getContentPane().add(changeButton);

		JButton listAllButton = new JButton("Listar Todos");
		listAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				completeList(facade, desktopPane);
			}
		});
		listAllButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		listAllButton.setBounds(404, 129, 120, 30);
		internalFrame.getContentPane().add(listAllButton);
		internalFrame.setVisible(true);
	}
}