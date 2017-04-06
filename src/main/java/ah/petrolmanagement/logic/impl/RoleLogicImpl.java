package ah.petrolmanagement.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ah.petrolmanagement.constants.ApiConstants;
import ah.petrolmanagement.dto.request.RoleRequestDto;
import ah.petrolmanagement.dto.request.TankRequestDto;
import ah.petrolmanagement.dto.response.RoleResponseDto;
import ah.petrolmanagement.entity.RoleEntity;
import ah.petrolmanagement.logic.IRoleLogic;
import ah.petrolmanagement.persistence.IRoleMapper;
import ah.petrolmanagement.utils.LogUtil;

@Component
public class RoleLogicImpl implements IRoleLogic {
	@Autowired
	private IRoleMapper mapper;

	@Override
	public List<RoleResponseDto> select(RoleRequestDto request)
			throws Exception {
		LogUtil.startMethod(this.getClass().getSimpleName(), "select", request);

		Map<String, Object> map = setDataMap(request);
		List<RoleEntity> entities = mapper.select(map);
		List<RoleResponseDto> list = setData(entities);
		return list;
	}

	private Map<String, Object> setDataMap(RoleRequestDto request) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (request.getId() != null) {
			map.put(TankRequestDto.ID, request.getId());
		}
		if (StringUtils.isNotBlank(request.getUsername())) {
			map.put(RoleRequestDto.USERNAME, request.getUsername());
		}
		if (StringUtils.isNotBlank(request.getRole())) {
			map.put(RoleRequestDto.ROLE, request.getRole());
		}
		return map;
	}

	private List<RoleResponseDto> setData(List<RoleEntity> entities) {
		List<RoleResponseDto> list = new ArrayList<RoleResponseDto>();

		for (RoleEntity entity : entities) {
			RoleResponseDto response = new RoleResponseDto();
			BeanUtils.copyProperties(entity, response);
			response.setStatus(ApiConstants.STATUS_CODE_SUCCESS);

			list.add(response);
		}
		return list;
	}
}