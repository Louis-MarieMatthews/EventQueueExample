/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minusone.logic;

/**
 *
 * @author Louis-Marie Matthews
 */
public class SoundPlayingTask
  implements Comparable<SoundPlayingTask>
{
  private int priority_;
  private String soundFile_;
  private int volume_;
  
  
  @Override
  public int compareTo(SoundPlayingTask t)
  {
    int sgn;
    
    if (priority_ > t.getPriority()) {
      sgn = 1;
    }
    else if (priority_ < t.getPriority()) {
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
  
  
  
  public SoundPlayingTask (int priority, int volume, String soundFile)
  {
    priority_ = priority;
    volume_ = volume;
    soundFile_ = soundFile;
  }
  
  
  
  public void perform()
  {
    System.out.println (soundFile_ + "is being played at a volume of " + volume_
                        + "dB.");
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
