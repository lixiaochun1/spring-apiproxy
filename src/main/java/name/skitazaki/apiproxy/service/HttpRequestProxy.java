package name.skitazaki.apiproxy.service;

import java.util.HashMap;
import java.util.Map;

import name.skitazaki.apiproxy.model.ServerInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class HttpRequestProxy {

	private static final Logger logger = LoggerFactory
			.getLogger(HttpRequestProxy.class);

	@Autowired
	private RestTemplate restTemplate;

	public String proxy(ServerInfo info, String query) {
		Map<String, String> vars = new HashMap<String, String>();
		String url = info.getUrl();
		url += query == null ? "" : ("?" + query);
		vars.put("core", "wikipedia");
		try {
			String ret = restTemplate.getForObject(url, String.class, vars);
			return ret;
		} catch (RestClientException e) {
			logger.error("{} - {}", e.getMessage(), url);
		}
		return null;
	}

}
