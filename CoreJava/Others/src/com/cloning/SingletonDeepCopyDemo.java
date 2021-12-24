package com.cloning;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Demo to see how we can break Singleton pattern using deep copy with serialization
 *  
 */
public class SingletonDeepCopyDemo {

  public static void main(String[] args) throws Exception {
    SingletonEmployee employee = SingletonEmployee.getInstance();
    System.out.println("Singleton employee: " + employee);

    SingletonEmployee clonedEmployee = (SingletonEmployee) ObjectCloner.deepCopy(employee);

    System.out.println("Changing name of original employee to NILESH WANI");
    employee.setFirstName("NILESH");
    employee.setLastName("WANI");

    System.out.println("Original Singleton Employee: " + employee);
    System.out.println("Cloned Singleton Employee: " + clonedEmployee);

  }

  private static class SingletonEmployee implements Serializable {

    private static final SingletonEmployee singletonEmployee = new SingletonEmployee("Nilesh", "Wani");

    private String firstName;
    private String lastName;

    private SingletonEmployee(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public static SingletonEmployee getInstance() {
      return singletonEmployee;
    }

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

    @Override
    public String toString() {
      return "SingletonEmployee [firstName=" + firstName + ", lastName=" + lastName + "]";
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
