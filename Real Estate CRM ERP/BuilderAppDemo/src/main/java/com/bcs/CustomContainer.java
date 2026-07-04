package com.bcs;

import org.springframework.stereotype.Component;

@Component
public class CustomContainer {

	/*@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {

		container.setPort(8080);
		container.setContextPath("/mkyong");

	}*/

	/*public @Bean EmbeddedServletContainerFactory embeddedServletContainerFactory() {
	    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory("", 8080) {
	        @Override
	        protected void configureContext(Context context, ServletContextInitializer[] initializers) {
	            context.setDocBase("C:/ysmvts/sms_images/");
	            super.configureContext(context, initializers);
	        }
	    };
	    return factory;
	}*/
	
}
