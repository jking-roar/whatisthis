package json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ObjectMapperConfigurationNotThreadSafe {
    public static void main(String[] args) {
        final ObjectMapper objectMapper = new ObjectMapper();

        ExecutorService executorService = Executors.newFixedThreadPool(16);

        List<Future<?>> submissions = new ArrayList<Future<?>>();
        for (int i = 0; i < 10000; i++) {
            submissions.add(executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        boolean wrapRoot = Math.random() < 0.5;
                        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, wrapRoot);
                        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, wrapRoot);
                        String s = objectMapper.writeValueAsString(new TestObject(2));
//                        System.out.println(s);
                        objectMapper.readValue(s, TestObject.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("oh no", e);
                    } catch (IOException e) {
                        throw new RuntimeException("oh no", e);
                    }
                }
            }));
        }

        for (Future<?> submission : submissions) {
            try {
                submission.get();
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            } catch (ExecutionException e) {
                System.out.println("exceptional");
                e.printStackTrace();
                executorService.shutdown();
                break;
            }
        }
        executorService.shutdown();
        System.out.println("done");
    }

}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("TestObject")
class TestObject {
    @JsonProperty("uuid")
    final UUID uuid = UUID.randomUUID();

    @JsonProperty("subTests")
    List<TestObject> subTests = null;

    public TestObject() {

    }

    public TestObject(int i) {
        this();
        if (i > 0) {
            subTests = new ArrayList<TestObject>();
            for (int j = 0; j < i; j++) {
                subTests.add(new TestObject(i - 1));
            }
        }
    }
}
