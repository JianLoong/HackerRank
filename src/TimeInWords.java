import java.util.Scanner;


public class TimeInWords {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner myScan = new Scanner(System.in);
        int hour = myScan.nextInt();
        int minute = myScan.nextInt();

        String[] minutesTxt = {"o' clock", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five", "twenty six", "twenty seven", "twenty eight", "twenty nine", "half"};

        String[] hoursTxt = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve"};
        String result = "";
        if (minute == 0 || minute == 60) {
            result = hoursTxt[hour] + " " + minutesTxt[minute];
        } else if (minute == 1) {
            result = minutesTxt[minute] + " minute past " + hoursTxt[hour];
        } else if(minute==15){
            result = "quarter "+ "past " + hoursTxt[hour];
        }else if (minute < 30) {//less than 30
            result = minutesTxt[minute] + " minutes past " + hoursTxt[hour];
        } else if (minute == 30) {
            result = "half past " + hoursTxt[hour];
        } else if (minute == 45) {
            result = "quarter to " + hoursTxt[hour + 1];
        } else {
            if ((60 - minute) == 1) {
                result = "one minute to " + hoursTxt[hour + 1];
            } else {
                if (hour == 12) {
                    result = minutesTxt[60 - minute] + " minutes to one";
                } else {
                    result = minutesTxt[60 - minute] + " minutes to " + hoursTxt[hour + 1];
                }

            }

        }

        System.out.println(result);

    }

}