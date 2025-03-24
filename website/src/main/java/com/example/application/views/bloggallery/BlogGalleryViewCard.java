package com.example.application.views.bloggallery;

import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Background;
import com.vaadin.flow.theme.lumo.LumoUtility.BorderRadius;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

public class BlogGalleryViewCard extends ListItem {
    
    public BlogGalleryViewCard(String title, String subtitle, String description, String date, String uuid) {
        addClassNames(Background.CONTRAST_5, Display.FLEX, FlexDirection.COLUMN, AlignItems.START, Padding.MEDIUM,
                BorderRadius.LARGE);
        
        Span header = new Span();
        header.addClassNames(FontSize.XLARGE, FontWeight.SEMIBOLD);
        header.setText(title);
        
        Span subtitleSpan = new Span();
        subtitleSpan.addClassNames(FontSize.SMALL, TextColor.SECONDARY);
        subtitleSpan.setText(subtitle);
        
        Paragraph descParagraph = new Paragraph(description);
        descParagraph.addClassName(Margin.Vertical.MEDIUM);
        
        Span dateBadge = new Span();
        dateBadge.getElement().setAttribute("theme", "badge");
        dateBadge.setText("Date: " + date);
        
        Span uuidBadge = new Span();
        uuidBadge.getElement().setAttribute("theme", "badge");
        uuidBadge.setText("ID: " + uuid.substring(0, 8) + "..."); // Showing only first 8 chars for brevity
        
        add(header, subtitleSpan, descParagraph, dateBadge, uuidBadge);
    }
}