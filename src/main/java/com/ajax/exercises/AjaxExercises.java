package com.ajax.exercises;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

/**
 * AjaxExercisesDetailed
 *
 * - 30 AJAX-focused Selenium exercises in a single class
 * - All locators use XPath
 * - Each action has a detailed comment explaining the step
 * - Uses TestNG @Test for each exercise
 *
 * WARNING: Running many real-site tests (Google, Amazon, YouTube, etc.) can be flaky due to dynamic content,
 * region differences, rate-limiting, login overlays, and anti-bot protections. Use test/demo pages first.
 */
public class AjaxExercises{

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        // Start ChromeDriver (ensure chromedriver binary is on PATH or configured)
        driver = new ChromeDriver();

        // Maximize window so all elements are reachable
        driver.manage().window().maximize();

        // Create an explicit wait to use for AJAX waits (15 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterClass
    public void teardown() {
        // Quit the browser and clean up resources
        if (driver != null) {
            driver.quit();
        }
    }

    // ---------------- Exercise 1 ----------------
    @Test
    public void exercise01_DynamicLoading_Example1() {
        // Navigate to the dynamic loading demo (Example 1)
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        // Find and click the Start button (triggers AJAX to fetch and reveal content)
        // We use XPath: div with id 'start' and its button child
        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        // Wait until the finish element is visible (AJAX completion)
        // This ensures we don't attempt to read the text before it's present
        WebElement finishText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4"))
        );

        // Print the loaded text to verify correct load
        System.out.println("Exercise 01 - Loaded text: " + finishText.getText());
    }

    // ---------------- Exercise 2 ----------------
    @Test
    public void exercise02_DynamicLoading_Example2() {
        // Navigate to dynamic loading Example 2 (element added to DOM after AJAX)
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");

        // Click start to trigger the AJAX action
        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        // Wait until the element appears and becomes visible
        WebElement finishText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4"))
        );

        // Output the text to confirm
        System.out.println("Exercise 02 - Loaded text: " + finishText.getText());
    }

    // ---------------- Exercise 3 ----------------
    @Test
    public void exercise03_AJAXLoader_UIPlayground() {
        // Navigate to UI Testing Playground's AJAX example
        driver.get("http://uitestingplayground.com/ajax");

        // Click the AJAX button that starts an asynchronous request
        driver.findElement(By.xpath("//button[@id='ajaxButton']")).click();

        // Wait until the success message appears (this element appears when AJAX completes)
        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'bg-success') or contains(text(),'Data loaded')]"))
        );

        // Print success message
        System.out.println("Exercise 03 - AJAX message: " + successMsg.getText());
    }

    // ---------------- Exercise 4 ----------------
    @Test
    public void exercise04_DynamicControls_EnableTextbox() {
        // Navigate to dynamic controls page
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        // Click the 'Enable' button to enable the disabled text box via AJAX
        driver.findElement(By.xpath("//form[@id='input-example']/button[text()='Enable']")).click();

        // Wait until the input becomes clickable (which indicates enabled)
        WebElement enabledInput = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='input-example']/input"))
        );

        // Enter text into enabled input to verify it's usable
        enabledInput.sendKeys("Hello AJAX");

        // Print confirmation
        System.out.println("Exercise 04 - Text entered into enabled input: " + enabledInput.getAttribute("value"));
    }

    // ---------------- Exercise 5 ----------------
    @Test
    public void exercise05_DynamicControls_RemoveAddCheckbox() {
        // Navigate to dynamic controls
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        // Click the Remove button to remove the checkbox (AJAX)
        driver.findElement(By.xpath("//form[@id='checkbox-example']/button[text()='Remove']")).click();

        // Wait until the message appears telling us the checkbox is gone
        WebElement removedMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='checkbox-example']/p"))
        );

        // Print the removal message
        System.out.println("Exercise 05 - Remove message: " + removedMsg.getText());

        // Click Add to bring the checkbox back (AJAX)
        driver.findElement(By.xpath("//form[@id='checkbox-example']/button[text()='Add']")).click();

        // Wait until the checkbox input is visible again
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='checkbox-example']//input[@type='checkbox']")));

        System.out.println("Exercise 05 - Checkbox re-added successfully");
    }

    // ---------------- Exercise 6 ----------------
    @Test
    public void exercise06_GoogleSearch_Suggestions() {
        // Navigate to Google (may show region consent overlays — handle manually if needed)
        driver.get("https://www.google.com");

        // Locate the search box (Google sometimes uses textarea for the main input)
        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name='q' or @name='Q'] | //input[@name='q']"))
        );

        // Enter the search term to trigger suggestions (AJAX)
        searchBox.sendKeys("Selenium");

        // Wait until the suggestions listbox becomes visible
        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//ul[@role='listbox']//li//span")
                )
        );

        // Print top 5 suggestions
        System.out.println("Exercise 06 - Top Google suggestions:");
        for (int i = 0; i < Math.min(5, suggestions.size()); i++) {
            System.out.println("  " + (i + 1) + ". " + suggestions.get(i).getText());
        }
    }

    // ---------------- Exercise 7 ----------------
    @Test
    public void exercise07_Wikipedia_Autocomplete() {
        // Navigate to Wikipedia homepage
        driver.get("https://www.wikipedia.org/");

        // Enter a term into the search input (this triggers AJAX suggestions)
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='searchInput']"));
        searchInput.sendKeys("Test");

        // Wait for the suggestion results container to appear
        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class,'suggestions-results')]//a")
                )
        );

        // Print up to 5 suggestions
        System.out.println("Exercise 07 - Wikipedia suggestions:");
        for (int i = 0; i < Math.min(5, suggestions.size()); i++) {
            System.out.println("  " + suggestions.get(i).getText());
        }
    }

    // ---------------- Exercise 8 ----------------
    @Test
    public void exercise08_Yahoo_SearchSuggestions() {
        // Navigate to Yahoo search page
        driver.get("https://search.yahoo.com/");

        // Enter search text to trigger AJAX suggestions
        WebElement input = driver.findElement(By.xpath("//input[@name='p']"));
        input.sendKeys("Selenium");

        // Wait for suggestion list elements to appear
        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[contains(@class,'sa-list')]/li"))
        );

        // Print first 3 suggestions
        System.out.println("Exercise 08 - Yahoo suggestions:");
        for (int i = 0; i < Math.min(3, suggestions.size()); i++) {
            System.out.println("  " + suggestions.get(i).getText());
        }
    }

    // ---------------- Exercise 9 ----------------
    @Test
    public void exercise09_Amazon_Autocomplete() {
        // Navigate to Amazon (homepage)
        driver.get("https://www.amazon.com/");

        // Locate the search textbox and type to trigger suggestions (region/popups may appear)
        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='twotabsearchtextbox']"))
        );
        searchBox.sendKeys("Laptop");

        // Wait for suggestion container (class names vary; use partial-match)
        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class,'s-suggestion') or contains(@class,'autocomplete-results')]/div")
                )
        );

        // Print up to 5 suggestions
        System.out.println("Exercise 09 - Amazon suggestions:");
        for (int i = 0; i < Math.min(5, suggestions.size()); i++) {
            System.out.println("  " + suggestions.get(i).getText());
        }
    }

    // ---------------- Exercise 10 ----------------
    @Test
    public void exercise10_jQueryUI_Autocomplete() {
        // Navigate to jQuery UI Autocomplete demo (widget is inside an iframe)
        driver.get("https://jqueryui.com/autocomplete/");

        // Switch to the demo iframe that contains the autocomplete widget
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src,'autocomplete')]")));

        // Type into the tags input to trigger suggestions
        WebElement tagsInput = driver.findElement(By.xpath("//input[@id='tags']"));
        tagsInput.sendKeys("ja");

        // Wait for autocomplete list items to appear
        List<WebElement> items = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='ui-id-1']/li"))
        );

        // Iterate items and click "Java" if present
        for (WebElement item : items) {
            if ("Java".equals(item.getText().trim())) {
                item.click();
                break;
            }
        }

        // Print selected value
        System.out.println("Exercise 10 - Autocomplete selected: " + tagsInput.getAttribute("value"));

        // Switch back to default content after interacting inside iframe
        driver.switchTo().defaultContent();
    }

    // ---------------- Exercise 11 ----------------
    @Test
    public void exercise11_InfiniteScroll_Herokuapp() throws InterruptedException {
        // Navigate to the infinite scroll demo
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");

        // Use JavaScript to scroll down multiple times to trigger dynamic loading
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            // Scroll down by 1000 pixels
            js.executeScript("window.scrollBy(0,1000)");
            // Wait a short time for new content to load
            Thread.sleep(800);
        }

        // Print confirmation
        System.out.println("Exercise 11 - Performed repeated scrolls to load dynamic content");
    }

    // ---------------- Exercise 12 ----------------
    @Test
    public void exercise12_InfiniteScroll_Reddit() throws InterruptedException {
        // Navigate to reddit front page
        driver.get("https://www.reddit.com/");

        // Scroll multiple times to allow new posts to load via AJAX
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 3; i++) {
            js.executeScript("window.scrollBy(0,2000)");
            Thread.sleep(1500); // short pause to let content load
        }

        // Capture first few post titles (h3 elements on reddit)
        List<WebElement> titles = driver.findElements(By.xpath("//h3"));

        System.out.println("Exercise 12 - Top Reddit titles after scrolling:");
        for (int i = 0; i < Math.min(5, titles.size()); i++) {
            System.out.println("  " + titles.get(i).getText());
        }
    }

    // ---------------- Exercise 13 ----------------
    @Test
    public void exercise13_DataTables_AJAX_Pagination() {
        // Navigate to DataTables AJAX example
        driver.get("https://datatables.net/examples/data_sources/ajax");

        // Wait for the example table to be present
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='example']")));

        // Click the 'Next' pagination button (id 'example_next' is an anchor)
        driver.findElement(By.xpath("//a[@id='example_next']")).click();

        // Wait until the table info text shows rows starting at 21 (indicates pagination worked)
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@id='example_info']"), "21"));

        System.out.println("Exercise 13 - Navigated to next page of DataTable");
    }

    // ---------------- Exercise 14 ----------------
    @Test
    public void exercise14_Primefaces_TableFilter() {
        // Navigate to PrimeFaces DataTable AJAX example
        driver.get("https://www.primefaces.org/showcase/ui/data/datatable/ajax.xhtml");

        // Find the first column's filter input and type 'London' to filter results via AJAX
        WebElement filter = driver.findElement(By.xpath("//input[contains(@class,'ui-column-filter')]"));
        filter.sendKeys("London");

        // Wait until a cell in the table contains 'London' (shows filter applied)
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//tbody/tr/td"), "London"));

        System.out.println("Exercise 14 - Filtered PrimeFaces table for 'London'");
    }

    // ---------------- Exercise 15 ----------------
    @Test
    public void exercise15_DependentDropdowns_Cascading() {
        // Navigate to a cascading dropdown demo
        driver.get("https://www.cascadingdropdown.com/");

        // Select a country by sending Keys to the country select element (triggers AJAX load of states)
        driver.findElement(By.xpath("//select[@id='ddlCountry']")).sendKeys("India");

        // Wait until the state select becomes clickable (indicates AJAX loaded options)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='ddlState']")));

        // Count the number of loaded state options and print
        List<WebElement> states = driver.findElements(By.xpath("//select[@id='ddlState']/option"));
        System.out.println("Exercise 15 - Loaded state options count: " + states.size());
    }

    // ---------------- Exercise 16 ----------------
    @Test
    public void exercise16_Guru99_RadioAjax() {
        // Open the Guru99 AJAX demo
        driver.get("http://demo.guru99.com/test/ajax.html");

        // Select the 'Yes' radio option (this sets form state)
        driver.findElement(By.xpath("//input[@id='yes']")).click();

        // Click the button that reads the selected radio using AJAX JS
        driver.findElement(By.xpath("//input[@id='buttoncheck']")).click();

        // Wait until the result paragraph appears showing which radio was selected
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='radiobutton']")));

        // Print the result text
        System.out.println("Exercise 16 - Radio selection result: " + result.getText());
    }

    // ---------------- Exercise 17 ----------------
    @Test
    public void exercise17_AutomationTesting_ProgressBar() {
        // Go to a progress bar demo which simulates a download via AJAX
        driver.get("https://demo.automationtesting.in/ProgressBar.html");

        // Click the Download button to start the simulated progress (AJAX)
        driver.findElement(By.xpath("//button[@id='downloadButton']")).click();

        // Wait until the progress label shows 'Complete!' (indicates finished)
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='progress-label']"), "Complete!"));

        // Print confirmation
        System.out.println("Exercise 17 - Progress bar completed");
    }

    // ---------------- Exercise 18 ----------------
    @Test
    public void exercise18_Bootstrap_ProgressBar_CheckStyle() {
        // Navigate to the Bootstrap Progress components page
        driver.get("https://getbootstrap.com/docs/4.0/components/progress/");

        // Locate the progress bar element and read its style attribute which contains width
        WebElement progressBar = driver.findElement(By.xpath("//div[contains(@class,'progress-bar')]"));
        String style = progressBar.getAttribute("style"); // e.g., "width: 25%"

        // Print style to know the progress percentage
        System.out.println("Exercise 18 - Progress bar style attribute: " + style);
    }

    // ---------------- Exercise 19 ----------------
    @Test
    public void exercise19_DynamicLoader_Disappears() {
        // Navigate to dynamic loading where loader element appears then disappears
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");

        // Click Start to trigger loader
        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        // Wait until the loader element (spinner) becomes invisible (AJAX finished)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));

        // Now the content is visible - print the finish text
        WebElement finish = driver.findElement(By.xpath("//div[@id='finish']/h4"));
        System.out.println("Exercise 19 - After loader disappeared: " + finish.getText());
    }

    // ---------------- Exercise 20 ----------------
    @Test
    public void exercise20_UITestingPlayground_Messages() {
        // Navigate to the UI Testing Playground AJAX example
        driver.get("http://uitestingplayground.com/ajax");

        // Click the AJAX button to trigger a request
        driver.findElement(By.xpath("//button[@id='ajaxButton']")).click();

        // Wait until a success message appears in the UI (bg-success area)
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'bg-success') or contains(text(),'Data loaded')]")));

        // Print the message shown after AJAX completes
        System.out.println("Exercise 20 - Message shown: " + msg.getText());
    }

    // ---------------- Exercise 21 ----------------
    @Test
    public void exercise21_jQuery_Datepicker_SelectDate() {
        // Open the jQuery UI datepicker demo (contains iframe)
        driver.get("https://jqueryui.com/datepicker/");

        // Switch into the demo iframe which contains the actual datepicker widget
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src,'datepicker')]")));

        // Click the input to open datepicker calendar
        WebElement dateInput = driver.findElement(By.xpath("//input[@id='datepicker']"));
        dateInput.click();

        // Click a date (for example the day '15') inside the calendar (choose whatever exists)
        driver.findElement(By.xpath("//table[contains(@class,'ui-datepicker-calendar')]//a[text()='15']")).click();

        // Print the selected date value in the input
        System.out.println("Exercise 21 - Selected date: " + dateInput.getAttribute("value"));

        // Switch back to default content
        driver.switchTo().defaultContent();
    }

    // ---------------- Exercise 22 ----------------
    @Test
    public void exercise22_MakeMyTrip_FlightSuggestions() throws InterruptedException {
        // Navigate to MakeMyTrip (travel booking) — may show overlays; wait for page to load
        driver.get("https://www.makemytrip.com/");

        // Sleep briefly to allow site scripts and overlays to settle (site heavy with JS)
        Thread.sleep(4000);

        // Click anywhere or close overlay if needed — Many regions show login/modal, try to click body
        try {
            driver.findElement(By.xpath("//body")).click();
        } catch (Exception ignored) {}

        // Find the 'From' city input and type a city to trigger autocomplete suggestions
        WebElement from = driver.findElement(By.xpath("//input[@id='fromCity']"));
        from.sendKeys("Delhi");

        // Wait until suggestions appear (they are usually in <p> with class font14 or similar)
        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[contains(@class,'font14') or contains(@class,'airport')]"))
        );

        // Print the first few suggestions found
        System.out.println("Exercise 22 - Flight suggestions:");
        for (int i = 0; i < Math.min(3, suggestions.size()); i++) {
            System.out.println("  " + suggestions.get(i).getText());
        }
    }

    // ---------------- Exercise 23 ----------------
    @Test
    public void exercise23_Primefaces_TableSort() {
        // Navigate to PrimeFaces DataTable sort demo
        driver.get("https://www.primefaces.org/showcase/ui/data/datatable/sort.xhtml");

        // Click on the Name column header to trigger AJAX sorting
        driver.findElement(By.xpath("//th[normalize-space()='Name']")).click();

        // Wait until some known cell text appears or the first row updates; using a sample expected text
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr/td")));

        // Print first row cell text after sorting to confirm action
        WebElement firstCell = driver.findElement(By.xpath("//tbody/tr/td"));
        System.out.println("Exercise 23 - First cell after sort: " + firstCell.getText());
    }

    // ---------------- Exercise 24 ----------------
    @Test
    public void exercise24_Primefaces_TabSwitching() {
        // Open PrimeFaces TabView demo
        driver.get("https://www.primefaces.org/showcase/ui/panel/tabView.xhtml");

        // Click the tab labeled "Ajax" to trigger AJAX content load
        driver.findElement(By.xpath("//a[normalize-space()='Ajax']")).click();

        // Wait until the panel's content contains the expected text indicating the Ajax tab loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'ui-tabs-panel') and contains(.,'Ajax Tab Content') or contains(.,'Ajax')]")));

        // Print confirmation
        System.out.println("Exercise 24 - Ajax tab selected and content loaded");
    }

    // ---------------- Exercise 25 ----------------
    @Test
    public void exercise25_Primefaces_MultiSelect() {
        // Navigate to the PrimeFaces MultiSelect example
        driver.get("https://www.primefaces.org/showcase/ui/input/multiSelect.xhtml");

        // Click the multi-select trigger to open options (this will trigger any AJAX UI updates)
        driver.findElement(By.xpath("//div[contains(@class,'ui-multiselect-trigger')]")).click();

        // Click specific options by label text (these are implemented as checkbox-like labels)
        driver.findElement(By.xpath("//label[normalize-space()='Option 1']")).click();
        driver.findElement(By.xpath("//label[normalize-space()='Option 2']")).click();

        // After selection, the component updates; print a message to show completion
        System.out.println("Exercise 25 - Multi-select options chosen (Option 1, Option 2)");
    }

    // ---------------- Exercise 26 ----------------
    @Test
    public void exercise26_NotificationMessage() {
        // Navigate to the notification message demo (message changes after AJAX)
        driver.get("http://the-internet.herokuapp.com/notification_message_rendered");

        // Click the link that triggers an AJAX-like reload of the message
        driver.findElement(By.xpath("//a[text()='Click here']")).click();

        // Wait until flash message appears
        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='flash']")));

        // Print the flash text shown (varies each click)
        System.out.println("Exercise 26 - Notification message: " + flash.getText().trim());
    }

    // ---------------- Exercise 27 ----------------
    @Test
    public void exercise27_UITestPlayground_AjaxFormSubmit() {
        // Open the UI Testing Playground AJAX example again (to reuse)
        driver.get("http://uitestingplayground.com/ajax");

        // Click the AJAX button to simulate a form submit that results in dynamic message
        driver.findElement(By.xpath("//button[@id='ajaxButton']")).click();

        // Wait until the result area shows success
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'bg-success') or contains(text(),'Data loaded')]")));

        // Print verified message
        System.out.println("Exercise 27 - AJAX form submit result: " + success.getText());
    }

    // ---------------- Exercise 28 ----------------
    @Test
    public void exercise28_DataTables_SearchUsers() {
        // Go to DataTables row details example which has a search box and uses AJAX
        driver.get("https://datatables.net/examples/api/row_details.html");

        // Find the search input and type "London" to filter table via AJAX
        WebElement search = driver.findElement(By.xpath("//input[@type='search']"));
        search.sendKeys("London");

        // Wait until a cell containing 'London' is present in the table body (indicates AJAX filter applied)
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//tbody/tr/td"), "London"));

        // Print confirmation
        System.out.println("Exercise 28 - Search applied and 'London' found in results");
    }

    // ---------------- Exercise 29 ----------------
    @Test
    public void exercise29_YouTube_InfiniteScroll() throws InterruptedException {
        // Navigate to YouTube search results for 'selenium'
        driver.get("https://www.youtube.com/results?search_query=selenium");

        // Use JS scroll to load more results multiple times (YouTube uses infinite loading)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 3; i++) {
            js.executeScript("window.scrollBy(0,3000)");
            Thread.sleep(2500); // wait for results to load
        }

        // Collect video title anchors (id='video-title') and print top 5
        List<WebElement> videos = driver.findElements(By.xpath("//a[@id='video-title']"));

        System.out.println("Exercise 29 - Top 5 YouTube titles:");
        for (int i = 0; i < Math.min(5, videos.size()); i++) {
            System.out.println("  " + videos.get(i).getText());
        }
    }

    // ---------------- Exercise 30 ----------------
    @Test
    public void exercise30_Primefaces_LazyLoadingTable() {
        // Open PrimeFaces lazy loading datatable example
        driver.get("https://www.primefaces.org/showcase/ui/data/datatable/lazy.xhtml");

        // Wait until table rows render (lazy load triggers AJAX)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr")));

        // Print number of rows currently shown (lazy load may fetch a page)
        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
        System.out.println("Exercise 30 - Lazy-loaded rows count: " + rows.size());
    }
}
