package com.switchfully.vaadin.ordergui.interfaces.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ItemResource {

    private RestTemplate restTemplate;

    @Autowired
    public ItemResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Item> getItems() {
        Item[] items = restTemplate.getForObject("http://localhost:9000/items", Item[].class);
        return Arrays.asList(items);
    }

    public Item saveItems(Item item) {
        Item item1 = restTemplate.postForObject("http://localhost:9000/items", item, Item.class);
        return item1;

        //Alternative:
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        String url = "http://localhost:9000/items";
//
//        HttpEntity<Item> requestEntity = new HttpEntity<>(item, headers);
//
//        ResponseEntity<Item> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Item.class);
//        exchange;
    }

    public Item getById(String id){
        return getItems().stream()
                .filter(item -> item.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
