package com.example.jwt_authentication.models;


public class JwtRequest {

    private String userName;
    private String password;

    public JwtRequest() {
    }

    public JwtRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static JwtRequestBuilder builder() {
        return new JwtRequestBuilder();
    }

    public static class JwtRequestBuilder {
        private String userName;
        private String password;

        public JwtRequestBuilder() {
        }

        public JwtRequestBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public JwtRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public JwtRequest build() {
            return new JwtRequest(userName, password);
        }
    }
}
