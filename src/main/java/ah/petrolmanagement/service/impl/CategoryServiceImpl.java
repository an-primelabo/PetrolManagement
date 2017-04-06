package ah.petrolmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.petrolmanagement.dto.request.CategoryRequestDto;
import ah.petrolmanagement.dto.response.CategoryResponseDto;
import ah.petrolmanagement.logic.ICategoryLogic;
import ah.petrolmanagement.service.ICategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private ICategoryLogic logic;

	@Override
	public List<CategoryResponseDto> select() throws Exception {
		return logic.select();
	}

	@Override
	public CategoryResponseDto save(CategoryRequestDto request)
			throws Exception {
		return logic.save(request);
	}

	@Override
	public CategoryResponseDto update(CategoryRequestDto request)
			throws Exception {
		return logic.update(request);
	}

	@Override
	public CategoryResponseDto delete(CategoryRequestDto request)
			throws Exception {
		return logic.delete(request);
	}
}