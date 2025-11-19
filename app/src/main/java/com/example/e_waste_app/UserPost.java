package com.example.e_waste_app;

public class UserPost {

    String userName;
    String userEmail;
    String userPhone;
    String profileImage;
    String postImage;

    public UserPost() {}

    // Constructor used in HomeFragment (3 parameters)
    public UserPost(String userName, String profileImage, String postImage) {
        this.userName = userName;
        this.profileImage = profileImage;
        this.postImage = postImage;
    }

    // Full constructor (5 parameters)
    public UserPost(String userName, String userEmail, String userPhone,
                    String profileImage, String postImage) {

        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.profileImage = profileImage;
        this.postImage = postImage;
    }

    public String getUserName() { return userName; }
    public String getUserEmail() { return userEmail; }
    public String getUserPhone() { return userPhone; }
    public String getProfileImage() { return profileImage; }
    public String getPostImage() { return postImage; }
}
