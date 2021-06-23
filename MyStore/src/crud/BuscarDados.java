package crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.FabricaConexao;

public class BuscarDados {

	public void BuscarProdutos(JTable _table) {
		Connection conexao;

		try {
			conexao = FabricaConexao.abrirConexao();
			String sql = "SELECT * FROM tb_produtos";
			Statement stmt = conexao.createStatement();
			ResultSet rSet = stmt.executeQuery(sql);
			
			DefaultTableModel modelo = (DefaultTableModel) _table.getModel();
			modelo.setRowCount(0);
			
			while(rSet.next()) {
				String id = String.valueOf(rSet.getInt("id"));
				String nome = rSet.getString("prd_nome");
				String tipo = rSet.getString("prd_tipo_produto");
				String valor = String.valueOf(rSet.getDouble("prd_valor_produto"));
				String tableData[] = { id, nome, tipo, valor };

				modelo.addRow(tableData);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
