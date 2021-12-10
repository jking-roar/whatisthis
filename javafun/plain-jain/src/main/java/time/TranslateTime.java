package time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class TranslateTime {
    public static void main(String[] args) throws IOException {
        DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm zzz");
        DateTime utctime = new DateTime(DateTimeZone.UTC).withHourOfDay(20);
        System.out.println(utctime.withZone(DateTimeZone.forID("America/New_York")));
    }
}
