/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import model.FeatureTemplate;
import model.SelectionTemplate;

/**
 *
 * @author ankitkhullar
 */
public class AddNewClassPanel extends JPanel{
        List<Object>objectList=new ArrayList<Object>();
        List<Object>resultList=new ArrayList<Object>();
        List <Selected>completeList=new ArrayList<Selected>();
    // JPanel horiz;
    public AddNewClassPanel()
    {
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
        JPanel horiz=new JPanel();
        BoxLayout layout = new BoxLayout (horiz, BoxLayout.X_AXIS);
        horiz.setLayout (layout);
        JButton b;
      //  b==new JButton("Back");
      //  b.addActionListener(new NavigateButtonListener());
      //  horiz.add(b);
        b =new JButton("Done");
        b.addActionListener(new NavigateButtonListener());
        horiz.add(b);
        add(horiz);
        add(addRow());
    }
    void generateLists()
    {
        resultList.add("--Select--");
        objectList.add("--Select--");
//        for(SelectionTemplate st:SelectionTemplate.finalList)
//        {
//            resultList.add(st);
//            if(!st.isDataProp)
//                objectList.add(st);
//        }
    }
    final JPanel addRow()
    {
        JPanel horiz=new JPanel();
        BoxLayout layout = new BoxLayout (horiz, BoxLayout.X_AXIS);
        horiz.setLayout (layout);
        
        String[] actionsList={"Assign","Calculate"};
        JComboBox actionBox=new JComboBox(actionsList);
        horiz.add(actionBox);
       
        //generate result and action list
        generateLists();
        
        resultList.add("Add More");
        JComboBox resultBox=new JComboBox(resultList.toArray());
        resultBox.addActionListener(new AddmoreActionListner());
        horiz.add(resultBox);
        
     //   JTextField rf=new JTextField();
     //   rf.setSize(20, 10);
     //   rf.setColumns(20);
     //   horiz.add(rf);
        
        String[] conjuctionsList={"to","for","of","on"};
        JComboBox conjuctionBox=new JComboBox(conjuctionsList);
        horiz.add(conjuctionBox);
       
        JComboBox objectBox=new JComboBox(objectList.toArray());
        horiz.add(objectBox);  
        
        JButton b=new JButton("Add");
        b.addActionListener(new AddButtonActionListner());
        horiz.add(b);
        return horiz;
    }
    private class AddButtonActionListner implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            JButton b=(JButton)arg0.getSource();
            b.setVisible(false);
//          b.setText("Remove");
            add(addRow());
            application.AppFrame.refreshFrame();
        }
    }
    private class AddmoreActionListner implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            JComboBox cb=(JComboBox)arg0.getSource();
            
           
            if(cb.getSelectedItem().getClass()== "".getClass())
            {
                if("Add More".equals(cb.getSelectedItem().toString()))
                {
                    String data=JOptionPane.showInputDialog("Enter Name");
                    cb.addItem(data);
                    cb.setSelectedItem(data);
                    cb.removeItem("Add");
                }
                //if the element selected is newly ceated data it'll fall in this category
                //but I am not saving it
                //TODO
            }
            else
            {
                
            }
        }
    }
    private class NavigateButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            System.out.println("not implemented");
        }
    }
    private class Selected
    {
        JComboBox action;
        JComboBox result;
        JComboBox object;
        FeatureTemplate feature;
        public Selected(String a,String r,String o)
        {
            
        }
        public void selectionChanged()
        {
        
        }
    }
}