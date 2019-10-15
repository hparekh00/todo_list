package com.example.todolist;

public class TodoItems {

    // store the name of the title of the task
    private String mtitle;

    // store the name of the description of the task
    private String mdescription;


    // Constructor to create an instance of the TodoItems object
    public TodoItems(String mtitle, String mdescription){
        this.mtitle = mtitle;
        this.mdescription = mdescription;
    }

    public String getMtitle(){
        return mtitle;
    }

    public String getMdescription(){
        return mdescription;
    }

    public void setMtitle(){
        this.mtitle = mtitle;
    }

    public void setMdescription(){
        this.mdescription = mdescription;
    }

}
