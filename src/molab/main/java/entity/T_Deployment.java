package molab.main.java.entity;

@SuppressWarnings("serial")
public class T_Deployment extends BaseEntity {
	private int id;
	private int application_id;
	private int websockify_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the application_id
	 */
	public int getApplication_id() {
		return application_id;
	}

	/**
	 * @param applicationId
	 *            the application_id to set
	 */
	public void setApplication_id(int applicationId) {
		application_id = applicationId;
	}

	/**
	 * @return the websockify_id
	 */
	public int getWebsockify_id() {
		return websockify_id;
	}

	/**
	 * @param websockifyId
	 *            the websockify_id to set
	 */
	public void setWebsockify_id(int websockifyId) {
		websockify_id = websockifyId;
	}

}