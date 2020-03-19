package gr.gt.gvapi.entity;

import java.util.Arrays;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "User", indexes = @Index(columnList = "name"))
public class User {

    public static final String farRight = "Far Right";
    public static final String rightWing = "Right Wing";
    public static final String center = "Center";
    public static final String centerRight = "Center Right";
    public static final String centerLeft = "Center Left";
    public static final String leftWing = "Left Wing";

    public static final List<String> parties =
            Arrays.asList(farRight, rightWing, center, centerRight, centerLeft, leftWing);

    public static String party(String val) {
        switch (val) {
            case farRight:
                return "farRight";
            case rightWing:
                return "rightWing";
            case center:
                return "center";
            case centerRight:
                return "centerRight";
            case centerLeft:
                return "centerLeft";
            case leftWing:
                return "leftWing";
            default:
                throw new IllegalArgumentException();
        }
    }


    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private String category;
    private String party;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

}
