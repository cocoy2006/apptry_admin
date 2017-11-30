package molab.main.java.util;

public class Status {
	
	/**
	 * common states*/
	public static final int SUCCESS = 1;
	public static final int ERROR = -1;
	public static final int ERROR_SESSION_ATTR_LOST = 97;
	public static final int ERROR_SESSION_LOST = 98;
	public static final int ERROR_SYSTEM = 99;
	
	/**
	 * websockify states*/
	public static final int IDLE = 1;
	public static final int BUSY = 2;
	public static final int OTHER = 9;
	
	/**
	 * for order page
	 * */
	public static final int ORDER_WAITING = 0;
	public static final int ORDER_SUCCESS = 1;
	public static final int ORDER_FAILURE = 2;
	public static final int ORDER_CANCEL = 3;
}