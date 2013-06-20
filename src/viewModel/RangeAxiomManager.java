package viewModel;

import java.util.Set;

import javax.swing.JLabel;

import model.Common;
import model.Ontologies;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;


public class RangeAxiomManager {

	public static Set<AxiomType<?>> allowedAxioms()
	{
		 Set<AxiomType<?>>setAxiomTypes=AxiomType.ABoxAxiomTypes;
         setAxiomTypes.add(AxiomType.DATA_PROPERTY_DOMAIN);
         setAxiomTypes.remove(AxiomType.CLASS_ASSERTION);
         setAxiomTypes.add(AxiomType.OBJECT_PROPERTY_DOMAIN);
         return setAxiomTypes;
	}
	public static OWLClass getRangeClass(OWLOntology ont,String propName) {
		
		 for(OWLAxiom rangeAxiom:ont.getAxioms(AxiomType.OBJECT_PROPERTY_RANGE))
    	 {
    		 if(Common.getPropertyName(rangeAxiom.toString()).equals(propName))
    		 {
    			for(OWLClass rangeClass: rangeAxiom.getClassesInSignature())
    			{
    				//System.out.println(rangeClass);
    				return rangeClass;
    			}
    			 
    			 
    		 }
    	 }
		 return null;
	}
}
