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
import org.springframework.stereotype.Component;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.dto.request.RoleRequestDto;
import ah.petrolmanagement.dto.request.TankRequestDto;
import ah.petrolmanagement.dto.response.RoleResponseDto;
import ah.petrolmanagement.entity.RoleEntity;
import ah.petrolmanagement.exception.PetrolException;
import ah.petrolmanagement.logic.CommonLogic;
import ah.petrolmanagement.logic.IRoleLogic;
import ah.petrolmanagement.persistence.IRoleMapper;

@Component
public class RoleLogicImpl extends CommonLogic implements IRoleLogic {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IRoleMapper mapper;

	@Override
	public List<RoleResponseDto> select(RoleRequestDto dto)
			throws PetrolException {
		logger.info("select : {}", dto);

		Map<String, Object> map = setDataMap(dto);
		List<RoleEntity> entities = mapper.select(map);
		List<RoleResponseDto> list = setData(entities);
		return list;
	}

	private Map<String, Object> setDataMap(RoleRequestDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (dto.getId() != null) {
			map.put(TankRequestDto.ID, dto.getId());
		}
		if (StringUtils.isNotBlank(dto.getUsername())) {
			map.put(RoleRequestDto.USERNAME, dto.getUsername());
		}
		if (StringUtils.isNotBlank(dto.getRole())) {
			map.put(RoleRequestDto.ROLE, dto.getRole());
		}
		return map;
	}

	private List<RoleResponseDto> setData(List<RoleEntity> entities) {
		List<RoleResponseDto> list = new ArrayList<RoleResponseDto>();

		for (RoleEntity entity : entities) {
			RoleResponseDto dto = new RoleResponseDto();
			BeanUtils.copyProperties(entity, dto);
			dto.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(dto);
		}
		return list;
	}
}