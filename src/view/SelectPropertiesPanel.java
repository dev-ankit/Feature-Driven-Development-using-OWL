/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.*;

import model.ClassHandler;
import model.Common;
import model.DataPropertyHandler;
import model.ObjectPropertyHandler;
import model.Ontologies;
import model.SelectionTemplate;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;

import viewModel.RangeAxiomManager;
import viewModel.Reasoner;
import viewModel.SelectFeature;

/**
 * 
 * @author ankitkhullar
 */
@SuppressWarnings("serial")
public class SelectPropertiesPanel extends JPanel {
	JPanel horiz;
	SelectionTemplate generatedData;

	public SelectPropertiesPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// use selectfeature to generate class and properties and then use
		// the info
		// given to add Jcheckboxes
		generatedData = SelectFeature.generateList();
		// add(new JLabel("   "));// for vertical seperation

		for (ClassHandler ch : generatedData.classList) {
			if (ch.getTotalPropertyCount() > 0) {

				add(new JLabel("   "));
				add(ch.check);
				for (DataPropertyHandler dph : ch.data) {
					// horiz = new JPanel();
					// horiz.setLayout(new BoxLayout(horiz,
					// BoxLayout.X_AXIS));
					// horiz.add(new JLabel("    "));
					// horiz.add(dph.check);
					// add(horiz);
					add(dph.check);
				}
				for (ObjectPropertyHandler oph : ch.objects) {
					// horiz = new JPanel();
					// horiz.setLayout(new BoxLayout(horiz,
					// BoxLayout.X_AXIS));
					// horiz.add(new JLabel("    "));
					// horiz.add(oph.check);
					// add(horiz);
					add(oph.check);
				}
			}
		}
		horiz = new JPanel();
		horiz.setLayout(new BoxLayout(horiz, BoxLayout.X_AXIS));
		JButton b;
		b = new JButton("Select All");
		b.addActionListener(new buttonListner());
		horiz.add(b);
		b = new JButton("Deselect All");
		b.addActionListener(new buttonListner());
		horiz.add(b);
		b = new JButton("Next");
		b.addActionListener(new buttonListner());
		horiz.add(b);
		add(horiz);
		// vertical alignment
	}

	private class buttonListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton b = (JButton) arg0.getSource();
			if (b.getText().equals("Select All")) {
				for (ClassHandler ch : generatedData.classList) {
					if (ch.getTotalPropertyCount() > 0) {
						ch.check.setSelected(true);
						for (DataPropertyHandler dph : ch.data) {
							dph.check.setSelected(true);
						}
						for (ObjectPropertyHandler oph : ch.objects) {
							oph.check.setSelected(true);
						}
					}
				}

			} else if (b.getText().equals("Deselect All")) {
				for (ClassHandler ch : generatedData.classList) {
					if (ch.getTotalPropertyCount() > 0) {
						ch.check.setSelected(false);
					}
				}
			} else if (b.getText().equals("Next")) {
				// javaapplication.JavaApplication.setContentPane(new
				// AddNewClassPanel());

				application.AppFrame
						.setContentPane(new ViewSelectionPanel());
			}
		}
	}
}
