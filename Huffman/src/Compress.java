
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import com.jgc.areyes.main.SampleJFileChooser;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Compress extends JFrame {

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
					
					 String newfile=(selectedFile.toString().replaceAll(".txt", "")+"comp.txt");
					 FileWriter fileWriter =new FileWriter(newfile);
			         BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
			         bufferedWriter.write(Huffman.comp(all,selectedFile.getParentFile().getAbsolutePath(),selectedFile.getName()));
			            
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
					Compress frame = new Compress();
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
	public Compress() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Choose File");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SampleJFileChooser();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(152, 11, 125, 34);
		contentPane.add(btnNewButton);
		
		JTextArea txtrOrWriteHere = new JTextArea();
		txtrOrWriteHere.setText("Or write here.....");
		txtrOrWriteHere.setToolTipText("Original text that will be compressed");
		txtrOrWriteHere.setBounds(10, 68, 414, 57);
		contentPane.add(txtrOrWriteHere);
		
		textField = new JTextField();
		textField.setBounds(10, 170, 414, 80);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s=txtrOrWriteHere.getText();
				textField.setText(Huffman.comp(s, "C:\\Users\\Dell\\eclipse-workspace\\Huffman", ""));
			}
		});
		btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEnter.setBounds(167, 136, 94, 23);
		contentPane.add(btnEnter);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Compress p=new Compress();
				p.setVisible(false);
				Home frame = new Home();
				frame.setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Dell\\Downloads\\Webp.net-resizeimage (1).png"));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(0, 0, 29, 28);
		contentPane.add(btnNewButton_1);
	}
}
