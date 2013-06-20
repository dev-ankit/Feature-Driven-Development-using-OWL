package viewModel;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SetOntologyID;

public class NewOntology {

    public OWLOntology createOntology(String iriOfOntology,String versionOfiri) throws OWLOntologyCreationException {
        // We first need to create an OWLOntologyManager, which will provide a
        // point for creating, loading and saving ontologies. We can create a
        // default ontology manager with the OWLManager class. This provides a
        // common setup of an ontology manager. It registers parsers etc. for
        // loading ontologies in a variety of syntaxes
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        // In OWL 2, an ontology may be named with an IRI (Internationalized
        // Resource Identifier) We can create an instance of the IRI class as
        // follows:
//        IRI ontologyIRI = IRI.create("http://www.semanticweb.org/ontologies/myontology");
        IRI ontologyIRI = IRI.create(iriOfOntology);
        
        // Here we have decided to call our ontology
        // "http://www.semanticweb.org/ontologies/myontology" If we publish our
        // ontology then we should make the location coincide with the ontology
        // IRI Now we have an IRI we can create an ontology using the manager
        OWLOntology ontology = manager.createOntology(ontologyIRI);
        System.out.println("Created ontology: " + ontology);
        // In OWL 2 if an ontology has an ontology IRI it may also have a
        // version IRI The OWL API encapsulates ontology IRI and possible
        // version IRI information in an OWLOntologyID Each ontology knows about
        // its ID
        OWLOntologyID ontologyID = ontology.getOntologyID();
        // In this case our ontology has an IRI but does not have a version IRI
        System.out.println("Ontology IRI: " + ontologyID.getOntologyIRI());
        // Our version IRI will be null to indicate that we don't have a version
        // IRI
        System.out.println("Ontology Version IRI: " + ontologyID.getVersionIRI());
        // An ontology may not have a version IRI - in this case, we count the
        // ontology as an anonymous ontology. Our ontology does have an IRI so
        // it is not anonymous:
        System.out.println("Anonymous Ontology: " + ontologyID.isAnonymous());
        // Once an ontology has been created its ontology ID (Ontology IRI and
        // version IRI can be changed) to do this we must apply a SetOntologyID
        // change through the ontology manager. Lets specify a version IRI for
        // our ontology. In our case we will just "extend" our ontology IRI with
        // some version information. We could of course specify any IRI for our
        // version IRI.
        IRI versionIRI = IRI.create(ontologyIRI + versionOfiri);
        // Note that we MUST specify an ontology IRI if we want to specify a
        // version IRI
        OWLOntologyID newOntologyID = new OWLOntologyID(ontologyIRI, versionIRI);
        // Create the change that will set our version IRI
        SetOntologyID setOntologyID = new SetOntologyID(ontology, newOntologyID);
        // Apply the change
        manager.applyChange(setOntologyID);
        System.out.println("Ontology: " + ontology);
        return ontology;
    }

}
