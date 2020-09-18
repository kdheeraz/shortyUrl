package com.example.demo.Endpoints;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.demo.CreateGroup;
import com.example.demo.DBModel;
import com.example.demo.DBModelWithId;
import com.example.demo.DBService;
import com.example.demo.FormValidate;
import com.example.demo.ShortUrlGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;



/**
 *
 * @dheeraj Kumar
 *
 */
@RestController
@RequestMapping("/api")
public class ControllerClass {

    @Autowired
    DBService dbService;
	private Object put;


@RequestMapping(method=RequestMethod.GET,path="/v1/{shortUrl}")

public RedirectView getUrl(@PathVariable final String shortUrl) throws InterruptedException, ExecutionException {

    String url=dbService.getUrl(shortUrl);

    if(url=="Short Link has expired"){
        return new RedirectView("https://support.virtru.com/hc/en-us/articles/360042098293--Your-verification-link-has-expired-error-message");
    }

    
    return new RedirectView(dbService.getUrl(shortUrl));
}


@PostMapping("/v1/create")
    public String createCard(
    

    @RequestBody DBModel dbModel


    
    ) throws InterruptedException, ExecutionException {

        DateTime dateTime= new DateTime();

        System.out.println(dbModel.getDescription());

        System.out.println(dbModel);

        FormValidate formValidate =new FormValidate();
        Boolean isFormValid=formValidate.isFormValid(dbModel);

        if(!isFormValid){

            System.out.println("Not a valid form"+dbModel.getDescription());
            return "Data is not valid";
        }
        else{


     final Map<String, Object> docData = new HashMap<>();


      final ShortUrlGenerator shortUrlGenerator=new ShortUrlGenerator();

     final String shUrl=shortUrlGenerator.randomUrlGen();
     

     System.out.println(shUrl);
    docData.put("title", dbModel.getTitle());
    docData.put("url",dbModel.getUrl() );
    docData.put("group", dbModel.getGroup());
    docData.put("shortUrl", shUrl);
    docData.put("flag", true);
    docData.put("description",dbModel.getDescription());
    put = docData.put("expireThresh",dbModel.getExpTime());
    docData.put("updateTime",dateTime.now().toString("MM/dd/yyyy HH:mm:ss"));

    // Add a new document (asynchronously) in collection "cities" with id "LA"

    dbService.setCardDetails(docData);
    
    //future.get() blocks on response



    return "Card Created";
        }
}

//@RequestMapping("/update/{id}/{title}/{url}/{description}/{group}/{expTime}")
@PostMapping("/v1/update")

public String UpdateCard(@RequestBody DBModelWithId dbModel) throws InterruptedException, ExecutionException 
{

    FormValidate formValidate=new FormValidate();
    Boolean isValid=formValidate.isUpdateFormValid(dbModel);

    if(isValid){

        final Map<String, Object> docData = new HashMap<>();
    final ShortUrlGenerator shortUrlGenerator=new ShortUrlGenerator();
     final String shUrl=shortUrlGenerator.randomUrlGen();
    //System.out.println();
    docData.put("title",dbModel.getTitle());
    docData.put("url", dbModel.getUrl());
    docData.put("flag", true);
    docData.put("group", dbModel.getGroup());
    docData.put("shortUrl", shUrl);
    docData.put("description",dbModel.getDescription());
    docData.put("expireThresh", dbModel.getExpTime());
    // Add a new document (asynchronously) in collection "cities" with id "LA"

    dbService.updateDetails(dbModel.getId(), docData);
    
    // future.get() blocks on response
    System.out.println(dbModel.getDescription()+dbModel.getId());
    return "Updated";

    }

    else{
        return "Can't be updated due to error in card data";
    }

    
}

@PostMapping("/v1/remove")
//@GetMapping()
//@ResponseBody
public String deleteCard(@RequestBody DBModelWithId dbModel) throws InterruptedException, ExecutionException {

    // Add a new document (asynchronously) in collection "cities" with id "LA"

    System.out.println(dbModel.getId());

    dbService.deleteDetails(dbModel.getId());
    
    // future.get() blocks on response
    return "deleted";
}


@PostMapping("/v1/createGroup")

public String createGroup(@RequestBody CreateGroup createGroup) throws InterruptedException, ExecutionException {

    if(createGroup.getName()==null || createGroup.getName()==""){

        return "Please enter a valid group name";
    }

    else{

    // Add a new document (asynchronously) in collection "cities" with id "LA"

    System.out.println(createGroup.getName());

    final Map<String, Object> docData = new HashMap<>();



     docData.put("name", createGroup.getName());
     DBService dbService=new DBService();

     dbService.createGroupService(docData);

    return "Group Created";
    }
}

}