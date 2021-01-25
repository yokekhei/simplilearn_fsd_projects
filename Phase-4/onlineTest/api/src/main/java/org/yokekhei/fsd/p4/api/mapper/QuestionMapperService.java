package org.yokekhei.fsd.p4.api.mapper;

import org.mapstruct.Mapper;

@Mapper
public interface QuestionMapperService {

	static org.yokekhei.fsd.p4.api.entity.Question findById(Long id) {
		if (id == null) {
			return null;
		}
		
		org.yokekhei.fsd.p4.api.entity.Question question = new org.yokekhei.fsd.p4.api.entity.Question();
		question.setId(id);
		
		return question;
	}
	
}

