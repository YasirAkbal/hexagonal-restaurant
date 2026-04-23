package com.yasirakbal.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yasirakbal.configuration.bootstrap.DataSeeder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.yml")
class PlaceOrderSystemTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql("classpath:PlaceOrderSystemTest.sql")
    void placeOrder() throws Exception {
        BigDecimal burgerPrice = burgerPriceFromCatalog();

        ResponseEntity<String> response = whenPlaceOrderOneBurger();

        then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        JsonNode created = bodyAsJson(response);
        UUID orderId = orderId(created);
        then(created.get("status").asText()).isEqualTo("PENDING");
        then(created.get("totalAmount").decimalValue()).isEqualByComparingTo(burgerPrice);

        ResponseEntity<String> loaded = whenGetOrder(orderId);
        then(loaded.getStatusCode()).isEqualTo(HttpStatus.OK);

        JsonNode loadedBody = bodyAsJson(loaded);
        then(orderId(loadedBody)).isEqualTo(orderId);
        then(loadedBody.get("totalAmount").decimalValue()).isEqualByComparingTo(burgerPrice);
    }

    private static BigDecimal burgerPriceFromCatalog() {
        return new BigDecimal("120.00");
    }

    private ResponseEntity<String> whenPlaceOrderOneBurger() {
        String body = placeOrderJson(DataSeeder.TABLE_1_ID, DataSeeder.MENU_ITEM_BURGER_ID, 1);
        return restTemplate.exchange(
                "/api/orders",
                HttpMethod.POST,
                new HttpEntity<>(body, jsonHeaders()),
                String.class
        );
    }

    private ResponseEntity<String> whenGetOrder(UUID orderId) {
        return restTemplate.getForEntity("/api/orders/" + orderId, String.class);
    }

    private static String placeOrderJson(UUID tableId, UUID menuItemId, int quantity) {
        return """
                {
                  "tableId": "%s",
                  "orderItems": [
                    { "menuItemId": "%s", "quantity": %d }
                  ]
                }
                """.formatted(tableId, menuItemId, quantity);
    }

    private static HttpHeaders jsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private JsonNode bodyAsJson(ResponseEntity<String> response) throws Exception {
        return objectMapper.readTree(response.getBody());
    }

    private static UUID orderId(JsonNode body) {
        return UUID.fromString(body.get("orderId").asText());
    }
}
