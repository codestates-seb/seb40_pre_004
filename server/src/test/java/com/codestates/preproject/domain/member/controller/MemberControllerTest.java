package com.codestates.preproject.domain.member.controller;

import com.codestates.preproject.domain.member.controller.MemberController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class MemberControllerTest {

    @Test
    void postMember() {
    }

    @Test
    void patchMember() {
    }

    @Test
    void getMember() {
    }

    @Test
    void getMembers() {
    }

    @Test
    void deleteMember() {
    }
}