/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minusone.logic.task;

import minusone.logic.Task;

/**
 *
 * @author Louis-Marie Matthews
 */
public class SoundPlayingTask extends Task
{
  private String soundFile_;
  private int volume_;
  
  
  
  public SoundPlayingTask (int priority, int volume, String soundFile)
  {
    super(priority);
    volume_ = volume;
    soundFile_ = soundFile;
  }
  
  
  
  @Override
  public void perform()
  {
    System.out.println (soundFile_ + "is being played at a volume of " + volume_ + "dB.");
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
