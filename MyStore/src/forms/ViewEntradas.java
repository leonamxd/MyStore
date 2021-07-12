package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import crud.BuscarDados;
import crud.DeletarDados;
import crud.EditarDados;
import crud.InserirDados;
import validacao.ValidarCampos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewEntradas extends JFrame {

	private JPanel contentPane;
	private JTable tableEntradas;
	private JComboBox cBProduto;
	private JDateChooser dChDataEntrada;
	private JDateChooser dChDataValidade;
	private JTextField txtPrecoCusto;
	private JTextField txtQuantidadeProduto;

	// GLOBAIS
	ViewPrincipal vPrin = new ViewPrincipal();
	DefaultTableModel model = new DefaultTableModel();
	InserirDados inserirDados = new InserirDados();
	BuscarDados buscarDados = new BuscarDados();
	DeletarDados deletarDados = new DeletarDados();
	EditarDados editarDados = new EditarDados();
	ValidarCampos validarCampos = new ValidarCampos();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEntradas frame = new ViewEntradas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewEntradas() {
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 4, 883, 402);
		contentPane.add(scrollPane);

		Object[] column = { "ID", "Produto", "Custo", "Qtd.", "Validade", "Entrada" };
		Object[] row = new Object[6];
		model.setColumnIdentifiers(column);

		tableEntradas = new JTable();
		tableEntradas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableEntradas.getSelectedRow();

				cBProduto.setSelectedItem(tableEntradas.getModel().getValueAt(row, 1));
				txtPrecoCusto.setText(tableEntradas.getModel().getValueAt(row, 2).toString());
				txtQuantidadeProduto.setText(tableEntradas.getModel().getValueAt(row, 3).toString());
				try {
					Date dataEntrada = sdf.parse(tableEntradas.getModel().getValueAt(row, 5).toString());
					Date dataValidade = sdf.parse(tableEntradas.getModel().getValueAt(row, 4).toString());
					dChDataEntrada.setDate(dataEntrada);
					dChDataValidade.setDate(dataValidade);

				} catch (ParseException e1) {
					System.out.println(e1);
				}
			}
		});
		scrollPane.setViewportView(tableEntradas);
		tableEntradas.setDefaultEditor(Object.class, null);
		tableEntradas.setModel(model);
		buscarDados.BuscarEntradas(tableEntradas);

		dChDataEntrada = new JDateChooser();
		dChDataEntrada.setBounds(221, 622, 131, 20);
		contentPane.add(dChDataEntrada);

		dChDataValidade = new JDateChooser();
		dChDataValidade.setBounds(221, 573, 131, 20);
		contentPane.add(dChDataValidade);

		cBProduto = new JComboBox();
		cBProduto.setBounds(221, 417, 400, 24);

		JButton btnEntradaCadastrar = new JButton("Cadastrar Entrada");
		btnEntradaCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valorComboBox = String.valueOf(cBProduto.getSelectedItem());

				String dataEntrada = sdf.format(dChDataEntrada.getDate());
				String dataValidade = sdf.format(dChDataValidade.getDate());

				if (validarCampos.validarPreenchimentoCamposEntrada(cBProduto, txtPrecoCusto, txtQuantidadeProduto,
						dChDataValidade, dChDataEntrada)) {
					
					validarCampos.validarInsercaoEntrada(valorComboBox, Double.parseDouble(txtPrecoCusto.getText()),
							Integer.parseInt(txtQuantidadeProduto.getText()), dataEntrada, dataValidade, row, cBProduto,
							txtPrecoCusto, txtQuantidadeProduto, dataEntrada, dataValidade, model);
				}

				cBProduto.setSelectedItem(null);
				txtPrecoCusto.setText("");
				txtQuantidadeProduto.setText("");
				dChDataEntrada.setDate(null);
				dChDataValidade.setDate(null);
				buscarDados.BuscarEntradas(tableEntradas);
			}
		});
		btnEntradaCadastrar.setBounds(741, 417, 175, 45);
		contentPane.add(btnEntradaCadastrar);

		JButton btnEntradaEditar = new JButton("Editar Entrada");
		btnEntradaEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeProduto = String.valueOf(cBProduto.getSelectedItem());
				String dataEntrada = sdf.format(dChDataEntrada.getDate());
				String dataValidade = sdf.format(dChDataValidade.getDate());
				String idEntrada = tableEntradas.getModel().getValueAt(tableEntradas.getSelectedRow(), 0).toString();
//				
				editarDados.editarEntradas(nomeProduto, Double.parseDouble(txtPrecoCusto.getText()),
						Integer.parseInt(txtQuantidadeProduto.getText()), dataEntrada, dataValidade,
						Integer.parseInt(idEntrada), tableEntradas);
				buscarDados.BuscarEntradas(tableEntradas);
			}
		});
		btnEntradaEditar.setBounds(741, 471, 175, 45);
		contentPane.add(btnEntradaEditar);

		JButton btnEntradaDeletar = new JButton("Deletar Entrada");
		btnEntradaDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarCampos.validarExclusaoEntrada(tableEntradas, model);
			}
		});
		btnEntradaDeletar.setBounds(741, 602, 175, 23);
		contentPane.add(btnEntradaDeletar);

		JButton btnEntradaVoltar = new JButton("Voltar");
		btnEntradaVoltar.setBounds(10, 11, 71, 23);
		btnEntradaVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				vPrin.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnEntradaVoltar);

		JLabel lblProdutoNome = new JLabel("Produto:");
		lblProdutoNome.setBounds(91, 423, 120, 14);
		contentPane.add(lblProdutoNome);

		buscarDados.PreencherProdutosComboBox(cBProduto);

		contentPane.add(cBProduto);

		JLabel lblDataEntrada = new JLabel("Data de Entrada:");
		lblDataEntrada.setBounds(91, 622, 120, 14);
		contentPane.add(lblDataEntrada);

		JLabel lblPrecoCusto = new JLabel("Pre\u00E7o de Custo:");
		lblPrecoCusto.setBounds(91, 469, 120, 14);
		contentPane.add(lblPrecoCusto);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(91, 522, 120, 14);
		contentPane.add(lblQuantidade);

		JLabel lblDataValidade = new JLabel("Data de Validade:");
		lblDataValidade.setBounds(91, 575, 120, 14);
		contentPane.add(lblDataValidade);

		txtPrecoCusto = new JTextField();
		txtPrecoCusto.setBounds(221, 466, 131, 20);
		contentPane.add(txtPrecoCusto);
		txtPrecoCusto.setColumns(10);

		txtQuantidadeProduto = new JTextField();
		txtQuantidadeProduto.setBounds(221, 517, 131, 20);
		contentPane.add(txtQuantidadeProduto);
		txtQuantidadeProduto.setColumns(10);

	}
}
