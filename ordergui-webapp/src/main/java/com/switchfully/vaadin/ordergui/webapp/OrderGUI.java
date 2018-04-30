package com.switchfully.vaadin.ordergui.webapp;

import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.switchfully.vaadin.ordergui.webapp.views.items.CreateItemView;
import com.switchfully.vaadin.ordergui.webapp.views.items.ItemOverviewView;
import com.switchfully.vaadin.ordergui.webapp.views.items.UpdateItemView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
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

    public static final String VIEW_ITEM_OVERVIEW = "";
    public static final String VIEW_ITEM_CREATE = "items";
    public static final String VIEW_ITEM_UPDATE = "update";

    private Navigator navigator;

    @Autowired
    public OrderGUI(ItemResource itemResource) {
        this.itemResource = itemResource;
    }

    @Override
    protected void init(VaadinRequest request) {
        navigator = new Navigator(this, this);

        navigator.addView(VIEW_ITEM_OVERVIEW, new ItemOverviewView(itemResource));
        navigator.addView(VIEW_ITEM_CREATE, new CreateItemView(itemResource));
        navigator.addView(VIEW_ITEM_UPDATE, new UpdateItemView(itemResource));
    }

}