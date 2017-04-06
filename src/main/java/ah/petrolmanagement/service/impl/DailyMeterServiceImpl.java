package ah.petrolmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.petrolmanagement.dto.request.DailyMeterRequestDto;
import ah.petrolmanagement.dto.response.DailyMeterResponseDto;
import ah.petrolmanagement.logic.IDailyMeterLogic;
import ah.petrolmanagement.service.IDailyMeterService;

@Service("dailyService")
public class DailyMeterServiceImpl implements IDailyMeterService {
	@Autowired
	private IDailyMeterLogic logic;

	@Override
	public List<DailyMeterResponseDto> select(DailyMeterRequestDto request)
			throws Exception {
		return logic.select(request);
	}

	@Override
	public DailyMeterResponseDto save(DailyMeterRequestDto request)
			throws Exception {
		return logic.save(request);
	}

	@Override
	public DailyMeterResponseDto update(DailyMeterRequestDto request)
			throws Exception {
		return logic.update(request);
	}

	@Override
	public DailyMeterResponseDto delete(DailyMeterRequestDto request)
			throws Exception {
		return logic.delete(request);
	}
}