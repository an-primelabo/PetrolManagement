package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.UserRequestDto;
import ah.petrolmanagement.dto.response.UserResponseDto;
import ah.petrolmanagement.exception.PetrolException;

public interface IUserLogic {
	List<UserResponseDto> select(final UserRequestDto dto)
			throws PetrolException;

	UserResponseDto save(final UserRequestDto dto) throws PetrolException;

	UserResponseDto update(final UserRequestDto dto) throws PetrolException;

	UserResponseDto delete(final UserRequestDto dto) throws PetrolException;
}