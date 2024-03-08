package awsProject.awsProject.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Boss extends BaseEntity {

    private String title;

    @JsonManagedReference
    @OneToMany(mappedBy = "boss", cascade = CascadeType.ALL)
    private List<Slave> slaves;
    @Override
    public String toString() {
        return "Boss{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                '}';
    }
}
