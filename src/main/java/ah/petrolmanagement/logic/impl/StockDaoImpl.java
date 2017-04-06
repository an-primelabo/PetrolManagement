package ah.petrolmanagement.logic.impl;

import org.springframework.stereotype.Component;

@Component
public class StockDaoImpl{
//implements
//		StockDao {
//	static final Logger logger = LoggerFactory.getLogger(StockDaoImpl.class);
//
//	@Override
//	public StockEntity findById(int id) {
//		logger.info("findById : {}", id);
//		return getByKey(id);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<StockEntity> findAllStocks() {
//		logger.info("findAllStocks : {}");
//		Criteria criteria = createEntityCriteria();
//		criteria.addOrder(Order.asc("id"));
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		return (List<StockEntity>) criteria.list();
//	}
//
//	@Override
//	public void save(StockEntity entity) {
//		logger.info("save : {}", entity);
//		persist(entity);
//	}
//
//	@Override
//	public void delete(int id) {
//		logger.info("delete : {}", id);
//		StockEntity entity = getByKey(id);
//		delete(entity);
//	}
}