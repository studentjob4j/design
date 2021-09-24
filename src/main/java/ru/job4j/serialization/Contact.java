package ru.job4j.serialization;

import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.*;
import java.nio.file.Files;

/**
 * Сериализация - десериализация используя библиотеку JAXB
 * @author Shegai Evgenii
 * @since 24.09.2021
 * @version 1.0
 */

@XmlElement(value = "contact")
public class Contact implements Serializable {

    @XmlAttribute
    private String phone;

    public Contact(String phone) {
        this.phone = phone;
    }

    public Contact() {
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{" + "phone='" + phone + '\'' + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact("+7 (111) 111-11-11");
        /* Запись объекта в файл */
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }
        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}
