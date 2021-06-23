package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import jdbc.FabricaConexao;

public class InserirDados {
	public void inserirProdutos(String _nomeProduto, String _tipoProduto, String _descricaoProduto, double _valorVenda) {
		Connection conexao;
		
		try {
			conexao = FabricaConexao.abrirConexao();
			String sql = "INSERT INTO tb_produtos (prd_nome, prd_tipo_produto, prd_descricao_prod, prd_valor_produto) VALUES (?,?,?,?)";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, _nomeProduto);
			stmt.setString(2, _tipoProduto);
			stmt.setString(3, _descricaoProduto);
			stmt.setDouble(4, _valorVenda);
			
			stmt.execute();
			
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Erro de inserção");
		}
	}
}
