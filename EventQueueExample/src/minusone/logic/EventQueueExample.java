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
  private PriorityQueue<Task> taskQueue;
  
  
  
  public EventQueueExample()
  {
    taskQueue = new PriorityQueue<Task>();
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
    taskQueue.remove().perform();
  }
  
  
  
  public void addTask (Task task)
  {
    taskQueue.add(task);
  }
}
