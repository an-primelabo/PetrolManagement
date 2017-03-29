package ah.petrolmanagement.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.dto.request.UserRequestDto;
import ah.petrolmanagement.dto.response.UserResponseDto;
import ah.petrolmanagement.entity.UserEntity;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.logic.CommonLogic;
import ah.petrolmanagement.logic.IUserLogic;
import ah.petrolmanagement.persistence.IUserMapper;

@Component
public class UserLogicImpl extends CommonLogic implements IUserLogic {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<UserResponseDto> select(UserRequestDto dto)
			throws PetrolException {
		logger.info("select : {}", dto);

		Map<String, Object> map = setDataMap(dto);
		List<UserEntity> entities = mapper.select(map);
		List<UserResponseDto> list = setData(entities);
		return list;
	}

	@Override
	public UserResponseDto save(UserRequestDto dto)
			throws PetrolException {
		logger.info("save : {}", dto);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		UserEntity entity = setDataEntity(dto);
		UserResponseDto response = new UserResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.save(entity);
		} catch (Exception e) {
			logger.error("save error : {}", e);

			status.releaseSavepoint(savePoint);
			transaction.rollback(status);

			response = setDataResponse(dto);

			if (e instanceof DuplicateKeyException) {
				response.setErrorsList(new String[] { ApiConstants.ERR_ITEM_DUPLICATE });
			} else {
				response.setErrorsList(new String[] { ApiConstants.ERR_SYSTEM });
			}
			response.setStatus(ApiConstants.STATUS_CODE_ERROR);
			return response;
		}
		transaction.commit(status);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	@Override
	public UserResponseDto update(UserRequestDto dto)
			throws PetrolException {
		logger.info("update : {}", dto);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		UserEntity entity = setDataEntity(dto);
		UserResponseDto response = new UserResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.update(entity);
		} catch (Exception e) {
			logger.error("update error : {}", e);

			status.releaseSavepoint(savePoint);
			transaction.rollback(status);

			response = setDataResponse(dto);
			response.setErrorsList(new String[] { ApiConstants.ERR_SYSTEM });
			response.setStatus(ApiConstants.STATUS_CODE_ERROR);
			return response;
		}
		transaction.commit(status);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	@Override
	public UserResponseDto delete(UserRequestDto dto)
			throws PetrolException {
		logger.info("delete : {}", dto);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		UserEntity entity = setDataEntity(dto);
		UserResponseDto response = new UserResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.delete(entity);
		} catch (Exception e) {
			logger.error("delete error : {}", e);

			status.releaseSavepoint(savePoint);
			transaction.rollback(status);

			response = setDataResponse(dto);
			response.setErrorsList(new String[] { ApiConstants.ERR_SYSTEM });
			response.setStatus(ApiConstants.STATUS_CODE_ERROR);
			return response;
		}
		transaction.commit(status);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	private Map<String, Object> setDataMap(UserRequestDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(dto.getUsername())) {
			map.put(UserRequestDto.USERNAME, dto.getUsername());
		}
		return map;
	}

	private UserEntity setDataEntity(UserRequestDto dto) {
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	private UserResponseDto setDataResponse(UserRequestDto dto) {
		UserResponseDto response = new UserResponseDto();
		BeanUtils.copyProperties(dto, response);
		return response;
	}

	private List<UserResponseDto> setData(List<UserEntity> entities) {
		List<UserResponseDto> list = new ArrayList<UserResponseDto>();

		for (UserEntity entity : entities) {
			UserResponseDto dto = new UserResponseDto();
			BeanUtils.copyProperties(entity, dto);
			dto.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(dto);
		}
		return list;
	}
}