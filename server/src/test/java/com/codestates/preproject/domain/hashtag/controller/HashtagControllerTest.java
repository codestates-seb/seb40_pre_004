package com.codestates.preproject.domain.hashtag.controller;

import com.codestates.preproject.domain.hashtag.controller.HashtagController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@WebMvcTest(HashtagController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class HashtagControllerTest {

    @Test
    void postHashtag() {
    }

    @Test
    void patchHashtag() {
    }

    @Test
    void getHashtag() {
    }

    @Test
    void getHashtags() {
    }

    @Test
    void deleteHashtag() {
    }
}