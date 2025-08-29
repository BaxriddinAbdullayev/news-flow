package novares.uz.controller;

import novares.uz.dto.AppResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface GenericController<D, C> {

    ResponseEntity<AppResponse<D>> get(@PathVariable(value = "id") Long id);

    ResponseEntity<AppResponse<Page<D>>> list(C criteria);
}