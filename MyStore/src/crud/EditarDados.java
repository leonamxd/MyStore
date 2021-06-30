package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jdbc.FabricaConexao;

public class EditarDados {

	public void editarEntradas(String _produto, double _valorCusto, int _qtd, String _dataEntrada, String _dataValidade, int _idEntrada) {
		String sql = "UPDATE tb_entrada SET fk_prd_produto = (SELECT id FROM tb_produtos WHERE prd_nome = ?),"
				+ " ent_valor_custo_prod = ?,"
				+ "ent_qntd_produto = ?, ent_data_cadastro = ?, ent_data_validade_prod = ?"
				+ "WHERE id = ?";
		
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
			stmt.setInt(6, _idEntrada);
			
			stmt.execute();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void editarProdutos(int _idProduto, String _nomeProduto, String _descricaoProduto, String _tipoProduto, double _valorVenda) {

		String sql = "UPDATE tb_produtos SET prd_nome = ?, prd_descricao_prod = ?, prd_tipo_produto = ?, prd_valor_produto = ? WHERE id = ?";

		Connection conexao = null;
		PreparedStatement stmt = null;

		try {
			// CRIAR CONEXÃO COM O BANCO
			conexao = FabricaConexao.abrirConexao();

			// CRIAR A CLASSE PARA EXECUTAR A QUERY
			stmt = conexao.prepareStatement(sql);

			// ADICIONAR OS VALORES PARA ATUALIZAR
			stmt.setString(1, _nomeProduto);
			stmt.setString(2, _descricaoProduto);
			stmt.setString(3, _tipoProduto);
			stmt.setDouble(4, _valorVenda);

			// ID DO PRODUTO A SER ATUALIZADO
			stmt.setInt(5, _idProduto);

			// EXECUTAR A QUERY
			stmt.execute();

		} catch (Exception e) {
			System.out.println(e);
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
				System.out.println(e2);
			}
		}

	}

}
