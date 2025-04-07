package org.example;

import org.example.services.ForexCalcService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ENTER SOURCE CURRENCY: ");
        String sourceCurrency = scanner.nextLine();
        System.out.println("ENTER TARGET CURRENCY: ");
        String targetCurrency = scanner.nextLine();
        System.out.println("ENTER AMOUNT: ");
        BigDecimal amount = new BigDecimal(scanner.nextLine());

        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "org.example"
                );

        ForexCalcService forexCalcService = context.getBean(ForexCalcService.class);

        if (forexCalcService.isSupported(sourceCurrency, targetCurrency)) {
            BigDecimal result = forexCalcService.convert(sourceCurrency, amount, targetCurrency);

            System.out.println(amount + " " + sourceCurrency + " equals to " + result + " " + targetCurrency);
        }
    }
}
