package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import crud.BuscarDados;
import crud.DeletarDados;
import crud.EditarDados;
import crud.InserirDados;
import mensagens.MensagensErro;
import mensagens.MensagensSucesso;
import validacao.ValidarCampos;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class ViewProdutos extends JFrame {

	private JPanel contentPane;
	private JTable tableProdutos;
	private JTextField txtNomeProduto;
	private JTextField txtValorVenda;

	/**
	 * Launch the application.
	 * hello darkness my old friend
	 */

	// GLOBAIS
	ViewPrincipal vPrin = new ViewPrincipal();
	DefaultTableModel model = new DefaultTableModel();
	InserirDados inserirDados = new InserirDados();
	BuscarDados buscarDados = new BuscarDados();
	DeletarDados deletarDados = new DeletarDados();
	EditarDados editarDados = new EditarDados();
	ValidarCampos validarCampos = new ValidarCampos();
	MensagensErro mErro = new MensagensErro();
	MensagensSucesso mSucesso = new MensagensSucesso();

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JComboBox cBTipoProduto = new JComboBox();
		cBTipoProduto.setBounds(188, 450, 440, 20);
		cBTipoProduto.setModel(new DefaultComboBoxModel(new String[] {"Boticário","Eudora","Handara", "Hinode",
				"Jalde Semi Jóias", "Kit Toalhas", "Mahogany", "Mary Kay", "Máscaras", "Natura","Rommanel", "Roupa Íntima",
				"Tupperware", "Outros"}));
		contentPane.add(cBTipoProduto);

		Object[] column = { "ID", "Nome", "Tipo", "Valor", "Qtd." };
		Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		contentPane.setLayout(null);
		buscarDados.BuscarProdutos(tableProdutos);

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

		JLabel lblProdutoNome = new JLabel("Nome do Produto:");
		lblProdutoNome.setBounds(91, 423, 87, 14);
		contentPane.add(lblProdutoNome);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 4, 883, 402);
		contentPane.add(scrollPane);

		tableProdutos = new JTable();
		tableProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableProdutos.getSelectedRow();
				txtNomeProduto.setText(tableProdutos.getModel().getValueAt(row, 1).toString());
				cBTipoProduto.setSelectedItem(tableProdutos.getModel().getValueAt(row, 2));
				txtAreaDescricao.setText(buscarDados.BuscarTextArea(tableProdutos));
				txtValorVenda.setText(tableProdutos.getModel().getValueAt(row, 3).toString());
			}
		});
		scrollPane.setViewportView(tableProdutos);
		tableProdutos.setModel(model);
		tableProdutos.setDefaultEditor(Object.class, null);
		buscarDados.BuscarProdutos(tableProdutos);

		txtValorVenda = new JTextField();
		txtValorVenda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String valorVenda = txtValorVenda.getText();
				validarCampos.validarTipoNumerico(valorVenda, txtValorVenda);
			}
		});
		txtValorVenda.setBounds(188, 603, 150, 20);
		contentPane.add(txtValorVenda);
		txtValorVenda.setColumns(10);

		JButton btnProdutoCadastrar = new JButton("Cadastrar Produto");
		btnProdutoCadastrar.setBounds(741, 417, 175, 45);
		btnProdutoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valorVenda = txtValorVenda.getText().replace(",", ".");
				String tipoProduto = cBTipoProduto.getSelectedItem().toString();
				
				if (validarCampos.validarPreenchimentoCamposProduto(txtNomeProduto, cBTipoProduto, txtAreaDescricao,
						txtValorVenda) == false) {

					inserirDados.inserirProdutos(txtNomeProduto.getText(), tipoProduto,
							txtAreaDescricao.getText(), Double.parseDouble(valorVenda), row, txtNomeProduto,
							cBTipoProduto, txtValorVenda, model);
				}

				txtNomeProduto.setText("");
				cBTipoProduto.setSelectedItem(null);;
				txtAreaDescricao.setText("");
				txtValorVenda.setText("");

				buscarDados.BuscarProdutos(tableProdutos);

			}
		});
		contentPane.add(btnProdutoCadastrar);

		JButton btnProdutoEditar = new JButton("Editar Produto");
		btnProdutoEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = tableProdutos.getSelectedRow();
				int idCelula = Integer.parseInt(tableProdutos.getModel().getValueAt(row, 0).toString());
				
				editarDados.editarProdutos(idCelula, txtNomeProduto.getText(), txtAreaDescricao.getText(),
						cBTipoProduto.getSelectedItem().toString(), Double.parseDouble(txtValorVenda.getText()), tableProdutos);
				
			}
		});
		btnProdutoEditar.setBounds(741, 471, 175, 45);
		contentPane.add(btnProdutoEditar);

		JButton btnProdutoDeletar = new JButton("Deletar Produto");
		btnProdutoDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Deseja prosseguir a exclusão?", "",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						deletarDados.deletarProdutos(tableProdutos, model);
					} catch (Exception e2) {
						System.out.println(e2);
					}
				} 
			}
		});
		
		btnProdutoDeletar.setBounds(741, 602, 175, 23);
		contentPane.add(btnProdutoDeletar);

		JButton btnProdutoVoltar = new JButton("Voltar");
		btnProdutoVoltar.setBounds(10, 11, 71, 23);
		btnProdutoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				vPrin.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnProdutoVoltar);
		
		
	}
}
