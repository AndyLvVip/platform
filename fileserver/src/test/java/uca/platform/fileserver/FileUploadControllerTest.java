package uca.platform.fileserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.fileUpload;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uca.platform.fileserver.CustomizationConfiguration.restDocument;

/**
 * Created by @author andy
 * On @date 19-1-25 下午11:38
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@Import(CustomizationConfiguration.class)
public class FileUploadControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void upload() throws Exception {
        this.mockMvc.perform(fileUpload("/file/upload").file("file", "example".getBytes()).param("filePath", "test"))
                .andExpect(status().isOk())
        .andDo(restDocument(requestParts(
                partWithName("file").description("The file to upload")
        )))

        ;
    }
}
