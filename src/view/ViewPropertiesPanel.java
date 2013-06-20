/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Common;
import model.Ontologies;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;

import viewModel.RangeAxiomManager;
import viewModel.Reasoner;

/**
 * 
 * @author ankitkhullar
 */
public class ViewPropertiesPanel extends JPanel {

	public ViewPropertiesPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// Reasoner.isConsistent();
		// for (Iterator<OWLOntology> it = Ontologies.ontologies.iterator();
		// it.hasNext();)
		{
			// OWLOntology ont = it.next();
			OWLOntology ont = Ontologies.mergedOntology;
			JLabel label = new JLabel(ont.toString());
			add(label);
			Set<OWLClass> owlClsSet = ont.getClassesInSignature();
			for (OWLClass c : owlClsSet) {
				add(new JLabel("*" + Common.getClassName(c) + "*"));
				Set<OWLAxiom> axiomSet = c.getReferencingAxioms(ont);

				for (OWLAxiom a : axiomSet) {
					// if(a.isOfType(setAxiom))
					// add(new JLabel("         -"+a));

					if (a.isOfType(AxiomType.OBJECT_PROPERTY_DOMAIN)) {
						OWLClass rangeClass = RangeAxiomManager.getRangeClass(
								ont, Common.getPropertyName(a.toString()));
						if (rangeClass != null)
							add(new JLabel("	Assign "
									+ Common.getClassName(rangeClass) + " to "
									+ Common.getClassName(c)));

					} else if (a.isOfType(AxiomType.DATA_PROPERTY_DOMAIN)) {
						add(new JLabel("	Assign "
								+ Common.getPropertyName(a.toString()) + " to "
								+ Common.getClassName(c)));
					}

				}
			}
			// vertical alignment
		}
	}

}
