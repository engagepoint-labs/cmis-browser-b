package simplejsf.core;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log
{
    private static final Logger logger = Logger.getLogger("MYLOG");

    public static void println(String msg)
    {
//        logger.log(Level.INFO, "    " + msg + "    ");
        System.out.println(msg);
    }
}
