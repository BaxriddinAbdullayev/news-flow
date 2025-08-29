package novares.uz.controller.admin.file;

import lombok.AllArgsConstructor;
import novares.uz.dto.AppResponse;
import novares.uz.dto.file.ResourceFileDto;
import novares.uz.service.file.FileTempStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping(value = "${app.api.admin-path}", produces = "application/json")
public class FileTempStorageController {

    private final FileTempStorageService service;


    @RequestMapping(value = "/file/resource-file/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AppResponse<ResourceFileDto>> upload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(service.upload(file));
    }

    @RequestMapping(value = "/file/resource-file/download/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) {
        Resource resource = service.download(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

    @RequestMapping(value = "/file/resource-file/open/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<Resource> open(@PathVariable(value = "fileName") String fileName) {
        return service.open(fileName);
    }
}
