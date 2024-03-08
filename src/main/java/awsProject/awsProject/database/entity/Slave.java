package awsProject.awsProject.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Slave extends BaseEntity {

    private String nationality;
    private int age;
    private boolean efficient;
    private boolean obedient;

    @ManyToOne
    @JsonBackReference
    private Boss boss;
}