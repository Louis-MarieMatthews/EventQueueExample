/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minusone.logic;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import minusone.gui.EventQueueExampleUi;

/**
 *
 * @author louis-mariematthews
 */
public class EventQueueExample
{
  private long fpsRate_;
  private PriorityQueue<EventQueueTask> taskQueue;
  
  
  
  public EventQueueExample()
  {
    fpsRate_ = 60;
    taskQueue = new PriorityQueue<EventQueueTask>();
  }
  
  
  
  public static void main(String[] args)
    throws InterruptedException
  {
    EventQueueExample eqe = new EventQueueExample();
    EventQueueExampleUi.createWindow(eqe);

    while (true) {
      // TODO: replace with TimeUnit.SECONDS.sleep or ScheduledExecutorService?
      Thread.sleep (1000 / eqe.getFpsRate());
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
    taskQueue.remove().perform();
  }
  
  
  
  public void addTask (EventQueueTask task)
  {
    taskQueue.add(task);
  }
  
  
  
  
  public long getFpsRate()
  {
    return fpsRate_;
  }
  
  
  
  public void setFpsRate (long fpsRate)
  {
    if (fpsRate <= 0) {
      throw new IllegalArgumentException();
    }
    fpsRate_ = fpsRate;
  }
}
