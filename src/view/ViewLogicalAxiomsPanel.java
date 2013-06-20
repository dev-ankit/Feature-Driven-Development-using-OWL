/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Iterator;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Common;
import model.Ontologies;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 *
 * @author ankitkhullar
 */
public class ViewLogicalAxiomsPanel extends JPanel {
  
    public ViewLogicalAxiomsPanel()
    {
    //    for (Iterator<OWLOntology> it = Ontologies.ontologies.iterator(); it.hasNext();) 
        {
//            OWLOntology ont = it.next();
        	OWLOntology ont = Ontologies.mergedOntology;
            JLabel label=new JLabel(ont.toString());
            add(label);
            Set<OWLClass> owlClsSet=ont.getClassesInSignature();
            for(OWLClass c:owlClsSet)
            {
                add(new JLabel("    *"+Common.getClassName(c)));
               Set<OWLAxiom> axiomSet= c.getReferencingAxioms(ont);
               for(OWLAxiom a:axiomSet)
               {
                   if(a.isLogicalAxiom())
                   add(new JLabel("         -"+a));
               }
            }
            //vertical alignment
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        }
    }
    
}
