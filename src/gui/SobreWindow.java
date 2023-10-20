package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SobreWindow extends JFrame {

	private JPanel contentPane;
	private JLabel lblTitulo;
	private JLabel lblDesenvolvedor;
	private JLabel lblUniversidade;
	private JButton btnOk;
	private AlunoWindow alunoWindow;

	public SobreWindow(AlunoWindow alunoWindow) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {

				fecharJanela();
			}
		});

		this.initComponents();
		this.alunoWindow = alunoWindow;
	}

	private void fecharJanela() {

		this.dispose();
		this.alunoWindow.setVisible(true);
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

				fecharJanela();
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOk.setBounds(118, 352, 203, 41);
		contentPane.add(btnOk);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(".\\src\\gui\\image\\utfpr.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(79, 208, 283, 83);
		contentPane.add(lblNewLabel);
		
		setLocationRelativeTo(null);
	}
}
