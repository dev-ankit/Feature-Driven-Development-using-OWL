/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.lang.System;

/**
 *
 * @author ankitkhullar
 */
public class AppFrame {

	private static JFrame frame=new JFrame("Select Ontologies");
       private static JPanel panel=null;
    public static void main(String[] args){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Menu menuBar=new Menu();
        frame.setJMenuBar(menuBar.getMenuBar());
        frame.setSize(800,500);
        frame.setTitle("Feature Driven Development using OWL");
      //  frame.setResizable(false);
        //frame.setMaximumSize(new Dimension(800, 500));
        frame.setVisible(true);
    }
    public static void refreshFrame()
    {
         frame.pack();
    }
    public static void setContentPane(JPanel NewPanel)
    {
        if(panel!=null)
        {
        frame.getContentPane().removeAll();   
        }
        panel=NewPanel;
        
        JScrollPane scrollPane=new JScrollPane(NewPanel);
        frame.getContentPane().add(scrollPane);
      
        frame.pack();
    }
}
/*File fs=new File("Ontologies/Education.owl");
       OWLOntologyManager manager=OWLManager.createOWLOntologyManager();
     //  OWLDataFactory dataFactory=manager.getOWLDataFactory();
       OWLOntology education=manager.loadOntologyFromOntologyDocument(fs);
       IRI educatioIiri=manager.getOntologyDocumentIRI(education);
      //  System.out.println("count:"+education);
        OWLOntologyWalker walker=new OWLOntologyWalker(Collections.singleton(education)); 
        OWLOntologyWalkerVisitor<Object> visitor = new OWLOntologyWalkerVisitor<Object>(walker){
        @Override
        public Object visit(OWLObjectProperty desc){
            System.out.println("\n\ndesc");
           // System.out.println("     "+getCurrentOntology()); //education.owl
            System.out.println("Axiom type: "+ getCurrentAxiom().getAxiomType());
           // System.out.println("signature: "+getCurrentAxiom().isLogicalAxiom()); not declration or annotation
            //System.out.println(""+getCurrentAxiom());
            Set<OWLClass> myset=getCurrentAxiom().getClassesInSignature();
                for (Iterator<OWLClass> it = myset.iterator(); it.hasNext();) {
                    OWLClass cls = it.next();
                    System.out.println("    class:"+cls);
                }
                Set<OWLDataProperty> mydataset=getCurrentAxiom().getDataPropertiesInSignature();
                for (Iterator<OWLDataProperty> it = mydataset.iterator(); it.hasNext();) {
                    OWLDataProperty cls = it.next();
                    System.out.println("    DataProp:"+cls);
                }
                Set<OWLObjectProperty> myobjprop=getCurrentAxiom().getObjectPropertiesInSignature();
                 for (Iterator<OWLObjectProperty> it = myobjprop.iterator(); it.hasNext();) {
                    OWLObjectProperty cls = it.next();
                    System.out.println("    ObjProp:"+cls);
                }       
            return null;
        }
        };
        walker.walkStructure(visitor);
        }
}
*/