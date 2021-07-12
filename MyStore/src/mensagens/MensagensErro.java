package mensagens;

import javax.swing.JOptionPane;

public class MensagensErro {

	public void erroCadastroDuplicado() {
		JOptionPane.showMessageDialog(null, "Cadastro duplicado.","Erro: CG_001", JOptionPane.ERROR_MESSAGE);
	}
	public void erroTipoCampo() {
		JOptionPane.showMessageDialog(null, "Tipo do campo inv�lido, verifique informa��es digitadas.", "Erro: CG_002", JOptionPane.ERROR_MESSAGE);
	}
	public void erroPreenchimentoCampos() {
		JOptionPane.showMessageDialog(null, "Campos obrigat�rios n�o preenchidos.","Erro: CG_003", JOptionPane.ERROR_MESSAGE);
	}
	public void erroInsercaoBanco() {
		JOptionPane.showMessageDialog(null, "Falha em conex�o com banco.","Erro: CB_001" , JOptionPane.ERROR_MESSAGE);
	}
	public void erroAtualizarBanco() {
		JOptionPane.showMessageDialog(null, "Falha em conex�o com banco.", "Erro: CB_002", JOptionPane.ERROR_MESSAGE);
	}
	public void erroDeletarBanco() {
		JOptionPane.showMessageDialog(null, "Falha em conex�o com banco.", "Erro: CB_003", JOptionPane.ERROR_MESSAGE);
	}
	
}
