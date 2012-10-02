package name.skitazaki.apiproxy.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.client.match.RequestMatchers.method;
import static org.springframework.test.web.client.match.RequestMatchers.requestTo;
import static org.springframework.test.web.client.response.ResponseCreators.withSuccess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class HttpRequestProxyTest {

	private MockRestServiceServer mockServer;

	@Autowired
	private RestTemplate restTemplate;

	@Configuration
	static class Config {

		@Bean
		public RestTemplate restTemplate() {
			RestTemplate restTemplate = new RestTemplate();
			// set properties, etc.
			return restTemplate;
		}

	}

	@Before
	public void setUp() throws Exception {
		this.mockServer = MockRestServiceServer.createServer(this.restTemplate);
	}

	@After
	public void tearDown() throws Exception {
		this.mockServer.verify();
	}

	@Test
	public void test() {
		String responseBody = "{\"name\" : \"Ludwig van Beethoven\", \"someDouble\" : \"1.6035\"}";
		this.mockServer
				.expect(requestTo("/composers/42"))
				.andExpect(method(HttpMethod.GET))
				.andRespond(
						withSuccess(responseBody, MediaType.APPLICATION_JSON));
		// XXX: Implement test logic.
		String object = restTemplate.getForObject("/composers/{id}", String.class, 42);
		assertThat(object, notNullValue());
	}

}
