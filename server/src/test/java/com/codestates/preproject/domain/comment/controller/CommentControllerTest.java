package com.codestates.preproject.domain.comment.controller;

import com.codestates.preproject.domain.comment.controller.CommentController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@WebMvcTest(CommentController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class CommentControllerTest {

    @Test
    void postComment() {
    }

    @Test
    void patchComment() {
    }

    @Test
    void getComment() {
    }

    @Test
    void getComments() {
    }

    @Test
    void deleteComment() {
    }
}