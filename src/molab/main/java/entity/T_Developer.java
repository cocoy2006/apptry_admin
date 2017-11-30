package molab.main.java.entity;

@SuppressWarnings("serial")
public class T_Developer extends BaseEntity {
	private int id;
	private String username;
	private String password;
	private String email;
	private int leftClicks;
	private long registerTime;
	private long expiration;
	private int isRenewal;
	private int state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the leftClicks
	 */
	public int getLeftClicks() {
		return leftClicks;
	}

	/**
	 * @param leftClicks the leftClicks to set
	 */
	public void setLeftClicks(int leftClicks) {
		this.leftClicks = leftClicks;
	}

	/**
	 * @return the registerTime
	 */
	public long getRegisterTime() {
		return registerTime;
	}

	/**
	 * @param registerTime the registerTime to set
	 */
	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}
	
	/**
	 * @return the expiration
	 */
	public long getExpiration() {
		return expiration;
	}

	/**
	 * @param expiration the expiration to set
	 */
	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}

	/**
	 * @return the isRenewal
	 */
	public int getIsRenewal() {
		return isRenewal;
	}

	/**
	 * @param isRenewal the isRenewal to set
	 */
	public void setIsRenewal(int isRenewal) {
		this.isRenewal = isRenewal;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	
}