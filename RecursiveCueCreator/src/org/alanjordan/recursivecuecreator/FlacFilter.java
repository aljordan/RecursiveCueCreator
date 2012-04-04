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

import java.io.FilenameFilter;
import java.io.File;

public class FlacFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
        return ((name.toLowerCase()).endsWith(".flac") 
                || (name.toLowerCase()).endsWith(".wav") 
                || (name.toLowerCase()).endsWith(".mp3") 
                || (name.toLowerCase()).endsWith(".ape"));
    }

}
