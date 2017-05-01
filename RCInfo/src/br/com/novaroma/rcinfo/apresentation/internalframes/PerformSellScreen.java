package br.com.novaroma.rcinfo.apresentation.internalframes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.novaroma.rcinfo.entities.Product;
import br.com.novaroma.rcinfo.entities.Sell;
import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.ExistingEntityException;
import br.com.novaroma.rcinfo.exceptions.InvalidAgeException;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidEmailException;
import br.com.novaroma.rcinfo.exceptions.NotFoundException;
import br.com.novaroma.rcinfo.facade.Facade;
import br.com.novaroma.rcinfo.utils.Utils;

public class PerformSellScreen implements InternalFrames {
	JInternalFrame internalFrame;
	private final JInternalFrame taxCouponScreen = new JInternalFrame();
	JScrollPane scrollPane;
	String[][] matriz;
	Product product = null;
	DecimalFormat decimalFormat = new DecimalFormat("0.00");

	public JInternalFrame getInternalFrame() {
		return internalFrame;
	}

	public void setInternalFrame(JInternalFrame internalFrame) {
		this.internalFrame = internalFrame;
	}

	public JInternalFrame getTaxCouponScreen() {
		return taxCouponScreen;
	}

	private void tableGridPersonalize(final JTable table) {
		table.getColumnModel().getColumn(0).setPreferredWidth(57);
		table.getColumnModel().getColumn(1).setPreferredWidth(43);
		table.getColumnModel().getColumn(2).setPreferredWidth(368);
		table.getColumnModel().getColumn(3).setPreferredWidth(65);
		table.getColumnModel().getColumn(4).setPreferredWidth(65);
	}

	public PerformSellScreen(final Facade facade, final JDesktopPane desktopPane) {
		try {
			facade.readSerializedFile();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		taxCouponScreen.setVisible(false);
		internalFrame = new JInternalFrame();
		internalFrame.getContentPane().setBackground(Color.WHITE);
		internalFrame.setTitle("Realizar Venda");
		internalFrame.setClosable(true);
		internalFrame.setMaximizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setBounds(10, 10, 800, 673);
		internalFrame.getContentPane().setLayout(null);

		JLabel productNameLabel = new JLabel("Nome do Produto: ");
		productNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		productNameLabel.setBounds(61, 151, 145, 30);
		internalFrame.getContentPane().add(productNameLabel);

		final JTextField productNameTextField = new JTextField();
		productNameTextField.setEditable(false);
		productNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		productNameTextField.setColumns(10);
		productNameTextField.setBounds(197, 151, 540, 30);
		internalFrame.getContentPane().add(productNameTextField);

		JLabel performSellLabel = new JLabel("Realizar Venda");
		performSellLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		performSellLabel.setBounds(34, 66, 159, 20);
		internalFrame.getContentPane().add(performSellLabel);

		JLabel amountLabel = new JLabel("Quantidade :");
		amountLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		amountLabel.setBounds(392, 192, 92, 30);
		internalFrame.getContentPane().add(amountLabel);

		final JTextField amountTextField = new JTextField();
		amountTextField.setBackground(Color.WHITE);
		amountTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		amountTextField.setBounds(494, 192, 130, 30);
		internalFrame.getContentPane().add(amountTextField);
		amountTextField.setColumns(10);

		JLabel codeLabel = new JLabel("Código:");
		codeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		codeLabel.setBounds(61, 110, 130, 30);
		internalFrame.getContentPane().add(codeLabel);

		final JTextField codeTextField = new JTextField();
		codeTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		codeTextField.setBounds(197, 110, 130, 30);
		internalFrame.getContentPane().add(codeTextField);
		codeTextField.setColumns(10);

		final ArrayList<Product> arrayListProduct = new ArrayList<>();
		final String[] guide = new String[] { "Quantidade", "Código", "Produto", "Preço Unitário", "Subtotal" };
		matriz = new String[arrayListProduct.size()][guide.length];
		final JTable table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(new String[arrayListProduct.size()][5], guide));
		tableGridPersonalize(table);
		table.setShowVerticalLines(false);
		table.setBackground(null);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(61, 233, 676, 225);
		internalFrame.getContentPane().add(scrollPane);

		final JTextField avalaibleAmountTextField = new JTextField();
		avalaibleAmountTextField.setEditable(false);
		avalaibleAmountTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		avalaibleAmountTextField.setColumns(10);
		avalaibleAmountTextField.setBounds(197, 192, 130, 30);
		internalFrame.getContentPane().add(avalaibleAmountTextField);

		JLabel subtotalLabel = new JLabel("Subtotal: ");
		subtotalLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		subtotalLabel.setBounds(532, 469, 92, 30);
		internalFrame.getContentPane().add(subtotalLabel);

		final JTextField subtotalTextField = new JTextField();
		subtotalTextField.setEditable(false);
		subtotalTextField.setBounds(634, 469, 103, 30);
		internalFrame.getContentPane().add(subtotalTextField);
		subtotalTextField.setColumns(10);

		final JTextField totalTextField = new JTextField();
		totalTextField.setEditable(false);
		totalTextField.setColumns(10);
		totalTextField.setBounds(634, 510, 103, 30);
		internalFrame.getContentPane().add(totalTextField);

		JButton searchButton = new JButton("Buscar");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					product = facade.simpleReadProduct(codeTextField.getText());
					if (product != null) {
						internalFrame.setVisible(false);
						productNameTextField.setText(product.getType() + " " + product.getModel() + " - " + product.getManufacturer());
						avalaibleAmountTextField.setText(product.getAmount() + "");

						JButton addButton = new JButton("Adicionar");
						addButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								preencherTable(amountTextField, arrayListProduct, guide, table, avalaibleAmountTextField,subtotalTextField, totalTextField);
							}
						});
						addButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
						addButton.setBounds(634, 192, 103, 30);
						internalFrame.getContentPane().add(addButton);
						internalFrame.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(internalFrame, "Produto inexistente");
					}
				} catch (EmptyFieldException | InvalidCharactersFieldException e1) {
					JOptionPane.showMessageDialog(internalFrame, e1.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchButton.setBounds(337, 110, 103, 30);
		internalFrame.getContentPane().add(searchButton);

		JLabel avalaibleAmountLabel = new JLabel("Em estoque: ");
		avalaibleAmountLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		avalaibleAmountLabel.setBounds(61, 192, 103, 30);
		internalFrame.getContentPane().add(avalaibleAmountLabel);

		JButton closeButton = new JButton("Fechar");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					internalFrame.dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		closeButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		closeButton.setBounds(576, 603, 161, 30);
		internalFrame.getContentPane().add(closeButton);

		JLabel cpfClientLabel = new JLabel("CPF do Cliente: ");
		cpfClientLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cpfClientLabel.setBounds(470, 551, 109, 30);
		internalFrame.getContentPane().add(cpfClientLabel);

		MaskFormatter cpfMask = null;
		try {
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cpfMask.setPlaceholderCharacter('_');

		final JFormattedTextField cpfTextFieldFormatted = new JFormattedTextField(cpfMask);
		cpfTextFieldFormatted.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cpfTextFieldFormatted.setBounds(589, 551, 148, 30);
		internalFrame.getContentPane().add(cpfTextFieldFormatted);

		JButton sellDeleteButton = new JButton("Cancelar Venda");
		sellDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearFields(productNameTextField, amountTextField, codeTextField, avalaibleAmountTextField,
						cpfTextFieldFormatted, subtotalTextField, totalTextField);
				clearTable(arrayListProduct, guide, table, subtotalTextField);
				try {
					facade.readSerializedFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		sellDeleteButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sellDeleteButton.setBounds(233, 603, 162, 30);
		internalFrame.getContentPane().add(sellDeleteButton);

		JButton sellPurchaseButton = new JButton("Finalizar Compra");
		sellPurchaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String protocol = String.valueOf(Utils.generateCode(facade.getMapSell()));
					if (facade.simpleReadClient(cpfTextFieldFormatted.getText()) != null
							&& facade.create(new Sell(protocol, matriz, cpfTextFieldFormatted.getText(),
									Calendar.getInstance(), subtotalTextField.getText().replaceAll(",", "."),
									totalTextField.getText().replaceAll(",", ".")))) {
						for (Product product : arrayListProduct) {
							facade.update(product);
						}
						internalFrame.dispose();
						taxCoupon(desktopPane, productNameTextField, amountTextField, codeTextField, arrayListProduct,
								guide, table, avalaibleAmountTextField, subtotalTextField, cpfTextFieldFormatted, protocol, totalTextField);
						JOptionPane.showMessageDialog(internalFrame, "Venda realizada com sucesso", "Venda realizada", JOptionPane.INFORMATION_MESSAGE);

					}
				} catch (ExistingEntityException | EmptyFieldException | InvalidCharactersFieldException | InvalidAgeException | InvalidEmailException | NotFoundException e1) {
					JOptionPane.showMessageDialog(internalFrame, e1.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});
		sellPurchaseButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sellPurchaseButton.setBounds(405, 603, 161, 30);
		internalFrame.getContentPane().add(sellPurchaseButton);

		JLabel totalLabel = new JLabel("Total: ");
		totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		totalLabel.setBounds(532, 510, 92, 30);
		internalFrame.getContentPane().add(totalLabel);
		internalFrame.setVisible(true);
	}

	private void clearTable(final ArrayList<Product> test, final String[] guide, final JTable table,
			final JTextField textFieldTotal) {
		test.clear();
		matriz = new String[test.size()][guide.length];
		table.setModel(new DefaultTableModel(matriz, guide));
		tableGridPersonalize(table);
		textFieldTotal.setText("");
	}

	private void clearFields(final JTextField productNameTextField, final JTextField amountTextField,
			final JTextField codeTextField, final JTextField avalaibleAmountTextField,
			final JFormattedTextField cpfTextFieldFormatted, final JTextField subtotalTextField, final JTextField totalTextField) {
		cpfTextFieldFormatted.setText("");
		codeTextField.setText("");
		productNameTextField.setText("");
		avalaibleAmountTextField.setText("");
		amountTextField.setText("");
		subtotalTextField.setText("");
		totalTextField.setText("");
	}

	private void preencherTable(final JTextField amountTextField, final ArrayList<Product> test, final String[] guide,
			final JTable table, final JTextField avalaibleAmountTextField, final JTextField subtotalTextField,
			final JTextField totalTextField) {

		boolean existingProduct = false;
		for (int i = 0; i < matriz.length; i++) {
			if (matriz[i][1].equals(product.getCode())) {
				existingProduct = true;
				break;
			}
		}

		if (amountTextField.getText().equals("") || amountTextField.getText().equals("0")) {
			JOptionPane.showMessageDialog(internalFrame, "Defina a quantidade maior que 0(zero)" + "");
		} else if (Integer.parseInt(amountTextField.getText()) > Integer.parseInt(product.getAmount())) {
			JOptionPane.showMessageDialog(internalFrame, "Quantidade requisitada excede estoque");
		} else if (existingProduct) {
			for (int i = 0; i < matriz.length; i++) {
				if (matriz[i][1].equals(product.getCode())) {
					matriz[i][0] = (Integer.parseInt(matriz[i][0]) + Integer.parseInt(amountTextField.getText())) + "";
					matriz[i][4] = decimalFormat.format(Double.parseDouble(product.getPrice()) * Integer.parseInt(matriz[i][0])) + "";
					break;
				}
			}
			changeTable(guide, table, avalaibleAmountTextField, subtotalTextField, amountTextField, totalTextField);
		} else {
			test.add(product);
			String[][] matrizStore = new String[test.size()][5];
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz[i].length; j++) {
					matrizStore[i][j] = matriz[i][j];
				}
			}
			matrizStore[test.size() - 1][0] = amountTextField.getText();
			matrizStore[test.size() - 1][1] = product.getCode();
			matrizStore[test.size() - 1][2] = product.getType() + " " + product.getModel() + " - " + product.getManufacturer();
			matrizStore[test.size() - 1][3] = product.getPrice();
			matrizStore[test.size() - 1][4] = decimalFormat.format(Double.parseDouble(product.getPrice()) * Integer.parseInt(matrizStore[test.size() - 1][0])) + "";

			matriz = matrizStore;
			changeTable(guide, table, avalaibleAmountTextField, subtotalTextField, amountTextField, totalTextField);
		}
	}

	private void changeTable(final String[] guide, final JTable table, final JTextField avalaibleAmountTextField,
			final JTextField subtotalTextField, final JTextField amountTextField, final JTextField totalTextField) {
		product.setAmount(Integer.parseInt(product.getAmount()) - Integer.parseInt(amountTextField.getText()) + "");
		table.setModel(new DefaultTableModel(matriz, guide));
		tableGridPersonalize(table);
		avalaibleAmountTextField.setText(product.getAmount() + "");
		double total = 0;
		for (int i = 0; i < matriz.length; i++) {
			total += Double.parseDouble(matriz[i][4].replaceAll(",", "."));
		}
		totalTextField.setText(decimalFormat.format(total * 1.18));
		subtotalTextField.setText(decimalFormat.format(total));
	}

	private void taxCoupon(final JDesktopPane desktopPane, final JTextField productNameTextField,
			final JTextField amountTextField, final JTextField codeTextField,
			final ArrayList<Product> arrayListProduct, final String[] guide, final JTable table,
			final JTextField avalaibleAmountTextField, final JTextField textFieldSubtotal,
			final JFormattedTextField cpfTextFieldFormatted, final String protocol, final JTextField textFieldTotal) {
		taxCouponScreen.getContentPane().setBackground(Color.WHITE);
		taxCouponScreen.getContentPane().setLayout(null);

		JTextArea topTextArea = new JTextArea();
		topTextArea.setFont(new Font("Calibri", Font.PLAIN, 13));
		topTextArea.setText("\t\tRC INFO LTDA\r\nEstrada do Bongi, 425 - Afogados - Recife - PE    CEP: 50830-260\r\nCNPJ: 25.275.381/0001-42\r\nIE: 0682193-69");
		topTextArea.setBounds(50, 0, 368, 72);
		topTextArea.setEditable(false);
		taxCouponScreen.getContentPane().add(topTextArea);

		JLabel lineLabel = new JLabel(
				"--------------------------------------------------------------------------------------------------------------------------");
		lineLabel.setBounds(0, 72, 488, 14);
		taxCouponScreen.getContentPane().add(lineLabel);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		JTextField dateTextField = new JTextField(sdf.format(Calendar.getInstance().getTime()));
		dateTextField.setBackground(Color.WHITE);
		dateTextField.setEditable(false);
		dateTextField.setFont(new Font("Calibri", Font.PLAIN, 13));
		dateTextField.setBounds(0, 85, 149, 20);
		dateTextField.setBorder(null);
		taxCouponScreen.getContentPane().add(dateTextField);
		dateTextField.setColumns(10);

		JLabel ccfLabel = new JLabel("CCF: 022631");
		ccfLabel.setFont(new Font("Calibri", Font.PLAIN, 13));
		ccfLabel.setBounds(201, 83, 81, 20);
		taxCouponScreen.getContentPane().add(ccfLabel);

		JTextField codeTextFieldProtocol = new JTextField(protocol);
		codeTextFieldProtocol.setBackground(Color.WHITE);
		codeTextFieldProtocol.setEditable(false);
		codeTextFieldProtocol.setBorder(null);
		codeTextFieldProtocol.setFont(new Font("Calibri", Font.PLAIN, 13));
		codeTextFieldProtocol.setBounds(395, 83, 93, 20);
		taxCouponScreen.getContentPane().add(codeTextFieldProtocol);
		codeTextFieldProtocol.setColumns(10);

		final String[] guides = new String[] { "Qtd.", "COD.", "Produto", "VL. UNIT.", "SUBTOTAL" };
		table.setModel(new DefaultTableModel(matriz, guides));
		table.getColumnModel().getColumn(0).setPreferredWidth(36);
		table.getColumnModel().getColumn(1).setPreferredWidth(44);
		table.getColumnModel().getColumn(2).setPreferredWidth(311);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(66);
		table.setBackground(null);
		table.setFont(new Font("Calibri", Font.PLAIN, 11));
		JScrollPane scrollPanes = new JScrollPane(table);
		scrollPanes.setBounds(0, 141, 488, 225);
		taxCouponScreen.getContentPane().add(scrollPanes);

		JLabel couponLabel = new JLabel("CUPOM FISCAL");
		couponLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		couponLabel.setBounds(187, 116, 100, 14);
		taxCouponScreen.getContentPane().add(couponLabel);

		JLabel subtotalLabel = new JLabel("Subtotal: ");
		subtotalLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		subtotalLabel.setBounds(350, 377, 45, 20);
		taxCouponScreen.getContentPane().add(subtotalLabel);

		JTextField subtotalTextField = new JTextField(textFieldSubtotal.getText());
		subtotalTextField.setBackground(Color.WHITE);
		subtotalTextField.setEditable(false);
		subtotalTextField.setBorder(null);
		subtotalTextField.setBounds(405, 377, 83, 20);
		taxCouponScreen.getContentPane().add(subtotalTextField);
		subtotalTextField.setColumns(10);

		JLabel lblIcms = new JLabel("ICMS: ");
		lblIcms.setBounds(10, 377, 32, 20);
		taxCouponScreen.getContentPane().add(lblIcms);

		JTextField txtIcms = new JTextField(decimalFormat.format(Double.parseDouble(textFieldSubtotal.getText().replaceAll(",", ".")) * 0.18));
		txtIcms.setBackground(Color.WHITE);
		txtIcms.setEditable(false);
		txtIcms.setBorder(null);
		txtIcms.setBounds(48, 377, 83, 20);
		taxCouponScreen.getContentPane().add(txtIcms);
		txtIcms.setColumns(10);

		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblTotal.setBounds(350, 408, 45, 20);
		taxCouponScreen.getContentPane().add(lblTotal);

		JTextField totalTextField = new JTextField(textFieldTotal.getText());
		totalTextField.setBackground(Color.WHITE);
		totalTextField.setEditable(false);
		totalTextField.setColumns(10);
		totalTextField.setBorder(null);
		totalTextField.setBounds(405, 408, 83, 20);
		taxCouponScreen.getContentPane().add(totalTextField);

		JLabel lblLine_2 = new JLabel(
				"--------------------------------------------------------------------------------------------------------------------------");
		lblLine_2.setBounds(0, 426, 488, 14);
		taxCouponScreen.getContentPane().add(lblLine_2);

		JTextArea txtrThankyou = new JTextArea();
		txtrThankyou.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtrThankyou.setText("Obrigado pela preferëncia!\r\nVolte sempre!");
		txtrThankyou.setBounds(164, 445, 162, 33);
		txtrThankyou.setEditable(false);
		txtrThankyou.setBorder(null);
		taxCouponScreen.getContentPane().add(txtrThankyou);

		JLabel lblCod = new JLabel("COD.:");
		lblCod.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblCod.setBounds(350, 83, 45, 20);
		taxCouponScreen.getContentPane().add(lblCod);

		JButton btnClose = new JButton("Fechar");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				taxCouponScreen.dispose();
				clearTable(arrayListProduct, guide, table, textFieldSubtotal);
			}
		});
		btnClose.setBounds(193, 489, 89, 23);
		btnClose.setBackground(null);
		taxCouponScreen.getContentPane().add(btnClose);

		clearFields(productNameTextField, amountTextField, codeTextFieldProtocol, avalaibleAmountTextField,cpfTextFieldFormatted, textFieldSubtotal, textFieldTotal);
		taxCouponScreen.setTitle("NOTA FISCAL");
		taxCouponScreen.setBounds(10, 10, 504, 550);
		taxCouponScreen.setVisible(true);
		desktopPane.add(taxCouponScreen);
	}
}