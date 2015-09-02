package se.meer.jpa.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblIssues")
public class Issue extends AbstractEntity {

	private String title;
	private String description;

	@OneToOne(mappedBy = "issue")
	private WorkItem workItem;

	public Issue(String title, String description) {
		this.title = title;
		this.description = description;
	}

	protected Issue() {
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public WorkItem getWorkItem() {
		return workItem;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setWorkItem(WorkItem workItem) {
		this.workItem = workItem;
	}

}
