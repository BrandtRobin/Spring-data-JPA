package se.meer.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import se.meer.jpa.superclass.AbstractEntity;

@Entity
@Table(name = "tblIssues")
public class Issue extends AbstractEntity {


	private Long issueNumber;

	private String issueNote;

	public Issue(Long issueNumber, String issueNote) {
		this.issueNumber = issueNumber;
		this.issueNote = issueNote;
	}

	protected Issue() {
	}

	public Long getIssueNumber() {
		return issueNumber;
	}

	public String getIssueNote() {
		return issueNote;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
