/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.OWLClassExpressionVisitorAdapter;

/**
 * 
 * @author ankitkhullar
 */
public class RestrictionVisitor extends OWLClassExpressionVisitorAdapter {
	private Set<OWLClass> processedClasses;
	private Set<OWLObjectPropertyExpression> restrictedObjectProperties;
	private Set<OWLDataPropertyExpression> restrictedDataProperties;

	private OWLOntology ont;

	public RestrictionVisitor(OWLOntology ont) {
		restrictedObjectProperties = new HashSet<OWLObjectPropertyExpression>();
		restrictedDataProperties=new HashSet<OWLDataPropertyExpression>();
		processedClasses = new HashSet<OWLClass>();
		this.ont = ont;
	}

	public Set<OWLObjectPropertyExpression> getRestrictedObjectProperties() {
		return restrictedObjectProperties;
	}
	public Set<OWLDataPropertyExpression> getRestrictedDataProperties() {
		return restrictedDataProperties;
	}
	@Override
	public void visit(OWLClass desc) {
		if (!processedClasses.contains(desc)) {
			// If we are processing inherited restrictions then we
			// recursively visit named supers. Note that we need to keep
			// track of the classes that we have processed so that we don't
			// get caught out by cycles in the taxonomy
			processedClasses.add(desc);
			// for (OWLOntology ont : onts)
			{
				for (OWLSubClassOfAxiom ax : ont
						.getSubClassAxiomsForSubClass(desc)) {
					ax.getSuperClass().accept(this);
				}
			}
		}
	}

	@Override
	public void visit(OWLObjectSomeValuesFrom desc) {
		// This method gets called when a class expression is an existential
		// (someValuesFrom) restriction and it asks us to visit it
		restrictedObjectProperties.add(desc.getProperty());
		// System.out.println(desc.getProperty());
	}

	@Override
	public void visit(OWLObjectHasValue desc) {
		// This method gets called when a class expression is an existential
		// (someValuesFrom) restriction and it asks us to visit it
		restrictedObjectProperties.add(desc.getProperty());
		// System.out.println(desc.getProperty());
	}

	@Override
	public void visit(OWLObjectAllValuesFrom desc) {
		// This method gets called when a class expression is an existential
		// (someValuesFrom) restriction and it asks us to visit it
		restrictedObjectProperties.add(desc.getProperty());
		// System.out.println(desc.getProperty());
	}

	@Override
	public void visit(OWLObjectExactCardinality desc) {
		restrictedObjectProperties.add(desc.getProperty());
		System.out.println(desc.getProperty());
	}
	// @Override
	// public void visit(OWLObjectMaxCardinality desc)
	// {
	// restrictedProperties.add(desc.getProperty());
	// }
	@Override
	public void visit(OWLDataSomeValuesFrom desc) {
		// This method gets called when a class expression is an existential
		// (someValuesFrom) restriction and it asks us to visit it
		restrictedDataProperties.add(desc.getProperty());
		// System.out.println(desc.getProperty());
	}

	@Override
	public void visit(OWLDataHasValue desc) {
		// This method gets called when a class expression is an existential
		// (someValuesFrom) restriction and it asks us to visit it
		restrictedDataProperties.add(desc.getProperty());
		// System.out.println(desc.getProperty());
	}

	@Override
	public void visit(OWLDataAllValuesFrom desc) {
		// This method gets called when a class expression is an existential
		// (someValuesFrom) restriction and it asks us to visit it
		restrictedDataProperties.add(desc.getProperty());
		// System.out.println(desc.getProperty());
	}

	@Override
	public void visit(OWLDataExactCardinality desc) {
		restrictedDataProperties.add(desc.getProperty());
		System.out.println(desc.getProperty());
	}

}