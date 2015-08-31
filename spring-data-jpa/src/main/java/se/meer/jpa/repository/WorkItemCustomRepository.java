package se.meer.jpa.repository;

import se.meer.jpa.model.WorkItem;

public interface WorkItemCustomRepository {
	void logWorkItem(WorkItem workItem);

}
