/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import viewModel.RestrictionVisitor;

import java.util.Collections;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Common;
import model.Ontologies;

import org.semanticweb.owlapi.model.*;

/**
 *
 * @author ankitkhullar
 */
public class ViewRestrictionsPanel extends JPanel{
    public ViewRestrictionsPanel()
    {
    	
      //  for(OWLOntology ont:Ontologies.ontologies)
        {
        	OWLOntology ont=Ontologies.mergedOntology;
            for(OWLClass c:ont.getClassesInSignature())
            {
        //Changed here might cause problems   
            RestrictionVisitor restrictionVisitor = new RestrictionVisitor(
                    ont);
            // In this case, restrictions are used as (anonymous) superclasses,
            // so to get the restrictions on
                for (OWLSubClassOfAxiom ax : ont.getSubClassAxiomsForSubClass(c)) {
                OWLClassExpression superCls = ax.getSuperClass();
                // Ask our superclass to accept a visit from the
                // RestrictionVisitor - if it is an
                // existential restiction then our restriction visitor will
                // answer it - if not our
                // visitor will ignore it
                superCls.accept(restrictionVisitor);
            }
            // Our RestrictionVisitor has now collected all of the properties
            // that have been restricted in existential
            // restrictions - print them out.
             add(new JLabel("Restricted properties for " +Common.getClassName(c)+",count="+ restrictionVisitor.getRestrictedObjectProperties().size()));
            for (OWLObjectPropertyExpression prop : restrictionVisitor
                    .getRestrictedObjectProperties()) {
                add(new JLabel("    " +prop));//Common.getPropertyName(prop.toString())));
            }

                
            }
        }
                  setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
 
    }
}
