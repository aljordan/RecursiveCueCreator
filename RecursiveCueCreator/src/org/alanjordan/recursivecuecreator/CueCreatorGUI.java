/*
  CueCreatorGUI.java
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

import javax.swing.*;
import java.io.File;

/**
 *
 * @author  Alan Jordan
 */
public class CueCreatorGUI extends javax.swing.JFrame {

    File libraryRoot;
    File outputFolder;
    JFileChooser fcLibraryRoot = new JFileChooser();
    JFileChooser fcOutputFolder = new JFileChooser();
    RCCOptions options;

    /** Creates new form CueCreatorGUI */
    public CueCreatorGUI() {
        initComponents();
        initCustomComponents();
        options = new RCCOptions();
        initializeOptions();
    }

    private void initializeOptions() {
        mnuChkUseEmbeddedTags.setSelected(options.isUseEmbeddedTags());

        if (mnuChkUseEmbeddedTags.isSelected()) {
            mnuChkArtistInTrackTitle.setEnabled(true);
            mnuChkSubstituteComposer.setEnabled(true);
            mnuChkArtistInTrackTitle.setSelected(options.isArtistInTrackTitle());
            mnuChkSubstituteComposer.setSelected(options.isSubstituteComposer());
        } else {
            mnuChkArtistInTrackTitle.setSelected(false);
            mnuChkSubstituteComposer.setSelected(false);
            mnuChkArtistInTrackTitle.setEnabled(false);
            mnuChkSubstituteComposer.setEnabled(false);
        }

        mnuChkParseTitle.setSelected(options.isParseTitle());
        mnuChkParseTagsFromSingleFolder.setSelected(options.isParseTagsFromSingleFolder());

        switch(options.getFoldersInCueSheetName()) {
            case 1: mnuRdoSingleParent.setSelected(true); break;
            case 2: mnuRdoDoubleParent.setSelected(true); break;
            case 3: mnuRdoTripleParent.setSelected(true); break;
        }

        if (options.isUseCustomOutputDirectory() && (options.getOutputFolder() != null)) {
            mnuChkUseCustomOutputDirectory.setSelected(options.isUseCustomOutputDirectory());
            chkUseCustomOutputDir.setSelected(options.isUseCustomOutputDirectory());
            btnSetOutputFolder.setEnabled(true);
            outputFolder = options.getOutputFolder();
            lblOutputFolder.setText("Cue Output Folder - " + outputFolder.getPath());
        }
        if (options.getLibraryRoot() != null) {
            libraryRoot = options.getLibraryRoot();
            lblLibraryRoot.setText("Library Root - " + libraryRoot.getPath());
            btnProcess.setEnabled(true);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnSetLibraryRoot = new javax.swing.JButton();
        lblLibraryRoot = new javax.swing.JLabel();
        chkUseCustomOutputDir = new javax.swing.JCheckBox();
        btnSetOutputFolder = new javax.swing.JButton();
        lblOutputFolder = new javax.swing.JLabel();
        btnProcess = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        mnuBarRCC = new javax.swing.JMenuBar();
        mnoOptions = new javax.swing.JMenu();
        mnuChkUseCustomOutputDirectory = new javax.swing.JCheckBoxMenuItem();
        mnuChkParseTitle = new javax.swing.JCheckBoxMenuItem();
        mnuChkParseTagsFromSingleFolder = new javax.swing.JCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        mnuChkUseEmbeddedTags = new javax.swing.JCheckBoxMenuItem();
        mnuChkArtistInTrackTitle = new javax.swing.JCheckBoxMenuItem();
        mnuChkSubstituteComposer = new javax.swing.JCheckBoxMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        mnuRdoSingleParent = new javax.swing.JRadioButtonMenuItem();
        mnuRdoDoubleParent = new javax.swing.JRadioButtonMenuItem();
        mnuRdoTripleParent = new javax.swing.JRadioButtonMenuItem();
        mnuHelp = new javax.swing.JMenu();
        mnuRCCHelp = new javax.swing.JMenuItem();
        mnuHelpAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnSetLibraryRoot.setText("Set Library Root");
        btnSetLibraryRoot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSetLibraryRoot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetLibraryRootActionPerformed(evt);
            }
        });

        lblLibraryRoot.setText("Library Root - Not Set ");

        chkUseCustomOutputDir.setText("Use Custom Output Directory (recommended for first time use)");
        chkUseCustomOutputDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkUseCustomOutputDirActionPerformed(evt);
            }
        });

        btnSetOutputFolder.setText("Set Output Folder");
        btnSetOutputFolder.setEnabled(false);
        btnSetOutputFolder.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSetOutputFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetOutputFolderActionPerformed(evt);
            }
        });

        lblOutputFolder.setText("Cue Output Folder - Wherever Music Files are Found");
        lblOutputFolder.setEnabled(false);

        btnProcess.setText("Process Library");
        btnProcess.setEnabled(false);
        btnProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessActionPerformed(evt);
            }
        });

        lblStatus.setText("Status: ");

        mnoOptions.setText("Options");

        mnuChkUseCustomOutputDirectory.setText("Use Custom Output Directory");
        mnuChkUseCustomOutputDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuChkUseCustomOutputDirectoryActionPerformed(evt);
            }
        });
        mnoOptions.add(mnuChkUseCustomOutputDirectory);

        mnuChkParseTitle.setText("Remove Track Number and File Extension from Track Title");
        mnuChkParseTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuChkParseTitleActionPerformed(evt);
            }
        });
        mnoOptions.add(mnuChkParseTitle);

        mnuChkParseTagsFromSingleFolder.setText("Parse Artist and Album from a Single Parent Folder");
        mnuChkParseTagsFromSingleFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuChkParseTagsFromSingleFolderActionPerformed(evt);
            }
        });
        mnoOptions.add(mnuChkParseTagsFromSingleFolder);
        mnoOptions.add(jSeparator1);

        mnuChkUseEmbeddedTags.setText("Use Embedded Tags when Processing FLAC and MP3");
        mnuChkUseEmbeddedTags.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuChkUseEmbeddedTagsActionPerformed(evt);
            }
        });
        mnoOptions.add(mnuChkUseEmbeddedTags);

        mnuChkArtistInTrackTitle.setText("Title Tracks \"Artist - Title\" when Track Artist Differs");
        mnuChkArtistInTrackTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuChkArtistInTrackTitleActionPerformed(evt);
            }
        });
        mnoOptions.add(mnuChkArtistInTrackTitle);

        mnuChkSubstituteComposer.setText("Substitute Composer for Genre when Genre is Classical");
        mnuChkSubstituteComposer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuChkSubstituteComposerActionPerformed(evt);
            }
        });
        mnoOptions.add(mnuChkSubstituteComposer);
        mnoOptions.add(jSeparator2);

        buttonGroup1.add(mnuRdoSingleParent);
        mnuRdoSingleParent.setText("Use One Parent Folder in Cue Sheet Name");
        mnuRdoSingleParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRdoSingleParentActionPerformed(evt);
            }
        });
        mnoOptions.add(mnuRdoSingleParent);

        buttonGroup1.add(mnuRdoDoubleParent);
        mnuRdoDoubleParent.setSelected(true);
        mnuRdoDoubleParent.setText("Use Two Parent Folders in Cue Sheet Name");
        mnuRdoDoubleParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRdoDoubleParentActionPerformed(evt);
            }
        });
        mnoOptions.add(mnuRdoDoubleParent);

        buttonGroup1.add(mnuRdoTripleParent);
        mnuRdoTripleParent.setText("Use Three Parent Folders in Cue Sheet Name");
        mnuRdoTripleParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRdoTripleParentActionPerformed(evt);
            }
        });
        mnoOptions.add(mnuRdoTripleParent);

        mnuBarRCC.add(mnoOptions);

        mnuHelp.setText("Help");

        mnuRCCHelp.setText("Recursive Cue Creator Help");
        mnuRCCHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRCCHelpActionPerformed(evt);
            }
        });
        mnuHelp.add(mnuRCCHelp);

        mnuHelpAbout.setText("About Recursive Cue Creator");
        mnuHelpAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHelpAboutActionPerformed(evt);
            }
        });
        mnuHelp.add(mnuHelpAbout);

        mnuBarRCC.add(mnuHelp);

        setJMenuBar(mnuBarRCC);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkUseCustomOutputDir)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSetLibraryRoot)
                        .addGap(30, 30, 30)
                        .addComponent(lblLibraryRoot))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSetOutputFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblOutputFolder))
                    .addComponent(btnProcess)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLibraryRoot)
                    .addComponent(btnSetLibraryRoot))
                .addGap(18, 18, 18)
                .addComponent(chkUseCustomOutputDir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSetOutputFolder)
                    .addComponent(lblOutputFolder))
                .addGap(18, 18, 18)
                .addComponent(btnProcess)
                .addGap(18, 18, 18)
                .addComponent(lblStatus)
                .addContainerGap(236, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initCustomComponents() {
        fcLibraryRoot.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fcOutputFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

private void btnSetLibraryRootActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetLibraryRootActionPerformed
    int retval = fcLibraryRoot.showOpenDialog(this);
    if (retval == JFileChooser.APPROVE_OPTION) {
        libraryRoot = fcLibraryRoot.getSelectedFile();
        options.setLibraryRoot(libraryRoot);
        lblLibraryRoot.setText("Library Root - " + libraryRoot.getPath());
        btnProcess.setEnabled(true);
    }
}//GEN-LAST:event_btnSetLibraryRootActionPerformed

public void setStatus(String status) {
    lblStatus.setText("Status: " + status);    
}

private void chkUseCustomOutputDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkUseCustomOutputDirActionPerformed
    // enable/disable output folder button 
    if (chkUseCustomOutputDir.isSelected()) {
        btnSetOutputFolder.setEnabled(true);
        lblOutputFolder.setEnabled(true);
        btnSetOutputFolder.doClick();
    } else {
        btnSetOutputFolder.setEnabled(false);
        options.setOutputFolder(null);
        lblOutputFolder.setText("Cue Output Folder - Wherever Music Files are Found");
        lblOutputFolder.setEnabled(false);
    }
    // set menu option appropriately.
    mnuChkUseCustomOutputDirectory.setSelected(chkUseCustomOutputDir.isSelected());
    options.setUseCustomOutputDirectory(chkUseCustomOutputDir.isSelected());
}//GEN-LAST:event_chkUseCustomOutputDirActionPerformed

private void btnSetOutputFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetOutputFolderActionPerformed
    int retval = fcOutputFolder.showOpenDialog(this);
    if (retval == JFileChooser.APPROVE_OPTION) {
        outputFolder = fcOutputFolder.getSelectedFile();
        options.setOutputFolder(outputFolder);
        lblOutputFolder.setText("Cue Output Folder - " + outputFolder.getPath());
    }
    else {
        chkUseCustomOutputDir.doClick();
    }
}//GEN-LAST:event_btnSetOutputFolderActionPerformed


private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed
    String outputPath;
    if (mnuChkUseCustomOutputDirectory.isSelected()) {
        outputPath = outputFolder.getPath();
    } else {
        outputPath = null;
    }
    
    RecursiveCueCreator rcc = new RecursiveCueCreator(options, this);
    lblStatus.setText("Status: Processing music directory");
    rcc.start();
}//GEN-LAST:event_btnProcessActionPerformed

private void mnuRCCHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRCCHelpActionPerformed
    try {
        java.net.URL url = getClass().getResource("RecursiveCueCreatorHelp.html");
        HelpWindow hw = new HelpWindow("Recursive Cue Creator Help",url);
    }
    catch (Exception e) {
        System.out.println(e.getMessage());
    }
}//GEN-LAST:event_mnuRCCHelpActionPerformed

private void mnuHelpAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuHelpAboutActionPerformed
    try {
        java.net.URL url = getClass().getResource("AboutRCC.html");
        HelpWindow hw = new HelpWindow("About Recursive Cue Creator",url);
    }
    catch (Exception e) {
        System.out.println(e.getMessage());
    }
}//GEN-LAST:event_mnuHelpAboutActionPerformed

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    options.saveOptions();
}//GEN-LAST:event_formWindowClosing

private void mnuChkSubstituteComposerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuChkSubstituteComposerActionPerformed
    options.setSubstituteComposer(mnuChkSubstituteComposer.isSelected());
}//GEN-LAST:event_mnuChkSubstituteComposerActionPerformed

private void mnuChkUseEmbeddedTagsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuChkUseEmbeddedTagsActionPerformed
    if (mnuChkUseEmbeddedTags.isSelected()) {
        mnuChkArtistInTrackTitle.setEnabled(true);
        mnuChkSubstituteComposer.setEnabled(true);
    } else {
        mnuChkArtistInTrackTitle.setSelected(false);
        mnuChkSubstituteComposer.setSelected(false);
        mnuChkArtistInTrackTitle.setEnabled(false);
        mnuChkSubstituteComposer.setEnabled(false);
    }
    options.setUseEmbeddedTags(mnuChkUseEmbeddedTags.isSelected());
    options.setArtistInTrackTitle(mnuChkArtistInTrackTitle.isSelected());
    options.setSubstituteComposer(mnuChkSubstituteComposer.isSelected());
}//GEN-LAST:event_mnuChkUseEmbeddedTagsActionPerformed

private void mnuChkUseCustomOutputDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuChkUseCustomOutputDirectoryActionPerformed
    //chkUseCustomOutputDir.setSelected(mnuChkUseCustomOutputDirectory.isSelected());
    chkUseCustomOutputDir.doClick();
}//GEN-LAST:event_mnuChkUseCustomOutputDirectoryActionPerformed

private void mnuRdoSingleParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRdoSingleParentActionPerformed
    options.setFoldersInCueSheetName(1);
}//GEN-LAST:event_mnuRdoSingleParentActionPerformed

private void mnuRdoDoubleParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRdoDoubleParentActionPerformed
    options.setFoldersInCueSheetName(2);
}//GEN-LAST:event_mnuRdoDoubleParentActionPerformed

private void mnuRdoTripleParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRdoTripleParentActionPerformed
        options.setFoldersInCueSheetName(3);
}//GEN-LAST:event_mnuRdoTripleParentActionPerformed

private void mnuChkParseTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuChkParseTitleActionPerformed
    options.setParseTitle(mnuChkParseTitle.isSelected());
}//GEN-LAST:event_mnuChkParseTitleActionPerformed

private void mnuChkParseTagsFromSingleFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuChkParseTagsFromSingleFolderActionPerformed
    if (mnuChkParseTagsFromSingleFolder.isSelected()) {
        String seperator = JOptionPane.showInputDialog(this,"Enter the characters that separate Artist from Album Title","Enter Seperator",1);
        if (seperator != null) {
            options.setParseTagsFromSingleFolder(true);
            options.setParseSingleFolderSeperator(seperator);
        }
        else {
            options.setParseTagsFromSingleFolder(false);
            mnuChkParseTagsFromSingleFolder.setSelected(false);
        }
    }
    else {
        options.setParseTagsFromSingleFolder(false);
    }
}//GEN-LAST:event_mnuChkParseTagsFromSingleFolderActionPerformed

private void mnuChkArtistInTrackTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuChkArtistInTrackTitleActionPerformed
    options.setArtistInTrackTitle(mnuChkArtistInTrackTitle.isSelected());
}//GEN-LAST:event_mnuChkArtistInTrackTitleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProcess;
    private javax.swing.JButton btnSetLibraryRoot;
    private javax.swing.JButton btnSetOutputFolder;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkUseCustomOutputDir;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblLibraryRoot;
    private javax.swing.JLabel lblOutputFolder;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JMenu mnoOptions;
    private javax.swing.JMenuBar mnuBarRCC;
    private javax.swing.JCheckBoxMenuItem mnuChkArtistInTrackTitle;
    private javax.swing.JCheckBoxMenuItem mnuChkParseTagsFromSingleFolder;
    private javax.swing.JCheckBoxMenuItem mnuChkParseTitle;
    private javax.swing.JCheckBoxMenuItem mnuChkSubstituteComposer;
    private javax.swing.JCheckBoxMenuItem mnuChkUseCustomOutputDirectory;
    private javax.swing.JCheckBoxMenuItem mnuChkUseEmbeddedTags;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JMenuItem mnuHelpAbout;
    private javax.swing.JMenuItem mnuRCCHelp;
    private javax.swing.JRadioButtonMenuItem mnuRdoDoubleParent;
    private javax.swing.JRadioButtonMenuItem mnuRdoSingleParent;
    private javax.swing.JRadioButtonMenuItem mnuRdoTripleParent;
    // End of variables declaration//GEN-END:variables
}