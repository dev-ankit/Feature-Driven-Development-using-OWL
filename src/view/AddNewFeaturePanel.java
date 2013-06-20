/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import model.ClassHandler;
import model.Common;
import model.DataPropertyHandler;
import model.FeatureTemplate;
import model.ObjectPropertyHandler;
import model.SelectionTemplate;

import org.semanticweb.owlapi.model.OWLClass;

/**
 * 
 * @author ankitkhullar
 */
public class AddNewFeaturePanel extends JPanel {

	List<OWLClass> objectList;
	List<String> objectNameList;

	private static class NavigateButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO
			// goto next page
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	void generateObjectList() // this list is global and no change is expected
	{
		objectList = new ArrayList<OWLClass>();
		objectNameList = new ArrayList<String>();
		objectNameList.add("--Select--");
		SelectionTemplate selectedData = SelectionTemplate
				.getSelectionTemplate();
		for (ClassHandler ch : selectedData.classList) {
			objectList.add(ch.owlClass);
			objectNameList.add(Common.getClassName(ch.owlClass));
		}
	}

	public AddNewFeaturePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton b = new JButton("Done");
		b.addActionListener(new NavigateButtonListener());
		add(b);
		Row r = new Row();
		add(r.addRow());
	}

	private class Row {
		JComboBox actionBox;
		JComboBox resultBox;
		JComboBox conjuctionBox;
		JComboBox objectBox;
		JButton b;
		FeatureTemplate ft;
		String addData; // additional data if generated
		List<String> resultNameList = new ArrayList<String>();

		void generateResultList()// this list may vary from box to box
		{
			resultNameList = new ArrayList<String>();
			for (String ch : objectNameList) {
				resultNameList.add(ch);
			}
			resultNameList.add("Add More...");
		}

		public Row() {
			// initialize lists
			String[] actionsList = { "Assign", "Calculate" };
			actionBox = new JComboBox(actionsList);
			// actionBox.addActionListener(new AddmoreActionListner());

			String[] conjuctionsList = { "to", "for", "of", "on" };
			conjuctionBox = new JComboBox(conjuctionsList);

			// if object list is already generated no need to regenerate
			if (objectList == null)
				generateObjectList();
			objectBox = new JComboBox(objectNameList.toArray());
			generateResultList();
			resultBox = new JComboBox(resultNameList.toArray());
			resultBox.addActionListener(new AddmoreActionListner());

			b = new JButton("Add");
			b.addActionListener(new AddButtonActionListner());

		}

		public JPanel addRow() {
			JPanel horiz = new JPanel();
			BoxLayout layout = new BoxLayout(horiz, BoxLayout.X_AXIS);
			horiz.setLayout(layout);
			// TODO add checkboxes
			horiz.add(actionBox);
			horiz.add(resultBox);
			horiz.add(conjuctionBox);
			horiz.add(objectBox);
			horiz.add(b);
			return horiz;
		}

		public JPanel addLabels() {
			actionBox.setVisible(false);
			resultBox.setVisible(false);
			conjuctionBox.setVisible(false);
			objectBox.setVisible(false);
			b.setVisible(false);

			JPanel horiz = new JPanel();
			BoxLayout layout = new BoxLayout(horiz, BoxLayout.X_AXIS);
			horiz.setLayout(layout);
			horiz.add(new JLabel(getLabelData()));
			return horiz;
		}

		String getLabelData() {
			return actionBox.getSelectedItem() + " "
					+ resultBox.getSelectedItem() + " "
					+ conjuctionBox.getSelectedItem() + " "
					+ objectBox.getSelectedItem() + ".";
		}

		private class AddButtonActionListner implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO verify input
				if (resultBox.getSelectedIndex() == 0
						|| objectBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Invalid Selection");
					return;
				}
				if (resultBox.getSelectedIndex() == objectBox
						.getSelectedIndex()) {
					int ans = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to " + getLabelData());
					if (ans != JOptionPane.YES_OPTION) {
						return;
					}
				}
				
				add(addLabels());
				Row r = new Row();
				add(r.addRow());
				application.AppFrame.refreshFrame();

			}
		}

		private class AddmoreActionListner implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// add more
				// dont ask anything else just name
				// rest will be done when he finalizes
				if ("Add More..."
						.equals(resultBox.getSelectedItem().toString())) {
					addData = JOptionPane.showInputDialog("Enter Name");
					resultBox.addItem(addData);
					resultBox.setSelectedItem(addData);
					resultBox.removeItem("Add More...");
				}

			}

		}

	}
}
