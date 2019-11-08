import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomehuffman = new JLabel("Welcome @Huffman");
		lblWelcomehuffman.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblWelcomehuffman.setBounds(137, 11, 158, 34);
		contentPane.add(lblWelcomehuffman);
		
		JButton btnCompress = new JButton("Compress");
		btnCompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Compress p=new Compress();
				p.setVisible(true);
				Home frame = new Home();
				frame.setVisible(false);
			}
		});
		btnCompress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCompress.setBounds(137, 83, 151, 47);
		contentPane.add(btnCompress);
		
		JButton btnDecompress = new JButton("Decompress");
		btnDecompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decompress p=new Decompress();
				p.setVisible(true);
			}
		});
		btnDecompress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDecompress.setBounds(137, 158, 151, 47);
		contentPane.add(btnDecompress);
	}
}
