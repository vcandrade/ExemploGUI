package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SobreWindow extends JFrame {

	private JPanel contentPane;
	private JLabel lblTitulo;
	private JLabel lblDesenvolvedor;
	private JLabel lblUniversidade;
	private JButton btnOk;

	public SobreWindow() {

		this.initComponents();
	}

	private void btnOkOkActionPerformed() {

		this.dispose();
	}

	private void initComponents() {

		setTitle("Sobre");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTitulo = new JLabel("Exemplo GUI");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 27));
		lblTitulo.setBounds(79, 28, 283, 75);
		contentPane.add(lblTitulo);

		lblDesenvolvedor = new JLabel("Prof. Vin\u00EDcius Camargo Andrade");
		lblDesenvolvedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesenvolvedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDesenvolvedor.setBounds(79, 105, 283, 35);
		contentPane.add(lblDesenvolvedor);

		lblUniversidade = new JLabel("Universidade Tecnol\u00F3gica Federal do Paran\u00E1");
		lblUniversidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblUniversidade.setBounds(79, 140, 283, 14);
		contentPane.add(lblUniversidade);

		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnOkOkActionPerformed();
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOk.setBounds(146, 352, 147, 41);
		contentPane.add(btnOk);
		
		setLocationRelativeTo(null);
	}
}
