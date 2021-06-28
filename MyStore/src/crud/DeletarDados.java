package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JTable;

import jdbc.FabricaConexao;

public class DeletarDados {

	public void deletarProdutos(JTable _table) {

		Connection conexao = null;
		PreparedStatement stmt = null;

		try {
			
			int row = _table.getSelectedRow();
			int idCelula = Integer.parseInt(_table.getModel().getValueAt(row, 0).toString());
			String sql = "DELETE FROM tb_produtos WHERE id = " + idCelula;

			// CRIAR CONEXÃO COM O BANCO
			conexao = FabricaConexao.abrirConexao();

			// CRIAR CLASSE PARA CONEXÃO COM A QUERY
			stmt = conexao.prepareStatement(sql);

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
