package com.switchfully.vaadin.ordergui.webapp.views.items;

import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ItemOverviewView extends CustomComponent implements View {

    private final VerticalLayout mainLayout;

    public ItemOverviewView(ItemResource itemResource) {
        this.mainLayout = new VerticalLayout();

        itemResource.getItems()
                .forEach(item ->
                        mainLayout.addComponent(
                                new HorizontalLayout(
                                        new Label("--> " + item.name + " €" + item.price))));

        mainLayout.addComponent(
                new HorizontalLayout(
                        new Label("ITEMS:")
                )
        );

        setCompositionRoot(mainLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
