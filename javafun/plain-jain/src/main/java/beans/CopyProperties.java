package beans;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.lang.reflect.Field;

public class CopyProperties implements Runnable {
    public static void main(String[] args) {
        new CopyProperties().run();
    }

    @Override
    public void run() {
        PojoA a = new PojoA();
        a.field = "salamander";

        PojoB b = new PojoB();


        try {
            BeanUtils.copyProperties(a, b); // doesn't work on public fields
        } catch (Exception e) {
            throw new RuntimeException("oh no!", e);
        }
        System.out.println(ToStringBuilder.reflectionToString(a));
        System.out.println(ToStringBuilder.reflectionToString(b));

        copyPublicFields(a, b);

        System.out.println(ToStringBuilder.reflectionToString(a));
        System.out.println(ToStringBuilder.reflectionToString(b));
    }

    public static void copyPublicFields(Object from, Object to) {
        Field[] fields = from.getClass().getFields();
        Class<?> toClazz = to.getClass();
        for (Field field : fields) {
            Field f;
            try {
                f = toClazz.getField(field.getName());
            } catch (NoSuchFieldException e) {
                continue;
            }
            if (f.getType().isAssignableFrom(field.getType())) {
                try {
                    Object v = field.get(from);
                    f.set(to, v);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("this shouldn't happen");
                }
            }
        }
    }

    class PojoA {
        public String field;
    }

    class PojoB {
        public String field;
    }
}
