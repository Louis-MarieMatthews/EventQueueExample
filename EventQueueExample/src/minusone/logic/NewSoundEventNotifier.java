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
 *
 * @author Louis-Marie Matthews
 */
public class NewSoundEventNotifier
  implements Runnable
{
  private final SoundPlayingEvent soundPlayingEvent_;
  private final SoundRequestEventQueue soundQueue_;
  
  
  
  public NewSoundEventNotifier (SoundPlayingEvent soundPlayingEvent,
                       SoundRequestEventQueue soundQueue)
  {
      soundPlayingEvent_ = soundPlayingEvent;
      soundQueue_ = soundQueue;
  }



  @Override
  public void run()
  {
    soundQueue_.add(soundPlayingEvent_);
    synchronized (soundQueue_) {
      soundQueue_.notify();
    }
  }
}
