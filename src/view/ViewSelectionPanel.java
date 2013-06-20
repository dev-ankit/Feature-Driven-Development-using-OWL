/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import viewModel.PrunedOntology;
import viewModel.RestrictionVisitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ClassHandler;
import model.Common;
import model.DataPropertyHandler;
import model.ObjectPropertyHandler;
import model.Ontologies;
import model.SelectionTemplate;

import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

/**
 * 
 * @author ankitkhullar
 */
@SuppressWarnings("serial")
public class ViewSelectionPanel extends JPanel {
	public ViewSelectionPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		SelectionTemplate selectedData = SelectionTemplate
				.getSelectionTemplate();
		// for(ClassHandler ch:selectedData.selectedList)
		for (ClassHandler ch : selectedData.classList) {
			if (ch.getSelectedPropertyCount() > 0) {
				add(new JLabel(Common.getClassName(ch.owlClass)));
				//Restrictions code
			    RestrictionVisitor restrictionVisitor = new RestrictionVisitor(
	                    Ontologies.mergedOntology);
			    for (OWLSubClassOfAxiom ax : Ontologies.mergedOntology.getSubClassAxiomsForSubClass(ch.owlClass)) {
	                OWLClassExpression superCls = ax.getSuperClass();
	                // Ask our superclass to accept a visit from the
	                // RestrictionVisitor - if it is an
	                // existential restiction then our restriction visitor will
	                // answer it - if not our
	                // visitor will ignore it
	                superCls.accept(restrictionVisitor);
	            }
			    
			    for (OWLDataPropertyExpression prop : restrictionVisitor
	                    .getRestrictedDataProperties()) {
	                add(new JLabel("    " +prop));//Common.getPropertyName(prop.toString())));
	            }
			    
			    for (OWLObjectPropertyExpression prop : restrictionVisitor
	                    .getRestrictedObjectProperties()) {
	                add(new JLabel("    " +prop));//Common.getPropertyName(prop.toString())));
	            }
			    
			    
			    
			    
			    
				for (DataPropertyHandler dph : ch.data) {
					if (dph.isSelected)
						add(new JLabel("Assign    "
								+ Common.getPropertyName(dph.prop.toString())
								+ "  to    " + Common.getClassName(ch.owlClass)));
				}
				for (ObjectPropertyHandler oph : ch.objects) {
					if (oph.isSelected)
						add(new JLabel("Assign    "
								+ Common.getPropertyName(oph.prop.toString())
								+ "  to    " + Common.getClassName(ch.owlClass)));
				}
			}
		}
		JPanel horiz = new JPanel();
		horiz.setLayout(new BoxLayout(horiz, BoxLayout.X_AXIS));

		JButton b;

		b = new JButton("Back");
		b.addActionListener(new buttonListner());
		horiz.add(b);
		b = new JButton("Next");
		b.addActionListener(new buttonListner());
		horiz.add(b);
		add(horiz);
	}

	private class buttonListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			JButton b = (JButton) arg0.getSource();
			if (b.getText().equals("Back")) {
				application.AppFrame
						.setContentPane(new SelectPropertiesPanel());
			} else if (b.getText().equals("Next")) {
				PrunedOntology.pruneAndSaveOntology();
				application.AppFrame
						.setContentPane(new AddNewFeaturePanel());
			}
		}

	}

}
