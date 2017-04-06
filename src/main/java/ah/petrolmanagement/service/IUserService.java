package ah.petrolmanagement.service;

import ah.petrolmanagement.dto.request.UserRequestDto;
import ah.petrolmanagement.dto.response.UserResponseDto;

public interface IUserService {
	public UserResponseDto login(final UserRequestDto request) throws Exception;

	public UserResponseDto save(final UserRequestDto request) throws Exception;

	public UserResponseDto update(final UserRequestDto request)
			throws Exception;

	public UserResponseDto delete(final UserRequestDto request)
			throws Exception;
}