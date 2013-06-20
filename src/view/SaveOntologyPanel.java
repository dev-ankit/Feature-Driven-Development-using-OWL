/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.SelectionTemplate;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

/**
 * 
 * @author ankitkhullar
 */
public class SaveOntologyPanel extends JPanel {

	public SaveOntologyPanel() throws OWLOntologyCreationException {

//		if (SelectionTemplate.finalList.isEmpty()) {
//			System.out.println("Nothing to save");
//		} else {
//			String iri = JOptionPane.showInputDialog("Enter IRI");
//			String name = JOptionPane.showInputDialog("Enter file Name");
//			OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
//			// IRI ontologyIRI = IRI.create(iri);
//			IRI ontologyIRI = IRI
//					.create("http://www.semanticweb.org/ontologies/myontology");
//			OWLOntology ontology = manager.createOntology(ontologyIRI);
//			System.out.println("Created ontology: " + ontology);
//			OWLOntologyID ontologyID = ontology.getOntologyID();
//			System.out.println("Ontology IRI: " + ontologyID.getOntologyIRI());
//			System.out.println("Ontology Version IRI: "
//					+ ontologyID.getVersionIRI());
//			System.out.println("Anonymous Ontology: "
//					+ ontologyID.isAnonymous());
//			IRI versionIRI = IRI.create(ontologyIRI + "/version1");
//			OWLOntologyID newOntologyID = new OWLOntologyID(ontologyIRI,
//					versionIRI);
//			SetOntologyID setOntologyID = new SetOntologyID(ontology,
//					newOntologyID);
//			manager.applyChange(setOntologyID);
//			System.out.println("Ontology: " + ontology);
//		}
	}

}
