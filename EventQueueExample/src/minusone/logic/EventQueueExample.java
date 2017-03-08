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
 * @author louis-mariematthews
 */
public class EventQueueExample
{
  private PriorityQueue<SoundPlayingEvent> soundQueue_;
  
  
  
  public EventQueueExample()
  {
    soundQueue_ = new PriorityQueue<SoundPlayingEvent>();
  }
  
  
  
  public static void main(String[] args)
    throws InterruptedException
  {
    EventQueueExample eqe = new EventQueueExample();
    EventQueueExampleUi.createWindow(eqe);

    while (true) {
      // TODO: replace with TimeUnit.SECONDS.sleep or ScheduledExecutorService?
      Thread.sleep (1000); // prevents the application from using too much CPU
      System.out.println ("New frame!");
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
  
  
  
  private void update()
  {
    soundQueue_.remove().perform();
  }
  
  
  
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
