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

public class CueWriter {

    private Album cd;
    private String cueFileName;
    private Track[] tracks;
    private FileWriter outFile;
    private RCCOptions options;

    public CueWriter(Album somethingGood, RCCOptions options) {
        cd = somethingGood;
        this.options = options;

        switch (options.getFoldersInCueSheetName()) {
            case 1:
                cueFileName = cd.getAlbumTitle() + ".cue";
                break;
            case 2:
                cueFileName = cd.getArtist() + " - " + cd.getAlbumTitle() + ".cue";
                break;
            case 3:
                cueFileName = cd.getGenre() + " - " + cd.getArtist() + " - " + cd.getAlbumTitle() + ".cue";
                break;
        }

        tracks = cd.getTracks();
    }

    public void writeCue() {
        try {
            outFile = new FileWriter(cd.getDirectoryOnDisc().getAbsolutePath() + "\\" + cueFileName);
            process(false);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public void writeCue(String outputDirectory) {
        try {
            outFile = new FileWriter(outputDirectory + "\\" + cueFileName);
            process(true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    private void process(boolean writePath) {
        try {
            PrintWriter out = new PrintWriter(outFile);

            if (cd.getGenre() != null) {
                out.println("REM GENRE " + cd.getGenre());
            }

            if (cd.getDate() != null) {
                out.println("REM DATE " + cd.getDate());
            }

            out.println("PERFORMER \"" + cd.getArtist() + "\"");
            out.println("TITLE \"" + cd.getAlbumTitle() + "\"");
            for (int counter = 1; counter <= tracks.length; counter++) {
                if (writePath == true) {
                    out.println("FILE \"" + cd.getDirectoryOnDisc().getAbsolutePath() + "\\" + tracks[counter - 1].getFileName() + "\" WAVE");
                } else {
                    out.println("FILE \"" + tracks[counter - 1].getFileName() + "\" WAVE");
                }

                if (counter < 10) {
                    out.println("  TRACK 0" + counter + " AUDIO");
                } else {
                    out.println("  TRACK " + counter + " AUDIO");
                }
                out.println("    TITLE \"" + tracks[counter - 1].getTrackTitle() + "\"");

                if (tracks[counter - 1].getTrackPerformer() != null) {
                    out.println("    PERFORMER \"" + tracks[counter - 1].getTrackPerformer() + "\"");
                } else {
                    out.println("    PERFORMER \"" + cd.getArtist() + "\"");
                }
                out.println("    INDEX 01 00:00:00");
            }
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
// Write cue files as follows:
//PERFORMER "Tracy Chapman"
//TITLE "Tracy Chapman"
//FILE "C:\Music\01 Track01.wav" WAVE
//  TRACK 01 AUDIO
//    TITLE "Track01"
//    PERFORMER "Tracy Chapman"
//    INDEX 01 00:00:00
//FILE "Pink Floyd - The Wall 1.flac" WAVE
//  TRACK 02 AUDIO
//    TITLE "Track02"
//    INDEX 01 00:00:00
//FILE "Track03.wav" WAVE
//  TRACK 03 AUDIO
//    TITLE "Track03"
//    INDEX 01 00:00:00
