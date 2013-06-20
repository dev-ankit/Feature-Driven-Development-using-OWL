/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

import model.Ontologies;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

/**
 *
 * @author ankitkhullar
 */
public class Menu {
    private JMenuBar menuBar;
    private JMenu fileMenu,viewMenu,selectMenu,helpMenu;
    private JMenuItem loadMenuItem,viewMenuItem,viewRstnMenuItem,ViewLogicalAxiom,ViewProperties;
    private JMenuItem savemenuItem;
    private JMenuItem selectPropertiesMenuItem;        
    private JMenuItem aboutMenuItem;
    public Menu()
    {
        menuBar=new JMenuBar();
       MenuActionListner mActnListn=new MenuActionListner();
            fileMenu =new JMenu("File");
                loadMenuItem=new JMenuItem("Load Ontology");
                loadMenuItem.addActionListener(mActnListn);
                savemenuItem=new JMenuItem("Save as");
                savemenuItem.addActionListener(mActnListn);
                fileMenu.add(loadMenuItem);
        
        menuBar.add(fileMenu);
        
          viewMenu =new JMenu("View");
                viewMenuItem=new JMenuItem("All Axioms");
                viewMenuItem.addActionListener(mActnListn);
                viewMenu.add(viewMenuItem);
                ViewLogicalAxiom=new JMenuItem("Logical Axioms");
                ViewLogicalAxiom.addActionListener(mActnListn);
                viewMenu.add(ViewLogicalAxiom);
                viewRstnMenuItem=new JMenuItem("Restrictions");
                viewRstnMenuItem.addActionListener(mActnListn);
                viewMenu.add(viewRstnMenuItem);
                ViewProperties=new JMenuItem("Properties");
                ViewProperties.addActionListener(mActnListn);
          viewMenu.add(ViewProperties);
       menuBar.add(viewMenu);
          selectMenu=new JMenu("Select");
                selectPropertiesMenuItem=new JMenuItem("Properties");
                selectPropertiesMenuItem.addActionListener(mActnListn);
          selectMenu.add(selectPropertiesMenuItem);
       menuBar.add(selectMenu);
          helpMenu= new JMenu("Help");
               aboutMenuItem=new JMenuItem("About");
              aboutMenuItem.addActionListener(mActnListn);
           helpMenu.add(aboutMenuItem);
       menuBar.add(helpMenu);
    }
    public JMenuBar getMenuBar()
    {
        return menuBar;
    }

class MenuActionListner implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
       JMenuItem item=(JMenuItem)ae.getSource();
       if(item==loadMenuItem)
       {
           System.out.println("load it!");
           loadOntology();
           Ontologies.loadOntologies();
       }
       else if(item==savemenuItem)
       {
           System.out.println("Save it");
                try {
                    AppFrame.setContentPane(new view.SaveOntologyPanel());
                } catch (OWLOntologyCreationException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
       }
       else if(item==viewMenuItem)
       {
           System.out.println("view it!");
        //   Ontologies.loadOntologies();
           AppFrame.setContentPane(new view.ViewAllAxiomsPanel());
       }
       else if(item==viewRstnMenuItem)
       {
            System.out.println("view Restrictionsit!");
      //     Ontologies.loadOntologies();
           
           AppFrame.setContentPane(new view.ViewRestrictionsPanel());
    
       } 
       else if(item==ViewLogicalAxiom)
       {
            System.out.println("view Logical Axioms!");
      //     Ontologies.loadOntologies();
           
           AppFrame.setContentPane(new view.ViewLogicalAxiomsPanel());
    
       } 
       else if(item==ViewProperties)
       {
            System.out.println("view RProperties!");
    //       Ontologies.loadOntologies();
           
           AppFrame.setContentPane(new view.ViewPropertiesPanel());
    
       }
       else if(item ==selectPropertiesMenuItem)
       {
           System.out.println("Select Properties");
    //       Ontologies.loadOntologies();
           AppFrame.setContentPane(new view.SelectPropertiesPanel());
       }
       else if(item==aboutMenuItem)
       {
           About.show();
       }
    }
    void loadOntology()
    {
     JFileChooser fileChooser=new JFileChooser();
     fileChooser.setFileFilter(new TextFileFilter());
     int status=fileChooser.showOpenDialog(null);
     if(status==JFileChooser.APPROVE_OPTION)
     {
         File file=fileChooser.getSelectedFile();
         Ontologies.addFile(file);
                 System.out.println("done!");
 
       }
     else
     {
         System.out.println(":(");
     }
         
    }
private class TextFileFilter extends FileFilter
{
        @Override
     public boolean accept(File f)
    {
        if(f.isDirectory())
        {
            return true;
        }
        return f.getName().endsWith(".owl");
    }
 
        @Override
    public String getDescription()
    {
        return "Ontology files (*.owl)";
    }
}

}
}