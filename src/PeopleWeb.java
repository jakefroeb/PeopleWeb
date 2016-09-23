import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//id,first_name,last_name,email,country,ip_address

public class PeopleWeb {
static ArrayList<Person> people = new ArrayList<>();

    static int index=0;

    public static void main(String[] args) throws Exception{
        readFile();
        Spark.init();
        Spark.get("/",
                (request, response) -> {
                    HashMap m = new HashMap();
                    String offset = request.queryParams("offset");
                    int offsetNum = 0;
                    if(offset != null){
                        offsetNum = Integer.parseInt(offset);
                    }
                    ArrayList<Person> these20people = new ArrayList();
                        for (int i=offsetNum;i<offsetNum+20;i++) {
                            these20people.add(people.get(i));
                        }
                    Integer next = null;
                    Integer previous = null;
                    if (offsetNum < people.size() - 20) {
                        next = offsetNum + 20;
                    }
                    if(offsetNum >= 20){
                        previous = offsetNum - 20;
                    }
                    m.put("next", next);
                    m.put("previous", previous);
                    m.put("people", these20people);
                    return new ModelAndView(m, "people.html");
                }, new MustacheTemplateEngine());
        Spark.get("/person",
                (request, response) -> {
                    HashMap m = new HashMap();
                    String id = request.queryParams("id");
                    int personId = 0;
                    if(id != null){
                        personId=Integer.parseInt(id);
                    }
                    Person person = people.get(personId-1);
                    m.put("person", person.toString());
                    return new ModelAndView(m,"person.html");
                }, new MustacheTemplateEngine());


    }

    static void readFile()throws Exception{
        File f = new File("people.csv");
        Scanner fileScanner = new Scanner(f);
        String line = fileScanner.nextLine(); //initializing here to skip first line
        while (fileScanner.hasNext()) {
            line = fileScanner.nextLine();
            String[] columns = line.split("\\,");
            Person person = new Person(Integer.parseInt(columns[0]),columns[1],columns[2],columns[3],columns[4],columns[5]);
            people.add(person);
        }
        fileScanner.close();
    }
}
