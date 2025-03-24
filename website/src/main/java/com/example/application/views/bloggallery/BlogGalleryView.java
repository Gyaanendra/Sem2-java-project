package com.example.application.views.bloggallery;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
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

import elemental.json.JsonArray;
import elemental.json.JsonObject;

@PageTitle("Blog Gallery")
@Route("image-gallery")
@Menu(order = 1, icon = LineAwesomeIconUrl.GLOBE_AMERICAS_SOLID)
public class BlogGalleryView extends Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;
    private List<BlogPost> posts = new ArrayList<>();

    private static class BlogPost {
        String id;
        String createdAt;
        String title;
        String subtitle;
        String description;

        BlogPost(String id, String createdAt, String title, String subtitle, String description) {
            this.id = id;
            this.createdAt = createdAt;
            this.title = title;
            this.subtitle = subtitle;
            this.description = description;
        }
    }

    public BlogGalleryView() {
        constructUI();
        fetchPosts();
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

    private void fetchPosts() {
        UI.getCurrent().getPage().executeJs(
            "fetch('http://127.0.0.1:6969/api/posts', { " +
            "  method: 'GET', " +
            "  headers: { 'Content-Type': 'application/json' } " +
            "})" +
            ".then(response => { " +
            "  if (!response.ok) { " +
            "    throw new Error('Network response was not ok: ' + response.status); " +
            "  } " +
            "  return response.json(); " +
            "})" +
            ".then(data => $0.$server.handlePosts(data))" +
            ".catch(error => $0.$server.handleError(error.message))",
            getElement()
        );
    }

    @ClientCallable
    private void handlePosts(JsonArray postsArray) {
        posts.clear();
        imageContainer.removeAll();

        for (int i = 0; i < postsArray.length(); i++) {
            JsonObject post = postsArray.getObject(i);
            BlogPost blogPost = new BlogPost(
                post.getString("id"),
                post.getString("createdAt"),
                post.getString("title"),
                post.getString("subtitle"),
                post.getString("description")
            );
            posts.add(blogPost);
            imageContainer.add(new BlogGalleryViewCard(
                blogPost.title,
                blogPost.subtitle,
                blogPost.description,
                blogPost.createdAt,
                blogPost.id
            ));
        }
    }

    @ClientCallable
    private void handleError(String errorMessage) {
        Notification.show("Failed to fetch posts: " + errorMessage, 5000, Notification.Position.MIDDLE);
    }
}