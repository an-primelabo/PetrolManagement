package ah.petrolmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.petrolmanagement.dto.request.UserRequestDto;
import ah.petrolmanagement.dto.response.UserResponseDto;
import ah.petrolmanagement.logic.IUserLogic;
import ah.petrolmanagement.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserLogic logic;

	@Override
	public UserResponseDto login(UserRequestDto request) throws Exception {
		return logic.login(request);
	}

	@Override
	public UserResponseDto save(UserRequestDto request) throws Exception {
		return logic.save(request);
	}

	@Override
	public UserResponseDto update(UserRequestDto request) throws Exception {
		return logic.update(request);
	}

	@Override
	public UserResponseDto delete(UserRequestDto request) throws Exception {
		return logic.delete(request);
	}
}