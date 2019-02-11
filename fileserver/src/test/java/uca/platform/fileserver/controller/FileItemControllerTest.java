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
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import uca.platform.StdStringUtils;
import uca.platform.fileserver.CustomizationConfiguration;
import uca.platform.fileserver.domain.FileItemInfo;
import uca.platform.fileserver.service.FileUploadService;
import uca.platform.json.StdObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.fileUpload;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uca.platform.fileserver.CustomizationConfiguration.mockUserInfoTokenServices;
import static uca.platform.fileserver.CustomizationConfiguration.restDocument;
import static uca.platform.fileserver.service.ImageFileItemInfoService.*;

/**
 * Created by @author andy
 * On @date 19-1-25 下午11:38
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@Import(CustomizationConfiguration.class)
@Transactional
public class FileItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserInfoTokenServices userInfoTokenServices;

    @Autowired
    StdObjectMapper stdObjectMapper;

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    ResourceLoader resourceLoader;

    @Before
    public void setUp() {
        mockUserInfoTokenServices(userInfoTokenServices);
    }

    @Test
    public void fileItemUpload() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "example.txt", ContentType.DEFAULT_BINARY.getMimeType(), "example".getBytes());
        String jsonResult = this.mockMvc.perform(
                fileUpload("/fileItem/file").file(file)
                        .header("Authorization", "Bearer " + StdStringUtils.uuid())
                        .param("fileSrcRemark", "Unit Test")
                        .param("fileSetInfoId", StdStringUtils.uuid())
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
                        , requestParameters(
                                parameterWithName("fileSrcRemark").description("File source remark")
                                , parameterWithName("fileSetInfoId").description("Id of the file set info")
                        )
                        , responseFields(
                                fieldWithPath("id").description("File item info id")
                                , fieldWithPath("size").description("The size of the file")
                                , fieldWithPath("filePath").description("The path of the file")
                                , fieldWithPath("fileName").description("The name of the file")
                                , fieldWithPath("sequence").description("The sequence of the order")
                                , fieldWithPath("fileSetInfoId").description("Id of the file set info")
                                , fieldWithPath("createdOn").description("")
                                , fieldWithPath("createdBy").description("")
                                , fieldWithPath("updatedOn").description("")
                                , fieldWithPath("updatedBy").description("")
                                , fieldWithPath("version").description("")
                        )
                ))
        .andReturn().getResponse().getContentAsString()

        ;
        FileItemInfo fileItemInfo = stdObjectMapper.fromJson(jsonResult, FileItemInfo.class);

        assertNotNull(fileItemInfo);
        File destFile = this.fileUploadService.asFile(fileItemInfo.getFilePath());
        assertTrue(destFile.exists());
        assertTrue(destFile.delete());
    }

    @Test
    public void fileDelete() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "example.txt", ContentType.DEFAULT_BINARY.getMimeType(), "example".getBytes());
        String jsonResult = this.mockMvc.perform(
                fileUpload("/fileItem/file").file(file)
                        .param("fileSrcRemark", "Unit Test")
                        .param("fileSetInfoId", StdStringUtils.uuid())
                        .header("Authorization", "Bearer " + StdStringUtils.uuid())
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        FileItemInfo fileItemInfo = stdObjectMapper.fromJson(jsonResult, FileItemInfo.class);

        assertNotNull(fileItemInfo);
        File destFile = this.fileUploadService.asFile(fileItemInfo.getFilePath());
        assertTrue(destFile.exists());
        assertTrue(destFile.delete());

        FileItemInfo paramFileItem = new FileItemInfo();
        paramFileItem.setVersion(fileItemInfo.getVersion());
        this.mockMvc.perform(delete("/fileItem/file/{id}", fileItemInfo.getId())
                .header("Authorization", "Bearer " + StdStringUtils.uuid())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(stdObjectMapper.toJson(paramFileItem))
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isNoContent())
                .andDo(restDocument(
                        requestHeaders(
                                headerWithName("Authorization").description("Oauth2 access token")
                        )
                        , pathParameters(
                                parameterWithName("id").description("File Item Info Id")
                        )
                        , requestFields(
                                fieldWithPath("version").description("Version of current record")
                        )
                        )
                )
        ;

    }

    @Test
    public void fileItemImgUpload() throws Exception {
        File file = resourceLoader.getResource("classpath:srcfile.png").getFile();
        assertTrue(file.exists());

        MockMultipartFile mfile = new MockMultipartFile("file", "srcfile.png", ContentType.DEFAULT_BINARY.getMimeType(), new FileInputStream(file));

        String jsonFileItemInfo = this.mockMvc.perform(
                fileUpload("/fileItem/img").file(mfile)
                        .header("Authorization", "Bearer " + StdStringUtils.uuid())
                        .param("fileSrcRemark", "Unit Test")
                        .param("fileSetInfoId", StdStringUtils.uuid())
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
                        , requestParameters(
                                parameterWithName("fileSrcRemark").description("File source remark")
                                , parameterWithName("fileSetInfoId").description("Id of the file set info")
                        )
                        , responseFields(
                                fieldWithPath("id").description("File item info id")
                                , fieldWithPath("size").description("The size of the file")
                                , fieldWithPath("filePath").description("The path of the file")
                                , fieldWithPath("fileName").description("The name of the file")
                                , fieldWithPath("sequence").description("The sequence of the order")
                                , fieldWithPath("fileSetInfoId").description("Id of the file set info")
                                , fieldWithPath("createdOn").description("")
                                , fieldWithPath("createdBy").description("")
                                , fieldWithPath("updatedOn").description("")
                                , fieldWithPath("updatedBy").description("")
                                , fieldWithPath("version").description("")
                        )
                ))
                .andReturn().getResponse().getContentAsString()

                ;
        FileItemInfo fileItemInfo = stdObjectMapper.fromJson(jsonFileItemInfo, FileItemInfo.class);
        assertNotNull(fileItemInfo);
        File destFile = fileUploadService.asFile(fileItemInfo.getFilePath());
        assertTrue(destFile.exists());
        Path srcfilePath = Paths.get(destFile.getAbsolutePath()).getParent().resolve(this.fileUploadService.appendSuffix2Filename(destFile.getName(), SUFFIX_SRC));
        File srcfile = new File(srcfilePath.toString());
        assertTrue(srcfile.exists());

        Path file100Path = Paths.get(destFile.getAbsolutePath()).getParent().resolve(this.fileUploadService.appendSuffix2Filename(destFile.getName(), SUFFIX_100_100));
        File file100 = new File(file100Path.toString());
        assertTrue(file100.exists());

        Path file800Path = Paths.get(destFile.getAbsolutePath()).getParent().resolve(this.fileUploadService.appendSuffix2Filename(destFile.getName(), SUFFIX_800_800));
        File file800 = new File(file800Path.toString());
        assertTrue(file800.exists());

        assertTrue(destFile.delete());
        assertTrue(srcfile.delete());
        assertTrue(file100.delete());
        assertTrue(file800.delete());
    }
}
