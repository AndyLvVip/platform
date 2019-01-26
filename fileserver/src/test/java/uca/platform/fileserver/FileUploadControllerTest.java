package uca.platform.fileserver;

import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.fileUpload;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
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
        MockMultipartFile file = new MockMultipartFile("file", "example.txt", ContentType.DEFAULT_BINARY.getMimeType(), "example".getBytes());
        this.mockMvc.perform(fileUpload("/file/upload").file(file).param("fileBaseCategory", "test").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(restDocument(requestParts(
                        partWithName("file").description("The file to upload")
                        ),
                        requestParameters(parameterWithName("fileBaseCategory").description("File base category"))
                        , responseFields(
                                fieldWithPath("id").description("File item info id")
                                , fieldWithPath("size").description("the size of the file")
                                , fieldWithPath("filePath").description("The path of the file")
                                , fieldWithPath("fileName").description("The name of the file")
                        )
                ))

        ;
    }
}
