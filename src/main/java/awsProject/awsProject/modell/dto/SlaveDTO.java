package awsProject.awsProject.modell.dto;

import awsProject.awsProject.database.entity.Slave;
import lombok.Data;

@Data
public class SlaveDTO {

    private String nationality;
    private int age;
    private boolean efficient;
    private boolean obedient;
    private Long bossId;

    public SlaveDTO(Slave slave) {
        this.nationality = slave.getNationality();
        this.age = slave.getAge();
        this.efficient = slave.isEfficient();
        this.obedient = slave.isObedient();
        if (slave.getBoss() != null) {
            this.bossId = slave.getBoss().getId();
        }
    }
}
