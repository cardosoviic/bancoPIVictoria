package view;



import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.DAO;
import net.proteanit.sql.DbUtils;

public class ReservasView extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReservasView() {
		setTitle("ReservasView");
		setResizable(false);
		setBounds(new Rectangle(300, 100, 724, 446));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 11, 653, 371);
		getContentPane().add(scrollPane);

		tblFuncionarios = new JTable();
		scrollPane.setViewportView(tblFuncionarios);
		buscarFuncionarioNaTabela();

	

	} // Fim do construtor

	// Criar um objeto da classe DAO para estabelecer conexão com banco
	DAO dao = new DAO();
	private JTable tblFuncionarios;

	private void buscarFuncionarioNaTabela() {
		String readTabela = "select * from reservas;";

		try {
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execução dos comandos SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readTabela);


			// Executar o comando SQL
			ResultSet resultadoExecucao = executarSQL.executeQuery();

			// Exibir o resultado na tabela, utilização da biblioteca rs2xml para "popular"
			// a tabela
			tblFuncionarios.setModel(DbUtils.resultSetToTableModel(resultadoExecucao));

			conexaoBanco.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasView dialog = new ReservasView();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

