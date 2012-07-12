package name.skitazaki.apiproxy;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import name.skitazaki.apiproxy.model.Configuration;
import name.skitazaki.apiproxy.service.ConfigurationManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	private ConfigurationManager manager;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		if (locale == null) {
			locale = Locale.getDefault();
		}
		logger.info("Welcome home! the client locale is " + locale.toString());

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		if (model != null) {
			model.addAttribute("serverTime", formattedDate);
		}

		return "home";
	}

	@RequestMapping(value = "/servers", method = RequestMethod.GET)
	@ResponseBody
	public List<Configuration> servers() {
		return manager.getConfigurations();
	}

	@RequestMapping(value = "/proxy/{server}", method = RequestMethod.GET)
	@ResponseBody
	public Configuration proxy(@PathVariable String server,
			HttpServletRequest request) {
		if (request == null) {
			logger.warn("'request' object is not given.");
		} else {
			logger.info("Query: " + request.getQueryString());
		}
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("core", "wikipedia");
		// XXX: Retrieve by the "server" variable.
		String url = "http://localhost:8983/solr/{core}";
		RestTemplate template = new RestTemplate();
		try {
			String ret = template.getForObject(url, String.class, vars);
			logger.info(ret);
		} catch (RestClientException e) {
			logger.error("{} - {}", e.getMessage(), url);
		}
		Configuration c1 = new Configuration();
		c1.setName("solr");
		c1.setUrl("http://localhost:8983/solr/{core}");
		return c1;
	}

	@Autowired
	public void setConfigurationManager(ConfigurationManager manager) {
		this.manager = manager;
	}
}
