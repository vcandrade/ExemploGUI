package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import entities.Aluno;
import entities.Curso;
import service.AlunoService;
import service.CursoService;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

public class AlunoWindow extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem itemSair;
	private JMenu menuAjuda;
	private JMenuItem itemSobre;
	private JLabel lblRegistroAcademico;
	private JTextField txtRegistroAcademico;
	private JLabel lblNome;
	private JTextField txtNome;
	private JPanel painelSexo;
	private JRadioButton rbMasculino;
	private JRadioButton rbFeminino;
	private JRadioButton rbNaoInformar;
	private JLabel lblCurso;
	private JComboBox cbCurso;
	private JLabel lblDataIngresso;
	private JFormattedTextField txtDataIngresso;
	private JLabel lblPeriodo;
	private JSpinner spPeriodo;
	private JLabel lblCoeficiente;
	private JTextField txtCoeficiente;
	private JSeparator separator;
	private JButton btnCadastrar;
	private JButton btnLimparCampos;
	private JPanel painelAlunos;
	private JTable tblAlunos;
	private JScrollPane scrollPane;

	private MaskFormatter mascaraData;

	private CursoService cursoService;
	private AlunoService alunoService;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public AlunoWindow() {

		this.criarMascaraData();
		this.initComponents();

		this.cursoService = new CursoService();
		this.alunoService = new AlunoService();

		this.buscarCursos();
		this.buscarAlunos();
		this.limparComponentes();
	}

	private void limparComponentes() {

		this.txtRegistroAcademico.setText("");
		this.txtNome.setText("");
		this.rbNaoInformar.setSelected(true);
		this.cbCurso.setSelectedIndex(0);
		this.txtDataIngresso.setText("");
		this.spPeriodo.setValue(1);
		this.txtCoeficiente.setText("");
	}

	private void buscarAlunos() {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		DefaultTableModel modelo = (DefaultTableModel) tblAlunos.getModel();
		modelo.fireTableDataChanged();
		modelo.setRowCount(0);

		List<Aluno> alunos = this.alunoService.buscarTodos();

		for (Aluno aluno : alunos) {

			modelo.addRow(new Object[] { 
				aluno.getRegistroAcademico(), 
				aluno.getNome(), 
				aluno.getSexo(),
				aluno.getCurso().getNome(), 
				formato.format(aluno.getDataIngresso()), // formatando a data no modelo dd/MM/yyyy
				aluno.getPeriodo(), 
				aluno.getCoeficiente() 
			});
		}
	}

	private void buscarCursos() {

		List<Curso> cursos = this.cursoService.buscarTodos();

		for (Curso curso : cursos) {

			this.cbCurso.addItem(curso);
		}
	}

	private void criarMascaraData() {

		try {

			this.mascaraData = new MaskFormatter("##/##/####");

		} catch (ParseException e) {

			System.out.println("ERRO: " + e.getMessage());
		}
	}

	private void cadastrarAluno() {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Aluno aluno = new Aluno();

			aluno.setRegistroAcademico(Integer.parseInt(this.txtRegistroAcademico.getText()));
			aluno.setNome(this.txtNome.getText());
			aluno.setSexo(verificarSelecaoRadioButtonSexo());
			aluno.setCurso((Curso) this.cbCurso.getSelectedItem());
			aluno.setDataIngresso(new java.sql.Date(sdf.parse(this.txtDataIngresso.getText()).getTime()));

			aluno.setPeriodo(Integer.parseInt(this.spPeriodo.getValue().toString()));
			aluno.setCoeficiente(Double.parseDouble(this.txtCoeficiente.getText()));

			this.alunoService.cadastrar(aluno);
			this.buscarAlunos();

		} catch (ParseException e) {

			System.out.println("ERRO: " + e.getMessage());
		}
	}

	private String verificarSelecaoRadioButtonSexo() {

		if (this.rbMasculino.isSelected()) {
			return this.rbMasculino.getText();
		} else if (this.rbFeminino.isSelected()) {
			return this.rbFeminino.getText();
		} else {
			return this.rbNaoInformar.getText();
		}
	}

	private void finalizarAplicacao() {

		System.exit(0);
	}

	private void abrirJanelaSobre() {

		SobreWindow janelaSobre = new SobreWindow(this);
		janelaSobre.setVisible(true);

		this.setVisible(false);
	}

	public void initComponents() {

		setTitle("Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 650, 709);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuArquivo = new JMenu("Arquivo");
		menuBar.add(menuArquivo);

		itemSair = new JMenuItem("Sair");
		itemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				finalizarAplicacao();
			}
		});
		menuArquivo.add(itemSair);

		menuAjuda = new JMenu("Ajuda");
		menuBar.add(menuAjuda);

		itemSobre = new JMenuItem("Sobre");
		itemSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirJanelaSobre();
			}
		});
		menuAjuda.add(itemSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblRegistroAcademico = new JLabel("Registro Acad\u00EAmico");
		lblRegistroAcademico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRegistroAcademico.setBounds(25, 21, 131, 23);
		contentPane.add(lblRegistroAcademico);

		txtRegistroAcademico = new JTextField();
		txtRegistroAcademico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRegistroAcademico.setBounds(166, 22, 143, 22);
		contentPane.add(txtRegistroAcademico);
		txtRegistroAcademico.setColumns(10);

		lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(25, 66, 46, 23);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setBounds(81, 66, 520, 23);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		painelSexo = new JPanel();
		painelSexo.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelSexo.setBounds(25, 129, 194, 156);
		contentPane.add(painelSexo);
		painelSexo.setLayout(null);

		rbMasculino = new JRadioButton("Masculino");
		buttonGroup.add(rbMasculino);
		rbMasculino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbMasculino.setBounds(18, 29, 109, 31);
		painelSexo.add(rbMasculino);

		rbFeminino = new JRadioButton("Feminino");
		buttonGroup.add(rbFeminino);
		rbFeminino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbFeminino.setBounds(18, 63, 109, 31);
		painelSexo.add(rbFeminino);

		rbNaoInformar = new JRadioButton("N\u00E3o Informar");
		buttonGroup.add(rbNaoInformar);
		rbNaoInformar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rbNaoInformar.setBounds(18, 97, 109, 31);
		painelSexo.add(rbNaoInformar);

		lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCurso.setBounds(250, 129, 46, 23);
		contentPane.add(lblCurso);

		cbCurso = new JComboBox();
		cbCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbCurso.setBounds(306, 131, 295, 22);
		contentPane.add(cbCurso);

		lblDataIngresso = new JLabel("Data de Ingresso");
		lblDataIngresso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataIngresso.setBounds(250, 183, 112, 23);
		contentPane.add(lblDataIngresso);

		txtDataIngresso = new JFormattedTextField(mascaraData); // adiciona a máscara ao componente
		txtDataIngresso.setBounds(372, 186, 97, 20);
		contentPane.add(txtDataIngresso);

		lblPeriodo = new JLabel("Per\u00EDodo");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPeriodo.setBounds(494, 183, 46, 23);
		contentPane.add(lblPeriodo);

		spPeriodo = new JSpinner();
		spPeriodo.setModel(new SpinnerNumberModel(1, 1, 10, 1)); // valor de início, valor mínimo, valor máximo, incremento
		spPeriodo.setBounds(550, 186, 50, 20);
		contentPane.add(spPeriodo);

		lblCoeficiente = new JLabel("Coeficiente");
		lblCoeficiente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCoeficiente.setBounds(250, 237, 77, 23);
		contentPane.add(lblCoeficiente);

		txtCoeficiente = new JTextField();
		txtCoeficiente.setBounds(337, 237, 86, 23);
		contentPane.add(txtCoeficiente);
		txtCoeficiente.setColumns(10);

		separator = new JSeparator();
		separator.setBounds(25, 296, 576, 12);
		contentPane.add(separator);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				cadastrarAluno();
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrar.setBounds(284, 319, 154, 38);
		contentPane.add(btnCadastrar);

		btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				limparComponentes();
			}
		});
		btnLimparCampos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLimparCampos.setBounds(447, 319, 154, 38);
		contentPane.add(btnLimparCampos);

		painelAlunos = new JPanel();
		painelAlunos.setBorder(new TitledBorder(null, "Alunos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelAlunos.setBounds(25, 368, 576, 269);
		contentPane.add(painelAlunos);
		painelAlunos.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 556, 235);
		painelAlunos.add(scrollPane);

		tblAlunos = new JTable();
		scrollPane.setViewportView(tblAlunos);
		tblAlunos.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "RA", "Nome", "Sexo", "Curso", "Data do Ingresso", "Período", "Coeficiente" }));

		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlunoWindow frame = new AlunoWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
