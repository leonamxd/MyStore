package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jdbc.FabricaConexao;

public class DeletarDados {

	public void deletarDados(int _idProduto) {

		String sql = "DELETE FROM tb_produtos WHERE id = ?";

		Connection conexao = null;
		PreparedStatement stmt = null;

		try {

			// CRIAR CONEXÃO COM O BANCO
			conexao = FabricaConexao.abrirConexao();

			// CRIAR CLASSE PARA CONEXÃO COM A QUERY
			stmt = conexao.prepareStatement(sql);

			// ID DE QUAL PRODUTO SERA DELETADO
			stmt.setInt(1, _idProduto);

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
