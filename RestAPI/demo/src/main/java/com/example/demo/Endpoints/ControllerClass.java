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
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @dheeraj Kumar
 *
 */
@RestController
//@EnableAutoConfiguration
@RequestMapping("/api")
public class ControllerClass {

    @Autowired
    DBService dbService;
	//private Object put;
    
    //@Autowired
    

    

   
//     public DBModel sayHello() throws InterruptedException, ExecutionException {

//     //return new RedirectView("http://www.google.com");
// //return "Hello World Developer!!!";
// System.out.println(dbService.getPatientDetails("GEAIrD8QsOQYNuKgj7es"));
// return dbService.getPatientDetails("GEAIrD8QsOQYNuKgj7es");
// }

@RequestMapping(method=RequestMethod.GET,path="/v1/{shortUrl}")
//@GetMapping()
//@ResponseBody
public RedirectView getUrl(@PathVariable final String shortUrl) throws InterruptedException, ExecutionException {

    // Add a new document (asynchronously) in collection "cities" with id "LA"

    
    
    // future.get() blocks on response
    return new RedirectView(dbService.getUrl(shortUrl));
}



//@RequestMapping("/create/{title}/{url}/{description}/{group}/{expTime}")
//@CrossOrigin(origins = "http://localhost:3000")
@PostMapping("/v1/create")
//@ResponseBody
//public String createCard(@PathVariable final String title,@PathVariable final String description,@PathVariable final String group,@PathVariable final String url,@PathVariable final String expTime) throws InterruptedException, ExecutionException {
    public String createCard(
    // @RequestParam("title") String title,
    // @RequestParam("description") String description,
    // @RequestParam("url") String url,
    // @RequestParam("group") String group,
    // @RequestParam("expTime") String expTime

    @RequestBody DBModel dbModel


    
    ) throws InterruptedException, ExecutionException {

        System.out.println(dbModel.getDescription());

        // Gson g = new Gson();

        // DBModel dbModel=g.fromJson(alpha,DBModel.class);
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
    docData.put("expireThresh",dbModel.getExpTime());
    // Add a new document (asynchronously) in collection "cities" with id "LA"

    dbService.setPatientDetails(docData);
    
    //future.get() blocks on response



    return "bingo";
        }
}

//@RequestMapping("/update/{id}/{title}/{url}/{description}/{group}/{expTime}")
@PostMapping("/v1/update")
//@ResponseBody
// public String UpdateCard(@RequestParam("id") String id,
//     @RequestParam("title") String title,
// @RequestParam("description") String description,
// @RequestParam("url") String url,
// @RequestParam("group") String group,
// @RequestParam("expireThresh") String expireThresh,
// @RequestParam("expTime") String expTime) throws InterruptedException, ExecutionException

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
//@GetMapping()
//@ResponseBody
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

    //dbService.deleteDetails(dbModel.getId());
    
    // future.get() blocks on response
    return "Group Created";
    }
}


// @RequestMapping("/abcd")
// //@GetMapping()
// @ResponseBody
// public RedirectView urlHit() throws InterruptedException, ExecutionException {

//     // Add a new document (asynchronously) in collection "cities" with id "LA"

// //     dbService.deleteDetails(id);
    
// //     // future.get() blocks on response
// //     return "deleted";

// return new RedirectView("http://www.google.com");

//  }
}