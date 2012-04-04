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
import java.util.Vector;


/**
 *
 * @author Alan Jordan
 */
public class RecursiveCueCreator extends Thread {

    private File[] files;
    private Vector<Album> rippedDiscs;
    private CueCreatorGUI parentWindow;
    private RCCOptions options;

    public RecursiveCueCreator(RCCOptions options, CueCreatorGUI parentWindow) {
        this.options = options;
        this.parentWindow = parentWindow;
        rippedDiscs = new Vector<Album>();
    }

    @Override
    public void run() {
        FilenameFilter filter = new FlacFilter();
        boolean atBeginningOfLibrary = true;
        boolean okToUseTags;
        TagReader tr;
        String path = "";
        Album rippedDisc = new Album();

        parentWindow.setStatus("Recursing directories to find music files");
        try { sleep(1);} catch (InterruptedException ie) {}
        
        this.files = RecurseDirectory.listFilesAsArray(options.getLibraryRoot(), filter ,true);
        for (File file : files) {
            if (options.isUseEmbeddedTags()  && ((file.getName().toLowerCase()).endsWith(".flac")
                    || (file.getName().toLowerCase()).endsWith(".mp3")))
                okToUseTags = true;
            else
                okToUseTags = false;
            
            File parentDirectory = file.getParentFile();
            
            String[] splitPath = (file.toString()).split("\\\\");
            if (parentDirectory.getPath().equals(path) == false) {
                //new album
                path = parentDirectory.getPath();
                parentWindow.setStatus("Processing " + path);
                try { sleep(1);} catch (InterruptedException ie) {}
                
                // add current Album first
                if (atBeginningOfLibrary == false) {
                    rippedDiscs.add(rippedDisc);
                }
                atBeginningOfLibrary = false;

                rippedDisc = new Album();


                // Get General Artist - Album information
                if (okToUseTags) {
                    try {
                        tr = new TagReader(file);
                        tr.processSong();
                        rippedDisc.setAlbumTitle(tr.getAlbum());
                        rippedDisc.setDate(tr.getDate());
                        
                        if (tr.getAlbumArtist() != null && !tr.getAlbumArtist().isEmpty())
                            rippedDisc.setArtist(tr.getAlbumArtist());
                        else
                            rippedDisc.setArtist(tr.getArtist());
                        
                        if (options.isSubstituteComposer()) {
                            if (tr.getComposer() != null && !tr.getComposer().isEmpty() && tr.getGenre().startsWith("Classical")) {
                                rippedDisc.setGenre(tr.getComposer());
                            } else {
                              rippedDisc.setGenre(tr.getGenre());
                            }
                        } else {
                            rippedDisc.setGenre(tr.getGenre());
                        }
                    }
                    catch (Exception e) {
                        rippedDisc.setAlbumTitle(splitPath[splitPath.length -2]);
                        rippedDisc.setArtist(splitPath[splitPath.length -3]);
                        parentWindow.setStatus(e.getMessage());
                        System.out.println(e.getStackTrace());
                    }
                    
                }
                else {
                    if (options.isParseTagsFromSingleFolder()) {
                        try {
                            rippedDisc.setAlbumTitle(getAlbumNameFromSingleFolder(splitPath[splitPath.length -2]));
                        } catch (Exception e) {
                            System.out.println("Can't parse album name from single folder name.");
                            System.out.println("You will need to manually edit " + splitPath[splitPath.length -2]);
                            System.out.println(e.getMessage());
                            System.out.println(e.getStackTrace());
                            rippedDisc.setAlbumTitle(splitPath[splitPath.length -2]);
                        }
                        try {
                            rippedDisc.setArtist(getArtistNameFromSingleFolder(splitPath[splitPath.length -2]));
                        } catch (Exception e) {
                            System.out.println("Can't parse artist from single folder name.");
                            System.out.println("You will need to manually edit " + splitPath[splitPath.length -2]);
                            System.out.println(e.getMessage());
                            System.out.println(e.getStackTrace());
                            rippedDisc.setArtist(splitPath[splitPath.length -2]);
                        }
                    }
                    else {
                        rippedDisc.setAlbumTitle(splitPath[splitPath.length -2]);
                        rippedDisc.setArtist(splitPath[splitPath.length -3]);
                    }

                    //write genre if using genre - artist - album folder structure
                    if (options.getFoldersInCueSheetName() == 3) {
                        try {
                            rippedDisc.setGenre(splitPath[splitPath.length -4]);
                        }
                        catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                rippedDisc.setDirectoryOnDisc(parentDirectory);
            }
            
            if (okToUseTags) {
                try {
                    tr = new TagReader(file);
                    tr.processSong();
                    if ( options.isArtistInTrackTitle()
                            && (tr.getArtist() != null && !tr.getArtist().isEmpty())
                            && (rippedDisc.getArtist() != null && !rippedDisc.getArtist().isEmpty())
                            && (!tr.getArtist().equalsIgnoreCase(rippedDisc.getArtist()))
                            ) {
                        rippedDisc.addTrack(new Track(splitPath[splitPath.length -1], tr.getArtist() + " - " + tr.getTitle(), tr.getArtist()));
                        
                    }
                    else {
                        if ((tr.getArtist() != null && !tr.getArtist().isEmpty())) {
                            rippedDisc.addTrack(new Track(splitPath[splitPath.length -1], tr.getTitle(), tr.getArtist()));                            
                        }
                        else {
                            rippedDisc.addTrack(new Track(splitPath[splitPath.length -1], tr.getTitle()));
                        }
                    }
                }
                catch (Exception e) {
                    rippedDisc.addTrack(new Track(splitPath[splitPath.length -1], splitPath[splitPath.length -1]));                
                    parentWindow.setStatus(e.getMessage());
                    System.out.println(e.getStackTrace());
                }
            } 
            else {
                String trackTitle;
                if (options.isParseTitle()) {
                    trackTitle = parseTrackTitle(splitPath[splitPath.length -1]);
                }
                else {
                    trackTitle = splitPath[splitPath.length -1];
                }
                rippedDisc.addTrack(new Track(splitPath[splitPath.length -1], trackTitle));
            }

            okToUseTags = false;
        }
        // add last album
        rippedDiscs.add(rippedDisc);
        
        //write cue files
        writeCueFiles();
        parentWindow.setStatus("Cue creation complete");
    }

    private String parseTrackTitle(String trackTitle) {
        String result;

        try {
            //strip extension from track title
            int period = trackTitle.lastIndexOf(".");
            result = trackTitle.substring(0, period);

            while (isFirstCharStripable(result))
            {
                result = result.substring(1);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            result = trackTitle;
        }

        return result;
    }

    private boolean isFirstCharStripable(String str) {
        if (Character.isDigit(str.charAt(0)))
            return true;

        if (str.startsWith(" "))
            return true;

        if (str.startsWith("-"))
            return true;

        if (str.startsWith("_"))
            return true;

        if (str.startsWith("."))
            return true;

        return false;
    }

    private String getAlbumNameFromSingleFolder(String folderName) {
        String[] splitFolder = folderName.split(options.getParseSingleFolderSeperator());
        return splitFolder[1];
    }

    private String getArtistNameFromSingleFolder(String folderName) {
        String[] splitFolder = folderName.split(options.getParseSingleFolderSeperator());
        return splitFolder[0];
    }
    
    private void writeCueFiles() {
        if (rippedDiscs.isEmpty() != true) {
            for (Album album : rippedDiscs) {
                parentWindow.setStatus("Writing cue sheet for: " + album.getDirectoryOnDisc().toString());

                try { sleep(1);} catch (InterruptedException ie) {}

                CueWriter cw = new CueWriter(album, options);
                if (options.isUseCustomOutputDirectory()) {
                    cw.writeCue(options.getOutputFolder().getPath());
                }
                else {
                    cw.writeCue();
                }
            }
        }
        else {
           parentWindow.setStatus("No music files found.");
           try { sleep(2000);} catch (InterruptedException ie) {}
        }
    } 
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        CueCreatorGUI gui = new CueCreatorGUI();
        gui.setVisible(true);
    }

}

