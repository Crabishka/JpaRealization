package com.example.demo.persistence;

import com.example.demo.util.Pair;
import org.reflections.Reflections;


import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class EntityBeanProcessor {


    public Map<Class<?>, EntityBean> createEntityBean() {
        Reflections reflections = new Reflections("com.example.demo.domain");
        Set<Class<?>> entities = reflections.getTypesAnnotatedWith(Entity.class);
        Map<Class<?>, EntityBean> entityBeanMap = new HashMap<>();
        for (Class<?> entity : entities) {
            Class<?> id = int.class;
            Field[] fields = entity.getDeclaredFields();
            Map<String, Class<?>> primitiveField = new HashMap<>();
            Map<String, Class<?>> oneToOneField = new HashMap<>();
            Map<String, Pair<String, String>> manyToManyField = new HashMap<>();
            Table tableAnnotation = entity.getAnnotation(Table.class);
            String tableName;
            if (tableAnnotation != null) {
                tableName = tableAnnotation.name();
            } else {
                tableName = entity.getSimpleName().toLowerCase(Locale.ROOT);
            }

            for (Field field : fields) {
                OneToOne oneToOneAnnotation = field.getAnnotation(OneToOne.class);
                ManyToOne manyToOneAnnotation = field.getAnnotation(ManyToOne.class);
                OneToMany oneToManyAnnotation = field.getAnnotation(OneToMany.class);
                ManyToMany manyToManyAnnotation = field.getAnnotation(ManyToMany.class);
                Id idAnnotation = field.getAnnotation(Id.class);
                Column columnAnnotation = field.getAnnotation(Column.class);
                JoinTable joinTableAnnotation = field.getAnnotation(JoinTable.class);
                // название
                String name = "";
                if (columnAnnotation == null) {
                    name = field.getName();
                } else if (manyToManyAnnotation != null || oneToManyAnnotation != null) {
                    name = entity.getSimpleName() + "_id";
                } else if (idAnnotation != null) {
                    name = "id";
                } else {
                    name = columnAnnotation.name();
                }
                // определение типа
                if (oneToOneAnnotation != null || manyToOneAnnotation != null) {
                    oneToOneField.put(name, field.getType());
                } else if (manyToManyAnnotation != null || oneToManyAnnotation != null) {
                    String myName = joinTableAnnotation.joinColumns()[0].name();
                    String referName = joinTableAnnotation.inverseJoinColumns()[0].name();
                    Pair<String, String> pair = new Pair<>(myName, referName);
                    manyToManyField.put(name, pair);
                } else if (idAnnotation != null) {
                    id = field.getType();
                } else {
                    primitiveField.put(name, field.getType());
                }
            }
            entityBeanMap.put(entity, new EntityBean(tableName, id, primitiveField, oneToOneField, manyToManyField));
        }
        return entityBeanMap;
    }
}
