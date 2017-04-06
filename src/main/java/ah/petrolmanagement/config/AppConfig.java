package ah.petrolmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import ah.petrolmanagement.utils.LogUtil;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ah.petrolmanagement")
public class AppConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		LogUtil.startMethod(this.getClass().getSimpleName(), "viewResolver");

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		LogUtil.startMethod(this.getClass().getSimpleName(), "tilesConfigurer");

		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles.xml" });
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		LogUtil.startMethod(this.getClass().getSimpleName(),
				"configureViewResolvers", registry);

		TilesViewResolver viewResolver = new TilesViewResolver();
		registry.viewResolver(viewResolver);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		LogUtil.startMethod(this.getClass().getSimpleName(),
				"addResourceHandlers", registry);

		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
}