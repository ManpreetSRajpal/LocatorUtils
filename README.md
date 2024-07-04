# LocatorUtils

LocatorUtils is a utility library designed to manage and utilize element locators for both Android and iOS automation tests using annotations. This library simplifies the process of defining and retrieving locators, making your test code cleaner and easier to maintain.

## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
    - [Annotations](#annotations)
    - [Locator Initialization](#locator-initialization)
    - [Environment Configuration](#environment-configuration)
- [Examples](#examples)



## Features

- Supports defining locators using custom annotations.
- Provides separate strategies for Android and iOS locators.
- Automatically initializes locators based on the specified environment.

## Installation

To use LocatorUtils in your project, add the library as a dependency in your build tool configuration.

### Maven

```xml
<dependency>
    <groupId>io.github.ManpreetSRajpal</groupId>
    <artifactId>locator-utils</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### Gradle

```java
implementation 'io.github.ManpreetSRajpal:locator-utils:1.0-SNAPSHOT'
```

## Usage

### Annotations

Define your element locators using the provided annotations.

#### AndroidLocator

Annotate fields in your page objects with `@AndroidLocator` to define Android-specific locators.

```java
@AndroidLocator(id = "username_input")
private By username;
```

#### IOSLocator

Annotate fields in your page objects with `@IOSLocator` to define iOS-specific locators.

```java
@IOSLocator(xpath = "//XCUIElementTypeTextField[@name='username']")
private By username;
```

### Locator Initialization

Initialize locators in your page objects using `LocatorUtils.initializeLocators(this);`.

```java
public class LoginPage {
    
    @AndroidLocator(id = "username_input")
    @IOSLocator(xpath = "//XCUIElementTypeTextField[@name='username']")
    private By username;

    @AndroidLocator(id = "password_input")
    @IOSLocator(xpath = "//XCUIElementTypeSecureTextField[@name='password']")
    private By password;

    @AndroidLocator(id = "login_button")
    @IOSLocator(xpath = "//XCUIElementTypeButton[@name='login']")
    private By loginButton;

    public LoginPage() {
        LocatorUtils.initializeLocators(this);
    }

    // Additional methods for interacting with elements
}
```

### Environment Configuration

The environment is determined by the `ENVIRONMENT` environment variable. Set `ENVIRONMENT` to either `ANDROID` or `IOS` to specify the platform.

```java
export ENVIRONMENT=Android
//or
export ENVIRONMENT=IOS
```

## Examples

#### Using the PageObject

```java
LoginPage loginPage = new LoginPage();
driver.findElement(loginPage.username).sendKeys("user");
driver.findElement(loginPage.password).sendKeys("password");
driver.findElement(loginPage.loginButton).click();
```