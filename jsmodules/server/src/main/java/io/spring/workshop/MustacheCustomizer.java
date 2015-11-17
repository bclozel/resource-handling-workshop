package io.spring.workshop;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.samskivert.mustache.Mustache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mustache.web.MustacheViewResolver;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

@Component
class MustacheCustomizer {

	private final ResourceUrlProvider resourceUrlProvider;

	private final MustacheViewResolver mustacheViewResolver;

	private final Environment env;

	@Autowired
	public MustacheCustomizer(ResourceUrlProvider resourceUrlProvider,
			MustacheViewResolver mustacheViewResolver, Environment env) {
		this.resourceUrlProvider = resourceUrlProvider;
		this.mustacheViewResolver = mustacheViewResolver;
		this.env = env;
	}

	@PostConstruct
	public void customizeViewResolver() {
		Map<String, Object> attributesMap = new HashMap<>();

		attributesMap.put("url", (Mustache.Lambda) (frag, out) -> {
			String url = frag.execute();
			String resourceUrl = resourceUrlProvider.getForLookupPath(url);
			if (resourceUrl != null) {
				out.write(resourceUrl);
			}
			else {
				out.write(url);
			}
		});

		attributesMap.put("info", (Mustache.Lambda) (frag, out) -> {
			String key = frag.execute();
			String value = env.getProperty("info." + key);
			if (value != null) {
				out.write(value);
			}
			else {
				out.write("");
			}
		});

		mustacheViewResolver.setAttributesMap(attributesMap);
	}

}
