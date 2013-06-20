package model;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;

public class DataPropertyHandler implements ItemListener {
	public JCheckBox check;
	public ClassHandler domain;
	public OWLDataProperty prop;
	public boolean isSelected;

	public DataPropertyHandler(OWLDataProperty p, ClassHandler c) {
		prop = p;
		domain = c;
		check = new JCheckBox("Assign "
				+ Common.getPropertyName(prop.toString()) + " to "
				+ Common.getClassName(domain.owlClass));
		check.addItemListener(this);
		check.setEnabled(false);
		isSelected = isKey();
	}
	public boolean isKey()
	{
		//TODO find cardinality
		return false;
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (isSelected) {
			isSelected = false;
		} else {
			isSelected = true;
		}
	}
}
