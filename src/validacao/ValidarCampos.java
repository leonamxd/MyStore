package validacao;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import crud.BuscarDados;
import mensagens.MensagensErro;
import mensagens.MensagensSucesso;

public class ValidarCampos {
	MensagensErro mErro = new MensagensErro();
	MensagensSucesso mSucesso = new MensagensSucesso();
	BuscarDados bd = new BuscarDados();
	
	public boolean validarQuantidadeProduto(JTable _table) {
		int row = _table.getSelectedRow();
		String id = _table.getModel().getValueAt(row, 0).toString();
		
		if(Integer.parseInt(bd.buscarQuantidade(id)) > 0) {
			mErro.erroDeletarProdutoComEntradas();
			return false;
		}
		
		return true;
	}
	
	public void validarTipoNumerico(String _text1, JTextField _text2) {

		int stringLength = _text1.length();

		if (_text1.substring(0, stringLength).matches("^\\d+(\\.|,|\\d+)*$")) {
			
		} else {
			
			_text2.setText("");
			mErro.erroTipoCampo();
		}

	}

	public boolean validarPreenchimentoCamposProduto(JTextField _text1, JComboBox _text2, JTextArea _textArea,
			JTextField _text4) {

		if (_text1.getText().isBlank() || _text2.getSelectedItem().equals(null) || _textArea.getText().isBlank()
				|| _text4.getText().isBlank()) {
			mErro.erroPreenchimentoCampos();
			return true;
		} else {
			return false;
		}
	}

	public boolean validarPreenchimentoCamposEntrada(JComboBox _combo, JTextField _text1, JTextField _text2, JDateChooser _date2) {

		if (_combo.getSelectedItem() == null || _text1.getText().isBlank() || _text2.getText().isBlank()
				|| _date2.getCalendar() == null) {

			mErro.erroPreenchimentoCampos();
			return false;
		} else {
			return true;
		}
		
	}
}
