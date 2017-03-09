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
package minusone.logic;

import java.util.NoSuchElementException;

/**
 * This class handles the processing of the queued sound request messages.
 * 
 * @author Louis-Marie Matthews
 */
public class SoundProcessingRunnable
  implements Runnable
{
  private SoundRequestEventQueue soundQueue_;
  
  
  
  public SoundProcessingRunnable(SoundRequestEventQueue soundQueue)
  {
    soundQueue_ = soundQueue;
  }
  


  /**
   * When running, this method processes, in the right order, all the sound
   * requests stored in the sound event queue of its instance. If or when the
   * queue is empty, it waits for it to be filled, and processes it again.
   */
  @Override
  public void run()
  {
    synchronized (soundQueue_) {
      while (true) {
        if (soundQueue_.isEmpty()) {
          try {
            soundQueue_.wait();
          }
          catch (InterruptedException ex) {
            System.err.println("Exception at EventQueueExample#run()");
          }
        }
        else {
          try {
            soundQueue_.update();
          }
          catch (InterruptedException ex) {
            System.err.println("Could not update the queue.");
          }
          catch (NullPointerException|NoSuchElementException exception) {
            System.err.println("Queue is empty.");
          }
        }
      }
    }
  }
}
