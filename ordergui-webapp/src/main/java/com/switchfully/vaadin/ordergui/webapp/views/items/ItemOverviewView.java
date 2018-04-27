package com.switchfully.vaadin.ordergui.webapp.views.items;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.switchfully.vaadin.ordergui.webapp.OrderGUI;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

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
        headerLayout.setSizeFull();
        this.gridLayout = new HorizontalLayout(grid);
        gridLayout.setSizeFull();
        gridLayout.setExpandRatio(grid, 1);
        this.mainLayout = new VerticalLayout(headerLayout, gridLayout);
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);

        setCompositionRoot(mainLayout);
    }

    private Grid createGrid(ItemResource itemResource) {
        Grid grid = new Grid();

        grid.setColumns("name", "description", "price", "amountOfStock");

        grid.getColumn("name").setHeaderCaption("Name");
        grid.getColumn("description").setHeaderCaption("Description");
        grid.getColumn("price").setHeaderCaption("Price");
        grid.getColumn("amountOfStock").setHeaderCaption("Amount of Stock");

        List<Item> itemList = itemResource.getItems();

        BeanItemContainer<Item> container =
                new BeanItemContainer<>(Item.class, itemList);

        grid.setContainerDataSource(container);
        grid.setSizeFull();

        return grid;
    }

    private Button createButtonNewItem() {
        Button btn = new Button("New Item");
        btn.setStyleName(ValoTheme.BUTTON_DANGER);
        btn.addClickListener(event -> getUI().getNavigator().navigateTo(OrderGUI.VIEW_ITEM_CREATE));
        return btn;
    }

    private Button createButtonFilter() {
        Button btn = new Button("Filter");
        btn.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        return btn;
    }

    private TextField createItemFilter() {
        TextField textField = new TextField();
        textField.setInputPrompt("Filter by name");
        return textField;
    }

    private Label createItemLabel() {
        Label label = new Label("Items");
        return label;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
