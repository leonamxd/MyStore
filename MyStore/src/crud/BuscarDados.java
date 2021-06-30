package crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jdbc.FabricaConexao;

public class BuscarDados {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	// METODO PARA PEGAR STRING DA DESCRIÇÃO E MOSTRAR NA TABLE
	public String BuscarTextArea(JTable _table) {

		// VARIAVEL QUE RECEBERÁ A STRING
		String txtArea = "";

		Connection conexao = null;
		Statement stmt = null;
		// CLASSE PARAR RECUPERAR DADOS DO BANCO
		ResultSet rSet = null;

		// VARIAVEL PARA PEGAR O ID DA LINHA SELECIONADA
		int row = _table.getSelectedRow();
		// VARIAVEL PARA PEGAR O VALOR A PARTIR DO ID E DA COLUNA
		int idCelula = Integer.parseInt(_table.getModel().getValueAt(row, 0).toString());

		// QUERY DO SQL
		String sql = "SELECT prd_descricao_prod FROM tb_produtos WHERE id = " + idCelula;

		try {

			// CRIAR UMA CONEXÃO COM O BANCO DE DADOS
			conexao = FabricaConexao.abrirConexao();

			// STATEMENTE PARA EXECUTAR UMA QUERY
			stmt = conexao.createStatement();
			rSet = stmt.executeQuery(sql);
			while (rSet.next()) {
				txtArea = rSet.getString("prd_descricao_prod");
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
		return txtArea;
	}

	// METODO PARA PEGAR NOMES DOS PRODUTOS E ATRIBUILOS A COMBOBOX DA TELA DE
	// ENTRADAS
	public void PreencherProdutosComboBox(JComboBox<String> _comboBox) {

		// QUERY DO SQL
		String sql = "SELECT prd_nome FROM tb_produtos";

		Connection conexao = null;
		Statement stmt = null;
		// CLASSE PARAR RECUPERAR DADOS DO BANCO
		ResultSet rSet = null;

		try {

			// CRIAR UMA CONEXÃO COM O BANCO DE DADOS
			conexao = FabricaConexao.abrirConexao();

			// STATEMENTE PARA EXECUTAR UMA QUERY
			stmt = conexao.createStatement();
			rSet = stmt.executeQuery(sql);

			while (rSet.next()) {
				String comboBoxProduto = rSet.getString("prd_nome");
				_comboBox.addItem(comboBoxProduto);
//				System.out.println(rSet.getString("prd_nome"));
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {

			try {

				// FECHAR AS CONEXÕES

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

	public void BuscarEntradas(JTable _table) {

		String sql = "SELECT tb_entrada.id, ent_data_cadastro, ent_data_validade_prod, ent_qntd_produto, ent_valor_custo_prod, prd_nome"
				+ " FROM tb_entrada"
				+ " INNER JOIN tb_produtos"
				+ " ON tb_entrada.fk_prd_produto = tb_produtos.id";

		Connection conexao = null;
		Statement stmt = null;
		ResultSet rSet = null;

		try {

			conexao = FabricaConexao.abrirConexao();
			stmt = conexao.createStatement();
			rSet = stmt.executeQuery(sql);

			DefaultTableModel modelo = (DefaultTableModel) _table.getModel();
			modelo.setRowCount(0);

			while (rSet.next()) {

				String id = String.valueOf(rSet.getInt("id"));
				String produto = rSet.getString("prd_nome");
				String valor = String.valueOf(rSet.getDouble("ent_valor_custo_prod"));
				String qtd = String.valueOf(rSet.getInt("ent_qntd_produto"));
				String dataValidade = sdf.format(sdf.parse(rSet.getString("ent_data_validade_prod")));
				String dataEntrada = sdf.format(sdf.parse(rSet.getString("ent_data_cadastro")));
				String tableData[] = { id, produto, valor, qtd, dataValidade, dataEntrada };

				modelo.addRow(tableData);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {

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

	public void BuscarProdutos(JTable _table) {

		// QUERY DO SQL
		String sql = "SELECT * FROM tb_produtos";

		Connection conexao = null;
		Statement stmt = null;
		// CLASSE PARAR RECUPERAR DADOS DO BANCO
		ResultSet rSet = null;

		try {

			// CRIAR UMA CONEXÃO COM O BANCO DE DADOS
			conexao = FabricaConexao.abrirConexao();

			// PREPARESTATEMENTE PARA EXECUTAR UMA QUERY
			stmt = conexao.createStatement();
			rSet = stmt.executeQuery(sql);

			// PEGAR O MODELO QUE SETA A FILEIRA COMO 0
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
