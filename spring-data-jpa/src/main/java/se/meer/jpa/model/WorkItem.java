package se.meer.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Issue issue;

	@ManyToOne
	private Team team;

	public WorkItem(String title, String description, String status) {
		this.title = title;
		this.description = description;
		this.status = status;
	}

	protected WorkItem() {
	}

	public Issue getIssue() {
		return issue;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public void addUser(User user) {
		this.user = user;
	}

	public void addTeam(Team team) {
		this.team = team;
	}

}
