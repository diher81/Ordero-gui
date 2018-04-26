package com.switchfully.vaadin.ordergui.webapp;

import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class OrderGUI extends UI {

    private ItemResource itemResource;

    @Autowired
    public OrderGUI(ItemResource itemResource) {
        this.itemResource = itemResource;
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout mainLayout = new VerticalLayout();
        addTitleLabel(mainLayout);
        renderItems(mainLayout);
        setContent(mainLayout);
    }

    private void renderItems(VerticalLayout mainLayout) {
        itemResource.getItems()
                .forEach(item ->
                        mainLayout.addComponent(
                                new HorizontalLayout(
                                        new Label("--> " + item.name + " €" + item.price))));
    }

    private void addTitleLabel(VerticalLayout mainLayout) {
        mainLayout.addComponent(
                new HorizontalLayout(
                        new Label("ITEMS:")
                )
        );
    }

}