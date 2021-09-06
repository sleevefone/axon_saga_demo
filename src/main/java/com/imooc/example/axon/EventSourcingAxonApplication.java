package com.imooc.example.axon;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class EventSourcingAxonApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventSourcingAxonApplication.class, args);
    }

//    @Bean
//    public Serializer serializer() {
//        return JacksonSerializer.builder()
//                .objectMapper(new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY))
//                .build();
//    }
//	/**
//	 * https://stackoverflow.com/questions/55298592/axon-4-xstream-configuration
//	 */
//	@Bean
//	XStream xstream(){
//		XStream xstream = new XStream();
//		// clear out existing permissions and set own ones
//		xstream.addPermission(NoTypePermission.NONE);
//		// allow any type from the same package
//		xstream.allowTypesByWildcard(new String[] {
//				"com.ourpackages.**",
//				"org.axonframework.**",
//				"java.**",
//				"com.thoughtworks.xstream.**"
//		});
//
//		return xstream;
//	}
//
//	@Bean
//	@Primary
//	public Serializer serializer(XStream xStream) {
//		return XStreamSerializer.builder().xStream(xStream).build();
//	}
}
