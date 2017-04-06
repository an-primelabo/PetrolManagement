package ah.petrolmanagement.logic.impl;

import java.util.Date;
import java.util.Map;

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
import ah.petrolmanagement.logic.IUserLogic;
import ah.petrolmanagement.persistence.IUserMapper;
import ah.petrolmanagement.utils.LogUtil;
import ah.petrolmanagement.utils.Utils;

@Component
public class UserLogicImpl implements IUserLogic {
	@Autowired
	private IUserMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public UserResponseDto login(final UserRequestDto request) throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "login", request);

		UserResponseDto response = new UserResponseDto();
		Map<String, Object> entity = mapper.login(request.getUsername());

		if (entity != null) {
			if (Utils.checkPassword(request.getPassword(), entity.get(UserRequestDto.PASSWORD).toString())) {
				response.setUsername(Utils.getString(entity.get(UserRequestDto.USERNAME)));
				response.setPassword(Utils.getString(entity.get(UserRequestDto.PASSWORD)));
				response.setFirstname(Utils.getString(entity.get(UserRequestDto.FIRSTNAME)));
				response.setLastname(Utils.getString(entity.get(UserRequestDto.LASTNAME)));
				response.setBirthday((Date) entity.get(UserRequestDto.BIRTHDAY));
				response.setPhone(Utils.getString(entity.get(UserRequestDto.PHONE)));
				response.setRole(Utils.getString(entity.get(UserRequestDto.ROLE)));
				response.setInsTime((Date) entity.get(UserRequestDto.INS_TIME));
				response.setInsUser(Utils.getString(entity.get(UserRequestDto.INS_USER)));
				response.setUpdTime((Date) entity.get(UserRequestDto.UPD_TIME));
				response.setUpdUser(Utils.getString(entity.get(UserRequestDto.UPD_USER)));
				response.setDelFlag(Utils.getInteger(entity.get(UserRequestDto.DEL_FLAG)));
				response.setDelTime((Date) entity.get(UserRequestDto.DEL_TIME));
				response.setDelUser(Utils.getString(entity.get(UserRequestDto.DEL_USER)));
				response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
			} else {
				response.setResultCode("PASSWORD NOT MATCH");
				response.setStatus(ApiConstants.STATUS_CODE_ERROR);
			}
		} else {
			response.setStatus(ApiConstants.STATUS_CODE_ERROR);
		}
		return response;
	}

	@Override
	public UserResponseDto save(final UserRequestDto request) throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "save", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		UserEntity entity = setDataEntity(request);
		UserResponseDto response = new UserResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.save(entity);
		} catch (Exception e) {
			LogUtil.errorLog(this.getClass().getSimpleName(), "save error", e);

			status.releaseSavepoint(savePoint);
			transaction.rollback(status);

			response = setDataResponse(request);

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
	public UserResponseDto update(final UserRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "update", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		UserEntity entity = setDataEntity(request);
		UserResponseDto response = new UserResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.update(entity);
		} catch (Exception e) {
			LogUtil.errorLog(this.getClass().getSimpleName(), "update error", e);

			status.releaseSavepoint(savePoint);
			transaction.rollback(status);

			response = setDataResponse(request);
			response.setErrorsList(new String[] { ApiConstants.ERR_SYSTEM });
			response.setStatus(ApiConstants.STATUS_CODE_ERROR);
			return response;
		}
		transaction.commit(status);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	@Override
	public UserResponseDto delete(final UserRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "delete", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		UserEntity entity = setDataEntity(request);
		UserResponseDto response = new UserResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.delete(entity);
		} catch (Exception e) {
			LogUtil.errorLog(this.getClass().getSimpleName(), "delete error", e);

			status.releaseSavepoint(savePoint);
			transaction.rollback(status);

			response = setDataResponse(request);
			response.setErrorsList(new String[] { ApiConstants.ERR_SYSTEM });
			response.setStatus(ApiConstants.STATUS_CODE_ERROR);
			return response;
		}
		transaction.commit(status);
		response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);
		return response;
	}

	private UserEntity setDataEntity(UserRequestDto request) {
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(request, entity);
		return entity;
	}

	private UserResponseDto setDataResponse(UserRequestDto request) {
		UserResponseDto response = new UserResponseDto();
		BeanUtils.copyProperties(request, response);
		return response;
	}
}