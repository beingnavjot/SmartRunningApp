package com.navjot.finalprojectapp;



public class ProfileData  {

    private String userId;
    private String userName;
    private String userEmail;
    private String userMobileNo;
    private String userWeight;
    private String userAge;

     public ProfileData(){

     }

    public ProfileData(String userId, String userName, String userEmail, String userMobileNo, String userWeight, String userAge) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userMobileNo = userMobileNo;
        this.userWeight = userWeight;
        this.userAge = userAge;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobileNo() {
        return userMobileNo;
    }

    public void setUserMobileNo(String userMobileNo) {
        this.userMobileNo = userMobileNo;
    }

    public String getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }


}
