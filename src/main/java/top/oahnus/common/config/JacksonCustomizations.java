package top.oahnus.common.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.oahnus.enums.SexEnum;

import java.io.IOException;

/**
 * Created by oahnus on 2018/5/27
 * 0:04.
 */
@Configuration
public class JacksonCustomizations {
    @Bean
    public Module realWorldModules() {
        return new RealWorldModules();
    }

    public static class RealWorldModules extends SimpleModule {
        public RealWorldModules() {
            addSerializer(SexEnum.class, new SexSerializer());
            addDeserializer(SexEnum.class, new SexDeserializer());
        }
    }

    public static class SexDeserializer extends StdDeserializer<SexEnum> {

        protected SexDeserializer() {
            super(SexEnum.class);
        }

        @Override
        public SexEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            String text = jsonParser.getText();
            if (StringUtils.isNumeric(text)) {
                return SexEnum.getSex(Integer.valueOf(text));
            }
            return null;
        }
    }

    public static class SexSerializer extends StdSerializer<SexEnum> {

        protected SexSerializer() {
            super(SexEnum.class);
        }

        @Override
        public void serialize(SexEnum value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            if (value == null) {
                gen.writeNull();
            } else {
                gen.writeString(String.valueOf(value.getCode()));
            }
        }
    }
}
