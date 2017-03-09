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
import java.util.PriorityQueue;

/**
 * Objects of this class are used for storing sound requests waiting to be
 * processed. The sound requests will be automatically ordered by their
 * priority, and duplicate sound requests will be merged in one sound request
 * with a higher volume.
 * 
 * @author Louis-Marie Matthews
 */
public class SoundRequestEventQueue
{
  private PriorityQueue<SoundPlayingEvent> queue_;
  
  
  
  public SoundRequestEventQueue()
  {
    queue_ = new PriorityQueue<>();
  }
  
  
  
  public boolean isEmpty()
  {
    return queue_.isEmpty();
  }
  
  
  
  /**
   * When called, this method processes the next event in the priority queue.
   * This event is the one with the highest priority in the queue, because
   * events are sorted according to their priority when added.
   * 
   * @throws java.lang.InterruptedException
   */
  public void update()
    throws InterruptedException
  {
    queue_.remove().perform();
  }
  
  
  
  /**
   * This method adds an event to the queue. If another event with the same
   * filename is already in the event, it doesn't add the event to the queue but
   * increase the decibel level of the already present event.
   * 
   * @param eventToBeAdded the event to be added to the queue
   */
  public synchronized void add (SoundPlayingEvent eventToBeAdded)
  {
    SoundPlayingEvent duplicateInQueue = getDuplicateInQueue (eventToBeAdded);
    
    if (duplicateInQueue != null) {
      // Slightly increases the volume of the already existing sound request but
      // does not add duplicate.
      int newVolume = (int) (duplicateInQueue.getVolume() +
                             eventToBeAdded.getVolume() / 10);
      duplicateInQueue.setVolume (newVolume);
    }
    else {
      queue_.add(eventToBeAdded);
    }
  }
  
  
  
  public SoundPlayingEvent peek()
  {
    return queue_.peek();
  }
  
  
  
  private SoundPlayingEvent getDuplicateInQueue(SoundPlayingEvent soundRequest)
  {
    SoundPlayingEvent duplicateInQueue = null;
    
    Iterator<SoundPlayingEvent> i = queue_.iterator();
    boolean duplicateNotFoundYet = true;
    
    while (i.hasNext() && duplicateNotFoundYet) {
      SoundPlayingEvent currentEvent = i.next();
      if (soundRequest.getSoundFile().equals (currentEvent.getSoundFile())) {
        duplicateInQueue = currentEvent;
        duplicateNotFoundYet = false;
      }
    }
    
    return duplicateInQueue;
  }
}
