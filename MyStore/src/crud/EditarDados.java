package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jdbc.FabricaConexao;

public class EditarDados {

	public void editarDados(int _idProduto, String _nomeProduto, String _descricaoProduto, double _valorVenda) {

		String sql = "UPDATE tb_produtos SET prd_nome = ?, prd_descricao_prod = ?, prd_valor_produto" + " WHERE id = ?";

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
			stmt.setDouble(3, _valorVenda);

			// ID DO PRODUTO A SER ATUALIZADO
			stmt.setInt(4, _idProduto);

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
