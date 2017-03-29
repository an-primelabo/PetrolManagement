package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.RoleRequestDto;
import ah.petrolmanagement.dto.response.RoleResponseDto;
import ah.petrolmanagement.exception.PetrolException;

public interface IRoleLogic {
	List<RoleResponseDto> select(final RoleRequestDto dto)
			throws PetrolException;
}