package src.br.com.javamysqlextra2025.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMysql {
	private final String IP = "localhost";
	private final String PORTA = "3306";
	private final String LOGIN = "root";
	private final String SENHA = "root";
	private final String NOME_BD = "bd_java_mysql_extra_2025";
	private Connection conexao;

	public ConexaoMysql() {

	}

	public Connection getConexao() {
		return conexao;
	}

	// abrir a conexao com BD
	public void abrirConexao() {
		String url = "jdbc:mysql://" + IP + ":" + PORTA + "/" + NOME_BD;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conexao = DriverManager.getConnection(url, LOGIN, SENHA);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// fechar a conexao com BD
	public void fecharConexao() {
		// verificar se esta aberta a conexao
		try {
			if (conexao != null & !conexao.isClosed()) {
				conexao.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
