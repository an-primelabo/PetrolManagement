package ah.petrolmanagement.service;

import java.util.List;

import ah.petrolmanagement.dto.request.DailyMeterRequestDto;
import ah.petrolmanagement.dto.response.DailyMeterResponseDto;

public interface IDailyMeterService {
	public List<DailyMeterResponseDto> select(final DailyMeterRequestDto request)
			throws Exception;

	public DailyMeterResponseDto save(final DailyMeterRequestDto request)
			throws Exception;

	public DailyMeterResponseDto update(final DailyMeterRequestDto request)
			throws Exception;

	public DailyMeterResponseDto delete(final DailyMeterRequestDto request)
			throws Exception;
}