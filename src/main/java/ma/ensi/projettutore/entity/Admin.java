package ma.ensi.projettutore.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@Entity
@EqualsAndHashCode(callSuper = true)
public class Admin extends User {

    public Admin() { super(); }

    public void manageUsers() {}
    public void addOffer() {}
    public void updateOffer() {}
    public void deleteOffer() {}
}