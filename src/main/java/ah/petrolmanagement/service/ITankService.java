package ah.petrolmanagement.service;

import java.util.List;

import ah.petrolmanagement.dto.request.TankRequestDto;
import ah.petrolmanagement.dto.response.TankResponseDto;

public interface ITankService {
	public List<TankResponseDto> select(final TankRequestDto request)
			throws Exception;

	public TankResponseDto save(final TankRequestDto request) throws Exception;

	public TankResponseDto update(final TankRequestDto request)
			throws Exception;

	public TankResponseDto delete(final TankRequestDto request)
			throws Exception;
}