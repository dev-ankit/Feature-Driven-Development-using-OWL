/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author ankitkhullar
 */
public class SelectionTemplate {
	public Set<ClassHandler> classList;
	private static SelectionTemplate singleton;

	private SelectionTemplate() {
		classList = new HashSet<ClassHandler>();
	}

	public static SelectionTemplate getSelectionTemplate() {
		if (singleton == null)
			singleton = new SelectionTemplate();
		return singleton;
	}

}
/*
 * public static List<SelectionTemplate>selectedList=new
 * ArrayList<SelectionTemplate>(); public static
 * List<SelectionTemplate>leftList=new ArrayList<SelectionTemplate>(); Object
 * item; public OWLClass container; public Boolean isDataProp; String name;
 * public SelectionTemplate(Object o,String n,OWLClass c,Boolean isData) {
 * 
 * item=o; container=c; name=n; isDataProp=isData; }
 * 
 * @Override public String toString() { return name; } public OWLClass
 * getDomain() { return container; } }
 */
