public class Metingen {

    private int stn;
    private String date;
    private String time;


    public int getStn() {
        return stn;
    }

    public void setStn(int stn) {
        this.stn = stn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return stn+","+date+","+time;
    }
}
