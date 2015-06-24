package org.praguehacks.signalizatorJ;

/**
 * Created by kucerajn on 14.6.2015.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.mail.EmailException;
import org.praguehacks.signalizatorJ.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    DocumentManager documentManager;

    @Autowired
    AddressManager addressManager;

    @Autowired
    UserManager userManager;

    @Autowired
    EmailTemplateManager emailTemplateManager;

    @Autowired
    SendEmail sendEmail;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/test")
    public Object test() {
        return "Ahoj Svete!";
    }

    @RequestMapping("/testDB")
    public String testDB() {
        List<Document> docs = new ArrayList<>();//documentManager.getAll();
        docs.add(new Document());
        List<Address> adds = new ArrayList<>();
        //addressManager.getAll();
        adds.add(new Address());
        List<User> users = userManager.getAll();
        List<EmailTemplate> templates = emailTemplateManager.getAll();
        String result =
                "Documents: " + docs.size() + "\n\n" +
                        "Addresses: " + adds.size() + "\n\n" +
                        "Users: " + users.size() + "\n\n" +
                        "templates: " + templates.size() + "\n\n" +
                        "First doc: " + docs.get(0).toString() + "\n\n" +
                        "First Address: " + adds.get(0) + "\n\n" +
                        "First User: " + users.get(0) +
                        "First template: " + templates.get(0);
        return result;
    }
    
    @RequestMapping("/testSelect")
    public String testSelect(){
        List<Address> addresses = addressManager.listItems();
        return String.valueOf(addresses);
    }
    
    @RequestMapping("/testSelectNamed")
    public String testSelectNamed(){
        List<Address> addresses = addressManager.listItemsNamed();
        return String.valueOf(addresses);
    }

    @RequestMapping("/testRectangle")
    public String testRectangle(){
        List<Document> documents = documentManager.findDocumentsInRectangle(14.58,50.075,14.6,50.08);
        return String.valueOf(documents);
    }


    // {"email":"email@email.com", "lowerLeftX":"150", "lowerLeftY":"50", "upperRightX":"150", "upperRightY":"50"}
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public @ResponseBody void saveUser(@RequestBody String usrJson) throws IOException, EmailException {
        System.out.println("\nINFO: saveUser.RequestBody: " + usrJson);
        
        User user = new User();

        HashMap<String, HashMap<String, Object>> result = new ObjectMapper().readValue(usrJson, HashMap.class);

        HashMap<String, Object> params;
        if (result.containsKey("params")) {
             params = result.get("params");
        } else { // assume email and others are stored directly in the json
            params = new ObjectMapper().readValue(usrJson, HashMap.class);
        }
        
        user.setEmail((String) params.get("email"));

        List<User> usersInDB = userManager.findAllByEmail(user.getEmail());
        if (usersInDB != null && usersInDB.size() > 0) {
            user = usersInDB.get(0);
        }

        user.setLowerLeftX((Double) params.get("lowerLeftX"));
        user.setLowerLeftY((Double) params.get("lowerLeftY"));
        user.setUpperRightX((Double) params.get("upperRightX"));
        user.setUpperRightY((Double) params.get("upperRightY"));
        user.setAuthenticated((byte) 1);
        
        Instant instant = Instant.now();
        
        java.util.Date javaDate = Date.from(instant);
        java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
        user.setRegistrationDate(sqlDate);
        
        userManager.save(user);

        sendEmail.setUser(user);
        sendEmail.setTypeWelcome();
        String sendEmailResult = sendEmail.send();

        List<User> users = userManager.getAll();
        System.out.println("\nINFO: saveUser.user: " + user);
//        HttpServletResponse resp = new Response();
//        resp.setHeader("Access-Control-Allow-Origin: *", "");
//        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        return;
    }
}