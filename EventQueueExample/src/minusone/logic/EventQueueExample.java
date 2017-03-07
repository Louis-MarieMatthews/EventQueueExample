/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minusone.logic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import minusone.gui.EventQueueExampleUi;
import minusone.logic.SoundPlayingTask;

/**
 *
 * @author louis-mariematthews
 */
public class EventQueueExample
{
  private PriorityQueue<SoundPlayingTask> soundQueue_;
  
  
  
  public EventQueueExample()
  {
    soundQueue_ = new PriorityQueue<SoundPlayingTask>();
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
  
  
  
  public void addTask (SoundPlayingTask task)
  {
    soundQueue_.add(task);
  }
}
