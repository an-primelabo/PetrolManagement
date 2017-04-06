package ah.petrolmanagement.logic;

import java.util.List;

import ah.petrolmanagement.dto.request.TankRequestDto;
import ah.petrolmanagement.dto.response.TankResponseDto;

public interface ITankLogic {
	List<TankResponseDto> select(final TankRequestDto request) throws Exception;

	TankResponseDto save(final TankRequestDto request) throws Exception;

	TankResponseDto update(final TankRequestDto request) throws Exception;

	TankResponseDto delete(final TankRequestDto request) throws Exception;
}