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

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import minusone.gui.EventQueueExampleUi;
import minusone.logic.SoundPlayingEvent;

/**
 * 
 * @author Louis-Marie Matthews
 */
public class EventQueueExample
{
  private PriorityQueue<SoundPlayingEvent> soundQueue_;
  
  
  
  public EventQueueExample()
  {
    soundQueue_ = new PriorityQueue<SoundPlayingEvent>();
  }
  
  
  
  /**
   * This class is the main method of the program. It displays the GUI allowing
   * the user to choose a sound to play. For the purposes of demonstrating how
   * an event queue work, and also to prevent sucking up too much CPU power,
   * there is an "artificial" delay between the time the user clicks the "Play
   * sound 1" (or 2 or 3) and the time the sound is played (or rather, displayed
   * to the console).
   * 
   * @param args useless default main parameters
   * @throws InterruptedException 
   */
  public static void main(String[] args)
    throws InterruptedException
  {
    EventQueueExample eqe = new EventQueueExample();
    EventQueueExampleUi.createWindow(eqe);

    while (true) {
      // TODO: replace with TimeUnit.SECONDS.sleep or ScheduledExecutorService?
      Thread.sleep (1000); // prevents the application from using too much CPU
      try {
        eqe.update();
      }
      catch (NullPointerException exception) {
        continue;
      }
      catch (NoSuchElementException exception) {
        continue;
      }
    }
  }
  
  
  
  /**
   * When called, this method processes the next event in the priority queue.
   * This event is the one with the highest priority in the queue, because
   * events are sorted according to their priority when added.
   */
  private void update()
  {
    soundQueue_.remove().perform();
  }
  
  
  
  /**
   * This method adds an event to the queue. If another event with the same
   * filename is already in the event, it doesn't add the event to the queue but
   * increase the decibel level of the already present event.
   * 
   * @param eventToBeAdded the event to be added to the queue
   */
  public void addTask (SoundPlayingEvent eventToBeAdded)
  {
    SoundPlayingEvent doubleInQueue = null;
    
    Iterator<SoundPlayingEvent> i = soundQueue_.iterator();
    boolean doubleNotFoundYet = true;
    while (i.hasNext() && doubleNotFoundYet) {
      SoundPlayingEvent currentEvent = i.next();
      if (eventToBeAdded.getSoundFile().equals (currentEvent.getSoundFile())) {
        doubleInQueue = currentEvent;
        doubleNotFoundYet = false;
      }
    }
    
    if (doubleInQueue != null) {
      doubleInQueue.setVolume (doubleInQueue.getVolume() + 10);
    }
    else {
      soundQueue_.add(eventToBeAdded);
    }
  }
}
