/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

/**
 *
 * @author mike
 */
public class Timer extends Thread{
    private int HS = 0;
    private int sek = 0;
    private int min = 0;
    //private int h = 0;
    private boolean running = true;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run(){
      while(running){
        try{Thread.sleep(9);}catch(Exception e){}
        if(HS <= 99){
          HS++;
        } else  {
          HS = 0;
          if(sek <= 59){
            sek++;
          }else {
            sek = 0;
            if(min <= 59){
              min++;
            } else {
              min = 0;
              //h++;
            }
          }
        }
        String tmpTime = "";
        if(min < 10) {
        	tmpTime += "0" + min + ":";
        } else {
        	tmpTime += min  + ":";
        }
        if(sek < 10) {
        	tmpTime += "0" + sek  + ":";
        } else {
        	tmpTime += sek  + ":";
        }
        if(HS < 10) {
        	tmpTime += "0" + HS;
        } else {
        	tmpTime += HS;
        }
        setTime(tmpTime);
      }
    }

	public int getHS() {
		return HS;
	}

	public void setHS(int hS) {
		HS = hS;
	}

	public int getSek() {
		return sek;
	}

	public void setSek(int sek) {
		this.sek = sek;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}
  }
