package gui;

import static TownRecommendationsTraveler.MapValues.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import TownRecommendationsTraveler.AllCities;
import TownRecommendationsTraveler.City;
import TownRecommendationsTraveler.JacksonTester;
import TownRecommendationsTraveler.NewTraveller;

public class GuiStart {

	private JFrame mainFrame;
	private JLabel headerLabel, age, city, code;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JTextField ageField, cityNameInput, domainCodeCity;
	private JButton backButton, nextButton;
	private static int index = 0;
	private JLabel question;
	private int option;
	private JButton sumbmitButton, main, repeat, updateJsonButton;

	public GuiStart() {
		prepareGUI();
	}

	public static void main(String[] args) {

		new AllCities();

		JacksonTester tester = new JacksonTester();
		/**
		 * READ IN FILE and load
		 */

		tester.call(AllCities.getArrayTowns());

		/**
		 * SET VALUES FORM ARAY TOWNS and store
		 */
		for (City f : AllCities.getArrayTowns())
			setValues(f, AllCities.getHm());

		/**
		 * ----- START GUI --------
		 */
		GuiStart actionListenerDemo = new GuiStart();
		actionListenerDemo.startMainWindows();
	}

	// -------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * ----------------------------- PREPARE GUI------------------------------
	 */
	private void prepareGUI() {

		mainFrame = new JFrame("LETS TRAVEL!");
		mainFrame.setSize(1300, 700);
		mainFrame.setLayout(new GridLayout(3, 1));

		headerLabel = new MyJLabel("");
		statusLabel = new MyJLabel("");
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		statusLabel.setHorizontalAlignment(JLabel.CENTER);
		statusLabel.setSize(1350, 1100);

		/**
		 * ---- EXIT X -----
		 */
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {

				/*
				 * UPDATE JSON
				 */
				JacksonTester tester = new JacksonTester();
				if (AllCities.getNewArrayTowns().size() > 0)
					tester.addNewCity(AllCities.getNewArrayTowns());
				System.exit(0);
			}
		});

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------
	/*
	 * --------------------- START MENU MAIN GUI ---------------------------------
	 */
	private void startMainWindows() {

		controlPanel.removeAll();
		statusLabel.setText("");
		headerLabel.setText("WELCOME MENU");

		JPanel panel = new JPanel();
		JButton buttonRecommendCity, buttonNewCity, buttonCollecion, exitButton;
		/**
		 * GridBagLayout layout
		 */
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		/**
		 * PANEL BACKGROUND
		 */
		panel.setBackground(Color.darkGray);
		panel.setSize(1900, 1900);
		panel.setLayout(layout);

		// --------------------------------------------------------------------------------
		/**
		 * ------------------------- BUTTONS MENU ----------------------------
		 */
		/**
		 * ---- RECOMMEND CITY----
		 */
		buttonRecommendCity = new MyJButton("RECOMMEND CITY"); // BUTTON RECCOMEND CITY
		buttonRecommendCity.addActionListener(new RecommendCityActionButton()); // ACTION LISTENER
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 20;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(buttonRecommendCity, gbc); // ADD BUTTON

		/**
		 * -----ADD NEW CITY IN COLLECION---
		 */
		buttonNewCity = new MyJButton("ADD NEW CITY IN COLLECION"); // BUTTON NEW CITY
		buttonNewCity.addActionListener(new NewCityActionButton()); // ACTION LISTENER
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 20;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(buttonNewCity, gbc);// ADD BUTTON

		/**
		 * -----COLLECION CITIES---
		 */
		buttonCollecion = new MyJButton("COLLECION CITIES"); // BUTTON COLLECTION CITIES
		buttonCollecion.addActionListener(new CollecionActionButton()); // ACTION LISTENER
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		panel.add(buttonCollecion, gbc);// ADD BUTTON

		/**
		 * ----- EXIT ---
		 */
		exitButton = new MyJButton("EXIT "); // BUTTON EXIT GUI
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		panel.add(exitButton, gbc);// ADD BUTTON

		// -------------------------------------------------------------------------------

		/**
		 * ----------- EXIT ACTION LISTENER -------------
		 */
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * UPDATE JSON
				 */
				JacksonTester tester = new JacksonTester();
				if (AllCities.getNewArrayTowns().size() > 0)
					tester.addNewCity(AllCities.getNewArrayTowns());
				System.exit(0);
			}
		});
		/**
		 * ADD PANEL
		 */
		controlPanel.add(panel);
		mainFrame.setVisible(true);
	}

	// ------------------------------------------------------------------------------------------------------------------------
	/**
	 * BUTTON Recommend City Action Listener
	 */
	class RecommendCityActionButton implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			controlPanel.removeAll();
			headerLabel.setText("");
			JPanel panel1 = new JPanel();

			/**
			 * GridBagLayout layout
			 */
			GridBagLayout layout = new GridBagLayout();
			GridBagConstraints gbc = new GridBagConstraints();
			/**
			 * PANEL BACKGROUND
			 */
			panel1.setBackground(Color.WHITE);
			panel1.setSize(2300, 2300);
			panel1.setLayout(layout);

			/**
			 * INPUT AGE
			 */
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			age = new MyJLabel("Enter your age:");
			ageField = new MyJTextField();
			panel1.add(age);
			panel1.add(ageField, gbc);

			/**
			 * AGE KEY FIELD LISTENER -------
			 */
			ageField.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent ke) {

					String value = ageField.getText();
					int len = value.length();
					if (len >= 3) { 
						ageField.setEditable(false);
						if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
							ageField.setEditable(true);
							statusLabel.setText("");
						}
					} else {

						if (ke.getExtendedKeyCode() >= '0' && ke.getExtendedKeyCode() <= '9'
								|| ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_ENTER) {
							ageField.setEditable(true);
							statusLabel.setText("");
						} else {
							ageField.setEditable(false);
							statusLabel.setText("* Enter only numeric digits(1-9)");
						}
					}
				}
			});

			/**
			 * BUTTON BACK
			 */
			backButton = new MyJButton("BACK");
			backButton.addActionListener(new GoToMenu());
			gbc.gridx = 4;
			gbc.gridy = 3;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 222;
			panel1.add(backButton, gbc);

			/**
			 * BUTTON NEXT
			 */
			nextButton = new MyJButton("NEXT");
			gbc.gridx = 4;
			gbc.gridy = 2;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			panel1.add(nextButton, gbc);

			/**
			 * ADD NEW PANEL
			 */
			controlPanel.add(panel1);
			mainFrame.setVisible(true);

			/**
			 * NEXT BUTTON ACTION LISTENER
			 */
			nextButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					String age = ageField.getText();
					String message = "";
					if (age.length() > 0) {
						int number = Integer.parseInt(age);
						if (number >= 16 && number <= 115) {

							Questions x = new Questions();
							x.actionPerformed(e);
							message = "";
						} else {
							message = "Please enter AGE ( 16-115 ) ";
						}
					} else {
						message = "Field is required ";
					}
					statusLabel.setText(message);
				}
			});
		}
	}

	// ----------------------------------------------------------------------------------------
	/**
	 * Questions SEE OPTIONS NEW JPANEL
	 */
	class Questions implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			controlPanel.removeAll();
			statusLabel.setText("");
			JPanel panel1 = new JPanel();

			ArrayList<Integer> explicitly;
			String age;
			int number;

			/**
			 * GridBagLayout layout
			 */
			GridBagLayout layout = new GridBagLayout();
			GridBagConstraints gbc = new GridBagConstraints();
			/**
			 * PANEL BACKGROUND
			 */
			panel1.setBackground(Color.WHITE);
			panel1.setSize(1300, 1300);
			panel1.setLayout(layout);

			headerLabel.setText("What place do you like to visit the most ? ");

			String[] questions = new String[] { "Firstly , Do you prefer going out to cafe ?",
					"Do you prefer going out to sea ?", "Do you prefer going out to museums ?",
					"Do you prefer going out to restaurants ?", "Do you prefer going out to stadium ?",
					"Do you prefer going out to bar ?", "Do you prefer going out to amusement park ?",
					"What kind of weather do you prefer ?", "Do you prefer has cloudy weather ?",
					"Do you prefer to travel long distances ?" };

			String[][] answer = new String[][] { { "YES", "MAYBE", "NO" }, { "YES", "MAYBE", "NO" },
					{ "YES", "MAYBE", "NO" }, { "YES", "MAYBE", "NO" }, { "YES", "MAYBE", "NO" },
					{ "YES", "MAYBE", "NO" }, { "YES", "MAYBE", "NO" }, { "Cold", "Cool", "Warm" },
					{ "YES", "MAYBE", "NO" }, { "YES", "MAYBE", "NO" } };

			explicitly = new ArrayList<>();
			/**
			 * ADD AGE IN ARRAY
			 */
			age = ageField.getText();
			number = Integer.parseInt(age);
			explicitly.add(number);

			/**
			 * LABEL QUESTION
			 */
			question = new MyJLabel(questions[index]);
			gbc.gridx = 2;
			gbc.gridy = 3;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.insets = new Insets(15, 5, 0, 0);

			/**
			 * BUTTON GROUP OPTION
			 */
			ButtonGroup options = new ButtonGroup();
			JRadioButton rb1 = new MyJRadioButton(answer[index][0]);
			JRadioButton rb2 = new MyJRadioButton(answer[index][1]);
			JRadioButton rb3 = new MyJRadioButton(answer[index][2]);

			rb1.addItemListener(new CustomItemListener());
			rb2.addItemListener(new CustomItemListener());
			rb3.addItemListener(new CustomItemListener());

			options.add(rb1);
			options.add(rb2);
			options.add(rb3);

			/**
			 * NEXT BUTTON
			 */
			nextButton = new MyJButton("NEXT"); // BUTTON nextButton
			nextButton.setEnabled(false);

			/**
			 * BACK BUTTON
			 */
			backButton = new MyJButton("BACK"); // BUTTON backButton

			/**
			 * ADD IN PANEL
			 */
			panel1.add(question); // ADD 1 JLABEL
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 2;
			panel1.add(rb1, gbc); // ADD 1 RADIO
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 3;
			panel1.add(rb2, gbc); // ADD 2 RADIO
			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 4;
			panel1.add(rb3, gbc); // ADD 3 RADIO
			gbc.gridx = 1;
			gbc.gridy = 6;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 201;
			panel1.add(nextButton, gbc); // ADD BUTTON NEXT
			gbc.gridx = 1;
			gbc.gridy = 8;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			panel1.add(backButton, gbc);

			/**
			 * BUTTON MAIN
			 */
			main = new MyJButton("MENU"); // BUTTON main CITIES
			main.addActionListener(new GoToMenu());
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			panel1.add(main, gbc);

			/**
			 * BUTTON repeat
			 */
			repeat = new MyJButton("REPEAT"); // BUTTON repeat CITIES
			repeat.addActionListener(new RecommendCityActionButton());
			gbc.gridx = 1;
			gbc.gridy = 2;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			panel1.add(repeat, gbc);

			/**
			 * ADD NEW PANEL
			 */
			controlPanel.add(panel1);
			mainFrame.setVisible(true);

			/**
			 * 
			 * NEXT BUTTON ACTION LISTENER
			 */
			nextButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {

					if (index >= 0 && index < 9) {
						question.setText(questions[++index]);

						rb1.setText(answer[index][0]);
						rb2.setText(answer[index][1]);
						rb3.setText(answer[index][2]);

						options.clearSelection();

						explicitly.add(option);
						nextButton.setEnabled(false);
					} else if (index == 9) {
						startReccomendCity(explicitly);
						statusLabel.setText("");
						index = 0;
					}
				}
			});
			/**
			 * 
			 * BACK BUTTTON ACTION LISTENER
			 */
			backButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (index == 0) {
						statusLabel.setText("");
						explicitly.remove(index);
						RecommendCityActionButton z = new RecommendCityActionButton();
						z.actionPerformed(e);

					} else if (index <= 9) {

						explicitly.remove(index);
						question.setText(questions[--index]);
						options.clearSelection();
						nextButton.setEnabled(false);
					}
				}
			});
		}

		// -----------------------------------------------------------------------------------
		/**
		 * ----------------------- START RECCOMEND CITY------------------------------
		 */
		private void startReccomendCity(ArrayList<Integer> explicitly) {

			headerLabel.setText("CITIES");
			controlPanel.removeAll();
			JPanel myPanel = new JPanel();
			myPanel.setSize(1400, 1400);

			/**
			 * GridBagLayout layout
			 */
			GridBagConstraints gbc = new GridBagConstraints();

			/**
			 * BUTTON MENU
			 */
			backButton = new MyJButton("GO TO MENU"); // BUTTON backButton CITIES
			backButton.addActionListener(new GoToMenu()); // ACTION LISTENER

			/**
			 * TABLE CITIES
			 */
			Font font = new Font("Dialog", Font.BOLD + Font.ITALIC, 14);
			JScrollPane jsp2 = new JScrollPane();
			jsp2.getViewport().setFont(font);

			/**
			 * FIND CITIES
			 */
			NewTraveller.createNewTraveller(explicitly, jsp2, AllCities.getArrayTowns());
			myPanel.add(jsp2);

			/**
			 * BUTTON repeat
			 */
			repeat = new MyJButton("REPEAT"); // BUTTON repeat CITIES
			repeat.addActionListener(new RecommendCityActionButton());

			myPanel.add(repeat, gbc);
			myPanel.add(backButton, gbc);
			/**
			 * ADD NEW JPANEL
			 */
			controlPanel.add(myPanel);
			mainFrame.setVisible(true);
		}

		/*
		 * RADIO LISTENER ----------------------------------------------------------
		 */
		class CustomItemListener implements ItemListener {
			public void itemStateChanged(ItemEvent e) {

				nextButton.setEnabled(true);
				String opt = ((JRadioButton) e.getItem()).getText();

				if (opt.equals("NO")) {
					option = -1;
				} else if (opt.equals("MAYBE")) {
					option = 0;

				} else if (opt.equals("YES")) {
					option = 1;
				}
			}
		}
	}

	// ------------------------------------------------------------------------------------
	/**
	 * ADD NEW CITY BUTTON -------------------------
	 */
	class NewCityActionButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			headerLabel.setText("");
			controlPanel.removeAll();
			JPanel panel1 = new JPanel();

			/**
			 * GridBagLayout layout
			 */
			GridBagLayout layout = new GridBagLayout();
			GridBagConstraints gbc = new GridBagConstraints();

			/**
			 * PANEL BACKGROUND
			 */
			panel1.setBackground(Color.WHITE);
			panel1.setSize(1300, 1300);
			panel1.setLayout(layout);

			/**
			 * INPUT NEW CITY AND DOMAIN
			 */
			city = new MyJLabel("Enter NEW CITY:");
			cityNameInput = new JTextField(15);
			cityNameInput.setFont(new Font("SansSerif", Font.BOLD, 22));

			code = new MyJLabel("Enter DOMAIN CITY:");
			domainCodeCity = new JTextField(15);
			domainCodeCity.setFont(new Font("SansSerif", Font.BOLD, 22));

			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 11;
			panel1.add(city, gbc);
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 11;
			panel1.add(cityNameInput, gbc);
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 11;
			panel1.add(code, gbc);
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 11;
			panel1.add(domainCodeCity, gbc);

			/**
			 * JSON UPDATE FILE
			 */
			updateJsonButton = new MyJButton("UPDATE JSON FILE"); // BUTTON updateJsonButton CITIES
			gbc.gridx = 0;
			gbc.gridy = 7;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 22;
			panel1.add(updateJsonButton, gbc);
			if (AllCities.getNewArrayTowns().size() == 0) {
				updateJsonButton.setEnabled(false);
			} else {
				updateJsonButton.setEnabled(true);
			}
			updateJsonButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					JacksonTester tester = new JacksonTester();
					tester.addNewCity(AllCities.getNewArrayTowns());
					AllCities.getNewArrayTowns().clear();
					updateJsonButton.setEnabled(false);
				}
			});

			/**
			 * BUTTON BACK
			 */
			backButton = new MyJButton("BACK"); // BUTTON backButton 
			backButton.addActionListener(new GoToMenu());
			gbc.gridx = 0;
			gbc.gridy = 6;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 22;
			panel1.add(backButton, gbc);

			/**
			 * BUTTON NEXT
			 */
			sumbmitButton = new MyJButton("SUBMIT"); // BUTTON sumbmitButton 
			gbc.gridx = 1;
			gbc.gridy = 5;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			panel1.add(sumbmitButton, gbc);

			/**
			 * ADD NEW PANEL
			 */
			controlPanel.add(panel1);
			mainFrame.setVisible(true);

			/**
			 * SUMBMIT ACTION
			 */
			sumbmitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {

					String value;
					int len;
					String value2;
					int len2;

					value = cityNameInput.getText();
					len = value.length();
					value2 = domainCodeCity.getText();
					len2 = value2.length();

					if (len > 0 && len2 > 0) {
						statusLabel.setText("");
						if (createNewCity(cityNameInput, domainCodeCity)) {
							updateJsonButton.setEnabled(true);
						}
					} else {
						statusLabel.setText("All fields are required.");
					}
				}
			});
		}

		// ----------------------------------------------------------------------------
		/**
		 * CREATE NEW CITY -----------------------------------------------------------
		 */
		private boolean createNewCity(JTextField cityNameInput, JTextField domainCodeCity) {

			String name;
			String code;
			String[] cityNameDomain;

			name = cityNameInput.getText();
			code = domainCodeCity.getText();
			cityNameDomain = new String[2];

			cityNameDomain[0] = String.valueOf(name);
			cityNameDomain[1] = String.valueOf(code);
			City newCity;

			newCity = City.createNewCity(AllCities.getArrayTowns(), cityNameDomain, statusLabel);

			if (newCity != null) {
				statusLabel.setText("SUCCESSFULLY REVERT DATA FORM WIKI AND OPEN DATA ");
				AllCities.getArrayTowns().add(newCity);
				AllCities.getNewArrayTowns().add(newCity);
				setValues(newCity, AllCities.getHm());

				return true;
			}
			return false;
		}
	}

	// ---------------------------------------------------------------------------------
	/*
	 * BUTTON COLLECTION ACTION --------------------------
	 */

	class CollecionActionButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			controlPanel.removeAll();
			JPanel myPanel = new JPanel();
			myPanel.setSize(1400, 1400);

			/**
			 * GridBagLayout layout
			 */
			GridBagConstraints gbc = new GridBagConstraints();

			/**
			 * BUTTON BACK
			 */
			backButton = new MyJButton("GO TO MENU"); // BUTTON backButton 
			backButton.addActionListener(new GoToMenu()); // ACTION LISTENER

			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 22;

			String[] columnNames = { "Monday", "Tuesday", "Wednesday", "Thursday", "Saturday", "Sunday" };

			Object[][] data = new Object[AllCities.getArrayTowns().size() + 2][5];
		
			int index2 = 0;
			int index ;
			for (String i : columnNames) {
				index = 0;

				for (String x1 : AllCities.getHm().get(i)) {
					data[index][index2] = x1;
					index++;
				}
				index2++;
			}

			/**
			 * TABLE CITIES --------------------
			 */
			DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
			JTable studentsTable = new JTable(dtm);
			studentsTable.setFont(new Font("monospaced", Font.PLAIN, 30));
			studentsTable.setRowHeight(50);

			JTableHeader tableHeader = studentsTable.getTableHeader();
			tableHeader.setBackground(Color.LIGHT_GRAY);
			tableHeader.setForeground(Color.black);
			Font headerFont = new Font("Verdana", Font.PLAIN, 30);
			tableHeader.setFont(headerFont);

			JScrollPane jsp2 = new JScrollPane();
			jsp2.setPreferredSize(new Dimension(1200, 450));

			jsp2.getViewport().add(studentsTable);

			myPanel.add(jsp2);
			myPanel.add(backButton, gbc);

			/**
			 * ADD NEW PANEL
			 */
			controlPanel.add(myPanel);
			mainFrame.setVisible(true);

		}
	}

	/**
	 * BUTTON GO TO MENU
	 */
	class GoToMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			startMainWindows();
		}
	}

	class MyJButton extends JButton {

		private static final long serialVersionUID = 1L;

		MyJButton(String d) {

			super();
			setFont(new Font("monospaced", Font.PLAIN, 30));
			setText(d);
		}
	}

	class MyJLabel extends JLabel {

		private static final long serialVersionUID = 1L;

		MyJLabel(String d) {

			super();
			setFont(new Font("monospaced", Font.PLAIN, 30));
			setText(d);
		}
	}

	class MyJTextField extends JTextField {

		private static final long serialVersionUID = 1L;

		MyJTextField() {

			super();
			setFont(new Font("SansSerif", Font.BOLD, 20));

		}
	}

	class MyJRadioButton extends JRadioButton {

		private static final long serialVersionUID = 1L;

		MyJRadioButton(String x) {

			super();
			setFont(new Font("SansSerif", Font.BOLD, 25));
			setText(x);
		}
	}
}