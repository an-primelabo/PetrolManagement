package ah.petrolmanagement.service;

import java.util.List;

import ah.petrolmanagement.dto.request.RoleRequestDto;
import ah.petrolmanagement.dto.response.RoleResponseDto;

public interface IRoleService {
	public List<RoleResponseDto> select(final RoleRequestDto request)
			throws Exception;
}