/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import viewModel.OntologyMerger;

/**
 *
 * @author ankitkhullar
 */
public class Ontologies {
    private static ArrayList<File> files;
    public static OWLOntology mergedOntology;
    //private static File file;

   
    public static void addFile(File file) {
        if(files==null)files= new ArrayList<File>();
        Ontologies.files.add(file);
    }
    public static void loadOntologies()
    {
           OWLOntologyManager manager=OWLManager.createOWLOntologyManager();
       
     for(File f:files)
     {
            try {
               manager.loadOntologyFromOntologyDocument(f);
             
               // System.out.println(ont);
            } catch (OWLOntologyCreationException ex) {
                Logger.getLogger(Ontologies.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
     mergedOntology=OntologyMerger.mergeOntologies(manager);
    }
}
