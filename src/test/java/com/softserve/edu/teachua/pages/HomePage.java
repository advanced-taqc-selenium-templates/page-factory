package com.softserve.edu.teachua.pages;

import com.softserve.edu.util.DriverWrapper;

/**
 * Task 3. Home page of https://speak-ukrainian.org.ua.
 * Initialize PageFactory with the shared wrapper. Do not create a new {@link DriverWrapper} here.
 */
public class HomePage {

    public HomePage(DriverWrapper driver) {
        throw new UnsupportedOperationException("Task 3: init HomePage with PageFactory");
    }

    public HomePage open() {
        throw new UnsupportedOperationException("Task 3: open base.url");
    }

    public HelpUsPage goToHelpUsPage() {
        throw new UnsupportedOperationException("Task 3: footer link Допомогти проекту");
    }

    public ClubsPage goToClubsPage() {
        throw new UnsupportedOperationException("Task 3: open Clubs");
    }

    public ChallengePage goToChallengePage() {
        throw new UnsupportedOperationException("Task 3: open Challenge");
    }

    public NewsPage goToNewsPage() {
        throw new UnsupportedOperationException("Task 3: open News");
    }

    public AboutUsPage goToAboutUsPage() {
        throw new UnsupportedOperationException("Task 3: open About us");
    }

    public UkrainianServicesPage goToUkrainianServicesPage() {
        throw new UnsupportedOperationException("Task 3: open Ukrainian services");
    }
}
