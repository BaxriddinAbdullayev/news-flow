package novares.uz.domain.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.Auditable;

@Getter
@Setter
@Entity
@Table(name = "tag")
public class Tag extends Auditable<Long> {

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "is_active")
    private Boolean active = Boolean.TRUE;
}
