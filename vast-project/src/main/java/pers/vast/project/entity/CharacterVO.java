package pers.vast.project.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.ToString;

/**
 * Created by sengzin on 2017/9/17.
 */
@ToString
public class CharacterVO {

    private Integer id;

    private Integer projectId;

    private String firstName;

    private String lastName;

    private Integer male;
    // json
    private JsonNode ability;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public Integer getMale() {
        return male;
    }

    public void setMale(Integer male) {
        this.male = male;
    }

    public JsonNode getAbility() {
        return ability;
    }

    public void setAbility(JsonNode ability) {
        this.ability = ability;
    }
}
