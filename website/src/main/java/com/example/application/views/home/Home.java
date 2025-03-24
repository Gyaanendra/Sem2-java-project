package com.example.application.views.home;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Home")
@Route("") 
@Menu(order = 0, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class Home extends Composite<VerticalLayout> {

    public Home() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        // Replace the empty icon with an Image for the logo
        Image logoImage = new Image("images/logo.png", "BU Logo");
        logoImage.setHeight("100px");
        logoImage.setWidth("200px");
        logoImage.addClassNames(LumoUtility.Margin.Right.SMALL);
        
        H1 h1 = new H1();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(Alignment.CENTER); // Changed to CENTER for better vertical alignment
        layoutRow.setJustifyContentMode(JustifyContentMode.START);
        h1.setText("BU Blogs");
        h1.setWidth("max-content");
        h1.addClassNames(LumoUtility.TextAlignment.CENTER);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        layoutColumn2.getStyle().set("flex-grow", "1");
        H1 heading1 = new H1("WeLcome to our webiste");
        heading1.addClassNames(LumoUtility.TextAlignment.CENTER);
        layoutColumn2.add(heading1);

        H2 aboutWebsiteHeader = new H2("About this Website");
        aboutWebsiteHeader.addClassNames(LumoUtility.Margin.Top.LARGE, LumoUtility.TextAlignment.CENTER);
        Paragraph aboutWebsiteText = new Paragraph(
            "We are a team of four passionate B.Tech Computer Science students who believe in the power of words and the freedom of expression. Our anonymous blogging platform was born out of a shared vision to create a safe, creative space where ideas can flow without judgment. Combining our technical skills and innovative spirit, we've built a website that empowers users to share their thoughts, stories, and experiences anonymously. Whether you're here to read, write, or simply explore, we're excited to have you join our community of free thinkers."
        );
        aboutWebsiteText.addClassNames(LumoUtility.TextColor.SECONDARY, LumoUtility.Margin.Bottom.LARGE);

        // Add About Us section
        H2 aboutUsHeader = new H2("About Us");
        aboutUsHeader.addClassNames(LumoUtility.Margin.Top.LARGE, LumoUtility.TextAlignment.CENTER);
        Paragraph aboutUsText = new Paragraph(
            "We are a group of four second-semester B.Tech Computer Science students, united by our curiosity and drive to apply what we've learned in the classroom to a real-world project. For our Semester 2 project, we developed an anonymous blogging website—a platform designed to encourage creativity and open expression while ensuring user privacy. This project reflects our collective skills in web development, teamwork, and problem-solving, as well as our commitment to creating something meaningful. From brainstorming the concept to coding the final product, this journey has been a hands-on exploration of technology and its potential to connect people."
        );
        aboutUsText.addClassNames(LumoUtility.TextColor.SECONDARY, LumoUtility.Margin.Bottom.LARGE);

        // Add the new sections to layoutColumn2
        layoutColumn2.add(aboutWebsiteHeader, aboutWebsiteText, aboutUsHeader, aboutUsText);

        getContent().add(layoutRow);
        layoutRow.add(logoImage); // Add the logo image instead of the empty icon
        layoutRow.add(h1);
        getContent().add(layoutRow2);
        layoutRow2.add(layoutColumn2);



        //
        //
        //
        //

        // Team section
        VerticalLayout teamSection = new VerticalLayout();
        teamSection.setPadding(false);
        teamSection.setSpacing(false);
        teamSection.setWidth("100%");
        
        H3 teamHeading = new H3("Team Behind this Project");
        teamHeading.addClassNames(LumoUtility.Margin.Bottom.MEDIUM, LumoUtility.TextAlignment.CENTER);
        teamSection.add(teamHeading);
        
        // Create a Scroller for horizontal scrolling on smaller devices
        Scroller teamScroller = new Scroller();
        teamScroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);
        teamScroller.setWidth("100%");
        
        // Create a flex layout for the team cards
        FlexLayout teamCardsLayout = new FlexLayout();
        teamCardsLayout.addClassNames(
            LumoUtility.Gap.MEDIUM, 
            LumoUtility.Display.FLEX, 
            LumoUtility.FlexWrap.NOWRAP
        );
        teamCardsLayout.setWidth("max-content"); // Allows content to expand beyond container width
        
        // Create team member cards
        teamCardsLayout.add(createTeamMemberCard("Gyanendra Prakash","gyanendra.jpg","Fullstack Dev responsible for the frontend and the lead developer plus project ideation."));
        teamCardsLayout.add(createTeamMemberCard("Goutam","goutam.jpg","team member"));
        teamCardsLayout.add(createTeamMemberCard("Devansh","devansh.jpg","team member"));
        teamCardsLayout.add(createTeamMemberCard("Dhruv","dhruv.jpg","team member"));
        
        // Add the flex layout to the scroller
        teamScroller.setContent(teamCardsLayout);
        
        // Add the scroller to the team section
        teamSection.add(teamScroller);
        
        // Add CSS for responsive behavior
        teamScroller.getElement().getStyle().set("--vaadin-scroller-arrows-visible", "true");
        teamScroller.getElement().getStyle().set("--vaadin-scroller-arrows-color", "var(--lumo-primary-color)");
        
        // Add indicator hint for mobile users
        Paragraph scrollHint = new Paragraph("Swipe to see more team members →");
        scrollHint.addClassNames(
            LumoUtility.FontSize.SMALL,
            LumoUtility.TextColor.SECONDARY,
            LumoUtility.TextAlignment.CENTER,
            LumoUtility.Display.HIDDEN
        );
        
        // Only show hint on small screens using CSS media query
        scrollHint.getElement().getStyle().set("display", "none");
        scrollHint.addClassName("mobile-scroll-hint");
        
        teamSection.add(scrollHint);
        
        // Add the team section to the main content
        getContent().add(teamSection);
    }
    


    //auto gen team member card 
    private Div createTeamMemberCard(String name, String imgName,String description) {
        Div card = new Div();
        card.addClassNames(
            LumoUtility.Background.BASE, 
            LumoUtility.Display.FLEX, 
            LumoUtility.FlexDirection.COLUMN, 
            LumoUtility.Overflow.HIDDEN, 
            LumoUtility.BorderRadius.MEDIUM, 
            LumoUtility.BoxShadow.SMALL
        );
        card.setWidth("280px"); // Fixed width for cards
        
        // Create image container div for consistent sizing
        Div imageContainer = new Div();
        imageContainer.setHeight("200px"); // Fixed height for all image containers
        imageContainer.setWidth("100%");
        imageContainer.getStyle().set("overflow", "hidden");
        imageContainer.getStyle().set("display", "flex");
        imageContainer.getStyle().set("align-items", "center");
        imageContainer.getStyle().set("justify-content", "center");
        
        Image image = new Image();
        image.setSrc("images/"+imgName);
        image.setAlt("Team member photo");
        image.addClassNames(LumoUtility.Width.FULL);
        image.getStyle().set("height", "200px"); // Fixed height for the image
        image.getStyle().set("object-fit", "cover"); // Ensures the image covers the area properly
        image.getStyle().set("object-position", "center"); // Centers the image
        
        imageContainer.add(image);
        
        Div contentDiv = new Div();
        contentDiv.addClassNames(
            LumoUtility.Display.FLEX, 
            LumoUtility.FlexDirection.COLUMN, 
            LumoUtility.Padding.MEDIUM
        );
        
        Span nameSpan = new Span(name);
        nameSpan.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        
        Span descriptionSpan = new Span(description);
        descriptionSpan.addClassNames(LumoUtility.FontSize.SMALL, LumoUtility.TextColor.SECONDARY);
        
        contentDiv.add(nameSpan, descriptionSpan);
        
        Div buttonDiv = new Div();
        buttonDiv.addClassNames(
            LumoUtility.Display.FLEX, 
            LumoUtility.Gap.SMALL, 
            LumoUtility.Padding.SMALL
        );
        
        Button profileButton = new Button("Profile");
        profileButton.setThemeName("tertiary");
        profileButton.addClassNames(LumoUtility.Margin.NONE);
        
        Button contactButton = new Button("Contact");
        contactButton.setThemeName("tertiary");
        contactButton.addClassNames(LumoUtility.Margin.NONE);
        
        buttonDiv.add(profileButton, contactButton);
        
        card.add(imageContainer, contentDiv, buttonDiv);
        
        return card;
    }
}