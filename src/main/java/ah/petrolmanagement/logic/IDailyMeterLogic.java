package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.DailyMeterRequestDto;
import ah.petrolmanagement.dto.response.DailyMeterResponseDto;

public interface IDailyMeterLogic {
	List<DailyMeterResponseDto> select(final DailyMeterRequestDto request)
			throws Exception;

	DailyMeterResponseDto save(final DailyMeterRequestDto request)
			throws Exception;

	DailyMeterResponseDto update(final DailyMeterRequestDto request)
			throws Exception;

	DailyMeterResponseDto delete(final DailyMeterRequestDto request)
			throws Exception;
}