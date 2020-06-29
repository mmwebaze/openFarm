package org.bashemera.openfarm.repository;


public interface CowRepositoryCustom<T>{
	<S extends T> S save(S entity);
}
