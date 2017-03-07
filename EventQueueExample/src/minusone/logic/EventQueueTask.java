/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minusone.logic;

/**
 *
 * @author Louis-Marie Matthews
 * TODO: interface?
 */
public abstract class EventQueueTask
  implements Comparable<EventQueueTask>
{
  private int priority_;
  
  
  
  public EventQueueTask(int priority)
  {
    priority_ = priority;
  }
  
  
  
  @Override
  public int compareTo(EventQueueTask t)
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
  
  
  
  public abstract void perform();
  
  
  
  public int getPriority()
  {
    return priority_;
  }
  
  
  
  public void setPriority (int priority)
  {
    priority_ = priority;
  }
}
