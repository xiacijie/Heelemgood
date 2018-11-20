package com.example.jerry.healemgood.Intent;

import android.support.v7.app.AppCompatActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.jerry.healemgood.R;
import com.example.jerry.healemgood.controller.UserController;
import com.example.jerry.healemgood.model.user.User;
import com.example.jerry.healemgood.MainActivity;
import com.example.jerry.healemgood.view.UserActivities.AccountCreationActivity;
import com.example.jerry.healemgood.view.UserViews.PatientAddProblemActivity;
import com.example.jerry.healemgood.view.UserViews.PatientAllProblemActivity;
import com.robotium.solo.Solo;

import java.sql.BatchUpdateException;
// Source: https://www.youtube.com/watch?v=T_8euppCz3k Accessed 2018-11-18

/**
 * Handles logging in
 * Creates an account, uses it to log in, and deletes it afterwards
 * Tests Main activity, AccountCreation Activity
 * @author WeakMill98
 * @version 1.0
 * @see ActivityInstrumentationTestCase2
 * @see AccountCreationActivity
 * @see MainActivity
 * @since 1.0
 */

public class Problem_Record_ActivityTest extends ActivityInstrumentationTestCase2<com.example.jerry.healemgood.MainActivity> {
    private Solo solo;

    public Problem_Record_ActivityTest(){
        super("com.example.jerry.healemgood.Intent",
                com.example.jerry.healemgood.MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testProblemCreation() {
        solo.assertCurrentActivity("Check on login", MainActivity.class);

        // Enter the credentials and enter the application
        EditText loginCredentials = (EditText) solo.getView(R.id.userIdEditText);
        solo.enterText(loginCredentials, "TestGUY12345");
        solo.clickOnButton("Sign In");
        solo.assertCurrentActivity("Check on login", PatientAllProblemActivity.class);

        // Create a new problem
        Button createProblemButton = (Button) solo.getView(R.id.createProblemButton);
        solo.clickOnView(createProblemButton);
        solo.assertCurrentActivity("Check on Problem", PatientAddProblemActivity.class);

        // Get the title and description edit texts
        EditText titleInput = (EditText) solo.getView(R.id.titleInput);
        EditText descriptionInput = (EditText) solo.getView(R.id.descriptionInput);

        // Populate the two fields
        solo.enterText(titleInput, "Test Title");
        solo.enterText(descriptionInput, "Test Description");

        // Save the Entry and leave
        Button saveButton = (Button) solo.getView(R.id.saveButton);
        solo.clickOnView(saveButton);

        // Assert the activity
        solo.assertCurrentActivity("Check on Problem", PatientAddProblemActivity.class);
    }
}
