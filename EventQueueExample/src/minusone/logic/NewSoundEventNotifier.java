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
 * Instances of this class are used to lock the sound request event queue and
 * add their sound request to it.
 * 
 * @author Louis-Marie Matthews
 */
public class NewSoundEventNotifier
  implements Runnable
{
  private final SoundPlayingRequest soundPlayingRequest_;
  private final SoundRequestEventQueue soundRequestQueue_;
  
  
  
  public NewSoundEventNotifier (SoundPlayingRequest soundPlayingRequest,
                       SoundRequestEventQueue soundRequestQueue)
  {
      soundPlayingRequest_ = soundPlayingRequest;
      soundRequestQueue_ = soundRequestQueue;
  }



  @Override
  public void run()
  {
    soundRequestQueue_.add(soundPlayingRequest_);
    synchronized (soundRequestQueue_) {
      soundRequestQueue_.notify();
    }
  }
}
