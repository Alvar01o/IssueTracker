package com.fiuni.sd.issuetracker.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:fiuni.properties")
public class Settings {
	public static final Integer PAGINACION = 30;
	public static final Integer PAGINACION2 = 6;
	public static final Integer PAGINACIONTABLEROS = 6;
	public static final String CACHE_NAME = "it_rest_cache";
}
