package src.br.com.javamysqlextra2025.persistencia;

import java.sql.*;
import java.util.*;

import src.br.com.exemplojavamysqlextra2025.model.Usuario;

public class UsuarioDAO {

	private ConexaoMysql conexaoMysql;

	public UsuarioDAO() {
		super();
		conexaoMysql = new ConexaoMysql();
	}

	// salvar
	public void salvar(Usuario usuario) {
		conexaoMysql.abrirConexao();
		String sql = "INSERT INTO usuario VALUES(null, ?, ?, ?);";
		PreparedStatement st;
		try {
			st = conexaoMysql.getConexao().prepareStatement(sql);
			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getEmail());
			st.setString(3, usuario.getSenha());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexaoMysql.fecharConexao();
		}
	}

	//editar
	public void editar(Usuario usuario) {
		conexaoMysql.abrirConexao();
		String sql = "UPDATE usuario SET nome=?, email=?, senha=? WHERE id_usuario=?;";
		PreparedStatement st;
		try {
			st = conexaoMysql.getConexao().prepareStatement(sql);
			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getEmail());
			st.setString(3, usuario.getSenha());
			st.setLong(4, usuario.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexaoMysql.fecharConexao();
		}
	}

	// excluirPorId
	public void excluirPorId(long id) {
		conexaoMysql.abrirConexao();
		String sql = "Delete FROM usuario WHERE id_usuario=?";
		PreparedStatement st;
		try {
			st = conexaoMysql.getConexao().prepareStatement(sql);
			st.setLong(1, id);
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexaoMysql.fecharConexao();
		}
	}

	// excluirTodos
	public void excluirTodosNUKE() {
		conexaoMysql.abrirConexao();
		String sql = "Delete FROM usuario";
		PreparedStatement st;
		try {
			st = conexaoMysql.getConexao().prepareStatement(sql);
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexaoMysql.fecharConexao();
		}
	}
	
	// buscarPorId
	public Usuario buscarPorId(long id) {
		Usuario u = null;
		conexaoMysql.abrirConexao();
		String sql = "SELECT * FROM usuario WHERE id_usuario=?";
		PreparedStatement st;
		try {
			st = conexaoMysql.getConexao().prepareStatement(sql);
			st.setLong(1, id);
			ResultSet rs = st.executeQuery();

			// converter um ResultSet em um Usuario
			if (rs.next()) {
				// significa que retornou um usuario
				u = new Usuario();
				u.setId(rs.getLong("id_usuario"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setSenha(rs.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexaoMysql.fecharConexao();
		}
		return u;
	}

	// buscarTodos
	public Usuario[] buscarTodos() {
		ArrayList<Usuario> u = new ArrayList<Usuario>();
		conexaoMysql.abrirConexao();
		String sql = "SELECT * FROM usuario";
		PreparedStatement st;
		try {
			st = conexaoMysql.getConexao().prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			// converter um ResultSet em um Usuario
			for (int i=0;rs.next();i++) {
				// significa que retornou um usuario
				u.add(i, new Usuario());
				u.get(i).setId(rs.getLong("id_usuario"));
				u.get(i).setNome(rs.getString("nome"));
				u.get(i).setEmail(rs.getString("email"));
				u.get(i).setSenha(rs.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexaoMysql.fecharConexao();
		}
		Usuario[] Usuarios = new Usuario[u.size()];
		for (int i=0;i<Usuarios.length;i++) { 
			Usuarios[i] = u.get(i); 
		}; return Usuarios;
	}

}
