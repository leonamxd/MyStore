package crud;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import jdbc.FabricaConexao;

public class InserirDados {
	public void inserirProdutos(String _nomeProduto, String _tipoProduto, String _descricaoProduto,
			float _valorVenda) {

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
			stmt.setFloat(4, _valorVenda);
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
