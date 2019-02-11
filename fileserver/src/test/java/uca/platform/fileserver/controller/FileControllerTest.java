package uca.platform.fileserver.controller;

import org.apache.http.entity.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uca.platform.StdStringUtils;
import uca.platform.fileserver.CustomizationConfiguration;
import uca.platform.fileserver.FileServerConfiguration;
import uca.platform.fileserver.domain.FileItemInfo;
import uca.platform.fileserver.service.FileUploadService;
import uca.platform.json.StdObjectMapper;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uca.platform.fileserver.CustomizationConfiguration.mockUserInfoTokenServices;
import static uca.platform.fileserver.CustomizationConfiguration.restDocument;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@Import(CustomizationConfiguration.class)
public class FileControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    StdObjectMapper stdObjectMapper;

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    FileServerConfiguration fileServerConfiguration;

    @MockBean
    UserInfoTokenServices userInfoTokenServices;

    @Before
    public void setUp() {
        mockUserInfoTokenServices(userInfoTokenServices);
    }


    @Test
    public void fileUpload() throws Exception {
        String content = "example";
        MockMultipartFile file = new MockMultipartFile("file", "example.txt", ContentType.DEFAULT_BINARY.getMimeType(), content.getBytes());
        MvcResult result = this.mockMvc.perform(
                RestDocumentationRequestBuilders.fileUpload("/file").file(file)
                        .header("Authorization", "Bearer " + StdStringUtils.uuid())
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andDo(restDocument(
                        requestHeaders(
                                headerWithName("Authorization").description("Oauth2 access token")
                        )
                        , requestParts(
                                partWithName("file").description("The file to upload")
                        )
                        , responseFields(
                                fieldWithPath("filePath").description("The path of the file")
                        )
                )).andReturn();
        String jsonFileItemInfo = result.getResponse().getContentAsString();

        FileItemInfo fileItemInfo = stdObjectMapper.fromJson(jsonFileItemInfo, FileItemInfo.class);
        assertNotNull(fileItemInfo);
        String uri = fileItemInfo.getFilePath().replace(this.fileServerConfiguration.getUrl(), "");
        this.mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(content().string(content));
        File realFile = fileUploadService.asFile(fileItemInfo.getFilePath());
        assertNotNull(realFile);
        assertTrue(realFile.exists());
        assertTrue(realFile.delete());
    }

    @Test
    public void fileUploadWithoutBearer() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "example.txt", ContentType.DEFAULT_BINARY.getMimeType(), "example".getBytes());
        this.mockMvc.perform(
                RestDocumentationRequestBuilders.fileUpload("/file").file(file)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isUnauthorized())
        ;
    }
}
