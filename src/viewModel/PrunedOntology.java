package viewModel;

import java.io.File;
import java.util.Collections;

import javax.swing.JLabel;

import model.ClassHandler;
import model.Common;
import model.DataPropertyHandler;
import model.ObjectPropertyHandler;
import model.Ontologies;
import model.SelectionTemplate;

import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.OWLEntityRemover;


public class PrunedOntology {
	public static void pruneAndSaveOntology() {
		 OWLOntology ont = Ontologies.mergedOntology;
	        OWLOntologyManager man = ont.getOWLOntologyManager();
	        OWLEntityRemover remover = new OWLEntityRemover(man, Collections.singleton(ont));
	    SelectionTemplate st= SelectionTemplate.getSelectionTemplate();
	    for(ClassHandler ch:st.classList)
	    {
	    	if(!ch.isSelected){
	    		ch.owlClass.accept(remover);
	    	}
	    	else
	    	{
	    		for (DataPropertyHandler dph : ch.data) {
					if (!dph.isSelected){
						dph.prop.accept(remover);
					}
	    		}
				for (ObjectPropertyHandler oph : ch.objects) {
					if (!oph.isSelected)
						oph.prop.accept(remover);
				}
	    	}
	    }
	    man.applyChanges(remover.getChanges());
	    File file =new File("NewOntology.owl");
        
        // Save to RDF/XML
        try {
			man.saveOntology(ont, new RDFXMLOntologyFormat(),
			        IRI.create(file.toURI()));
		} catch (OWLOntologyStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Saved");  remover.reset();
	}

}
