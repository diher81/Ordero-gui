package com.switchfully.vaadin.ordergui.webapp.views.items;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import org.springframework.web.client.HttpClientErrorException;

public class ItemForm extends FormLayout {

    private TextField name;
    private TextField description;
    private TextField price;
    private TextField amountOfStock;
    private HorizontalLayout buttons;
    private Button createBtn;
    private Button cancelBtn;
    private Button updateBtn;

    private Item item;
    private BeanFieldGroup<Item> beanFieldGroup;
    private ItemResource itemResource;

    public ItemForm(ItemResource itemResource) {
        this.name = createNameTxt();
        this.description = createDescriptionTxt();
        this.price = createPriceTxt();
        this.amountOfStock = createAmountOfStockTxt();
        this.createBtn = createCreateBtn();
        this.cancelBtn = cancelCancelBtn();

        buttons = new HorizontalLayout(createBtn, cancelBtn);

        buttons.setSpacing(true);
        this.itemResource = itemResource;

        addComponents(name, description, price, amountOfStock, buttons);
    }

    public ItemForm(ItemResource itemResource, String originView){
        this.name = createNameTxt();
        this.description = createDescriptionTxt();
        this.price = createPriceTxt();
        this.amountOfStock = createAmountOfStockTxt();
        this.cancelBtn = cancelCancelBtn();
        this.updateBtn = createUpdateBtn();

        buttons = new HorizontalLayout(updateBtn, cancelBtn);

        buttons.setSpacing(true);
        this.itemResource = itemResource;

        addComponents(name, description, price, amountOfStock, buttons);
    }

    private Button createUpdateBtn() {
        Button updateBtn = new Button("Update");
        updateBtn.addClickListener(event -> updateItem());
        return updateBtn;
    }

    private void updateItem() {
        itemResource.updateItem(item);
    }

    private Button cancelCancelBtn() {
        Button cancelBtn = new Button("Cancel");

        return cancelBtn;
    }

    private Button createCreateBtn() {
        Button createBtn = new Button("Create");
        createBtn.addClickListener(e -> saveItem());
        return createBtn;
    }

    private void saveItem() throws HttpClientErrorException {
        setItem(item);
        Item itemResult = itemResource.saveItems(item);
        conjureUpNotification(itemResult);
    }

    private void conjureUpNotification(Item itemResult) {
        try {
            if (itemResult.getId() != null) {
                generateNotification(
                        "Success",
                        "<br/>The new item %s was added to the database",
                        item.getName(),
                        Notification.Type.HUMANIZED_MESSAGE
                );
            }
        } catch (HttpClientErrorException ex){
            generateNotification(
                    "Error!",
                    "<br/>Impossible to add %s to database.",
                    item.getName(),
                    Notification.Type.ERROR_MESSAGE);

        }
    }

    private void generateNotification(String caption, String message, String itemName, Notification.Type type) {
        Notification notification = new Notification(caption,
                String.format(message, itemName),
                type, true);
        notification.setDelayMsec(-1);
        notification.show(Page.getCurrent());
    }

    private TextField createAmountOfStockTxt() {
        return new TextField("Amount of stock");
    }

    private TextField createPriceTxt() {
        TextField priceTxt = new TextField("Price");
        priceTxt.setIcon(FontAwesome.EURO);
        return priceTxt;
    }

    private TextField createDescriptionTxt() {
        return new TextField("Description");
    }

    private TextField createNameTxt() {
        return new TextField("Name");
    }

    public void setItem(Item item) {
        this.item = new Item(name.getValue(), description.getValue(), Float.parseFloat(price.getValue()), Integer.parseInt(amountOfStock.getValue()));
        BeanFieldGroup.bindFieldsUnbuffered(this.item, this);
    }

    public void setItem(String itemId) {
        this.item = Item.cloneItem(itemResource.getById(itemId));
        BeanFieldGroup.bindFieldsUnbuffered(this.item, this);
    }

}
