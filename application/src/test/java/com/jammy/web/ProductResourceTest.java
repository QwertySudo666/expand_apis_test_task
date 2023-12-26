//package com.jammy.web;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jammy.domain.models.ProductRecord;
//import com.jammy.domain.models.ProductRequest;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class ProductResourceTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private TestRestTemplate restTemplate = new TestRestTemplate();
//
//    @Test
//    public void testAddProduct() throws Exception {
//        // Prepare test data
//        ProductRequest productRequest = new ProductRequest(/* your data */);
//        List<ProductRecord> expectedResult = Arrays.asList(/* your expected data */);
//
//        // Perform the request and assert the response
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/products/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(productRequest)))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect((ResultMatcher) jsonPath("$").isArray())
//                .andExpect((ResultMatcher) jsonPath("$[0].property").value("expectedValue")); // Adjust this based on your expected response
//
//        // You may want to add additional assertions or verifications here
//    }
//
//    @Test
//    public void testFetchAllProducts() throws Exception {
//        restTemplate.mockMvc.perform(SecurityMoc)
//
//        // Perform the request and assert the response
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/all"))
//                .andExpect(status().isOk());
////                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
////                .andExpect((ResultMatcher) jsonPath("$").isArray())
////                .andExpect((ResultMatcher) jsonPath("$[0].property").value("expectedValue")); // Adjust this based on your expected response
//// You may want to add additional assertions or verifications here
//    }
//
//    // Helper method to convert objects to JSON string
//    private String asJsonString(Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}