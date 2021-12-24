package com.cloning;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Demo to deep clone object using serialization
 *  
 */
public class DeepCloneUsingSerializationDemo {

  public static void main(String[] args) throws Exception {
    Employee employee = new Employee();
    employee.setFirstName("Nilesh");
    employee.setLastName("Wani");

    Address address = new Address();
    address.setCity("Pune");
    address.setCountry("India");

    employee.setAddress(address);

    System.out.println("Original Employee: " + employee);

    Employee clonedEmployee = (Employee) ObjectCloner.deepCopy(employee);
    System.out.println("Cloned Employee: " + clonedEmployee);

    System.out.println("Changing city of original employee to Banglore");
    employee.getAddress().setCity("Banglore");

    System.out.println("Original Employee: " + employee);
    System.out.println("Cloned Employee: " + clonedEmployee);
  }

  private static class Employee implements Serializable {
    private String firstName;
    private String lastName;
    private Address address;

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public Address getAddress() {
      return address;
    }

    public void setAddress(Address address) {
      this.address = address;
    }

    @Override
    public String toString() {
      return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + "]";
    }
  }

  private static class Address implements Serializable {
    private String city;
    private String country;

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getCountry() {
      return country;
    }

    public void setCountry(String country) {
      this.country = country;
    }

    @Override
    public String toString() {
      return "Address [city=" + city + ", country=" + country + "]";
    }
  }

  private static class ObjectCloner {
    private ObjectCloner() {
      // Nobody can accidentally create an ObjectCloner object
    }

    static public Object deepCopy(Object originalObject) throws Exception {
      ObjectOutputStream objectOutputStream = null;
      ObjectInputStream objectInputStream = null;
      try {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        objectOutputStream = new ObjectOutputStream(bos);

        // Serialize the object
        objectOutputStream.writeObject(originalObject);
        objectOutputStream.flush();

        ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
        objectInputStream = new ObjectInputStream(bin);
        return objectInputStream.readObject();
      }
      catch (Exception e) {
        System.out.println("Exception in ObjectCloner = " + e);
        throw (e);
      }
      finally {
        objectOutputStream.close();
        objectInputStream.close();
      }
    }

  }
}
