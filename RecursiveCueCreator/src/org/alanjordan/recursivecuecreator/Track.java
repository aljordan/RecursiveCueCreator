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

public class Track {
    
    private String fileName;
    private String trackTitle;
    private String trackPerformer;
    
    public Track (String fileName, String trackTitle) {
        this.fileName = fileName;
        this.trackTitle = trackTitle;
        this.trackPerformer = null;
    }

    public Track (String fileName, String trackTitle, String trackPerformer) {
        this.fileName = fileName;
        this.trackTitle = trackTitle;
        this.trackPerformer = trackPerformer;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    /**
     * @return the trackPerformer
     */
    public String getTrackPerformer() {
        return trackPerformer;
    }

    /**
     * @param trackPerformer the trackPerformer to set
     */
    public void setTrackPerformer(String trackPerformer) {
        this.trackPerformer = trackPerformer;
    }
    

}
