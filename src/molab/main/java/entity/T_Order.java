package molab.main.java.entity;

@SuppressWarnings("serial")
public class T_Order extends BaseEntity {
	private long id;
	private int product_id;
	private int developer_id;
	private long time;
	private int state;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the product_id
	 */
	public int getProduct_id() {
		return product_id;
	}

	/**
	 * @param productId the product_id to set
	 */
	public void setProduct_id(int productId) {
		product_id = productId;
	}

	/**
	 * @return the developer_id
	 */
	public int getDeveloper_id() {
		return developer_id;
	}

	/**
	 * @param developerId the developer_id to set
	 */
	public void setDeveloper_id(int developerId) {
		developer_id = developerId;
	}

	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
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