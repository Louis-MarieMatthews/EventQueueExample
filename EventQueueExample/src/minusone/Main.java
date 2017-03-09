/**
 * Copyright 2017 Louis-Marie Matthews
 * 
 * This file is part of Event Queue Example.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package minusone;

import minusone.gui.EventQueueExampleUi;
import minusone.logic.SoundProcessingRunnable;
import minusone.logic.SoundRequestEventQueue;

/**
 * Contains the main method, which runs the entire program.
 * 
 * @author Louis-Marie Matthews
 */
public class Main
{
  /**
   * This class is the main method of the program. It displays the GUI allowing
   * the user to choose a sound to play.
   * 
   * @param args useless default main parameters
   * @throws InterruptedException 
   */
  public static void main(String[] args)
    throws InterruptedException
  {
    // Creates the queue in which all the sound requests will be stored before
    // being processed.
    SoundRequestEventQueue queue = new SoundRequestEventQueue();
    
    // Creates the thread processing all the sound requests stored in the queue
    SoundProcessingRunnable waiter = new SoundProcessingRunnable(queue);
    Thread thread = new Thread (waiter, "GABOUI");
    thread.start();
    
    // Creates the window
    EventQueueExampleUi.createWindow(queue);
  }
}
