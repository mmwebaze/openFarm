package org.bashemera.openfarm.repository;

import org.bashemera.openfarm.model.Cow;
import org.springframework.transaction.annotation.Transactional;

public class CowRepositoryCustomImpl implements CowRepositoryCustom<Cow>{
	
	
	@Override
	public <S extends Cow> S save(S entity) {
		entity.setTagId("****ajfhs");
		System.out.println("HAHAHA");
		
		return entity;
	}

}
