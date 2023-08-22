package A2;

public class Time extends GraphM{

    public int startTime, endTime;

    public int threshold;

    public Time(){

    }

    public boolean setTimeWindow(int startTime, int endTime) {
        if(endTime>startTime){
            this.startTime = startTime;
            this.endTime = endTime;
            return true;
        }
        return false;
    }

    public void clearTimeWindow(){
        this.startTime = 0;
        this.endTime = 0;
    }
}