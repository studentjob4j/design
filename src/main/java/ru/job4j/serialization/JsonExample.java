package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

public class JsonExample {

    private boolean value;
    private int data;
    private String text;
    private Contact contact;
    private String[] array;

    public JsonExample(final boolean value, final int data, final String text, final Contact contact, final String[] array) {
        this.value = value;
        this.data = data;
        this.text = text;
        this.contact = contact;
        this.array = array;
    }

    @Override
    public String toString() {
        return "JsonExample{" + "value=" + value + ", data=" + data + ", text='" + text + '\'' + ", contact=" + contact
                + ", array=" + Arrays.toString(array) + '}';
    }

    public static void main(String[] args) {
        String[] array = new String[] {"text", "data", "value"};
        final JsonExample example = new JsonExample(true, 2021, "Thursday",
                new Contact(12345, "1122"), array);
        final Gson gson = new GsonBuilder().create();
        // создал json из объекта JsonExample
        String temp = gson.toJson(example);
        System.out.println(temp);
        // создал объект из json
        final JsonExample jsonExample = gson.fromJson(temp, JsonExample.class);
        System.out.println(jsonExample);
    }
}
