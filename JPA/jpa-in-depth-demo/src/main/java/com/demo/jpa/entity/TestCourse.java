package com.demo.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "course")
public class TestCourse implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Transient
  // Is it a new reservation
  private boolean isNew;
  @Transient
  // Can we replace the accommodation unit with another one for this reservation
  private boolean changeable = true;
  @Transient
  // Have we changed the accommodation unit for this reservation
  private boolean isChanged;
  @Column(name = "marks")
  private Integer marks;

  public TestCourse() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("%nCourse [id=%s, name=%s, marks=%s]", id, name, marks);
  }

  public Integer getMarks() {
    return marks;
  }

  public void setMarks(Integer marks) {
    this.marks = marks;
  }

  public boolean isNew() {
    return isNew;
  }

  public void setNew(boolean isNew) {
    this.isNew = isNew;
  }

  public boolean isChangeable() {
    return changeable;
  }

  public void setChangeable(boolean changeable) {
    this.changeable = changeable;
  }

  public boolean isChanged() {
    return isChanged;
  }

  public void setChanged(boolean isChanged) {
    this.isChanged = isChanged;
  }

}
