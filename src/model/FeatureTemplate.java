/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.owlapi.model.OWLClass;

/**
 *
 * @author ankitkhullar
 */
public class FeatureTemplate {
   public static List<String>actionList= new ArrayList<String>();
   public static List<String>resultList=new ArrayList<String>();
   public static List<String>objectList=new ArrayList<String>();
   
    String action;
    OWLClass result;
    Object object; 
    public FeatureTemplate(String act,OWLClass res, Object obj)
    {
        action=act;
        result=res;
        object=obj;
        
    }
    public FeatureTemplate(String act,OWLClass res, Object obj,Object rng)
    {
        action=act;
        result=res;
        object=obj;
    }
}
