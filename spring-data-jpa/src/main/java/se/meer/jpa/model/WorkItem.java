package se.meer.jpa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import se.meer.jpa.superclass.AbstractEntity;

@Entity
@Table(name = "tblWorkItems")
public class WorkItem extends AbstractEntity {

	private String itemName;
	
	@ManyToOne
	private User user;
	
	@OneToOne
	private Issue issue;
	
	public WorkItem(String itemName, User user, Issue issue) {
		this.itemName = itemName;
		this.user = user;
		this.issue = issue;
	}

	protected WorkItem() {
	}

	public String getItemName() {
		return itemName;
	}

	public User getUser() {
		return user;
	}
	
	public Issue getIssue() {
		return issue;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
