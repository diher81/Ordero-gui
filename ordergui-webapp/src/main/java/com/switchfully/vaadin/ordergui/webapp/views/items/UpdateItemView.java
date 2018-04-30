package com.switchfully.vaadin.ordergui.webapp.views.items;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

public class UpdateItemView extends CustomComponent implements View {

    private final VerticalLayout mainLayout;

    private final Label newLabel;
    private final FormLayout newItemForm;

    private Item item;

    private ItemResource itemResource;

    public UpdateItemView(ItemResource itemResource) {
        this.itemResource = itemResource;
        this.newLabel = createNewLabel();
        this.newItemForm = new ItemForm(itemResource);

        this.mainLayout = new VerticalLayout(newLabel, newItemForm);

        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);

        setCompositionRoot(mainLayout);
    }

    private void init(String itemId){
        this.item = Item.cloneItem(itemResource.getById(itemId));
        BeanFieldGroup<Item> itemBeanFieldGroup = BeanFieldGroup.bindFieldsUnbuffered(this.item, this);
        itemBeanFieldGroup.setItemDataSource(item);
    }

    private Label createNewLabel() {
        return new Label("Update item");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        init(event.getParameters());
    }
}
