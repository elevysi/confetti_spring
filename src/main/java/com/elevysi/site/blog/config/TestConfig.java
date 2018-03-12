package com.elevysi.site.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


/**
 * 
 * @author Elevysi
 * //https://stackoverflow.com/questions/27979735/cannot-process-locations-and-classes-for-context-configuration
 *Spring test cannot mix XML Context and Java Classes Application context, so create a bridge class for test
 */

@Configuration
@ImportResource({"classpath:META-INF/application-context.xml", "classpath:META-INF/datasource.xml"})
public class TestConfig {

}
