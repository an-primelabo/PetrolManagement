package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.DailyMeterRequestDto;
import ah.petrolmanagement.dto.response.DailyMeterResponseDto;
import ah.petrolmanagement.exception.PetrolException;

public interface IDailyMeterLogic {
	List<DailyMeterResponseDto> select(final DailyMeterRequestDto dto)
			throws PetrolException;

	DailyMeterResponseDto save(final DailyMeterRequestDto dto)
			throws PetrolException;

	DailyMeterResponseDto update(final DailyMeterRequestDto dto)
			throws PetrolException;

	DailyMeterResponseDto delete(final DailyMeterRequestDto dto)
			throws PetrolException;
}