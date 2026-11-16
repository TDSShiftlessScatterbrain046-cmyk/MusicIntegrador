package src.br.com.javamysqlextra2025.apresentacao;

import src.br.com.exemplojavamysqlextra2025.model.Usuario;
import src.br.com.javamysqlextra2025.persistencia.UsuarioDAO;

public class Teste {

	public static void main(String[] args) {
		
		UsuarioDAO uDAO = new UsuarioDAO();
		
		//Adiciona Livia na ultima posição disponivel(AutoIncrement Push)
		Usuario New = new Usuario(64, "L�via", "liviaremor4@gmail.com", "123");
		uDAO.salvar(New);

		//Edita a posição 3 para ser o eggman, update não funciona na posição errada
//		Usuario edit = new Usuario(12, "Eggman", "ImtheWalrus@gmail.com", "Gogocachu");
//		uDAO.editar(edit);		
		
		Usuario u = uDAO.buscarPorId(7);
		Usuario[] Us = uDAO.buscarTodos();
		
//		uDAO.excluirPorId(3);
		
//		uDAO.excluirTodosNUKE();

		
		System.out.println(u.toString());
		for (int i=0;i<Us.length;i++) {
			System.out.println(Us[i]);
		}
		
	}

}
