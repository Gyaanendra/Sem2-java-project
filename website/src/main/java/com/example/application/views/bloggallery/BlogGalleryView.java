package com.example.application.views.bloggallery;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

@PageTitle("Blog Gallery")
@Route("image-gallery")
@Menu(order = 1, icon = LineAwesomeIconUrl.GLOBE_AMERICAS_SOLID)
public class BlogGalleryView extends Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;

    public BlogGalleryView() {
        constructUI();
        imageContainer.add(new BlogGalleryViewCard("Snow covered mountain"));
        imageContainer.add(new BlogGalleryViewCard("Snow covered mountain"));
        imageContainer.add(new BlogGalleryViewCard("Snow covered mountain"));
        imageContainer.add(new BlogGalleryViewCard("Snow covered mountain"));
        imageContainer.add(new BlogGalleryViewCard("Snow coverred mountain"));
    }

    private void constructUI() {
        addClassNames("blog-gallery-view");
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);
        container.addClassName("gallery-header-container");

        VerticalLayout headerContainer = new VerticalLayout();
        headerContainer.setPadding(false);
        headerContainer.setSpacing(false);
        
        H2 header = new H2("Anonymous Blogs");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph description = new Paragraph("Royalty free photos and pictures, courtesy of Unsplash");
        description.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        headerContainer.add(header, description);

        Select<String> sortBy = new Select<>();
        sortBy.setLabel("Sort by");
        sortBy.setItems("Popularity", "Newest first", "Oldest first");
        sortBy.setValue("Popularity");

        imageContainer = new OrderedList();
        imageContainer.addClassNames(ListStyleType.NONE, Margin.NONE, Padding.NONE);
        imageContainer.addClassName("responsive-grid");

        container.add(headerContainer, sortBy);
        add(container, imageContainer);
    }
}