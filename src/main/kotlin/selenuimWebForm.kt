package dev.seleniumhq

import org.junit.jupiter.api.Assertions.assertEquals
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.chrome.ChromeDriver
import java.io.File
import java.time.Duration


fun main(args: Array<String>) {
      val driver = ChromeDriver()

      driver.get("https://www.selenium.dev/selenium/web/web-form.html")

      val title = driver.title
      assertEquals("Web form", title)

      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))

      var textBox = driver.findElement(By.name("my-text"))
      val submitButton = driver.findElement(By.cssSelector("button"))

      textBox.sendKeys("Selenium")
      submitButton.click()

      val message = driver.findElement(By.id("message"))
      val value = message.getText()
      assertEquals("Received!", value)

      val file = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)
      //val screenshotBase64 = (driver as TakesScreenshot).getScreenshotAs<String>(OutputType.BASE64)
      val rand = (0..2000).random().toString()
      val pathString = buildString {
            append("C:\\Users\\Kevin\\Downloads\\screenshot")
            append(rand)
            append(".png")
      }
      val fileN = File(pathString)
      file.copyTo(fileN)
      println(pathString)
      println(file.path)

      println(message.text)
      driver.quit()
}