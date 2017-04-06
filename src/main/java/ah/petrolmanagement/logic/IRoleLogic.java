package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.RoleRequestDto;
import ah.petrolmanagement.dto.response.RoleResponseDto;

public interface IRoleLogic {
	List<RoleResponseDto> select(final RoleRequestDto request) throws Exception;
}