package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaConexao {
	public static Connection abrirConexao() {
		Connection conexao = null;
		
		try {
			final String url = "jdbc:mysql://localhost:3306/db_mystore";
			final String usuario = "admin";
			final String senha = "admin";
			
			conexao = DriverManager.getConnection(url, usuario, senha);
			
		} catch (Exception e) {
			System.out.println("Erro de conexão: " + e);
		}
		
		return conexao;
	}
}
