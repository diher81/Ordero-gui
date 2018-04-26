package com.switchfully.vaadin.ordergui.webapp.views.items;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.List;

public class ItemOverviewView extends CustomComponent implements View {

    private final VerticalLayout mainLayout;
    private final HorizontalLayout headerLayout;
    private final HorizontalLayout gridLayout;

    private final Label items;
    private final TextField itemFilter;
    private final Button btnFilter;
    private final Button btnNewItem;

    private final Grid grid;

    public ItemOverviewView(ItemResource itemResource) {
        this.items = createItemLabel();
        this.itemFilter = createItemFilter();
        this.btnFilter = createButtonFilter();
        this.btnNewItem = createButtonNewItem();

        this.grid = createGrid(itemResource);

        this.headerLayout = new HorizontalLayout(items, itemFilter, btnFilter, btnNewItem);
        headerLayout.setSpacing(true);
        this.gridLayout = new HorizontalLayout(grid);
        this.mainLayout = new VerticalLayout(headerLayout, gridLayout);
        mainLayout.setSpacing(true);

//        itemResource.getItems()
//                .forEach(item ->
//                        mainLayout.addComponent(
//                                new HorizontalLayout(
//                                        new Label("--> " + item.name + " €" + item.price))));

//        mainLayout.addComponent(
//                new HorizontalLayout(
//                        new Label("ITEMS:")
//                )
//        );

        setCompositionRoot(mainLayout);
    }

    private Grid createGrid(ItemResource itemResource) {
        Grid grid = new Grid();

        List<Item> itemList = itemResource.getItems();

        BeanItemContainer<Item> container =
                new BeanItemContainer<>(Item.class, itemList);

        grid.setColumns("name", "description");

        grid.setContainerDataSource(container);

        return grid;
    }

    private Button createButtonNewItem() {
        Button btn = new Button("New Item");
        return btn;
    }

    private Button createButtonFilter() {
        Button btn = new Button("Filter");
        return btn;
    }

    private TextField createItemFilter() {
        TextField textField = new TextField();
        textField.setInputPrompt("Filter by name");
        return textField;
    }

    private Label createItemLabel() {
        return new Label("Items");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
