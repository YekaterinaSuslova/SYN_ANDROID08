package suslova.yekaterina.syn_android08;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// СОЗДАТЕЛЬ КОТИРОВОК ВАЛЮТ
public class CountryReader {

    private static final String BASE_URL = "https://immigrantinvest.com/insider/un-rating-2020/"; // Адрес с котировками

    // Парсинг котировок из формата html web-страницы банка, при ошибке доступа возвращаем null
    public static String getRatesData() {
        StringBuilder data = new StringBuilder();
        try {
            Document doc = Jsoup.connect(BASE_URL).timeout(5000).get(); // Создание документа JSOUP из html
            data.append("Топ-10 стран мира в 2019 году\n"); // Считываем заголовок страницы
            data.append(String.format("%12s %12s %12s %12s\n", "место", "страна", "ИЧР", "ИЧР(экология)").trim());
            data.append("\n");
            Elements e = doc.select("div.kgin-widget-container"); // Ищем в документе "<div class="exchange"> с данными о валютах
            Elements tables = e.select("table"); // Ищем таблицы с котировками
            Element table = tables.get(0); // Берем 1 таблицу
            int i = 0;
            // Цикл по строкам таблицы
            for (Element row : table.select("tr")) {
                // Цикл по столбцам таблицы
                for (Element col : row.select("td")) { //
                    data.append(String.format("%12s ", col.text())); // Считываем данные с ячейки таблицы
                }
                data.append("\n"); // Добавляем переход на следующую строку;
            }
        } catch (Exception ignored) {
            return null; // При ошибке доступа возвращаем null
        }
        return data.toString().trim(); // Возвращаем результат
    }

}