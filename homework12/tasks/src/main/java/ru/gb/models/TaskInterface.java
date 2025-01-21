package ru.gb.models;

public interface TaskInterface {
    boolean isUrgent();


    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

}
