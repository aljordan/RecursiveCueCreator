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

import org.jaudiotagger.audio.*;
import org.jaudiotagger.tag.*;
import java.io.File;
import java.util.regex.*;
//import java.util.Iterator;

public class TagReader {
    
    private File song;
    private Tag t;
    private String genre;
    private String title;
    private String artist;
    private String albumArtist;
    private String date;
    private String album;
    private String composer;
    
    public TagReader(File song) {
        this.song = song;
    }

    public void processSong() throws Exception {
        //char[] badCharacters = {'\\',':','|'};
        try {
            AudioFile f = AudioFileIO.read(song);
            this.t = f.getTag();

            this.artist = t.getFirst(FieldKey.ARTIST).trim();
            this.albumArtist = t.getFirst(FieldKey.ALBUM_ARTIST).trim();
            this.title = t.getFirst(FieldKey.TITLE).trim();
            this.genre = scrubString(t.getFirst(FieldKey.GENRE).trim());
            this.date = t.getFirst(FieldKey.YEAR).trim();
            this.album = scrubString(t.getFirst(FieldKey.ALBUM).trim());
            this.composer = t.getFirst(FieldKey.COMPOSER).trim();
            
//            Iterator iterator = t.getFields();
//            while(iterator.hasNext())
//            {
//                System.out.println(iterator.next());
//            }	


        }
        catch (Exception e){
            throw e;
        }
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDate() {
        return date;
    }

    public String getAlbum() {
        return album;
    }
    
    private String scrubString(String s) {
        String returnString;
        returnString = s.replaceAll("/", "");
        returnString = returnString.replaceAll("|","");
        returnString = returnString.replaceAll(":","");
        returnString = returnString.replaceAll("&","and");
        returnString = returnString.replaceAll("\"","");
        returnString = returnString.replaceAll(Matcher.quoteReplacement("\\"),"");
        return returnString;
    }
    
    public String getComposer() {
        return composer;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

}
