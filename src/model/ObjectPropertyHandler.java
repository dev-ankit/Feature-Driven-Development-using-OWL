package model;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObjectProperty;

public class ObjectPropertyHandler implements ItemListener {
	public ClassHandler domain;
	public OWLClass range;
	public JCheckBox check;
	public OWLObjectProperty prop;
	public boolean isSelected;
	public ObjectPropertyHandler(OWLObjectProperty p,ClassHandler dom,OWLClass rang) {
		prop=p;
		domain=dom;
		range=rang;
		isSelected = isKey();
		check=new JCheckBox("Assign "+ Common.getClassName(range)+" to "+Common.getClassName(domain.owlClass));		
		check.addItemListener(this);
		check.setEnabled(false);
		
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
