package com.switchfully.vaadin.ordergui.webapp.views.items;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.switchfully.vaadin.ordergui.webapp.OrderGUI;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.data.util.filter.Or;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer;
import com.vaadin.ui.themes.ValoTheme;

import javax.swing.*;
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

        List<Item> itemList = itemResource.getItems();

        BeanItemContainer<Item> container =
                new BeanItemContainer<>(Item.class, itemList);

        GeneratedPropertyContainer gpc =
                new GeneratedPropertyContainer(container);

        gpc.addGeneratedProperty("editButton",
                new PropertyValueGenerator<String>() {

                    @Override
                    public String getValue(com.vaadin.data.Item item, Object itemId, Object propertyId) {
                        return "Edit";// The caption
                    }

                    @Override
                    public Class<String> getType() {
                        return String.class;
                    }
                });

        Grid grid = new Grid(gpc);

        grid.getColumn("editButton")
                .setRenderer(new ButtonRenderer(e -> {
                    BeanItem<Item> beanItem = container.getItem(e.getItemId());
                    getUI().getNavigator().navigateTo(String.format("%s/%s", OrderGUI.VIEW_ITEM_UPDATE, beanItem.getBean().getId()));
                }
                ));

        grid.setColumns("name", "description", "price", "amountOfStock", "editButton");

        grid.getColumn("name").setHeaderCaption("Name");
        grid.getColumn("description").setHeaderCaption("Description");
        grid.getColumn("price").setHeaderCaption("Price");
        grid.getColumn("amountOfStock").setHeaderCaption("Amount of Stock");

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
