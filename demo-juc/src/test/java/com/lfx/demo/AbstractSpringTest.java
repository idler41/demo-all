package com.lfx.demo;


import com.lfx.demo.config.AppConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2022-11-13 09:23:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
public class AbstractSpringTest {

//    public static final ObjectMapper OBJECT_MAPPER;
//
//    static {
//        OBJECT_MAPPER = new ObjectMapper();
//        // 对象的所有字段全部列入，还是其他的选项，可以忽略null等
//        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
//        // 设置Date类型的序列化及反序列化格式
//        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//
//        // 忽略空Bean转json的错误
//        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        // 忽略未知属性，防止json字符串中存在，java对象中不存在对应属性的情况出现错误
//        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        // 注册一个时间序列化及反序列化的处理模块，用于解决jdk8中localDateTime等的序列化问题
//        OBJECT_MAPPER.registerModule(new JavaTimeModule());
//    }


    public static void initSysProperty() {
        System.setProperty("log.log-default-file", "CONSOLE_LOG");
    }

//    public static <T> T readJsonFile(String file, Class<T> clazz) {
//        String jsonContent = loadResource(file);
//        return parseObject(jsonContent, clazz);
//    }
//
//    public static <T> List<T> readListJsonFile(String file, Class<T> clazz) {
//        String jsonContent = loadResource(file);
//        return parseList(jsonContent, clazz);
//    }
//
//    public static String loadResource(String resourceName) {
//        StringBuilder buffer = new StringBuilder();
//        try (BufferedReader in = new BufferedReader(new InputStreamReader(Objects.requireNonNull(AbstractSpringTest.class.getClassLoader().getResourceAsStream(resourceName))))) {
//            String line;
//            while ((line = in.readLine()) != null) {
//                buffer.append(line);
//            }
//            return buffer.toString();
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    private static <T> T parseObject(String json, Class<T> clazz) {
//        try {
//            return OBJECT_MAPPER.readValue(json, clazz);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static <T> List<T> parseList(String jsonContent, Class<T> clazz) {
//        try {
//            return OBJECT_MAPPER.readValue(jsonContent, OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, clazz));
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
}