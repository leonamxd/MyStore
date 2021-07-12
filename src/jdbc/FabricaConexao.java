package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

	// CAMINHO DO BANCO DE DADOS, PORTA, NOME BANCO DE DADOS
	private static final String URL = "jdbc:mysql://localhost:3306/db_mystore";

	// NOME DO USUARIO MYSQL
	private static final String USERNAME = "root";

	// SENHA DO BANCO
	private static final String PASSWORD = "";

	/*
	 * CONEXÃO COM O BANCO DE DADOS
	 */

	public static Connection abrirConexao() {

		// FAZ COM QUE A CLASSE SEJA CARREGADA PELA JVM
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		// CRIA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = null;

		try {

			conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Erro de conexão: " + e);
		}

		return conexao;
	}
}
