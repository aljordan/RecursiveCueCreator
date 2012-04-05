/*
 * 
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
 *
 */

package org.alanjordan.recursivecuecreator;

import java.util.Vector;
import java.io.File;

public class Album {
    private String genre;
    private String artist;
    private String albumTitle;
    private String date;
    private Vector<Track> tracks;
    private File directoryOnDisc;
    
    public Album() {
        tracks = new Vector<Track>();
    }
    
    public void addTrack(Track song) {
        tracks.add(song);
    }
    
    public Track[] getTracks() {
        Track[] returnValue = new Track[tracks.size()];
        for (int counter = 0; counter < returnValue.length; counter++) {
            returnValue[counter] = (Track)tracks.get(counter);
        }
        return returnValue;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public File getDirectoryOnDisc() {
        return directoryOnDisc;
    }

    public void setDirectoryOnDisc(File directoryOnDisc) {
        this.directoryOnDisc = directoryOnDisc;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return artist + ": " + albumTitle;
    }
}
