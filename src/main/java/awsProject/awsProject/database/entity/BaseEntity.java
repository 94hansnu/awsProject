package awsProject.awsProject.database.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        } return super.hashCode();
    }
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BaseEntity)) {
            return false;
        }
        BaseEntity other = (BaseEntity) object;
        if (getId() != null) {
            return getId().equals(other.getId());
        }
        return super.equals(other);
    }

}