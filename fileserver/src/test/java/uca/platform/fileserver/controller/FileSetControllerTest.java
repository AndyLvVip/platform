package uca.platform.fileserver.controller;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uca.platform.StdStringUtils;
import uca.platform.fileserver.CustomizationConfiguration;
import uca.platform.fileserver.domain.FileItemInfo;
import uca.platform.fileserver.repository.FileItemInfoRepository;
import uca.platform.fileserver.service.FileUploadService;
import uca.platform.json.StdObjectMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uca.platform.fileserver.CustomizationConfiguration.mockUserInfoTokenServices;
import static uca.platform.fileserver.CustomizationConfiguration.restDocument;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@Import(CustomizationConfiguration.class)
public class FileSetControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    StdObjectMapper stdObjectMapper;

    @Autowired
    FileUploadService fileUploadService;

    @MockBean
    UserInfoTokenServices userInfoTokenServices;

    @MockBean
    FileItemInfoRepository fileItemInfoRepository;

    FileItemInfo fileItemInfo1;
    FileItemInfo fileItemInfo2;

    @Before
    public void setUp() {
        mockUserInfoTokenServices(userInfoTokenServices);
        fileItemInfo1 = new FileItemInfo();
        fileItemInfo1.setId(StdStringUtils.uuid());
        fileItemInfo1.setSequence(1);
        fileItemInfo1.setSize(1024L);
        fileItemInfo1.setFileName("example.doc");
        fileItemInfo1.setFilePath("http://localhost:7010/media/f02/20190209/e34e95af-1842-4ed9-b719-f257ddc85fac.doc");
        fileItemInfo1.setFileSetInfoId(StdStringUtils.uuid());
        fileItemInfo1.setVersion(1);
        fileItemInfo1.setCreatedBy("Unit Test");
        fileItemInfo1.setUpdatedBy("Unit Test");
        LocalDateTime now = LocalDateTime.now();
        fileItemInfo1.setCreatedOn(now);
        fileItemInfo1.setUpdatedOn(now);


        fileItemInfo2 = new FileItemInfo();
        fileItemInfo2.setId(StdStringUtils.uuid());
        fileItemInfo2.setSequence(1);
        fileItemInfo2.setSize(1024L);
        fileItemInfo2.setFileName("example.doc");
        fileItemInfo2.setFilePath("http://localhost:7010/media/f02/20190209/e34e95af-1842-4ed9-b719-f257ddc85fac.doc");
        fileItemInfo2.setFileSetInfoId(StdStringUtils.uuid());
        fileItemInfo2.setVersion(1);
        fileItemInfo2.setCreatedBy("Unit Test");
        fileItemInfo2.setUpdatedBy("Unit Test");
        now = LocalDateTime.now();
        fileItemInfo2.setCreatedOn(now);
        fileItemInfo2.setUpdatedOn(now);
        List<FileItemInfo> fileItems = Arrays.asList(
                fileItemInfo1
                , fileItemInfo2
        );
        when(fileItemInfoRepository.fetchByFileSetIds(anyList())).thenReturn(fileItems);
        when(fileItemInfoRepository.fetchFirstFileInEachGroup(anyList())).thenReturn(fileItems);
    }

    @Test
    public void fetchItems() throws Exception {
        this.mockMvc.perform(
                get("/fileSet/fileItems")
                        .header("Authorization", "Bearer " + StdStringUtils.uuid())
                        .param("fileSetInfoIds", fileItemInfo1.getFileSetInfoId())
                        .param("fileSetInfoIds", fileItemInfo2.getFileSetInfoId())
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andDo(restDocument(
                        requestHeaders(
                                headerWithName("Authorization").description("Oauth2 access token")
                        )
                        , requestParameters(
                                parameterWithName("fileSetInfoIds").description("File Set Info Ids")
                                , parameterWithName("fileSetInfoIds").description("File Set Info Ids")
                        )
                        , responseFields(
                                fieldWithPath("[].id").description("File item info id")
                                , fieldWithPath("[].size").description("The size of the file")
                                , fieldWithPath("[].filePath").description("The path of the file")
                                , fieldWithPath("[].fileName").description("The name of the file")
                                , fieldWithPath("[].sequence").description("The sequence of the order")
                                , fieldWithPath("[].fileSetInfoId").description("Id of the file set info")
                                , fieldWithPath("[].createdOn").description("")
                                , fieldWithPath("[].createdBy").description("")
                                , fieldWithPath("[].updatedOn").description("")
                                , fieldWithPath("[].updatedBy").description("")
                                , fieldWithPath("[].version").description("")
                        )
                ))

        ;
    }

    @Test
    public void fetchFirstFileInEachGroup() throws Exception {
        this.mockMvc.perform(
                get("/fileSet/fetchFirstItemInEachGroup")
                        .header("Authorization", "Bearer " + StdStringUtils.uuid())
                        .param("fileSetInfoIds", fileItemInfo1.getFileSetInfoId())
                        .param("fileSetInfoIds", fileItemInfo2.getFileSetInfoId())
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andDo(restDocument(
                        requestHeaders(
                                headerWithName("Authorization").description("Oauth2 access token")
                        )
                        , requestParameters(
                                parameterWithName("fileSetInfoIds").description("File Set Info Ids")
                                , parameterWithName("fileSetInfoIds").description("File Set Info Ids")
                        )
                        , responseFields(
                                fieldWithPath("[].id").description("File item info id")
                                , fieldWithPath("[].size").description("The size of the file")
                                , fieldWithPath("[].filePath").description("The path of the file")
                                , fieldWithPath("[].fileName").description("The name of the file")
                                , fieldWithPath("[].sequence").description("The sequence of the order")
                                , fieldWithPath("[].fileSetInfoId").description("Id of the file set info")
                                , fieldWithPath("[].createdOn").description("")
                                , fieldWithPath("[].createdBy").description("")
                                , fieldWithPath("[].updatedOn").description("")
                                , fieldWithPath("[].updatedBy").description("")
                                , fieldWithPath("[].version").description("")
                        )
                ))

        ;
    }
}
