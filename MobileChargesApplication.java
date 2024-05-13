// File: MobileChargesApplication.java
// Project: CSIS3101 Assignment 13
// Author: Allen Worrell
// History: Version 1.0 April 26, 2024
// Program: extends Application, creates a GUI for a mobile charges calculator


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MobileChargesApplication extends Application {
    private TextField dataUsageField, numUsersField, taxPercentField;
    private Label annualChargeLabel, messageLabel;
    private ToggleGroup planToggleGroup;
    private RadioButton plan15Radio, plan25Radio, plan50Radio, unlimitedRadio;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mobile Charges Calculator");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Part 1
        Label dataUsageLabel = new Label("Expected Data Usage (GB):");
        dataUsageField = new TextField();
        gridPane.add(dataUsageLabel, 0, 0);
        gridPane.add(dataUsageField, 1, 0);

        Label numUsersLabel = new Label("Number of Users:");
        numUsersField = new TextField();
        gridPane.add(numUsersLabel, 0, 1);
        gridPane.add(numUsersField, 1, 1);

        Label taxPercentLabel = new Label("Tax Percent:");
        taxPercentField = new TextField();
        gridPane.add(taxPercentLabel, 0, 2);
        gridPane.add(taxPercentField, 1, 2);

        annualChargeLabel = new Label();
        gridPane.add(new Label("Calculated Annual Mobile Charges:"), 0, 3);
        gridPane.add(annualChargeLabel, 1, 3);

        Button calculateButton = new Button("Calculate Annual Mobile Charge");
        gridPane.add(calculateButton, 1, 4);
        calculateButton.setOnAction(e -> calculateAnnualCharge());

        // Part 2
        planToggleGroup = new ToggleGroup();

        plan15Radio = new RadioButton("Plan15");
        plan15Radio.setToggleGroup(planToggleGroup);
        gridPane.add(plan15Radio, 0, 5);

        plan25Radio = new RadioButton("Plan25");
        plan25Radio.setToggleGroup(planToggleGroup);
        gridPane.add(plan25Radio, 1, 5);

        plan50Radio = new RadioButton("Plan50");
        plan50Radio.setToggleGroup(planToggleGroup);
        gridPane.add(plan50Radio, 2, 5);

        unlimitedRadio = new RadioButton("Unlimited");
        unlimitedRadio.setToggleGroup(planToggleGroup);
        unlimitedRadio.setSelected(true);
        gridPane.add(unlimitedRadio, 3, 5);

        messageLabel = new Label();
        gridPane.add(new Label("Message:"), 0, 6);
        gridPane.add(messageLabel, 1, 6, 3, 1);

        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateAnnualCharge() {
        double dataUsage = Double.parseDouble(dataUsageField.getText());
        int numUsers = Integer.parseInt(numUsersField.getText());
        double taxPercent = Double.parseDouble(taxPercentField.getText()) / 100;

        double annualCharge;
        String planMessage;

        if (plan15Radio.isSelected()) {
            annualCharge = calculateAnnualCharge(dataUsage, numUsers, 14.99);
            planMessage = "You are on Plan15 and can use up to 15 GB per month per user";
        } else if (plan25Radio.isSelected()) {
            annualCharge = calculateAnnualCharge(dataUsage, numUsers, 24.50);
            planMessage = "You are on Plan25 and can use up to 25 GB per month per user";
        } else if (plan50Radio.isSelected()) {
            annualCharge = calculateAnnualCharge(dataUsage, numUsers, 35.00);
            planMessage = "You are on Plan50 and can use up to 50 GB per month per user";
        } else {
            annualCharge = calculateAnnualCharge(dataUsage, numUsers, 50.00);
            planMessage = "You are on Unlimited plan and Enjoy unlimited data for all users";
        }

        double taxAmount = annualCharge * taxPercent;
        double totalAnnualCharge = annualCharge + taxAmount;

        annualChargeLabel.setText(String.format("$%.2f", totalAnnualCharge));
        messageLabel.setText(planMessage);
    }

    private double calculateAnnualCharge(double dataUsage, int numUsers, double monthlyCharge) {
        return numUsers * monthlyCharge * 12;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
