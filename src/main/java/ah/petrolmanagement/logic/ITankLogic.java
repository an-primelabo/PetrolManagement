package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.TankRequestDto;
import ah.petrolmanagement.dto.response.TankResponseDto;
import ah.petrolmanagement.exception.PetrolException;

public interface ITankLogic {
	List<TankResponseDto> select(final TankRequestDto dto)
			throws PetrolException;

	TankResponseDto save(final TankRequestDto dto) throws PetrolException;

	TankResponseDto update(final TankRequestDto dto) throws PetrolException;

	TankResponseDto delete(final TankRequestDto dto) throws PetrolException;
}