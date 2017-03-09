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
  private PriorityQueue<SoundPlayingRequest> queue_;
  
  
  
  public SoundRequestEventQueue()
  {
    queue_ = new PriorityQueue<>();
  }
  
  
  
  public boolean isEmpty()
  {
    return queue_.isEmpty();
  }
  
  
  
  /**
   * When called, this method processes the next request in the event queue.
   * This request is the one with the highest priority in the queue, because
   * requests are sorted according to their priority when added.
   * 
   * @throws java.lang.InterruptedException
   */
  public void update()
    throws InterruptedException
  {
    queue_.remove().perform();
  }
  
  
  
  /**
   * This method adds an request to the queue. If another request with the same
   * filename is already in the event, it doesn't add the request to the queue
   * but increase the decibel level of the already present request.
   * 
   * @param eventToBeAdded the event to be added to the queue
   */
  public synchronized void add (SoundPlayingRequest requestToBeAdded)
  {
    SoundPlayingRequest duplicateInQueue = getDuplicateInQueue (requestToBeAdded);
    
    if (duplicateInQueue != null) {
      // Slightly increases the volume of the already existing sound request but
      // does not add duplicate.
      int newVolume = (int) (duplicateInQueue.getVolume() +
                             requestToBeAdded.getVolume() / 10);
      duplicateInQueue.setVolume (newVolume);
    }
    else {
      queue_.add(requestToBeAdded);
    }
  }
  
  
  
  public SoundPlayingRequest peek()
  {
    return queue_.peek();
  }
  
  
  
  private SoundPlayingRequest getDuplicateInQueue(SoundPlayingRequest
                                                  soundRequest)
  {
    SoundPlayingRequest duplicateInQueue = null;
    
    Iterator<SoundPlayingRequest> i = queue_.iterator();
    boolean duplicateNotFoundYet = true;
    
    while (i.hasNext() && duplicateNotFoundYet) {
      SoundPlayingRequest currentEvent = i.next();
      if (soundRequest.getSoundFile().equals (currentEvent.getSoundFile())) {
        duplicateInQueue = currentEvent;
        duplicateNotFoundYet = false;
      }
    }
    
    return duplicateInQueue;
  }
}
