package se.meer.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblWorkItems")
public class WorkItem extends AbstractEntity {

	private String title;
	private String description;
	private String status;

	@ManyToOne
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	private Issue issue;

	public WorkItem(String title, String description, String status, User user, Issue issue) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.user = user;
		this.issue = issue;
	}

	protected WorkItem() {
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	public Issue getIssues() {
		return issue;
	}

	public User getUser() {
		return user;
	}

}
