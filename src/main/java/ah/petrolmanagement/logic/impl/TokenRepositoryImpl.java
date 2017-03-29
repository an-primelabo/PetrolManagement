package ah.petrolmanagement.logic.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import ah.petrolmanagement.entity.PersistentLoginEntity;
import ah.petrolmanagement.logic.CommonLogic;
import ah.petrolmanagement.persistence.IPersistentLoginMapper;

@Component
public class TokenRepositoryImpl extends CommonLogic implements PersistentTokenRepository {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IPersistentLoginMapper mapper;

	@Override
	public void createNewToken(final PersistentRememberMeToken token) {
		logger.info("createNewToken : {}", token.getUsername());

		PersistentLoginEntity entity = new PersistentLoginEntity();
		BeanUtils.copyProperties(token, entity);

		try {
			mapper.save(entity);
		} catch (Exception e) {
			logger.error("save error : {}", e);

			throw e;
		}
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		logger.info("getTokenForSeries : {}", seriesId);

		List<PersistentLoginEntity> entities = mapper.select(seriesId);

		if (entities.size() == 1) {
			PersistentLoginEntity entity = entities.get(0);
			return new PersistentRememberMeToken(entity.getUsername(), entity.getSeries(), entity.getToken(), entity.getLastUsed());
		}
		return null;
	}

	@Override
	public void removeUserTokens(String username) {
		logger.info("removeUserTokens : {}", username);

		try {
			logger.info("rememberMe was selected");

			mapper.delete(username);
		} catch (Exception e) {
			logger.error("delete error : {}", e);

			throw e;
		}
	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		logger.info("updateToken : {}", seriesId);

		PersistentLoginEntity entity = new PersistentLoginEntity();
		entity.setSeries(seriesId);
		entity.setToken(tokenValue);
		entity.setLastUsed(lastUsed);

		try {
			mapper.update(entity);
		} catch (Exception e) {
			logger.error("update error : {}", e);

			throw e;
		}
	}
}