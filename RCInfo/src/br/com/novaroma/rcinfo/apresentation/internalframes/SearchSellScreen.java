package br.com.novaroma.rcinfo.apresentation.internalframes;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.text.DecimalFormat;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import br.com.novaroma.rcinfo.entities.Product;
import br.com.novaroma.rcinfo.entities.Sell;
import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;
import br.com.novaroma.rcinfo.facade.Facade;

public class SearchSellScreen implements InternalFrames {

	JInternalFrame internalFrame;
	String[][] advancedMatriz;
	private JScrollPane simpleScrollPane;
	private JScrollPane advancedScrollPane;
	DecimalFormat decimalFormat = new DecimalFormat("0.00");
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dfWithHour = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private void completeList(final Facade facade, final JDesktopPane desktopPane) {
		JInternalFrame completeList = new JInternalFrame();
		completeList.setClosable(true);
		completeList.setMaximizable(true);
		completeList.setResizable(true);
		completeList.setIconifiable(true);
		ArrayList<Sell> listSelectedSell = new ArrayList<Sell>(facade.getMapSell().values());
		String[] arraySell = new String[listSelectedSell.size()];
		for (int i = 0; i < arraySell.length; i++) {
			arraySell[i] = listSelectedSell.get(i).getProtocolo() + " - "
					+ dfWithHour.format(listSelectedSell.get(i).getDataVenda().getTime()) + " - " + "R$ "
					+ listSelectedSell.get(i).getTotal() + " - " + listSelectedSell.get(i).getCpfCliente();
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JList<String[]> list = new JList(arraySell);
		list.setBounds(485, 116, 116, 105);
		JScrollPane scrollPaneTest = new JScrollPane(list);

		completeList.setBounds(550, 20, 260, 482);
		scrollPaneTest.setBounds(completeList.getBounds());
		scrollPaneTest.setVisible(true);
		completeList.getContentPane().add(scrollPaneTest);
		completeList.setVisible(true);
		try {
			internalFrame.setIcon(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		desktopPane.add(completeList);
	}

	public JInternalFrame getInternalFrame() {
		return internalFrame;
	}

	public void setInternalFrame(JInternalFrame internalFrame) {
		this.internalFrame = internalFrame;
	}

	public SearchSellScreen(final Facade facade, final JDesktopPane desktopPane) {
		internalFrame = new JInternalFrame();
		internalFrame.setTitle("Buscar Venda");
		internalFrame.setClosable(true);
		internalFrame.setMaximizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setBounds(0, 10, 800, 655);
		internalFrame.getContentPane().setLayout(null);

		final JLabel lblCpf = new JLabel("CPF do cliente: ");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCpf.setBounds(314, 52, 111, 30);
		internalFrame.getContentPane().add(lblCpf);
		MaskFormatter cpfMask = null;
		try {
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (Exception e) {
			e.printStackTrace();
		}
		cpfMask.setPlaceholderCharacter('_');
		final JFormattedTextField ftfCpfAdvanced = new JFormattedTextField(cpfMask);
		ftfCpfAdvanced.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ftfCpfAdvanced.setBounds(435, 52, 133, 30);
		internalFrame.getContentPane().add(ftfCpfAdvanced);

		final JLabel lblSubtotal = new JLabel("Subtotal: ");
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSubtotal.setBounds(569, 468, 92, 30);
		lblSubtotal.setVisible(false);
		internalFrame.getContentPane().add(lblSubtotal);

		final JTextField textFieldSubtotal = new JTextField();
		textFieldSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSubtotal.setEditable(false);
		textFieldSubtotal.setColumns(10);
		textFieldSubtotal.setBounds(648, 468, 126, 30);
		textFieldSubtotal.setVisible(false);
		internalFrame.getContentPane().add(textFieldSubtotal);

		final JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotal.setBounds(569, 509, 92, 30);
		lblTotal.setVisible(false);
		internalFrame.getContentPane().add(lblTotal);

		final JTextField txtTotal = new JTextField();
		txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTotal.setEditable(false);
		txtTotal.setBounds(648, 509, 126, 30);
		txtTotal.setVisible(false);
		internalFrame.getContentPane().add(txtTotal);
		txtTotal.setColumns(10);

		final JLabel lblCpfClient = new JLabel("CPF do Cliente: ");
		lblCpfClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpfClient.setBounds(10, 468, 109, 30);
		lblCpfClient.setVisible(false);
		internalFrame.getContentPane().add(lblCpfClient);

		final JFormattedTextField ftfCpfSimple = new JFormattedTextField(cpfMask);
		ftfCpfSimple.setEditable(false);
		ftfCpfSimple.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftfCpfSimple.setBounds(129, 468, 148, 30);
		ftfCpfSimple.setVisible(false);
		internalFrame.getContentPane().add(ftfCpfSimple);

		final JLabel sellDateLabel = new JLabel("Data da venda: ");
		sellDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sellDateLabel.setBounds(20, 52, 117, 30);
		internalFrame.getContentPane().add(sellDateLabel);

		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(147, 52, 133, 30);
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		internalFrame.getContentPane().add(dateChooser);

		final JLabel protocolLabel = new JLabel("Protocolo: ");
		protocolLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		protocolLabel.setBounds(20, 134, 84, 30);
		internalFrame.getContentPane().add(protocolLabel);

		final JTextField protocolTextField = new JTextField();
		protocolTextField.setBounds(147, 134, 133, 30);
		internalFrame.getContentPane().add(protocolTextField);
		protocolTextField.setColumns(10);
		final String[] simpleGuias = new String[] { "Quantidade", "Código", "Produto", "Preço Unitário", "Subtotal" };
		final String[] advancedGuias = new String[] { "Protocolo", "CPF Cliente", "Data da venda", "Valor total" };

		final JTable simpleTable = new JTable();
		simpleTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		simpleTable.setEnabled(false);
		simpleTable.setBackground(null);
		simpleTable.setModel(new DefaultTableModel(new String[0][simpleGuias.length], simpleGuias));
		personalizarGradeTableProducts(simpleTable);
		simpleTable.setShowVerticalLines(false);
		simpleScrollPane = new JScrollPane(simpleTable);
		simpleScrollPane.setBounds(10, 232, 764, 225);
		simpleScrollPane.setVisible(false);
		internalFrame.getContentPane().add(simpleScrollPane);

		final JTable advancedTable = new JTable();
		advancedTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		advancedTable.setEnabled(false);
		advancedTable.setBackground(null);
		advancedTable.setModel(new DefaultTableModel(new String[0][advancedGuias.length], advancedGuias));
		advancedTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		advancedTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		advancedTable.getColumnModel().getColumn(2).setPreferredWidth(149);
		advancedScrollPane = new JScrollPane(advancedTable);
		advancedScrollPane.setBounds(129, 232, 542, 225);
		advancedScrollPane.setVisible(false);
		internalFrame.getContentPane().add(advancedScrollPane);

		final JButton protocolSearchButton = new JButton("Buscar");
		protocolSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Sell sell = facade.simpleReadSell(protocolTextField.getText());
					if (sell != null) {
						simpleTable.setModel(new DefaultTableModel(sell.getProdutos(), simpleGuias));
						personalizarGradeTableProducts(simpleTable);
						textFieldSubtotal.setText(sell.getSubtotal());
						ftfCpfSimple.setText(sell.getCpfCliente());
						txtTotal.setText(sell.getTotal());
						simpleScrollPane.setVisible(true);
						advancedScrollPane.setVisible(false);
						lblCpfClient.setVisible(true);
						ftfCpfSimple.setVisible(true);
						lblSubtotal.setVisible(true);
						lblTotal.setVisible(true);
						txtTotal.setVisible(true);
						textFieldSubtotal.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(internalFrame, "Nenhuma venda registrada com esse protocol");
					}
				} catch (EmptyFieldException | InvalidCharactersFieldException e) {
					JOptionPane.showMessageDialog(internalFrame, e.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		protocolSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		protocolSearchButton.setBounds(290, 134, 130, 30);
		internalFrame.getContentPane().add(protocolSearchButton);

		final JButton sellSearchButton = new JButton("Buscar");
		sellSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				advancedSearch(facade, ftfCpfAdvanced, dateChooser, advancedGuias, advancedTable);
				protocolTextField.setText("");
				lblCpfClient.setVisible(false);
				ftfCpfSimple.setVisible(false);
				lblSubtotal.setVisible(false);
				textFieldSubtotal.setVisible(false);
				lblTotal.setVisible(false);
				txtTotal.setVisible(false);

			}
		});
		sellSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sellSearchButton.setBounds(578, 52, 130, 30);
		internalFrame.getContentPane().add(sellSearchButton);

		JLabel lblAdvancedSearch = new JLabel("Busca por data e cpf");
		lblAdvancedSearch.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAdvancedSearch.setBounds(10, 11, 247, 30);
		internalFrame.getContentPane().add(lblAdvancedSearch);

		JLabel lblSimpleSearch = new JLabel("Busca por protocol");
		lblSimpleSearch.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSimpleSearch.setBounds(10, 93, 247, 30);
		internalFrame.getContentPane().add(lblSimpleSearch);

		JButton btnClose = new JButton("Fechar");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				internalFrame.dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBounds(644, 585, 130, 30);
		internalFrame.getContentPane().add(btnClose);

		JButton btnDeleteSell = new JButton("Deletar Venda");
		btnDeleteSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String protocol = JOptionPane.showInputDialog("Digite o protocolo da venda a ser deletada: ");
				try {
					Sell sellDelete = facade.simpleReadSell(protocol);
					if (sellDelete != null) {
						for (int i = 0; i < sellDelete.getProdutos().length; i++) {
							Product product = facade.simpleReadProduct(sellDelete.getProdutos()[i][1]);
							product.setAmount(Integer.parseInt(product.getAmount()) + Integer.parseInt(sellDelete.getProdutos()[i][0]) + "");
							facade.update(product);
						}
						if (facade.delete(facade.simpleReadSell(protocol))) {
							advancedScrollPane.setVisible(false);
							simpleScrollPane.setVisible(false);
							JOptionPane.showMessageDialog(internalFrame, "Venda " + protocol + " deletada com sucesso");
						}
					}
				} catch (EmptyFieldException | InvalidCharactersFieldException e) {
					JOptionPane.showMessageDialog(internalFrame, e.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDeleteSell.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteSell.setBounds(504, 585, 130, 30);
		internalFrame.getContentPane().add(btnDeleteSell);

		JButton listAllButton = new JButton("Listar Todos");
		listAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				completeList(facade, desktopPane);
			}
		});
		listAllButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		listAllButton.setBounds(435, 134, 130, 30);
		internalFrame.getContentPane().add(listAllButton);
		internalFrame.setVisible(true);

	}

	private void personalizarGradeTableProducts(final JTable table) {
		table.getColumnModel().getColumn(0).setPreferredWidth(57);
		table.getColumnModel().getColumn(1).setPreferredWidth(43);
		table.getColumnModel().getColumn(2).setPreferredWidth(368);
		table.getColumnModel().getColumn(3).setPreferredWidth(65);
		table.getColumnModel().getColumn(4).setPreferredWidth(65);
	}

	private void advancedSearch(final Facade facade, final JFormattedTextField ftfCpfAdvanced,
			final JDateChooser dateChooser, final String[] advancedGuias, final JTable advancedTable) {
		try {
			advancedMatriz = facade.advancedRead(dateChooser.getCalendar(), ftfCpfAdvanced.getText());
			advancedTable.setModel(new DefaultTableModel(advancedMatriz, advancedGuias));
			advancedTable.getColumnModel().getColumn(0).setPreferredWidth(40);
			advancedTable.getColumnModel().getColumn(3).setPreferredWidth(40);
			advancedTable.getColumnModel().getColumn(2).setPreferredWidth(149);
			advancedScrollPane.setVisible(true);
			simpleScrollPane.setVisible(false);
		} catch (EmptyFieldException e) {
			JOptionPane.showMessageDialog(internalFrame, e.getMessage(), "Alerta", JOptionPane.WARNING_MESSAGE);
		}
	}
}