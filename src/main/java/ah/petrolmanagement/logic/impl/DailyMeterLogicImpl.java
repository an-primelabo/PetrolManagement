package ah.petrolmanagement.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.dto.request.DailyMeterRequestDto;
import ah.petrolmanagement.dto.response.DailyMeterResponseDto;
import ah.petrolmanagement.entity.DailyMeterEntity;
import ah.petrolmanagement.enums.Shifts;
import ah.petrolmanagement.logic.IDailyMeterLogic;
import ah.petrolmanagement.persistence.IDailyMeterMapper;
import ah.petrolmanagement.utils.LogUtil;
import ah.petrolmanagement.utils.Utils;

@Component
public class DailyMeterLogicImpl implements IDailyMeterLogic {
	@Autowired
	private IDailyMeterMapper mapper;

	@Autowired
	private DataSourceTransactionManager transaction;

	@Override
	public List<DailyMeterResponseDto> select(final DailyMeterRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "select", request);

		Map<String, Object> map = setDataMap(request);
		List<Map<String, Object>> entities = mapper.select(map);
		return setData(entities);
	}

	@Override
	public DailyMeterResponseDto save(final DailyMeterRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "save", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		List<DailyMeterEntity> list = setDataEntityList(request);
		DailyMeterResponseDto response = new DailyMeterResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			mapper.save(list);
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
	public DailyMeterResponseDto update(final DailyMeterRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "update", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		DailyMeterEntity entity = setDataEntity(request);
		DailyMeterResponseDto response = new DailyMeterResponseDto();

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
	public DailyMeterResponseDto delete(final DailyMeterRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "delete", request);

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transaction.getTransaction(def);

		List<DailyMeterEntity> list = setDataEntityList(request);
		DailyMeterResponseDto response = new DailyMeterResponseDto();

		Object savePoint = status.createSavepoint();

		try {
			for (DailyMeterEntity entity : list) {
				mapper.delete(entity);
			}
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

	private Map<String, Object> setDataMap(DailyMeterRequestDto request) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (request.getShift() != null) {
			map.put(DailyMeterRequestDto.SHIFT, request.getShift());
		}
		if (request.getInsTime() != null) {
			map.put(DailyMeterRequestDto.INS_TIME, request.getInsTime());
		}
		if (request.getDateFrom() != null) {
			map.put(DailyMeterRequestDto.DATE_FROM, request.getDateFrom());
		}
		if (request.getDateTo() != null) {
			map.put(DailyMeterRequestDto.DATE_TO, request.getDateTo());
		}
		if (request.getMonthFrom() != null) {
			map.put(DailyMeterRequestDto.MONTH_FROM, request.getMonthFrom());
		}
		if (request.getMonthTo() != null) {
			map.put(DailyMeterRequestDto.MONTH_TO, request.getMonthTo());
		}
		return map;
	}

	private List<DailyMeterResponseDto> setData(
			List<Map<String, Object>> entities) {
		List<DailyMeterResponseDto> list = new ArrayList<DailyMeterResponseDto>();

		for (Map<String, Object> entity : entities) {
			DailyMeterResponseDto response = new DailyMeterResponseDto();
			response.setId(Utils.getInteger(entity.get(DailyMeterRequestDto.ID)));
			response.setTankId(Utils.getInteger(entity
					.get(DailyMeterRequestDto.TANK_ID)));
			response.setTankName(Utils.getString(entity
					.get(DailyMeterRequestDto.TANK_NAME)));
			response.setProductId(Utils.getInteger(entity
					.get(DailyMeterRequestDto.PRODUCT_ID)));
			response.setShift(Utils.getInteger(entity
					.get(DailyMeterRequestDto.SHIFT)));
			response.setShiftName(Shifts.getByCode(response.getShift()));
			response.setPriceId(Utils.getInteger(entity
					.get(DailyMeterRequestDto.PRICE_ID)));
			response.setPrice(Utils.getInteger(entity
					.get(DailyMeterRequestDto.PRICE)));
			response.setMeterNew(Utils.getFloat(entity
					.get(DailyMeterRequestDto.METER_NEW)));
			response.setMeterOld(Utils.getFloat(entity
					.get(DailyMeterRequestDto.METER_OLD)));
			response.setMeterElecNew(Utils.getFloat(entity
					.get(DailyMeterRequestDto.METER_ELEC_NEW)));
			response.setMeterElecOld(Utils.getFloat(entity
					.get(DailyMeterRequestDto.METER_ELEC_OLD)));
			response.setInsTime((Date) entity
					.get(DailyMeterRequestDto.INS_TIME));
			response.setInsUser(Utils.getString(entity
					.get(DailyMeterRequestDto.INS_USER)));
			response.setUpdTime((Date) entity
					.get(DailyMeterRequestDto.UPD_TIME));
			response.setUpdUser(Utils.getString(entity
					.get(DailyMeterRequestDto.UPD_USER)));
			response.setDelFlag(Utils.getInteger(entity
					.get(DailyMeterRequestDto.DEL_FLAG)));
			response.setDelTime((Date) entity
					.get(DailyMeterRequestDto.DEL_TIME));
			response.setDelUser(Utils.getString(entity
					.get(DailyMeterRequestDto.DEL_USER)));
			response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(response);
		}
		return list;
	}

	private List<DailyMeterEntity> setDataEntityList(DailyMeterRequestDto request) {
		List<DailyMeterEntity> list = new ArrayList<DailyMeterEntity>();

		for (DailyMeterRequestDto daily : request.getDailyList()) {
			DailyMeterEntity entity = new DailyMeterEntity();
			BeanUtils.copyProperties(daily, entity);

			if (StringUtils.equals(request.getMode(), ApiConstants.INSERT)) {
				entity.setInsUser(request.getInsUser());
			} else if (StringUtils.equals(request.getMode(), ApiConstants.UPDATE)) {
				entity.setUpdUser(request.getUpdUser());
			}
			list.add(entity);
		}
		return list;
	}

	private DailyMeterEntity setDataEntity(DailyMeterRequestDto request) {
		DailyMeterEntity entity = new DailyMeterEntity();
		BeanUtils.copyProperties(request, entity);
		return entity;
	}

	private DailyMeterResponseDto setDataResponse(DailyMeterRequestDto request) {
		DailyMeterResponseDto response = new DailyMeterResponseDto();
		BeanUtils.copyProperties(request, response);
		return response;
	}
}