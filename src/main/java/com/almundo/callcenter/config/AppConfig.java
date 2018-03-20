package com.almundo.callcenter.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.almundo.callcenter")
public class AppConfig {
	
	public static final Integer CORE_POOL_SIZE = 10;
	public static final Integer MAX_POOL_SIZE = 10;
	public static final long NEXT_CALL_WAIT = 500;
	
	/**
	 * Se configura la cantidad de hilos que se va a tener en ejecución
	 * @return ThreadPoolTaskExecutor
	 */
	@Bean
	public TaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setThreadNamePrefix("call_center_task_executor_thread");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
	}
	
	@Bean
    public ViewResolver contentNegotiatingViewResolver(
            ContentNegotiationManager manager) {

        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

        InternalResourceViewResolver pagesResolver = new InternalResourceViewResolver();
        pagesResolver.setPrefix("/WEB-INF/");
        pagesResolver.setSuffix(".jsp");
        pagesResolver.setViewClass(JstlView.class);
        resolvers.add(pagesResolver);

        JsonViewResolver jsonResolver = new JsonViewResolver();
        resolvers.add(jsonResolver);

        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setViewResolvers(resolvers);
        resolver.setContentNegotiationManager(manager);
        return resolver;

    }

    /**
     * View resolver for returning JSON in a view-based system. Always returns a
     * {@link MappingJacksonJsonView}.
     */
    public class JsonViewResolver implements ViewResolver{
    	 
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            view.setPrettyPrint(true);       
            return view;
          }
     
    }
}
