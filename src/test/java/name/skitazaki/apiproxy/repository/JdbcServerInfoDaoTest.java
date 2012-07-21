package name.skitazaki.apiproxy.repository;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:test-context.xml")
public class JdbcServerInfoDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	private ServerInfoDao serverInfoDao;

	public void setServerInfoDao(ServerInfoDao serverInfoDao) {
		this.serverInfoDao = serverInfoDao;
	}

	// @Override
	// protected void onSetUpInTransaction() throws Exception {
	// super.deleteFromTables(new String[] { "products" });
	// super.executeSqlScript("file:db/load_data.sql", true);
	// }

	@Test
	public void testGetProductList() {
		//
		// List<Product> products = productDao.getProductList();
		//
		// assertEquals("wrong number of products?", 3, products.size());

	}

	@Test
	public void testSaveProduct() {
		//
		// List<Product> products = productDao.getProductList();
		//
		// for (Product p : products) {
		// p.setPrice(200.12);
		// productDao.saveProduct(p);
		// }
		//
		// List<Product> updatedProducts = productDao.getProductList();
		// for (Product p : updatedProducts) {
		// assertEquals("wrong price of product?", 200.12, p.getPrice());
		// }
		//
	}
}
