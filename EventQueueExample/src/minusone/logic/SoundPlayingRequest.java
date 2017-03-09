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

/**
 * Represents a request for playing a sound. Instances are created by
 * any parts of the program and added to an event queue.
 * Their priority affects the order in which they are processed, the order in
 * which they have been added should have no impact on the order they will be
 * processed for any request not processed yet.
 * 
 * @author Louis-Marie Matthews
 */
public class SoundPlayingRequest
  implements Comparable<SoundPlayingRequest>
{
  private int priority_;
  private String soundFile_;
  private int volume_;
  
  
  
  /**
   * By implementing Comparable and overriding the compareTo method, events can
   * be sorted in a priority queue according to the priority they have been set
   * with. Thus if event A has been added after event B and if event A has a
   * higher priority than event B and event B has not been processed yet, event
   * A will be processed first.
   * @param t the SoundPlayingEvent object to compare the instance to
   * @return -1, 0 or 1 if the current instance is less tha, equal to or greater
   * than the parameter.
   */
  @Override
  public int compareTo(SoundPlayingRequest t)
  {
    int sgn;
    
    if (priority_ < t.getPriority()) {
      sgn = 1;
    }
    else if (priority_ > t.getPriority()) {
      sgn = -1;
    }
    else {
      sgn = 0;
    }
    
    return sgn;
  }
  
  
  
  public int getPriority()
  {
    return priority_;
  }
  
  
  
  public void setPriority (int priority)
  {
    priority_ = priority;
  }
  
  
  
  public SoundPlayingRequest (int priority, int volume, String soundFile)
  {
    priority_ = priority;
    volume_ = volume;
    soundFile_ = soundFile;
  }
  
  
  
  /**
   * This method processes the request. For the purpose of understanding, the
   * method is only printing a line to the console as the goal of the program is
   * only to understand how an event queue works and how it can be used. You can
   * imagine, or even implement it yourself if you want, that the method
   * actually plays the song according to its dB level and file name.
   * @throws java.lang.InterruptedException
   */
  public void perform()
    throws InterruptedException
  {
    System.out.println (soundFile_ + " is being played at a volume of " + volume_
                        + "dB...");
    System.out.println();
    for (int i = 0; i < 10; i++) {
        Thread.sleep (500);
        System.out.print(" * ");
    }
    System.out.println();
    System.out.println (soundFile_ + " has been played!");
  }
  
  
  
  public int getVolume()
  {
    return volume_;
  }
  
  
  
  public void setVolume (int volume)
  {
    if (volume <= 0) {
      throw new IllegalArgumentException();
    }
    volume_ = volume;
  }
  
  
  
  public String getSoundFile()
  {
    return soundFile_;
  }
  
  
  
  public void setSoundFile (String soundFile)
  {
    soundFile_ = soundFile;
  }
}
