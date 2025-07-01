package model.user;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class mahasiswa - Deskripsi singkat mengenai kelas ini.
 */
public class Mahasiswa {

    private StringProperty studentId;
    private StringProperty name;
    private StringProperty address;
    private StringProperty phoneNumber;
    private StringProperty password;

    /**
     * Constructor to create a Mahasiswa object with specified details.
     *
     * @param studentId   The unique student ID
     * @param name        The name of the student
     * @param address     The address of the student
     * @param phoneNumber The phone number of the student
     * @param password    The password of the student
     */
    public Mahasiswa(String studentId, String name, String address, String phoneNumber, String password) {
        this.studentId = new SimpleStringProperty(studentId);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.password = new SimpleStringProperty(password);
    }

    // Getters and Setters using JavaFX properties
    public String getStudentId() {
        return studentId.get();
    }

    public void setStudentId(String studentId) {
        this.studentId.set(studentId);
    }

    public StringProperty studentIdProperty() {
        return studentId;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId.get() + ", Name: " + name.get() + ", Address: " + address.get() +
                ", Phone: " + phoneNumber.get() + ", Password: " + password.get();
    }
}
