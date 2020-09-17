package com.example.demo;

public class FormValidate {

    public Boolean isFormValid(DBModel dbModel){

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

    public Boolean isUpdateFormValid(DBModelWithId dbModel){

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
