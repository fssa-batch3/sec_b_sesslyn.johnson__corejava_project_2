package in.fssa.minimal.util;


public class Logger {
  
	/**
     * Log an informational message.
     *
     * @param msg The informational message to be logged.
     */
    public static void info(String msg) {
        System.out.println("INFO: " + msg); 
    }
 
    /**
     * Log an error message.
     *
     * @param msg The error message to be logged.
     */
    public static void error(String msg) {
        System.out.println("ERROR: " + msg); 
    } 

    /**
     * Log an exception and its stack trace.
     *
     * @param e The exception to be logged.
     */
    public static void error(Exception e) {
        e.printStackTrace();
    } 
}





