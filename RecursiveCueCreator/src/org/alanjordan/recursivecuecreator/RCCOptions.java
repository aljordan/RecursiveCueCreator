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

public class RCCOptions implements java.io.Serializable {
    private boolean useCustomOutputDirectory; //Write cue sheets to separate folder
    private File outputFolder; // Output folder to write cue sheets when above is selected
    private String outputFolderPath; //String representation of above var because can't always serialize File object
    private boolean useEmbeddedTags; //Read embedded tags from FLAC and MP3 files
    private boolean substituteComposer; //Classical music
    private File libraryRoot; //Root folder of music library
    private String libraryRootPath; //String representation of above var because can't always serialize File object
    private int foldersInCueSheetName; //How many parent directories to you in cue sheet file name
    private boolean parseTitle; //Remove track number and file extension from track names
    private boolean parseTagsFromSingleFolder; //Parse artist and album from single parent folder instead of two parent folders.
    private String parseSingleFolderSeperator; //String to use when parsing tags from single parent folder
    private boolean artistInTrackTitle; //Various artist - performer - title overrides

    public RCCOptions() {
        initOptions();
    }

    private void initOptions() {
        try {
            FileInputStream f_in = new FileInputStream("rccOptions.data");
            ObjectInputStream obj_in = new ObjectInputStream (f_in);
            Object obj = obj_in.readObject();

            if (obj instanceof RCCOptions) {
                RCCOptions tempOptions = (RCCOptions)obj;
                this.substituteComposer = tempOptions.isSubstituteComposer();
                this.useEmbeddedTags = tempOptions.isUseEmbeddedTags();
                this.parseTitle = tempOptions.isParseTitle();
                this.foldersInCueSheetName = tempOptions.getFoldersInCueSheetName();
                this.parseTagsFromSingleFolder = tempOptions.isParseTagsFromSingleFolder();
                this.parseSingleFolderSeperator = tempOptions.getParseSingleFolderSeperator();
                this.artistInTrackTitle = tempOptions.isArtistInTrackTitle();
                this.useCustomOutputDirectory = tempOptions.isUseCustomOutputDirectory();
                if (this.useCustomOutputDirectory) {
                    this.outputFolder = new File(tempOptions.getOutputFolderPath()); 
                    this.outputFolderPath = outputFolder.getPath();
                }
                else {
                    this.outputFolder = null;
                    this.outputFolderPath = null;
                }
                if (tempOptions.getLibraryRootPath() != null) {
                    this.libraryRoot = new File(tempOptions.getLibraryRootPath()); 
                    this.libraryRootPath = libraryRoot.getPath();
                }
                else {
                    this.libraryRoot = null;
                    this.libraryRootPath = null;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            useCustomOutputDirectory = false;
            useEmbeddedTags = false;
            substituteComposer = false;
            parseTitle = false;
            parseTagsFromSingleFolder = false;
            artistInTrackTitle = false;
            libraryRoot = null;
            libraryRootPath = null;
            outputFolder = null;
            outputFolderPath = null;
            foldersInCueSheetName = 2;
        }
    }

    public void saveOptions() {
        //set objects in a manner that will be serializable.  File object at root of drive
        // can't be serialized and unserialized.
        if (this.useCustomOutputDirectory) {
            // rely on string instead of File object
            outputFolderPath = outputFolder.getPath();
            outputFolder = null;
        }
        else {
            outputFolder = null;
            outputFolderPath = null;
        }

        try {
            libraryRootPath = libraryRoot.getPath();
            libraryRoot = null;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            libraryRootPath = null;
            libraryRoot = null;
        }
        
        try {
            FileOutputStream f_out = new FileOutputStream("rccOptions.data");
            ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
            obj_out.writeObject(this);
        }
        catch (FileNotFoundException fe) {
            System.out.println(fe.getMessage());
            System.out.println(fe.getStackTrace());
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            System.out.println(ioe.getStackTrace());
        }
    }

    /**
     * @return the useCustomOutputDirectory
     */
    public boolean isUseCustomOutputDirectory() {
        return useCustomOutputDirectory;
    }

    /**
     * @param useCustomOutputDirectory the useCustomOutputDirectory to set
     */
    public void setUseCustomOutputDirectory(boolean useCustomOutputDirectory) {
        this.useCustomOutputDirectory = useCustomOutputDirectory;
    }

    /**
     * @return the useEmbeddedTags
     */
    public boolean isUseEmbeddedTags() {
        return useEmbeddedTags;
    }

    /**
     * @param useEmbeddedTags the useEmbeddedTags to set
     */
    public void setUseEmbeddedTags(boolean useEmbeddedTags) {
        this.useEmbeddedTags = useEmbeddedTags;
    }

    /**
     * @return the substituteComposer
     */
    public boolean isSubstituteComposer() {
        return substituteComposer;
    }

    /**
     * @param substituteComposer the substituteComposer to set
     */
    public void setSubstituteComposer(boolean substituteComposer) {
        this.substituteComposer = substituteComposer;
    }

    /**
     * @return the libraryRoot
     */
    public File getLibraryRoot() {
        return libraryRoot;
    }

    /**
     * @param libraryRoot the libraryRoot to set
     */
    public void setLibraryRoot(File libraryRoot) {
        this.libraryRoot = libraryRoot;
        this.libraryRootPath = libraryRoot.getPath();
    }

    /**
     * @return the outputFolder
     */
    public File getOutputFolder() {
        return outputFolder;
    }

    /**
     * @param outputFolder the outputFolder to set
     */
    public void setOutputFolder(File outputFolder) {
        this.outputFolder = outputFolder;
        if (outputFolder != null) {
            this.outputFolderPath = outputFolder.getPath();
        }
        else {
            this.outputFolderPath = null; 
        }
    }

    /**
     * @return the foldersInCueSheetName
     */
    public int getFoldersInCueSheetName() {
        return foldersInCueSheetName;
    }

    /**
     * @param foldersInCueSheetName the foldersInCueSheetName to set
     */
    public void setFoldersInCueSheetName(int foldersInCueSheetName) {
        this.foldersInCueSheetName = foldersInCueSheetName;
    }

    public String getLibraryRootPath() {
        return libraryRootPath;
    }

    public String getOutputFolderPath() {
        return outputFolderPath;
    }

    /**
     * @return the parseTitle
     */
    public boolean isParseTitle() {
        return parseTitle;
    }

    /**
     * @param parseTitle the parseTitle to set
     */
    public void setParseTitle(boolean parseTitle) {
        this.parseTitle = parseTitle;
    }

    /**
     * @return the parseTagsFromSingleFolder
     */
    public boolean isParseTagsFromSingleFolder() {
        return parseTagsFromSingleFolder;
    }

    /**
     * @param parseTagsFromSingleFolder the parseTagsFromSingleFolder to set
     */
    public void setParseTagsFromSingleFolder(boolean parseTagsFromSingleFolder) {
        this.parseTagsFromSingleFolder = parseTagsFromSingleFolder;
    }

    /**
     * @return the parseSingleFolderSeperator
     */
    public String getParseSingleFolderSeperator() {
        return parseSingleFolderSeperator;
    }

    /**
     * @param parseSingleFolderSeperator the parseSingleFolderSeperator to set
     */
    public void setParseSingleFolderSeperator(String parseSingleFolderSeperator) {
        this.parseSingleFolderSeperator = parseSingleFolderSeperator;
    }

    /**
     * @return the artistInTrackTitle
     */
    public boolean isArtistInTrackTitle() {
        return artistInTrackTitle;
    }

    /**
     * @param artistInTrackTitle the artistInTrackTitle to set
     */
    public void setArtistInTrackTitle(boolean compilationOptimization) {
        this.artistInTrackTitle = compilationOptimization;
    }


}
