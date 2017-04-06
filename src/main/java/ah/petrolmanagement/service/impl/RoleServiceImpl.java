package ah.petrolmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.petrolmanagement.dto.request.RoleRequestDto;
import ah.petrolmanagement.dto.response.RoleResponseDto;
import ah.petrolmanagement.logic.IRoleLogic;
import ah.petrolmanagement.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleLogic logic;

	@Override
	public List<RoleResponseDto> select(RoleRequestDto request)
			throws Exception {
		return logic.select(request);
	}
}