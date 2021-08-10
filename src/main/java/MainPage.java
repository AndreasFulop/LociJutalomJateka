import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.navigate().to("https://www.goldrecord.hu/zenekar/loci-jatszik");
    }

    private final By COOKIE1 = By.xpath("/html/body/div[5]/form/div/div[2]/div/div[2]/label/input");
    private final By COOKIE2 = By.xpath("/html/body/div[5]/form/div/div[2]/div/div[3]/label/input");
    private final By COOKIE3 = By.xpath("/html/body/div[5]/form/div/div[3]/div/a[1]/div");
    private final By MEMBERS = By.xpath("//*[@class='zenekarboxszov animated fadeIn go']/p[5]/span");

    public void cookieAccept() {
        driver.findElement(COOKIE1).click();
        driver.findElement(COOKIE2).click();
        driver.findElement(COOKIE3).click();
    }

    public void MembersWriter(String guitar) {
        List<WebElement> members = driver.findElements(MEMBERS);
        String mainMemberFull = "";
        for (WebElement i : members) {
            if (i.getText().contains(" " + guitar)) {
                mainMemberFull = i.getText();
            }
        }
            try {
                FileWriter myWriter = new FileWriter("members.txt", true);   //true is mandatory
                for (int i = 1; i < members.size(); i++) {
                    String actualGetText = members.get(i).getText();
                    if (!actualGetText.equals(mainMemberFull)) {
                        myWriter.append(mainMemberFull + "\n");
                        if (actualGetText.equals("- szaxofon\n" + "Rácz Dániel - trombita")) {
                            myWriter.append("Vadász Gellért - szaxofon\nFülöp Bence - gitár\nRácz Dániel - trombita");
                        } else {
                            myWriter.append(actualGetText + "\n");
                        }
                    }
                }
                myWriter.close();
            }catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }


