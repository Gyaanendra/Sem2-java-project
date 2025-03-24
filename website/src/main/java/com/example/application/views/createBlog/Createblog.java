package com.example.application.views.createBlog;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Blog Create")
@Menu(order = 2, icon = LineAwesomeIconUrl.TH_LIST_SOLID)
@Route(value = "createBlog", layout = MainLayout.class)
@CssImport("./styles/create-blog.css")
public class Createblog extends VerticalLayout {

    private final TextField title = new TextField("Title");
    private final TextField subtitle = new TextField("Subtitle");
    private final TextArea description = new TextArea("Description");
    private final Button save = new Button("Publish");
    private final Button cancel = new Button("Cancel");
    private final Binder<BlogPost> binder = new Binder<>(BlogPost.class);

    public Createblog() {
        setClassName("create-blog-view");
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        setupBinder();
        setupListeners();
        clearForm();
    }

    private void setupBinder() {
        binder.forField(title)
            .asRequired("Title is required")
            .bind(BlogPost::getTitle, BlogPost::setTitle);
        binder.forField(subtitle)
            .bind(BlogPost::getSubtitle, BlogPost::setSubtitle);
        binder.forField(description)
            .asRequired("Description is required")
            .bind(BlogPost::getDescription, BlogPost::setDescription);
    }

    private void setupListeners() {
        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> savePost());
    }

    private void savePost() {
        if (!binder.validate().isOk()) {
            showErrorNotification("Please fill all required fields");
            return;
        }

        BlogPost post = binder.getBean();
        String json = String.format(
            "{\"title\":\"%s\",\"subtitle\":\"%s\",\"description\":\"%s\"}",
            post.getTitle(), post.getSubtitle(), post.getDescription()
        );

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:6969/api/save_posts"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    getUI().ifPresent(ui -> ui.access(() -> {
                        if (response.statusCode() == 200 || response.statusCode() == 201) {
                            showSuccessNotification("Blog post saved successfully!");
                            clearForm();
                        } else {
                            showErrorNotification("Failed to save post: " + response.body());
                        }
                    }));
                })
                .exceptionally(throwable -> {
                    getUI().ifPresent(ui -> ui.access(() -> 
                        showErrorNotification("Network error: " + throwable.getMessage())));
                    return null;
                });
        } catch (Exception ex) {
            showErrorNotification("Error saving post: " + ex.getMessage());
        }
    }

    private void showSuccessNotification(String message) {
        Notification notification = new Notification(message);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.setDuration(3000); // 3 seconds
        notification.setPosition(Notification.Position.TOP_END);
        notification.open();
    }

    private void showErrorNotification(String message) {
        Notification notification = new Notification(message);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setDuration(5000); // 5 seconds for errors
        notification.setPosition(Notification.Position.TOP_END);
        // Add close button for persistent error notifications
        Button closeButton = new Button("Close", click -> notification.close());
        notification.add(closeButton);
        notification.open();
    }

    private void clearForm() {
        binder.setBean(new BlogPost());
    }

    private Component createTitle() {
        return new H3("Create New Blog Post");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
        
        title.setWidth("100%");
        subtitle.setWidth("100%");
        description.setWidth("100%");
        description.setHeight("200px");

        formLayout.add(title, subtitle, description);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClassName("save-button");
        cancel.addClassName("cancel-button");
        
        buttonLayout.add(save, cancel);
        return buttonLayout;
    }

    public static class BlogPost {
        private String title = "";
        private String subtitle = "";
        private String description = "";

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getSubtitle() { return subtitle; }
        public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}