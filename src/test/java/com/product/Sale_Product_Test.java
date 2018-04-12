package com.product;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {Sale_Product_Test.class})
@ContextConfiguration(classes = {Main.class})
public class Sale_Product_Test {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_a_Create() throws Exception {

        this.mockMvc.perform(post("/Sales/products/create/product88").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string(containsString("product88")));

        this.mockMvc.perform(post("/Sales/products/create/product99").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string(containsString("product99")));
    }

    @Test
    public void test_b_Get() throws Exception {

        this.mockMvc.perform(get("/Sales/products"))
                .andDo(print())
                .andExpect(content().string(containsString("product99")));
    }

    @Test
    public void test_c_Update() throws Exception {

        this.mockMvc.perform(post("/Sales/products/update").contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "        \"productName\": \"product100\",\n" +
                "        \"productID\":1\n" +
                "}"))
                .andDo(print())
                .andExpect(content().string(containsString("product100")));
    }

    @Test
    public void test_d_Delete() throws Exception {

        this.mockMvc.perform(delete("/Sales/products/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test_e_Get() throws Exception {

        this.mockMvc.perform(get("/Sales/products/1"))
                .andDo(print())
                .andExpect(content().string(containsString("product100")));
    }






    @Test
    public void test_f_Create() throws Exception {

        this.mockMvc.perform(post("/Sales/create").contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "\t\"saleID\":600,\n" +
                "\t\"saleDate\":\"2011-08-21T18:02:52.249Z\",\n" +
                "\t\"saleAmount\":800,\n" +
                "\t\"product\":\n" +
                "\t\t{\n" +
                "        \"productID\": \"1\"\n" +
                "      }\n" +
                "}"))
                .andDo(print())
                .andExpect(content().string(containsString("800")));

        this.mockMvc.perform(post("/Sales/create").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"saleID\":800,\n" +
                        "\t\"saleDate\":\"2011-08-21T18:02:52.249Z\",\n" +
                        "\t\"saleAmount\":800,\n" +
                        "\t\"product\":\n" +
                        "\t\t{\n" +
                        "        \"productID\": \"1\"\n" +
                        "      }\n" +
                        "}"))
                .andDo(print())
                .andExpect(content().string(containsString("800")));
    }

    @Test
    public void test_g_Get() throws Exception {

        this.mockMvc.perform(get("/Sales"))
                .andDo(print())
                .andExpect(content().string(containsString("product100")));
    }

    @Test
    public void test_h_Update() throws Exception {

        this.mockMvc.perform(post("/Sales/update").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"saleID\":1,\n" +
                        "\t\"saleDate\":\"2011-08-21T18:02:52.249Z\",\n" +
                        "\t\"saleAmount\":700,\n" +
                        "\t\"product\":\n" +
                        "\t\t{\n" +
                        "        \"productID\": \"1\"\n" +
                        "      }\n" +
                        "}"))
                .andDo(print())
                .andExpect(content().string(containsString("700")));
    }

    @Test
    public void test_i_Delete() throws Exception {

        this.mockMvc.perform(delete("/Sales/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test_j_Get() throws Exception {

        this.mockMvc.perform(get("/Sales/2"))
                .andDo(print())
                .andExpect(content().string(containsString("product100")));
    }

    @Test
    public void test_k_Get() throws Exception {

        this.mockMvc.perform(get("/Sales/products/sales_amount/1"))
                .andDo(print())
                .andExpect(content().string(containsString("800")));
    }

}