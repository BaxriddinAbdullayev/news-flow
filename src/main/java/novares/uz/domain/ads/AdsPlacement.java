package novares.uz.domain.ads;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import novares.uz.domain.Auditable;

@Getter
@Setter
@Entity
@Table(name = "ads_placement")
public class AdsPlacement extends Auditable<Long> {

    @Column(name = "code", length = 50, nullable = false, unique = true)
    private String code;

    @Column(name = "title")
    @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_active")
    private Boolean active = Boolean.TRUE;
}
