package viewModel;

import java.util.Set;

import model.ClassHandler;
import model.Common;
import model.DataPropertyHandler;
import model.ObjectPropertyHandler;
import model.Ontologies;
import model.SelectionTemplate;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;


public class SelectFeature {
	public static SelectionTemplate generateList() {
		OWLOntology ont = Ontologies.mergedOntology;
		// get Singleton
		SelectionTemplate selectionDatabase = SelectionTemplate
				.getSelectionTemplate();
		//if not empty
		if(selectionDatabase.classList.size()>0)
		return selectionDatabase;
		Set<OWLClass> owlClsSet = ont.getClassesInSignature();
		for (OWLClass c : owlClsSet) {
			ClassHandler ch = new ClassHandler(c);
			selectionDatabase.classList.add(ch);
			Set<OWLAxiom> axiomSet = c.getReferencingAxioms(ont);
			for (OWLAxiom a : axiomSet) {
				// If referencing axiom is of desired type
				if (a.isOfType(AxiomType.OBJECT_PROPERTY_DOMAIN)) {
					OWLClass rangeClass = RangeAxiomManager.getRangeClass(ont,
							Common.getPropertyName(a.toString()));
					if (rangeClass != null) {
						
						// Trial
						// Assuming only one Object Property is associated with
						// the axiom
						for (OWLObjectProperty prop : a
								.getObjectPropertiesInSignature()) {
							ObjectPropertyHandler oph = new ObjectPropertyHandler(
									prop, ch, rangeClass);
							ch.objects.add(oph);
						}

						// Selected Prop = new
						// Selected(a,Common.getClassName(rangeClass), c,
						// false);
						// completeList.add(Prop);
						// add(Prop.checkBox);
					}
				} else if (a.isOfType(AxiomType.DATA_PROPERTY_DOMAIN)) {

					// Trial
					// Assuming only one Data Property is associated with the
					// axiom
					System.out.println(a.getDataPropertiesInSignature().size());
					for (OWLDataProperty prop : a
							.getDataPropertiesInSignature()) {
						DataPropertyHandler dph = new DataPropertyHandler(prop,
								ch);
						ch.data.add(dph);
					}

					// Selected Prop = new Selected(a,
					// Common.getPropertyName(a.toString()), c, true);
					// completeList.add(Prop);
					// add(Prop.checkBox);
				}
			}
		}
		return selectionDatabase;
	}
}
