package bd.edu.seu.ums.Helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverter {
    public static String convert24h(String inputTime) throws ParseException {
        //Date/time pattern of input date (12 Hours format - hh used for 12 hours)
        DateFormat inputFormat = new SimpleDateFormat("hh:mm aa");

        //Date/time pattern of desired output date (24 Hours format HH - Used for 24 hours)
        DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        String outputTime = null;

        //Returns Date object

        date = inputFormat.parse(inputTime);


        //old date format to new date format
        outputTime = outputformat.format(date);

        return outputTime;
    }

    public static String convert12h(String inputTime) throws ParseException {
        //Date/time pattern of desired output date (24 Hours format HH - Used for 24 hours)
        DateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");

        //Date/time pattern of input date (12 Hours format - hh used for 12 hours)
        DateFormat outputformat = new SimpleDateFormat("hh:mm aa");
        Date date = null;
        String outputTime = null;

        //Returns Date object
        date = inputFormat.parse(inputTime);

        //old date format to new date format
        outputTime = outputformat.format(date);

        return outputTime;
    }
}
