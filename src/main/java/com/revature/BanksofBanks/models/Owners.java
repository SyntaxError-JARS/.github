package com.revature.BanksofBanks.models;

//import com.fasterxml.jackson.annotation.JsonIgnore;

public class Owners {

    // Encapsulated these variables/attributes to the class or instance thereof
    // Another pillar of OOP Encapsulation
    private String fname;
    private String lname;
    private String email;
   // @JsonIgnore
    private Integer age;
    private Integer last4Social;


//    public Trainer(){}

    // public is allowing any instance of class to leverage this command
    // This is a constructor because it's using the class name
    // This requires all atttributes defined above to be passed
    // This assigns each argument to their respective parameter variable being fname, lname, email, etc
    // We assign this instance of the objects the passed argumented.
    // So now, "this" is refering to the instance and we are setting this.fname to equal the passed arugment that was assign fname

    // Overloading constructors another subset of polymorphism
    public Owners(String fname, String lname, String email, Integer age, Integer last4Social) {
        super(); // just always there, by default of EVERY CLASS is Object
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.age = age;
        this.last4Social = last4Social;
    }

    public Owners(Integer last4Social){
        this.last4Social = last4Social;
    }

    // public is allowing any instance of class to leverage this command
    // This is a constructor because it's using the class name
    // this constructor requires no Arguments to be passed.
    public Owners() {

    }

    // Getters & Setters
    public String getFname() {
        return fname;
    }

    // public is letting every instance of this class use the .setFname anywhere.
    // void - this means it will return nothing to me
    // setFname() - to reassign fname attribute
    // method takes in paramerters of String that will be assigned fname
    //this - is the keyword to indicate it's refering to the particular instance of that class
    // this.fname is refering to the attribute in that class
    // we are setting this objects fname attribute to equal the fname that has been provided as an argument
    public void setFname(String fname) {
        this.fname = fname;
    }

    // public is letting every instance of this class use the .getLName anywhere.
    // returning a String value when called
    // getLname() is a method to retrieve this instances lname attribute
    // no arguments required
    // return a string which happens to be our attribute lname
    public String getLname() {
        return lname;
    }

    // trainer.lname = "Jester" is bad, because you could reassign on accident if it were say and int and you did
    // trainer.age = 10;
    // This allows us to explicitly state we are setting the lname variablem, or reassigning it
    // Trainer trainer = new Trainer();
    // trainer.setLname("Jester")
    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getlast4Social() {
        return last4Social;
    }

    public void setlast4Social(Integer last4Social) {
        Integer setlast4Social = null;
        this.last4Social = setlast4Social;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toFileString() {
        // StringBuilder, there is also a StringBuffer (it's thread-safe)
        // Is another class for Strings that allows them to be mutated
        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(fname).append(",")
                .append(lname).append(",")
                .append(email).append(",")
                .append(last4Social).append(",")
                .append(age);

        // Without changing the mutableString class from StringBuilder we wont' have an appropriate return type
        return mutableString.toString(); // We need the toString to return it to it's appropriate type
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return "Owners{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                '}';
    }


}
