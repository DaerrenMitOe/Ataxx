import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class MatchHistory {
    public MatchHistory() {

    }

    public void createNewJson(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd_HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now)); 
        JSONObject obj = new JSONObject();
        int i = 0;
        String[] a = {
            "a",
            "a",
            "a",
            "a",
            "a",
        };

        JSONArray employeeDetails = new JSONArray();
        for(int i = 0; i < a.length; i++){
            employeeDetails.add(a[i]);
        }
        JSONObject employeeObject = new JSONObject(); 
        employeeObject.put(i, employeeDetails);

        try {
            FileWriter file = new FileWriter(dtf.format(now) + ".json");
            file.write(employeeList.toJSONString()); 
            file.flush();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class WritingJSONArray {
   public static void main(String args[]) {
      //Creating a JSONObject object
      JSONObject jsonObject = new JSONObject();
      //Inserting key-value pairs into the json object
      jsonObject.put("ID", "1");
      jsonObject.put("First_Name", "Krishna Kasyap");
      jsonObject.put("Last_Name", "Bhagavatula");
      jsonObject.put("Date_Of_Birth", "1989-09-26");
      jsonObject.put("Place_Of_Birth", "Vishakhapatnam");
      jsonObject.put("Country", "25000");
      //Creating a json array
      JSONArray array = new JSONArray();
      array.add("e-mail: krishna_kasyap@gmail.com");
      array.add("phone: 9848022338");
      array.add("city: Hyderabad");
      array.add("Area: Madapur");
      array.add("State: Telangana");
      //Adding array to the json object
      jsonObject.put("contact",array);
      try {
         FileWriter file = new FileWriter("E:/json_array_output.json");
         file.write(jsonObject.toJSONString());
         file.close();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      System.out.println("JSON file created: "+jsonObject);
   }
}
Output
JSON file created: {
"First_Name":"Krishna Kasyap",
"Place_Of_Birth":"Vishakhapatnam",
"Last_Name":"Bhagavatula",
"contact":[
"e-mail: krishna_kasyap@gmail.com",
"phone: 9848022338","city: Hyderabad",
"Area: Madapur",
"State: Telangana"],
"Country":"25000",
"ID":"1",
"Date_Of_Birth":"1989-09-26"}