package ah.petrolmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.petrolmanagement.dto.request.TankRequestDto;
import ah.petrolmanagement.dto.response.TankResponseDto;
import ah.petrolmanagement.logic.ITankLogic;
import ah.petrolmanagement.service.ITankService;

@Service("tankService")
public class TankServiceImpl implements ITankService {
	@Autowired
	private ITankLogic logic;

	@Override
	public List<TankResponseDto> select(TankRequestDto request)
			throws Exception {
		return logic.select(request);
	}

	@Override
	public TankResponseDto save(TankRequestDto request) throws Exception {
		return logic.save(request);
	}

	@Override
	public TankResponseDto update(TankRequestDto request) throws Exception {
		return logic.update(request);
	}

	@Override
	public TankResponseDto delete(TankRequestDto request) throws Exception {
		return logic.delete(request);
	}
}