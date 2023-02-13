package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    public long buildDate(String endDate) throws ParseException {
        String startDate = "2023-01-01T18:00:00.000-03:00";

        Date dateInicial = getDate(startDate);
        Date dateFinal = getDate(endDate);

        long longFinal = dateFinal.getTime();
        long longInicial = dateInicial.getTime();

        long result = longFinal - longInicial;
        return TimeUnit.MILLISECONDS.toMinutes(result);
    }

    private Date getDate(String tempDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date newDate = sdf.parse(tempDate);
        Calendar c = Calendar.getInstance();
        c.setTime(newDate);
        c = backToSameDay(c);
        newDate = c.getTime();
        return newDate;
    }

    private Calendar backToSameDay(Calendar c) {
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, 1);
        c.set(Calendar.YEAR, 2023);
        return c;
    }
}
