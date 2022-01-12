import java.time.Clock;

class Timer extends Thread{
    private int HS = 0;
    private int sek = 0;
    private int min = 0;
    private int h = 0;

    private boolean running;
    private int extraTime;

    public Timer(int time, int extraTime) {
        
    }
    public void run(){
        Clock clock = new Clock();
        player1.setNewClock(clock);
        player2.getClock(); //Hier wird bei der naiven Umsetzung immer noch clock ausgegeben, und nicht newClock
        
        void setNewClock(Clock clock){
         clock=new Clock(); //player2 bekommt dieses neue Clock nicht implizit mit, er hat immer noch die alte Uhr (du kannst die Referenz so nicht ändern)
        }
        void setNewTime(Clock clock){
         clock.setTime(300); //Das Attribut time von clock kannst du verändern, dann sieht es auch jeder andere
        }
    }
}