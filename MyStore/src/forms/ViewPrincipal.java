package forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewPrincipal extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	
	//GLOBAIS
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPrincipal frame = new ViewPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewPrincipal() {
		setResizable(false);
		setTitle("MyStore");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 510);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewProdutos vProdutos = new ViewProdutos();
				vProdutos.setVisible(true);
				setVisible(false);
			}
		});
		btnProdutos.setBounds(72, 45, 200, 40);
		contentPane.add(btnProdutos);
		
		JButton btnMenuClientes = new JButton("Clientes");
		btnMenuClientes.setEnabled(false);
		btnMenuClientes.setBounds(72, 215, 200, 40);
		contentPane.add(btnMenuClientes);
		
		JButton btnMenuVendas = new JButton("Vendas");
		btnMenuVendas.setEnabled(false);
		btnMenuVendas.setBounds(72, 300, 200, 40);
		contentPane.add(btnMenuVendas);
		
		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.setEnabled(false);
		btnDashboard.setBounds(72, 385, 200, 40);
		contentPane.add(btnDashboard);
		
		JButton btnEntradas = new JButton("Entradas");
		btnEntradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEntradas vEntradas = new ViewEntradas();
				vEntradas.setVisible(true);
				setVisible(false);
			}
		});
		btnEntradas.setBounds(72, 130, 200, 40);
		contentPane.add(btnEntradas);
	}
}
