package se.meer.jpa.repository;

import se.meer.jpa.model.WorkItem;

public class WorkItemRepositoryImpl implements WorkItemCustomRepository{

	@Override
	public void logWorkItem(WorkItem workItem) {
		System.out.println("0 # Logging workItem: [" + workItem + "]");
	}

}
