package workflow.db;

// Generated 2014-5-16 13:16:00 by Hibernate Tools 3.4.0.CR1

/**
 * Userwokflow generated by hbm2java
 */
public class Userwokflow implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3466069703558594355L;
	private Long id;
	private long userId;
	private long wfId;

	public Userwokflow() {
	}

	public Userwokflow(long userId, long wfId) {
		this.userId = userId;
		this.wfId = wfId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getWfId() {
		return this.wfId;
	}

	public void setWfId(long wfId) {
		this.wfId = wfId;
	}

}