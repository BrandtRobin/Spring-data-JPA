package se.meer.jpa.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import se.meer.jpa.superclass.AbstractEntity;

@Entity
@Table(name = "tblWorkItems")
public class WorkItem extends AbstractEntity {

	private Long workItemNumber;

	private Status status;

	@ManyToOne
	private User user;

	@OneToOne
	@JoinColumn
	private Issue issue;

	public WorkItem(Long workItemNumber) {
		this.workItemNumber = workItemNumber;
	}

	protected WorkItem() {
	}

	public Long getWorkItemNumber() {
		return workItemNumber;
	}

	public Status getStatus() {
		return status;
	}

	public Issue getIssue() {
		return issue;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
