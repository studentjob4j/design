package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Преобразование JSON в POJO. JsonObject
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

    public boolean isValue() {
        return value;
    }

    public int getData() {
        return data;
    }

    public String getText() {
        return text;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getArrays() {
        return arrays;
    }

    @Override
    public String toString() {
        return "JsonExample{" + "value=" + value + ", data=" + data + ", text='" + text + '\'' + ", contact=" + contact
                + ", array=" + Arrays.toString(arrays) + '}';
    }

    public static void main(String[] args) throws JAXBException, IOException {

       /* Использование gson

       String[] array = new String[] {"text", "data", "value"};
        final JsonExample example = new JsonExample(true, 2021, "Thursday",
                new Contact(12345, "1122"), array);
        final Gson gson = new GsonBuilder().create();

        // создал json из объекта JsonExample

        String temp = gson.toJson(example);
        System.out.println(temp);

        // создал объект из json

        final JsonExample jsonExample = gson.fromJson(temp, JsonExample.class);
        System.out.println(jsonExample);*/

        /*JAXB

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
    }*/

        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person).toString());
    }
}
