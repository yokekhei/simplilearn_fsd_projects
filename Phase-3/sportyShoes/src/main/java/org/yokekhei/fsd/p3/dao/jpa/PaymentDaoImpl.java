package org.yokekhei.fsd.p3.dao.jpa;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yokekhei.fsd.p3.dao.PaymentDao;
import org.yokekhei.fsd.p3.dao.SportyShoesDaoException;
import org.yokekhei.fsd.p3.dto.Payment;

@Repository
@Qualifier("jpa")
public class PaymentDaoImpl implements PaymentDao {

	@Resource
	PaymentRepository repository;
	
	@Resource
	PaymentMapper mapper;
	
	@Resource
	UserMapper userMapper;
	
	@Override
	@Transactional
	public Payment save(Payment payment) throws SportyShoesDaoException {
		Payment savedPayment = null;
		
		try {
			org.yokekhei.fsd.p3.entity.Payment entity = mapper.toEntity(payment);
			entity.getPurchaseOrder().setUser(userMapper.toEntity(
					payment.getPurchaseOrder().getUser()));
			savedPayment = mapper.toDto(repository.save(entity));
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return savedPayment;
	}

	@Override
	public Payment getPayment(Long id) throws SportyShoesDaoException {
		Payment payment = null;
		
		try {
			org.yokekhei.fsd.p3.entity.Payment entity = repository.findById(id)
					.orElse(null);
			
			if (entity != null) {
				payment = mapper.toDto(entity);
				payment.getPurchaseOrder().setUser(userMapper.toDto(
						entity.getPurchaseOrder().getUser()));
			}
		} catch (Exception e) {
			throw new SportyShoesDaoException(e.getMessage());
		}
		
		return payment;
	}

}
