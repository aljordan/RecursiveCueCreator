/*
  Copyright 2008 Alan Brent Jordan
  This file is part of Recursive Cue Creator.

  Recursive Cue Creator is free software: you can redistribute 
  it and/or modify it under the terms of the GNU General Public License 
  as published by the Free Software Foundation, version 3 of the License.

  Recursive Cue Creator is distributed in the hope that it will
  be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General 
  Public License for more details.

  You should have received a copy of the GNU General Public License along with 
  Recursive Cue Creator.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.alanjordan.recursivecuecreator;

import java.io.*;
import javax.swing.event.*;
import javax.swing.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;

public class HelpWindow extends JFrame implements ActionListener {

    private final int WIDTH = 600;
    private final int HEIGHT = 400;
    private JEditorPane editorpane;
    private URL helpURL;
    //////////////////////////////////////////////////////////////////
    /**
     * HelpWindow constructor
     * @param String and URL
     */
    public HelpWindow(String title, URL hlpURL) {
        super(title);
        helpURL = hlpURL;
        editorpane = new JEditorPane();
        editorpane.setEditable(false);
        try {
            editorpane.setPage(helpURL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //anonymous inner listener
        editorpane.addHyperlinkListener(new HyperlinkListener() {

            public void hyperlinkUpdate(HyperlinkEvent ev) {
                try {
                    if (ev.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        editorpane.setPage(ev.getURL());
                    }
                } catch (IOException ex) {
                    //put message in window
                    ex.printStackTrace();
                }
            }
        });
        getContentPane().add(new JScrollPane(editorpane));
        addButtons();
        // no need for listener just dispose
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // dynamically set location
        calculateLocation();
        setVisible(true);
    // end constructor
    }

    /**
     * An Actionlistener so must implement this method
     *
     */
    public void actionPerformed(ActionEvent e) {
        String strAction = e.getActionCommand();
        URL tempURL;
        try {
//            if (strAction.equals("Contents")) {
//                tempURL = editorpane.getPage();
//                editorpane.setPage(helpURL);
//            }
            if (strAction.equals("Close")) {
                // more portable if delegated
                processWindowEvent(new WindowEvent(this,
                        WindowEvent.WINDOW_CLOSING));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * add buttons at the south
     */
    private void addButtons() {
//        JButton btncontents = new JButton("Contents");
//        btncontents.addActionListener(this);
        JButton btnclose = new JButton("Close");
        btnclose.addActionListener(this);
        //put into JPanel
        JPanel panebuttons = new JPanel();
//        panebuttons.add(btncontents);
        panebuttons.add(btnclose);
        //add panel south
        getContentPane().add(panebuttons, BorderLayout.SOUTH);
    }

    /**
     * locate in middle of screen
     */
    private void calculateLocation() {
        Dimension screendim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(WIDTH, HEIGHT));
        int locationx = (screendim.width - WIDTH) / 2;
        int locationy = (screendim.height - HEIGHT) / 2;
        setLocation(locationx, locationy);
    }

    public static void main(String[] args) {
        URL index = ClassLoader.getSystemResource("index.html");
        new HelpWindow("Test", index);

    }
}//end HelpWindow class
