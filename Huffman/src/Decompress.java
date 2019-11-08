import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Decompress extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

public void SampleJFileChooser(){
		
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setCurrentDirectory(new File("C:\\Users\\Dell\\eclipse-workspace\\Huffman"));
		
		int result = jFileChooser.showOpenDialog(new JFrame());
	
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = jFileChooser.getSelectedFile();
		    String line = null;
			String all="";
	        try {
	            FileReader fileReader = new FileReader(selectedFile.getAbsolutePath());
	            BufferedReader bufferedReader =new BufferedReader(fileReader);
	            try {
					while((line = bufferedReader.readLine()) != null) {
						all+=line;
					}
					//write on another file
					
					 String newfile=(selectedFile.toString().replaceAll("comp.txt", "")+".txt");
					 FileWriter fileWriter =new FileWriter(newfile);
			         BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
			         bufferedWriter.write(Huffman.decomp(all,selectedFile.getParentFile().getAbsolutePath(),selectedFile.getName()));
			            
			         bufferedWriter.close();
					//store the table in the same dir. with suitable name
					//textField.setText(Huffman.comp(all));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}   

	            // Always close files.
	            try {
					bufferedReader.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println( "Unable to open file d.text ");                
	        }
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Decompress frame = new Decompress();
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
	public Decompress() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Choose File");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SampleJFileChooser();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.setBounds(153, 11, 125, 34);
		contentPane.add(button);
		
		JTextArea textArea = new JTextArea();
		textArea.setToolTipText("compressed text");
		textArea.setText("Or write here.....");
		textArea.setBounds(10, 68, 414, 57);
		contentPane.add(textArea);
		
		JButton button_1 = new JButton("Enter");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=textArea.getText();
				try {
					textField.setText(Huffman.decomp(s, "C:\\Users\\Dell\\eclipse-workspace\\Huffman", "comp"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_1.setBounds(167, 136, 94, 23);
		contentPane.add(button_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 170, 414, 80);
		contentPane.add(textField);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home frame = new Home();
				frame.setVisible(true);
			}
		});
		button_2.setIcon(new ImageIcon("C:\\Users\\Dell\\Downloads\\Webp.net-resizeimage (1).png"));
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_2.setBounds(0, 0, 29, 28);
		contentPane.add(button_2);
	}
}
