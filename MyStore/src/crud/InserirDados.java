package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.swing.JOptionPane;

import jdbc.FabricaConexao;

public class InserirDados {
	
	public void inserirEntrada(String _produto, double _valorCusto, int _qtd, String _dataEntrada, String _dataValidade) {
		//ent_data_cadastro se refere a data que a Entrada foi realizada
		
		String sql = "INSERT INTO tb_entrada (fk_prd_produto, ent_valor_custo_prod, "
				+ "ent_qntd_produto, ent_data_cadastro"
				+ ",ent_data_validade_prod) VALUES ((SELECT id FROM tb_produtos WHERE prd_nome ="
				+ " ?), ?,?,?,?)";
		
		Connection conexao = null;
		PreparedStatement stmt = null;
		
		try {
			conexao = FabricaConexao.abrirConexao();
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, _produto);
			stmt.setDouble(2, _valorCusto);
			stmt.setInt(3, _qtd);
			stmt.setString(4, _dataEntrada);
			stmt.setString(5, _dataValidade);
			
			stmt.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	public void inserirProdutos(String _nomeProduto, String _tipoProduto, String _descricaoProduto,
			double _valorVenda) {

		String sql = "INSERT INTO tb_produtos (prd_nome, prd_tipo_produto, prd_descricao_prod, prd_valor_produto) VALUES (?,?,?,?)";

		Connection conexao = null;
		PreparedStatement stmt = null;

		try {

			// CRIAR UMA CONEXÃO COM O BANCO DE DADOS
			conexao = FabricaConexao.abrirConexao();

			// PREPARESTATEMENTE PARA EXECUTAR UMA QUERY
			stmt = conexao.prepareStatement(sql);

			// VALORES ESPERADOS PELA QUERY
			stmt.setString(1, _nomeProduto);
			stmt.setString(2, _tipoProduto);
			stmt.setString(3, _descricaoProduto);
			stmt.setDouble(4, _valorVenda);
//			stmt.setDouble(4, _valorVenda);

			// EXECUTAR A QUERY
			stmt.execute();

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Erro de inserção");
		} finally {

			// FECHAR AS CONEXÕES

			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conexao != null) {
					conexao.close();
				}

			} catch (Exception e2) {
				JOptionPane.showConfirmDialog(null, "Erro de inserção");
			}
		}
	}
}
