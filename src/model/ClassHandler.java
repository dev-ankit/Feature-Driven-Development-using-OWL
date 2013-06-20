package model;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;

import org.semanticweb.owlapi.model.OWLClass;

public class ClassHandler implements ItemListener {
	public OWLClass owlClass;
	public JCheckBox check;
	public Set<ObjectPropertyHandler> objects;
	public Set<DataPropertyHandler> data;
	public boolean isSelected;

	public ClassHandler(OWLClass c) {
		owlClass = c;
		objects = new HashSet<ObjectPropertyHandler>();
		data = new HashSet<DataPropertyHandler>();
		isSelected = false;
		check = new JCheckBox(Common.getClassName(owlClass));
		check.addItemListener(this);
	}

	public int getTotalPropertyCount() {
		int count = data.size() + objects.size();
		return count;
	}

	public int getSelectedPropertyCount() {
		int count = 0;
		for (DataPropertyHandler dph : data) {
			if (dph.isSelected)
				count++;
		}
		for (ObjectPropertyHandler oph : objects) {
			if (oph.isSelected)
				count++;
		}
		return count;
	}

	private void disableFeatures() {
		for (DataPropertyHandler dph : data) {
			dph.check.setEnabled(false);
			if(!dph.isKey())
				dph.check.setSelected(false);
		}
		for (ObjectPropertyHandler oph : objects) {
			oph.check.setEnabled(false);
			if(!oph.isKey())
				oph.check.setSelected(false);
		}
	}

	private void enableFeatures() {
		for (DataPropertyHandler dph : data) {
			if(!dph.isKey())
			dph.check.setEnabled(true);
		}
		for (ObjectPropertyHandler oph : objects) {
			if(!oph.isKey())
			oph.check.setEnabled(true);
		}

	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (isSelected) {
			isSelected = false;
			disableFeatures();
		} else {
			isSelected = true;
			enableFeatures();
		}
	}
}