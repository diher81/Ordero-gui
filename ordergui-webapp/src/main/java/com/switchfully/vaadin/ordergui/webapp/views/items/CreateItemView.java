package com.switchfully.vaadin.ordergui.webapp.views.items;

import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

public class CreateItemView extends CustomComponent implements View {

    private final VerticalLayout mainLayout;

    private final Label newLabel;
    private final FormLayout newItemForm;

    public CreateItemView(ItemResource itemResource) {
        this.newLabel = createNewLabel();
        this.newItemForm = new ItemForm(itemResource);

        this.mainLayout = new VerticalLayout(newLabel, newItemForm);

        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);

        setCompositionRoot(mainLayout);
    }

    private void init(){
    }



    private Label createNewLabel() {
        return new Label("New item");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        init();
    }
}
