package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import jdbc.FabricaConexao;

public class DeletarDados {

	public void deletarEntradas(JTable _table) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		try {
			int row = _table.getSelectedRow();
			int idCelula = Integer.parseInt(_table.getModel().getValueAt(row, 0).toString());
			String sql = "DELETE FROM tb_entrada WHERE id = " + idCelula;

			conexao = FabricaConexao.abrirConexao();
			stmt = conexao.prepareStatement(sql);
			stmt.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e, "Erro de conex�o", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				stmt.close();
				conexao.close();

			} catch (SQLException e2) {
				JOptionPane.showMessageDialog(null, "Erro: " + e2, "Erro de conex�o", JOptionPane.ERROR_MESSAGE);

			}
		}
	}

	public void deletarProdutos(JTable _table) {

		Connection conexao = null;
		PreparedStatement stmt = null;

		try {

			int row = _table.getSelectedRow();
			int idCelula = Integer.parseInt(_table.getModel().getValueAt(row, 0).toString());
			String sql = "DELETE FROM tb_produtos WHERE id = " + idCelula;

			// CRIAR CONEX�O COM O BANCO
			conexao = FabricaConexao.abrirConexao();

			// CRIAR CLASSE PARA CONEX�O COM A QUERY
			stmt = conexao.prepareStatement(sql);

			// EXECUTAR A QUERY
			stmt.execute();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e, "Erro de conex�o", JOptionPane.ERROR_MESSAGE);
		} finally {

			// FECHAR AS CONEX�ES

			try {
				stmt.close();
				conexao.close();

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Erro: " + e2, "Erro ao fechar conex�o", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
