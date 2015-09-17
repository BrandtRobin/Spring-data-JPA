package se.meer.jpa.model;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {

	@Id
	@GeneratedValue
	private long id;
	
	@CreatedDate
	@Convert(converter = LocalDateConverter.class)
	private LocalDate created;
	

	public Long getId() {
		return id;
	}

	protected AbstractEntity() {
	}

}
