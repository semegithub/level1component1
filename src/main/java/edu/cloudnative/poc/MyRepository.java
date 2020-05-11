package edu.cloudnative.poc;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MyRepository implements PanacheRepository<MyEntity2> {
	List<MyEntity2> findByLastName(String lastName) {
		return list("lastName", lastName);
	}
}
