package com.softserve.edu.teachua.tests;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

class RunnerExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        boolean failed = context.getExecutionException().isPresent();
        System.out.println("\t\t\t\tException.isPresent() = " + failed);
        System.out.println("\t\t\t\tTest context.getDisplayName(): " + context.getDisplayName());
        TestRunner.isTestSuccessful = !failed;
    }
}
