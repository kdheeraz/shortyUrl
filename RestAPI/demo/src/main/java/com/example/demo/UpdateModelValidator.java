package com.example.demo;

public class UpdateModelValidator {
    

    public Boolean isFormValid(DBModelWithId dbModel){

        if((dbModel.getDescription()==null ||dbModel.getDescription()=="" )||

        (dbModel.getTitle()==null ||dbModel.getTitle()=="" )||
        (dbModel.getGroup()==null ||dbModel.getGroup()=="" )||
        (dbModel.getUrl()==null ||dbModel.getUrl()=="" )||
        (dbModel.getExpTime()<1 )
        
    )
     return false;
{

        }

        return true;

    }

    
    
}
