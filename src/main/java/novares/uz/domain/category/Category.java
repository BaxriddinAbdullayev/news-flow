package novares.uz.domain.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.Auditable;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends Auditable<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @Column(name = "is_active")
    private Boolean active = Boolean.TRUE;
}
