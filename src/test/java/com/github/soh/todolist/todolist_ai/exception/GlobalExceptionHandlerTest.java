package com.github.soh.todolist.todolist_ai.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    // 400 Bad Request (잘못된 요청) - 데이터 유효성 문제
    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void shouldReturn400WhenIllegalArgumentExceptionOccurs() throws Exception {
        mockMvc.perform(get("/tasks/-1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."));
    }

    // 404 Not Found (존재하지 않는 리소스)
    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void shouldReturn404WhenEntityNotFound() throws Exception {
        mockMvc.perform(get("/tasks/99999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("해당 ID의 Task가 없습니다."));
    }

    // 400 Bad Request ( 잘못된 데이터 타입 요청 )

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void shouldReturn400WhenTypeMismatchOccurs() throws Exception {
        mockMvc.perform(get("/tasks/abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value("잘못된 요청 형식입니다"));
    }

    // 405 Not ALlowed Method ( 허용되지 않은 메소드 요청 )
    @Test
    @WithMockUser(username= "testUser", roles = {"USER"})
    public void shouldReturn405WhenMethodNotSupported() throws Exception {
        mockMvc.perform(post("/tasks/1"))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(jsonPath("$.error").value("Method Not Allowed"))
                .andExpect(jsonPath("$.message").value("허용되지 않은 메서드입니다."));
    }

    // 400 필수 파라미터 누락
    @Test
    @WithMockUser(username= "testUser", roles = {"USER"})
    public void sholdReturn400WhenRequiredParameterIsMissing() throws Exception {
        mockMvc.perform(get("/tasks/"));
    }

    // 500 Internal Server Error(예상치 못한 서버 오류)
}
