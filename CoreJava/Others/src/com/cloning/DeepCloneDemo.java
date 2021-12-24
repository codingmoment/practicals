package com.cloning;

/**
 * Demo class to see how to achieve deep copy using clone().
 * 
 */
public class DeepCloneDemo {

  public static void main(String[] args) throws CloneNotSupportedException {
    Employee employee = new Employee();
    employee.setFirstName("Nilesh");
    employee.setLastName("Wani");

    Address address = new Address();
    address.setCity("Pune");
    address.setCountry("India");

    employee.setAddress(address);

    System.out.println("Original Employee: " + employee);

    Employee clonedEmployee = (Employee) employee.clone();
    System.out.println("Cloned Employee: " + clonedEmployee);

    System.out.println("Changing city of original employee to Banglore");
    employee.getAddress().setCity("Banglore");

    System.out.println("Original Employee: " + employee);
    System.out.println("Cloned Employee: " + clonedEmployee);
  }

  private static class Employee implements Cloneable {
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
    protected Object clone() throws CloneNotSupportedException {
      Employee clonedEmployee = (Employee) super.clone();
      // Create clone of field 'address'
      Address clonedAddress = (Address) clonedEmployee.getAddress().clone();
      clonedEmployee.setAddress(clonedAddress);
      return clonedEmployee;
    }

    @Override
    public String toString() {
      return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + "]";
    }

  }

  private static class Address implements Cloneable {
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
    protected Object clone() throws CloneNotSupportedException {
      return super.clone();
    }

    @Override
    public String toString() {
      return "Address [city=" + city + ", country=" + country + "]";
    }

  }
}
