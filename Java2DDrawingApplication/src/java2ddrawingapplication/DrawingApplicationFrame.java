/*
 *  a) an Undo button to undo the last shape drawn.
    b) a Clear button to clear all shapes from the drawing.
    c) a combo box for selecting the shape to draw, a line, oval, or rectangle.
    d) a checkbox which specifies if the shape should be filled or unfilled.
    e) a checkbox to specify whether to paint using a gradient.
    f) two JButtons that each show a JColorChooser dialog to allow the user to choose the first and second color in the gradient.
    g) a text field for entering the Stroke width.
    h) a text field for entering the Stroke dash length.
    I) a checkbox for specifying whether to draw a dashed or solid line.
    j) a JPanel on which the shapes are drawn.
    k) a status bar JLabel at the bottom of the frame that displays the current location of the mouse on the draw panel.
 */
package java2ddrawingapplication;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Divyesh Johri
 * 
 */
public class DrawingApplicationFrame extends JFrame
{

    // Create the panels for the top of the application. One panel for each
    // line and one to contain both of those panels.
    JPanel first, second, top; 
    
    // create the widgets for the firstLine Panel.
    JButton undo, clear;
    JLabel sh;
    JComboBox<String> pickShape;
    JCheckBox filled;
    
    //create the widgets for the secondLine Panel.
    JCheckBox gradient, dashed;
    JButton firstC, secondC;
    JLabel lw, dl;
    JTextField lineWidth, dashLength;    
    
    // Variables for drawPanel.
    DrawPanel drawPanel;
    int selected;
    Point point1 = new Point(0,0); 
    Point point2 = new Point(0,0);
    Boolean fill = false;
    Color color1 = Color.LIGHT_GRAY;
    Color color2 = Color.LIGHT_GRAY;
    Paint paint;
    Stroke stroke;
    float linewid;
    float dashlen;
    MyShapes currentShape;
    ArrayList<MyShapes> shapes = new ArrayList<MyShapes>();
    
    // add status label
    Point mousePnt = new Point(0,0);
    JLabel status;
  
    // Constructor for DrawingApplicationFrame
    public DrawingApplicationFrame()
    {
        first = new JPanel();
        second = new JPanel();
        top = new JPanel();
        drawPanel = new DrawPanel();
        
        // firstLine widgets
        undo = new JButton("Undo");
        clear = new JButton("Clear");
        sh = new JLabel("Shape:");
        pickShape = new JComboBox<String>(new String[]{"Line", "Oval", "Rectangle"});
        filled = new JCheckBox("Filled");
        
        // secondLine widgets
        gradient = new JCheckBox("Use Gradient");
        firstC = new JButton("1st Color...");
        secondC = new JButton("2nd Color...");
        lw = new JLabel("Line Width:");
        dl = new JLabel("Dash Length:");
        lineWidth = new JTextField("10",2);
        dashLength = new JTextField("15",2);
        dashed = new JCheckBox("Dashed");
        
        // add widgets to panels
        first.add(undo);
        first.add(clear);
        first.add(sh);
        first.add(pickShape);
        first.add(filled);
        
        second.add(gradient);
        second.add(firstC);
        second.add(secondC);
        second.add(lw);
        second.add(lineWidth);
        second.add(dl);
        second.add(dashLength);        
        second.add(dashed);
        
        // Status
        status = new JLabel();
        
        // add top panel of two panels
        top.setLayout(new BorderLayout());
        top.add(first, BorderLayout.NORTH);
        top.add(second, BorderLayout.SOUTH);

        // add topPanel to North, drawPanel to Center, and statusLabel to South
        add(top, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);
        
        //add listeners and event handlers
        undo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try{
                    shapes.remove(shapes.size()-1);
                    
                    Graphics g = drawPanel.getGraphics();
                    drawPanel.paintComponent(g);
                }catch(IndexOutOfBoundsException e){                    
                }
            }
        });
        
        clear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                shapes.clear();
                
                Graphics g = drawPanel.getGraphics();
                drawPanel.paintComponent(g);
            }
        });
        /*
        pickShape.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                selected = pickShape.getItemAt(pickShape.getSelectedIndex());                 
            }
        });
        
        filled.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                if(filled.isSelected()){
                    fill = true;
                }else{
                    fill = false;
                }
            }
        });
        
        gradient.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                if(gradient.isSelected()){
                    paint = new GradientPaint(0,0,color1,50,50,color2,true);
                } 
                else{
                    paint = color1;
                }
            }
        });
        */
        firstC.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                color1 = JColorChooser.showDialog(DrawingApplicationFrame.this, "Choose a color", color1);
                
                if(color1 == null){
                    color1 = Color.LIGHT_GRAY;
                }
                
                Graphics g = drawPanel.getGraphics();
                drawPanel.paintComponent(g);
            }
        });
        
        secondC.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                color2 = JColorChooser.showDialog(DrawingApplicationFrame.this, "Choose a color", color2);
                
                if(color2 == null){
                    color2 = Color.LIGHT_GRAY;
                }
                
                Graphics g = drawPanel.getGraphics();
                drawPanel.paintComponent(g);
            }
        });
        /*
        lineWidth.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                linewid = Integer.parseInt(event.getActionCommand());
            }
        });
        
        dashLength.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                dashlen = Integer.parseInt(event.getActionCommand());
            }
        });
        
        dashed.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                if(dashed.isSelected()){
                    stroke = new BasicStroke(linewid, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[dashlen], 0);
                }else{
                    stroke = new BasicStroke(linewid, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                }
            }
        });
        */
    }   
    

    // Create a private inner class for the DrawPanel.
    private class DrawPanel extends JPanel
    {

        public DrawPanel()
        {
            //Mouse handler
            MouseHandler handler = new MouseHandler(); 
            addMouseListener(handler);
            addMouseMotionListener(handler);
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            //loop through and draw each shape in the shapes arraylist
            if(shapes.size() > 0){
                shapes.forEach((shape) -> shape.draw(g2d));
            }
            
        }


        private class MouseHandler extends MouseAdapter implements MouseMotionListener
        {
            @Override
            public void mousePressed(MouseEvent event)
            {
                //Filled
                if(filled.isSelected()){
                    fill = true;
                }else{
                    fill = false;
                }
                
                //Gradient
                if(gradient.isSelected()){
                    paint = new GradientPaint(0,0,color1,50,50,color2,true);
                } 
                else{
                    paint = color1;
                }
                
                //Line Width
                linewid = Float.parseFloat(lineWidth.getText());
                
                //Dash Length
                dashlen = Float.parseFloat(dashLength.getText());
                
                //Dashed
                if(dashed.isSelected()){
                    stroke = new BasicStroke(linewid, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[]{dashlen}, 0);
                }else{
                    stroke = new BasicStroke(linewid, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);}
                
                //Points                
                point1 = new Point(event.getX(),event.getY());
                point2 = new Point(event.getX(),event.getY());                
                
                //Current Shape
                selected = pickShape.getSelectedIndex();
                
                //Add current shape to array and redraw
                Graphics g = getGraphics();
                switch (selected) {
                    case 0: //Line
                        currentShape = new MyLine(point1, point2, paint, stroke);
                        shapes.add(currentShape);                        
                        
                        paintComponent(g);
                        break;
                    case 1: //Oval
                        currentShape = new MyOval(point1, point2, paint, stroke, fill);
                        shapes.add(currentShape);
                        
                        paintComponent(g);
                        break;
                    case 2: //Rectangle
                        currentShape = new MyRectangle(point1, point2, paint, stroke, fill);
                        shapes.add(currentShape);
                        
                        paintComponent(g);
                        break;
                    default:
                        break;
                }                  
            }                
            
            @Override
            public void mouseReleased(MouseEvent event)
            {
                //Set new endpoint and redraw
                currentShape.setEndPoint(event.getPoint());
                
                Graphics g = getGraphics();
                paintComponent(g);
            }

            @Override
            public void mouseDragged(MouseEvent event)
            {
                //Set new endpoint and redraw
                currentShape.setEndPoint(event.getPoint());
                
                Graphics g = getGraphics();
                paintComponent(g);
            }

            @Override
            public void mouseMoved(MouseEvent event)
            {
                status.setText(String.format("(%d, %d)", event.getX(), event.getY()));
            }
        }

    }
}

