package dev.seleniumhq

import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.io.File
import java.time.Duration


fun takeScreen(number : Int, driver : WebDriver) {
    val file = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
    val rand = (0..2000).random().toString()
    val pathString = buildString {
        append("C:\\Users\\Kevin\\Downloads\\screenshot")
        append(number)
        append("_")
        append(rand)
        append(".png")
    }
    val fileN = File(pathString)
    file.copyTo(fileN)
    println(pathString)
    println(file.path)
}
fun main(args: Array<String>) {
    val driver = ChromeDriver()
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000))
    driver.get("https://my.affrepublic.com/partner/login")

    //val title = driver.title
//    assertEquals("Web form", title)

    //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

    val emailField = driver.findElement(By.name("email"))
    val passwordField = driver.findElement(By.name("password"))
    val buttons  = driver.findElements(By.cssSelector("button"))

    var confirmButton = driver.findElement(By.cssSelector("button"))

    for (button in buttons) {
        println("Button: " + button.text)
        if (button.text=="Войти как партнер") {
            confirmButton = button
        }
    }

    emailField.sendKeys("DevinEllington3@itarchermail.com")
    passwordField.sendKeys("jAxePMK5H39Gn3")
    //println(confirmButton.text)
    takeScreen(1, driver)
    confirmButton.click()
    Thread.sleep(2500)
    driver.get("https://my.affrepublic.com/partner/profile")
    Thread.sleep(2500)
    val textLines = driver.findElements(By.cssSelector("span"))
    val apiToken =  driver.findElement(By.cssSelector("span"))
    takeScreen(2, driver)
    var foundTheRightLine = 0
    for (line in textLines) {

        if (foundTheRightLine == 1) {
            println("Your API token: " + line.text)
            takeScreen(3, driver)
            break
        }
        if (line.text == "Account info") {
            foundTheRightLine = 1
        }
    }
    //  val message = driver.findElement(By.cssSelector("src-shared-react-components-layouts-Section2-styles___titleText___2yvhP"))
   //  val value = message.getText()
   //  assertEquals("Current balance", value)

//    val revealed = driver.findElement(By.id("revealed"))
//    val wait: Wait<WebDriver> = WebDriverWait(driver, Duration.ofSeconds(2))
//
//    driver.findElement(By.id("reveal")).click()
//    wait.until { d: WebDriver? -> revealed.isDisplayed }
//
//    revealed.sendKeys("Displayed")



     driver.quit()
}
