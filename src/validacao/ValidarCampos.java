package validacao;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import crud.DeletarDados;
import crud.EditarDados;
import crud.InserirDados;
import mensagens.MensagensErro;
import mensagens.MensagensSucesso;

public class ValidarCampos {
	MensagensErro mErro = new MensagensErro();
	MensagensSucesso mSucesso = new MensagensSucesso();
	DeletarDados dd = new DeletarDados();
	EditarDados ed = new EditarDados();
	InserirDados id = new InserirDados();
	
	public boolean validarPreenchimentoCamposProduto(JTextField _text1, JTextField _text2,
			JTextArea _textArea, JTextField _text4) {
		
		if(_text1.getText().isBlank() || _text2.getText().isBlank() || _textArea.getText().isBlank() 
				|| _text4.getText().isBlank()) {
			mErro.erroPreenchimentoCampos();
			return true;
		}else {
			return false;
		}
	}
	public boolean validarPreenchimentoCamposEntrada(JComboBox _combo, JTextField _text1, JTextField _text2, String _date1, String _date2) {
		
		System.out.println(_date1);
		System.out.println(_date2);
		
		if(_combo.getSelectedItem() == null || _text1.getText().isBlank() || _text2.getText().isBlank()
				|| _date1.equals("") || _date2.equals("")) {
			
			mErro.erroPreenchimentoCampos();
			return true;
		}else {
			return false;
		}
	}
	
	public void validarInsercaoEntrada(String _produto, double _valorCusto, int _qtd, String _dataEntrada,
			String _dataValidade, Object[] _object, JComboBox _combo, JTextField _text1, JTextField _text2,
			String _data1, String _data2, DefaultTableModel _model) {
		
		try {
			id.inserirEntrada(_produto, _valorCusto, _qtd, _dataEntrada, _dataValidade, _object, _combo,
					_text1, _text2, _data1, _data2, _model);
			mSucesso.cadastrarSucesso();
		} catch (Exception e) {
			mErro.erroInsercaoBanco();
		}
	}
	
	public void validarInsercaoProduto(String _nomeProduto, String _tipoProduto, String _descricaoProduto,
			double _valorVenda, Object[] _object, JTextField _text1, JTextField _text2, JTextField _text3,
			DefaultTableModel _model) {
		try {
			id.inserirProdutos(_nomeProduto,  _tipoProduto, _descricaoProduto, _valorVenda, _object, 
					_text1, _text2, _text3, _model);
			mSucesso.cadastrarSucesso();
		} catch (Exception e) {
			mErro.erroInsercaoBanco();
		}
	}
	
	
	public void validarEdicaoEntrada(String _produto, double _valorCusto, int _qtd, String _dataEntrada, 
			String _dataValidade, int _idEntrada, JTable _table) {
		
		if (JOptionPane.showConfirmDialog(null, "Deseja prosseguir com a edição?", "", JOptionPane.YES_NO_OPTION) 
				== JOptionPane.YES_OPTION) {
			try {
				ed.editarEntradas(_produto, _valorCusto, _qtd, _dataEntrada, _dataValidade, _idEntrada, _table);
				mSucesso.editarSucesso();
			} catch (Exception e) {
				mErro.erroAtualizarBanco();
			}
			
		}
	}
	
	public void validarEdicaoProduto(int _idProduto, String _nomeProduto, String _descricaoProduto, 
			String _tipoProduto, double _valorVenda, JTable _table) {
		
		if (JOptionPane.showConfirmDialog(null, "Deseja prosseguir com a edição?", "", JOptionPane.YES_NO_OPTION) 
				== JOptionPane.YES_OPTION) {
			try {
				ed.editarProdutos( _idProduto, _nomeProduto, _descricaoProduto,  _tipoProduto,  _valorVenda, _table);
				mSucesso.editarSucesso();
			} catch (Exception e) {
				mErro.erroAtualizarBanco();
			}
		}
	}
	
	public void validarExclusaoProduto(JTable _table, DefaultTableModel _model) {
		
		if(JOptionPane.showConfirmDialog(null, "Deseja prosseguir a exclusão?", "",  JOptionPane.YES_NO_OPTION)
				== JOptionPane.YES_OPTION) {
			try {
				dd.deletarProdutos(_table, _model);
				mSucesso.deletarSucesso();
			} catch (Exception e) {
				mErro.erroDeletarBanco();
			}
		}else {
			
		}	
	}
	
	public void validarExclusaoEntrada(JTable _table, DefaultTableModel _model) {
		
		if(JOptionPane.showConfirmDialog(null, "Deseja prosseguir a exclusão?", "",  JOptionPane.YES_NO_OPTION)
				== JOptionPane.YES_OPTION) {
			try {
				dd.deletarEntradas(_table, _model);
				mSucesso.deletarSucesso();
			} catch (Exception e) {
				mErro.erroDeletarBanco();
			}
		}else {
			
		}	
	}
}
