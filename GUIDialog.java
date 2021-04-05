package ui.dialog;


import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import construction.FileTypeFilter;


public class GUIDialog {

	private JFrame frame;
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;

	// the constructor initialize the dialog frame
	public GUIDialog() {

		frame = new JFrame("OpenAPI Checker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setLayout(new GridBagLayout());
		frame.setResizable(false);
	}

	// the execution of the dialog
	public void execute() {
		// Set up the content pane.
		addComponentsToPane(frame.getContentPane());

		// Display the window.
		frame.setVisible(true);
	}

	// as its say this method add the buttons that we need for making the dialog
	private void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			// natural height, maximum width
			c.fill = GridBagConstraints.LINE_START;
		}

		//makes the first line of the page. the browse open file
		JLabel openApiLabel = new JLabel("OpenAPI URL: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(openApiLabel, c);

		JTextField firstBrowseTextField = new JTextField(30);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(firstBrowseTextField, c);

		JButton firstBrowseButton = new JButton("Browse");
		//adding open JFileChooser file functionality
		addFunctionalityBrowseButton1(firstBrowseButton,firstBrowseTextField);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(firstBrowseButton, c);

		//makes the second line of the page. the radiobuttons 
		JLabel servicesLabel = new JLabel("Services: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(20, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 1;
		pane.add(servicesLabel, c);

		JRadioButton currencyRadioButton = new JRadioButton("Currency");
		JRadioButton testRadioButton = new JRadioButton("Test");
		currencyRadioButton.setBounds(75, 50, 100, 30);
		testRadioButton.setBounds(75, 50, 100, 30);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(currencyRadioButton);
		buttonGroup.add(testRadioButton);

		JPanel radioButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		radioButtonsPanel.add(currencyRadioButton);
		radioButtonsPanel.add(testRadioButton);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(radioButtonsPanel, c);

		// makes the third line of the page. the combobox with the ports
		JLabel portsLabel = new JLabel("Ports: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		pane.add(portsLabel, c);

		String[] comboBoxPortsString = { "soap1", "soap2" };
		JComboBox comboBoxPorts = new JComboBox(comboBoxPortsString);

		JPanel comboBoxPortsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		comboBoxPortsPanel.add(comboBoxPorts);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		pane.add(comboBoxPortsPanel, c);

		//the forth line of the dialog. the combobox with the operations
		JLabel OperationsLabel = new JLabel("Operations: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		pane.add(OperationsLabel, c);

		String[] comboBoxOperationsString = { "getRealTime", "getTime", "getTimeAsString" };
		JComboBox comboBoxOperations = new JComboBox(comboBoxOperationsString);

		JPanel comboBoxOperationsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		comboBoxOperationsPanel.add(comboBoxOperations);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		pane.add(comboBoxOperationsPanel, c);

		//the fifth line of the dialog. the first checkbox
		JCheckBox checkBoxContent = new JCheckBox("Only required content");
		checkBoxContent.setBounds(100, 100, 50, 50);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		pane.add(checkBoxContent, c);

		//the sixth line of the dialog. the second checkbox
		JCheckBox checkBoxExamples = new JCheckBox("Include examples");
		checkBoxExamples.setBounds(100, 100, 50, 50);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.insets = new Insets(10, 0, 0, 0);
		c.gridy = 5;
		pane.add(checkBoxExamples, c);

		//the seventh line of the dialog. the second browse button for the saving file
		JLabel outputFileLabel = new JLabel("Output File: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		pane.add(outputFileLabel, c);

		JTextField secondBrowseTextField = new JTextField(30);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		pane.add(secondBrowseTextField, c);

		JButton secondBrowseButton = new JButton("Browse");
		
		//add the functionality for the second button for saving file
		addFunctionalityBrowseButton2(secondBrowseButton,secondBrowseTextField);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 6;
		pane.add(secondBrowseButton, c);
		
		//the eighth line. includes the buttons save and cancel 
		JButton sendButton = new JButton("Send");
		JButton closeButton = new JButton("Cancel");
		
		addFunctonalityCloseButton(closeButton);

		JPanel sendClosePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		sendClosePanel.add(sendButton);
		sendClosePanel.add(closeButton);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 7;
		pane.add(sendClosePanel, c);

	}

	private void addFunctionalityBrowseButton1(JButton button,JTextField textField){
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				// directory c appears as the default dialog
				JFileChooser fileChooser = new JFileChooser(new File("C:/"));
				//we are looking for .json files
				FileTypeFilter docFilter = new FileTypeFilter(".json", "JSON Files");
				fileChooser.addChoosableFileFilter(docFilter);
				fileChooser.setDialogTitle("Select Location");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
					//Obtaining the url of the file. looks that is the same with the .getFullPath
					try {
						@SuppressWarnings("deprecation")
						URL fileURL = fileChooser.getSelectedFile().toURL();
						String fileOut = fileURL.toString();
						textField.setText(fileOut);
						
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
			}
		});
}

	private void addFunctionalityBrowseButton2(JButton button,JTextField textField){
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				JFileChooser fileChooser = new JFileChooser(new File("C:/"));
				FileTypeFilter docFilter = new FileTypeFilter(".json", "JSON Files");
				fileChooser.addChoosableFileFilter(docFilter);
				fileChooser.setDialogTitle("Select Location");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(true);
				if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
					//we only select the name
					String fileID = fileChooser.getSelectedFile().getName();
					textField.setText(fileID);
				}
			}
		});
		
	}
	
	private void addFunctonalityCloseButton(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//close the dialog
				System.exit(0);
				}
		});
	}
	
	public static void main(String[] args) {
		GUIDialog gui = new GUIDialog();
		gui.execute();

	}

}
