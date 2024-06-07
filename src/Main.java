import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.Map;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/730adf889ff273087c00ee18/latest/USD"))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        Gson gson = new Gson();

        ExchangeRates exchangeRates = gson.fromJson(response.body(), ExchangeRates.class);

        Map<String, Double> conversionRates = exchangeRates.getConversionRates();


        Scanner scanner = new Scanner(System.in);

        String mensajeInicial = """
                ****************************************************
                Sea bienvenid@ al conversor de moneda
                
                1. Dolar -> Peso Argentino
                2. Peso Argentino -> Dolar
                3. Dolar -> Real Brasileño
                4. Real Brasileño -> Dolar 
                5. Dolar -> Peso Colombiano
                6. Peso Colombiano -> Dolar
                7. Salir
                Elija una opción valida:
                
                """;

        boolean flag = true;
        while (flag == true){
            System.out.println(mensajeInicial);
            String num = scanner.nextLine();
            System.out.println("Ingresa la cantidad:");
            double cantidad = scanner.nextDouble();
            double deMoneda;
            double aMoneda;
            switch (num){
                case "1":
                    deMoneda = conversionRates.getOrDefault("USD", 1.0);
                    aMoneda  = conversionRates.getOrDefault("ARS", 1.0);
                    System.out.println("Resultado: "+ cantidad * (aMoneda / deMoneda));
                    break;
                case "2":
                    deMoneda = conversionRates.getOrDefault("ARS", 1.0);
                    aMoneda  = conversionRates.getOrDefault("USD", 1.0);
                    System.out.println("Resultado: "+ cantidad * (aMoneda / deMoneda));
                    break;
                case "3":
                    deMoneda = conversionRates.getOrDefault("USD", 1.0);
                    aMoneda  = conversionRates.getOrDefault("BRL", 1.0);
                    System.out.println("Resultado: "+ cantidad * (aMoneda / deMoneda));
                    break;
                case "4":
                    deMoneda = conversionRates.getOrDefault("BRL", 1.0);
                    aMoneda  = conversionRates.getOrDefault("USD", 1.0);
                    System.out.println("Resultado: "+ cantidad * (aMoneda / deMoneda));
                    break;
                case "5":
                    deMoneda = conversionRates.getOrDefault("USD", 1.0);
                    aMoneda  = conversionRates.getOrDefault("COP", 1.0);
                    System.out.println("Resultado: "+ cantidad * (aMoneda / deMoneda));
                    break;
                case "6":
                    deMoneda = conversionRates.getOrDefault("COP", 1.0);
                    aMoneda  = conversionRates.getOrDefault("USD", 1.0);
                    System.out.println("Resultado: "+ cantidad * (aMoneda / deMoneda));
                    break;
                case "7":
                    flag = false;
                    break;
                default:
                    System.out.println("numero no valido");
                    flag = false;
                    break;
            }

        }



    }

    class ExchangeRates {
        private Map<String, Double> conversion_rates;

        public Map<String, Double> getConversionRates() {
            return conversion_rates;
        }
    }
}