package herman;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.util.Scanner;

import javax.swing.JTextArea;

/**
 * 
 * @author KatherineHerman-Williams
 *
 */

public class Main {

	private JFrame frame;
	private JButton btnInvTable;
	private JButton btnAddBook;
	private JButton btnBookInfo;
	private JButton btnRemove;
	private JTextField textFieldTitle;
	private JTextField textFieldPrice;
	private JTextField textFieldQuant;
	private JTextField textFieldSku;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextArea textArea;
	private Book book;
	private static final File writer = new File(
			"/Users/KatherineHerman-Williams/eclipse-workspace/HW8bookstore/inventory");

	/**
	 * Launch the application.
	 * 
	 * @oaram args args
	 */
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

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Redlands Campus Bookstore Inventory");
		lblNewLabel.setBounds(100, 16, 249, 16);
		frame.getContentPane().add(lblNewLabel);

		btnAddBook = new JButton("Add a Book");
		btnAddBook.setBounds(289, 44, 142, 29);
		frame.getContentPane().add(btnAddBook);

		btnBookInfo = new JButton("Find a Book");
		btnBookInfo.setBounds(289, 74, 142, 29);
		frame.getContentPane().add(btnBookInfo);

		btnInvTable = new JButton("Display Inventory");
		btnInvTable.setBounds(290, 131, 141, 29);
		frame.getContentPane().add(btnInvTable);

		btnRemove = new JButton("Remove a Book");
		btnRemove.setBounds(289, 103, 142, 29);
		frame.getContentPane().add(btnRemove);

		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(163, 44, 114, 26);
		frame.getContentPane().add(textFieldTitle);
		textFieldTitle.setColumns(10);

		textFieldPrice = new JTextField();
		textFieldPrice.setBounds(163, 74, 114, 26);
		frame.getContentPane().add(textFieldPrice);
		textFieldPrice.setColumns(10);

		textFieldQuant = new JTextField();
		textFieldQuant.setBounds(163, 103, 114, 26);
		frame.getContentPane().add(textFieldQuant);
		textFieldQuant.setColumns(10);

		textFieldSku = new JTextField();
		textFieldSku.setBounds(164, 131, 114, 26);
		frame.getContentPane().add(textFieldSku);
		textFieldSku.setColumns(10);

		lblNewLabel_1 = new JLabel("Title:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(27, 49, 97, 16);
		frame.getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Price:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(27, 79, 97, 16);
		frame.getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Quantity:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(27, 108, 97, 16);
		frame.getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("SKU:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(27, 134, 99, 21);
		frame.getContentPane().add(lblNewLabel_4);

		textArea = new JTextArea();
		textArea.setBounds(27, 172, 404, 85);
		frame.getContentPane().add(textArea);
	}

	private void createEvents() {
		btnAddBook.addActionListener(new ActionListener() {// Add Book Button
			public void actionPerformed(ActionEvent e) {
				try {
					addBook();
				} catch (IOException e1) {// error forgot try catch
					e1.printStackTrace();
				}

			}
		});

		btnBookInfo.addActionListener(new ActionListener() {// Find Book Button
			public void actionPerformed(ActionEvent e) {
				try {
					findBook();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			}
		});

		btnInvTable.addActionListener(new ActionListener() {// Display Inventory Button
			public void actionPerformed(ActionEvent e) {
				try {
					showInv();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnRemove.addActionListener(new ActionListener() {// Remove Book Button
			public void actionPerformed(ActionEvent e) {
				try {
					removeBook();
				} catch (IOException e1) {// need IOException try catch
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}

	private void addBook() throws IOException {
		Book book = new Book(textFieldTitle.getText(), Double.parseDouble(textFieldPrice.getText()),
				Integer.parseInt(textFieldQuant.getText()), Integer.parseInt(textFieldSku.getText()));// creates a book
																										// with
																										// attributes
																										// taken from
																										// textFields

		sendToFile(book.store());// stores book in inventory text file
									// error solved: object String, store method in book class store makes book a
									// String

	}

	private void sendToFile(String book) throws IOException {// error solved: needs to be String, stores book in
																// inventory text file
		BufferedWriter in = new BufferedWriter(new FileWriter(writer, true));// allows to transition from file to
																				// filewriter
		in.write(book + System.getProperty("line.separator"));// writes to inventory file
		in.close();// executes writing

	}

	private void findBook() throws FileNotFoundException {// helper method
		searchLib();
	}

	private void searchLib() throws FileNotFoundException {// finds book from inventory text file
		Scanner in = new Scanner(new File("/Users/KatherineHerman-Williams/eclipse-workspace/HW8bookstore/inventory"));// scans
																														// file

		while (in.hasNext()) {// looks for words
			String checkLine = in.nextLine();// checks each line
			String[] split = checkLine.split("\t");// stores each word in an array
			String sku = split[3].trim();// sku is last position in string array

			if (Integer.parseInt(textFieldSku.getText()) == Integer.parseInt(sku)) {// if sku entered = sku found
				String output = "Stock Keeping Unit: " + split[3].trim() + "\nTitle: " + split[0].trim() + "\nPrice: $"
						+ split[1].trim() + "\nQuantity: " + split[2].trim();// creates string of book information for
																				// that sku
				textArea.setText(null);// empties out text area
				textArea.setText(output);// displays book in out text area
			}
		}

	}

	private void showInv() throws FileNotFoundException {// shows info for all books in inventory
		Scanner in = new Scanner(new File("/Users/KatherineHerman-Williams/eclipse-workspace/HW8bookstore/inventory"));// scans
																														// file

		while (in.hasNext()) {// looks for words

			String checkLine = in.nextLine();// looks for each line
			String[] split = checkLine.split("\t");// stores each word in an array
			String output = "Stock Keeping Unit: " + split[3].trim() + "\nTitle: " + split[0].trim() + "\nPrice: $"
					+ split[1].trim() + "\nQuantity: " + split[2].trim();// creates string of book info for that book
			textArea.setText(output);// displays all books
		}

	}

	private void removeBook() throws IOException {
		destroyBook();

	}// solved bracket syntax error ugh

	private void destroyBook() throws IOException {// error?- solved syntax " IOException() //removes book from
													// inventory
		Scanner scn = new Scanner(new File("/Users/KatherineHerman-Williams/eclipse-workspace/HW8bookstore/inventory"));// scans
																														// file
		File fi = new File("library.txt");// temporary file
		BufferedWriter in = new BufferedWriter(new FileWriter(fi, true));// buffer from file to filewriter, true allows
																			// file to be appended

		while (scn.hasNext()) {// looks for words in file

			String checkLine = scn.nextLine();// looks at each line
			String[] split = checkLine.split("\t");// stores words in an array
			String sku = split[3].trim();// finds sku in array which is always the 4th index

			if (Integer.parseInt(textFieldSku.getText()) == Integer.parseInt(sku)) {// empty if statement due to program
																					// not seeing the !=

			} else {// if sku isn't sku

				in.write(checkLine + System.getProperty("line.separator"));// moves books to temporary file except
																			// deleted book

			}

		}

		in.close();
		boolean works = fi.renameTo(writer);// temporary file is now inventory
	}
}
