package in.fssa.minimal.util;


public class Logger {
  
    public static void info(String msg) {
        System.out.println("INFO: " + msg);
    }

    public static void error(String msg) {
        System.out.println("ERROR: " + msg); 
    } 

    public static void error(Exception e) {
        e.printStackTrace();
    }
 
}

