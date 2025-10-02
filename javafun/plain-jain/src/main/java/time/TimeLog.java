package time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class TimeLog {
    public static void main(String[] args) throws IOException {
        DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm zzz");
        DateTime eastern = new DateTime(DateTimeZone.forID("America/New_York"))
                .withMillisOfDay(0)
                .withHourOfDay(17);
        DateTime pacific = eastern.withZone(DateTimeZone.forID("America/Los_Angeles"));
        DateTime utc = eastern.withZone(DateTimeZone.UTC);

        String pString = format.print(pacific);
        String eString = format.print(eastern);
        String utcString = format.print(utc);
        /* this needs java language level to be 8*/
        String timelog = String.join(" / ", pString, eString, utcString);
        System.out.println(timelog);
    }
}
