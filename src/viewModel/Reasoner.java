package viewModel;

import model.Ontologies;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;


public class Reasoner {

	public static void isConsistent() {
		OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();

		// Uncomment the line below
		// reasonerFactory = new PelletReasonerFactory();
		// Load an example ontology - for the purposes
		// of the example, we will just load the pizza ontology.
		OWLOntology ont = Ontologies.mergedOntology;
		OWLOntologyManager man = ont.getOWLOntologyManager();
		// Create the reasoner and classify the ontology
		OWLReasoner reasoner = reasonerFactory.createNonBufferingReasoner(ont);
		if (reasoner.isConsistent()) {
			System.out.println("Consistent");
		}

	}
}
