package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Сериализация - десериализация используя библиотеку JAXB
 * @author Shegai Evgenii
 * @since 24.09.2021
 * @version 1.0
 */

@XmlRootElement(name = "jsonexample")
@XmlAccessorType(XmlAccessType.FIELD)
public class JsonExample {

    @XmlAttribute
    private boolean value;
    @XmlAttribute
    private int data;
    @XmlAttribute
    private String text;
    @XmlElement
    private Contact contact;

    @XmlElementWrapper(name = "arrays")
    @XmlElement(name = "array")
    private String[] arrays;

    public JsonExample(boolean value, int data, String text, Contact contact, String[] array) {
        this.value = value;
        this.data = data;
        this.text = text;
        this.contact = contact;
         arrays = array;
    }

    public JsonExample() {
    }

    @Override
    public String toString() {
        return "JsonExample{" + "value=" + value + ", data=" + data + ", text='" + text + '\'' + ", contact=" + contact
                + ", array=" + Arrays.toString(arrays) + '}';
    }

    public static void main(String[] args) throws JAXBException, IOException {
       /* String[] array = new String[] {"text", "data", "value"};
        final JsonExample example = new JsonExample(true, 2021, "Thursday",
                new Contact(12345, "1122"), array);
        final Gson gson = new GsonBuilder().create();
        // создал json из объекта JsonExample
        String temp = gson.toJson(example);
        System.out.println(temp);
        // создал объект из json
        final JsonExample jsonExample = gson.fromJson(temp, JsonExample.class);
        System.out.println(jsonExample);*/

        JsonExample example = new JsonExample(true, 2021, "Thursday",
                new Contact("1122"), new String[] {"one", "two", "three"});

        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(JsonExample.class);

        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();

        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {

            // Сериализуем
            marshaller.marshal(example, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        //  создаем десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {

            // десериализуем
            JsonExample result = (JsonExample) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
