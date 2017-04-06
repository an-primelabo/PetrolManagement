package ah.petrolmanagement.logic;

import ah.petrolmanagement.dto.request.UserRequestDto;
import ah.petrolmanagement.dto.response.UserResponseDto;

public interface IUserLogic {
	UserResponseDto login(final UserRequestDto request) throws Exception;

	UserResponseDto save(final UserRequestDto request) throws Exception;

	UserResponseDto update(final UserRequestDto request) throws Exception;

	UserResponseDto delete(final UserRequestDto request) throws Exception;
}