package org.refact4j.model;

public class DummyBean {
    private Integer id;

    private Double value;

    private String name;

    public DummyBean() {
    }

    public Double getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
