package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.ShiftRequestDto;
import ah.petrolmanagement.dto.response.ShiftResponseDto;
import ah.petrolmanagement.exception.PetrolException;

public interface IShiftLogic {
	List<ShiftResponseDto> select(final ShiftRequestDto dto)
			throws PetrolException;

	ShiftResponseDto save(final ShiftRequestDto dto) throws PetrolException;

	ShiftResponseDto update(final ShiftRequestDto dto) throws PetrolException;

	ShiftResponseDto delete(final ShiftRequestDto dto) throws PetrolException;
}