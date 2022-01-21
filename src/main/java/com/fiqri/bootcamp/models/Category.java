package com.fiqri.bootcamp.models;

public class Category {
    private Category() {

    }

    public Category(Integer category_id, Integer department_id, String name,
                    String description) {
        this.category_id = category_id;
        this.department_id = department_id;
        this.name = name;
        this.description = description;
    }

    public Integer category_id;
    public Integer department_id;
    public String name;
    public String description;

    public void setcategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getcategory_id() {
        return category_id;
    }

    public Integer getdepartment_id() {
        return department_id;
    }

    public void setdepartment_id(Integer department_id) {
        this.department_id = department_id;

    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }


    public void setdescription(String description) {
        this.description = description;}



    public String getdescription() {
        return description;
    }

}
