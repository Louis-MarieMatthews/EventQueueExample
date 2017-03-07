/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minusone.logic;

import minusone.gui.EventQueueExampleUi;

/**
 *
 * @author louis-mariematthews
 */
public class EventQueueExample
{
  public long fpsRate_;
  
  
  
  public EventQueueExample()
  {
    fpsRate_ = 60;
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
    }
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
