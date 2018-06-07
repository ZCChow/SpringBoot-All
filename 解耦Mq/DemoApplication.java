package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;

@SpringBootApplication
public class DemoApplication {

/*
	 @Bean
	JmsListenerContainerFactory myJmsListenerContainerFactory(ConnectionFactory connectionFactory){
		 SimpleJmsListenerContainerFactory factory=new SimpleJmsListenerContainerFactory();
		 factory.setConnectionFactory(connectionFactory);
		 factory.setPubSubDomain(true);
		 return factory;
	 }
*/

	@Bean(name = "topicContainerFactory1")
	public DefaultJmsListenerContainerFactory topicClient1(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer){
		DefaultJmsListenerContainerFactory factory = defaultJmsListenerContainerFactoryTopic(connectionFactory,configurer);
		factory.setClientId("10001");
		return factory;
	}

	@Bean(name = "topicContainerFactory2")
	public DefaultJmsListenerContainerFactory topicClient2(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer){
		DefaultJmsListenerContainerFactory factory = defaultJmsListenerContainerFactoryTopic(connectionFactory,configurer);
		factory.setClientId("10002");
		return factory;
	}

	/**
	 *
	 * @param connectionFactory
	 * @param configurer
	 * @return
	 */

	public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactoryTopic(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory,connectionFactory);
		factory.setPubSubDomain(true);
		factory.setSessionTransacted(true);
		factory.setAutoStartup(true);
		//开启持久化订阅
		factory.setSubscriptionDurable(true);
		return factory;
	}






    @Bean
	JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
      JmsTemplate template=new JmsTemplate();
      template.setDeliveryPersistent(true);
      template.setPubSubDomain(true);
      template.setConnectionFactory(connectionFactory);
      return template;
	}




	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
