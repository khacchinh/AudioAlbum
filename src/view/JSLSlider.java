/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Administrator PC
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class JSLSlider extends JPanel {

    JPanel pnlMain=new JPanel(new SliderCardLayout());
    
    public JSLSlider() {
        setLayout(new BorderLayout());
 
        add(pnlMain, BorderLayout.CENTER);
 
        
 
    }
 
    public void previous() {
        SliderCardLayout cl=(SliderCardLayout)pnlMain.getLayout();
        Component currentComp=cl.getCurrentComponent(pnlMain);
        Component previousComp=cl.getPreviousComponent(pnlMain);
        Rectangle b=currentComp.getBounds();
        previousComp.setVisible(true);
        SliderListener sl=new SliderListener(10, currentComp, previousComp, false);
        Timer t=new Timer(40,sl);
        sl.timer=t;
        t.start();
    }
 
    public void next() {
        SliderCardLayout cl=(SliderCardLayout)pnlMain.getLayout();
        Component currentComp=cl.getCurrentComponent(pnlMain);
        Component nextComp=cl.getNextComponent(pnlMain);
        Rectangle b=currentComp.getBounds();
        nextComp.setVisible(true);
        SliderListener sl=new SliderListener(10, currentComp, nextComp, true);
        Timer t=new Timer(40,sl);
        sl.timer=t;
        t.start();
    }
 
    public void addSliderComponent(JComponent c) {
        pnlMain.add(c,""+getComponentCount());
    }
 
    public void refresh() {
        revalidate();
        repaint();
    }
 
    public class SliderListener implements ActionListener {
        Component c1;
        Component c2;
        int steps;
        int step=0;
        Timer timer;
        boolean isNext;
 
        public SliderListener(int steps, Component c1, Component c2, boolean isNext) {
            this.steps=steps;
            this.c1=c1;
            this.c2=c2;
            this.isNext=isNext;
        }
 
        public void actionPerformed(ActionEvent e) {
            Rectangle bounds=c1.getBounds();
            int shift=bounds.width/steps;
            if (!isNext) {
                c1.setLocation(bounds.x-shift, bounds.y);
                c2.setLocation(bounds.x-shift+bounds.width, bounds.y);
            }
            else {
                c1.setLocation(bounds.x+shift, bounds.y);
                c2.setLocation(bounds.x+shift-bounds.width, bounds.y);
            }
            pnlMain.repaint();
            step++;
            if (step==steps) {
                timer.stop();
                c2.setVisible(false);
                CardLayout cl=(CardLayout)pnlMain.getLayout();
                if (isNext) {
                    cl.next(pnlMain);
                }
                else {
                    cl.previous(pnlMain);
                }
            }
        }
    } 
}