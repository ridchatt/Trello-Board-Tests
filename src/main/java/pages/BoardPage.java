package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WaitHelper;

public class BoardPage {
	
	private WebDriver driver;
	WaitHelper waithelper;

    private By boardTitle = By.cssSelector("h1[data-testid='board-name-display']");
    private By moreButton = By.cssSelector("button[aria-label='Show menu']");
    private By closeBoardButton = By.xpath("//a[contains(@class,'js-close-board')]");
    private By confirmCloseButton = By.cssSelector("input[data-testid='close-board-confirm-button']");
    private By boardContent = By.xpath("//div[@data-testid='board-name-container']");
    private By boardContainer = By.xpath("//div[@data-testid='board-name-container']");
    private By boardNameInput = By.xpath("//input[@data-testid='board-name-input']");
    private By deleteBoard = By.xpath("//button[@data-testid='close-board-delete-board-button']");
    private By permanentlyDeleteBoard = By.xpath("//button[@data-testid='close-board-delete-board-confirm-button']");
    private By changeVisibilityButton = By.cssSelector("button[data-testid='board-visibility-option-org']");

    public BoardPage(WebDriver driver) {
        this.driver = driver;
        this.waithelper = new WaitHelper(driver);
    }
    
    public void clickBoardByName(String boardName) throws InterruptedException {
    	Thread.sleep(2000);
        WebElement boardElement = driver.findElement(By.xpath("//div[text()='" + boardName + "']"));
        boardElement.click();
    }

    public String getBoardTitle() {
    	waithelper.WaitForElement(boardTitle, 10);
        return driver.findElement(boardTitle).getText();
    }

    public void clickMoreButton() {
    	waithelper.WaitForElement(moreButton, 10);
        driver.findElement(moreButton).click();
    }

    public void clickCloseBoardButton() {
    	waithelper.WaitForElement(closeBoardButton, 10);
        driver.findElement(closeBoardButton).click();
    }

    public void confirmCloseBoard() {
    	waithelper.WaitForElement(confirmCloseButton, 10);
        driver.findElement(confirmCloseButton).click();
    }
    public void deleteBoard() throws InterruptedException {
    	Thread.sleep(3000);
    	waithelper.WaitForElement(deleteBoard, 30);
        driver.findElement(deleteBoard).click();
        waithelper.WaitForElement(permanentlyDeleteBoard, 30);
        driver.findElement(permanentlyDeleteBoard).click();
        Thread.sleep(1000);
    }
    
    public boolean isBoardDisplayed() throws InterruptedException {
    	Thread.sleep(3000);
        WebElement boardDetail = driver.findElement(boardContent);
        return boardDetail.isDisplayed();
    }
    
    public void updateBoardName(String newBoardName) throws InterruptedException {
    	Thread.sleep(3000);
    	waithelper.WaitForElement(boardContainer, 30);
        driver.findElement(boardContainer).click();
        WebElement boardLabel = driver.findElement(boardNameInput);
        String currentText = boardLabel.getAttribute("value");
        for (int i = 0; i < currentText.length(); i++) {
        	boardLabel.sendKeys(Keys.BACK_SPACE); 
        }
        driver.findElement(boardNameInput).sendKeys(newBoardName);
        waithelper.WaitForElement(changeVisibilityButton, 10);
        driver.findElement(changeVisibilityButton).click();
    }
  
    public void verifyBoardNotPresent(String boardName) {
        boolean isBoardPresent = driver.findElements(By.xpath("//div[@data-testid='board-name-container']//h1[text()='" + boardName + "']")).size() > 0;
        
        if (isBoardPresent) {
            throw new AssertionError("The board '" + boardName + "' was not deleted successfully.");
        }
    }

}
