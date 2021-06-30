package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class ViewEntradas extends JFrame {

	private JPanel contentPane;
	private JTable tableEntradas;
	private JTextField txtPrecoCusto;
	private JTextField txtQuantidadeProduto;

	//GLOBAIS
	ViewPrincipal vPrin = new ViewPrincipal();
	DefaultTableModel model = new DefaultTableModel();
	InserirDados inserirDados = new InserirDados();
	BuscarDados buscarDados = new BuscarDados();
	DeletarDados deletarDados = new DeletarDados();
	EditarDados editarDados = new EditarDados();
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
		
		Object[]column = {"ID", "Produto","Custo","Qtd.","Validade","Entrada"};
		Object[]row = new Object[6];
		model.setColumnIdentifiers(column);
		
		
		tableEntradas = new JTable();
		scrollPane.setViewportView(tableEntradas);
		tableEntradas.setModel(model);
		
		JDateChooser dChDataEntrada = new JDateChooser();
		
		
		dChDataEntrada.setBounds(221, 571, 131, 20);
		contentPane.add(dChDataEntrada);
		
		JDateChooser dChDataValidade = new JDateChooser();
		
		dChDataValidade.setBounds(221, 622, 131, 20);
		contentPane.add(dChDataValidade);
		
		JComboBox cBProduto = new JComboBox();
		cBProduto.setBounds(221, 417, 400, 24);
		
		JButton btnEntradaCadastrar = new JButton("Cadastrar Entrada");
		btnEntradaCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valorComboBox = String.valueOf(cBProduto.getSelectedItem());
				System.out.println(valorComboBox);
				 
				String dataEntrada = sdf.format(dChDataEntrada.getDate());
				String dataValidade = sdf.format(dChDataValidade.getDate());
				inserirDados.inserirEntrada(valorComboBox, Double.parseDouble(txtPrecoCusto.getText()), Integer.parseInt(txtQuantidadeProduto.getText()), dataEntrada, dataValidade);
				row[1] = cBProduto.getSelectedItem();
				row[2] = txtPrecoCusto.getText();
				row[3] = txtQuantidadeProduto.getText();
				row[4] = dataEntrada;
				row[5] = dataValidade;
				
				model.addRow(row);
			}
		});
		btnEntradaCadastrar.setBounds(741, 417, 175, 45);
		contentPane.add(btnEntradaCadastrar);
		
		JButton btnEntradaEditar = new JButton("Editar Entrada");
		btnEntradaEditar.setBounds(741, 471, 175, 45);
		contentPane.add(btnEntradaEditar);
		
		JButton btnEntradaDeletar = new JButton("Deletar Entrada");
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
		lblDataEntrada.setBounds(91, 571, 120, 14);
		contentPane.add(lblDataEntrada);
		
		JLabel lblPrecoCusto = new JLabel("Pre\u00E7o de Custo:");
		lblPrecoCusto.setBounds(91, 469, 120, 14);
		contentPane.add(lblPrecoCusto);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(91, 520, 120, 14);
		contentPane.add(lblQuantidade);
		
		JLabel lblDataValidade = new JLabel("Data de Validade:");
		lblDataValidade.setBounds(91, 622, 120, 14);
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
