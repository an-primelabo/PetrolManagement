package ah.petrolmanagement.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.context.ServletContextAware;

import ah.petrolmanagement.constants.ApiConstants;

public class JSTLConstantsRegister implements ServletContextAware, ResourceLoaderAware, InitializingBean {
	private static final String CLASS_RESOURCE_PATTERN = "**/*.class";
	private final TypeFilter annotationFilter = new AnnotationTypeFilter(ConstantsRegister.class);
	private ServletContext servletContext;
	private ResourcePatternResolver resourcePatternResolver;
	private MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();
	private List<String> packages;

	public void setPackages(final List<String> packages) {
		this.packages = packages;
	}

	@Override
	public void setServletContext(final ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void setResourceLoader(final ResourceLoader resourceLoader) {
		this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
		this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (packages == null) {
			return;
		}
		for (String p : packages) {
			String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
					+ ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(p))
					+ ApiConstants.SLASH + CLASS_RESOURCE_PATTERN;
			Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);

			for (Resource resource : resources) {
				if (resource.isReadable()) {
					MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);

					if (this.annotationFilter.match(metadataReader, metadataReaderFactory)) {
						registerConstantsClass(metadataReader.getClassMetadata().getClassName());
					}
				}
			}
		}
	}

	private void registerConstantsClass(final String className) {
		Class<?> clazz = null;

		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
		}
		String shortName;
		int i = className.lastIndexOf('.');

		if (i > 0) {
			shortName = className.substring(i + 1);
		} else {
			shortName = className;
		}
		Map<String, Object> map = new HashMap<String, Object>();

		for (Field f : clazz.getDeclaredFields()) {
			if (f.getDeclaringClass() != clazz) {
				continue;
			}
			int mod = f.getModifiers();

			if (Modifier.isPublic(mod) && Modifier.isStatic(mod)) {
				Object value = null;

				try {
					value = f.get(null);
				} catch (Exception e) {
				}
				map.put(f.getName(), value);
			}
		}
		if (!map.isEmpty()) {
			servletContext.setAttribute(shortName, map);
		}
	}
}