package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.FabricaConexao;

public class BuscarDados {

	public void BuscarProdutos(JTable _table) {

		String sql = "SELECT * FROM tb_produtos";

		Connection conexao = null;
		Statement stmt = null;
		// PreparedStatement blabla = null;
		// CLASSE PARAR RECUPERAR DADOS DO BANCO
		ResultSet rSet = null;

		try {
			conexao = FabricaConexao.abrirConexao();
			stmt = conexao.createStatement();
			rSet = stmt.executeQuery(sql);

			DefaultTableModel modelo = (DefaultTableModel) _table.getModel();
			modelo.setRowCount(0);

			while (rSet.next()) {

				// RECUPERA O ID DO PRODUTO
				String id = String.valueOf(rSet.getInt("id"));
				// RECUPERA O NOME DO PRODUTO
				String nome = rSet.getString("prd_nome");
				// RECUPERA O TIPO DO PRODUTO
				String tipo = rSet.getString("prd_tipo_produto");
				// RECUPERA O VALOR DO PRODUTOR
				String valor = String.valueOf(rSet.getDouble("prd_valor_produto"));
				String tableData[] = { id, nome, tipo, valor };

				modelo.addRow(tableData);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {

			// FECHAR AS CONEXÕES

			try {

				if (rSet != null) {
					rSet.close();
				}
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
