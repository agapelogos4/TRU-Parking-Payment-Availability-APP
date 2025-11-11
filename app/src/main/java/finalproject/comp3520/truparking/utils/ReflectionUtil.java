package finalproject.comp3520.truparking.utils;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static int getResourceIdFromString(String resourceName, Class<?> c) throws IllegalAccessException, NoSuchFieldException {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
    }

}
