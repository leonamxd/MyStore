package forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import crud.BuscarDados;
import crud.InserirDados;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewProdutos extends JFrame {

	private JPanel contentPane;
	private JTable tableProdutos;
	private JTextField txtNomeProduto;
	private JTextField txtTipoProduto;
	private JTextField txtValorVenda;

	/**
	 * Launch the application.
	 */
	
	//GLOBAIS
	ViewPrincipal vPr = new ViewPrincipal();
	DefaultTableModel model = new DefaultTableModel();
	InserirDados inserirDados = new InserirDados();
	BuscarDados buscarDados = new BuscarDados();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewProdutos frame = new ViewProdutos();
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
	public ViewProdutos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Object[] column = {"ID", "Nome", "Tipo", "Valor", "Qtd."};
		Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		contentPane.setLayout(null);
		
		JLabel lblProdutoDescricao = new JLabel("Descri\u00E7\u00E3o :");
		lblProdutoDescricao.setBounds(91, 486, 53, 14);
		contentPane.add(lblProdutoDescricao);
		
		JLabel lblProdutoValorVenda = new JLabel("Valor de Venda :");
		lblProdutoValorVenda.setBounds(91, 606, 79, 14);
		contentPane.add(lblProdutoValorVenda);
		
		txtNomeProduto = new JTextField();
		txtNomeProduto.setBounds(188, 420, 440, 20);
		contentPane.add(txtNomeProduto);
		txtNomeProduto.setColumns(10);
		
		JTextArea txtAreaDescricao = new JTextArea();
		txtAreaDescricao.setBounds(188, 481, 440, 106);
		contentPane.add(txtAreaDescricao);
		
		JLabel lblProdutoTipo = new JLabel("Tipo do Produto :");
		lblProdutoTipo.setBounds(91, 448, 83, 14);
		contentPane.add(lblProdutoTipo);
		
		txtTipoProduto = new JTextField();
		txtTipoProduto.setBounds(188, 451, 440, 20);
		contentPane.add(txtTipoProduto);
		txtTipoProduto.setColumns(10);
		
		JLabel lblProdutoNome = new JLabel("Nome do Produto:");
		lblProdutoNome.setBounds(91, 423, 87, 14);
		contentPane.add(lblProdutoNome);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 4, 883, 402);
		contentPane.add(scrollPane);
		
		tableProdutos = new JTable();
		scrollPane.setViewportView(tableProdutos);
		tableProdutos.setModel(model);
		buscarDados.BuscarProdutos(tableProdutos);
		
		txtValorVenda = new JTextField();
		txtValorVenda.setBounds(188, 603, 150, 20);
		contentPane.add(txtValorVenda);
		txtValorVenda.setColumns(10);
		
		JButton btnProdutoCadastrar = new JButton("Cadastrar Produto");
		btnProdutoCadastrar.setBounds(741, 417, 175, 45);
		btnProdutoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valorVenda = txtValorVenda.getText().replace(",", ".");
				inserirDados.inserirProdutos(txtNomeProduto.getText(), txtTipoProduto.getText(), txtAreaDescricao.getText(), Double.parseDouble(valorVenda));
				row[1] = txtNomeProduto.getText();
				row[2] = txtTipoProduto.getText();
				row[3] = txtValorVenda.getText();
				model.addRow(row);
				
				txtNomeProduto.setText("");
				txtTipoProduto.setText("");
				txtValorVenda.setText("");
				JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.");
				buscarDados.BuscarProdutos(tableProdutos);
				
			}
		});
		contentPane.add(btnProdutoCadastrar);
		
		JButton btnProdutoEditar = new JButton("Editar Produto");
		btnProdutoEditar.setBounds(741, 471, 175, 45);
		contentPane.add(btnProdutoEditar);
		
		JButton btnProdutoDeletar = new JButton("Deletar Produto");
		btnProdutoDeletar.setBounds(807, 602, 109, 23);
		contentPane.add(btnProdutoDeletar);
		
		JButton btnProdutoVoltar = new JButton("Voltar");
		btnProdutoVoltar.setBounds(10, 11, 71, 23);
		btnProdutoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				vPr.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnProdutoVoltar);
	}
}
