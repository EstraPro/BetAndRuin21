package gui;

import java.awt.BorderLayout;

import java.io.Closeable;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import businessLogic.BlFacade;
import businessLogic.BlFacadeImplementation;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class InsertResultsGUI extends JFrame {

	private JPanel contentPane;
	private JTextField inputText;
	private JTextArea errorArea;
	private BlFacade businessLogic;
	private MainGUI prevFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertResultsGUI frame = new InsertResultsGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setBusinessLogic(BlFacade checker) {
		businessLogic = checker;
	}

	/**
	 * Create the frame.
	 */
	public InsertResultsGUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		inputText = new JTextField();
		inputText.setBounds(26, 99, 366, 20);
		contentPane.add(inputText);
		inputText.setColumns(10);

		JLabel lblFilePath = new JLabel("Introduce where the results file is placed in your computer:");
		lblFilePath.setBounds(26, 74, 380, 14);
		contentPane.add(lblFilePath);

		JLabel lblTitle = new JLabel("Results");
		lblTitle.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(140, 11, 141, 37);
		contentPane.add(lblTitle);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorArea.setText("");
				if (!inputText.getText().equals("")) {
					File file;

					try {
						file = new File(inputText.getText());

						/**
						 * Scan the results, lortu arrayList bat zutabe bakoitzarentzat. Sartu diot
						 * question type. Emaitza bakoitzak question bati erantzungo diolako. Horrela
						 * determina dezakegu sartuko duen resulta Adibidez question tipe-a aukera
						 * mugatuena bada, esan ahal diogu sartzeko erantzuna espezifikoki. Edo
						 * erantzuna emaitza exaktua bada hori sartzeko zuzenean... Zutabeak: Question
						 * type-a, Event-a, Result-a, Data
						 * 
						 * Hutsik dauden zutabeak ez kontuan hartu listan, ez gorde (" ") adibidez
						 */
						//
						POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
						HSSFWorkbook wb = new HSSFWorkbook(fs);
						HSSFSheet sheet = wb.getSheetAt(0);
						HSSFRow row;
						HSSFCell cell;

						int rows; // No of rows
						rows = sheet.getPhysicalNumberOfRows();

						ArrayList<String> eventList = new ArrayList<String>();
						ArrayList<Integer> questionType = new ArrayList<Integer>();
						ArrayList<String> resultList = new ArrayList<String>();
						ArrayList<String> questionsContent = new ArrayList<String>();
						ArrayList<Date> dateList = new ArrayList<Date>();

						for (int r = 0; r < rows; r++) {
							row = sheet.getRow(r);
							if (row != null) {
								for (int c = 0; c < 5; c++) {
									cell = row.getCell((short) c);
									if (cell != null) {
										
										switch (c) {
										case 0:
											eventList.add(cell.getStringCellValue());
											break;
										case 1:
											questionType.add(Integer.parseInt(cell.getStringCellValue()));
											break;
										case 2:
											resultList.add(cell.getStringCellValue());
											break;
										case 3:
											questionsContent.add(cell.getStringCellValue());
											break;
										case 4:
											String sDate1=cell.getStringCellValue();  
										    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
											dateList.add(date1);
											break;
										default:
									
										}

									}
								}
							}
						}
						int depends = businessLogic.manageResults(eventList, questionType, resultList,questionsContent, dateList);
						switch (depends) {
						case 0:
							errorArea.setText("Results entered and managed!");
							break;
						case 1:
							errorArea.setText("Something failed: Some part is missing.");
							break;
						case 2:
							errorArea.setText("Something failed: Something is written incorrectly.");
							break;
						case 3:
							errorArea.setText("Something failed: Dates are incorrect.");
							break;
						}

					} catch (FileNotFoundException e1) {
					
					} catch (IOException e1) {
						errorArea.setText("Can't find a existing file with that path.");
						System.out.println("Some thing wrong with I/O");
						e1.printStackTrace();
					} catch (ParseException e1) {
						System.out.println("Some date is written wrong");
						errorArea.setText("Some date is written wrong");
						e1.printStackTrace();
					}

				} else {
					errorArea.setText("Please enter a path.");
				}

			}
		});
		btnSubmit.setBounds(303, 227, 89, 23);
		contentPane.add(btnSubmit);

		errorArea = new JTextArea();
		errorArea.setBounds(26, 150, 366, 48);
		contentPane.add(errorArea);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prevFrame.setVisible(true);
			}
		});
		btnClose.setBounds(26, 227, 89, 23);
		contentPane.add(btnClose);
	}

	/**
	 * Gets the previous frame
	 * 
	 * @param frame
	 */
	public void previousFrame(MainGUI frame) {

		prevFrame = frame;
	}
}
