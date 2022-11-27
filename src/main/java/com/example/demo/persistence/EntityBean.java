package com.example.demo.persistence;

import com.example.demo.util.Pair;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class EntityBean {

    public EntityBean(String tableName, Class<?> id, Map<String, Class<?>> primitiveField, Map<String, Class<?>> mappingClass, Map<String, Pair<String, String>> mappingList) {
        this.tableName = tableName;
        Id = id;
        this.primitiveField = primitiveField;
        this.mappingClass = mappingClass;
        this.mappingList = mappingList;
        numberOfPrimitiveField = mappingClass.size() + primitiveField.size();
    }

    int numberOfPrimitiveField;

    String tableName = "";
    Class<?> Id = null;

    // Entry<Название параметра,Класс>

    // для примитивов
    Map<String, Class<?>> primitiveField = new HashMap<>();
    // для @OneToOne и @ManyToOne
    Map<String, Class<?>> mappingClass = new HashMap<>();
    // Для @ManyToMany и @OneToMany
    // Entry<name,Pair<My_id,ref_id>>
    Map<String, Pair<String, String>> mappingList = new HashMap<>();


}
