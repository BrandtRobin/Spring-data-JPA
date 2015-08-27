package se.meer.jpa.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import se.meer.jpa.superclass.AbstractEntity;

@Entity
@Table(name = "tblIssues")
public class Issue extends AbstractEntity {

	private String issueName;

	@OneToOne
	private WorkItem workItem;

	public Issue(String issueName, WorkItem workItem) {
		this.issueName = issueName;
		this.workItem = workItem;
	}

	protected Issue() {
	}

	public String getIssueName() {
		return issueName;
	}
	
	public WorkItem getWorkItem() {
		return workItem;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
