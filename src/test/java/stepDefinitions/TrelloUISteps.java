package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BoardPage;
import pages.DashboardPage;
import pages.LoginPage;
import utilities.ConfigReader;
import pages.HomePage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TrelloUISteps {
	
	WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    HomePage homePage;
    BoardPage boardPage;
    ConfigReader configReader;
    
    @Before("@ui")
    public void browserSetup() {
    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
    	driver = new ChromeDriver();
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
    	
    	configReader = new ConfigReader();
    	
    }
    
    
    @After("@ui")
    public void tearDown() {
    	driver.close();
    	driver.quit();
    }
    
    
	
    @When("user is on login page")
	public void user_is_on_login_page() {
    	loginPage = new LoginPage(driver);
    	driver.get(configReader.getUrl());
	    
	}
    
	@Then("user enters credentials")
    public void user_enters_credentials() {
        loginPage.enterEmail(configReader.getEmail());
        loginPage.enterPassword(configReader.getPassword());
	}

	@And("clicks on login button")
	public void clicks_on_login_button() throws InterruptedException {
	    loginPage.clickLogin();
	    Thread.sleep(3000);
	}
	
	@Then("user verifies page title {string}")
    public void user_verifies_page_title(String title) {
        if (driver.getPageSource().contains("Incorrect email address and / or password.")) {
            driver.close();
            
            assertThat("Error message was found but the title check was not performed.", false, is(true));
        } else {
            
            assertThat(driver.getTitle(), is(equalTo(title)));
        }
    }

	@Then("user is navigated to home page")
	public void user_is_navigated_to_home_page() {
	    driver.getPageSource().contains("Trello");
	}
	
	@When("user clicks on create new board button")
    public void user_clicks_on_create_new_board_button() throws InterruptedException {
		homePage = new HomePage(driver);
		homePage.clickCreateNewBoard();
    }
	
	@And("user enters board name {string}")
    public void user_enters_board_name(String string) {
		homePage.enterBoardTitle(string);
    }
	
	@And("user clicks on create board button")
    public void user_clicks_on_create_board_button() {
		homePage.clickCreateBoardButton();
    }
	
	@And("user verifies {string}")
	public void user_verifies(String msg) throws InterruptedException {
		String errorMessage = homePage.getBoardNameErrorMessage();
		Assert.assertTrue("Error message is not displayed or does not match",
                errorMessage.contains("Board title is required"));
	}
	
	@And("user checks if create board is disabled")
    public void user_checks_if_create_board_is_disabled() {
		boolean isDisabled = homePage.isCreateBoardButtonDisabled();
        Assert.assertTrue(isDisabled);
    }

	@Then("the board {string} should be created")
	public void the_board_should_be_created(String string) throws InterruptedException {
		boardPage = new BoardPage(driver);
		Thread.sleep(3000);
	    boardPage.isBoardDisplayed();
	}
	
	@And("user clicks on logout link")
	public void user_clicks_on_logout_link() throws InterruptedException {
	    loginPage.clickLogout();
	    Thread.sleep(3000);
	}
	
	@Then("user verifies page title contains {string}")
    public void user_verifies_page_title_contains(String partialTitle) {
        String actualTitle = driver.getTitle();
        assertThat("Title does not contain the expected text.", actualTitle, containsString(partialTitle));
    }
	
	@Given("user clicks on {string} board")
	public void user_clicks_on(String boardName) throws InterruptedException {
		boardPage = new BoardPage(driver);
	    boardPage.clickBoardByName(boardName);
	}

	@Then("the board title should be {string}")
	public void the_board_title_should_be(String string) {
	    String boardTitle = boardPage.getBoardTitle();
	    System.out.println(boardTitle);
	}

	@When("user changes the board name to {string}")
	public void i_change_the_board_name_to(String string) throws InterruptedException {
		boardPage.updateBoardName(string);
	    
	}

	@When("user clicks on Show Menu")
	public void user_clicks_on_Show_Menu() {
		boardPage = new BoardPage(driver);
	    boardPage.clickMoreButton();
	}
	
	@And("user clicks on Close Board")
	public void user_clicks_on_Close_Board() {
		boardPage.clickCloseBoardButton();
	}
	
	@And("user confirms Close Board")
	public void user_confirms_Close_Board() {
		boardPage.confirmCloseBoard();
	}
	
	@And("user clicks on Permanently Delete Board")
	public void user_clicks_on_Permanently_Delete_Board() throws InterruptedException {
		boardPage = new BoardPage(driver);
		boardPage.deleteBoard();
	}
	
	@And("user views delete confirmation message {string}")
	public void user_views_delete_confirmation_message(String msg) throws InterruptedException {
		homePage = new HomePage(driver);
		String errorMessage = homePage.getBoardDeleteConfirmMessage();
		Assert.assertTrue("Error message is not displayed or does not match",
                errorMessage.contains("Board deleted."));
	}
	
	@Then("the board should be deleted successfully {string}")
	public void the_board_should_be_deleted_successfully(String boardName) {
	    boardPage.verifyBoardNotPresent(boardName);
	}

}
