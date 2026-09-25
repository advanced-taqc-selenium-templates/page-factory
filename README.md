# Page Object starter

Selenium starter for the TeachUA site [https://speak-ukrainian.org.ua](https://speak-ukrainian.org.ua).
The repository already contains a working example (`OtherTest` / `ExamplePage`) and empty hooks for your tasks.
Do not replace the example. Extend the project around it.

## Setup

1. JDK 17 and Maven 3.9+.
2. Google Chrome, Mozilla Firefox, or Microsoft Edge installed locally. Selenium Manager downloads the matching driver.
3. Copy the environment file and set the browser you will use first:

```bash
cp .env.example .env
```

4. Run the example:

```bash
mvn -Dtest=OtherTest test
```

Failure screenshots and page source are written to `screenshots/`.

## What is already in the project

| Piece | Role |
| --- | --- |
| `TestRunner` | Starts and stops the browser. `beforeEach` is intentionally incomplete. |
| `RunnerExtension` | Records whether the test passed, so `afterEach` can save artifacts only on failure. |
| `DriverWrapper` | Small helper around `WebDriver`. Not a full wrapper yet. |
| `ExamplePage` + `OtherTest` | Reference for PageFactory, frames, and explicit waits. |
| Page and test stubs | Class names and method signatures for tasks 3–5. Method bodies throw `UnsupportedOperationException`. |

`ExamplePage` receives the shared `DriverWrapper`. New pages must do the same. Do not construct another wrapper inside a page.

## Task 1. Browser lifecycle in `TestRunner`

Refactor `beforeEach`.

1. Support `firefox`, `chrome`, and `edge` from the `browser` value in `.env`. An unknown value must fail with a message that names the value.
2. Read `.env` once per JVM, not before every test.
3. A test class must still get a fresh browser for each test method.
4. `mvn -Dtest=OtherTest test` must pass for the browser selected in `.env`.

## Task 2. WebDriver wrapper

Turn `DriverWrapper` into the type pages and tests use instead of a raw `WebDriver`.

1. Expose the operations pages need: open a URL, find elements, switch frame, quit, delete cookies.
2. Keep the existing explicit waits (`scrollToElement`, `moveToFrame`, `waitAndType`). New waits belong on the wrapper, not copied into each page.
3. `PageFactory.initElements` still needs a `SearchContext`. Pass `wrapper.getDriver()` (or the wrapper itself if it implements `SearchContext`).

## Task 3. TeachUA pages

Create pages with PageFactory and the shared `DriverWrapper`:

`HomePage`, `HelpUsPage`, `ClubsPage`, `ChallengePage`, `NewsPage`, `AboutUsPage`, `UkrainianServicesPage`.

On `HomePage`, open `base.url` and add navigation methods:

- `goToHelpUsPage()` follows **Допомогти проекту** in the footer
- `goToClubsPage()`
- `goToChallengePage()`
- `goToNewsPage()`
- `goToAboutUsPage()`
- `goToUkrainianServicesPage()`

Each method returns the opened page object. Stubs already declare these signatures.

## Task 4. Payment methods

Implement `HelpUsTest`.

1. From the home page, open Help Us with **Допомогти проекту**.
2. Assert the payment methods from `img/payment_methods.png` are present:
   - Google Pay
   - card brands (Visa / Mastercard)
   - **Інший спосіб оплати**
   - Privat24, cash terminal, MasterPass, Visa Checkout
3. Cover the disabled pay action and the alternate payment path in the two test methods that are already declared.

## Task 5 (optional). Bootstrap selects

Extend `DriverWrapper` and `BootstrapFormControlsPage`.

1. Add a method on `DriverWrapper` that selects options in a `<select>`, including a multi-select.
2. Open [Bootstrap 4 form controls](https://getbootstrap.com/docs/4.0/components/forms/#form-controls).
3. Assert the single select and the multiple select on that page. Reference screenshot: `img/bootstrap_select.png` (add it to the repository if you capture one).

## Report

The report includes the GitHub link.