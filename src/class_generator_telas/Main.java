package class_generator_telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import class_generator.ClassGenerator;

public class Main {

	private JFrame frame;
	private JTextField nomeProjeto;
	private JButton btnGerar;
	private JEditorPane csvCode;
	private JButton btnCancelar;
	public Main() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 491);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		csvCode = new JEditorPane();
		csvCode.setBounds(10, 82, 375, 359);
		frame.getContentPane().add(csvCode);
		
		JLabel lblNewLabel = new JLabel("Digite seu codigo aqui:");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 51, 228, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNomeDoProjeto = new JLabel("Nome do projeto:");
		lblNomeDoProjeto.setBounds(10, 26, 97, 14);
		frame.getContentPane().add(lblNomeDoProjeto);
		
		nomeProjeto = new JTextField();
		nomeProjeto.setBounds(101, 23, 284, 20);
		frame.getContentPane().add(nomeProjeto);
		nomeProjeto.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(395, 0, 269, 452);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				geraProjeto();
			}
		});
		btnGerar.setBounds(88, 75, 89, 23);
		panel.add(btnGerar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
			}
		});
		btnCancelar.setBounds(88, 250, 89, 23);
		panel.add(btnCancelar);
	}
	
	
	public void geraProjeto() {
		String csv = csvCode.getText();
		String projeto = nomeProjeto.getText();
		if(csv == null || !csv.contains(",")) {
			JOptionPane.showMessageDialog(null, "Digite um codigo válido!");
		}if(projeto.contains(".") || projeto.equals("")) {
			JOptionPane.showMessageDialog(null, "Digite um nome de projeto válido!");
		}else {

			try {
				ClassGenerator geraClasse = new ClassGenerator();
				geraClasse.geraClasse(csvCode.getText().replaceAll(" ", ""), nomeProjeto.getText().replaceAll(" ", ""));
				JOptionPane.showMessageDialog(null, "Projeto gerado com sucesso!!\nSalvo em: "+System.getProperty("user.dir")+"\\Projetos"+"\\"+projeto);	
			}catch(NullPointerException|ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Erro ao criar projeto!\nVerifique seu código.");
			}
			
		}
	}
	public void cancelar() {
		csvCode.setText("");

		nomeProjeto.setText("");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
