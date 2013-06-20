/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.semanticweb.owlapi.model.OWLClass;

/**
 *
 * @author ankitkhullar
 */
public class Common {
    public static String getClassName(OWLClass cls)
    {
        String name=cls.toString();
        int i=name.lastIndexOf("#");
        name=name.substring(i+1,name.length()-1);
        return name;
    }
    public static String getPropertyName(String name)
    {
        int i=name.indexOf("#");
        int j=name.indexOf(">");
        name=name.substring(i+1,j);
        return name;
    }
    
    public static String getDomainName(String name)
    {
        int i=name.indexOf("#");
        int j=name.indexOf(">");
        name=name.substring(i+1,j);
        return name;
    }
}
