package Ex1;

import javax.swing.*;

import javafx.scene.control.RadioButton;

import java.awt.event.*;
import java.awt.*;

public class SwingLayout extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1557879078675645L;
	private JPanel pCenter, pWest, pEast, pSouth;
	private JLabel lblName, lblAdr, lblProvince, lblCity, lblPC, lblPhone, lblEmail;
	private JTextField textName, textAdr, textProvince, textCity, textPC, textPhone, textEmail;
	private final JCheckBox councilCheckBox, workCheckBox;
	private JRadioButton computerRadioButton, businessRadioButton;
	private final JComboBox<String> courseComboBox;
	private static final String[] courses = { "Cumputer Science", "Business" };
	private ButtonGroup radioGroup;
	private final JList<String> courseJList;
	private static final String[] courseName = { "course1", "course2", "course3" };
	private final JTextArea textArea1;
	private final JButton diplayJButton;

	public SwingLayout() {
		pCenter = new JPanel();
		pWest = new JPanel();
		pEast = new JPanel();
		pSouth = new JPanel();
		pWest.setLayout(new GridLayout(7, 2));
		lblName = new JLabel("Name:");
		lblAdr = new JLabel("Adress:");
		lblProvince = new JLabel("Provice:");
		lblCity = new JLabel("City:");
		lblPC = new JLabel("Post Code:");
		lblPhone = new JLabel("Phone Number:");
		lblEmail = new JLabel("Email:");
		textName = new JTextField(10);
		textAdr = new JTextField(10);
		textProvince = new JTextField(10);
		textCity = new JTextField(10);
		textPC = new JTextField(10);
		textPhone = new JTextField(10);
		textEmail = new JTextField(10);
		pWest.add(lblName);
		pWest.add(textName);
		pWest.add(lblAdr);
		pWest.add(textAdr);
		pWest.add(lblProvince);
		pWest.add(textProvince);
		pWest.add(lblPC);
		pWest.add(textPC);
		pWest.add(lblCity);
		pWest.add(textCity);
		pWest.add(lblPhone);
		pWest.add(textPhone);
		pWest.add(lblEmail);
		pWest.add(textEmail);

		add(pWest, BorderLayout.WEST);
		councilCheckBox = new JCheckBox("Student Council");
		workCheckBox = new JCheckBox("Volunteer work");

		pCenter.add(councilCheckBox);
		pCenter.add(workCheckBox);
		pCenter.setLayout(new GridLayout(2, 1));
		add(pCenter, BorderLayout.CENTER);
		computerRadioButton = new JRadioButton("Computer Science");
		computerRadioButton.setSelected(true);
		
		businessRadioButton = new JRadioButton("Business");

		courseComboBox = new JComboBox<String>(courses);
		courseComboBox.addActionListener(cbActionListener);
		radioGroup = new ButtonGroup();
		radioGroup.add(computerRadioButton);
		radioGroup.add(businessRadioButton);

		RadioButtonHandler radioHandler = new RadioButtonHandler();

		computerRadioButton.addItemListener(radioHandler);
		businessRadioButton.addItemListener(radioHandler);

		pEast.add(computerRadioButton);
		pEast.add(businessRadioButton);

		pEast.add(courseComboBox);
		courseJList = new JList<String>(new DefaultListModel<String>());
		courseJList.setVisibleRowCount(3);
		pEast.add(courseJList);
		pEast.setLayout(new GridLayout(4, 1));
		add(pEast, BorderLayout.EAST);
		diplayJButton = new JButton("Display");
		textArea1 = new JTextArea(3, 40);

		TextFieldHandler handler = new TextFieldHandler();
		textName.addActionListener(handler);
		textAdr.addActionListener(handler);
		textProvince.addActionListener(handler);
		textCity.addActionListener(handler);
		textPC.addActionListener(handler);
		textPhone.addActionListener(handler);
		textEmail.addActionListener(handler);

		Box box = Box.createVerticalBox();
		box.add(diplayJButton);
		diplayJButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		ButtonHandler buttonHandler = new ButtonHandler();
		diplayJButton.addActionListener(buttonHandler);
		box.add(textArea1);
		pSouth.add(box);
		add(pSouth, BorderLayout.SOUTH);

	}

	private class RadioButtonHandler implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (computerRadioButton.isSelected()) {
				courseComboBox.removeAllItems();
				courseComboBox.setModel(new DefaultComboBoxModel<String>(courses));
			}
			if (businessRadioButton.isSelected()) {
				courseComboBox.removeAllItems();
				courseComboBox.setModel(new DefaultComboBoxModel<String>(courseName));
			}
		}
	}

	ActionListener cbActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String item = (String) courseComboBox.getSelectedItem();
			DefaultListModel<String> model =  (DefaultListModel<String>) courseJList.getModel();
			if (item != null && !model.contains(item)) {
				model.addElement(item);
			}
		}
	};

	private class TextFieldHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
		}
	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			textArea1.append(textName.getText() + ", " + textAdr.getText() + ", " + textProvince.getText() + ","
					+ textCity.getText() + "," + textPC.getText() + "," + textPhone.getText() + ","
					+ textEmail.getText() + "\nCourse:");
			DefaultListModel<String> model =  (DefaultListModel<String>) courseJList.getModel();
			for(int i = 0; i < model.getSize(); i++) {
				textArea1.append("\n" + model.get(i));
			}
		}
	}
}
