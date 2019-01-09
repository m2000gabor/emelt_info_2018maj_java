/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netbeansfirsttestproject;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author test
 */
 
public class Ember
{
  private final int hour;
  private final int min;
  private final int id;
  private final String direction;
  private final Time ido;
   
  public Ember(int ora,int perc, int azon, String irany)
  { hour= ora;
    min = perc;
    id = azon;
    direction = irany;
    ido = new Time(TimeUnit.MINUTES.toMillis(min) + TimeUnit.HOURS.toMillis(hour)-3600000); 
  }
  
  public int getHour(){return hour;}
  public int getMin(){return min;}
  public int getId(){return id;}
  public String getDirection(){return direction;}
  public Time getIdo(){return ido;}
  public boolean getDirBoolean(){return direction.equals("be");}
}