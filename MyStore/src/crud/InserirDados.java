package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import jdbc.FabricaConexao;

public class InserirDados {

	public void preencherTabelaEntrada(Object[] _object, JComboBox _combo, JTextField _text1, JTextField _text2,
			String _data1, String _data2, DefaultTableModel _model) {
		_object[1] = _combo.getSelectedItem();
		_object[2] = _text1.getText();
		_object[3] = _text2.getText();
		_object[4] = _data1;
		_object[5] = _data2;

		_model.addRow(_object);
	}

	public void preencherTabelaProduto(Object[] _object, JTextField _text1, JTextField _text2, JTextField _text3,
			DefaultTableModel _model) {
		_object[1] = _text1.getText();
		_object[2] = _text2.getText();
		_object[3] = _text3.getText();
		_model.addRow(_object);
	}

	// METODO PARA INSERIR NOVAS ENTRADAS
	public void inserirEntrada(String _produto, double _valorCusto, int _qtd, String _dataEntrada, String _dataValidade,
			Object[] _object, JComboBox _combo, JTextField _text1, JTextField _text2, String _data1, String _data2,
			DefaultTableModel _model) {
		// ent_data_cadastro se refere a data que a Entrada foi realizada

		// QUERY DO SQL
		String sql = "INSERT INTO tb_entrada (fk_prd_produto, ent_valor_custo_prod, "
				+ "ent_qntd_produto, ent_data_cadastro"
				+ ",ent_data_validade_prod) VALUES ((SELECT id FROM tb_produtos WHERE prd_nome =" + " ?), ?,?,?,?)";

		Connection conexao = null;
		PreparedStatement stmt = null;

		try {

			// CRIAR UMA CONEX�O COM O BANCO
			conexao = FabricaConexao.abrirConexao();

			// STATEMENTE PARA EXECUTAR UMA QUERY
			stmt = conexao.prepareStatement(sql);

			// VALORES ESPERADOS PELA QUERY
			stmt.setString(1, _produto);
			stmt.setDouble(2, _valorCusto);
			stmt.setInt(3, _qtd);
			stmt.setString(4, _dataEntrada);
			if (_dataValidade.equals("")) {
				stmt.setString(5, null);
			} else {
				stmt.setString(5, _dataValidade);
			}

			// EXECUTAR A QUERY
			stmt.execute();

			preencherTabelaEntrada(_object, _combo, _text1, _text2, _data1, _data2, _model);

		} catch (Exception e) {
			System.out.println(e);
		} finally {

			try {

				// FECHAR AS CONEX�ES
				stmt.close();
				conexao.close();

			} catch (Exception e2) {
				System.out.println(e2);
			}

		}

	}

	public void inserirProdutos(String _nomeProduto, String _tipoProduto, String _descricaoProduto, double _valorVenda,
			Object[] _object, JTextField _text1, JTextField _text2, JTextField _text3, DefaultTableModel _model) {

		// QUERY DO SQL
		String sql = "INSERT INTO tb_produtos (prd_nome, prd_tipo_produto, prd_descricao_prod, prd_valor_produto) VALUES (?,?,?,?)";

		Connection conexao = null;
		PreparedStatement stmt = null;

		try {

			// CRIAR UMA CONEX�O COM O BANCO DE DADOS
			conexao = FabricaConexao.abrirConexao();

			// PREPARESTATEMENTE PARA EXECUTAR UMA QUERY
			stmt = conexao.prepareStatement(sql);

			// VALORES ESPERADOS PELA QUERY
			stmt.setString(1, _nomeProduto);
			stmt.setString(2, _tipoProduto);
			stmt.setString(3, _descricaoProduto);
			stmt.setDouble(4, _valorVenda);

			// EXECUTAR A QUERY
			stmt.execute();

			preencherTabelaProduto(_object, _text1, _text2, _text3, _model);

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Erro de inser��o");
		} finally {

			// FECHAR AS CONEX�ES

			try {
				stmt.close();
				conexao.close();

			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(null, "Erro de inser��o");
			}
		}
	}
}
